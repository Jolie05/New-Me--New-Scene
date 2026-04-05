import org.code.theater.*;

public class TheaterRunner {
  public static void main(String[] args) {

    // List of images read from text file, names match in assets
    String[] stackImages = FileReader.toStringArray("family.txt");
    String[] tileImages = FileReader.toStringArray("friends.txt");
    String text = "1. Good Grades.  2. Drive.  3. Going out more.";
    
    // Create scene objects
    LastYear lastYear = new LastYear(stackImages, tileImages);
    ThisYear thisYear = new ThisYear(text);

    // Call top level methods
    lastYear.drawScene();
    thisYear.drawScene();

    // Play scenes (in order of arguments)
    Scene[] myScenes = {lastYear, thisYear};
    Theater.play(myScenes);

  }
}