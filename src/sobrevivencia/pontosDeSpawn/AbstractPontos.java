/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.pontosDeSpawn;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Administrator
 */
public class AbstractPontos {

    private int x, y;

    public AbstractPontos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

//    public void graficos(Graphics g) {
//        g.setColor(Color.red);
//        g.fillRect(x, y, 10, 10);
//    }
}
