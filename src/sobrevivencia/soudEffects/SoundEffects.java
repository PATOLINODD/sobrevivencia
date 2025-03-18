/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.soudEffects;

import jdk.internal.foreign.LayoutPath;

/**
 *
 * @author PATOLINODD
 */
public class SoundEffects {

    private static final String caminhoRaiz = "";
    private static final String HURT = caminhoRaiz.concat("Male Hurt.mp3"),
            TIRO_M4A1 = caminhoRaiz.concat("M4-A1-_Oneshot_firing_Sound_Effect.mp3"),
            EXPLOSAO = caminhoRaiz.concat("Explosion.mp3"),
            HIT_MARKER = caminhoRaiz.concat("Hitmarker.mp3"),
            SHOOTGUN = caminhoRaiz.concat("shootgun.mp3"),
            BLOOD_SPLAT = caminhoRaiz.concat("blood_splat.mp3"),
            ROCK_METAL = caminhoRaiz.concat("metal.mp3"),
            SHOT_ON_WALL = caminhoRaiz.concat("shotOnWall.mp3"),
            RELOAD_M4 = caminhoRaiz.concat("reloadM4.mp3"),
            EMPTY_GUN_SHOT = caminhoRaiz.concat("empty-gun-shot.mp3");

    public static String getHURT() {
        return HURT;
    }

    public static String getTIRO_M4A1() {
        return TIRO_M4A1;
    }

    public static String getEXPLOSAO() {
        return EXPLOSAO;
    }

    public static String getHIT_MARKER() {
        return HIT_MARKER;
    }

    public static String getSHOOTGUN() {
        return SHOOTGUN;
    }

    public static String getBLOOD_SPLAT() {
        return BLOOD_SPLAT;
    }

    public static String getROCK_METAL() {
        return ROCK_METAL;
    }

    public static String getSHOT_ON_WALL() {
        return SHOT_ON_WALL;
    }

    public static String getEMPTY_GUN_SHOT() {
        return EMPTY_GUN_SHOT;
    }

    public static String getRELOAD_M4() {
        return RELOAD_M4;
    }

}
