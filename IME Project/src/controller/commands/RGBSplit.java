package controller.commands;

import model.Image;
import model.ImageProcessingModel;

public class RGBSplit implements ImageCommandController {
  String sourceImageName;
  String redImage;
  String greenImage;
  String blueImage;

  public RGBSplit(String sourceImageName, String redImage, String greenImage, String blueImage) {
    this.sourceImageName = sourceImageName;
    this.redImage = redImage;
    this.greenImage = greenImage;
    this.blueImage = blueImage;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return m.rgbSplit(sourceImageName, redImage, greenImage, blueImage);
  }
}
