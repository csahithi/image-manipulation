package controller.commands;


import controller.CommandController;
import controller.IController;
import model.ImageProcessingModel;

public class RGBCombine implements IController {
  String destImage;
  ImageProcessingModel redImage;
  ImageProcessingModel greenImage;
  ImageProcessingModel blueImage;

  public RGBCombine(String destImage, ImageProcessingModel redImage,
                    ImageProcessingModel greenImage, ImageProcessingModel blueImage) {
    this.blueImage = blueImage;
    this.destImage = destImage;
    this.greenImage = greenImage;
    this.redImage = redImage;
  }

  @Override
  public void go(ImageProcessingModel m) {
    ImageProcessingModel resultImage = m.rgbCombine(redImage, greenImage, blueImage);
    CommandController.listOfImages.put(destImage, resultImage);
  }
}
