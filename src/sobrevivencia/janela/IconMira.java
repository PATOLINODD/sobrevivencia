/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.janela;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import sobrevivencia.jogador.Jogador;
import sobrevivencia.mouse.MouseXY;
import sobrevivencia.spritesheet.SpriteSheet;

/**
 *
 * @author PATOLINODD
 */
public class IconMira {
    BufferedImage[] mira;
    BufferedImage[] miraFeedback;
    SpriteSheet spriteMira;
    public static boolean feedback;
    
    public int mx, my;
    
    public IconMira(int mx, int my){
        this.mx = mx;
        this.my = my;
        
        spriteMira = new SpriteSheet("/mira.png");
        mira = new BufferedImage[4];
        for(int i = 0; i < mira.length; i++){
            mira[i] = spriteMira.getImg(i*spriteMira.getImg().getHeight(), 0, spriteMira.getImg().getHeight(), spriteMira.getImg().getHeight());
        }
        miraFeedback = new BufferedImage[4];
        spriteMira = new SpriteSheet("/miraFeedback.png");
        for(int i = 0; i < miraFeedback.length; i++){
            miraFeedback[i] = spriteMira.getImg(i*spriteMira.getImg().getHeight(), 0, spriteMira.getImg().getHeight(), spriteMira.getImg().getHeight());
        }
    }
    
    int index, tempo, delay;
    public void acao(){
        this.mx = MouseXY.getX() - 80/2;
        this.my = MouseXY.getY() - 80/2;
        tempo++;
        if(tempo == 14){
            tempo = 0;
            index++;
            if(index == mira.length){
                index = 0;
            }            
        }
    }
    
    int tamanho = 48;
    
    public void graficos(Graphics g){
//        g.setColor(Color.GREEN);
//        g.fillRect(mx+tamanho/2-3, my+tamanho/2-3, 6, 6);
        if(feedback){
            delay++;
            if(delay == 5){
                delay = 0;
                feedback = false;
            }
            g.drawImage(miraFeedback[index], mx, my, tamanho, tamanho, null);
        } else {
            g.drawImage(mira[index], mx, my, tamanho, tamanho, null);
        }
    }
    
    public int getLargura(){
        return tamanho;
    }
    public int getAltura(){
        return tamanho;
    }
}
