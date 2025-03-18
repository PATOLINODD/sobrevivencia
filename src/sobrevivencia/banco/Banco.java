/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sobrevivencia.banco;

/**
 *
 * @author PATOLINODD
 */
public class Banco {

    private int creditos;

    public int getCreditos() {
        return creditos;
    }

    /**
     * recebe creditos para alteração 
     * creditos = 10 colocar -1 = 9
     * @param creditos 
     */
    public void alteraCreditos(int creditos) {
        this.creditos =+ creditos;
    }
    
    public void zeraCreditos(){
        this.creditos = 0;
    }

    @Override
    public String toString() {
        return "Banco{" + "creditos=" + creditos + '}';
    }

}
