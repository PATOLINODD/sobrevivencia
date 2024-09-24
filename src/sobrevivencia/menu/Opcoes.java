package sobrevivencia.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import sobrevivencia.Sobrevivencia;

public class Opcoes {
   public static final String FACIL = "facil";
   public static final String NORMAL = "normal";
   public static final String DIFICIL = "dificil";
   public static final String EXPERT = "expert";
   private static final Map<String, String[]> opcoes = new HashMap();
   private static final String[] options = new String[]{"musica", "efeito sonoros", "dificuldade", "voltar"};
   private int currentOptions;
   private int currentValueMusica;
   private int currentValueEfeitoSonoros;
   private static int currentValueDificuldade;
   private boolean up;
   private boolean down;
   private boolean left;
   private boolean right;
   private boolean enter;

   public Opcoes() {
      String[] var1 = options;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         String str = var1[var3];
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
         ++this.currentOptions;
      } else if (this.isUp()) {
         this.setUp(false);
         --this.currentOptions;
      }

      if (this.currentOptions >= options.length - 1) {
         this.currentOptions = options.length - 1;
      }

      if (this.currentOptions <= 0) {
         this.currentOptions = 0;
      }

      String var1 = options[this.currentOptions];
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1110800967:
         if (var1.equals("efeito sonoros")) {
            var2 = 1;
         }
         break;
      case -1062807844:
         if (var1.equals("musica")) {
            var2 = 0;
         }
         break;
      case -810884622:
         if (var1.equals("voltar")) {
            var2 = 3;
         }
         break;
      case 1290116592:
         if (var1.equals("dificuldade")) {
            var2 = 2;
         }
      }

      switch(var2) {
      case 0:
         if (this.isLeft()) {
            this.setLeft(false);
            --this.currentValueMusica;
         } else if (this.isRight()) {
            this.setRight(false);
            ++this.currentValueMusica;
         }
         break;
      case 1:
         if (this.isLeft()) {
            this.setLeft(false);
            --this.currentValueEfeitoSonoros;
         } else if (this.isRight()) {
            this.setRight(false);
            ++this.currentValueEfeitoSonoros;
         }
         break;
      case 2:
         if (this.isLeft()) {
            this.setLeft(false);
            --currentValueDificuldade;
         } else if (this.isRight()) {
            this.setRight(false);
            ++currentValueDificuldade;
         }
         break;
      case 3:
         if (this.isEnter()) {
            Sobrevivencia.init.getMenu().setMenuOpcoes(false);
         }
         break;
      default:
         throw new AssertionError();
      }

      if (opcoes.get(options[this.currentOptions]) != null) {
         if (this.currentValueMusica > ((String[])opcoes.get("musica")).length - 1) {
            this.currentValueMusica = ((String[])opcoes.get("musica")).length - 1;
         } else if (this.currentValueMusica < 0) {
            this.currentValueMusica = 0;
         }

         if (this.currentValueEfeitoSonoros > ((String[])opcoes.get("efeito sonoros")).length - 1) {
            this.currentValueEfeitoSonoros = ((String[])opcoes.get("efeito sonoros")).length - 1;
         } else if (this.currentValueEfeitoSonoros < 0) {
            this.currentValueEfeitoSonoros = 0;
         }

         if (currentValueDificuldade >= ((String[])opcoes.get("dificuldade")).length - 1) {
            currentValueDificuldade = ((String[])opcoes.get("dificuldade")).length - 1;
         } else if (currentValueDificuldade <= 0) {
            currentValueDificuldade = 0;
         }
      }

   }

   public void graficos(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setColor(Color.white);
      g2.drawString("musica", 100, 100);
      g2.drawString(((String[])opcoes.get("musica"))[this.currentValueMusica], 400, 100);
      g2.drawString("efeito sonoros", 100, 200);
      g2.drawString(((String[])opcoes.get("efeito sonoros"))[this.currentValueEfeitoSonoros], 400, 200);
      g2.drawString("dificuldade", 100, 300);
      g2.drawString(((String[])opcoes.get("dificuldade"))[currentValueDificuldade], 400, 300);
      g2.drawString("voltar", 100, 400);
      String var3 = options[this.currentOptions];
      byte var4 = -1;
      switch(var3.hashCode()) {
      case -1110800967:
         if (var3.equals("efeito sonoros")) {
            var4 = 1;
         }
         break;
      case -1062807844:
         if (var3.equals("musica")) {
            var4 = 0;
         }
         break;
      case -810884622:
         if (var3.equals("voltar")) {
            var4 = 3;
         }
         break;
      case 1290116592:
         if (var3.equals("dificuldade")) {
            var4 = 2;
         }
      }

      switch(var4) {
      case 0:
         Sobrevivencia.init.getMenu().gradienteCores(g2);
         g2.drawString(options[this.currentOptions], 100, 100);
         g2.drawString(((String[])opcoes.get(options[this.currentOptions]))[this.currentValueMusica], 400, 100);
         break;
      case 1:
         Sobrevivencia.init.getMenu().gradienteCores(g2);
         g2.drawString(options[this.currentOptions], 100, 200);
         g2.drawString(((String[])opcoes.get(options[this.currentOptions]))[this.currentValueEfeitoSonoros], 400, 200);
         break;
      case 2:
         Sobrevivencia.init.getMenu().gradienteCores(g2);
         g2.drawString(options[this.currentOptions], 100, 300);
         g2.drawString(((String[])opcoes.get(options[this.currentOptions]))[currentValueDificuldade], 400, 300);
         break;
      case 3:
         Sobrevivencia.init.getMenu().gradienteCores(g2);
         g2.drawString("voltar", 100, 400);
         break;
      default:
         throw new AssertionError();
      }

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

   public boolean isLeft() {
      return this.left;
   }

   public void setLeft(boolean left) {
      this.left = left;
   }

   public boolean isRight() {
      return this.right;
   }

   public void setRight(boolean right) {
      this.right = right;
   }

   public boolean isEnter() {
      return this.enter;
   }

   public void setEnter(boolean enter) {
      this.enter = enter;
   }

   public boolean isMusicOn() {
      return ((String[])opcoes.get("musica"))[this.currentValueMusica].equals("on");
   }

   public boolean isEfeitoSonoroIsOn() {
      return ((String[])opcoes.get("efeito sonoros"))[this.currentValueEfeitoSonoros].equals("on");
   }

   public static String qualDificuldade() {
      return ((String[])opcoes.get("dificuldade"))[currentValueDificuldade];
   }
}
