import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;

class mainFrame extends JFrame {
  //initializes private class variables that are used later in the class:
  private JButton fallButton;
  private JButton winterButton;
  private JButton springButton;
  private JButton summerButton;
  private JButton vivaldiButton;
  private GridBagConstraints mainFrameConst;
  public mainFrame() {
    //sets frame attributes and GridBagConstraints:
    setLayout(new GridBagLayout());
    setSize(300,400);
    setTitle("Four Seasons");
    mainFrameConst = new GridBagConstraints();
    //creates buttons for each time waster and main button:
    fallButton = new JButton("Fall (Liliana)");
    winterButton = new JButton("Winter (Catherine)");
    springButton = new JButton("Spring (Patrick)");
    summerButton = new JButton("Summer (Luke)");
    JButton fortuneButton = new JButton("click"); //this is because FortuneButton was being finnicky with compiling inside springListener it's not called the only purpose is to create the class.
    vivaldiButton = new JButton("Click here for a cool thing!!");
    //sets colors of the season buttons:
    fallButton.setBackground(Color.ORANGE);
    winterButton.setBackground(Color.WHITE);
    springButton.setBackground(Color.GREEN);
    summerButton.setBackground(Color.PINK);
    fortuneButton.addActionListener(new FortuneButton()); //also only for getting FortuneButton to compile when running mainFrame
    //adds each corresponding action listener to each button (the listeners pull up new instances of each class that corresponds to the seasons):
    fallButton.addActionListener(new fallListener());
    winterButton.addActionListener(new winterListener());
    springButton.addActionListener(new springListener());
    summerButton.addActionListener(new summerListener());
    vivaldiButton.addActionListener(new vivaldiListener());
    //GridBagLayout stuff:
    mainFrameConst.gridx = 0;
    mainFrameConst.gridy = 0;
    mainFrameConst.ipady = 100;
    mainFrameConst.ipadx = 100;
    mainFrameConst.fill= GridBagConstraints.BOTH;
    add(fallButton, mainFrameConst);
    mainFrameConst.gridx = 0;
    mainFrameConst.gridy = 1;
    mainFrameConst.ipady = 100;
    mainFrameConst.ipadx = 100;
    mainFrameConst.fill= GridBagConstraints.BOTH;
    add(springButton, mainFrameConst);
    mainFrameConst.gridx = 1;
    mainFrameConst.gridy = 0;
    mainFrameConst.ipady = 100;
    mainFrameConst.ipadx = 100;
    mainFrameConst.fill= GridBagConstraints.BOTH;
    add(winterButton, mainFrameConst);
    mainFrameConst.gridx = 1;
    mainFrameConst.gridy = 1;
    mainFrameConst.ipady = 100;
    mainFrameConst.ipadx = 100;
    mainFrameConst.fill= GridBagConstraints.BOTH;
    add(summerButton, mainFrameConst);
    mainFrameConst.gridx = 0;
    mainFrameConst.gridy = 2;
    mainFrameConst.gridwidth= 2;
    add(vivaldiButton, mainFrameConst);
  }//end constructor

  public static void main(String[] args) {
    //main method that makes a new mainFrame and makes it visible:
    mainFrame yeet = new mainFrame();
    yeet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    yeet.setLocationRelativeTo(null);
    yeet.pack();
    yeet.setVisible(true);
  }//end main
}//end class
