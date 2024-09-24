package sobrevivencia.Area;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import sobrevivencia.Init;
import sobrevivencia.janela.Janela;
import sobrevivencia.spritesheet.SpriteSheet;

public class AbstractArea {
   int x;
   int y;
   int larg;
   int altu;
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

   public int getLarg() {
      return this.larg;
   }

   public void setLarg(int larg) {
      this.larg = larg;
   }

   public int getAltu() {
      return this.altu;
   }

   public void setAltu(int altu) {
      this.altu = altu;
   }

   public boolean isComprar() {
      return this.comprar;
   }

   public void setComprar(boolean comprar) {
      this.comprar = comprar;
   }

   public void graficos(Graphics g) {
      g.setColor(Color.black);
      g.fillRect(this.x, this.y, this.altu, this.larg);
      if (this.image != null) {
         g.drawImage(this.image, this.x, this.y, (ImageObserver)null);
      }

      if (this.comprar()) {
         Graphics2D g2 = (Graphics2D)g;
         g2.setColor(Color.white);
         g2.drawString("F", this.x, this.y);
         if (this.comprar) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(100 - (int)Init.getCam().getX(), 100 - (int)Init.getCam().getY(), Janela.largura - 200, Janela.altura - 200);
         }
      } else {
         this.comprar = false;
      }

   }

   public boolean comprar() {
      Rectangle este = new Rectangle(this.x, this.y, this.larg, this.altu);
      return este.intersects(Init.getJogador().getRetangulo());
   }
}
