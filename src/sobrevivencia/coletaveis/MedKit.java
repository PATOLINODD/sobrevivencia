/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.coletaveis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.menu.Opcoes;

/**
 *
 * @author Administrator
 */
public class MedKit extends AbstractEntidade {

    private double acresVida;
    private int tempo, maxTempo;

    public MedKit(float x, float y, int largura, int altura) {
        super(x, y, largura, altura);
        switch (Opcoes.qualDificuldade()) {
            case Opcoes.FACIL:
                this.acresVida = 400;
                this.maxTempo = 500;
                break;
            case Opcoes.NORMAL:
                this.acresVida = new Random().nextInt(100) + 200;
                this.maxTempo = 500; 
                break;
            default:
                this.acresVida = new Random().nextInt(100) + 100;
                this.maxTempo = 300;
                break;
        }
    }

    @Override
    public void graficos(Graphics g) {
        int tamanho = 32;
        g.setColor(Color.red);
        g.fillRect((int) getX(), (int) getY(), tamanho, tamanho);
        g.setColor(Color.white);
        g.fillRect((int) getX() + 2, (int) getY() + 2, tamanho - 4, tamanho - 4);
        g.setColor(Color.red);
        g.fillRect((int) getX() + 5, (int) getY() + 11, tamanho - 10, tamanho - 22);
        g.fillRect((int) getX() + 11, (int) getY() + 5, tamanho - 22, tamanho - 10);
    }

    @Override
    public void acao() {
        tempo++;
        if (tempo >= this.maxTempo) {
            tempo = 0;
            Init.removeMedKit(this);
        }
        if (Init.getJogador().getVida() < 1000) {
            saude();
        }
    }

    private void saude() {
        Rectangle vida = new Rectangle((int) getX() + 3, (int) getY() + 3, 34, 34);
        Rectangle jogador = Init.getJogador().getRetangulo();

        if (jogador.intersects(vida)) {
            Init.removeMedKit(this);
            Init.getJogador().alteraVida(acresVida);
        }
    }
}
