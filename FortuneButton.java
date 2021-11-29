
import javax.swing.JFrame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
Patrick Quinn
CSCE 111
630002654
11/17/2021
*/
public class FortuneButton implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent event) {
    JFrame appFrame = null;
    String output = "";


      double decision = Math.random(); //generates a random number that decides the outcome printed on the screen.

      if (decision >= 0.5){
        output = "They love you <3!";
      }
      if (decision < 0.5 && decision > 0.15) {
        output = "They don't love you :(";
      }
      if (decision < 0.15) {
        output = "You really shouldn't be trusting a random flower's romantic advice...";
      }




    //Makes frame and lays out components on grid;
    appFrame = new JFrame();
    JLabel result = new JLabel(output);


    appFrame.setLayout(new GridBagLayout());

        GridBagConstraints layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.anchor = GridBagConstraints.CENTER;
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        appFrame.add(result, layoutConst);

    //options for displaying GUI
    appFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    appFrame.setVisible(true);
    appFrame.setLocationRelativeTo(null);
    appFrame.setSize(500,200);
    appFrame.setTitle("Result!");


    } //end actionlistener
}//end class
