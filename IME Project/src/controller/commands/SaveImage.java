package controller.commands;

import java.io.IOException;

import controller.IController;
import model.ImageProcessingModel;

public class SaveImage implements IController {
  String imagePath;

  public SaveImage(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public void go(ImageProcessingModel m) throws IOException {
    m.saveImage(imagePath);
  }
}
