package sobrevivencia.janela;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import sobrevivencia.Init;
import sobrevivencia.Sobrevivencia;
import sobrevivencia.acao.PontosSpawn;

public class Interface {
   public static double delay = 1000.0D;
   private double cont = 0.0D;

   public void graficos(Graphics g) {
      ++this.cont;
      if (this.cont >= 10.0D) {
         this.cont = 0.0D;
         delay -= 4.0D;
         if (delay <= Init.getJogador().getVida()) {
            delay = Init.getJogador().getVida();
         }
      }

      if (delay >= 1000.0D) {
         delay = 1000.0D;
      }

      int altura = 20;
      int tamanho = 300;
      g.setColor(Color.red);
      g.fillRect(10, 20, tamanho, altura);
      g.setColor(Color.white);
      g.fillRect(10, 20, (int)(delay / 1000.0D * (double)tamanho), altura);
      g.setColor(Color.green);
      g.fillRect(10, 20, (int)(Init.getJogador().getVida() / 1000.0D * (double)tamanho), altura);
      Graphics2D g2 = (Graphics2D)g;
      g2.setColor(Color.white);
      g2.setFont(new Font("TimeRoman", 0, 32));
      g2.drawString(String.valueOf(PontosSpawn.getRecord()), Janela.largura / 2 - 32, 64);
      g2.drawString(String.valueOf(PontosSpawn.getOnda()), Janela.largura / 2 - 32, 96);
      g2.drawString(String.valueOf(Init.getEntidades().size()), Janela.largura - 100 - 80, 32);
      g2.drawString(String.valueOf(Init.getJogador().getCargaAtual()), Janela.largura - 100 - 200, Janela.altura - 100 - 64);
      g2.drawString(String.valueOf(Init.getJogador().getMunicao()), Janela.largura - 100 - 160, Janela.altura - 100 - 64);
      g2.drawString(String.valueOf(Sobrevivencia.framess), Janela.largura - 100 - 80, 80);
   }
}
