/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.som;

import java.io.BufferedInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import sons.Sons;

/**
 *
 * @author PATOLINODD
 */
public class Som extends PlaybackListener{

    private final Logger log = Logger.getLogger(Som.class.getName());

    AdvancedPlayer player;
    int framePaused = 0;

    public Som(String fileMusic) {
        try {
            BufferedInputStream bis = new BufferedInputStream(Sons.class.getResourceAsStream(fileMusic));
            player = new AdvancedPlayer(bis);
            
            player.setPlayBackListener(new PlaybackListener(){
                @Override
                public void playbackFinished(PlaybackEvent event){
                    framePaused = event.getFrame();
                }
            });
        } catch (JavaLayerException ex) {
            log.log(Level.SEVERE, "Error in construct Som(String fileMusic)", ex);
        }
    }

    public void play() {
        new Thread() {
            @Override
            public void run() {
                try {
                    if (player != null) {
                        player.play(framePaused, Integer.MAX_VALUE);
                    }
                } catch (JavaLayerException ex) {
                    log.log(Level.SEVERE, "Error in method play()", ex);
                }
            }
        }.start();
    }

    public void stop() {
        System.out.println(framePaused);
        if (player != null) {
            player.stop();
        }
    }
}
