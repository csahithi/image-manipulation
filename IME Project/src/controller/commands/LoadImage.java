package controller.commands;

import model.Image;
import model.ImageProcessingModel;

public class LoadImage implements ImageCommandController {
  String imagePath;
  String destImageName;

  public LoadImage(String imagePath, String destImageName) {
    this.imagePath = imagePath;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return m.loadImage(imagePath, destImageName);
  }
}
