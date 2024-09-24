/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstmenu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author PATOLINODD
 */
public class WindowJF extends AbstractMenu {

    private static final Logger log = System.getLogger(WindowJF.class.getName());

    public WindowJF(Canvas c, String t) {
        super(c, t);
        log.log(Level.INFO, "Creating all componets!");
    }

    public void openWindow(Dimension d ) {
        open(d.width, d.height);
    }
    public void openWindow(int w, int h ) {
        open(w, h);
    }

    private void open(int w, int h) {
        jf = new JFrame(title);
        jf.add(canvas);
        jf.pack();
        jf.setSize(w, h);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.requestFocus();
    }
    
    public void newJanela() {
        Dimension d = new Dimension(400, 600);
        
        pf = new JFrame();
        pf.setAlwaysOnTop(true);
        pf.setUndecorated(true);
        pf.setBackground(new Color(0,0,0,110));
        
        JPanel p = new JPanel();
        p.setBackground(new Color(100,100,100,52));
        JButton b = new JButton("Click me!");
        p.setPreferredSize(d);
        p.add(b);
        pf.add(p);
        
        b.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pf.setVisible(false);
            }
        });
        
        pf.setPreferredSize(d);
        pf.pack();
        pf.setVisible(true);
        pf.setLocationRelativeTo(canvas);
    }
}
