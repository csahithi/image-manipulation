package controller.commands;

import java.io.IOException;

import model.Image;
import model.ImageProcessingModel;

public class SaveImage implements ImageCommandController {
  String imagePath;
  String imageName;

  public SaveImage(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public Image go(ImageProcessingModel m) throws IOException {
    return m.saveImage(imagePath, imageName);
  }
}
