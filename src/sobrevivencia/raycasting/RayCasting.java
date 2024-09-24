package sobrevivencia.raycasting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D.Float;
import java.util.Iterator;
import java.util.LinkedList;
import sobrevivencia.Init;
import sobrevivencia.janela.Janela;
import sobrevivencia.mapa.Parede;

public class RayCasting {
   public boolean jogadorEstaVendo;

   public void graficos(Graphics g) {
      this.jogadorEstaVendo = false;
      int x = (int)Init.getJogador().getX() + 16;
      int y = (int)Init.getJogador().getY() + 16;
      Init.setRays(this.calcRays(x, y, 80, Janela.altura / 2));
      g.setColor(new Color(255, 255, 255, 100));
      Iterator var4 = Init.getRays().iterator();

      while(var4.hasNext()) {
         Float ray = (Float)var4.next();
         g.drawLine((int)ray.x1, (int)ray.y1, (int)ray.x2, (int)ray.y2);
      }

   }

   private LinkedList<Float> calcRays(int x, int y, int resolution, int maxDist) {
      LinkedList<Float> raios = new LinkedList();
      LinkedList<Float> ents = new LinkedList();
      Iterator var7 = Init.getParedes().iterator();

      while(var7.hasNext()) {
         Parede par = (Parede)var7.next();
         ents.add(new Float((float)par.getX(), (float)par.getY(), (float)(par.getX() + par.getWidth()), (float)(par.getY() + par.getHeight())));
         ents.add(new Float((float)par.getX(), (float)(par.getY() + par.getHeight()), (float)(par.getX() + par.getWidth()), (float)par.getY()));
      }

      for(int i = 0; i < resolution; ++i) {
         double dir = 6.283185307179586D * ((double)i / (double)resolution);
         float minDist = (float)maxDist;
         Iterator var11 = ents.iterator();

         while(var11.hasNext()) {
            Float line = (Float)var11.next();
            float dist = getRayCast((float)x, (float)y, (float)((double)x + Math.cos(dir) * (double)maxDist), (float)((double)y + Math.sin(dir) * (double)maxDist), line.x1, line.y1, line.x2, line.y2);
            if (dist < minDist && dist > 0.0F) {
               minDist = dist;
            }
         }

         raios.add(new Float((float)x, (float)y, (float)((double)x + Math.cos(dir) * (double)minDist), (float)((double)y + Math.sin(dir) * (double)minDist)));
      }

      return raios;
   }

   public static float getRayCast(float p0_x, float p0_y, float p1_x, float p1_y, float p2_x, float p2_y, float p3_x, float p3_y) {
      float s1_x = p1_x - p0_x;
      float s1_y = p1_y - p0_y;
      float s2_x = p3_x - p2_x;
      float s2_y = p3_y - p2_y;
      float s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
      float t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);
      if (s >= 0.0F && s <= 1.0F && t >= 0.0F && t <= 1.0F) {
         float x = p0_x + t * s1_x;
         float y = p0_y + t * s1_y;
         return dist(p0_x, p0_y, x, y);
      } else {
         return -1.0F;
      }
   }

   private static float dist(float x1, float y1, float x2, float y2) {
      return (float)Math.sqrt((double)((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
   }
}
