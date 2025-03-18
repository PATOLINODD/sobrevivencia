/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;
import sobrevivencia.Init;
import sobrevivencia.acao.PontosSpawn;
import sobrevivencia.janela.Janela;
import sobrevivencia.jogador.Jogador;

/**
 *
 * @author PATOLINODD
 */
public class Menu {

    public Opcoes opcao;
    final Logger log = Logger.getLogger(Menu.class.getName());
    private static String estadoDoJogo = "menu";
    private final String[] opcoes = {"novo jogo", "opcoes", "modo treino", "sair do jogo"};
    private boolean up, down, enter, menuOpcoes;
    private int currentOptions = 0;

    public Menu() {
        opcao = new Opcoes();
    }

    public void acao() {
        if (!menuOpcoes) {
            opcao.setEnter(false);
            if (this.isDown()) {
                this.setDown(false);
                currentOptions += 1;
            } else if (this.isUp()) {
                this.setUp(false);
                currentOptions -= 1;
            }

            if (this.enter) {
                switch (opcoes[currentOptions]) {
                    case "novo jogo":
                        Menu.estadoDoJogo = "novo jogo";
                        PontosSpawn.modoTreino = false;
                        Jogador.novoJogo = true;
                        Init.novoJogo();
                        break;
                    case "opcoes":
                        menuOpcoes = true;
                        break;
                    case "modo treino":
                        Menu.estadoDoJogo = "novo jogo";
                        Jogador.novoJogo = true;
                        Init.novoJogo();
                        PontosSpawn.modoTreino = true;
                        break;
                    case "sair do jogo":
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
            if (currentOptions >= opcoes.length - 1) {
                currentOptions = opcoes.length - 1;
            }
            if (currentOptions < 0) {
                currentOptions = 0;
            }
        } else {
            this.setEnter(false);
            currentOptions = 0;
            opcao.acao();
        }
    }

    public void graficos(Graphics g) {
        //plano de fundo
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Janela.largura, Janela.altura);

        if (!menuOpcoes) {
            Graphics2D g2 = (Graphics2D) g;
            for (int i = 1; i <= opcoes.length; i++) {
                g2.setColor(Color.white);
                g2.drawString(opcoes[i - 1], 100, i * Janela.altura / opcoes.length - 120);
                if (i - 1 == currentOptions) {
                    this.gradienteCores(g2);
                    g2.drawString(opcoes[currentOptions], 100, i * Janela.altura / opcoes.length - 120);
                }
            }
        } else {
            opcao.graficos(g);
        }
    }

    public static String getEstadoDoJogo() {
        return estadoDoJogo;
    }

    public static void setEstadoDoJogo(String estadoDoJogo) {
        Menu.estadoDoJogo = estadoDoJogo;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isEnter() {
        return enter;
    }

    public void setEnter(boolean enter) {
        this.enter = enter;
    }

    public boolean isMenuOpcoes() {
        return menuOpcoes;
    }

    public void setMenuOpcoes(boolean menuOpcoes) {
        this.menuOpcoes = menuOpcoes;
    }

    int red = 255, green = 255, blue;
    boolean gradiente = true, Bgreen, Bblue;

    public void gradienteCores(Graphics2D g2) {
        g2.setColor(new Color(red, green, blue));
        if (gradiente) {
            red -= 2;
            green += 3;
            if (red <= 0) {
                red = 0;
                gradiente = false;
            }
        }
        if (Bgreen) {
            green -= 2;
            blue += 3;
            if (green <= 0) {
                green = 0;
            }
        }
        if (Bblue) {
            blue -= 2;
            red += 3;
            if (blue <= 0) {
                blue = 0;
            }
        }

        if (red >= 255) {
            red = 255;
            Bblue = false;
            gradiente = true;
        }
        if (green >= 255) {
            if (red == 0) {
                Bgreen = true;
            }
            green = 255;
        }
        if (blue >= 255) {
            blue = 255;
            Bgreen = false;
            Bblue = true;
        }
    }
}
