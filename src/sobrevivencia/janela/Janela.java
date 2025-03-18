/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.janela;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import sobrevivencia.Sobrevivencia;

public class Janela {
    
    public static final int largura = Toolkit.getDefaultToolkit().getScreenSize().width,
                            altura = Toolkit.getDefaultToolkit().getScreenSize().height;
    JFrame jf;
    
    public Janela(){
        jf = new JFrame("Sobrevivencia");
    }
    
    public void janela(Sobrevivencia sob){
        
        sob.setPreferredSize(new Dimension(largura, altura));
        
        jf.add(sob);
        jf.setUndecorated(true);
        jf.pack();
//        jf.setIconImage(new SpriteSheet("/soldadoatira.png").getImg());
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        setCursor(jf);
    }
    
    private void setCursor(JFrame jf){
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Image icon = toolKit.getImage(getClass().getResource("/icon.png"));
        Cursor cursor = toolKit.createCustomCursor(icon, new Point(0, 0), "img");
        jf.setCursor(cursor);
    }
    
}
