import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;

class mainFrame extends JFrame {
  private JButton fallButton;
  private JButton winterButton;
  private JButton springButton;
  private JButton summerButton;
  private JButton vivaldiButton;
  private JPanel fallPanel;
  private JPanel winterPanel;
  private JPanel springPanel;
  private JPanel summerPanel;
  private GridBagConstraints mainFrameConst;
  public mainFrame() {
    setLayout(new GridBagLayout());
    setSize(300,400);
    setTitle("Four Seasons");
    mainFrameConst = new GridBagConstraints();
    fallButton = new JButton("Fall (Liliana)");
    winterButton = new JButton("Winter (Catherine)");
    springButton = new JButton("Spring (Patrick)");
    summerButton = new JButton("Summer (Luke)");
    JButton fortuneButton = new JButton("click"); //this is because FortuneButton was being finnicky with compiling inside springListener it's not called the only purpose is to create the class.
    vivaldiButton = new JButton("click");
    fallButton.setBackground(Color.ORANGE);
    winterButton.setBackground(Color.WHITE);
    springButton.setBackground(Color.GREEN);
    summerButton.setBackground(Color.PINK);
    fortuneButton.addActionListener(new FortuneButton()); //also only for getting FortuneButton to compile when running mainFrame
    fallPanel = new JPanel();
    winterPanel = new JPanel();
    springPanel = new JPanel();
    summerPanel = new JPanel();
    fallButton.addActionListener(new fallListener());
    winterButton.addActionListener(new winterListener());
    springButton.addActionListener(new springListener());
    summerButton.addActionListener(new summerListener());
    vivaldiButton.addActionListener(new vivaldiListener());
    fallPanel.add(fallButton);
    winterPanel.add(winterButton);
    summerPanel.add(summerButton);
    springPanel.add(springButton);
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
    //mainFrameConst.ipady = 100;
    //mainFrameConst.ipadx = 100;
    mainFrameConst.gridwidth= 2;
    add(vivaldiButton, mainFrameConst);
  }

  public static void main(String[] args) {
    mainFrame yeet = new mainFrame();
    yeet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    yeet.setLocationRelativeTo(null);
    yeet.pack();
    yeet.setVisible(true);
  }
}
