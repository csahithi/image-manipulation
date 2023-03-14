import java.io.IOException;

import controller.ImageProcessingControllerImpl;
import controller.ImageProcessingController;
import model.ImageProcessingModelImpl;
import model.ImageProcessingModel;

public class ImageManipulator {
  public static void main(String[] args) throws IOException {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, System.in, System.out);
    controller.go();
  }
}
