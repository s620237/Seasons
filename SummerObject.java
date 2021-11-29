import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.geom.Ellipse2D.Double;
import java.awt.Point;
import java.util.Random;

class SummerObject {
  private int width = 50, height = 10;
  private double xPos, yPos, speed = 0;
  private Point pnt;
  private Random rand = new Random(10);

  public SummerObject() {
    xPos = 0;
    yPos = 0;
    speed = 0.1;
  }
  public SummerObject(Point pnt) {
    this.xPos = pnt.getX();
    this.yPos = pnt.getY();
    this.speed = 0.25;
  }

  public void moveObject(double newSpeed) {
    xPos = xPos - speed;
    speed = newSpeed;
  }

  public void moveDecor(double newSpeed) {
    xPos = xPos - speed;
    speed = newSpeed;
    if (xPos < -100) {
      xPos = 800 + 50 * rand.nextInt(20);
    }
  }

  public void playerMove(boolean mU, boolean mL, boolean mD, boolean mR) {
    if (mU) yPos = yPos - speed;
    if (mL) xPos = xPos - speed;
    if (mD) yPos = yPos + speed;
    if (mR) xPos = xPos + speed;
    if (xPos < 0) xPos = 0;
    if (yPos < 100 - height) yPos = 100 - height;
    if (xPos > 800 - width) xPos = 800 - width;
    if (yPos > 400 - 2 * height - 20) yPos = 400 - 2 * height - 20;
  }

  public void setPoint(Point pnt) {
    this.xPos = pnt.getX();
    this.yPos = pnt.getY();
  }

  public void setSpeed(double speed) { this.speed = speed; }

  public int getX() { return (int) xPos; }

  public int getY() { return (int) yPos; }
}
