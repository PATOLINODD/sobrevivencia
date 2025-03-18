/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.projeteis;

import java.awt.Color;
import sobrevivencia.som.Som;

/**
 *
 * @author PATOLINODD
 */
public class ProjetilIni extends AbstractProjetil{
    
    public ProjetilIni(float x, float y, int tamanho, double dx, double dy, double danoAtk, int tempoVida, Color cor, Som som) {
        super(x, y, tamanho, dx, dy, danoAtk, tempoVida, cor, som);
    }
}
