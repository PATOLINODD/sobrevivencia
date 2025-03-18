/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.chefes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.mapa.Mapa;
import sobrevivencia.spritesheet.SpriteSheet;

/**
 *
 * @author PATOLINODD
 */
public class Helicoptero extends AbstractEntidade{
    
    BufferedImage helicoptero = new SpriteSheet("/helicoptero.png").getImg();
    BufferedImage helice = new SpriteSheet("/helice.png").getImg();
    
    public int vidaH = 1000;
    
    public Helicoptero(float x, float y, int largura, int altura) {
        super(x, y, largura, altura);
    }
    
    int xx, yy, delay;
    long velocidadeS = (long) 0.1;
    double orbitAngle;
    public Shape shape = new Rectangle();
    @Override
    public void acao(){
        velocidadeS = (long)1;
        orbitAngle+= 0.1;
        rotate+=7;
        
        shape = new Rectangle(nx-helice.getWidth()+110, ny+helice.getHeight()+30, helice.getWidth()*5, helice.getHeight());
//        delay++;
//        if(delay >= 10){
//            delay = 0;
//            velocidadeH++;
//        }
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(orbitAngle), nx+helice.getWidth()*4, ny+helice.getHeight()*2+2);
        shape = at.createTransformedShape(shape);
        orbita();
        removeH();
    }
    private void removeH(){
        if(vidaH <= 0){
            vidaH = 0;
            Init.getHelicopteros().remove(this);
        }
    }
    
    int nx, ny;
    private void orbita(){
//        double orbitAngle = Math.atan2(Mapa.altu/2*32 - (getY()+30),
//                            Mapa.larg/2*32 - (getX()+30));
        double rads = Math.toRadians(orbitAngle); // Make 0 point out to the right...
        int fullLength = Math.round((Mapa.altu*24));

        // Calculate the outter point of the line
        nx = Math.round((float) (Math.cos(rads) * fullLength)) + Mapa.larg/2*16;
        ny = Math.round((float) (Math.sin(rads) * fullLength)) + Mapa.altu/2*24;
    }
    
    long rotate, velocidadeH;
    @Override
    public void graficos(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
//        g2.draw(shape);
//        g2.setColor(Color.red);
//        g2.fillRect((int)getX()-helice.getWidth()+110, (int)getY()+helice.getHeight()+30, helice.getWidth()*5, helice.getHeight());
        AffineTransform at = g2.getTransform();
        g2.rotate(Math.toRadians(orbitAngle), nx+helice.getWidth()*4, ny+helice.getHeight()*2+2);
        g2.drawImage(helicoptero, nx, ny, helicoptero.getWidth()*4, helicoptero.getHeight()*4, null);
//        g2.drawImage(helicoptero, (int)getX(), (int)getY(), helicoptero.getWidth()*4, helicoptero.getHeight()*4, null);
//        g2.drawImage(helicoptero, xx, yy, helicoptero.getWidth()*4, helicoptero.getHeight()*4, null);
        
        giraHelice(g2);
        g2.setTransform(at);
//        g2.dispose();
    }
    
    private void giraHelice(Graphics2D g2){
        //giraHelice
//        g2.rotate(Math.toDegrees(rotate), (int)getX()+helice.getWidth()*2+28, (int)getY()+helice.getHeight()*2+2);
        g2.rotate(Math.toDegrees(rotate), nx+helice.getWidth()*2+28, ny+helice.getHeight()*2+2);
//        g2.rotate(Math.toRadians(rotate), xx+helice.getWidth()*2+28, yy+helice.getHeight()*2+2);
        g2.setColor(Color.red);
//        g2.drawImage(helice, (int)getX()+28, (int)getY()+4, helice.getWidth()*4, helice.getHeight()*4, null);
        g2.drawImage(helice, nx+28, ny+4, helice.getWidth()*4, helice.getHeight()*4, null);
//        g2.drawImage(helice, xx+28, yy+4, helice.getWidth()*4, helice.getHeight()*4, null);
    }
    
    public Rectangle helicop = new Rectangle();
}
