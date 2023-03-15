package controller.commands;

import model.Image;
import model.ImageProcessingModel;

public class HorizontalFlip implements ImageCommandController {
  String sourceImageName;
  String destImageName;

  public HorizontalFlip(String sourceImageName, String destImageName) {
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return m.horizontalFlip(sourceImageName, destImageName);
  }
}
