/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.explosoes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import sobrevivencia.Init;

/**
 *
 * @author Administrator
 */
public abstract class AbstractExplosao {
    
    protected double x, y, dx, dy, velocidade;
    private int largura, altura, vida, tempoMax;
    protected Color cor;
    
    public AbstractExplosao(double x, double y, int largura, int altura, int tempoMax, Color cor, double velocidade){
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
        this.tempoMax = tempoMax;
        this.velocidade = velocidade;
        
        this.dx = new Random().nextGaussian();
        this.dy = new Random().nextGaussian();
    }
    
    public void acao(){
        x += dx*velocidade;
        y += dy*velocidade;
        
        vida++;
        if(vida >= tempoMax) {
            vida = 0;
            Init.removeExplosao(this);
        }
    }
    
    public void graficos(Graphics g){
        g.setColor(cor);
        g.fillOval((int)x, (int)y, getLargura(), getAltura());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    
}
