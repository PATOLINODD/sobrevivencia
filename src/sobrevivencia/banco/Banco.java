package sobrevivencia.banco;

public class Banco {
   private int creditos;

   public int getCreditos() {
      return this.creditos;
   }

   public void alteraCreditos(int creditos) {
      this.creditos = creditos;
   }

   public void zeraCreditos() {
      this.creditos = 0;
   }

   public String toString() {
      return "Banco{creditos=" + this.creditos + '}';
   }
}
