import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

class summerListener implements ActionListener {

  public void actionPerformed(ActionEvent event) {
    JFrame arbys = new JFrame();
    arbys.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    arbys.setLocationRelativeTo(null);
    arbys.pack();
    arbys.setVisible(true);

  }
}
