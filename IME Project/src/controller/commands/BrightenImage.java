package controller.commands;

import model.Image;
import model.ImageProcessingModel;

public class BrightenImage implements ImageCommandController {
  int increment;
  String sourceImageName;
  String destImageName;

  public BrightenImage(int increment, String sourceImageName, String destImageName) {
    this.increment = increment;
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return m.brighten(increment, sourceImageName, destImageName);
  }
}
