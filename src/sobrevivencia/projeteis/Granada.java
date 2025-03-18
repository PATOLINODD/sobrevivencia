/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.projeteis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import sobrevivencia.Init;
import sobrevivencia.mapa.Parede;
import sobrevivencia.som.Som;

/**
 *
 * @author PATOLINODD
 */
public class Granada extends AbstractProjetil {

    public Granada(float x, float y, int tamanho, double dx, double dy, double danoAtk, int tempoVida, Color cor, Som som) {
        super(x, y, tamanho, dx, dy, danoAtk, tempoVida, cor, som);
    }
    int nVelocidade = 1, tempoReduzVel;

    double angulo;

    @Override
    public void acao() {
//        tempoReduzVel++;
//        if(tempoReduzVel >= 10){
//            tempoReduzVel = 0;
//            nVelocidade--;
//        }
        tamanho = 15;
        if (nVelocidade <= 0) {
            nVelocidade = 0;
        }

        vida++;
        if (vida >= tempoVida) {
            vida = 0;
            Init.removeProjetil(this);
//            gerarExplosao(50, (int)this.getX()+10, (int)this.getY()+10, 2, 10, Color.YELLOW);
//            gerarExplosao(50, (int)this.getX()+10, (int)this.getY()+10, 2, 10, Color.RED);
//            gerarExplosao(50, (int)this.getX()+10, (int)this.getY()+10, 2, 10, Color.BLACK);
        }
        paraDireita((float) (dx * nVelocidade));
        paraBaixo((float) (dy * nVelocidade));

        iniciaAngulo();
        rebate();
    }

    private void iniciaAngulo() {
        angulo = Math.atan2((double) Init.getJogador().getY() + 8 - getY(), (double) Init.getJogador().getX() + 8 - getX()) * -1;
    }

    Rectangle granadaCima = new Rectangle((int) getX() + 3, (int) getY(), this.tamanho - 6, 3);
    Rectangle granadaBaixo = new Rectangle((int) getX() + 3, (int) getY() + this.tamanho, this.tamanho - 6, 3);
    Rectangle granadaEsquerda = new Rectangle((int) getX(), (int) getY() + 3, 3, this.tamanho - 6);
    Rectangle granadaDireita = new Rectangle((int) getX() + this.tamanho, (int) getY() + 3, 3, this.tamanho - 6);

    Rectangle granada = new Rectangle((int) getX(), (int) getY(), getTamanho(), getTamanho());

    @Override
    public void graficos(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int) x, (int) y, this.tamanho, this.tamanho);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.draw(granadaCima);
        g2.draw(granadaBaixo);
        g2.draw(granadaEsquerda);
        g2.draw(granadaDireita);
    }
    int ttamanhoP = 10;
    int ttamanho = 6;

    private void rebate() {
        granada = new Rectangle((int) getX(), (int) getY(), getTamanho(), getTamanho());

        granadaCima = new Rectangle((int) getX() + 3, (int) getY(), this.tamanho - 6, 3);
        granadaBaixo = new Rectangle((int) getX() + 3, (int) getY() + this.tamanho - 3, this.tamanho - 6, 3);
        granadaEsquerda = new Rectangle((int) getX(), (int) getY() + 3, 3, this.tamanho - 6);
        granadaDireita = new Rectangle((int) getX() + this.tamanho - 3, (int) getY() + 3, 3, this.tamanho - 6);

        for (Parede parede : Init.getParedes()) {
            Rectangle p = new Rectangle(parede.getX(), parede.getY(), parede.getWidth(), parede.getHeight());
            if (granadaCima.intersects(p)) {
                this.dy = -this.dy;
            }
            if (granadaBaixo.intersects(p)) {
                this.dy = -this.dy;
            }
            if (granadaEsquerda.intersects(p)) {
                this.dx = -this.dx;
            }
            if (granadaDireita.intersects(p)) {
                this.dx = -this.dx;
            }
        }
    }
}
