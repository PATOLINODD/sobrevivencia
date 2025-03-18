/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.acao;

import sobrevivencia.Init;
import sobrevivencia.Sobrevivencia;
import sobrevivencia.chefes.Helicoptero;
import sobrevivencia.coletaveis.Municao;
import sobrevivencia.coletaveis.MedKit;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.explosoes.AbstractExplosao;
import sobrevivencia.menu.Menu;
import sobrevivencia.projeteis.AbstractProjetil;
import sobrevivencia.projeteis.DanoAtkArmas;
import sobrevivencia.projeteis.Granada;
import sobrevivencia.projeteis.ProjeteisM4;
import sobrevivencia.projeteis.ProjetilIni;
import sobrevivencia.projeteis.RastroProjetil;
import sobrevivencia.som.Som;
import sobrevivencia.soudEffects.SoundEffects;
import sobrevivencia.teste.Teste;

public class Acao {

    Teste teste = new Teste();
    public Som rockMetal;

    public static String explodiu = "";

    public Acao() {
        rockMetal = new Som(SoundEffects.getROCK_METAL());
//        rockMetal.play();
    }
    int tempo = 0;
    boolean tocarMusica;

    public void acao() {
//        if(!Sobrevivencia.init.getMenu().opcao.isMusicOn()){
//            rockMetal.stop();
//        }
        if (Menu.getEstadoDoJogo().equals("novo jogo") && !Menu.getEstadoDoJogo().equals("pause")) {
            Init.getJogador().acao();

            for (AbstractEntidade inimigo : Init.getEntidades()) {
                if (inimigo != null) {
                    inimigo.acao();
                }
            }

            for (AbstractProjetil p : Init.getProjeteis()) {
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
//        for(Helicoptero helicoptero : Init.getHelicopteros()){
//            helicoptero.acao();
//        }

            for (Municao municao : Init.getMunicoes()) {
                municao.acao();
            }
            for (MedKit vida : Init.getVidas()) {
                vida.acao();
            }

            for (AbstractExplosao explosao : Init.getExplosoes()) {
                explosao.acao();
            }
            for (Helicoptero helicoptero : Init.getHelicopteros()) {
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
