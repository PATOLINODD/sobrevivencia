package sobrevivencia.projeteis;

import java.awt.Color;
import sobrevivencia.som.Som;

public class ProjetilIni extends AbstractProjetil {
   public ProjetilIni(float x, float y, int tamanho, double dx, double dy, double danoAtk, int tempoVida, Color cor, Som som) {
      super(x, y, tamanho, dx, dy, danoAtk, tempoVida, cor, som);
   }
}
