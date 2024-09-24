package sobrevivencia.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import sobrevivencia.Init;
import sobrevivencia.Sobrevivencia;
import sobrevivencia.coletaveis.MedKit;
import sobrevivencia.coletaveis.Municao;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.explosoes.AbstractExplosao;
import sobrevivencia.janela.Interface;
import sobrevivencia.janela.Janela;
import sobrevivencia.janela.MiniMapa;
import sobrevivencia.jogador.Jogador;
import sobrevivencia.menu.Menu;
import sobrevivencia.projeteis.AbstractProjetil;
import sobrevivencia.raycasting.RayCasting;

public class Graficos {
   public RayCasting rayCasting;
   public MiniMapa miniMapa;
   public BufferedImage img;
   public static final int escala = 100;
   private Interface ui = new Interface();

   public Graficos() {
      this.img = new BufferedImage(Janela.largura, Janela.altura, 1);
      this.rayCasting = new RayCasting();
      this.miniMapa = new MiniMapa();
   }

   public void graficos(Sobrevivencia sob) {
      Graphics g = this.img.getGraphics();
      Graphics2D g2 = (Graphics2D)g;
      BufferStrategy bf = sob.getBufferStrategy();
      if (bf == null) {
         sob.createBufferStrategy(3);
      } else {
         g.setColor(Color.BLACK);
         g.fillRect(0, 0, Janela.largura, Janela.altura);
         g2.translate((double)Init.getCam().getX(), (double)Init.getCam().getY());
         Init.getMapa().graficos(g);
         Iterator var5 = Init.getMunicoes().iterator();

         while(var5.hasNext()) {
            Municao m = (Municao)var5.next();
            m.graficos(g);
         }

         var5 = Init.getVidas().iterator();

         while(var5.hasNext()) {
            MedKit v = (MedKit)var5.next();
            v.graficos(g);
         }

         var5 = Init.getExplosoes().iterator();

         while(var5.hasNext()) {
            AbstractExplosao ex = (AbstractExplosao)var5.next();
            ex.graficos(g);
         }

         Init.getArmas().graficos(g);
         Init.getExplosivos().graficos(g);
         Init.getSentinelas().graficos(g);
         Init.getSuportes().graficos(g);
         Init.getJogador().graficos(g);
         var5 = Init.getEntidades().iterator();

         while(var5.hasNext()) {
            AbstractEntidade inimigo = (AbstractEntidade)var5.next();
            inimigo.graficos(g);
         }

         var5 = Init.getProjeteis().iterator();

         while(var5.hasNext()) {
            AbstractProjetil p = (AbstractProjetil)var5.next();
            p.graficos(g);
         }

         g2.translate((double)(-Init.getCam().getX()), (double)(-Init.getCam().getY()));
         Sobrevivencia.init.getMira().graficos(g);
         this.ui.graficos(g);
         if (Jogador.miniMapa) {
            Sobrevivencia.g.miniMapa.graficos(g);
         }

         if (Menu.getEstadoDoJogo().equals("menu")) {
            Sobrevivencia.init.getMenu().graficos(g);
         }

         if (Menu.getEstadoDoJogo().equals("pause")) {
            Sobrevivencia.init.getPause().graficos(g);
         }

         g.dispose();
         g = bf.getDrawGraphics();
         g.drawImage(this.img, 0, 0, Janela.largura + 100, Janela.altura + 100, sob);
         bf.show();
      }
   }
}
