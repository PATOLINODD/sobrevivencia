/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.entidades;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author PATOLINODD
 */
public class Ponto extends AbstractEntidade {

    public Ponto(float x, float y, int largura, int altura) {
        super(x, y, largura, altura);
    }
    
    @Override
    public void graficos(Graphics g){
        g.setColor(Color.red);
        g.fillOval((int)getX(), (int)getY(), 5, 5);
    }

}
