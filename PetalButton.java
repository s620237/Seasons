import java.lang.Math;
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
public class PetalButton implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent event) {
    double decision = 0;
    String output = "";
    JFrame appFrame = null;
    JLabel petalResult;  //lavels for all text boxes in table

    decision = Math.random(); //generates a random number

    if (decision >= 0.5){
      System.out.println("He loves you");
      output = "He loves you";
    }
    if (decision < 0.5 && decision > 0.05) {
      System.out.println("He loves you not");
      output = "He loves you not";
    }
    if (decision < 0.05) {
      System.out.println("You really shouldn't be trusting a badly drawn flower's romantic advice, I think you already know the answer to this question");
      output = "You really shouldn't be trusting a badly drawn flower's romantic advice, I think you already know the answer to this question";
    }

    GridBagConstraints layoutConst = null; //for grid





    petalResult = new JLabel(output); //text entries for table





    //Makes frame and lays out components on grid;
    appFrame = new JFrame();
    appFrame.setLayout(new GridBagLayout());

    layoutConst = new GridBagConstraints();
    layoutConst.insets = new Insets(10, 10, 10, 1);
    layoutConst.anchor = GridBagConstraints.LINE_END;
    layoutConst.gridx = 0;
    layoutConst.gridy = 1;
    appFrame.add(petalResult, layoutConst);


    //options for displaying GUI
    appFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    appFrame.setVisible(true);
    appFrame.setLocationRelativeTo(null);
    appFrame.setSize(300,200);
    appFrame.setTitle("Do they love me???");


  } //end actionlistener
}//end class
