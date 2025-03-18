/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import sobrevivencia.Init;
import sobrevivencia.acao.PontosSpawn;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.soudEffects.SoundEffects;
import sobrevivencia.som.Som;

/**
 *
 * @author PATOLINODD
 */
public class Bomba extends AbstractEntidade {

    public int vida = 30, tempoVida = 0;

    public Bomba(float x, float y, int largura, int altura) {
        super(x, y, largura, altura);
        this.range = 100;
    }

    boolean colidindo = false;

    double distancia = distancia((int) getX(), (int) getY(), Init.getJogador().getX(), Init.getJogador().getY());

    @Override
    public void acao() {
        this.rays = this.calcRays((int) this.getX() + this.getLargura() / 2, (int) this.getY() + this.getAltura() / 2, 30, range);

        tempoVida++;
        int tamanho = 20;
        if (tempoVida == vida) {
            tempoVida = 0;
            Init.removeBomba(this);
            gerarExplosao(50, (int) getX(), (int) getY(), tamanho, tamanho, 30, Color.red, 2, new Som(SoundEffects.getEXPLOSAO()));
            gerarExplosao(25, (int) getX(), (int) getY(), tamanho, tamanho, 30, Color.gray, 2, null);
            gerarExplosao(50, (int) getX(), (int) getY(), tamanho, tamanho, 30, Color.ORANGE, 2, null);

            for (Line2D.Float raioExplosao : this.getRays()) {
                Init.getJogador().alteraVida(raioExplosao
                        .intersects(Init.getJogador().getRetangulo()) ? -10 : 0);
            }
        }
    }

    @Override
    public void graficos(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) getX(), (int) getY(), getLargura(), getAltura());
        mostrarRange(g);
    }

    void mostrarRange(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.CYAN);
        g2.drawOval((int) this.getX() - this.getRange() + this.getLargura() / 2, (int) this.getY() - this.getRange() + this.getAltura() / 2, this.getRange() * 2, this.getRange() * 2);
    }

}
