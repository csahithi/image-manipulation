package controller.commands;

import controller.CommandController;
import controller.IController;
import model.ImageProcessingModel;

public class LoadImage implements IController {
  String imagePath;
  String destImage;

  public LoadImage(String imagePath, String destImage) {
    this.imagePath = imagePath;
    this.destImage = destImage;
  }

  @Override
  public void go(ImageProcessingModel m) {
    CommandController.listOfImages.put(destImage, m.loadImage(imagePath));
  }
}
