package sobrevivencia.janela;

import sobrevivencia.Init;

public class Camera {
   private float x;
   private float y;

   public void camera() {
      this.x = -Init.getJogador().getX() + (float)((Janela.largura + 100) / 3) + 96.0F;
      this.y = -Init.getJogador().getY() + (float)((Janela.altura + 100) / 3);
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
}
