/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.mapa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import sobrevivencia.Init;

/**
 *
 * @author PATOLINODD
 */
public class Tiles {

    public BufferedImage img;

    int x, y, width = 32, height = 32;
    
    protected Rectangle rectangle = new Rectangle();

    public Tiles(int x, int y, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public Tiles(int x, int y, int width, int height, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public void graficos(Graphics g) {
        g.drawImage(img, x, y, null);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
//        jogadorEstaVendo();
    }

    boolean jogadorEstaVendo;

    public void jogadorEstaVendo() {
        jogadorEstaVendo = false;
        for (Line2D.Float ray : Init.getRays()) {
            if (ray.intersects(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()))) {
                jogadorEstaVendo = true;
            }
        }
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

    public int getWidth() {
        return img.getWidth();
    }

    public int getHeight() {
        return img.getHeight();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    
}
