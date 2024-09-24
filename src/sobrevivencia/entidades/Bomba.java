package sobrevivencia.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D.Float;
import java.util.Iterator;
import sobrevivencia.Init;
import sobrevivencia.som.Som;
import sobrevivencia.soudEffects.SoundEffects;

public class Bomba extends AbstractEntidade {
   public int vida = 30;
   public int tempoVida = 0;
   boolean colidindo = false;
   double distancia = this.distancia((double)((int)this.getX()), (double)((int)this.getY()), (double)Init.getJogador().getX(), (double)Init.getJogador().getY());

   public Bomba(float x, float y, int largura, int altura) {
      super(x, y, largura, altura);
      this.range = 100;
   }

   public void acao() {
      this.rays = this.calcRays((int)this.getX() + this.getLargura() / 2, (int)this.getY() + this.getAltura() / 2, 30, this.range);
      ++this.tempoVida;
      int tamanho = 20;
      if (this.tempoVida == this.vida) {
         this.tempoVida = 0;
         Init.removeBomba(this);
         this.gerarExplosao(50, (int)this.getX(), (int)this.getY(), tamanho, tamanho, 30, Color.red, 2.0D, new Som(SoundEffects.getEXPLOSAO()));
         this.gerarExplosao(25, (int)this.getX(), (int)this.getY(), tamanho, tamanho, 30, Color.gray, 2.0D, (Som)null);
         this.gerarExplosao(50, (int)this.getX(), (int)this.getY(), tamanho, tamanho, 30, Color.ORANGE, 2.0D, (Som)null);
         Iterator var2 = this.getRays().iterator();

         while(var2.hasNext()) {
            Float raioExplosao = (Float)var2.next();
            Init.getJogador().alteraVida(raioExplosao.intersects(Init.getJogador().getRetangulo()) ? -10.0D : 0.0D);
         }
      }

   }

   public void graficos(Graphics g) {
      g.setColor(Color.red);
      g.fillRect((int)this.getX(), (int)this.getY(), this.getLargura(), this.getAltura());
      this.mostrarRange(g);
   }

   void mostrarRange(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setColor(Color.CYAN);
      g2.drawOval((int)this.getX() - this.getRange() + this.getLargura() / 2, (int)this.getY() - this.getRange() + this.getAltura() / 2, this.getRange() * 2, this.getRange() * 2);
   }
}
