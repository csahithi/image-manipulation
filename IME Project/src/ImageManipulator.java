import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImprovedImageProcessing;
import model.ImprovedImageProcessingImpl;

/**
 * The entry point of the program where we can create Image Processing Model and
 * run some operations on it.
 */

public class ImageManipulator {
  /**
   * Main method that starts the application.
   *
   * @param args accepts a single argument of type array.
   */
  public static void main(String[] args) {
    ImprovedImageProcessing model = new ImprovedImageProcessingImpl();
    InputStream in;
    if(args.length==2 && args[0].equalsIgnoreCase("-file")){
      try{
        in = new FileInputStream(args[1]);
      }
      catch (FileNotFoundException e){
        System.out.println("File Not Found");
        return;
      }
    }
    else{
      in = System.in;
    }
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in,
            System.out);
    controller.execute();

  }
}
