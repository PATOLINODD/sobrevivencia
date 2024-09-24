package sobrevivencia.acao;

import java.util.Random;
import sobrevivencia.Init;
import sobrevivencia.entidades.Inimigo12;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.entidades.Inimigos02;
import sobrevivencia.pontosDeSpawn.AbstractPontos;

public class PontosSpawn {
   public static boolean modoTreino;
   private int tempoRespawn;
   private int tempoEspera;
   private int maxInis = 20;
   private int qInis = 0;
   private boolean ondaIniciada = true;
   private static int contador = 0;
   private static int record;
   private static int onda = 1;
   private Random r = new Random();
   private int tempoRespawnMiniBoss = 0;
   private int maxQMiniBoss = 1;
   private int qMiniBoss;
   public static int quantidadeMiniC;
   public static int quantidadeIni;

   public PontosSpawn() {
      this.qMiniBoss = this.maxQMiniBoss;
   }

   public void acao() {
      if (!modoTreino) {
         this.spawnaEnemies();
      }

   }

   private void spawnaEnemies() {
      if (this.ondaIniciada) {
         int chanceSpawnInis;
         int index;
         AbstractPontos p;
         if (onda % 10 != 0) {
            if (onda % 5 == 0) {
               if (this.qMiniBoss < this.maxQMiniBoss && this.tempoRespawnMiniBoss == 300) {
                  this.tempoRespawnMiniBoss = 0;
               }

               if (Init.getEntidades().isEmpty() && this.qInis >= this.maxInis) {
                  this.ondaIniciada = false;
               }
            }

            if (Init.getEntidades().isEmpty() && this.qInis >= this.maxInis) {
               this.ondaIniciada = false;
            }

            if (this.qInis < this.maxInis) {
               ++this.tempoRespawn;
               if (this.tempoRespawn > 150) {
                  this.tempoRespawn = 0;
                  if (Init.getEntidades().size() < 50) {
                     chanceSpawnInis = this.r.nextInt(6);
                     index = this.r.nextInt(Init.getPontosSpawn().size() - 1);
                     p = (AbstractPontos)Init.getPontosSpawn().get(index);
                     if (chanceSpawnInis > 4) {
                        Init.addIni(new Inimigos((float)p.getX(), (float)p.getY(), 31, 31));
                     } else if (chanceSpawnInis > 2) {
                        Init.addIni(new Inimigos02((float)p.getX(), (float)p.getY(), 31, 31));
                     } else {
                        Init.addIni(new Inimigo12((float)p.getX(), (float)p.getY(), 31, 31));
                     }

                     ++this.qInis;
                  }
               }
            }
         } else {
            if (Init.getEntidades().isEmpty() && this.qInis >= this.maxInis) {
               this.ondaIniciada = false;
            }

            if (this.qInis < this.maxInis) {
               ++this.tempoRespawn;
               if (this.tempoRespawn > 150) {
                  this.tempoRespawn = 0;
                  if (Init.getEntidades().size() < 50) {
                     chanceSpawnInis = this.r.nextInt(6);
                     index = this.r.nextInt(Init.getPontosSpawn().size() - 1);
                     p = (AbstractPontos)Init.getPontosSpawn().get(index);
                     if (chanceSpawnInis > 4) {
                        Init.addIni(new Inimigos((float)p.getX(), (float)p.getY(), 31, 31));
                     } else if (chanceSpawnInis > 2) {
                        Init.addIni(new Inimigos02((float)p.getX(), (float)p.getY(), 31, 31));
                     } else {
                        Init.addIni(new Inimigo12((float)p.getX(), (float)p.getY(), 31, 31));
                     }

                     ++this.qInis;
                  }
               }
            }
         }
      } else {
         ++this.tempoEspera;
         if (this.tempoEspera >= 150) {
            if (onda % 10 != 0 && onda % 5 == 0) {
            }

            this.qInis = 0;
            this.maxInis += 5;
            this.tempoEspera = 0;
            this.tempoRespawn = 0;
            ++onda;
            this.ondaIniciada = true;
         }
      }

   }

   public int getMaxInis() {
      return this.maxInis;
   }

   public void setMaxInis(int maxInis) {
      this.maxInis = maxInis;
   }

   public int getqInis() {
      return this.qInis;
   }

   public void setqInis(int qInis) {
      this.qInis = qInis;
   }

   public static int getContador() {
      return contador;
   }

   public static void setContador(int contador) {
      PontosSpawn.contador = contador;
   }

   public static void alteraContador(int contador) {
      PontosSpawn.contador += contador;
   }

   public static int getRecord() {
      return record;
   }

   public static void setRecord(int record) {
      PontosSpawn.record = record;
   }

   public static void alteraRecord(int record) {
      PontosSpawn.record += record;
   }

   public static int getOnda() {
      return onda;
   }

   public static void setOnda(int onda) {
      PontosSpawn.onda = onda;
   }

   public int getMaxQMiniBoss() {
      return this.maxQMiniBoss;
   }

   public void setMaxQMiniBoss(int maxQMiniBoss) {
      this.maxQMiniBoss = maxQMiniBoss;
   }

   public int getqMiniBoss() {
      return this.qMiniBoss;
   }

   public void setqMiniBoss(int qMiniBoss) {
      this.qMiniBoss = qMiniBoss;
   }
}
