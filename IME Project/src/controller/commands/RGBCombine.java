package controller.commands;

import model.Image;
import model.ImageProcessingModel;

public class RGBCombine implements ImageCommandController {
  String destImageName;
  String redImage;
  String greenImage;
  String blueImage;

  public RGBCombine(String destImageName, String redImage, String greenImage, String blueImage) {
    this.blueImage = blueImage;
    this.destImageName = destImageName;
    this.greenImage = greenImage;
    this.redImage = redImage;
  }

  @Override
  public Image go(ImageProcessingModel m) {
    return m.rgbCombine(destImageName, redImage, greenImage, blueImage);
  }
}
