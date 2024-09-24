package sobrevivencia.som;

import java.io.BufferedInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import sons.Sons;

public class Som {
   private final Logger log = Logger.getLogger(Som.class.getName());
   AdvancedPlayer player;

   public Som(String fileMusic) {
      try {
         BufferedInputStream bis = new BufferedInputStream(Sons.class.getResourceAsStream(fileMusic));
         this.player = new AdvancedPlayer(bis);
      } catch (JavaLayerException var3) {
         this.log.log(Level.SEVERE, "Error in construct Som(String fileMusic)", var3);
      }

   }

   public void play() {
      (new Thread() {
         public void run() {
            try {
               if (Som.this.player != null) {
                  Som.this.player.play();
               }
            } catch (JavaLayerException var2) {
               Som.this.log.log(Level.SEVERE, "Error in method play()", var2);
            }

         }
      }).start();
   }
}
