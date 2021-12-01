import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.geom.Ellipse2D.Double;
import java.awt.Point;
import java.util.Random;

class SummerObject {
  // Object sepcifications
  private int width = 50, height = 10;
  private double xPos, yPos, speed = 0;
  private Point pnt;
  private Random rand = new Random(10);

  // Default Constructor
  public SummerObject() {
    xPos = 0;
    yPos = 0;
    speed = 0.1;
  }
  // Defined Constructor (with starting position)
  public SummerObject(Point pnt) {
    this.xPos = pnt.getX();
    this.yPos = pnt.getY();
    this.speed = 0.25;
  }
  // Moves obstacles from right to left at a determined speed
  public void moveObject(double newSpeed) {
    xPos = xPos - speed;
    speed = newSpeed;
  }
  // If object is decor, the object respawns back at the right side of the screen
  // if it reaches the left side
  public void moveDecor(double newSpeed) {
    xPos = xPos - speed;
    speed = newSpeed;
    if (xPos < -100) {
      xPos = 800 + 50 * rand.nextInt(20);
    }
  }

  // If object is player, move based on input of keys (whether they're pressed is
  // true)
  public void playerMove(boolean mU, boolean mL, boolean mD, boolean mR) {
    if (mU) yPos = yPos - speed;
    if (mL) xPos = xPos - speed;
    if (mD) yPos = yPos + speed;
    if (mR) xPos = xPos + speed;
    // Not able to move out of bounds
    if (xPos < 0) xPos = 0;
    if (yPos < 100 - height) yPos = 100 - height;
    if (xPos > 800 - width) xPos = 800 - width;
    if (yPos > 400 - 2 * height - 20) yPos = 400 - 2 * height - 20;
  }

  // sets the position (Point(x,y)) of the object on the screen
  public void setPoint(Point pnt) {
    this.xPos = pnt.getX();
    this.yPos = pnt.getY();
  }
  // sets speed of the object across the screen
  public void setSpeed(double speed) { this.speed = speed; }
  // returns x-position on screen
  public int getX() { return (int) xPos; }
  // returns y-position on screen
  public int getY() { return (int) yPos; }
}
