import java.io.IOException;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;

public class ImageManipulator {
  public static void main(String[] args) throws IOException {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, System.in, System.out);
    controller.execute();
  }
}
