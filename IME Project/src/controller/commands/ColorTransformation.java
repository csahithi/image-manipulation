package controller.commands;

import model.Image;
import model.ImprovedImageProcessing;

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
  public Image execute(ImprovedImageProcessing m) {
    return m.colorTransformation(operation, sourceImageName, destImageName);
  }

}
