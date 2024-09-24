package sobrevivencia.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import sobrevivencia.mapa.Mapa;
import sobrevivencia.mapa.Parede;
import sobrevivencia.mapa.Tiles;

public class Astar {
   private static final Comparator<Node> nodeSorter = (n0, n1) -> {
      if (n1.fCost < n0.fCost) {
         return 1;
      } else {
         return n1.fCost > n0.fCost ? -1 : 0;
      }
   };

   public static List<Node> findPath(Mapa mapa, Vector2 start, Vector2 end) {
      List<Node> openList = new ArrayList();
      List<Node> closeList = new ArrayList();
      Node current = new Node(start, (Node)null, 0.0D, getDistance(start, end));
      openList.add(current);

      while(!openList.isEmpty()) {
         Collections.sort(openList, nodeSorter);
         current = (Node)openList.get(0);
         if (current.tile.equals(end)) {
            ArrayList path;
            for(path = new ArrayList(); current.parente != null; current = current.parente) {
               path.add(current);
            }

            openList.clear();
            closeList.clear();
            return path;
         }

         openList.remove(current);
         closeList.add(current);

         for(int i = 0; i < 9; ++i) {
            if (i != 4) {
               int x = current.tile.x;
               int y = current.tile.y;
               int xi = i % 3 - 1;
               int yi = i / 3 - 1;
               Tiles tile = mapa.tiles[x + xi + (y + yi) * Mapa.larg];
               if (tile != null && !(tile instanceof Parede)) {
                  Tiles teste;
                  Tiles teste2;
                  if (i == 0) {
                     teste = mapa.tiles[x + xi + 1 + (y + yi) * Mapa.larg];
                     teste2 = mapa.tiles[x + xi + (y + yi + 1) * Mapa.larg];
                     if (teste instanceof Parede || teste2 instanceof Parede) {
                        continue;
                     }
                  }

                  if (i == 2) {
                     teste = mapa.tiles[x + xi - 1 + (y + yi) * Mapa.larg];
                     teste2 = mapa.tiles[x + xi + (y + yi + 1) * Mapa.larg];
                     if (teste instanceof Parede || teste2 instanceof Parede) {
                        continue;
                     }
                  }

                  if (i == 6) {
                     teste = mapa.tiles[x + xi + (y + yi - 1) * Mapa.larg];
                     teste2 = mapa.tiles[x + xi + 1 + (y + yi) * Mapa.larg];
                     if (teste instanceof Parede || teste2 instanceof Parede) {
                        continue;
                     }
                  }

                  if (i == 8) {
                     teste = mapa.tiles[x + xi + (y + yi - 1) * Mapa.larg];
                     teste2 = mapa.tiles[x + xi - 1 + (y + yi) * Mapa.larg];
                     if (teste instanceof Parede || teste2 instanceof Parede) {
                        continue;
                     }
                  }

                  Vector2 goal = new Vector2(x + xi, y + yi);
                  double gcost = current.gCost + getDistance(current.tile, goal);
                  double hcost = getDistance(goal, end);
                  Node node = new Node(goal, current, gcost, hcost);
                  if (!vecInList(closeList, goal) || !(gcost >= current.gCost)) {
                     if (!vecInList(openList, goal)) {
                        openList.add(node);
                     } else if (gcost < current.gCost) {
                        openList.remove(current);
                        openList.add(node);
                     }
                  }
               }
            }
         }
      }

      closeList.clear();
      return null;
   }

   private static boolean vecInList(List<Node> list, Vector2 vec) {
      for(int i = 0; i < list.size(); ++i) {
         if (((Node)list.get(i)).tile.equals(vec)) {
            return true;
         }
      }

      return false;
   }

   private static double getDistance(Vector2 tile, Vector2 goal) {
      double dx = (double)(tile.x - goal.x);
      double dy = (double)(tile.y - goal.y);
      return Math.sqrt(dx * dx + dy * dy);
   }
}
