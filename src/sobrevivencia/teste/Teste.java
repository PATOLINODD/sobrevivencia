package sobrevivencia.teste;

import java.util.Iterator;
import sobrevivencia.Init;
import sobrevivencia.Sobrevivencia;
import sobrevivencia.mapa.Parede;

public class Teste {
   public void teste() {
      int larg = Sobrevivencia.g.img.getWidth();
      int altu = Sobrevivencia.g.img.getHeight();

      for(int xy = 0; xy < larg * altu; ++xy) {
         Iterator var4 = Init.getParedes().iterator();

         while(var4.hasNext()) {
            Parede parede = (Parede)var4.next();
            if (xy == parede.getX() + parede.getY()) {
               System.out.println("posX: " + parede.getX());
               System.out.println("posY: " + parede.getY());
            }
         }
      }

   }
}
