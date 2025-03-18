/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.astar;

/**
 *
 * @author PATOLINODD
 */
public class Vector2 {
    
    public int x, y;
    
    public Vector2(int x, int y){
        this.x=x;this.y=y;
    }
    
    public boolean equals(Object obj){
        Vector2 vec = (Vector2)obj;
        
        return (vec.x == this.x && vec.y == this.y);
    }


    @Override
    public String toString() {
        return "Vector2{" + "x=" + x + ", y=" + y + '}';
    }
    
    
}
