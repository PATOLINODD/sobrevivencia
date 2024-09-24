package sobrevivencia.jogador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Iterator;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.janela.Interface;
import sobrevivencia.mapa.Parede;
import sobrevivencia.mapa.ParedesLimite;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.mouse.MouseXY;
import sobrevivencia.projeteis.DanoAtkArmas;
import sobrevivencia.projeteis.ProjeteisM4;
import sobrevivencia.som.Som;
import sobrevivencia.soudEffects.SoundEffects;
import sobrevivencia.spritesheet.SpriteSheet;

public class Jogador extends AbstractEntidade {
   public static String acao = "";
   public static boolean direita;
   public static boolean esquerda;
   public static boolean cima;
   public static boolean baixo;
   public static boolean recarregar;
   public static boolean novoJogo;
   public static boolean mouseAtira;
   public static boolean moveu;
   public static boolean lancaGranada;
   public static boolean miniMapa;
   public static boolean emCombate;
   public static boolean recebendoAtk;
   public static boolean estaMirandoHelicoptero;
   public static int miraHelic;
   public double vida = 1000.0D;
   public double tempoProximoAtk = 20.0D;
   SpriteSheet sprit = new SpriteSheet("/Soldado.png");
   SpriteSheet spriteAtira = new SpriteSheet("/SoldadoAtirando.png");
   BufferedImage[] img;
   BufferedImage[] imgAtira;
   int ax;
   int ay;
   private final float velocidade = 3.0F;
   private Rectangle rectangle = new Rectangle();
   int tempoForaCombate;
   boolean recuperado;
   int tempo = 0;
   int index;
   int tempoAtira;
   int ind;
   int fireRate = 10;
   private int municao = 1000;
   private int tempoCarregamento = 0;
   private int cargaAtual = 30;
   private boolean atirou;
   private ProjeteisM4 proj;
   private double angulo;
   private int offset = 96;

   public Jogador(float x, float y, int largura, int altura) {
      super(x, y, largura, altura);
      int larg = 96;
      int framesCaminhada = this.sprit.getImg().getWidth() / larg;
      int framesAtirando = this.spriteAtira.getImg().getWidth() / larg;
      this.img = new BufferedImage[framesCaminhada];
      this.imgAtira = new BufferedImage[framesAtirando];
      System.out.println(this.imgAtira.length);

      int i;
      for(i = 0; i < this.img.length; ++i) {
         this.img[i] = this.sprit.getImg(i * larg, 0, larg, larg);
      }

      for(i = 0; i < this.imgAtira.length; ++i) {
         this.imgAtira[i] = this.spriteAtira.getImg(i * larg, 0, larg, larg);
      }

   }

   public void acao() {
      recebendoAtk = false;
      acao = "PARADO";
      moveu = false;
      int x = (int)this.getX();
      int y = (int)(this.getY() + 32.0F);
      if (direita && !this.caminhoLivre((int)((float)x + 3.0F), y)) {
         moveu = true;
         this.paraDireita(3.0F);
      }

      if (esquerda && !this.caminhoLivre((int)((float)x - 3.0F), y)) {
         moveu = true;
         this.paraEsquerda(3.0F);
      }

      if (cima && !this.caminhoLivre(x, (int)((float)y - 3.0F))) {
         moveu = true;
         this.paraCima(3.0F);
      }

      if (baixo && !this.caminhoLivre(x, (int)((float)y + 3.0F))) {
         moveu = true;
         this.paraBaixo(3.0F);
      }

      if (novoJogo) {
         novoJogo = false;
         this.vida = 1000.0D;
      }

      if (this.vida >= 1000.0D) {
         this.vida = 1000.0D;
      }

      this.emCombate();
      this.retangulo();
      this.iniciaAnimacao();
      this.atira();
   }

   private void retangulo() {
      double angulo1 = Math.atan2((double)((float)MouseXY.getY() - (this.getY() + (float)(this.offset / 2) + Init.getCam().getY())), (double)((float)MouseXY.getX() - (this.getX() + (float)(this.offset / 6) + Init.getCam().getX())));
      int xi = Math.round((float)Math.cos(angulo1));
      int yi = Math.round((float)Math.sin(angulo1));
      int aax = (int)((float)xi + this.getX() + 5.0F);
      int aay = (int)((float)yi + this.getY() + 38.0F);
      this.rectangle = new Rectangle(aax, aay, 17, 20);
      AffineTransform at = AffineTransform.getRotateInstance(angulo1, (double)(this.getX() + (float)(this.offset / 6)), (double)(this.getY() + (float)(this.offset / 2)));
      this.rectangle = at.createTransformedShape(this.rectangle).getBounds();
   }

   private void emCombate() {
      if (!recebendoAtk && emCombate) {
         ++this.tempoForaCombate;
         if (this.tempoForaCombate == 100) {
            this.tempoForaCombate = 0;
            emCombate = false;
         }
      }

      if (recebendoAtk) {
         this.tempoForaCombate = 0;
         this.recuperado = true;
      }

      if (!emCombate) {
         if (this.vida < Interface.delay) {
            ++this.vida;
         }

         if (this.vida >= Interface.delay && this.recuperado) {
            this.recuperado = false;
            this.vida = Interface.delay;
         }
      }

   }

   public void graficos(Graphics g) {
      this.animacao(g);
      this.tempoCarregando(g);
   }

   void iniciaAnimacao() {
      ++this.tempo;
      if (this.tempo == 10) {
         this.tempo = 0;
         ++this.index;
         if (this.index == this.img.length) {
            this.index = 0;
         }
      }

      if (mouseAtira) {
         ++this.tempoAtira;
         if (this.tempoAtira == 5) {
            this.tempoAtira = 0;
            ++this.ind;
            if (this.ind == this.imgAtira.length) {
               this.ind = 0;
            }
         }
      } else {
         this.ind = 0;
      }

   }

   void animacao(Graphics g) {
      int x = (int)this.getX();
      int y = (int)this.getY();
      int tamanho = 96;
      Graphics2D g2 = (Graphics2D)g.create();
      AffineTransform at = g2.getTransform();
      g2.rotate(this.angulo, (double)(this.getX() + (float)(this.offset / 6)), (double)(this.getY() + (float)(this.offset / 2)));
      String var7;
      byte var8;
      if (!moveu) {
         var7 = acao;
         var8 = -1;
         switch(var7.hashCode()) {
         case -1942098837:
            if (var7.equals("PARADO")) {
               var8 = 0;
            }
            break;
         case -557257685:
            if (var7.equals("RECARREGANDO")) {
               var8 = 2;
            }
            break;
         case 1022090260:
            if (var7.equals("ATIRANDO")) {
               var8 = 1;
            }
         }

         switch(var8) {
         case 0:
            this.index = 0;
            g2.drawImage(this.img[this.index], x, y, tamanho, tamanho, (ImageObserver)null);
            break;
         case 1:
            g2.drawImage(this.imgAtira[this.ind], x, y, tamanho, tamanho, (ImageObserver)null);
            break;
         case 2:
            this.index = 0;
            g2.drawImage(this.img[this.index], x, y, tamanho, tamanho, (ImageObserver)null);
         }
      } else {
         var7 = acao;
         var8 = -1;
         switch(var7.hashCode()) {
         case -1942098837:
            if (var7.equals("PARADO")) {
               var8 = 0;
            }
            break;
         case -557257685:
            if (var7.equals("RECARREGANDO")) {
               var8 = 2;
            }
            break;
         case 1022090260:
            if (var7.equals("ATIRANDO")) {
               var8 = 1;
            }
         }

         switch(var8) {
         case 0:
            g2.drawImage(this.img[this.index], x, y, tamanho, tamanho, (ImageObserver)null);
            break;
         case 1:
            g2.drawImage(this.imgAtira[this.ind], x, y, tamanho, tamanho, (ImageObserver)null);
            break;
         case 2:
            g2.drawImage(this.img[this.index], x, y, tamanho, tamanho, (ImageObserver)null);
         }
      }

      g2.setTransform(at);
   }

   void tempoCarregando(Graphics g) {
      g.setColor(Color.cyan);
      g.fillRect((int)this.getX() - 40, (int)this.getY() + this.getSpriteAtira().getImg().getHeight(), this.getTempoCarregamento(), 5);
   }

   public void atira() {
      this.angulo = Math.atan2((double)((float)(MouseXY.getY() - 16) - (this.getY() + (float)(this.offset / 2) + Init.getCam().getY())), (double)((float)(MouseXY.getX() - 16) - (this.getX() + (float)(this.offset / 6) + Init.getCam().getX())));
      double dx = Math.cos(this.angulo);
      double dy = Math.sin(this.angulo);
      int fullLenght = Math.round(15.0F);
      int xx = Math.round((float)(dx * (double)fullLenght));
      int yy = Math.round((float)(dy * (double)fullLenght));
      int fullLenght2 = Math.round(40.0F);
      int xx2 = Math.round((float)(dx * (double)fullLenght2));
      int yy2 = Math.round((float)(dy * (double)fullLenght2));
      if (mouseAtira && this.cargaAtual > 0 && !recarregar) {
         acao = "ATIRANDO";
         this.atirou = true;
         if (this.fireRate == 10) {
            --this.cargaAtual;
            int tempoVida = 50;
            int tamanho = 10;
            this.proj = new ProjeteisM4(this.getX() + (float)(this.offset / 6) - 5.0F + (float)xx, this.getY() + (float)(this.offset / 2) - 5.0F + (float)yy, tamanho, dx, dy, (double)DanoAtkArmas.getDanoM4(), tempoVida, Color.yellow, new Som(SoundEffects.getTIRO_M4A1()));
            Init.addProjetil(this.proj);
            this.gerarExplosao(30, (int)(this.getX() + (float)(this.offset / 6) - 5.0F + (float)xx2), (int)(this.getY() + (float)(this.offset / 2) - 5.0F + (float)yy2), 3, 3, 10, Color.YELLOW.brighter().brighter(), 2.0D, (Som)null);
         }
      }

      this.recarregar();
      if (this.atirou) {
         --this.fireRate;
         if (this.fireRate <= 0) {
            if (this.cargaAtual <= 0 && !recarregar) {
               (new Som(SoundEffects.getEMPTY_GUN_SHOT())).play();
            }

            this.fireRate = 10;
            this.atirou = false;
         }
      }

   }

   private void recarregar() {
      String var1 = Opcoes.qualDificuldade();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case 97187371:
         if (var1.equals("facil")) {
            var2 = 0;
         }
      default:
         switch(var2) {
         case 0:
            if (this.cargaAtual <= 0) {
               recarregar = true;
            }
         }

         if (recarregar) {
            acao = "RECARREGANDO";
            if (this.municao > 0 && this.cargaAtual < 30) {
               ++this.tempoCarregamento;
               if (this.tempoCarregamento == 1) {
                  (new Som(SoundEffects.getRELOAD_M4())).play();
               }

               if (this.tempoCarregamento >= 130) {
                  recarregar = false;
                  this.tempoCarregamento = 0;
                  int carga = 30 - this.cargaAtual;
                  if (this.municao - carga < 0) {
                     this.cargaAtual += this.municao;
                     this.municao -= this.municao;
                  } else {
                     this.cargaAtual += carga;
                  }
               }
            } else {
               recarregar = false;
            }
         }

      }
   }

   public boolean caminhoLivre(int x, int y) {
      this.setRetangulo(new Rectangle(x, y, this.getLargura(), this.getAltura()));
      Iterator var3 = Init.getEntidades().iterator();

      while(var3.hasNext()) {
         AbstractEntidade ent = (AbstractEntidade)var3.next();
         if (ent != this && ent instanceof Inimigos) {
            Inimigos ini = (Inimigos)ent;
            if (ini.getEne().intersects(this.getRetangulo())) {
               return true;
            }
         }
      }

      var3 = Init.getParedes().iterator();

      Parede p;
      do {
         if (!var3.hasNext()) {
            var3 = Init.getParedesParaJogador().iterator();

            ParedesLimite pl;
            do {
               if (!var3.hasNext()) {
                  return false;
               }

               pl = (ParedesLimite)var3.next();
            } while(!pl.getRectangle().intersects(this.getRetangulo()));

            return true;
         }

         p = (Parede)var3.next();
      } while(!p.getRectangle().intersects(this.getRetangulo()));

      return true;
   }

   public void mostraHitBox(Graphics g) {
      Graphics2D g2 = (Graphics2D)g.create();
      g2.setColor(Color.red);
      g2.draw(this.getRetangulo());
      g2.setColor(Color.white);
      g2.draw(this.rectangle);
   }

   public double getVida() {
      return this.vida;
   }

   public void setVida(double vida) {
      this.vida = vida;
   }

   public void alteraVida(double danoAtk) {
      this.vida += danoAtk;
   }

   public ProjeteisM4 getProj() {
      return this.proj;
   }

   public int getMunicao() {
      return this.municao;
   }

   public void setMunicao(int municao) {
      this.municao = municao;
   }

   public void addMunicao(int municao) {
      this.municao += municao;
   }

   public int getTempoCarregamento() {
      return this.tempoCarregamento;
   }

   public void setTempoCarregamento(int tempoCarregamento) {
      this.tempoCarregamento = tempoCarregamento;
   }

   public int getCargaAtual() {
      return this.cargaAtual;
   }

   public void setCargaAtual(int cargaAtual) {
      this.cargaAtual = cargaAtual;
   }

   public boolean isAtirou() {
      return this.atirou;
   }

   public void setAtirou(boolean atirou) {
      this.atirou = atirou;
   }

   public Rectangle getRectangle() {
      return this.rectangle;
   }

   public int getOffset() {
      return this.offset;
   }
}
