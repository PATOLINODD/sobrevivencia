/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import sobrevivencia.Sobrevivencia;
import sobrevivencia.janela.Janela;

/**
 *
 * @author PATOLINODD
 */
public class Pause extends Menu {

    private final String[] opcoes = {"CONTINUAR", "voltar ao menu"};
    int currentOptions = 0;

    @Override
    public void acao() {
        Sobrevivencia.init.getMenu().setEnter(false);
        
        if (this.isUp()) {
            this.setUp(false);
            currentOptions--;
        }
        if (this.isDown()) {
            this.setDown(false);
            currentOptions++;
        }
        
        if(this.isEnter()){
            this.setEnter(false);
            switch(opcoes[currentOptions]){
                case "CONTINUAR":
                    Menu.setEstadoDoJogo("novo jogo");
                    break;
                case "voltar ao menu":
                    Menu.setEstadoDoJogo("menu");
                    break;
            }
        }
        
        if (currentOptions >= opcoes.length - 1) {
            currentOptions = opcoes.length - 1;
        }
        if (currentOptions < 0) {
            currentOptions = 0;
        }

    }

    @Override
    public void graficos(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, Janela.largura, Janela.altura);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.drawString("CONTINUAR", 520, 300);
        g2.drawString("voltar ao menu", 509, 350);

        this.gradienteCores(g2);
        switch (opcoes[currentOptions]) {
            case "CONTINUAR":
                g2.drawString(opcoes[currentOptions], 520, 300);
                break;
            case "voltar ao menu":
                g2.drawString(opcoes[currentOptions], 509, 350);
                break;
        }
    }

    public String[] getOpcoes() {
        return opcoes;
    }

}
