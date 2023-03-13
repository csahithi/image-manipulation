package controller.commands;

import controller.CommandController;
import controller.IController;
import model.ImageProcessingModel;


public class VerticalFlip implements IController {
  String destImage;

  public VerticalFlip(String destImage) {
    this.destImage = destImage;
  }

  @Override
  public void go(ImageProcessingModel m) {
    ImageProcessingModel resultImage = m.verticalFlip();
    CommandController.listOfImages.put(destImage, resultImage);
  }

}