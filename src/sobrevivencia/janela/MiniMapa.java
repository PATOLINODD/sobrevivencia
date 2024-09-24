package sobrevivencia.janela;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.mapa.Mapa;
import sobrevivencia.mapa.Parede;

public class MiniMapa {

    public void graficos(Graphics g) {

        int x = 400, y = 150;
        int alfa = 50;
        g.setColor(new Color(50, 100, 0, alfa));
        g.fillRect(x, y, Mapa.larg * 10, Mapa.altu * 10);
        g.setColor(new Color(255, 255, 255));
        g.fillRect((int) (Init.getJogador().getX() / 32.0F * 10.0F) + x, (int) (Init.getJogador().getY() / 32.0F * 10.0F) + y, 10, 10);
        g.setColor(new Color(255, 0, 0));
        Iterator var8 = Init.getEntidades().iterator();

        while (var8.hasNext()) {
            AbstractEntidade entidade = (AbstractEntidade) var8.next();
            if (entidade instanceof Inimigos) {
                Inimigos inimigo = (Inimigos) entidade;
                g.fillRect((int) (inimigo.getX() / 32.0F * 10.0F) + x, (int) (inimigo.getY() / 32.0F * 10.0F) + y, 10, 10);
            }
        }

        var8 = Init.getParedes().iterator();

        while (var8.hasNext()) {
            Parede parede = (Parede) var8.next();
            g.setColor(new Color(100, 255, 0, alfa));
            g.fillRect(parede.getX() / 32 * 10 + x, parede.getY() / 32 * 10 + y, 10, 10);
        }

    }
}
