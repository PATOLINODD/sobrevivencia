/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import sobrevivencia.mapa.Mapa;
import static sobrevivencia.mapa.Mapa.larg;
import sobrevivencia.mapa.Parede;
import sobrevivencia.mapa.Tiles;

/**
 *
 * @author PATOLINODD
 */
public class Astar {

    private static final Comparator<Node> nodeSorter = (Node n0, Node n1) -> {
        if (n1.fCost < n0.fCost) {
            return +1;
        }
        if (n1.fCost > n0.fCost) {
            return - 1;
        }
        return 0;
    };

    public static List<Node> findPath(Mapa mapa, Vector2 start, Vector2 end) {
        List<Node> openList = new ArrayList<>();
        List<Node> closeList = new ArrayList<>();

        Node current = new Node(start, null, 0, getDistance(start, end));
        openList.add(current);

        while (!openList.isEmpty()) {
            Collections.sort(openList, nodeSorter);
            current = openList.get(0);
            if (current.tile.equals(end)) {
                //chegamos ao ponto final
                //só retornar o valor!
                List<Node> path = new ArrayList<>();
                while (current.parente != null) {
                    path.add(current);
                    current = current.parente;
                }
                openList.clear();
                closeList.clear();
                return path;
            }
            openList.remove(current);
            closeList.add(current);

            for (int i = 0; i < 9; i++) {

                if (i == 4) {
                    continue;
                }

                int x = current.tile.x;
                int y = current.tile.y;

                int xi = (i % 3) - 1;//-1, 0, 1,
                int yi = (i / 3) - 1;//-1, -1, -1, 0, 0, 0, 1, 1, 1, 2
                Tiles tile = mapa.tiles[x + xi + ((y + yi) * larg)];
//                Pontos ponto = mapa.pontos[x+xi+ ((y+yi)*mapa.larg)];

                //                    Tiles tiles = RandomWorld.tile[x+xi + ((y+yi*RandomWorld.mapaWidth))];
                if (tile == null) {
                    continue;
                }

                if (tile instanceof Parede) {
                    continue;
                }
                if (i == 0) {
                    Tiles teste = mapa.tiles[x + xi + 1 + ((y + yi) * larg)];
                    Tiles teste2 = mapa.tiles[x + xi + ((y + yi + 1) * larg)];

                    if (teste instanceof Parede || teste2 instanceof Parede) {
                        continue;
                    }

                }
                if (i == 2) {
                    Tiles teste = mapa.tiles[x + xi - 1 + ((y + yi) * larg)];
                    Tiles teste2 = mapa.tiles[x + xi + ((y + yi + 1) * larg)];
                    if (teste instanceof Parede || teste2 instanceof Parede) {
                        continue;
                    }
                }
                if (i == 6) {
                    Tiles teste = mapa.tiles[x + xi + ((y + yi - 1) * larg)];
                    Tiles teste2 = mapa.tiles[x + xi + 1 + ((y + yi) * larg)];
                    if (teste instanceof Parede || teste2 instanceof Parede) {
                        continue;
                    }
                }
                if (i == 8) {
                    Tiles teste = mapa.tiles[x + xi + ((y + yi - 1) * larg)];
                    Tiles teste2 = mapa.tiles[x + xi - 1 + ((y + yi) * larg)];
                    if (teste instanceof Parede || teste2 instanceof Parede) {
                        continue;
                    }
                }
                Vector2 goal = new Vector2(x + xi, y + yi);
                double gcost = current.gCost + getDistance(current.tile, goal);
                double hcost = getDistance(goal, end);

                Node node = new Node(goal, current, gcost, hcost);

                if (vecInList(closeList, goal) && gcost >= current.gCost) {
                    continue;
                }

                if (!vecInList(openList, goal)) {
                    openList.add(node);
                } else if (gcost < current.gCost) {
                    openList.remove(current);
                    openList.add(node);
                }
            }
        }
        closeList.clear();
        return null;
    }

    private static boolean vecInList(List<Node> list, Vector2 vec) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).tile.equals(vec)) {
                return true;
            }
        }
        return false;
    }

    private static double getDistance(Vector2 tile, Vector2 goal) {
        double dx = tile.x - goal.x;
        double dy = tile.y - goal.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

}
