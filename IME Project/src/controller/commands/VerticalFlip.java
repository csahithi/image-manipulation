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
    ImageProcessingModel destImageModel = m;
    m.verticalFlip(destImageModel);
    CommandController.listOfImages.put(destImage, destImageModel);
  }

}