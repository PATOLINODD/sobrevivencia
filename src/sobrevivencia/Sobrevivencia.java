/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import sobrevivencia.acao.Acao;
import sobrevivencia.acao.PontosSpawn;
import sobrevivencia.entidades.Inimigo12;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.entidades.Inimigos02;
import sobrevivencia.jogador.Jogador;
import sobrevivencia.graficos.Graficos;
import sobrevivencia.janela.Janela;
import sobrevivencia.menu.Menu;
import sobrevivencia.mouse.MouseXY;

/**
 *
 * @author Administrator
 */
public class Sobrevivencia extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    Logger log = Logger.getLogger(Sobrevivencia.class.getName());

    private boolean estaJogando = false;

    public static Graficos g;
    public static Init init = new Init();
    public Acao acao;
    public Janela janela;

    public static int frames = 0, framess;

    public Sobrevivencia() {
        log.log(Level.INFO, "Iniciando objetos!");
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        g = new Graficos();
        acao = new Acao();
        janela = new Janela();
        janela.janela(this);
    }

    public static void main(String[] args) {
        Sobrevivencia sobrevivencia = new Sobrevivencia();
        sobrevivencia.start();
    }

    public synchronized void start() {
        Thread trd = new Thread(this);
        estaJogando = true;
        trd.start();
    }

    @Override
    public void run() {
        this.requestFocus(true);
        long nanoTime = System.nanoTime();
        double trilhao = 1000000000 / 60.0;
        double delta = 0, timer = System.currentTimeMillis();
        frames = 0;
        while (estaJogando) {
            long agora = System.nanoTime();
            delta += (agora - nanoTime) / trilhao;
            nanoTime = agora;

            if (delta >= 1) {
                acao.acao();
                g.graficos(this);
                delta--;
                frames++;

                if (System.currentTimeMillis() - timer >= 1000) {
//                    System.out.println(frames);
                    framess = frames;
                    frames = 0;
                    timer += 1000;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    String tipoInimigo = "inimigos";

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
                tipoInimigo = "inimigos";
                break;
            case KeyEvent.VK_2:
                tipoInimigo = "homem bomba";
                break;
            case KeyEvent.VK_3:
                tipoInimigo = "medico";
                break;
            case KeyEvent.VK_B:
                Jogador.lancaGranada = true;
                break;
            default:
                break;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            Jogador.direita = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            Jogador.esquerda = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Jogador.cima = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            Jogador.baixo = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            Jogador.recarregar = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            Jogador.miniMapa = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!init.getMenu().isMenuOpcoes()) {
                init.getMenu().setEnter(true);
            }
            init.getPause().setEnter(true);
            init.getMenu().opcao.setEnter(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            init.getMenu().setDown(true);
            init.getPause().setDown(true);
            init.getMenu().opcao.setDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            init.getMenu().setUp(true);
            init.getPause().setUp(true);
            init.getMenu().opcao.setUp(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            init.getMenu().opcao.setLeft(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            init.getMenu().opcao.setRight(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Menu.setEstadoDoJogo("pause");
        }

        if (e.getKeyCode() == KeyEvent.VK_F) {
            Init.getArmas().setComprar(true);
            Init.getExplosivos().setComprar(true);
            Init.getSentinelas().setComprar(true);
            Init.getSuportes().setComprar(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            Jogador.direita = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            Jogador.esquerda = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Jogador.cima = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            Jogador.baixo = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (Init.getJogador().getCargaAtual() == 30) {
                Jogador.recarregar = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            Jogador.miniMapa = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            init.getMenu().setDown(false);
            init.getPause().setDown(false);
            init.getMenu().opcao.setDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            init.getMenu().setUp(false);
            init.getPause().setUp(false);
            init.getMenu().opcao.setUp(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            init.getMenu().opcao.setLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            init.getMenu().opcao.setRight(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            init.getMenu().setEnter(false);
            init.getPause().setEnter(false);
            init.getMenu().opcao.setEnter(false);
        }

    }

    //problema desse metodo é que ele não clicka em movimento.
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        MouseXY.setX(e.getX());
        MouseXY.setY(e.getY());
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!Jogador.recarregar) {
                Jogador.mouseAtira = true;
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (PontosSpawn.modoTreino) {
                switch (tipoInimigo) {
                    case "inimigos":
                        Init.addIni(new Inimigos(MouseXY.getX() - Init.getCam().getX(), MouseXY.getY() - Init.getCam().getY(), 31, 31));
                        break;
                    case "homem bomba":
                        Init.addIni(new Inimigo12(MouseXY.getX() - Init.getCam().getX(), MouseXY.getY() - Init.getCam().getY(), 31, 31));
                        break;
                    case "medico":
                        Init.addIni(new Inimigos02(MouseXY.getX() - Init.getCam().getX(), MouseXY.getY() - Init.getCam().getY(), 31, 31));
                        break;
                    default:
                        throw new AssertionError();
                }
            }
//            if(Jogador.miraHelic == 0){
//                Jogador.estaMirandoHelicoptero = true;
//                Jogador.miraHelic = 1;
//            } else {
//                Jogador.estaMirandoHelicoptero = false;
//                Jogador.miraHelic = 0;
//            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {
            Jogador.mouseAtira = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MouseXY.setX(e.getX());
        MouseXY.setY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseXY.setX(e.getX());
        MouseXY.setY(e.getY());
    }

}
