package sobrevivencia.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D.Float;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Iterator;
import java.util.List;
import sobrevivencia.Init;
import sobrevivencia.acao.PontosSpawn;
import sobrevivencia.astar.Astar;
import sobrevivencia.astar.Node;
import sobrevivencia.astar.Vector2;
import sobrevivencia.coletaveis.MedKit;
import sobrevivencia.jogador.Jogador;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.projeteis.DanoAtkArmas;
import sobrevivencia.projeteis.ProjetilIni;
import sobrevivencia.som.Som;
import sobrevivencia.soudEffects.SoundEffects;
import sobrevivencia.spritesheet.SpriteSheet;

public class Inimigos extends AbstractEntidade {
   protected double velocidade = 2.0D;
   protected double acresVel = 0.5D;
   protected boolean hit;
   protected double vida;
   protected Som hurt = new Som(SoundEffects.getHURT());
   protected Som bloodSplat = new Som(SoundEffects.getBLOOD_SPLAT());
   public double tempo = 100.0D;
   private int offset = 96;
   int xi;
   int yi;
   int chanceDropMedKit = 0;
   int chanceDropBomba;
   int cargaAtual = 30;
   int carga = 30;
   int tempoRecarregando;
   int xx;
   int yy;
   boolean jogadorEstaVendo;

   public Inimigos(float x, float y, int largura, int altura) {
      super(x, y, largura, altura);
      this.spriteCaminhada = new SpriteSheet("/SoldadoInimigo.png");
      this.spriteAtira = new SpriteSheet("/SoldadoInimigoAtirando.png");
      this.iniciaBufferedImg();
      this.updateVida();
   }

   void iniciaBufferedImg() {
      int tamanho = 96;
      int frames = this.spriteCaminhada.getImg().getWidth() / tamanho;
      this.caminhada = new BufferedImage[frames];

      int i;
      for(i = 0; i < frames; ++i) {
         this.caminhada[i] = this.spriteCaminhada.getImg(i * tamanho, 0, tamanho, tamanho);
      }

      this.atira = new BufferedImage[frames];

      for(i = 0; i < frames; ++i) {
         this.atira[i] = this.spriteAtira.getImg(i * tamanho, 0, tamanho, tamanho);
      }

   }

   public void acao() {
      this.updateLocale();
      this.initAngle();
      this.segueOjogador();
      this.validaVida();
      this.iniciaAni();
      this.iniciaRectangle();
      this.updateFireRate();
      this.setMask(3, 3, this.getLargura() - 6, this.getAltura() - 6);
      this.setChanceDropMedKit(200);
   }

   void updateLocale() {
      this.xi = (int)(this.getX() + (float)(this.offset / 6));
      this.yi = (int)(this.getY() + (float)(this.offset / 2));
   }

   public int getXi() {
      return this.xi;
   }

   public int getYi() {
      return this.yi;
   }

   private double iniciaAngulo(double y1, double y2, double x1, double x2) {
      return Math.atan2(y1 - y2, x1 - x2);
   }

   void initAngle() {
      this.setAngulo(this.iniciaAngulo((double)Init.getJogador().getY() + (double)(Init.getJogador().getOffset() / 2) - 16.0D, (double)this.getY(), (double)Init.getJogador().getX(), (double)this.getX()));
   }

   void updateFireRate() {
      String var1 = Opcoes.qualDificuldade();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1039745817:
         if (var1.equals("normal")) {
            var2 = 1;
         }
         break;
      case 97187371:
         if (var1.equals("facil")) {
            var2 = 0;
         }
      }

      switch(var2) {
      case 0:
         this.setFire(20);
         break;
      case 1:
         this.setFire(15);
         break;
      default:
         this.setFire(10);
      }

   }

   void updateVida() {
      String var1 = Opcoes.qualDificuldade();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1039745817:
         if (var1.equals("normal")) {
            var2 = 1;
         }
         break;
      case 97187371:
         if (var1.equals("facil")) {
            var2 = 0;
         }
      }

      switch(var2) {
      case 0:
         this.setVida(100.0D);
         break;
      case 1:
         this.setVida(100.0D);
         break;
      default:
         this.setVida(150.0D);
      }

   }

   void updateVelocidade() {
      String var1 = Opcoes.qualDificuldade();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1039745817:
         if (var1.equals("normal")) {
            var2 = 1;
         }
         break;
      case 97187371:
         if (var1.equals("facil")) {
            var2 = 0;
         }
      }

      switch(var2) {
      case 0:
         this.setVelocidade(2.0D);
         break;
      case 1:
         this.setVelocidade(2.0D);
         break;
      default:
         this.setVelocidade(3.0D);
      }

   }

   void validaVida() {
      if (this.isHit()) {
         this.setHit(false);
         if (!Jogador.estaMirandoHelicoptero) {
            this.setMenosVida(Init.getJogador().getProj().getDanoAtk());
            if (this.vida <= 0.0D) {
               PontosSpawn.alteraContador(1);
               PontosSpawn.alteraRecord(1);
               Init.removeIni(this);
               this.getHurt().play();
               this.gerarExplosao(80, (int)this.getX(), (int)this.getY(), 6, 6, 20, Color.red, 1.3D, this.getBloodSplat());
               if (this.getRandom().nextInt(100) >= this.getChanceDropMedKit()) {
                  Init.addVida(new MedKit((float)((int)this.getX()), (float)((int)this.getY()), 0, 0));
               }

               if (this.getChanceDropBomba() >= 2) {
                  Init.dropBomba(new Bomba((float)((int)this.getX()), (float)((int)this.getY()), 20, 20));
               }
            }
         }
      }

   }

   void atira() {
      double dx = Math.cos(this.getAngulo());
      double dy = Math.sin(this.getAngulo());
      int fullLenght = Math.round(18.0F);
      int xxi = (int)Math.round(dx * (double)fullLenght);
      int yyi = (int)Math.round(dy * (double)fullLenght);
      int tamanho = 10;
      int tempoVida = 50;
      ++this.fireRate;
      if (this.getFireRate() >= this.getFire()) {
         this.setFireRate(0);
         if (this.cargaAtual > 0) {
            this.setEstaAtirando(true);
            ProjetilIni pInimigo1 = new ProjetilIni(this.getX() + 8.0F + (float)xxi, this.getY() + 8.0F + (float)yyi, tamanho, dx, dy, (double)DanoAtkArmas.getDanoM4(), tempoVida, Color.MAGENTA, new Som(SoundEffects.getTIRO_M4A1()));
            Init.addProjetil(pInimigo1);
            this.diminuiCargaAtual();
         }
      }

      this.recarregar();
   }

   void diminuiCargaAtual() {
      String var1 = Opcoes.qualDificuldade();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1039745817:
         if (var1.equals("normal")) {
            var2 = 1;
         }
         break;
      case 97187371:
         if (var1.equals("facil")) {
            var2 = 0;
         }
         break;
      case 1659541438:
         if (var1.equals("dificil")) {
            var2 = 2;
         }
      }

      switch(var2) {
      case 0:
         --this.cargaAtual;
         break;
      case 1:
         --this.cargaAtual;
         break;
      case 2:
         --this.cargaAtual;
      }

   }

   void recarregar() {
      String var1 = Opcoes.qualDificuldade();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1039745817:
         if (var1.equals("normal")) {
            var2 = 1;
         }
         break;
      case 97187371:
         if (var1.equals("facil")) {
            var2 = 0;
         }
         break;
      case 1659541438:
         if (var1.equals("dificil")) {
            var2 = 2;
         }
      }

      switch(var2) {
      case 0:
         this.iniciaReload();
         break;
      case 1:
         this.iniciaReload();
         break;
      case 2:
         this.iniciaReload();
      }

   }

   void iniciaReload() {
      if (this.cargaAtual <= 0) {
         ++this.tempoRecarregando;
         if (this.tempoRecarregando >= 100) {
            this.tempoRecarregando = 0;
            this.cargaAtual = this.carga;
         }
      }

   }

   void segueOjogador() {
      int jx;
      if (!this.isEstouVendo()) {
         if (!PontosSpawn.modoTreino) {
            this.setMoveu(true);
            this.setEstaAtirando(false);
            jx = (int)Init.getJogador().getX() + Init.getJogador().getOffset() / 6;
            int jy = (int)Init.getJogador().getY() + Init.getJogador().getOffset() / 2;
            int tamanho = 32;
            Vector2 start;
            Vector2 end;
            if (this.path == null || this.path.isEmpty()) {
               start = new Vector2((int)this.getX() / tamanho, (int)this.getY() / tamanho);
               end = new Vector2(jx / tamanho, jy / tamanho);
               this.path = Astar.findPath(Init.getMapa(), start, end);
            }

            if (this.getRandom().nextInt(100) >= 10) {
               start = new Vector2((int)this.getX() / tamanho, (int)this.getY() / tamanho);
               end = new Vector2(jx / tamanho, jy / tamanho);
               this.path = Astar.findPath(Init.getMapa(), start, end);
            }

            this.followPath(this.path);
         }
      } else {
         jx = this.r.nextInt(2);
         this.setMoveu(false);
         this.atira();
      }

   }

   public void graficos(Graphics g) {
      this.iniciaRayCasting(g);
      int tamanho = 96;
      this.setXx((int)this.getX());
      this.setYy((int)this.getY() - 32);
      Graphics2D g2 = (Graphics2D)g.create();
      this.mostraHitBox(g2);
      AffineTransform at = g2.getTransform();
      g2.rotate(this.getAngulo(), (double)this.getXi(), (double)(this.getY() + 16.0F));
      if (!this.isEstaAtirando()) {
         g2.drawImage(this.getCaminhada()[this.getIndex()], this.getXx(), this.getYy(), tamanho, tamanho, (ImageObserver)null);
      } else {
         g2.drawImage(this.getAtira()[this.getInd()], this.getXx(), this.getYy(), tamanho, tamanho, (ImageObserver)null);
      }

      g2.dispose();
      g2.setTransform(at);
   }

   void mostraHitBox(Graphics2D g2) {
      if (PontosSpawn.modoTreino) {
         g2.setColor(Color.red);
         g2.draw(this.getHitBox());
         g2.setColor(Color.WHITE);
         g2.draw(this.getEne());
      } else {
         String var2 = Opcoes.qualDificuldade();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case 97187371:
            if (var2.equals("facil")) {
               var3 = 0;
            }
         default:
            switch(var3) {
            case 0:
               g2.setColor(Color.red);
               g2.draw(this.getHitBox());
               g2.setColor(Color.blue);
               g2.draw(this.getEne());
            }
         }
      }

   }

   protected void jogadorEstaVendo() {
      this.setJogadorEstaVendo(false);
      Iterator var1 = Init.getRays().iterator();

      while(var1.hasNext()) {
         Float ray = (Float)var1.next();
         this.setJogadorEstaVendo(ray.intersects(this.getEne()));
      }

   }

   protected void followPath(List<Node> path) {
      int tamanho = 32;
      if (path != null && !path.isEmpty()) {
         Vector2 target = ((Node)path.get(path.size() - 1)).tile;
         if (this.getX() < (float)(target.x * tamanho) && !this.caminhoLivre((int)((double)this.getX() + this.getVelocidade()), (int)this.getY())) {
            this.paraDireita((float)this.getVelocidade());
         } else if (this.getX() > (float)(target.x * tamanho) && !this.caminhoLivre((int)((double)this.getX() - this.getVelocidade()), (int)this.getY())) {
            this.paraEsquerda((float)this.getVelocidade());
         }

         if (this.getY() < (float)(target.y * tamanho) && !this.caminhoLivre((int)this.getX(), (int)((double)this.getY() + this.getVelocidade()))) {
            this.paraBaixo((float)this.getVelocidade());
         } else if (this.getY() > (float)(target.y * tamanho) && !this.caminhoLivre((int)this.getX(), (int)((double)this.getY() - this.getVelocidade()))) {
            this.paraCima((float)this.getVelocidade());
         } else if (this.getX() == (float)(target.x * tamanho) && this.getY() == (float)(target.y * tamanho)) {
            path.remove(path.size() - 1);
         }
      }

   }

   protected double getVelocidade() {
      return this.velocidade;
   }

   protected void setVelocidade(double velocidade) {
      this.velocidade = velocidade;
   }

   protected double getAcresVel() {
      return this.acresVel;
   }

   protected void setAcresVel(double acresVel) {
      this.acresVel = acresVel;
   }

   protected Som getHurt() {
      return this.hurt;
   }

   protected void setHurt(Som hurt) {
      this.hurt = hurt;
   }

   protected Som getBloodSplat() {
      return this.bloodSplat;
   }

   protected void setBloodSplat(Som bloodSplat) {
      this.bloodSplat = bloodSplat;
   }

   protected double getTempo() {
      return this.tempo;
   }

   protected void setTempo(double tempo) {
      this.tempo = tempo;
   }

   protected int getChanceDropMedKit() {
      return this.chanceDropMedKit;
   }

   protected void setChanceDropMedKit(int chanceDropMedKit) {
      this.chanceDropMedKit = chanceDropMedKit;
   }

   protected int getChanceDropBomba() {
      return this.chanceDropBomba;
   }

   protected void setChanceDropBomba(int chanceDropBomba) {
      this.chanceDropBomba = chanceDropBomba;
   }

   protected int getXx() {
      return this.xx;
   }

   protected void setXx(int xx) {
      this.xx = xx;
   }

   protected int getYy() {
      return this.yy;
   }

   protected void setYy(int yy) {
      this.yy = yy;
   }

   protected boolean isJogadorEstaVendo() {
      return this.jogadorEstaVendo;
   }

   protected void setJogadorEstaVendo(boolean jogadorEstaVendo) {
      this.jogadorEstaVendo = jogadorEstaVendo;
   }

   public boolean isHit() {
      return this.hit;
   }

   public void setHit(boolean hit) {
      this.hit = hit;
   }

   public double getVida() {
      return this.vida;
   }

   public void setVida(double vida) {
      this.vida = vida;
   }

   public void setMenosVida(double danoAtk) {
      this.vida -= danoAtk;
   }

   public String toString() {
      return "Inimigos{vida=" + this.vida + ", xx=" + this.xx + ", yy=" + this.yy + '}';
   }
}
