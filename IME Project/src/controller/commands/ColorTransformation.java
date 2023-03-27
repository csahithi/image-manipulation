package controller.commands;

import model.Image;
import model.ImageProcessingModel;

public class ColorTransformation implements ImageCommandController {
  String operation;
  String sourceImageName;
  String destImageName;

  public ColorTransformation(String operation, String sourceImageName, String destImageName) {
    this.operation = operation;
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return null;
  }
}
