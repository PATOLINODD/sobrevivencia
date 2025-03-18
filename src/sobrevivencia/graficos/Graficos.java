/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import sobrevivencia.Init;
import sobrevivencia.Sobrevivencia;
import sobrevivencia.coletaveis.Municao;
import sobrevivencia.coletaveis.MedKit;
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

    //
    //
    public RayCasting rayCasting;
    public MiniMapa miniMapa;

    public BufferedImage img;
    public static final int escala = 100;

    private Interface ui = new Interface();

    public Graficos() {
        img = new BufferedImage(Janela.largura, Janela.altura, BufferedImage.TYPE_INT_RGB);
        rayCasting = new RayCasting();
        miniMapa = new MiniMapa();
    }

    public void graficos(Sobrevivencia sob) {
        Graphics g = img.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        BufferStrategy bf = sob.getBufferStrategy();
        if (bf == null) {
            sob.createBufferStrategy(3);
            return;
        }

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Janela.largura, Janela.altura);
        ///////////////////////
        g2.translate(Init.getCam().getX(), Init.getCam().getY());

        ///mapa
        Init.getMapa().graficos(g);

        for (Municao m : Init.getMunicoes()) {
            m.graficos(g);
        }
        for (MedKit v : Init.getVidas()) {
            v.graficos(g);
        }

        for (AbstractExplosao ex : Init.getExplosoes()) {
            ex.graficos(g);
        }
        Init.getArmas().graficos(g);
        Init.getExplosivos().graficos(g);
        Init.getSentinelas().graficos(g);
        Init.getSuportes().graficos(g);

//        rayCasting.graficos(g);
        Init.getJogador().graficos(g);
        for (AbstractEntidade inimigo : Init.getEntidades()) {
            inimigo.graficos(g);
        }

        for (AbstractProjetil p : Init.getProjeteis()) {
            p.graficos(g);
        }

        g2.translate(-Init.getCam().getX(), -Init.getCam().getY());

        Sobrevivencia.init.getMira().graficos(g);

        ui.graficos(g);
        //minaMapa
        if (Jogador.miniMapa) {
            Sobrevivencia.g.miniMapa.graficos(g);
        }
        //////////////////////
        if (Menu.getEstadoDoJogo().equals("menu")) {
            Sobrevivencia.init.getMenu().graficos(g);
        }
        
        if (Menu.getEstadoDoJogo().equals("pause")) {
            Sobrevivencia.init.getPause().graficos(g);
        }
        
        g.dispose();
        g = bf.getDrawGraphics();
        g.drawImage(img, 0, 0, Janela.largura + escala, Janela.altura + escala, sob);
        bf.show();
    }

}
