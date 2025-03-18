/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.teste;

import sobrevivencia.Init;
import static sobrevivencia.Sobrevivencia.g;
import sobrevivencia.mapa.Parede;

/**
 *
 * @author PATOLINODD
 */
public class Teste {
    
    public void teste(){
        int larg = g.img.getWidth();
        int altu = g.img.getHeight();
        for(int xy = 0; xy < larg*altu; xy++){
            for(Parede parede : Init.getParedes()){
                if(xy == (parede.getX()+ parede.getY())){
                    System.out.println("posX: "+ parede.getX());
                    System.out.println("posY: "+ parede.getY());
                }
            }
        }
    }
}
