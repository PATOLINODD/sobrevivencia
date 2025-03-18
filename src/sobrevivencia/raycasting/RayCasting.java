/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.raycasting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.janela.Janela;
import sobrevivencia.mapa.Parede;

/**
 *
 * @author PATOLINODD
 */
public class RayCasting {
    public boolean jogadorEstaVendo;
    
    public void graficos(Graphics g){
        jogadorEstaVendo = false;
        int x = (int)Init.getJogador().getX()+16, y = (int)Init.getJogador().getY()+16;
        Init.setRays(calcRays(x, y, 80, Janela.altura/2));
        g.setColor(new Color(255, 255, 255, 100));
        for(Line2D.Float ray : Init.getRays()){
            g.drawLine((int)ray.x1, (int)ray.y1, (int)ray.x2, (int)ray.y2);
        }
    }
    
    private LinkedList<Line2D.Float> calcRays(int x, int y, int resolution, int maxDist){
        LinkedList<Line2D.Float> raios = new LinkedList<>();
        
        
        LinkedList<Line2D.Float> ents = new LinkedList<>();
        for(Parede par : Init.getParedes()){
            ents.add(new Line2D.Float(par.getX(), par.getY(), par.getX() + par.getWidth(), par.getY() + par.getHeight()));
            ents.add(new Line2D.Float(par.getX(), par.getY()+par.getHeight(), par.getX() + par.getWidth(), par.getY()));
        }
        
        for(int i = 0; i < resolution; i++){
            double dir = (Math.PI * 2) * ((double) i / resolution);
            float minDist = maxDist;
            for(Line2D.Float line : ents){
                float dist = getRayCast((float)x, (float)y, (float)(x + (Math.cos(dir) * maxDist)), (float)(y + Math.sin(dir) * maxDist), line.x1, line.y1, line.x2, line.y2);
                if(dist < minDist && dist > 0){
                    minDist = dist;
                }
            }
            raios.add(new Line2D.Float(x, y, (float)(x + (Math.cos(dir) * minDist)), (float)(y + Math.sin(dir) * minDist)));
        }
        return raios;
    }
    
    
    public static float getRayCast(float p0_x, float p0_y, float p1_x, float p1_y, float p2_x, float p2_y, float p3_x, float p3_y) {
        float s1_x, s1_y, s2_x, s2_y;
        s1_x = p1_x - p0_x;
        s1_y = p1_y - p0_y;
        s2_x = p3_x - p2_x;
        s2_y = p3_y - p2_y;

        float s, t;
        s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
        t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);
        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            float x = p0_x + (t * s1_x);
            float y = p0_y + (t * s1_y);
            return dist(p0_x, p0_y, x, y);
        }

        return -1; // No collision
    }
    
    private static float dist(float x1, float y1, float x2, float y2) {
        return (float)Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

}
