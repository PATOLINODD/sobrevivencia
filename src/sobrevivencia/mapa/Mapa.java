package sobrevivencia.mapa;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sobrevivencia.Init;
import sobrevivencia.Area.Armas;
import sobrevivencia.Area.Explosivos;
import sobrevivencia.Area.Sentinelas;
import sobrevivencia.Area.Suportes;
import sobrevivencia.pontosDeSpawn.PontoDeSpawn;
import sobrevivencia.spritesheet.SpriteSheet;

public class Mapa {
   private static BufferedImage mapa;
   public Tiles[] tiles;
   public static int larg;
   public static int altu;

   public Mapa(String path) {
      try {
         mapa = ImageIO.read(this.getClass().getResource(path));
         int[] pixels = new int[mapa.getWidth() * mapa.getHeight()];
         this.tiles = new Tiles[mapa.getWidth() * mapa.getHeight()];
         larg = mapa.getWidth();
         altu = mapa.getHeight();
         mapa.getRGB(0, 0, mapa.getWidth(), mapa.getHeight(), pixels, 0, mapa.getWidth());
         int tamanho = 32;
         SpriteSheet sprites = new SpriteSheet("/compras.png");

         for(int xx = 0; xx < mapa.getWidth(); ++xx) {
            for(int yy = 0; yy < mapa.getHeight(); ++yy) {
               this.tiles[xx + yy * mapa.getWidth()] = new Tiles(xx * tamanho, yy * tamanho, (new SpriteSheet("/chao02.png")).getImg());
               switch(pixels[xx + yy * mapa.getWidth()]) {
               case -16777216:
                  this.tiles[xx + yy * mapa.getWidth()] = new Tiles(xx * tamanho, yy * tamanho, (new SpriteSheet("/chao03.png")).getImg());
                  break;
               case -15584170:
                  Init.setSentinelas(new Sentinelas(xx * tamanho, yy * tamanho, tamanho, tamanho, sprites.getImg(64, 0, 32, 32)));
                  break;
               case -15584136:
                  Init.setExplosivos(new Explosivos(xx * tamanho, yy * tamanho, tamanho, tamanho, sprites.getImg(32, 0, 32, 32)));
                  break;
               case -15584102:
                  Init.setArmas(new Armas(xx * tamanho, yy * tamanho, tamanho, tamanho, sprites.getImg(0, 0, 32, 32)));
                  break;
               case -15584065:
                  Init.setSuportes(new Suportes(xx * tamanho, yy * tamanho, tamanho, tamanho, sprites.getImg(96, 0, 32, 32)));
                  break;
               case -14254336:
                  this.tiles[xx + yy * mapa.getWidth()] = new Grama(xx * tamanho, yy * tamanho, (new SpriteSheet("/grama.png")).getImg());
                  break;
               case -13833995:
                  Init.getJogador().setX((float)(xx * tamanho));
                  Init.getJogador().setY((float)(yy * tamanho));
                  break;
               case -11731200:
                  Init.addParedesParaJogador(new ParedesLimite(xx * tamanho, yy * tamanho, (new SpriteSheet("/PAREDE02.png")).getImg()));
                  break;
               case -65536:
                  Init.addPontoSpawn(new PontoDeSpawn(xx * tamanho, yy * tamanho));
                  break;
               case -38400:
                  this.tiles[xx + yy * mapa.getWidth()] = new Tiles(xx * tamanho, yy * tamanho, (new SpriteSheet("/chao02.png")).getImg());
                  break;
               case -1:
                  Parede parede = new Parede(xx * tamanho, yy * tamanho, (new SpriteSheet("/PAREDE02.png")).getImg());
                  Init.addParede(parede);
                  this.tiles[xx + yy * mapa.getWidth()] = parede;
               }
            }
         }
      } catch (IOException var8) {
         Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, (String)null, var8);
      }

   }

   public void graficos(Graphics g) {
      for(int xx = 0; xx < larg; ++xx) {
         for(int yy = 0; yy < altu; ++yy) {
            Tiles tile = this.tiles[xx + yy * larg];
            tile.graficos(g);
         }
      }

      Iterator var5 = Init.getParedesParaJogador().iterator();

      while(var5.hasNext()) {
         ParedesLimite paredeParaJogador = (ParedesLimite)var5.next();
         paredeParaJogador.graficos(g);
      }

   }

   public static BufferedImage getMapa() {
      return mapa;
   }
}
