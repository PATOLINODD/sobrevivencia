/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.mapa;

import java.awt.image.BufferedImage;

/**
 *
 * @author PATOLINODD
 */
public class Grama extends Tiles{

    public Grama(int x, int y, BufferedImage img) {
        super(x, y, img);
    }

    public Grama(int x, int y, int width, int height, BufferedImage img) {
        super(x, y, width, height, img);
    }
    
}
