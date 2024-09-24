package sobrevivencia.mapa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D.Float;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Iterator;
import sobrevivencia.Init;

public class Tiles {
   public BufferedImage img;
   int x;
   int y;
   int width = 32;
   int height = 32;
   protected Rectangle rectangle = new Rectangle();
   boolean jogadorEstaVendo;

   public Tiles(int x, int y, BufferedImage img) {
      this.x = x;
      this.y = y;
      this.img = img;
      this.rectangle = new Rectangle(x, y, this.width, this.height);
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
      g.drawImage(this.img, this.x, this.y, (ImageObserver)null);
      Graphics2D g2 = (Graphics2D)g;
      g2.setColor(Color.red);
   }

   public void jogadorEstaVendo() {
      this.jogadorEstaVendo = false;
      Iterator var1 = Init.getRays().iterator();

      while(var1.hasNext()) {
         Float ray = (Float)var1.next();
         if (ray.intersects(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()))) {
            this.jogadorEstaVendo = true;
         }
      }

   }

   public int getX() {
      return this.x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return this.y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getWidth() {
      return this.img.getWidth();
   }

   public int getHeight() {
      return this.img.getHeight();
   }

   public Rectangle getRectangle() {
      return this.rectangle;
   }

   public void setRectangle(Rectangle rectangle) {
      this.rectangle = rectangle;
   }
}
