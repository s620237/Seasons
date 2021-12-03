import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D.Double;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.Font;
import java.util.Random;
import java.awt.Point;

// GameplayScreen extends the functionality of a JComponent
// in order to draw and implement a playing field
public class GameplayScreen extends JComponent{
   // Player Info
   private int PLAYER_WIDTH = 50;
   private int PLAYER_HEIGHT = 10;
   // Obstacle Info
   private int NUM_ROCKS = 12;
   private int NUM_SHARKS = 4;
   private int NUM_SQUIDS = 2;
   // Decor Info
   private int NUM_PALMS = 4;
   private int NUM_PIERS = 2;
   private int NUM_BTOWELS = 1;
   private int NUM_BBALLS = 1;
   // Key Global Elements
   private int playerScore = 0;
   private double grid1Position = 800;
   private double grid2Position = 1600;
   private double worldSpeed = 0.2;
   // Player Instantiation
   private Point playerPnt = new Point(25, 300);
   private SummerObject player = new SummerObject(playerPnt);
   // Rock Array Insantiation
   private SummerObject[] rockArray = new SummerObject[NUM_ROCKS];
   private Point[] rockPntArray = new Point[NUM_ROCKS];
   // Shark Array Insantiation
   private SummerObject[] sharkArray = new SummerObject[NUM_SHARKS];
   private Point[] sharkPntArray = new Point[NUM_SHARKS];
   // Squid Array Insantiation
   private SummerObject[] squidArray = new SummerObject[NUM_SQUIDS];
   private Point[] squidPntArray = new Point[NUM_SQUIDS];
   // Pier Array Insantiation
   private SummerObject[] pierArray = new SummerObject[NUM_PIERS];
   private Point[] pierPntArray = new Point[NUM_PIERS];
   // Palm Array Insantiation
   private SummerObject[] palmArray = new SummerObject[NUM_PALMS];
   private Point[] palmPntArray = new Point[NUM_PALMS];
   // Beach Towel Array Insantiation
   private SummerObject[] bTowelsArray = new SummerObject[NUM_BTOWELS];
   private Point[] bTowelsPntArray = new Point[NUM_BTOWELS];
   // Beach Ball Array Insantiation
   private SummerObject[] bBallArray = new SummerObject[NUM_BBALLS];
   private Point[] bBallPntArray = new Point[NUM_BBALLS];
   /*
   The ocean is set into two grids with equal number of obstacles, each taking up
   the height of the playing field and one coming right after another on the horizontal
   axis, so that as soon as one grid of objects moves off the screen enough,it respawns
   (but randomized) to keep heading towards the player
   */
   private int[][] objArray1 = new int[16][6];
   private int[][] objArray2 = new int[16][6];
   // Random Generator and conditions for gameovers and collisions
   private Random rand = new Random(1);
   private boolean objCollide = false;
   private boolean isBlackOut = false;
   private boolean isInstruction = true;

   // Scatters half of the objects across grid1
   public Point setGridPos1(Point pt) {
     int randx, randy;
     do {
       randx = rand.nextInt(16);
       randy = rand.nextInt(6);
     } while (objArray1[randx][randy] == 1);
     objArray1[randx][randy] = 1;
     pt.setLocation(randx * 50 + grid1Position, randy * 50 + 110);
     return pt;
   }
   // Scatters other half of the objects across grid2
   public Point setGridPos2(Point pt) {
     int randx, randy;
     do {
       randx = rand.nextInt(16);
       randy = rand.nextInt(6);
     } while (objArray2[randx][randy] == 1);
     objArray2[randx][randy] = 1;
     pt.setLocation(randx * 50 + grid2Position, randy * 50 + 110);
     return pt;
   }

   // Instantiate objects
   public void createObjects() {
     for (int i = 0; i < NUM_ROCKS; i++) {    // Individual Rock creation
       rockArray[i] = new SummerObject();
       rockPntArray[i] = new Point();
     }
     for (int i = 0; i < NUM_SHARKS; i++) {   // Individual Shark creation
       sharkArray[i] = new SummerObject();
       sharkPntArray[i] = new Point();
     }
     for (int i = 0; i < NUM_SQUIDS; i++) {   // Individual Squid creation
       squidArray[i] = new SummerObject();
       squidPntArray[i] = new Point();
     }
     for (int i = 0; i < NUM_PIERS; i++) {    // Individual Pier creation
       pierArray[i] = new SummerObject();
       pierPntArray[i] = new Point(rand.nextInt(800) * i, 0);
     }
     for (int i = 0; i < NUM_PALMS; i++) {    // Individual Palm creation
       palmArray[i] = new SummerObject();
       palmPntArray[i] = new Point(rand.nextInt(800) * i, 60);
     }
     for (int i = 0; i < NUM_BTOWELS; i++) {    // Individual beachtowels creation
       bTowelsArray[i] = new SummerObject();
       bTowelsPntArray[i] = new Point(rand.nextInt(350) * i, 40);
     }
     for (int i = 0; i < NUM_BBALLS; i++) {   // Individual Beachball creation
       bBallArray[i] = new SummerObject();
       bBallPntArray[i] = new Point(900, 40);
     }
   }
   // After game starts (or restarts), set initial points of objects, grids, and speeds
   public void startPosition() {
     worldSpeed = 0.2;
     grid1Position = 800;
     grid2Position = 1600;
     player.setPoint(playerPnt);
     spawnDecor();
     spawnObjects1();
     spawnObjects2();
     // The player has not collided with anything (yet)
     objCollide = false;
   }

   public void playerMove(boolean mU, boolean mL, boolean mD, boolean mR) {
     // while not gameover, allow player movement and player score to increase
     if (!isBlackOut) {
       player.playerMove(mU, mL, mD, mR);
       playerScore++;
     }
   }

   public void objectsMove() {
     // while not gameover, start moving objects across the screen towards the player at world speed
     if (!isBlackOut) {
       // Move Beach objects
       for (int i = 0; i < NUM_PIERS; i++) pierArray[i].moveDecor(worldSpeed);
       for (int i = 0; i < NUM_PALMS; i++) palmArray[i].moveDecor(worldSpeed);
       for (int i = 0; i < NUM_BTOWELS; i++) bTowelsArray[i].moveDecor(worldSpeed);
       for (int i = 0; i < NUM_BBALLS; i++) bBallArray[i].moveDecor(worldSpeed);
       // Move Obstacles on Grid 1
       for (int i = 0; i < NUM_ROCKS; i++) rockArray[i].moveObject(worldSpeed);
       for (int i = 0; i < NUM_SHARKS; i++) sharkArray[i].moveObject(worldSpeed);
       for (int i = 0; i < NUM_SQUIDS; i++) squidArray[i].moveObject(worldSpeed);
       // Move all the objects according to the worldSpeed (always increasing)
       worldSpeed += 0.00001;
       grid1Position-=worldSpeed;
       grid2Position-=worldSpeed;
       // If it reaches the end of the screen, reset the grid's position
       if (grid1Position < -800) {
         grid1Position = 800;
         spawnObjects1();
       }
       if (grid2Position < -800) {
         grid2Position = 800;
         spawnObjects2();
       }
     }
   }
   // Sets the objects initial position to the right of the screen and moves them
   // Decor
   public void spawnDecor() {
     for (int i = 0; i < NUM_PIERS; i++) pierArray[i].setPoint(pierPntArray[i]);
     for (int i = 0; i < NUM_PALMS; i++) palmArray[i].setPoint(palmPntArray[i]);
     for (int i = 0; i < NUM_BTOWELS; i++) bTowelsArray[i].setPoint(bTowelsPntArray[i]);
     for (int i = 0; i < NUM_BBALLS; i++) bBallArray[i].setPoint(bBallPntArray[i]);
   }
   // Grid 1
   public void spawnObjects1() {
     for (int i = 0; i < 16; i++) for (int j = 0; j < 6; j++) objArray1[i][j] = 0;
     for (int i = 0; i < NUM_ROCKS/2; i++) rockArray[i].setPoint(setGridPos1(rockPntArray[i]));
     for (int i = 0; i < NUM_SHARKS/2; i++) sharkArray[i].setPoint(setGridPos1(sharkPntArray[i]));
     for (int i = 0; i < NUM_SQUIDS/2; i++) squidArray[i].setPoint(setGridPos1(squidPntArray[i]));
   }
   // Grid 2
   public void spawnObjects2() {
     for (int i = 0; i < 16; i++) for (int j = 0; j < 6; j++) objArray2[i][j] = 0;
     for (int i = NUM_ROCKS/2; i < NUM_ROCKS; i++) rockArray[i].setPoint(setGridPos2(rockPntArray[i]));
     for (int i = NUM_SHARKS/2; i < NUM_SHARKS; i++) sharkArray[i].setPoint(setGridPos2(sharkPntArray[i]));
     for (int i = NUM_SQUIDS/2; i < NUM_SQUIDS; i++) squidArray[i].setPoint(setGridPos2(squidPntArray[i]));
   }
   // Draw the playing screen
   @Override
   public void paintComponent(Graphics g) {
      // Cast to Graphics2D
      Graphics2D graphicsObj = (Graphics2D) g;
      // Important shapes/areas
      Double ellipse = null;
      Polygon polygon = null;
      Area playerHitBox = null;
      Area objectHitBox = null;
      int x, y;
      int[] xPoints = null;
      int[] yPoints = null;
      String text;

      { // BackGrounds
        // Water
        graphicsObj.setColor(new Color(0, 20, 255));
        graphicsObj.fill(new Rectangle(0, 50, 800, 325));
        // Sand
        graphicsObj.setColor(new Color(190, 180, 130));
        graphicsObj.fill(new Rectangle(0, 25, 800, 50));
        // Sky
        graphicsObj.setColor(new Color(0, 200, 255));
        graphicsObj.fill(new Rectangle(0, 0, 800, 25));
        // Sun
        graphicsObj.setColor(new Color(255, 255, 50));
        graphicsObj.fill(new Double(700, 0, 20, 20));
      }

      if (isInstruction) {
        // Black Screen
        //graphicsObj.setColor(new Color(0, 0, 0));
        //graphicsObj.fill(new Rectangle(0, 0, 800, 400));
        // Summer Instructions
        graphicsObj.setColor(new Color(0, 0, 0));
        graphicsObj.setFont(new Font("Courier", Font.BOLD,50));
        text = "Use WASD to move.";
        g.drawString(text, 10, 70);
        text = "Avoid oncoming obstacles";
        g.drawString(text, 10, 123);
        text = "to get the highest score!";
        g.drawString(text, 10, 176);
        // Start "Button"
        graphicsObj.setColor(new Color(255, 255, 255));
        graphicsObj.fill(new Rectangle(300, 200, 200, 50));
        // Start Text
        graphicsObj.setColor(new Color(0, 0, 0));
        graphicsObj.setFont(new Font("Courier", Font.BOLD,50));
        text = "Start";
        g.drawString(text, 320, 240);
      }
      else {
        // Decor / BackGrounds
        { // Pier
          for (int i = 0; i < NUM_PIERS; i++) {
            x = pierArray[i].getX();
              // Dock
              graphicsObj.setColor(new Color(81, 47, 13));
              graphicsObj.fill(new Rectangle(x + 5, 70, 60, 5));
              // Pole1
              graphicsObj.setColor(new Color(101, 67, 33));
              graphicsObj.fill(new Rectangle(x, 65, 5, 15));
              // Pole2
              graphicsObj.setColor(new Color(101, 67, 33));
              graphicsObj.fill(new Rectangle(x + 10, 65, 5, 15));
              // Pole3
              graphicsObj.setColor(new Color(101, 67, 33));
              graphicsObj.fill(new Rectangle(x + 20, 65, 5, 15));
              // Pole4
              graphicsObj.setColor(new Color(101, 67, 33));
              graphicsObj.fill(new Rectangle(x + 30, 65, 5, 15));
              // Pole5
              graphicsObj.setColor(new Color(101, 67, 33));
              graphicsObj.fill(new Rectangle(x + 40, 65, 5, 15));
          }
        }
        { // Beach Ball
          for (int i = 0; i < NUM_BBALLS; i++) {
            x = bBallArray[i].getX();
            y = bBallArray[i].getY();
            graphicsObj.setColor(new Color(200, 50, 50));
            graphicsObj.fill(new Double(x, y, 15, 15));
          }
        }
        { // Beach Towels (and Umbrella)
          for (int i = 0; i < NUM_BTOWELS; i++) {
            x = bTowelsArray[i].getX();
            y = bTowelsArray[i].getY();
              // Towel1
              graphicsObj.setColor(new Color(200, 47, 13));
              graphicsObj.fill(new Rectangle(x, y + 2, 10, 15));
              // Towel2
              graphicsObj.setColor(new Color(47, 170, 33));
              graphicsObj.fill(new Rectangle(x + 14, y, 10, 15));
              // Towel3
              graphicsObj.setColor(new Color(13, 67, 150));
              graphicsObj.fill(new Rectangle(x + 28, y + 3, 10, 15));
              // Umbrella - handle
              graphicsObj.setColor(new Color(0, 0, 0));
              graphicsObj.fill(new Rectangle(x - 5, y - 15, 3, 20));
              // Umbrella - Cover
              graphicsObj.setColor(new Color(120, 0, 0));
              graphicsObj.fillArc(x - 20, y - 20, 30, 20, 0, 180);
          }
        }
        {  //Palm Trees
          for (int i = 0; i < NUM_PALMS; i++) {
            x = palmArray[i].getX();
            y = palmArray[i].getY();
              // Trunk
              xPoints = new int[] { x, x     , x + 5 , x + 10, x + 5 , x + 5 };
              yPoints = new int[] { y, y - 20, y - 40, y - 40, y - 20, y     };
              graphicsObj.setColor(new Color(101, 67, 33));
              graphicsObj.fill(new Polygon(xPoints, yPoints, 6));
            // Leaves
            x = palmArray[i].getX() + 5;
            y = palmArray[i].getY() - 40;
              // Leaf Color
              graphicsObj.setColor(new Color(0, 150, 20));
              // leaf1
              graphicsObj.fillArc(x, y - 6, 30, 20, 0, 180);
              graphicsObj.fillArc(x + 5, y - 6, 30, 20, -60, 180);
              // leaf2
              graphicsObj.fillArc(x - 30, y - 6, 30, 20, 0, 180);
              graphicsObj.fillArc(x - 35, y - 6, 30, 20, 60, 180);
          }

        }

        // Player Hitbox
        { // Player Hitbox/Surfboard
          x = player.getX();
          y = player.getY();
            // Surfboard
            ellipse = new Double(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
            graphicsObj.setColor(new Color(128, 0, 0));
            graphicsObj.fill(ellipse);
            ellipse = new Double(x + 15, y, 20, 10);
            playerHitBox = new Area(ellipse);
        }

        // Obstacles
        { // Sharks
          for (int i = 0; i < NUM_SHARKS; i++) {
            x = sharkArray[i].getX();
            y = sharkArray[i].getY();
              // Face - fin
              xPoints = new int[] { x + 40, (x + 40), x        };
              yPoints = new int[] { y - 25, y,        y - 25  };
              polygon = new Polygon(xPoints, yPoints, 3);
              graphicsObj.setColor(new Color(20, 20, 20));
              graphicsObj.fill(polygon);
              objectHitBox = new Area(polygon);
              // Face - dark
              xPoints = new int[] { x, (x + 45), x       };
              yPoints = new int[] { y, y,        y - 45  };
              polygon = new Polygon(xPoints, yPoints, 3);
              graphicsObj.setColor(new Color(50, 50, 50));
              graphicsObj.fill(polygon);
              objectHitBox.add(new Area(polygon));
              // Face - light
              xPoints = new int[] { x, (x + 35), x       };
              yPoints = new int[] { y, y,        y - 35  };
              graphicsObj.setColor(new Color(150, 150, 150));
              graphicsObj.fill(new Polygon(xPoints, yPoints, 3));
              // Face - mouth
              xPoints = new int[] { x + 2, (x + 20), x + 2  };
              yPoints = new int[] { y - 2, y - 2,    y - 25 };
              graphicsObj.setColor(new Color(120, 20, 20));
              graphicsObj.fill(new Polygon(xPoints, yPoints, 3));
              // Eyeball - dark
              graphicsObj.setColor(new Color(0, 0, 0));
              graphicsObj.fill(new Double(x + 10, y - 25, 10, 10));
              // Eyeball - light
              graphicsObj.setColor(new Color(255, 255, 255));
              graphicsObj.fill(new Double(x + 12, y - 23, 2, 2));
              // Collision Details
              objectHitBox.intersect(playerHitBox);
              if (!objectHitBox.isEmpty()) objCollide = true;
              // height = 50
          }
        }
        { // Rocks
          for (int i = 0; i < NUM_ROCKS; i++) {
            // Rocks1 = rock1
            x = rockArray[i].getX() + 10;
            y = rockArray[i].getY() - 15;
            xPoints = new int[] { x, x,        (x + 5),  (x + 25), (x + 30), (x + 30), x };
            yPoints = new int[] { y, (y - 20), (y - 25), (y - 25), (y - 20), y,        y };
            polygon = new Polygon(xPoints, yPoints, 7);
            graphicsObj.setColor(new Color(30, 30, 30));
            graphicsObj.fill(polygon);
            objectHitBox = new Area(polygon);
            // Rocks1 = rock2
            x = rockArray[i].getX() + 20;
            y = rockArray[i].getY() - 5;
            xPoints = new int[] { x, x,        (x + 5),  (x + 25), (x + 30), (x + 30), x };
            yPoints = new int[] { y, (y - 20), (y - 25), (y - 25), (y - 20), y,        y };
            polygon = new Polygon(xPoints, yPoints, 7);
            graphicsObj.setColor(new Color(50, 50, 50));
            graphicsObj.fill(polygon);
            objectHitBox.add(new Area(polygon));
            // Rocks1 = rock3
            x = rockArray[i].getX();
            y = rockArray[i].getY();
            xPoints = new int[] { x, x,        (x + 5),  (x + 25), (x + 30), (x + 30), x };
            yPoints = new int[] { y, (y - 20), (y - 25), (y - 25), (y - 20), y,        y };
            polygon = new Polygon(xPoints, yPoints, 7);
            graphicsObj.setColor(new Color(70, 70, 70));
            graphicsObj.fill(polygon);
            objectHitBox.add(new Area(polygon));
            // Collisison Details
            objectHitBox.intersect(playerHitBox);
            if (!objectHitBox.isEmpty()) objCollide = true;
            // height = 50
          }
        }
        { // Squids
          for (int i = 0; i < NUM_SQUIDS; i++) {
            x = squidArray[i].getX();
            y = squidArray[i].getY();
              // Head - back layer
              xPoints = new int[] { x + 2 , x + 25, x + 48, x + 25 };
              yPoints = new int[] { y - 20, y - 50, y - 20, y };
              polygon = new Polygon(xPoints, yPoints, 4);
              graphicsObj.setColor(new Color(80, 80, 80));
              objectHitBox.add(new Area(polygon));
              // Body
              graphicsObj.fill(polygon);
              graphicsObj.setColor(new Color(200, 200, 200));
              graphicsObj.fill(new Rectangle(x + 15, y - 10, 20, 20));
              // Head - front layer (square portion)
              graphicsObj.setColor(new Color(150, 150, 150));
              graphicsObj.fill(new Rectangle(x + 12, y - 25, 26, 20));
              // Head - top layer
              xPoints = new int[] { x + 12 , x + 25, x + 38  };
              yPoints = new int[] { y - 25, y - 40, y - 25  };
              polygon = new Polygon(xPoints, yPoints, 3);
              graphicsObj.setColor(new Color(150, 150, 150));
              graphicsObj.fill(polygon);
              // Eyes - Black Part
              graphicsObj.setColor(new Color(0, 0, 0));
              graphicsObj.fill(new Double(x + 12, y - 4, 10, 10));
              graphicsObj.fill(new Double(x + 28, y - 4, 10, 10));
              objectHitBox.add(new Area(new Double(x + 12, y - 4, 10, 10)));
              objectHitBox.add(new Area(new Double(x + 28, y - 4, 10, 10)));
              // Eyes - White Part
              graphicsObj.setColor(new Color(255, 255, 255));
              graphicsObj.fill(new Double(x + 13, y - 3, 5, 5));
              graphicsObj.fill(new Double(x + 29, y - 3, 5, 5));

              objectHitBox.intersect(playerHitBox);
              if (!objectHitBox.isEmpty()) objCollide = true;
          }
        }

        // Player and UI
        { // Player (Stick Figure)
          x = player.getX() - 2;
          y = player.getY() - 2;
            // Head
            graphicsObj.setColor(new Color(0, 0, 0));
            graphicsObj.fill(new Double(x + 15, y - 30, 15, 15));
            // Body
            graphicsObj.setColor(new Color(0, 0, 0));
            graphicsObj.fill(new Rectangle(x + 20, y - 20, 5, 20));
            // Arms
            graphicsObj.setColor(new Color(0, 0, 0));
            graphicsObj.fill(new Rectangle(x + 10, y - 14, 25, 4));
            // Legs
            // Bottom of body: x(20-25) + y(y)
            // Points:             bottom,   bLeft,      left,  left top, right top, right,    bRight,    bottom
            xPoints = new int[] { (x + 22), (x + 20), (x + 17), (x + 20), (x + 25), (x + 28), (x + 25), (x + 23) };
            yPoints = new int[] { (y + 5),  (y + 10), (y + 8),  (y - 5),  (y - 05), (y + 8),  (y + 10), (y + 05) };
            graphicsObj.setColor(new Color(0, 0, 0));
            graphicsObj.fill(new Polygon(xPoints, yPoints, 8));
        }
        { // Score Text
          graphicsObj.setColor(new Color(0, 0, 0));
          graphicsObj.setFont(new Font("Courier", Font.BOLD,25));
          g.drawString("Score: " + playerScore, 0, 20);
        }
        { // Black Out Sensor
          if (isBlackOut) {
            // Black Screen
            graphicsObj.setColor(new Color(0, 0, 0));
            graphicsObj.fill(new Rectangle(0, 0, 800, 400));
            // Final Score Text
            graphicsObj.setColor(new Color(255, 255, 255));
            graphicsObj.setFont(new Font("Courier", Font.BOLD,100));
            g.drawString("Score: " + playerScore, 0, 200);
          }
        }
      }
   }
   // Short Methods
   // Gets collision state of the player
   public boolean getObjCollide() { return objCollide; }
   // Gets the instructions on the screen
   public boolean getInstruction() { return isInstruction; }
   // Sets the player score
   public void setPlayerScore(int playerScore) { this.playerScore = playerScore; }
   // Sets the collision state of the player
   public void setObjCollide(boolean option) { objCollide = option; }
   // Sets the blackout (gameover) of the screen
   public void setBlackOut(boolean option) { isBlackOut = option; }
   // Sets the instructions on the screen
   public void setInstruction(boolean option) { isInstruction = option; }
}
