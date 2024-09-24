package sobrevivencia.spritesheet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpriteSheet {
   private BufferedImage img;

   public SpriteSheet(String path) {
      try {
         this.img = ImageIO.read(this.getClass().getResource(path));
      } catch (IOException var3) {
         Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, (String)null, var3);
      }

   }

   public BufferedImage getImg(int x, int y, int width, int height) {
      return this.img.getSubimage(x, y, width, height);
   }

   public BufferedImage getImg() {
      return this.img.getSubimage(0, 0, this.img.getWidth(), this.img.getHeight());
   }
}
