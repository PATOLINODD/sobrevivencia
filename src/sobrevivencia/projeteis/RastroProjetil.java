package sobrevivencia.projeteis;

import java.awt.Color;
import java.awt.Graphics;
import sobrevivencia.Init;
import sobrevivencia.som.Som;

public class RastroProjetil extends AbstractProjetil {
   int vidaRatro;

   public RastroProjetil(float x, float y, int tamanho, double dx, double dy, double danoAtk, int tempoVida, Color cor) {
      super(x, y, tamanho, dx, dy, danoAtk, tempoVida, cor, (Som)null);
   }

   public void acao() {
      ++this.vidaRatro;
      if (this.vidaRatro >= this.tempoVida) {
         this.vidaRatro = 0;
         Init.removeProjetil(this);
      }

   }

   public void graficos(Graphics g) {
      if (this.vidaRatro == this.tempoVida / 5) {
         g.setColor(new Color(this.cor.getRed(), this.cor.getGreen(), this.cor.getBlue(), 200));
      } else if (this.vidaRatro == this.tempoVida / 3) {
         g.setColor(new Color(this.cor.getRed(), this.cor.getGreen(), this.cor.getBlue(), 150));
      } else if (this.vidaRatro >= this.tempoVida / 2) {
         g.setColor((new Color(this.cor.getRed(), this.cor.getGreen(), this.cor.getBlue(), 100)).darker());
      } else {
         g.setColor(this.cor);
      }

      g.fillOval((int)this.getX(), (int)this.getY(), this.getTamanho(), this.getTamanho());
   }
}
