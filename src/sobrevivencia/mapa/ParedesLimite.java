package sobrevivencia.mapa;

import java.awt.image.BufferedImage;

public class ParedesLimite extends Tiles {
   public ParedesLimite(int x, int y, BufferedImage img) {
      super(x, y, img);
   }

   public ParedesLimite(int x, int y, int width, int height, BufferedImage img) {
      super(x, y, width, height, img);
   }
}
