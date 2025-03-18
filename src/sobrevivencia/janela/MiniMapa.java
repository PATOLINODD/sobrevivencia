/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.janela;

import java.awt.Color;
import java.awt.Graphics;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.mapa.Mapa;
import sobrevivencia.mapa.Parede;

/**
 *
 * @author PATOLINODD
 */
public class MiniMapa {
    
    public void graficos(Graphics g){
        final int escala = 10, tamanho = escala, tamanhoMapa = 32;
        int x = 400, y = 150;
        int alfa = 50;
        /////////mini mapa////////
        g.setColor(new Color(50, 100, 0, alfa));
        g.fillRect(x, y, Mapa.larg*escala, Mapa.altu*escala);
        
        g.setColor(new Color(255, 255, 255));
        g.fillRect((int)(Init.getJogador().getX()/tamanhoMapa*escala)+x, (int)(Init.getJogador().getY()/tamanhoMapa*escala)+y, tamanho, tamanho);
        
        g.setColor(new Color(255, 0, 0));
        for(AbstractEntidade entidade : Init.getEntidades()){
            if(entidade instanceof Inimigos){
                Inimigos inimigo = (Inimigos) entidade;
                g.fillRect((int)(inimigo.getX()/tamanhoMapa*escala)+x, (int)(inimigo.getY()/tamanhoMapa*escala)+y, tamanho, tamanho);
            }
        }
        for(Parede parede : Init.getParedes()){
            g.setColor(new Color(100, 255, 0, alfa));
            g.fillRect(parede.getX()/tamanhoMapa*escala+x, parede.getY()/tamanhoMapa*escala+y, tamanho, tamanho);
        }
//        for(Parede parede : Init.getParedesParaJogador()){
//            g.setColor(new Color(100, 255, 0, alfa));
//            g.fillRect(parede.getX()/tamanhoMapa*escala+x, parede.getY()/tamanhoMapa*escala+y, tamanho, tamanho);
//        }
    }
}
