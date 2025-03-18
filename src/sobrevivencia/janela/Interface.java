/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.janela;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import sobrevivencia.Init;
import static sobrevivencia.Sobrevivencia.framess;
import sobrevivencia.acao.PontosSpawn;
import static sobrevivencia.graficos.Graficos.escala;

/**
 *
 * @author Administrator
 */
public class Interface {
    
    public static double delay = 1000;
    private double cont = 0;
    
    
    public void graficos(Graphics g){
        
        cont++;
        if(cont >= 10){
            cont = 0;
            delay-=4;
            if(delay <= (Init.getJogador().getVida())){
                delay = (Init.getJogador().getVida());
            }
        }
        if(delay >= 1000){
            delay = 1000;
        }
        //vida
        int altura = 20;
        int tamanho = 300;
        g.setColor(Color.red);
        g.fillRect(10, 20, tamanho, altura);
        g.setColor(Color.white);
        g.fillRect(10, 20, (int)((delay/1000)*tamanho), altura);
        g.setColor(Color.green);
        g.fillRect(10, 20, (int)((Init.getJogador().getVida()/1000)*tamanho), altura);
        
        
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.setFont(new Font("TimeRoman", Font.PLAIN, 32));
        g2.drawString(String.valueOf(PontosSpawn.getRecord()), Janela.largura/2-32, 64);
        g2.drawString(String.valueOf(PontosSpawn.getOnda()), Janela.largura/2-32, 96);
        g2.drawString(String.valueOf(Init.getEntidades().size()), Janela.largura-escala-80, 32);
        
        g2.drawString(String.valueOf(Init.getJogador().getCargaAtual()), Janela.largura-escala-200, Janela.altura-escala-64);
        g2.drawString(String.valueOf(Init.getJogador().getMunicao()), Janela.largura-escala-160, Janela.altura-escala-64);
        
        //frames
        g2.drawString(String.valueOf(framess), Janela.largura-escala-80, 80);
        
    }
}
