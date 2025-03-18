/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.projeteis;

import java.awt.Color;
import sobrevivencia.som.Som;

/**
 *
 * @author Administrator
 */
public class ProjeteisM4 extends AbstractProjetil {

    public ProjeteisM4(float x, float y, int tamanho, double dx, double dy, double danoAtk, int tempoVida, Color cor, Som som) {
        super(x, y, tamanho, dx, dy, danoAtk, tempoVida, cor, som);
    }
    
}
