package sobrevivencia.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import sobrevivencia.Sobrevivencia;
import sobrevivencia.janela.Janela;

public class Pause extends Menu {
   private final String[] opcoes = new String[]{"CONTINUAR", "voltar ao menu"};
   int currentOptions = 0;

   public void acao() {
      Sobrevivencia.init.getMenu().setEnter(false);
      if (this.isUp()) {
         this.setUp(false);
         --this.currentOptions;
      }

      if (this.isDown()) {
         this.setDown(false);
         ++this.currentOptions;
      }

      if (this.isEnter()) {
         this.setEnter(false);
         String var1 = this.opcoes[this.currentOptions];
         byte var2 = -1;
         switch(var1.hashCode()) {
         case -1911785457:
            if (var1.equals("CONTINUAR")) {
               var2 = 0;
            }
            break;
         case -1699950109:
            if (var1.equals("voltar ao menu")) {
               var2 = 1;
            }
         }

         switch(var2) {
         case 0:
            Menu.setEstadoDoJogo("novo jogo");
            break;
         case 1:
            Menu.setEstadoDoJogo("menu");
         }
      }

      if (this.currentOptions >= this.opcoes.length - 1) {
         this.currentOptions = this.opcoes.length - 1;
      }

      if (this.currentOptions < 0) {
         this.currentOptions = 0;
      }

   }

   public void graficos(Graphics g) {
      g.setColor(new Color(0, 0, 0, 150));
      g.fillRect(0, 0, Janela.largura, Janela.altura);
      Graphics2D g2 = (Graphics2D)g;
      g2.setColor(Color.YELLOW);
      g2.drawString("CONTINUAR", 520, 300);
      g2.drawString("voltar ao menu", 509, 350);
      this.gradienteCores(g2);
      String var3 = this.opcoes[this.currentOptions];
      byte var4 = -1;
      switch(var3.hashCode()) {
      case -1911785457:
         if (var3.equals("CONTINUAR")) {
            var4 = 0;
         }
         break;
      case -1699950109:
         if (var3.equals("voltar ao menu")) {
            var4 = 1;
         }
      }

      switch(var4) {
      case 0:
         g2.drawString(this.opcoes[this.currentOptions], 520, 300);
         break;
      case 1:
         g2.drawString(this.opcoes[this.currentOptions], 509, 350);
      }

   }

   public String[] getOpcoes() {
      return this.opcoes;
   }
}
