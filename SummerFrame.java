import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.awt.Font;
import java.awt.Color;

/*
Joseph (Luke) Gallucci
UIN: 630004235
CSCE 111: section 503
JosephGallucciEx7.java 10/22/21
Platform: Windows
*/

public class SummerFrame extends JFrame implements KeyListener {
  private boolean moveLeft = false, moveRight = false, moveUp = false, moveDown = false;
  private boolean delay = false;
  private Timer timer = new Timer();
  private GameplayScreen screen = new GameplayScreen();
  private long DELAY_TIME = 2000;

  TimerTask aTask = new TimerTask() {
    @Override
    public void run() {
      screen.repaint();
      screen.playerMove(moveUp, moveLeft, moveDown, moveRight);
      screen.objectsMove();
      // Crash Event
      if (screen.getObjCollide() && delay == false) {
        delay = true;
        screen.setBlackOut(true);
        Timer delayStart = new Timer();
        screen.startPosition();
        delayStart.schedule(new TimerTask() {
          @Override
          public void run() {
            delay = false;
            screen.setBlackOut(false);
            screen.setPlayerScore(0);
            delayStart.cancel();
            }
          }, DELAY_TIME);
      }
    }
  };

  // Frame for the main Program
  SummerFrame() {
    setTitle("SummerFrame");
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    add(screen);
    screen.createObjects();
    screen.startPosition();
    setSize(800,400);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    timer.scheduleAtFixedRate(aTask, new Date(), 1);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    char cR = java.lang.Character.toLowerCase(e.getKeyChar());e.getKeyChar();
    if (cR == 'w') moveUp = true;
    if (cR == 'a') moveLeft = true;
    if (cR == 's') moveDown = true;
    if (cR == 'd') moveRight = true;
  }
  public void keyReleased(KeyEvent e) {
    char cR = java.lang.Character.toLowerCase(e.getKeyChar());
    if (cR == 'w') moveUp = false;
    if (cR == 'a') moveLeft = false;
    if (cR == 's') moveDown = false;
    if (cR == 'd') moveRight = false;
  }
  public void keyTyped(KeyEvent e) {}

}
