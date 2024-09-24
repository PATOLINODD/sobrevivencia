package sobrevivencia.explosoes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import sobrevivencia.Init;

public abstract class AbstractExplosao {
   protected double x;
   protected double y;
   protected double dx;
   protected double dy;
   protected double velocidade;
   private int largura;
   private int altura;
   private int vida;
   private int tempoMax;
   protected Color cor;

   public AbstractExplosao(double x, double y, int largura, int altura, int tempoMax, Color cor, double velocidade) {
      this.x = x;
      this.y = y;
      this.largura = largura;
      this.altura = altura;
      this.cor = cor;
      this.tempoMax = tempoMax;
      this.velocidade = velocidade;
      this.dx = (new Random()).nextGaussian();
      this.dy = (new Random()).nextGaussian();
   }

   public void acao() {
      this.x += this.dx * this.velocidade;
      this.y += this.dy * this.velocidade;
      ++this.vida;
      if (this.vida >= this.tempoMax) {
         this.vida = 0;
         Init.removeExplosao(this);
      }

   }

   public void graficos(Graphics g) {
      g.setColor(this.cor);
      g.fillOval((int)this.x, (int)this.y, this.getLargura(), this.getAltura());
   }

   public double getX() {
      return this.x;
   }

   public void setX(double x) {
      this.x = x;
   }

   public double getY() {
      return this.y;
   }

   public void setY(double y) {
      this.y = y;
   }

   public int getLargura() {
      return this.largura;
   }

   public void setLargura(int largura) {
      this.largura = largura;
   }

   public int getAltura() {
      return this.altura;
   }

   public void setAltura(int altura) {
      this.altura = altura;
   }
}
