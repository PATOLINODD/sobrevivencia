package sobrevivencia.mapa;

import java.awt.image.BufferedImage;

public class Parede extends Tiles {
   public Parede(int x, int y, BufferedImage img) {
      super(x, y, img);
   }

   public Parede(int x, int y, int width, int height, BufferedImage img) {
      super(x, y, width, height, img);
   }
}
