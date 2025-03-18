/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import sobrevivencia.Init;
import sobrevivencia.soudEffects.SoundEffects;
import sobrevivencia.janela.Janela;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.projeteis.DanoAtkArmas;
import sobrevivencia.projeteis.ProjetilIni;
import sobrevivencia.som.Som;
import sobrevivencia.spritesheet.SpriteSheet;

/**
 *
 * @author PATOLINODD
 */
public class Inimigo12 extends Inimigos {

    public Inimigo12(float x, float y, int largura, int altura) {
        super(x, y, largura, altura);
        spriteCaminhada = new SpriteSheet("/SoldadoBomba.png");
        spriteAtira = new SpriteSheet("/SoldadoBombaAtirando.png");
        iniciaBufferedImg();
        this.updateVida();
        this.fireRate = 80;
        this.cargaAtual = 6;
        this.carga = 6;
    }

    double angulo1, angulo2, angulo3;

    @Override
    public void acao() {
        this.updateLocale();
        this.initAngle();
        //testar com os pontos setando o angulo do math.round
        //int xi1 = (int) Math.round(-dx1 * fullLenght);
        //lembrar dos calculos q fiz das bolinhas pro boss olho gigante!
        
        angulo1 = this.iniciaAngulo(Init.getJogador().getY() + Init.getJogador().getOffset() / 2 - 16, this.getY(), Init.getJogador().getX() + 32, this.getX());
        angulo2 = this.iniciaAngulo(Init.getJogador().getY() + Init.getJogador().getOffset() / 2 - 8, this.getY(), Init.getJogador().getX() + 16, this.getX());
        angulo3 = this.iniciaAngulo(Init.getJogador().getY() + Init.getJogador().getOffset() / 2 + 8, this.getY(), Init.getJogador().getX(), this.getX());
        
        
        this.setRange(Janela.altura / 4);
//        this.setVelocidade(this.getRandom().nextDouble(2) + 1.5);
        this.segueOjogador();
        validaVida();
        iniciaAni();
        iniciaRectangle();
        this.setChanceDropBomba(3);
        this.updateFireRate();
//        this.updateVelocidade();
        this.setMask(3, 3, this.getLargura() - 6, this.getAltura() - 6);
        this.setChanceDropMedKit(200);
    }

    @Override
    void updateFireRate() {
        switch (Opcoes.qualDificuldade()) {
            case Opcoes.FACIL:
                this.setFire(80);
                break;
            default:
                this.setFire(50);
                break;
        }
    }

    @Override
    void atira() {
        double dx1 = Math.cos(angulo1);
        double dy1 = Math.sin(angulo1);
        double dx2 = Math.cos(angulo2);
        double dy2 = Math.sin(angulo2);
        double dx3 = Math.cos(angulo3);
        double dy3 = Math.sin(angulo3);

        int fullLenght = Math.round(18);
        int xi1 = (int) Math.round(dx1 * fullLenght);
        int yi1 = (int) Math.round(dy1 * fullLenght);
        int xi2 = (int) Math.round(dx2 * fullLenght);
        int yi2 = (int) Math.round(dy2 * fullLenght);
        int xi3 = (int) Math.round(dx3 * fullLenght);
        int yi3 = (int) Math.round(dy3 * fullLenght);

        int tamanho = 10;
        int tempoVida = 50;
        fireRate++;
        if (fireRate >= fire) {
            fireRate = 0;
            if (cargaAtual > 0) {
                estaAtirando = true;
                Init.addProjetil(new ProjetilIni(getX() + 8 + xi1, getY() + 8 + yi1, tamanho, dx1, dy1, DanoAtkArmas.getDanoM4(), tempoVida, Color.MAGENTA, new Som(SoundEffects.getSHOOTGUN())));
                Init.addProjetil(new ProjetilIni(getX() + 8 + xi2, getY() + 8 + yi2, tamanho, dx2, dy2, DanoAtkArmas.getDanoM4(), tempoVida, Color.MAGENTA, null));
                Init.addProjetil(new ProjetilIni(getX() + 8 + xi3, getY() + 8 + yi3, tamanho, dx3, dy3, DanoAtkArmas.getDanoM4(), tempoVida, Color.MAGENTA, null));
                this.diminuiCargaAtual();
            }
        }
        this.recarregar();
//        tempoAtirando++;
//        if (tempoAtirando >= 150) {
//            tempoAtirando = 0;
//            estaAtirando = false;
//        }
    }

    @Override
    public void graficos(Graphics g) {
        iniciaRayCasting(g);

        int tamanho = 96;
        setXx((int) getX());
        setYy((int) getY() - 32);

        Graphics2D g2 = (Graphics2D) g.create();
        this.mostraHitBox(g2);

        AffineTransform at = g2.getTransform();
        g2.rotate(this.getAngulo(), this.getXi(), this.getY() + 16);
        if (!estaAtirando) {
            g2.drawImage(this.caminhada[index], getXx(), getYy(), tamanho, tamanho, null);
        } else {
            g2.drawImage(this.atira[ind], getXx(), getYy(), tamanho, tamanho, null);
        }
        g2.dispose();
        g2.setTransform(at);
    }

}
