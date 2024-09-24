package sobrevivencia.acao;

import java.util.Iterator;
import sobrevivencia.Init;
import sobrevivencia.Sobrevivencia;
import sobrevivencia.chefes.Helicoptero;
import sobrevivencia.coletaveis.MedKit;
import sobrevivencia.coletaveis.Municao;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.explosoes.AbstractExplosao;
import sobrevivencia.menu.Menu;
import sobrevivencia.projeteis.AbstractProjetil;
import sobrevivencia.projeteis.Granada;
import sobrevivencia.projeteis.ProjeteisM4;
import sobrevivencia.projeteis.ProjetilIni;
import sobrevivencia.projeteis.RastroProjetil;
import sobrevivencia.som.Som;
import sobrevivencia.soudEffects.SoundEffects;
import sobrevivencia.teste.Teste;

public class Acao {
   Teste teste = new Teste();
   public Som rockMetal = new Som(SoundEffects.getROCK_METAL());
   public static String explodiu = "";
   int tempo = 0;
   boolean tocarMusica;

   public void acao() {
      if (Menu.getEstadoDoJogo().equals("novo jogo") && !Menu.getEstadoDoJogo().equals("pause")) {
         Init.getJogador().acao();
         Iterator var1 = Init.getEntidades().iterator();

         while(var1.hasNext()) {
            AbstractEntidade inimigo = (AbstractEntidade)var1.next();
            if (inimigo != null) {
               inimigo.acao();
            }
         }

         var1 = Init.getProjeteis().iterator();

         while(var1.hasNext()) {
            AbstractProjetil p = (AbstractProjetil)var1.next();
            if (p instanceof ProjetilIni) {
               p.acao();
            }

            if (p instanceof ProjeteisM4) {
               p.acao();
            }

            if (p instanceof RastroProjetil) {
               p.acao();
            }

            if (p instanceof Granada) {
               p.acao();
            }
         }

         var1 = Init.getMunicoes().iterator();

         while(var1.hasNext()) {
            Municao municao = (Municao)var1.next();
            municao.acao();
         }

         var1 = Init.getVidas().iterator();

         while(var1.hasNext()) {
            MedKit vida = (MedKit)var1.next();
            vida.acao();
         }

         var1 = Init.getExplosoes().iterator();

         while(var1.hasNext()) {
            AbstractExplosao explosao = (AbstractExplosao)var1.next();
            explosao.acao();
         }

         var1 = Init.getHelicopteros().iterator();

         while(var1.hasNext()) {
            Helicoptero helicoptero = (Helicoptero)var1.next();
            helicoptero.acao();
         }

         Sobrevivencia.init.getMira().acao();
         Init.getSpawn().acao();
         Init.getCam().camera();
         Init.gameOver();
      } else if (Menu.getEstadoDoJogo().equals("menu")) {
         Sobrevivencia.init.getMenu().acao();
      } else if (Menu.getEstadoDoJogo().equals("pause")) {
         Sobrevivencia.init.getPause().acao();
      }

   }
}
