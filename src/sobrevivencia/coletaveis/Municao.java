/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.coletaveis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;

/**
 *
 * @author Administrator
 */
public class Municao extends AbstractEntidade{
    private int x, y;
    private double acresDano = 0.5, tempo;
    
    public Municao(float x, float y, int largura, int altura){
        super(x, y, largura, altura);
        this.x = (int)x;
        this.y = (int)y;
    }
    
    @Override
    public void acao(){
        tempo++;
        if(tempo >= 300){
            tempo = 0;
            Init.removeMunicao(this);
        }
        
//        getThis();
    }
    
    @Override
    public void graficos(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(x, y, 20, 20);
    }
    
    private void getThis(){
//        Rectangle proj = new Rectangle(x+3, y+3, 20, 20);
//        Rectangle jogador = Init.getJogador().colisoes.rectangle;
//    
//        if(jogador.intersects(proj)){
//            Init.removeMunicao(this);
//            Init.getJogador().atira.addMunicao(30);
//        }
    }
}
