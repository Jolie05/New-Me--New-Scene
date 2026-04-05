import org.code.theater.*;
import org.code.media.*;

public class ThisYear extends Scene {

  // Variables (instance & static)
  private String text;
  
  // Constuctor
  public ThisYear(String text) {
    this.text = text;
  }

/*
 * Top level drawScene method
 */
  public void drawScene() {
    drawBanner(300,22);
    drawEnding();
  }

  public void drawEnding(){
    setFillColor("white");
    drawRectangle(0, 0, 400, 400);
    drawImage("sunset.png", 0,0,400);
    drawRectangle(60, 155, 265, 70);
    setTextStyle(Font.SANS, FontStyle.BOLD);
    setTextHeight(30);
    drawText("THANK YOU!", 95, 200);
  }

// other methods
/*
 * Draws text coming in from right side of screen to left, 
 * Text should all leave screen based on calculator of help method
 */
  public void drawBanner(int yPos, int textSize) {
    setTextHeight(textSize);

    int xPos = 400; // start on right side of screen
    int textWidth = getEstimateLengthInPixels(text, textSize);

    while (xPos > -textWidth) {
      clear("black"); // can change to different color or image
      drawImage("sunset.png", 0, 0, 400);
      setFillColor("white");
      drawRectangle(80, 60, 260, 55);
      setTextStyle(Font.SERIF, FontStyle.BOLD);
      drawText("Goals for 2026:", 135, 90);
      drawText(text, xPos, yPos);
      pause(0.1); // this is the fastest it can be, can slow down
      
      xPos -= 10; // shift over by 10px, can change
    }
  }

/*
 * Helper method, estimates the with of text in pixels
 */
  public static int getEstimateLengthInPixels(String text, int textSize) {
    // the average width per character at size 22 is ~15px, which is about 70%
    double avgWidthPerChar = 0.7 * textSize;
    // add 15% buffer to overestimate
    return (int) (text.length() * avgWidthPerChar * 1.15);
  }
}