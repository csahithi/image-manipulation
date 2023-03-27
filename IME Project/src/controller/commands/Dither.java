package controller.commands;

import model.Image;
import model.ImprovedImageProcessing;

public class Dither implements ImageCommandController {
  String sourceImageName;
  String destImageName;

  public Dither(String sourceImageName, String destImageName) {
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImprovedImageProcessing m) {
    return m.dither(sourceImageName, destImageName);
  }
}
