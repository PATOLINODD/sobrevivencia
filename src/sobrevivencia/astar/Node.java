package sobrevivencia.astar;

public class Node {
   public Vector2 tile;
   public Node parente;
   public double fCost;
   public double gCost;
   public double hCost;

   public Node(Vector2 tile, Node parente, double gCost, double hCost) {
      this.gCost = gCost;
      this.hCost = hCost;
      this.fCost = gCost + hCost;
      this.parente = parente;
      this.tile = tile;
   }

   public String toString() {
      return "Node{tile=" + this.tile + ", parente=" + this.parente + ", fCost=" + this.fCost + ", gCost=" + this.gCost + ", hCost=" + this.hCost + '}';
   }
}
