/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstmenu;

import java.awt.Canvas;
import javax.swing.JFrame;

/**
 *
 * @author PATOLINODD
 */
public abstract class AbstractMenu {
    
    private static final System.Logger log = System.getLogger(WindowJF.class.getName());

    JFrame jf;
    JFrame pf;
    Canvas canvas;
    String title;

    public AbstractMenu(Canvas c, String t) {
        log.log(System.Logger.Level.INFO, "Creating all componets!");
        this.canvas = c;
        this.title = t;
    }

}
