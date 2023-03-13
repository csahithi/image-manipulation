package controller.commands;

import controller.CommandController;
import controller.IController;
import model.ImageProcessingModel;

public class HorizontalFlip implements IController {
  String destImage;

  public HorizontalFlip(String destImage) {
    this.destImage = destImage;
  }

  @Override
  public void go(ImageProcessingModel m) {
    ImageProcessingModel resultImage = m.horizontalFlip();
    CommandController.listOfImages.put(destImage, resultImage);
  }
}
