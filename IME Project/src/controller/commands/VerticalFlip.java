package controller.commands;

import model.Image;
import model.ImageProcessingModel;


public class VerticalFlip implements ImageCommandController {
  String sourceImageName;
  String destImageName;

  public VerticalFlip(String sourceImageName, String destImageName) {
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return m.verticalFlip(sourceImageName, destImageName);
  }

}