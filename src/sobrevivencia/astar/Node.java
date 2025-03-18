/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.astar;

/**
 *
 * @author PATOLINODD
 */
public class Node {
    
    public Vector2 tile;
    public Node parente;
    public double fCost, gCost, hCost;
    
    public Node(Vector2 tile, Node parente, double gCost, double hCost){
        this.gCost = gCost;this.hCost=hCost;this.fCost = gCost + hCost;
        this.parente = parente;
        this.tile = tile;
    }

    @Override
    public String toString() {
        return "Node{" + "tile=" + tile + ", parente=" + parente + ", fCost=" + fCost + ", gCost=" + gCost + ", hCost=" + hCost + '}';
    }
    
}
