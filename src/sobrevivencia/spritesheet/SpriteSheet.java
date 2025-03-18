/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.spritesheet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author PATOLINODD
 */
public class SpriteSheet {
    
    private BufferedImage img;
    public SpriteSheet(String path){
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage getImg(int x, int y, int width, int height){
        return img.getSubimage(x, y, width, height);
    }
    public BufferedImage getImg(){
        return img.getSubimage(0, 0, img.getWidth(), img.getHeight());
    }
}
