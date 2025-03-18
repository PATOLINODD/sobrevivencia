/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.janela;

import sobrevivencia.Init;
import static sobrevivencia.graficos.Graficos.escala;

/**
 *
 * @author Administrator
 */
public class Camera {
    private float x, y;
    
    public void camera(){
        x = -Init.getJogador().getX() + (Janela.largura+escala)/3+96;
        y = -Init.getJogador().getY() + (Janela.altura+escala)/3;
        
        
//        if(x < (Mapa.larg*64 - (1302+(3*64)))*-1){
//            x = (Mapa.larg*64 - (1302+(3*64)))*-1;
//        }
//        if(x > (130)*-1){
//            x = (130)*-1;
//        }
//        if(y < (Mapa.altu*64- (1000- (2*64-32)))*-1){
//            y = (Mapa.altu*64- (1000-(2*64-32)))*-1;
//        }
//        if(y > (130)*-1){
//            y = (130)*-1;
//        }
    }
    

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
    
}
