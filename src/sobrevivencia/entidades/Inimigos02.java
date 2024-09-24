package sobrevivencia.entidades;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.spritesheet.SpriteSheet;

public class Inimigos02 extends Inimigos {
   public Inimigos02(float x, float y, int largura, int altura) {
      super(x, y, largura, altura);
      this.spriteCaminhada = new SpriteSheet("/SoldadoMedico.png");
      this.spriteAtira = new SpriteSheet("/MedicoAtirando.png");
      this.iniciaBufferedImg();
      this.updateVida();
   }

   public void acao() {
      this.updateLocale();
      this.initAngle();
      this.segueOjogador();
      this.validaVida();
      this.iniciaAni();
      this.iniciaRectangle();
      this.updateFireRate();
      this.setChanceDropMedKit();
      this.setMask(3, 3, this.getLargura() - 6, this.getAltura() - 6);
   }

   void setChanceDropMedKit() {
      String var1 = Opcoes.qualDificuldade();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1289163222:
         if (var1.equals("expert")) {
            var2 = 1;
         }
         break;
      case 1659541438:
         if (var1.equals("dificil")) {
            var2 = 0;
         }
      }

      switch(var2) {
      case 0:
         this.setChanceDropMedKit(50);
         break;
      case 1:
         this.setChanceDropMedKit(70);
         break;
      default:
         this.setChanceDropMedKit(0);
      }

   }

   public void graficos(Graphics g) {
      this.iniciaRayCasting(g);
      int tamanho = 96;
      this.setXx((int)this.getX());
      this.setYy((int)this.getY() - 32);
      Graphics2D g2 = (Graphics2D)g.create();
      this.mostraHitBox(g2);
      AffineTransform at = g2.getTransform();
      g2.rotate(this.getAngulo(), (double)this.getXi(), (double)(this.getY() + 16.0F));
      if (!this.estaAtirando) {
         g2.drawImage(this.caminhada[this.index], this.getXx(), this.getYy(), tamanho, tamanho, (ImageObserver)null);
      } else {
         g2.drawImage(this.atira[this.ind], this.getXx(), this.getYy(), tamanho, tamanho, (ImageObserver)null);
      }

      g2.dispose();
      g2.setTransform(at);
   }
}
