package controller.commands;

import controller.CommandController;
import controller.IController;
import model.ImageProcessingModel;

public class BrightenImage implements IController {
  int increment;
  String destImage;

  public BrightenImage(int increment, String destImage) {
    this.destImage = destImage;
    this.increment = increment;
  }

  @Override
  public void go(ImageProcessingModel m) {
    ImageProcessingModel resultImage = m.brighten(increment);
    CommandController.listOfImages.put(destImage, resultImage);
  }
}
