package sobrevivencia.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D.Float;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import sobrevivencia.Init;
import sobrevivencia.acao.PontosSpawn;
import sobrevivencia.astar.Node;
import sobrevivencia.explosoes.Explosao;
import sobrevivencia.janela.Janela;
import sobrevivencia.mapa.Parede;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.som.Som;
import sobrevivencia.spritesheet.SpriteSheet;

public abstract class AbstractEntidade {
   protected final Logger log = Logger.getLogger(AbstractEntidade.class.getName());
   private float x;
   private float y;
   private int largura;
   private int altura;
   protected boolean estouVendo;
   boolean moveu;
   boolean estaAtirando;
   List<Node> path;
   Random r = new Random();
   SpriteSheet spriteCaminhada = new SpriteSheet("/SoldadoInimigo.png");
   SpriteSheet spriteAtira = new SpriteSheet("/SoldadoInimigoAtirando.png");
   BufferedImage[] caminhada;
   BufferedImage[] atira;
   protected int maskX;
   protected int maskY;
   protected int maskLargura;
   protected int maskAltura;
   int tempoAtirando;
   int fireRate;
   int fire;
   int index;
   int tempoAni;
   int ind;
   int tempoAti;
   protected int range;
   Rectangle retangulo;
   protected Rectangle ene;
   LinkedList<Float> rays;
   double angulo;
   protected Rectangle hitBox;

   public AbstractEntidade(float x, float y, int largura, int altura) {
      this.range = Janela.altura / 3;
      this.retangulo = new Rectangle((int)this.getX(), (int)this.getY(), this.getLargura(), this.getAltura());
      this.ene = new Rectangle();
      this.hitBox = new Rectangle();
      this.x = x;
      this.y = y;
      this.largura = largura;
      this.altura = altura;
      this.maskLargura = largura;
      this.maskAltura = altura;
   }

   public float getX() {
      return this.x;
   }

   public void setX(float x) {
      this.x = x;
   }

   public float getY() {
      return this.y;
   }

   public void setY(float y) {
      this.y = y;
   }

   public int getLargura() {
      return this.largura;
   }

   public void setLargura(int largura) {
      this.largura = largura;
   }

   public int getAltura() {
      return this.altura;
   }

   public void setAltura(int altura) {
      this.altura = altura;
   }

   public void paraCima(float velocidade) {
      this.y -= velocidade;
   }

   public void paraBaixo(float velocidade) {
      this.y += velocidade;
   }

   public void paraDireita(float velocidade) {
      this.x += velocidade;
   }

   public void paraEsquerda(float velocidade) {
      this.x -= velocidade;
   }

   protected boolean isEstouVendo() {
      return this.estouVendo;
   }

   protected void setEstouVendo(boolean estouVendo) {
      this.estouVendo = estouVendo;
   }

   protected boolean isMoveu() {
      return this.moveu;
   }

   protected void setMoveu(boolean moveu) {
      this.moveu = moveu;
   }

   protected boolean isEstaAtirando() {
      return this.estaAtirando;
   }

   protected void setEstaAtirando(boolean estaAtirando) {
      this.estaAtirando = estaAtirando;
   }

   protected List<Node> getPath() {
      return this.path;
   }

   protected void setPath(List<Node> path) {
      this.path = path;
   }

   protected Random getRandom() {
      return this.r;
   }

   protected void setRandom(Random r) {
      this.r = r;
   }

   protected SpriteSheet getSpriteCaminhada() {
      return this.spriteCaminhada;
   }

   protected void setSpriteCaminhada(SpriteSheet spriteCaminhada) {
      this.spriteCaminhada = spriteCaminhada;
   }

   protected SpriteSheet getSpriteAtira() {
      return this.spriteAtira;
   }

   protected void setSpriteAtira(SpriteSheet spriteAtira) {
      this.spriteAtira = spriteAtira;
   }

   protected BufferedImage[] getCaminhada() {
      return this.caminhada;
   }

   protected void setCaminhada(BufferedImage[] caminhada) {
      this.caminhada = caminhada;
   }

   protected BufferedImage[] getAtira() {
      return this.atira;
   }

   protected void setAtira(BufferedImage[] atira) {
      this.atira = atira;
   }

   protected int getMaskX() {
      return this.maskX;
   }

   protected void setMaskX(int maskX) {
      this.maskX = maskX;
   }

   protected int getMaskY() {
      return this.maskY;
   }

   protected void setMaskY(int maskY) {
      this.maskY = maskY;
   }

   protected int getMaskLargura() {
      return this.maskLargura;
   }

   protected void setMaskLargura(int maskLargura) {
      this.maskLargura = maskLargura;
   }

   protected int getMaskAltura() {
      return this.maskAltura;
   }

   protected void setMaskAltura(int maskAltura) {
      this.maskAltura = maskAltura;
   }

   protected int getTempoAtirando() {
      return this.tempoAtirando;
   }

   protected void setTempoAtirando(int tempoAtirando) {
      this.tempoAtirando = tempoAtirando;
   }

   protected int getFireRate() {
      return this.fireRate;
   }

   protected void setFireRate(int fireRate) {
      this.fireRate = fireRate;
   }

   protected void addFireRate(int fireRate) {
      this.fireRate += fireRate;
   }

   protected int getFire() {
      return this.fire;
   }

   protected void setFire(int fire) {
      this.fire = fire;
   }

   protected int getIndex() {
      return this.index;
   }

   protected void setIndex(int index) {
      this.index = index;
   }

   protected void addIndex(int index) {
      this.index += index;
   }

   protected int getTempoAni() {
      return this.tempoAni;
   }

   protected void setTempoAni(int tempoAni) {
      this.tempoAni = tempoAni;
   }

   protected void addTempoAni(int tempoAni) {
      this.tempoAni += tempoAni;
   }

   protected int getInd() {
      return this.ind;
   }

   protected void setInd(int ind) {
      this.ind = ind;
   }

   protected void addInd(int ind) {
      this.ind += ind;
   }

   protected int getTempoAti() {
      return this.tempoAti;
   }

   protected void setTempoAti(int tempoAti) {
      this.tempoAti = tempoAti;
   }

   protected void addTempoAti(int tempoAti) {
      this.tempoAti += tempoAti;
   }

   protected int getRange() {
      return this.range;
   }

   protected void setRange(int range) {
      this.range = range;
   }

   public LinkedList<Float> getRays() {
      return this.rays;
   }

   protected void setRays(LinkedList<Float> rays) {
      this.rays = rays;
   }

   protected double getAngulo() {
      return this.angulo;
   }

   protected void setAngulo(double angulo) {
      this.angulo = angulo;
   }

   public Rectangle getHitBox() {
      return this.hitBox;
   }

   public void setHitBox(Rectangle hitBox) {
      this.hitBox = hitBox;
   }

   public void acao() {
   }

   void iniciaAni() {
      if (this.isMoveu()) {
         this.addTempoAni(1);
         if (this.getTempoAni() == 8) {
            this.setTempoAni(0);
            this.addIndex(1);
            if (this.getIndex() == this.getCaminhada().length) {
               this.setIndex(0);
            }
         }
      }

      if (this.isEstaAtirando()) {
         this.addTempoAti(1);
         if (this.getTempoAti() == 5) {
            this.setEstaAtirando(false);
            this.setTempoAti(0);
            this.addInd(1);
            if (this.getInd() == this.getAtira().length) {
               this.setInd(0);
            }
         }
      } else {
         this.setInd(0);
      }

   }

   public void graficos(Graphics g) {
   }

   protected double distancia(double x1, double y1, double x2, double y2) {
      return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
   }

   protected void setMask(int maskX, int maskY, int maskLargura, int maskAltura) {
      this.maskX = maskX;
      this.maskY = maskY;
      this.maskLargura = maskLargura;
      this.maskAltura = maskAltura;
   }

   public void gerarExplosao(int montante, int x, int y, int largura, int altura, int tempoMax, Color cor, double velocidade, Som som) {
      if (som != null) {
         som.play();
      }

      for(int i = 0; i < montante; ++i) {
         Init.addExplosoes(new Explosao((double)x, (double)y, largura, altura, tempoMax, cor, velocidade));
      }

   }

   void iniciaRayCasting(Graphics g) {
      this.setRays(this.calcRays((int)this.getX() + 16, (int)this.getY() + 16, 1, this.getRange()));
      LinkedList<Float> lines = this.getRays();

      for(int i = 0; i < lines.size(); ++i) {
         this.setEstouVendo(((Float)lines.get(i)).intersects(Init.getJogador().getRetangulo()));
      }

      this.mostraRayCasting(g);
   }

   void mostraRayCasting(Graphics g) {
      if (PontosSpawn.modoTreino) {
         g.setColor(Color.CYAN);
         Iterator var2 = this.getRays().iterator();

         while(var2.hasNext()) {
            Float ray = (Float)var2.next();
            g.drawLine((int)ray.x1, (int)ray.y1, (int)ray.x2, (int)ray.y2);
         }
      } else {
         String var6 = Opcoes.qualDificuldade();
         byte var7 = -1;
         switch(var6.hashCode()) {
         case 97187371:
            if (var6.equals("facil")) {
               var7 = 0;
            }
         }

         switch(var7) {
         case 0:
            g.setColor(Color.CYAN);
            Iterator var4 = this.getRays().iterator();

            while(var4.hasNext()) {
               Float ray = (Float)var4.next();
               g.drawLine((int)ray.x1, (int)ray.y1, (int)ray.x2, (int)ray.y2);
            }
         }
      }

   }

   boolean colidindo(int x2, int y2, int largura2, int altura2) {
      Rectangle este = new Rectangle((int)this.getX() + this.getMaskX(), (int)this.getY() + this.getMaskY(), this.getMaskLargura(), this.getMaskAltura());
      return este.intersects((double)x2, (double)y2, (double)largura2, (double)altura2);
   }

   boolean colidindo(Rectangle rectangle) {
      Rectangle este = new Rectangle((int)this.getX() + this.getMaskX(), (int)this.getY() + this.getMaskY(), this.getMaskLargura(), this.getMaskAltura());
      return este.intersects(rectangle);
   }

   public boolean caminhoLivre(int x, int y) {
      this.setEne(new Rectangle(x + this.getMaskX(), y + this.getMaskY(), this.getMaskLargura(), this.getMaskAltura()));
      Inimigos ini = null;
      Iterator var4 = Init.getEntidades().iterator();

      while(var4.hasNext()) {
         AbstractEntidade entidade = (AbstractEntidade)var4.next();
         if (entidade instanceof Inimigos) {
            ini = (Inimigos)entidade;
         }

         if (ini != null) {
            Rectangle outro = new Rectangle((int)ini.getX(), (int)ini.getY(), ini.getLargura(), ini.getAltura());
            if (ini != this) {
               if (this.getEne().intersects(outro)) {
                  return true;
               }

               if (this.getEne().intersects(this.getRetangulo())) {
                  return true;
               }
            }
         }
      }

      var4 = Init.getParedes().iterator();

      Parede p;
      do {
         if (!var4.hasNext()) {
            return false;
         }

         p = (Parede)var4.next();
      } while(!this.getEne().intersects(p.getRectangle()));

      return true;
   }

   public Rectangle getEne() {
      return this.ene;
   }

   protected void setEne(Rectangle ene) {
      this.ene = ene;
   }

   public Rectangle getRetangulo() {
      return this.retangulo;
   }

   public void setRetangulo(Rectangle retangulo) {
      this.retangulo = retangulo;
   }

   LinkedList<Float> calcRays(int x, int y, int resolution, int maxDist) {
      LinkedList<Float> raios = new LinkedList();

      for(int i = 0; i < resolution; ++i) {
         double dir = Math.atan2((double)(Init.getJogador().getY() + (float)(Init.getJogador().getOffset() / 2) - 16.0F - this.getY()), (double)(Init.getJogador().getX() - this.getX()));
         float minDist = (float)maxDist;
         Iterator var10 = Init.getLinesOnWalls().iterator();

         while(var10.hasNext()) {
            Float line = (Float)var10.next();
            float dist = this.getRayCast((float)x, (float)y, (float)((double)x + Math.cos(dir) * (double)maxDist), (float)((double)y + Math.sin(dir) * (double)maxDist), line.x1, line.y1, line.x2, line.y2);
            if (dist < minDist && dist > 0.0F) {
               minDist = dist;
            }
         }

         raios.add(new Float((float)x, (float)y, (float)((double)x + Math.cos(dir) * (double)minDist), (float)((double)y + Math.sin(dir) * (double)minDist)));
      }

      return raios;
   }

   protected float getRayCast(float p0_x, float p0_y, float p1_x, float p1_y, float p2_x, float p2_y, float p3_x, float p3_y) {
      float s1_x = p1_x - p0_x;
      float s1_y = p1_y - p0_y;
      float s2_x = p3_x - p2_x;
      float s2_y = p3_y - p2_y;
      float s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
      float t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);
      if (s >= 0.0F && s <= 1.0F && t >= 0.0F && t <= 1.0F) {
         float x1 = p0_x + t * s1_x;
         float y1 = p0_y + t * s1_y;
         return dist(p0_x, p0_y, x1, y1);
      } else {
         return -1.0F;
      }
   }

   void iniciaRectangle() {
      double rx = (double)Math.round((float)Math.cos(this.getAngulo()));
      double ry = (double)Math.round((float)Math.sin(this.getAngulo()));
      int rxx = (int)(rx + (double)this.getX());
      int ryy = (int)(ry + (double)this.getY());
      Rectangle rec = new Rectangle(rxx + 5, ryy + 6, 17, 20);
      AffineTransform at = AffineTransform.getRotateInstance(this.getAngulo(), (double)(this.getX() + 16.0F), (double)(this.getY() + 16.0F));
      this.setHitBox(at.createTransformedShape(rec).getBounds());
   }

   static float dist(float x1, float y1, float x2, float y2) {
      return (float)Math.sqrt((double)((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
   }
}
