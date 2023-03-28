import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
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
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, System.in,
            System.out);
    controller.execute();

  }
}
