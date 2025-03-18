/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.projeteis;

import java.awt.Color;
import java.awt.Graphics;
import sobrevivencia.Init;

/**
 *
 * @author PATOLINODD
 */
public class RastroProjetil extends AbstractProjetil {

    public RastroProjetil(float x, float y, int tamanho, double dx, double dy, double danoAtk, int tempoVida, Color cor) {
        super(x, y, tamanho, dx, dy, danoAtk, tempoVida, cor, null);
    }

    int vidaRatro;

    @Override
    public void acao() {
        vidaRatro++;
        if (vidaRatro >= tempoVida) {
            vidaRatro = 0;
            Init.removeProjetil(this);
        }
    }

    @Override
    public void graficos(Graphics g) {
        if (vidaRatro == tempoVida / 5) {
            g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 200));
        } else if (vidaRatro == tempoVida / 3) {
            g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 150));
        } else if (vidaRatro >= tempoVida / 2) {
            g.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 100).darker());
        } else {
            g.setColor(cor);
        }
        g.fillOval((int) getX(), (int) getY(), getTamanho(), getTamanho());
    }
}
