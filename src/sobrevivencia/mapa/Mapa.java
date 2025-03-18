/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.mapa;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sobrevivencia.Area.Armas;
import sobrevivencia.Area.Explosivos;
import sobrevivencia.Area.Sentinelas;
import sobrevivencia.Area.Suportes;
import sobrevivencia.Init;
import sobrevivencia.pontosDeSpawn.PontoDeSpawn;
import sobrevivencia.spritesheet.SpriteSheet;

/**
 *
 * @author PATOLINODD
 */
public class Mapa {
    
    private static BufferedImage mapa;
    public Tiles[] tiles;
    public static int larg, altu;
    
    public Mapa(String path){
        try {
            mapa = ImageIO.read(this.getClass().getResource(path));
            
            int[] pixels = new int[mapa.getWidth() * mapa.getHeight()];
            tiles = new Tiles[mapa.getWidth() * mapa.getHeight()];
            larg = mapa.getWidth();
            altu = mapa.getHeight();
            mapa.getRGB(0, 0, mapa.getWidth(), mapa.getHeight(), pixels, 0, mapa.getWidth());
            int tamanho = 32;
            
            SpriteSheet sprites = new SpriteSheet("/compras.png");
            for(int xx = 0; xx < mapa.getWidth(); xx++){
                for(int yy = 0; yy < mapa.getHeight(); yy++){
                    
                    tiles[xx + (yy * mapa.getWidth())] = new Tiles(xx * tamanho, yy * tamanho, new SpriteSheet("/chao02.png").getImg());
//                    Init.tiles1.add(new Tiles(xx * tamanho, yy * tamanho, new SpriteSheet("/chao02.png").getImg()));
                    
                    switch(pixels[xx + (yy * mapa.getWidth())]){
                        case 0xFFFFFFFF:
                            Parede parede = new Parede(xx * tamanho, yy * tamanho, new SpriteSheet("/PAREDE02.png").getImg());
                            Init.addParede(parede);
                            tiles[xx + (yy * mapa.getWidth())] = parede;
                            break;
                        case 0xff2CE8F5:
                            Init.getJogador().setX(xx * tamanho);
                            Init.getJogador().setY(yy * tamanho);
                            break;
                        case 0xff000000:
                            tiles[xx + (yy * mapa.getWidth())] = new Tiles(xx * tamanho, yy * tamanho, new SpriteSheet("/chao03.png").getImg());
                            break;
                        case 0xffFF6A00:
                            tiles[xx + (yy * mapa.getWidth())] = new Tiles(xx * tamanho, yy * tamanho, new SpriteSheet("/chao02.png").getImg());
                            break;
                        case 0xff267F00:
                            tiles[xx + (yy * mapa.getWidth())] = new Grama(xx * tamanho, yy * tamanho, new SpriteSheet("/grama.png").getImg());
                        break;
                        case 0xff4CFF00:
                            Init.addParedesParaJogador(new ParedesLimite(xx * tamanho, yy *tamanho, new SpriteSheet("/PAREDE02.png").getImg()));
                            break;
                        case 0xffFF0000:
                            Init.addPontoSpawn(new PontoDeSpawn(xx *tamanho, yy * tamanho));
                            break;
                        case 0xff123456:
                            Init.setSentinelas(new Sentinelas(xx * tamanho, yy * tamanho, tamanho, tamanho, sprites.getImg(64, 0, 32, 32)));
                            break;
                        case 0xff123478:
                            Init.setExplosivos(new Explosivos(xx * tamanho, yy * tamanho, tamanho, tamanho, sprites.getImg(32, 0, 32, 32)));
                            break;
                        case 0xff12349A:
                            Init.setArmas(new Armas(xx * tamanho, yy * tamanho, tamanho, tamanho, sprites.getImg(0, 0, 32, 32)));
                            break;
                        case 0xff1234BF:
                            Init.setSuportes(new Suportes(xx * tamanho, yy * tamanho, tamanho, tamanho, sprites.getImg(96, 0, 32, 32)));
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void graficos(Graphics g){
        for(int xx = 0; xx < larg; xx++){
            for(int yy = 0; yy < altu; yy++){
                Tiles tile = tiles[xx + (yy * larg)];
                tile.graficos(g);
            }
        }
        for(ParedesLimite paredeParaJogador : Init.getParedesParaJogador()){
            paredeParaJogador.graficos(g);
        }
    }
    
    public static BufferedImage getMapa(){
        return Mapa.mapa;
    }
}
