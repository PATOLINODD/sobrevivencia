/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.Area;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import sobrevivencia.Init;
import sobrevivencia.janela.Camera;
import sobrevivencia.janela.Janela;
import sobrevivencia.spritesheet.SpriteSheet;

/**
 *
 * @author PATOLINODD
 */
public class AbstractArea {

    int x, y, larg, altu;
    SpriteSheet sprite;
    BufferedImage image;

    protected boolean comprar;

    public AbstractArea(int x, int y, int larg, int altu, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.larg = larg;
        this.altu = altu;
        this.image = image;
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

    public int getLarg() {
        return larg;
    }

    public void setLarg(int larg) {
        this.larg = larg;
    }

    public int getAltu() {
        return altu;
    }

    public void setAltu(int altu) {
        this.altu = altu;
    }

    public boolean isComprar() {
        return comprar;
    }

    public void setComprar(boolean comprar) {
        this.comprar = comprar;
    }

    public void graficos(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(x, y, altu, larg);
        
        if (image != null) {
            g.drawImage(image, x, y, null);
        }

        if (comprar()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.white);
            g2.drawString("F", x, y);

            if (comprar) {
                //criar imagem de compra sobreposto na tela
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(100 - (int) Init.getCam().getX(), 100 - (int) Init.getCam().getY(), Janela.largura - 200, Janela.altura - 200);
            }
        } else {
            comprar = false;
        }
    }

    public boolean comprar() {
        Rectangle este = new Rectangle(x, y, larg, altu);
        return este.intersects(Init.getJogador().getRetangulo());
    }
}
