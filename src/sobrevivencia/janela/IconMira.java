package sobrevivencia.janela;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import sobrevivencia.mouse.MouseXY;
import sobrevivencia.spritesheet.SpriteSheet;

public class IconMira {
   BufferedImage[] mira;
   BufferedImage[] miraFeedback;
   SpriteSheet spriteMira;
   public static boolean feedback;
   public int mx;
   public int my;
   int index;
   int tempo;
   int delay;
   int tamanho = 48;

   public IconMira(int mx, int my) {
      this.mx = mx;
      this.my = my;
      this.spriteMira = new SpriteSheet("/mira.png");
      this.mira = new BufferedImage[4];

      int i;
      for(i = 0; i < this.mira.length; ++i) {
         this.mira[i] = this.spriteMira.getImg(i * this.spriteMira.getImg().getHeight(), 0, this.spriteMira.getImg().getHeight(), this.spriteMira.getImg().getHeight());
      }

      this.miraFeedback = new BufferedImage[4];
      this.spriteMira = new SpriteSheet("/miraFeedback.png");

      for(i = 0; i < this.miraFeedback.length; ++i) {
         this.miraFeedback[i] = this.spriteMira.getImg(i * this.spriteMira.getImg().getHeight(), 0, this.spriteMira.getImg().getHeight(), this.spriteMira.getImg().getHeight());
      }

   }

   public void acao() {
      this.mx = MouseXY.getX() - 40;
      this.my = MouseXY.getY() - 40;
      ++this.tempo;
      if (this.tempo == 14) {
         this.tempo = 0;
         ++this.index;
         if (this.index == this.mira.length) {
            this.index = 0;
         }
      }

   }

   public void graficos(Graphics g) {
      if (feedback) {
         ++this.delay;
         if (this.delay == 5) {
            this.delay = 0;
            feedback = false;
         }

         g.drawImage(this.miraFeedback[this.index], this.mx, this.my, this.tamanho, this.tamanho, (ImageObserver)null);
      } else {
         g.drawImage(this.mira[this.index], this.mx, this.my, this.tamanho, this.tamanho, (ImageObserver)null);
      }

   }

   public int getLargura() {
      return this.tamanho;
   }

   public int getAltura() {
      return this.tamanho;
   }
}
