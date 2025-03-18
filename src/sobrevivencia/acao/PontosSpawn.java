/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.acao;

import java.util.Random;
import sobrevivencia.Area.AbstractArea;
import sobrevivencia.Init;
import sobrevivencia.entidades.Inimigo12;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.entidades.Inimigos02;
import sobrevivencia.pontosDeSpawn.AbstractPontos;

/**
 *
 * @author PATOLINODD
 */
public class PontosSpawn {

    public static boolean modoTreino;
    private int tempoRespawn, tempoEspera,
            maxInis = 20, qInis = 0;

    private boolean ondaIniciada = true;

    private static int contador = 0, record, onda = 1;

    private Random r = new Random();

    public PontosSpawn() {
    }

    public void acao() {
        if (!modoTreino) {
            spawnaEnemies();
        }
    }

    private int tempoRespawnMiniBoss = 0, maxQMiniBoss = 1, qMiniBoss = maxQMiniBoss;
    public static int quantidadeMiniC, quantidadeIni;

    private void spawnaEnemies() {

        if (ondaIniciada) {
            if (onda % 10 != 0) {
                if (onda % 5 == 0) {
                    if (qMiniBoss < maxQMiniBoss) {
//                        tempoRespawnMiniBoss++;
                        if (tempoRespawnMiniBoss == 300) {
                            tempoRespawnMiniBoss = 0;
//                            if(mcs.size() < 10){
//                                int index = r.nextInt(pontosSpawn.size()-1);
//                                AbstractPontos p = pontosSpawn.get(index);
//                                qMiniBoss += 1;
//                            }
                        }
                    }
                    if (/*mcs.isEmpty() && qMiniBoss >= maxQMiniBoss && */Init.getEntidades().isEmpty() && qInis >= maxInis) {
                        ondaIniciada = false;
                    }
                }
                if (Init.getEntidades().isEmpty() && qInis >= maxInis) {
                    ondaIniciada = false;
                }
                if (qInis < maxInis) {
                    tempoRespawn++;
                    if (tempoRespawn > 150) {
                        tempoRespawn = 0;
                        if (Init.getEntidades().size() < 50) {
                            int chanceSpawnInis = r.nextInt(6);
                            int index = r.nextInt(Init.getPontosSpawn().size() - 1);
                            AbstractPontos p = Init.getPontosSpawn().get(index);
                            if (chanceSpawnInis > 4) {
                                Init.addIni(new Inimigos((float) p.getX(), (float) p.getY(), 31, 31));
                            } else if (chanceSpawnInis > 2) {
                                Init.addIni(new Inimigos02((float) p.getX(), (float) p.getY(), 31, 31));
                            } else {
                                Init.addIni(new Inimigo12((float) p.getX(), (float) p.getY(), 31, 31));
                            }
                            qInis += 1;
                        }
                    }
                }
            } else {
                if (Init.getEntidades().isEmpty() && qInis >= maxInis) {
                    ondaIniciada = false;
                }
                ////////////////////////////////////////////////////////////////
                ////////////////temporario!/////////////////////////////////////
                ////////////////////////////////////////////////////////////////
                if (qInis < maxInis) {
                    tempoRespawn++;
                    if (tempoRespawn > 150) {
                        tempoRespawn = 0;
                        if (Init.getEntidades().size() < 50) {
                            int chanceSpawnInis = r.nextInt(6);
                            int index = r.nextInt(Init.getPontosSpawn().size() - 1);
                            AbstractPontos p = Init.getPontosSpawn().get(index);
                            if (chanceSpawnInis > 4) {
                                Init.addIni(new Inimigos((float) p.getX(), (float) p.getY(), 31, 31));
                            } else if (chanceSpawnInis > 2) {
                                Init.addIni(new Inimigos02((float) p.getX(), (float) p.getY(), 31, 31));
                            } else {
                                Init.addIni(new Inimigo12((float) p.getX(), (float) p.getY(), 31, 31));
                            }
                            qInis += 1;
                        }
                    }
                }
                ////////////////////////////////////////////////////////////////
                //////////////////////temporario////////////////////////////////
                ////////////////////////////////////////////////////////////////

                //chama o boss
//                if(qMiniBoss < maxQMiniBoss){
//                    tempoRespawnMiniBoss++;
//                    if(tempoRespawnMiniBoss == 300){
//                        tempoRespawnMiniBoss = 0;
//                            if(mcs.size() < 10){
//                                int index = r.nextInt(pontosSpawn.size()-1);
//                                AbstractPontos p = pontosSpawn.get(index);
//                                init.addMcs(
//                                        new MiniChefe(p.getX(), p.getY(), 180, 180));
//                                qMiniBoss += 1;
//                            }
//                    }
//                }
//                if (mcs.isEmpty() && qMiniBoss == maxQMiniBoss){
//                    ondaIniciada = false;
//                }
            }
        } else {
            tempoEspera++;
            if (tempoEspera >= 150) {
                if (onda % 10 != 0) {
                    if (onda % 5 == 0) {
//                        maxQMiniBoss += 1;
                    }
                }
                qInis = 0;
//                qMiniBoss = 0;
                maxInis += 5;
                tempoEspera = 0;
//                tempoRespawnMiniBoss = 0;
                tempoRespawn = 0;
                onda += 1;
                ondaIniciada = true;
            }
        }
    }

    public int getMaxInis() {
        return maxInis;
    }

    public void setMaxInis(int maxInis) {
        this.maxInis = maxInis;
    }

    public int getqInis() {
        return qInis;
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
    
    public static void alteraContador(int contador){
        PontosSpawn.contador += contador;
    }

    public static int getRecord() {
        return record;
    }

    public static void setRecord(int record) {
        PontosSpawn.record = record;
    }
    
    public static void alteraRecord(int record){
        PontosSpawn.record += record;
    }

    public static int getOnda() {
        return onda;
    }

    public static void setOnda(int onda) {
        PontosSpawn.onda = onda;
    }

    public int getMaxQMiniBoss() {
        return maxQMiniBoss;
    }

    public void setMaxQMiniBoss(int maxQMiniBoss) {
        this.maxQMiniBoss = maxQMiniBoss;
    }

    public int getqMiniBoss() {
        return qMiniBoss;
    }

    public void setqMiniBoss(int qMiniBoss) {
        this.qMiniBoss = qMiniBoss;
    }

}
