package controller.commands;

import model.Image;
import model.ImageProcessingModel;

public class Dither implements ImageCommandController {
  String sourceImageName;
  String destImageName;

  public Dither(String sourceImageName, String destImageName) {
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return null;
  }
}
