/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.explosoes;

import java.awt.Color;

/**
 *
 * @author Administrator
 */
public class Explosao extends AbstractExplosao{
    
    public Explosao(double x, double y, int largura, int altura, int tempoMax, Color cor, double velocidade) {
        super(x, y, largura, altura, tempoMax, cor, velocidade);
    }
    
}
