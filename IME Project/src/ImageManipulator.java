import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import controller.ImageMVCController;
import controller.ImageMVCControllerImpl;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImprovedImageProcessing;
import model.ImprovedImageProcessingImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

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
    if (args.length == 2 && args[0].equalsIgnoreCase("-file")) {
      try {
        InputStream in = new FileInputStream(args[1]);
        ImageProcessingController controller = new ImageProcessingControllerImpl(model, in,
                System.out);
        controller.execute();
      } catch (FileNotFoundException e) {
        System.out.println("File Not Found");
      }
    }
    else if(args.length == 1 && args[0].equalsIgnoreCase("-text")){
      InputStream in = System.in;
      ImageProcessingController controller = new ImageProcessingControllerImpl(model, in,
              System.out);
      controller.execute();
    }
    else {
      ImageProcessingView view = new ImageProcessingViewImpl();
      ImageMVCController controller = new ImageMVCControllerImpl(model, view);
      controller.execute();
    }
  }
}
