import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

class winterListener implements ActionListener {

  public void actionPerformed(ActionEvent event) {
    //makes a new treeToggler when the button is pressed and initializes some aspects of it:
    TreeToggler tree = new TreeToggler();
    tree.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    tree.setLocationRelativeTo(null);
    tree.setTitle("Tree Toggler");
    tree.pack();
    tree.setVisible(true);
  }
}
