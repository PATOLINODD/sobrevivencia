package sobrevivencia.janela;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import sobrevivencia.Sobrevivencia;

public class Janela {
   public static final int largura;
   public static final int altura;
   JFrame jf = new JFrame("Sobrevivencia");

   public void janela(Sobrevivencia sob) {
      sob.setPreferredSize(new Dimension(largura, altura));
      this.jf.add(sob);
      this.jf.setUndecorated(true);
      this.jf.pack();
      this.jf.setLocationRelativeTo((Component)null);
      this.jf.setResizable(false);
      this.jf.setDefaultCloseOperation(3);
      this.jf.setVisible(true);
      this.setCursor(this.jf);
   }

   private void setCursor(JFrame jf) {
      Toolkit toolKit = Toolkit.getDefaultToolkit();
      Image icon = toolKit.getImage(this.getClass().getResource("/icon.png"));
      Cursor cursor = toolKit.createCustomCursor(icon, new Point(0, 0), "img");
      jf.setCursor(cursor);
   }

   static {
      largura = Toolkit.getDefaultToolkit().getScreenSize().width;
      altura = Toolkit.getDefaultToolkit().getScreenSize().height;
   }
}
