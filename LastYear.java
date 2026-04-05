import org.code.theater.*;
import org.code.media.*;

public class LastYear extends Scene {

  // Variables (instance & static)
  //These variables are for the photostack
  private String[] stackImages; 
  private String[] titleImages; 
  private static final String blackBoarder = "blackSquare.png";

  // Constuctor
  public LastYear(String[] stackImages, String[] titleImages) {
    this.stackImages = stackImages;
    this.titleImages = titleImages;
  }

  /**
   * Top level drawScene method
   */
  public void drawScene() {
    drawIntro();
    pause(2);
    drawFamily();
    pause(2);
    drawFriends();
    pause(2);
    clear("black");
  }

  public void drawIntro() {
    setFillColor("white");
    drawRectangle(0, 0, 400, 400);
    drawImage("sunset.png", 0,0,400);
    drawRectangle(60, 155, 265, 70);
    setTextStyle(Font.SANS, FontStyle.BOLD);
    setTextHeight(30);
    drawText("My 2025 Recap", 95, 200);

  }

  public void drawFamily(){
    clear("black");
    drawImage("sunset.png", 0, 0, 400);
    drawRectangle(70, 300, 260, 55);
    setTextStyle(Font.SERIF, FontStyle.BOLD);
    drawText("My Family!", 130, 340);
    drawStack(false);
    drawRectangle(70, 300, 260, 55);
    setTextStyle(Font.SERIF, FontStyle.BOLD);
    drawText("My Family!", 120, 340);
  }

  public void drawFriends(){
    clear("black");
    drawImage("sunset.png", 0, 0, 400);
    drawTiles(3, true);
    drawRectangle(70, 150, 260, 55);
    setTextStyle(Font.SERIF, FontStyle.BOLD);
    drawText("My Friends!", 135, 190);
  }
  
  // other methods
/*
 * Creates a "stacking" animations with random angle rotations
 * of the image.
 */
  public void drawStack(boolean hasBoarder) {
    int centerX = 200;  // center of the stack
    int centerY = 200;  // center of the stack
    int size = 250;
    
    for (String image : stackImages) {
      // choose a random degree to rotate (0-45)
      int angle = (int) (Math.random() * 46);
      // if odd angle, rotate other direction
      if (angle % 2 == 1) {
        angle = (-1) * angle;
      }
      double dAngle = (double) angle;
      
      // calculate position for center-based rotation
      // standard 2D rotation: 
      // newX = x * cos(angle) - y * sin(angle)
      // newY = x * sin(angle) + y * cos(angle)
      double radians = Math.toRadians(dAngle);
      double halfSize = size / 2.0;
      int adjustedX = (int)(centerX - (halfSize * Math.cos(radians) - halfSize * Math.sin(radians)));
      int adjustedY = (int)(centerY - (halfSize * Math.sin(radians) + halfSize * Math.cos(radians)));
      
      // black boarder - 5px offset all around
      if (hasBoarder) {
        double boarderHalfSize = (size + 10) / 2.0;
        int boarderX = (int)(centerX - (boarderHalfSize * Math.cos(radians) - boarderHalfSize * Math.sin(radians)));
        int boarderY = (int)(centerY - (boarderHalfSize * Math.sin(radians) + boarderHalfSize * Math.cos(radians)));
        drawImage(blackBoarder, boarderX, boarderY, size + 10, dAngle);
      }
      
      // image on top of black boarder (if drawn)
      drawImage(image, adjustedX, adjustedY, size, dAngle);
      pause(1);
    }
  }
  
/*
 * Creates a tile arrangement of the images
 * Best for images to be square in dimension
 * This method only works for 1x1, 2x2, 3x3, 
 * and 4x4 tiles paterns.
 *
 * If staggered is true, there will be a pause inbetween
 * drawing each tiled image
 */
  public void drawTiles(int size, boolean staggered) {
    if (size <= 0 || size > 5) {
      System.out.println("Only use size 1, 2, 3, or 4");
      return;
    }

    int tileSize = getTileSize(size);
    int width = getWidth();
    int height = getHeight();
    int imageIndex = 0;
    for (int y = 0; y < height; y += tileSize) {
      for (int x = 0; x < width; x += tileSize) {
        drawImage(titleImages[imageIndex], x, y, tileSize);
        
        if (staggered) {
          pause(0.5); // change time
        }
        
        imageIndex++;
      }
    }  
  }

/*
 * Determines the tile size of the image to draw
 * precondition: size > 0 && size < 5
 */
  public int getTileSize(int size) {
    if (size == 1) {
      return 400;
    } else if (size == 2) {
      return 200;
    } else if (size == 3) {
      return 134;
    } else {  // when size = 4
      return 100;
    }
  }
}