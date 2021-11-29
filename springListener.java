
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
import javax.swing.event.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/*
Patrick Quinn
CSCE 111
630002654
11/17/2021
*/
public class springListener implements ActionListener {

  public static final String IMAGE_PATH = "https://www.hdwallpapers.in/download/white_daisy_flowers_4k_5k_hd_flowers-480x800.jpg"; // How to include a background image found at https://stackoverflow.com/questions/30018630/gridbag-background-image

  private static void createAndShowUI() {
     Image image = null;
     try {
        URL url = new URL(IMAGE_PATH);
        image = ImageIO.read(url);
        // JLabel label = new JLabel(new ImageIcon(image));
        ImagePanelA imagePanel = new ImagePanelA(image);

        JFrame frame = new JFrame("Flower Fortune Teller");
        frame.getContentPane().add(imagePanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
     } catch (IOException e) {
        e.printStackTrace();
     }

  } //end createAndShowUI

  public void actionPerformed(ActionEvent event) {
     java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
           createAndShowUI();
          }
       });

  } //end actionPerformed

}//end springlistener class

@SuppressWarnings("serial")
class ImagePanelA extends JPanel {
  private Image image;
  private JButton pick;

  public ImagePanelA(Image image) {

    ActionListener FortuneButton = new FortuneButton();
    pick = new JButton("Pick a petal");
    pick.addActionListener(FortuneButton);

    JLabel intro = new JLabel("Howdy! Welcome to the Romance Fortune Teller!");
    JPanel introPanel = new JPanel();
    introPanel.setBackground(Color.white);
    introPanel.add(intro);

    JLabel explain = new JLabel("Click on the button below to figure out if your crush loves you or not!");
    JPanel explainPanel = new JPanel();
    explainPanel.setBackground(Color.white);
    explainPanel.add(explain);
    //sets image and creates gridbaglayout and how arranges what info goes where.
     this.image = image;
     setLayout(new GridBagLayout());

     add(introPanel, createGbc(0, 0, 1, 1));
     add(explainPanel, createGbc(0, 1, 1, 1));
     add(pick, createGbc(0, 2, 1, 1));
  }

  private GridBagConstraints createGbc(int x, int y, int w, int h) {
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.gridx = x;
     gbc.gridy = y;
     gbc.gridwidth = w;
     gbc.gridheight = h;

     gbc.weightx = 0.0; // bunches stuff in center in x orientation
     gbc.weighty = 0.0; // bunches stuff in center in y orientation
     gbc.fill = GridBagConstraints.BOTH;
     gbc.insets = new Insets(5, 5, 5, 5);

     return gbc;
  }

  @Override
  public Dimension getPreferredSize() {
     Dimension superSize = super.getPreferredSize();
     if (isPreferredSizeSet() || image == null) {
        return superSize;
     }
     int prefW = Math.max(image.getWidth(null), superSize.width);
     int prefH = Math.max(image.getHeight(null), superSize.height);
     return new Dimension(prefW, prefH);
  }

  @Override
  protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     if (image != null) {
        g.drawImage(image, 0, 0, null);
     }
  }

 }//end class
