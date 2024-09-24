package sobrevivencia.chefes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.mapa.Mapa;
import sobrevivencia.spritesheet.SpriteSheet;

public class Helicoptero extends AbstractEntidade {
   BufferedImage helicoptero = (new SpriteSheet("/helicoptero.png")).getImg();
   BufferedImage helice = (new SpriteSheet("/helice.png")).getImg();
   public int vidaH = 1000;
   int xx;
   int yy;
   int delay;
   long velocidadeS = 0L;
   double orbitAngle;
   public Shape shape = new Rectangle();
   int nx;
   int ny;
   long rotate;
   long velocidadeH;
   public Rectangle helicop = new Rectangle();

   public Helicoptero(float x, float y, int largura, int altura) {
      super(x, y, largura, altura);
   }

   public void acao() {
      this.velocidadeS = 1L;
      this.orbitAngle += 0.1D;
      this.rotate += 7L;
      this.shape = new Rectangle(this.nx - this.helice.getWidth() + 110, this.ny + this.helice.getHeight() + 30, this.helice.getWidth() * 5, this.helice.getHeight());
      AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(this.orbitAngle), (double)(this.nx + this.helice.getWidth() * 4), (double)(this.ny + this.helice.getHeight() * 2 + 2));
      this.shape = at.createTransformedShape(this.shape);
      this.orbita();
      this.removeH();
   }

   private void removeH() {
      if (this.vidaH <= 0) {
         this.vidaH = 0;
         Init.getHelicopteros().remove(this);
      }

   }

   private void orbita() {
      double rads = Math.toRadians(this.orbitAngle);
      int fullLength = Math.round((float)(Mapa.altu * 24));
      this.nx = Math.round((float)(Math.cos(rads) * (double)fullLength)) + Mapa.larg / 2 * 16;
      this.ny = Math.round((float)(Math.sin(rads) * (double)fullLength)) + Mapa.altu / 2 * 24;
   }

   public void graficos(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      AffineTransform at = g2.getTransform();
      g2.rotate(Math.toRadians(this.orbitAngle), (double)(this.nx + this.helice.getWidth() * 4), (double)(this.ny + this.helice.getHeight() * 2 + 2));
      g2.drawImage(this.helicoptero, this.nx, this.ny, this.helicoptero.getWidth() * 4, this.helicoptero.getHeight() * 4, (ImageObserver)null);
      this.giraHelice(g2);
      g2.setTransform(at);
   }

   private void giraHelice(Graphics2D g2) {
      g2.rotate(Math.toDegrees((double)this.rotate), (double)(this.nx + this.helice.getWidth() * 2 + 28), (double)(this.ny + this.helice.getHeight() * 2 + 2));
      g2.setColor(Color.red);
      g2.drawImage(this.helice, this.nx + 28, this.ny + 4, this.helice.getWidth() * 4, this.helice.getHeight() * 4, (ImageObserver)null);
   }
}
