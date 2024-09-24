package sobrevivencia.mapa;

import java.awt.image.BufferedImage;

public class Grama extends Tiles {
   public Grama(int x, int y, BufferedImage img) {
      super(x, y, img);
   }

   public Grama(int x, int y, int width, int height, BufferedImage img) {
      super(x, y, width, height, img);
   }
}
