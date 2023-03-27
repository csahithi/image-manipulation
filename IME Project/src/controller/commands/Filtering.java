package controller.commands;

import model.Image;
import model.ImprovedImageProcessing;


public class Filtering implements ImageCommandController {
  String operation;
  String sourceImageName;
  String destImageName;

  public Filtering(String operation, String sourceImageName, String destImageName) {
    this.operation = operation;
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImprovedImageProcessing m) {
    return m.filtering(operation, sourceImageName, destImageName);
  }
}
