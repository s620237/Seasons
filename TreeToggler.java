import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
/*
Catherine Larson
431006908
Tree toggler
11/17/2021
PC User
CSCE-111-503
*/
public class TreeToggler extends JFrame implements ActionListener {
  //creates private class variables that get used in the program:
  private JLabel aggieTree;
  private String[] starsOnNames = {"blue.jpg", "cyan.jpg", "maroon.jpg", "orange.jpg", "pink.jpg",
    "purple.jpg", "red.jpg", "yellow.jpg"};
  private String[] starsOffNames = {"blue_star_off.jpg", "cyan_star_off.jpg", "maroon_star_off.jpg",
    "orange_star_off.jpg", "pink_star_off.jpg", "purple_star_off.jpg", "red_star_off.jpg",
    "yellow_star_off.jpg"};
  private JLabel[] starsOn;
  private JLabel[] starsOff;
  private JButton toggleColor;
  private JButton toggleStar;
  private int treeCounter;
  private int currTree;
  private boolean starOn;
  private boolean aggieOn;
  private GridBagConstraints treeConstraints;
  public TreeToggler() {
    //sets up stuff for GridBagLayout and initialization factors.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());
    treeConstraints = new GridBagConstraints();
    //code that uses loops to make a bunch of jlabels with the correct pictures on them:
    starsOn = new JLabel[8];
    starsOff = new JLabel[8];
    //makes a counter that counts how many times the change color button is pressed:
    treeCounter = 1;
    aggieOn = false;
    TreeMaker aggie = new TreeMaker("aggie.jpg");
    aggieTree = aggie.getTreeLabel();
    for(int i = 0; i < 8; i++) {
      TreeMaker newOnTree = new TreeMaker(starsOnNames[i]);
      TreeMaker newOffTree = new TreeMaker(starsOffNames[i]);
      starsOn[i] = newOnTree.getTreeLabel();
      starsOff[i] = newOffTree.getTreeLabel();
    }//end for
    //creates the buttons:
    toggleColor = new JButton("Toggle Color");
    toggleStar = new JButton("Toggle Star");
    toggleColor.addActionListener(this);
    toggleStar.addActionListener(this);
    JPanel colorPanel = new JPanel();
    JPanel starPanel = new JPanel();
    colorPanel.add(toggleColor);
    starPanel.add(toggleStar);
    //sets the initial tree in the frame and adds buttons to the frame:
    treeConstraints.gridx = 0;
    treeConstraints.gridy = 0;
    treeConstraints.insets = new Insets(10,10,10,10);
    add(starsOn[6], treeConstraints);
    starOn = true;
    currTree = 6;
    treeConstraints.gridx = 0;
    treeConstraints.gridy = 1;
    treeConstraints.insets = new Insets(10,10,10,10);
    add(colorPanel, treeConstraints);
    treeConstraints.gridx = 0;
    treeConstraints.gridy = 2;
    treeConstraints.insets = new Insets(10,10,10,10);
    add(starPanel, treeConstraints);
  }//end constructor

  @Override
  public void actionPerformed(ActionEvent event) {
    //if-else that checks which button was pressed and does a different thing depending on what was pressed:
    if(event.getSource() == this.toggleColor) {
      //if the number of times tree counter has been pressed is a multiple of 25 (AAAAAAAA), then an aggie tree will show up:
      if(this.treeCounter%25 == 0) {
        this.aggieOn = true;
        treeCounter++;
        if(this.starOn) {
          this.remove(this.starsOn[this.currTree]);
        } else {
          this.remove(this.starsOff[this.currTree]);
        }
        this.treeConstraints.gridx = 0;
        this.treeConstraints.gridy = 0;
        this.treeConstraints.insets = new Insets(10,10,10,10);
        this.add(this.aggieTree, this.treeConstraints);
        this.setVisible(false);
        this.setVisible(true);
      } else {
        if(this.starOn) {
          //if the star is on, removes the current image and iterates one step through the starsOn array to add
          //the next tree in the sequence:
          if(aggieOn) {
            this.remove(this.aggieTree);
            this.aggieOn = false;
          } else {
            this.remove(this.starsOn[this.currTree]);
          }
          this.currTree++;
          this.treeCounter++;
          if(this.currTree == 8) {
            this.currTree = 0;
          }
          this.treeConstraints.gridx = 0;
          this.treeConstraints.gridy = 0;
          this.treeConstraints.insets = new Insets(10,10,10,10);
          this.add(this.starsOn[this.currTree], this.treeConstraints);
          this.setVisible(false);
          this.setVisible(true);
        } else {
          //if the star is off, does the same thing, but with the starsOff array.
          if(aggieOn) {
            this.remove(this.aggieTree);
            this.aggieOn = false;
          } else {
            this.remove(this.starsOff[this.currTree]);
          }
          this.currTree++;
          this.treeCounter++;
          if(this.currTree == 8) {
            this.currTree = 0;
          }
          this.treeConstraints.gridx = 0;
          this.treeConstraints.gridy = 0;
          this.treeConstraints.insets = new Insets(10,10,10,10);
          this.add(this.starsOff[this.currTree], this.treeConstraints);
          this.setVisible(false);
          this.setVisible(true);
        }
      }
    } else if(event.getSource() == this.toggleStar) {
      //if the star is on, "turns it off" by setting it to false and changing arrays to iterate through, and does the opposite if the star is off:
      if(this.starOn) {
        this.starOn = false;
        if(aggieOn) {
          this.remove(this.aggieTree);
          this.aggieOn = false;
        } else {
          this.remove(this.starsOn[this.currTree]);
        }
        this.treeConstraints.gridx = 0;
        this.treeConstraints.gridy = 0;
        this.treeConstraints.insets = new Insets(10,10,10,10);
        this.add(this.starsOff[this.currTree], this.treeConstraints);
        this.setVisible(false);
        this.setVisible(true);
      } else {
        this.starOn = true;
        if(aggieOn) {
          this.remove(this.aggieTree);
          this.aggieOn = false;
        } else {
          this.remove(this.starsOff[this.currTree]);
        }
        this.treeConstraints.gridx = 0;
        this.treeConstraints.gridy = 0;
        this.treeConstraints.insets = new Insets(10,10,10,10);
        this.add(this.starsOn[this.currTree], this.treeConstraints);
        this.setVisible(false);
        this.setVisible(true);
      }
    }
  }//end actionPerformed
}// end class
