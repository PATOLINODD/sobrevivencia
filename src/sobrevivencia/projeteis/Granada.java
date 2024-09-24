package sobrevivencia.projeteis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import sobrevivencia.Init;
import sobrevivencia.mapa.Parede;
import sobrevivencia.som.Som;

public class Granada extends AbstractProjetil {
   int nVelocidade = 1;
   int tempoReduzVel;
   double angulo;
   Rectangle granadaCima = new Rectangle((int)this.getX(), (int)this.getY(), this.getTamanho(), 3);
   Rectangle granadaBaixo = new Rectangle((int)this.getX(), (int)this.getY(), this.getTamanho(), 3);
   Rectangle granadaEsquerda = new Rectangle((int)this.getX(), (int)this.getY(), this.getTamanho(), 3);
   Rectangle granadaDireita = new Rectangle((int)this.getX(), (int)this.getY(), this.getTamanho(), 3);
   Rectangle granada = new Rectangle((int)this.getX(), (int)this.getY(), this.getTamanho(), this.getTamanho());
   int ttamanhoP = 10;
   int ttamanho = 6;

   public Granada(float x, float y, int tamanho, double dx, double dy, double danoAtk, int tempoVida, Color cor, Som som) {
      super(x, y, tamanho, dx, dy, danoAtk, tempoVida, cor, som);
   }

   public void acao() {
      this.tempoVida = 100;
      this.tamanho = 15;
      if (this.nVelocidade <= 0) {
         this.nVelocidade = 0;
      }

      ++this.vida;
      if (this.vida >= this.tempoVida) {
         this.vida = 0;
         Init.removeProjetil(this);
      }

      this.paraDireita((float)(this.dx * (double)this.nVelocidade));
      this.paraBaixo((float)(this.dy * (double)this.nVelocidade));
      this.iniciaAngulo();
      this.rebate();
   }

   private void iniciaAngulo() {
      this.angulo = Math.atan2((double)Init.getJogador().getY() + 8.0D - (double)this.getY(), (double)Init.getJogador().getX() + 8.0D - (double)this.getX()) * -1.0D;
   }

   public void graficos(Graphics g) {
      g.setColor(Color.BLACK);
      g.fillOval((int)this.x, (int)this.y, this.tamanho, this.tamanho);
   }

   private void rebate() {
      this.granada = new Rectangle((int)this.getX(), (int)this.getY(), this.getTamanho(), this.getTamanho());
      Iterator var1 = Init.getParedes().iterator();

      Rectangle p;
      do {
         if (!var1.hasNext()) {
            return;
         }

         Parede parede = (Parede)var1.next();
         p = new Rectangle(parede.getX(), parede.getY(), parede.getWidth(), parede.getHeight());
      } while(!p.intersects(this.granada));

      this.dx = Math.cos(this.angulo);
      this.dy = Math.sin(this.angulo);
      this.dx *= -1.0D;
      this.dy *= 1.0D;
   }
}
