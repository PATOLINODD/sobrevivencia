/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.projeteis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import sobrevivencia.Init;
import sobrevivencia.chefes.Helicoptero;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.explosoes.Explosao;
import sobrevivencia.janela.IconMira;
import sobrevivencia.jogador.Jogador;
import sobrevivencia.mapa.Mapa;
import sobrevivencia.mapa.ParedesLimite;
import sobrevivencia.som.Som;

/**
 *
 * @author PATOLINODD
 */
public abstract class AbstractProjetil {

    double dx, dy;

    double velocidade = 25, tempo = 0, tempoB = 0, danoAtk;

    int vida, tempoVida, tamanho;
    float x, y;
    public static boolean hit = false;

    Color cor;

    public AbstractProjetil(float x, float y, int tamanho, double dx, double dy, double danoAtk, int tempoVida, Color cor, Som som) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.tempoVida = tempoVida;
        this.cor = cor;
        this.tamanho = tamanho;
        this.danoAtk = danoAtk;
        if (som != null) {
            som.play();
        }
//        this.gerarExplosao(30, (int)x, (int)y, 2, 20, cor, null);
    }

    void tiles() {
    }

    int tempoMax = 15;

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void paraBaixo(float velocidade) {
        this.y += velocidade;
    }

    public void paraDireita(float velocidade) {
        this.x += velocidade;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void acao() {
        if (Jogador.estaMirandoHelicoptero) {
            this.hitHelicoptero();
        }
        vaiEmDir(dx, dy, velocidade);
        if (hitParede()) {
            Init.removeProjetil(this);
            this.gerarExplosao(50, (int) this.getX() + 5, (int) this.getY() + 5, 4, tempoMax, cor, null);
        }
        this.iniciaRastro();
        this.initRectangle();
        this.atkJogador();
        this.atkAosInimigos();
    }

    void initRectangle() {
        projetil = new Rectangle((int) getX(), (int) getY(), this.getTamanho(), this.getTamanho());
    }

    void vaiEmDir(double dx, double dy, double velocidade) {
        paraDireita((float) (dx * velocidade));
        paraBaixo((float) (dy * velocidade));
    }

    protected void iniciaRastro() {
        vida++;
        if (vida >= 3) {
            Init.addProjetil(new RastroProjetil(x, y, tamanho, 0, 0, 0, 10, cor));
        }
        if (vida >= tempoVida) {
            vida = 0;
            Init.removeProjetil(this);
            gerarExplosao(50, (int) this.getX() + 10, (int) this.getY() + 10, 2, 10, cor, null);
        }
    }

    public void graficos(Graphics g) {
        g.setColor(cor);
        g.fillOval((int) x, (int) y, this.tamanho, this.tamanho);
    }

    void gerarExplosao(int monte, int x, int y, int tamanho, int vida, Color cor, Som som) {
        if (som != null) {
            som.play();
        }
        for (int i = 0; i < monte; i++) {
            Init.addExplosoes(new Explosao(x, y, tamanho, tamanho, tempoMax, cor, 1.3));
        }

    }
    Rectangle projetil = new Rectangle((int) getX(), (int) getY(), this.getTamanho(), this.getTamanho());

    void atkJogador() {
        if (projetil.intersects(Init.getJogador().getRectangle())) {
            Jogador.emCombate = true;
            Jogador.recebendoAtk = true;
            Init.getJogador().vida -= this.getDanoAtk();
            Init.removeProjetil(this);
            //colocar som atk ao jogador
            this.gerarExplosao(50, (int) getX(), (int) getY(), 4, 15, Color.red, null);
        }
    }

    void atkAosInimigos() {
        for (AbstractEntidade entidade : Init.getEntidades()) {
            if (entidade instanceof Inimigos) {
                Inimigos inimigo = (Inimigos) entidade;
                if (projetil.intersects(inimigo.getHitBox())) {
                    if (!Jogador.estaMirandoHelicoptero) {
                        IconMira.feedback = true;
                        Init.removeProjetil(this);
                        this.gerarExplosao(50, (int) getX(), (int) getY(), 4, 15, Color.red, null);
                        inimigo.setHit(true);
                    }
                }
            }
        }
    }

    protected boolean hitParede() {
        int projX = (int)(this.getX() / 32), projY = (int)(this.getY() / 32);
        int[] pixels = new int[Mapa.getMapa().getWidth() * Mapa.getMapa().getHeight()];
        
        Mapa.getMapa().getRGB(0, 0, Mapa.getMapa().getWidth(), Mapa.getMapa().getHeight(), pixels, 0, Mapa.getMapa().getWidth());
        
        switch(pixels[projX + (projY * Mapa.getMapa().getWidth())]){
            case 0xFFFFFFFF:
                return true;
            case 0xff4CFF00:
                return true;
        }
        return false;
    }

    void hitHelicoptero() {
        for (Helicoptero helic : Init.getHelicopteros()) {
            if (helic != null) {
                if (helic.shape.intersects(projetil)) {
                    helic.vidaH -= this.getDanoAtk();
                    Init.removeProjetil(this);
                    this.gerarExplosao(50, (int) this.getX() + 10, (int) this.getY() + 10, 4, tempoMax, Color.DARK_GRAY.brighter(), null);
                    return;
                }
            }
        }
    }

    public double getDanoAtk() {
        return danoAtk;
    }
}
