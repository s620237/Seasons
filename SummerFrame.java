import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
/*
Summer Sources:
https://docs.oracle.com/javase/7/docs/api/java/util/Timer.html - Timers
https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html - JComponent
https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html - graphics2D
https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html - KeyEvent
https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseEvent.html - MouseEvent
*/
public class SummerFrame extends JFrame implements KeyListener, MouseListener {
  private boolean moveLeft = false, moveRight = false, moveUp = false, moveDown = false;
  private boolean delay = false;
  private Timer timer = new Timer();
  private GameplayScreen screen = new GameplayScreen();
  private long DELAY_TIME = 3000;

  TimerTask aTask = new TimerTask() {
    @Override
    public void run() {
      if (screen.getInstruction() == false) {
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
    }
  };

  // Frame for the main Program
  SummerFrame() {
    setTitle("Wave Rider");
    addKeyListener(this);
    addMouseListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    add(screen);
    screen.createObjects();
    screen.startPosition();
    setSize(800,400);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    if (x < 505 && x > 305 && y > 225 && y < 280) screen.setInstruction(false);
  }
  @Override
  public void mouseExited(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}

}
