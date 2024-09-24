package sobrevivencia.pontosDeSpawn;

public class AbstractPontos {
   private int x;
   private int y;

   public AbstractPontos(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return this.x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return this.y;
   }

   public void setY(int y) {
      this.y = y;
   }
}
