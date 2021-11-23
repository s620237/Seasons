import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

class fallListener implements ActionListener {

  public void actionPerformed(ActionEvent event) {
    halloween pizza = new halloween();
    pizza.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    pizza.setLocationRelativeTo(null);
    pizza.pack();
    pizza.setVisible(true);

  }
}
