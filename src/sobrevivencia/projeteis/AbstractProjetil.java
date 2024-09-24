package sobrevivencia.projeteis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import sobrevivencia.Init;
import sobrevivencia.chefes.Helicoptero;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.explosoes.Explosao;
import sobrevivencia.janela.IconMira;
import sobrevivencia.jogador.Jogador;
import sobrevivencia.mapa.Mapa;
import sobrevivencia.som.Som;

public abstract class AbstractProjetil {
   double dx;
   double dy;
   double velocidade = 25.0D;
   double tempo = 0.0D;
   double tempoB = 0.0D;
   double danoAtk;
   int vida;
   int tempoVida;
   int tamanho;
   float x;
   float y;
   public static boolean hit = false;
   Color cor;
   int tempoMax = 15;
   Rectangle projetil = new Rectangle((int)this.getX(), (int)this.getY(), this.getTamanho(), this.getTamanho());

   public AbstractProjetil(float x, float y, int tamanho, double dx, double dy, double danoAtk, int tempoVida, Color cor, Som som) {
      this.x = x;
      this.y = y;
      this.dx = dx;
      this.dy = dy;
      this.tempoVida = tempoVida;
      this.cor = cor;
      this.tamanho = tamanho;
      this.danoAtk = danoAtk;
      if (som != null) {
         som.play();
      }

   }

   void tiles() {
   }

   public int getTamanho() {
      return this.tamanho;
   }

   public void setTamanho(int tamanho) {
      this.tamanho = tamanho;
   }

   public void paraBaixo(float velocidade) {
      this.y += velocidade;
   }

   public void paraDireita(float velocidade) {
      this.x += velocidade;
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

   public void acao() {
      if (Jogador.estaMirandoHelicoptero) {
         this.hitHelicoptero();
      }

      this.vaiEmDir(this.dx, this.dy, this.velocidade);
      if (this.hitParede()) {
         Init.removeProjetil(this);
         this.gerarExplosao(50, (int)this.getX() + 5, (int)this.getY() + 5, 4, this.tempoMax, this.cor, (Som)null);
      }

      this.iniciaRastro();
      this.initRectangle();
      this.atkJogador();
      this.atkAosInimigos();
   }

   void initRectangle() {
      this.projetil = new Rectangle((int)this.getX(), (int)this.getY(), this.getTamanho(), this.getTamanho());
   }

   void vaiEmDir(double dx, double dy, double velocidade) {
      this.paraDireita((float)(dx * velocidade));
      this.paraBaixo((float)(dy * velocidade));
   }

   protected void iniciaRastro() {
      ++this.vida;
      if (this.vida >= 3) {
         Init.addProjetil(new RastroProjetil(this.x, this.y, this.tamanho, 0.0D, 0.0D, 0.0D, 10, this.cor));
      }

      if (this.vida >= this.tempoVida) {
         this.vida = 0;
         Init.removeProjetil(this);
         this.gerarExplosao(50, (int)this.getX() + 10, (int)this.getY() + 10, 2, 10, this.cor, (Som)null);
      }

   }

   public void graficos(Graphics g) {
      g.setColor(this.cor);
      g.fillOval((int)this.x, (int)this.y, this.tamanho, this.tamanho);
   }

   void gerarExplosao(int monte, int x, int y, int tamanho, int vida, Color cor, Som som) {
      if (som != null) {
         som.play();
      }

      for(int i = 0; i < monte; ++i) {
         Init.addExplosoes(new Explosao((double)x, (double)y, tamanho, tamanho, this.tempoMax, cor, 1.3D));
      }

   }

   void atkJogador() {
      if (this.projetil.intersects(Init.getJogador().getRectangle())) {
         Jogador.emCombate = true;
         Jogador.recebendoAtk = true;
         Jogador var10000 = Init.getJogador();
         var10000.vida -= this.getDanoAtk();
         Init.removeProjetil(this);
         this.gerarExplosao(50, (int)this.getX(), (int)this.getY(), 4, 15, Color.red, (Som)null);
      }

   }

   void atkAosInimigos() {
      Iterator var1 = Init.getEntidades().iterator();

      while(var1.hasNext()) {
         AbstractEntidade entidade = (AbstractEntidade)var1.next();
         if (entidade instanceof Inimigos) {
            Inimigos inimigo = (Inimigos)entidade;
            if (this.projetil.intersects(inimigo.getHitBox()) && !Jogador.estaMirandoHelicoptero) {
               IconMira.feedback = true;
               Init.removeProjetil(this);
               this.gerarExplosao(50, (int)this.getX(), (int)this.getY(), 4, 15, Color.red, (Som)null);
               inimigo.setHit(true);
            }
         }
      }

   }

   protected boolean hitParede() {
      int projX = (int)(this.getX() / 32.0F);
      int projY = (int)(this.getY() / 32.0F);
      int[] pixels = new int[Mapa.getMapa().getWidth() * Mapa.getMapa().getHeight()];
      Mapa.getMapa().getRGB(0, 0, Mapa.getMapa().getWidth(), Mapa.getMapa().getHeight(), pixels, 0, Mapa.getMapa().getWidth());
      switch(pixels[projX + projY * Mapa.getMapa().getWidth()]) {
      case -11731200:
         return true;
      case -1:
         return true;
      default:
         return false;
      }
   }

   void hitHelicoptero() {
      Iterator var1 = Init.getHelicopteros().iterator();

      Helicoptero helic;
      do {
         if (!var1.hasNext()) {
            return;
         }

         helic = (Helicoptero)var1.next();
      } while(helic == null || !helic.shape.intersects(this.projetil));

      helic.vidaH = (int)((double)helic.vidaH - this.getDanoAtk());
      Init.removeProjetil(this);
      this.gerarExplosao(50, (int)this.getX() + 10, (int)this.getY() + 10, 4, this.tempoMax, Color.DARK_GRAY.brighter(), (Som)null);
   }

   public double getDanoAtk() {
      return this.danoAtk;
   }
}
