package sobrevivencia.coletaveis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.menu.Opcoes;

public class MedKit extends AbstractEntidade {
   private double acresVida;
   private int tempo;
   private int maxTempo;

   public MedKit(float x, float y, int largura, int altura) {
      super(x, y, largura, altura);
      String var5 = Opcoes.qualDificuldade();
      byte var6 = -1;
      switch(var5.hashCode()) {
      case -1039745817:
         if (var5.equals("normal")) {
            var6 = 1;
         }
         break;
      case 97187371:
         if (var5.equals("facil")) {
            var6 = 0;
         }
      }

      switch(var6) {
      case 0:
         this.acresVida = 400.0D;
         this.maxTempo = 500;
         break;
      case 1:
         this.acresVida = (double)((new Random()).nextInt(100) + 200);
         this.maxTempo = 500;
         break;
      default:
         this.acresVida = (double)((new Random()).nextInt(100) + 100);
         this.maxTempo = 300;
      }

   }

   public void graficos(Graphics g) {
      int tamanho = 32;
      g.setColor(Color.red);
      g.fillRect((int)this.getX(), (int)this.getY(), tamanho, tamanho);
      g.setColor(Color.white);
      g.fillRect((int)this.getX() + 2, (int)this.getY() + 2, tamanho - 4, tamanho - 4);
      g.setColor(Color.red);
      g.fillRect((int)this.getX() + 5, (int)this.getY() + 11, tamanho - 10, tamanho - 22);
      g.fillRect((int)this.getX() + 11, (int)this.getY() + 5, tamanho - 22, tamanho - 10);
   }

   public void acao() {
      ++this.tempo;
      if (this.tempo >= this.maxTempo) {
         this.tempo = 0;
         Init.removeMedKit(this);
      }

      if (Init.getJogador().getVida() < 1000.0D) {
         this.saude();
      }

   }

   private void saude() {
      Rectangle vida = new Rectangle((int)this.getX() + 3, (int)this.getY() + 3, 34, 34);
      Rectangle jogador = Init.getJogador().getRetangulo();
      if (jogador.intersects(vida)) {
         Init.removeMedKit(this);
         Init.getJogador().alteraVida(this.acresVida);
      }

   }
}
