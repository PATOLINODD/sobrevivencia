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
import sobrevivencia.graficos.Graficos;
import sobrevivencia.janela.Janela;
import sobrevivencia.jogador.Jogador;
import sobrevivencia.menu.Menu;
import sobrevivencia.mouse.MouseXY;

public class Sobrevivencia extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {
   Logger log = Logger.getLogger(Sobrevivencia.class.getName());
   private boolean estaJogando = false;
   public static Graficos g;
   public static Init init = new Init();
   public Acao acao;
   public Janela janela;
   public static int frames = 0;
   public static int framess;
   String tipoInimigo = "inimigos";

   public Sobrevivencia() {
      this.log.log(Level.INFO, "Iniciando objetos!");
      this.addKeyListener(this);
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      g = new Graficos();
      this.acao = new Acao();
      this.janela = new Janela();
      this.janela.janela(this);
   }

   public static void main(String[] args) {
      Sobrevivencia sobrevivencia = new Sobrevivencia();
      sobrevivencia.start();
   }

   public synchronized void start() {
      Thread trd = new Thread(this);
      this.estaJogando = true;
      trd.start();
   }

   public void run() {
      this.requestFocus(true);
      long nanoTime = System.nanoTime();
      double trilhao = 1.6666666666666666E7D;
      double delta = 0.0D;
      double timer = (double)System.currentTimeMillis();
      frames = 0;

      while(this.estaJogando) {
         long agora = System.nanoTime();
         delta += (double)(agora - nanoTime) / trilhao;
         nanoTime = agora;
         if (delta >= 1.0D) {
            this.acao.acao();
            g.graficos(this);
            --delta;
            ++frames;
            if ((double)System.currentTimeMillis() - timer >= 1000.0D) {
               framess = frames;
               frames = 0;
               timer += 1000.0D;
            }
         }
      }

   }

   public void keyTyped(KeyEvent e) {
   }

   public void keyPressed(KeyEvent e) {
      switch(e.getKeyCode()) {
      case 49:
         this.tipoInimigo = "inimigos";
         break;
      case 50:
         this.tipoInimigo = "homem bomba";
         break;
      case 51:
         this.tipoInimigo = "medico";
      }

      if (e.getKeyCode() == 68) {
         Jogador.direita = true;
      }

      if (e.getKeyCode() == 65) {
         Jogador.esquerda = true;
      }

      if (e.getKeyCode() == 87) {
         Jogador.cima = true;
      }

      if (e.getKeyCode() == 83) {
         Jogador.baixo = true;
      }

      if (e.getKeyCode() == 82) {
         Jogador.recarregar = true;
      }

      if (e.getKeyCode() == 81) {
         Jogador.miniMapa = true;
      }

      if (e.getKeyCode() == 10) {
         if (!init.getMenu().isMenuOpcoes()) {
            init.getMenu().setEnter(true);
         }

         init.getPause().setEnter(true);
         init.getMenu().opcao.setEnter(true);
      }

      if (e.getKeyCode() == 40) {
         init.getMenu().setDown(true);
         init.getPause().setDown(true);
         init.getMenu().opcao.setDown(true);
      }

      if (e.getKeyCode() == 38) {
         init.getMenu().setUp(true);
         init.getPause().setUp(true);
         init.getMenu().opcao.setUp(true);
      }

      if (e.getKeyCode() == 37) {
         init.getMenu().opcao.setLeft(true);
      }

      if (e.getKeyCode() == 39) {
         init.getMenu().opcao.setRight(true);
      }

      if (e.getKeyCode() == 27) {
         Menu.setEstadoDoJogo("pause");
      }

      if (e.getKeyCode() == 70) {
         Init.getArmas().setComprar(true);
         Init.getExplosivos().setComprar(true);
         Init.getSentinelas().setComprar(true);
         Init.getSuportes().setComprar(true);
      }

   }

   public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == 68) {
         Jogador.direita = false;
      }

      if (e.getKeyCode() == 65) {
         Jogador.esquerda = false;
      }

      if (e.getKeyCode() == 87) {
         Jogador.cima = false;
      }

      if (e.getKeyCode() == 83) {
         Jogador.baixo = false;
      }

      if (e.getKeyCode() == 82 && Init.getJogador().getCargaAtual() == 30) {
         Jogador.recarregar = false;
      }

      if (e.getKeyCode() == 81) {
         Jogador.miniMapa = false;
      }

      if (e.getKeyCode() == 40) {
         init.getMenu().setDown(false);
         init.getPause().setDown(false);
         init.getMenu().opcao.setDown(false);
      }

      if (e.getKeyCode() == 38) {
         init.getMenu().setUp(false);
         init.getPause().setUp(false);
         init.getMenu().opcao.setUp(false);
      }

      if (e.getKeyCode() == 37) {
         init.getMenu().opcao.setLeft(false);
      }

      if (e.getKeyCode() == 39) {
         init.getMenu().opcao.setRight(false);
      }

      if (e.getKeyCode() == 10) {
         init.getMenu().setEnter(false);
         init.getPause().setEnter(false);
         init.getMenu().opcao.setEnter(false);
      }

   }

   public void mouseClicked(MouseEvent e) {
   }

   public void mousePressed(MouseEvent e) {
      MouseXY.setX(e.getX());
      MouseXY.setY(e.getY());
      if (e.getButton() == 1 && !Jogador.recarregar) {
         Jogador.mouseAtira = true;
      }

      if (e.getButton() == 3 && PontosSpawn.modoTreino) {
         String var2 = this.tipoInimigo;
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -1078031031:
            if (var2.equals("medico")) {
               var3 = 2;
            }
            break;
         case 262603787:
            if (var2.equals("inimigos")) {
               var3 = 0;
            }
            break;
         case 1207306957:
            if (var2.equals("homem bomba")) {
               var3 = 1;
            }
         }

         switch(var3) {
         case 0:
            Init.addIni(new Inimigos((float)MouseXY.getX() - Init.getCam().getX(), (float)MouseXY.getY() - Init.getCam().getY(), 31, 31));
            break;
         case 1:
            Init.addIni(new Inimigo12((float)MouseXY.getX() - Init.getCam().getX(), (float)MouseXY.getY() - Init.getCam().getY(), 31, 31));
            break;
         case 2:
            Init.addIni(new Inimigos02((float)MouseXY.getX() - Init.getCam().getX(), (float)MouseXY.getY() - Init.getCam().getY(), 31, 31));
            break;
         default:
            throw new AssertionError();
         }
      }

   }

   public void mouseReleased(MouseEvent e) {
      if (e.getButton() == 1) {
         Jogador.mouseAtira = false;
      }

   }

   public void mouseEntered(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
   }

   public void mouseDragged(MouseEvent e) {
      MouseXY.setX(e.getX());
      MouseXY.setY(e.getY());
   }

   public void mouseMoved(MouseEvent e) {
      MouseXY.setX(e.getX());
      MouseXY.setY(e.getY());
   }
}
