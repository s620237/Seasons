import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.net.URI;
import java.awt.Desktop;

class vivaldiListener implements ActionListener {

  public void actionPerformed(ActionEvent event) {
    try {

      Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=zzE-kVadtNw"));
    } catch(Exception e) {
      System.out.println("weh");
    }

  }
}
