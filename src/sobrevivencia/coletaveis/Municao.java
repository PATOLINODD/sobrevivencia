package sobrevivencia.coletaveis;

import java.awt.Color;
import java.awt.Graphics;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;

public class Municao extends AbstractEntidade {
   private int x;
   private int y;
   private double acresDano = 0.5D;
   private double tempo;

   public Municao(float x, float y, int largura, int altura) {
      super(x, y, largura, altura);
      this.x = (int)x;
      this.y = (int)y;
   }

   public void acao() {
      ++this.tempo;
      if (this.tempo >= 300.0D) {
         this.tempo = 0.0D;
         Init.removeMunicao(this);
      }

   }

   public void graficos(Graphics g) {
      g.setColor(Color.yellow);
      g.fillOval(this.x, this.y, 20, 20);
   }

   private void getThis() {
   }
}
