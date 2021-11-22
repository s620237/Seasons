/*
CSCE 111 Section 502
Liliana's Time Waster: Halloween
This calculator collects information from you and determines whether or not
you should drive right now. This is not a calculator that should be used outside
of this class and is created PURELY for me to understand class concepts.
Name: Liliana Hildebrand
UIN: 930006956
Additional Source: https://www.geeksforgeeks.org/path-relativize-method-in-java-with-examples/
https://www.usatoday.com/story/money/food/2021/10/10/reeses-starburst-sour-patch-kids-top-candy-candy-corn-worst/5930166001/
https://www.mobilityworks.com/blog/halloween-candy-through-the-decades/
*/
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class halloween extends JFrame implements ActionListener,ChangeListener{
  private static final int FRAME_WIDTH = 500;
  private static final int FRAME_HEIGHT = 500;
  private JSlider sugarlevel;// to intake sugarlevel
  private JLabel ageLabel;// label for hours slept
  private JLabel sugarlevelLabel;// label for how they feeling
  private JFormattedTextField age;// text field of hours slept
  private JFormattedTextField hrsF;// text field for hours since food
  private JFormattedTextField can_results;// tell the person if they should drive
  private JButton letguess;// button to letguess
  private JPanel panelsugar;
  private int feel;
  int emotState;
  private JLabel drive_result;
  halloween(){



// Setting up the first JFrame with asking the user how they feel

    setSize(FRAME_WIDTH,FRAME_HEIGHT);
    setTitle("I bet I can guess your Halloween candy");
    setResizable(false);
// Creating the grid
setLayout(new GridBagLayout());
GridBagConstraints grid = new GridBagConstraints();
  //Locations
  grid.insets = new Insets(0,0,0,0);
  grid.gridx = 0;
  grid.gridy = 0;


  //Creating the button
  JButton letguess = new JButton("Let me guess");
  //ActionListener listener1;
  letguess.addActionListener(this);


// Asking the user how they feel emotionally using a slider
//Creating the sugarlevel
int sweet = 10;// the max amount of happniness you can feel
int sour = 5; // the middle amount of happiness you can feel
int bitter = 0;// when you feel sad
// Creating the slider
sugarlevel = new JSlider(bitter, sweet); // creating the settings
sugarlevel.addChangeListener(this);
sugarlevel.setMajorTickSpacing(1);
sugarlevel.setPaintTicks(true);
sugarlevel.setPaintLabels(true);

//Asking the user how many hours in the past 24 hours have they slept
  //Creating the labels
  JLabel ageLabel = new JLabel("How old are you?");
  JLabel sugarlevelLabel = new JLabel("On a scale of 0 being bitter, 5 being sour, and 10 being very sweet, what flavor do you like your candy to have?");
  JLabel drive_result = new JLabel("Your favorite candy is...");
  //Creating the text fields
  age = new JFormattedTextField(NumberFormat.getNumberInstance());
  //age.addChangeListener(this);
  age.setEditable(true);
  age.setText("0");
  age.setColumns(5);

  can_results = new JFormattedTextField();
  can_results.setEditable(true);
  can_results.setText("I have yet to guess");
  can_results.setColumns(20);
// how do I make these both show up in my input
  // Formatting them
  grid = new GridBagConstraints();
  grid.gridx = 0;
  grid.gridy = 5;
  grid.insets = new Insets(0,0,0,0);
  add(ageLabel,grid);

  grid = new GridBagConstraints();
  grid.gridx = 0;
  grid.gridy = 10;
  grid.insets = new Insets(0,0,0,0);
  add(age,grid);

  grid = new GridBagConstraints();
  grid.gridx = 0;
  grid.gridy = 15;
  grid.insets = new Insets(0,0,0,0);
  add(sugarlevelLabel,grid);

  grid = new GridBagConstraints();
  grid.gridx = 0;
  grid.gridy = 20;
  grid.insets = new Insets(1,1,1,1);
  add(sugarlevel,grid);

  grid = new GridBagConstraints();
  grid.gridx = 0;
  grid.gridy = 25;
  grid.insets = new Insets(1,1,1,1);
  add(letguess,grid);

  grid = new GridBagConstraints();
  grid.gridx = 0;
  grid.gridy = 30;
  grid.insets = new Insets(5,5,5,5);
  add(drive_result,grid);

  grid = new GridBagConstraints();
  grid.gridx = 0;
  grid.gridy = 35;
  add(can_results,grid);

// go to office hours and ask how you can make the textfields coexist

// closing statements
JPanel panelsugar = new JPanel();// creating panel
add(panelsugar);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
pack();
setResizable(false);
setVisible(true);
// Getting the value from the slider
favcandy user = new favcandy();





// Setting that as the value for emotional feeling
//user.setTaste(emotState);


//Asking the user how many hours has it been since they ate
// Telling the user whether or not they should drive
}//end of constructor
public void stateChanged(ChangeEvent event){
  // JSlider sourceEvent = (JSlider) event.getSource();
  feel = sugarlevel.getValue();
}

@Override
public void actionPerformed(ActionEvent event){
  double howold = ((Number)age.getValue()).doubleValue();
  favcandy favcandy = new favcandy();
  favcandy.setTaste(feel);
  favcandy.setAge(howold);
  can_results.setText(favcandy.favcandy());






}// end of action event



  public static void main(String[] args) {
    halloween person = new halloween();




  }// end of main

}// end of class
