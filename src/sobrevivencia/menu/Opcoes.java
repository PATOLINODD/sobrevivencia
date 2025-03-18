/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import sobrevivencia.Sobrevivencia;

/**
 *
 * @author PATOLINODD
 */
public class Opcoes {

    public static final String FACIL = "facil"; 
    public static final String NORMAL = "normal"; 
    public static final String DIFICIL = "dificil"; 
    public static final String EXPERT = "expert";
    
    private static final Map<String, String[]> opcoes = new HashMap<>();
    private static final String[] options = {"musica", "efeito sonoros", "dificuldade", "voltar"};
    private int currentOptions, currentValueMusica, currentValueEfeitoSonoros;
    private static int currentValueDificuldade;

    private boolean up, down, left, right, enter;

    public Opcoes() {
        for (String str : options) {
            if (!str.equals("dificuldade")) {
                opcoes.put(str, new String[]{"on", "off"});
            } else {
                opcoes.put(str, new String[]{"facil", "normal", "dificil", "expert"});
            }
        }
    }

    public void acao() {
        if (this.isDown()) {
            this.setDown(false);
            currentOptions += 1;
        } else if (this.isUp()) {
            this.setUp(false);
            currentOptions -= 1;
        }
        if (currentOptions >= options.length - 1) {
            currentOptions = options.length - 1;
        }
        if (currentOptions <= 0) {
            currentOptions = 0;
        }
        switch (options[currentOptions]) {
            case "musica":
                if (this.isLeft()) {
                    this.setLeft(false);
                    currentValueMusica--;
                } else if (this.isRight()) {
                    this.setRight(false);
                    currentValueMusica++;
                }
                break;
            case "efeito sonoros":
                if (this.isLeft()) {
                    this.setLeft(false);
                    currentValueEfeitoSonoros--;
                } else if (this.isRight()) {
                    this.setRight(false);
                    currentValueEfeitoSonoros++;
                }
                break;
            case "dificuldade":
                if (this.isLeft()) {
                    this.setLeft(false);
                    currentValueDificuldade--;
                } else if (this.isRight()) {
                    this.setRight(false);
                    currentValueDificuldade++;
                }
                break;
            case "voltar":
                if (this.isEnter()) {
                    Sobrevivencia.init.getMenu().setMenuOpcoes(false);
                }
                break;
            default:
                throw new AssertionError();
        }
        if (opcoes.get(options[currentOptions]) != null) {

            if (currentValueMusica > opcoes.get("musica").length - 1) {
                currentValueMusica = opcoes.get("musica").length - 1;
            } else if (currentValueMusica < 0) {
                currentValueMusica = 0;
            }
            if (currentValueEfeitoSonoros > opcoes.get("efeito sonoros").length - 1) {
                currentValueEfeitoSonoros = opcoes.get("efeito sonoros").length - 1;
            } else if (currentValueEfeitoSonoros < 0) {
                currentValueEfeitoSonoros = 0;
            }

            if (currentValueDificuldade >= opcoes.get("dificuldade").length - 1) {
                currentValueDificuldade = opcoes.get("dificuldade").length - 1;
            } else if (currentValueDificuldade <= 0) {
                currentValueDificuldade = 0;
            }
        }

    }

    public void graficos(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.drawString("musica", 100, 100);
        g2.drawString(opcoes.get("musica")[currentValueMusica], 400, 100);
        g2.drawString("efeito sonoros", 100, 200);
        g2.drawString(opcoes.get("efeito sonoros")[currentValueEfeitoSonoros], 400, 200);
        g2.drawString("dificuldade", 100, 300);
        g2.drawString(opcoes.get("dificuldade")[currentValueDificuldade], 400, 300);
        g2.drawString("voltar", 100, 400);

        switch (options[currentOptions]) {
            case "musica":
                Sobrevivencia.init.getMenu().gradienteCores(g2);
                g2.drawString(options[currentOptions], 100, 100);
                g2.drawString(opcoes.get(options[currentOptions])[currentValueMusica], 400, 100);
                break;
            case "efeito sonoros":
                Sobrevivencia.init.getMenu().gradienteCores(g2);
                g2.drawString(options[currentOptions], 100, 200);
                g2.drawString(opcoes.get(options[currentOptions])[currentValueEfeitoSonoros], 400, 200);
                break;
            case "dificuldade":
                Sobrevivencia.init.getMenu().gradienteCores(g2);
                g2.drawString(options[currentOptions], 100, 300);
                g2.drawString(opcoes.get(options[currentOptions])[currentValueDificuldade], 400, 300);
                break;
            case "voltar":
                Sobrevivencia.init.getMenu().gradienteCores(g2);
                g2.drawString("voltar", 100, 400);
                break;
            default:
                throw new AssertionError();
        }
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

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isEnter() {
        return enter;
    }

    public void setEnter(boolean enter) {
        this.enter = enter;
    }

    public boolean isMusicOn() {
        return opcoes.get("musica")[currentValueMusica].equals("on");
    }

    public boolean isEfeitoSonoroIsOn() {
        return opcoes.get("efeito sonoros")[currentValueEfeitoSonoros].equals("on");
    }

    public static String qualDificuldade() {
        return opcoes.get("dificuldade")[currentValueDificuldade];
    }
}
