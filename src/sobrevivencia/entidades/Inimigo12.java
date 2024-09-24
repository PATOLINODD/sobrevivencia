package sobrevivencia.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import sobrevivencia.Init;
import sobrevivencia.janela.Janela;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.projeteis.DanoAtkArmas;
import sobrevivencia.projeteis.ProjetilIni;
import sobrevivencia.som.Som;
import sobrevivencia.soudEffects.SoundEffects;
import sobrevivencia.spritesheet.SpriteSheet;

public class Inimigo12 extends Inimigos {
   double angulo1;
   double angulo2;
   double angulo3;

   public Inimigo12(float x, float y, int largura, int altura) {
      super(x, y, largura, altura);
      this.spriteCaminhada = new SpriteSheet("/SoldadoBomba.png");
      this.spriteAtira = new SpriteSheet("/SoldadoBombaAtirando.png");
      this.iniciaBufferedImg();
      this.updateVida();
      this.fireRate = 80;
      this.cargaAtual = 6;
      this.carga = 6;
   }

   public void acao() {
      this.updateLocale();
      this.initAngle();
      this.angulo1 = Math.atan2((double)Init.getJogador().getY() + 24.0D - (double)this.getY(), (double)Init.getJogador().getX() + 24.0D - (double)this.getX());
      this.angulo2 = Math.atan2((double)Init.getJogador().getY() + 8.0D - (double)this.getY(), (double)Init.getJogador().getX() + 8.0D - (double)this.getX());
      this.angulo3 = Math.atan2((double)Init.getJogador().getY() - 24.0D - (double)this.getY(), (double)Init.getJogador().getX() - 24.0D - (double)this.getX());
      this.setRange(Janela.altura / 4);
      this.segueOjogador();
      this.validaVida();
      this.iniciaAni();
      this.iniciaRectangle();
      this.setChanceDropBomba(3);
      this.updateFireRate();
      this.setMask(3, 3, this.getLargura() - 6, this.getAltura() - 6);
      this.setChanceDropMedKit(200);
   }

   void updateFireRate() {
      String var1 = Opcoes.qualDificuldade();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case 97187371:
         if (var1.equals("facil")) {
            var2 = 0;
         }
      default:
         switch(var2) {
         case 0:
            this.setFire(80);
            break;
         default:
            this.setFire(50);
         }

      }
   }

   void atira() {
      double dx1 = Math.cos(this.angulo1);
      double dy1 = Math.sin(this.angulo1);
      double dx2 = Math.cos(this.angulo2);
      double dy2 = Math.sin(this.angulo2);
      double dx3 = Math.cos(this.angulo3);
      double dy3 = Math.sin(this.angulo3);
      int fullLenght = Math.round(18.0F);
      int xi1 = (int)Math.round(dx1 * (double)fullLenght);
      int yi1 = (int)Math.round(dy1 * (double)fullLenght);
      int xi2 = (int)Math.round(dx2 * (double)fullLenght);
      int yi2 = (int)Math.round(dy2 * (double)fullLenght);
      int xi3 = (int)Math.round(dx3 * (double)fullLenght);
      int yi3 = (int)Math.round(dy3 * (double)fullLenght);
      int tamanho = 10;
      int tempoVida = 50;
      ++this.fireRate;
      if (this.fireRate >= this.fire) {
         this.fireRate = 0;
         if (this.cargaAtual > 0) {
            this.estaAtirando = true;
            Init.addProjetil(new ProjetilIni(this.getX() + 8.0F + (float)xi1, this.getY() + 8.0F + (float)yi1, tamanho, dx1, dy1, (double)DanoAtkArmas.getDanoM4(), tempoVida, Color.MAGENTA, new Som(SoundEffects.getSHOOTGUN())));
            Init.addProjetil(new ProjetilIni(this.getX() + 8.0F + (float)xi2, this.getY() + 8.0F + (float)yi2, tamanho, dx2, dy2, (double)DanoAtkArmas.getDanoM4(), tempoVida, Color.MAGENTA, (Som)null));
            Init.addProjetil(new ProjetilIni(this.getX() + 8.0F + (float)xi3, this.getY() + 8.0F + (float)yi3, tamanho, dx3, dy3, (double)DanoAtkArmas.getDanoM4(), tempoVida, Color.MAGENTA, (Som)null));
            this.diminuiCargaAtual();
         }
      }

      this.recarregar();
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
