/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myfirstmenu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.System.Logger;

/**
 *
 * @author PATOLINODD
 */
public class MyFirstMenu extends Canvas implements Runnable, KeyListener, MouseListener {

    private static final Logger log = System.getLogger(MyFirstMenu.class.getName());

    private boolean isRunning = false;
    private Thread thread;
    private Graphics g;
    private BufferedImage img;
    public static final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    private WindowJF jf;
    
    public static void main(String[] args) {
        MyFirstMenu mfMenu = new MyFirstMenu();
        mfMenu.start();
    }

    public MyFirstMenu() {
        this.addKeyListener(this);
        this.addMouseListener(this);
        img = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
        
        jf = new WindowJF(this, "Meu game!");
        jf.openWindow(d);
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.red);
        g.fillRect(0, 0, d.width, d.height);

        g.drawImage(img, 0, 0, null);
        bs.show();
    }
    
    void update(){
    }

    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        thread.interrupt();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                frames++;
                delta--;
                render();
                update();
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(frames);
                frames = 0;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        jf.newJanela();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
