/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.entidades;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.spritesheet.SpriteSheet;

/**
 *
 * @author PATOLINODD
 */
public class Inimigos02 extends Inimigos {

    public Inimigos02(float x, float y, int largura, int altura) {
        super(x, y, largura, altura);
        spriteCaminhada = new SpriteSheet("/SoldadoMedico.png");
        spriteAtira = new SpriteSheet("/MedicoAtirando.png");
        iniciaBufferedImg();
        this.updateVida();
    }

    @Override
    public void acao() {
        this.updateLocale();
        this.initAngle();
        this.segueOjogador();
        validaVida();
        iniciaAni();
        iniciaRectangle();
        this.updateFireRate();
        this.setChanceDropMedKit();
        this.setMask(3, 3, this.getLargura() - 6, this.getAltura() - 6);
    }
    
    void setChanceDropMedKit(){
        switch (Opcoes.qualDificuldade()) {
            case Opcoes.DIFICIL:
                this.setChanceDropMedKit(50);
                break;
            case Opcoes.EXPERT:
                this.setChanceDropMedKit(70);
                break;
            default:
                this.setChanceDropMedKit(0);
                break;
        }
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
