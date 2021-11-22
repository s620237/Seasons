import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
/*
Catherine Larson
431006908
Tree Maker
11/17/2021
PC User
CSCE-111-503
Code examples from https://www.javacodex.com/More-Examples/2/1 were used to make this.
*/
//This program turns files into jlabels that can be used in jframes from strings indicating the file name of these files.
public class TreeMaker {
  private String fileName;
  private ImageIcon treeIcon;
  private BufferedImage img;
  private JLabel treeLabel;
  public TreeMaker(String fileName) {
    this.fileName = fileName;
    try {
      this.img = ImageIO.read(new File(fileName));
    } catch(Exception e) {

    }
    this.treeIcon = new ImageIcon(this.img);
    this.treeLabel = new JLabel(this.treeIcon);
  }//end constructor

  public JLabel getTreeLabel() {
    return this.treeLabel;
  }
}//end class
