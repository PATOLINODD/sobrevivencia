package sobrevivencia.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;
import sobrevivencia.Init;
import sobrevivencia.acao.PontosSpawn;
import sobrevivencia.janela.Janela;
import sobrevivencia.jogador.Jogador;

public class Menu {
   public Opcoes opcao = new Opcoes();
   final Logger log = Logger.getLogger(Menu.class.getName());
   private static String estadoDoJogo = "menu";
   private final String[] opcoes = new String[]{"novo jogo", "opcoes", "modo treino", "sair do jogo"};
   private boolean up;
   private boolean down;
   private boolean enter;
   private boolean menuOpcoes;
   private int currentOptions = 0;
   int red = 255;
   int green = 255;
   int blue;
   boolean gradiente = true;
   boolean Bgreen;
   boolean Bblue;

   public void acao() {
      if (!this.menuOpcoes) {
         this.opcao.setEnter(false);
         if (this.isDown()) {
            this.setDown(false);
            ++this.currentOptions;
         } else if (this.isUp()) {
            this.setUp(false);
            --this.currentOptions;
         }

         if (this.enter) {
            String var1 = this.opcoes[this.currentOptions];
            byte var2 = -1;
            switch(var1.hashCode()) {
            case -1346010442:
               if (var1.equals("modo treino")) {
                  var2 = 2;
               }
               break;
            case -1010637957:
               if (var1.equals("opcoes")) {
                  var2 = 1;
               }
               break;
            case -511168237:
               if (var1.equals("novo jogo")) {
                  var2 = 0;
               }
               break;
            case 2105627705:
               if (var1.equals("sair do jogo")) {
                  var2 = 3;
               }
            }

            switch(var2) {
            case 0:
               estadoDoJogo = "novo jogo";
               PontosSpawn.modoTreino = false;
               Jogador.novoJogo = true;
               Init.novoJogo();
               break;
            case 1:
               this.menuOpcoes = true;
               break;
            case 2:
               estadoDoJogo = "novo jogo";
               Jogador.novoJogo = true;
               Init.novoJogo();
               PontosSpawn.modoTreino = true;
               break;
            case 3:
               System.exit(0);
            }
         }

         if (this.currentOptions >= this.opcoes.length - 1) {
            this.currentOptions = this.opcoes.length - 1;
         }

         if (this.currentOptions < 0) {
            this.currentOptions = 0;
         }
      } else {
         this.setEnter(false);
         this.currentOptions = 0;
         this.opcao.acao();
      }

   }

   public void graficos(Graphics g) {
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, Janela.largura, Janela.altura);
      if (!this.menuOpcoes) {
         Graphics2D g2 = (Graphics2D)g;

         for(int i = 1; i <= this.opcoes.length; ++i) {
            g2.setColor(Color.white);
            g2.drawString(this.opcoes[i - 1], 100, i * Janela.altura / this.opcoes.length - 120);
            if (i - 1 == this.currentOptions) {
               this.gradienteCores(g2);
               g2.drawString(this.opcoes[this.currentOptions], 100, i * Janela.altura / this.opcoes.length - 120);
            }
         }
      } else {
         this.opcao.graficos(g);
      }

   }

   public static String getEstadoDoJogo() {
      return estadoDoJogo;
   }

   public static void setEstadoDoJogo(String estadoDoJogo) {
      Menu.estadoDoJogo = estadoDoJogo;
   }

   public boolean isUp() {
      return this.up;
   }

   public void setUp(boolean up) {
      this.up = up;
   }

   public boolean isDown() {
      return this.down;
   }

   public void setDown(boolean down) {
      this.down = down;
   }

   public boolean isEnter() {
      return this.enter;
   }

   public void setEnter(boolean enter) {
      this.enter = enter;
   }

   public boolean isMenuOpcoes() {
      return this.menuOpcoes;
   }

   public void setMenuOpcoes(boolean menuOpcoes) {
      this.menuOpcoes = menuOpcoes;
   }

   public void gradienteCores(Graphics2D g2) {
      g2.setColor(new Color(this.red, this.green, this.blue));
      if (this.gradiente) {
         this.red -= 2;
         this.green += 3;
         if (this.red <= 0) {
            this.red = 0;
            this.gradiente = false;
         }
      }

      if (this.Bgreen) {
         this.green -= 2;
         this.blue += 3;
         if (this.green <= 0) {
            this.green = 0;
         }
      }

      if (this.Bblue) {
         this.blue -= 2;
         this.red += 3;
         if (this.blue <= 0) {
            this.blue = 0;
         }
      }

      if (this.red >= 255) {
         this.red = 255;
         this.Bblue = false;
         this.gradiente = true;
      }

      if (this.green >= 255) {
         if (this.red == 0) {
            this.Bgreen = true;
         }

         this.green = 255;
      }

      if (this.blue >= 255) {
         this.blue = 255;
         this.Bgreen = false;
         this.Bblue = true;
      }

   }
}
