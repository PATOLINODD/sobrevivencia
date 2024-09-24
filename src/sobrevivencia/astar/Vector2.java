package sobrevivencia.astar;

public class Vector2 {
   public int x;
   public int y;

   public Vector2(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public boolean equals(Object obj) {
      Vector2 vec = (Vector2)obj;
      return vec.x == this.x && vec.y == this.y;
   }

   public String toString() {
      return "Vector2{x=" + this.x + ", y=" + this.y + '}';
   }
}
