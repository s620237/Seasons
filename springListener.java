import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

class springListener implements ActionListener {

  public void actionPerformed(ActionEvent event) {
    JFrame variable = new JFrame();
    variable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    variable.setLocationRelativeTo(null);
    variable.pack();
    variable.setVisible(true);

  }
}
