package controller.commands;


import controller.CommandController;
import controller.IController;
import model.ImageProcessingModel;

public class RGBSplit implements IController {
  String redImage;
  String greenImage;
  String blueImage;

  public RGBSplit(String redImage, String greenImage, String blueImage) {
    this.redImage = redImage;
    this.greenImage = greenImage;
    this.blueImage = blueImage;
  }

  @Override
  public void go(ImageProcessingModel m) {
    ImageProcessingModel redImageModel = m;
    ImageProcessingModel greenImageModel = m;
    ImageProcessingModel blueImageModel = m;
    m.rgbSplit(redImageModel, greenImageModel, blueImageModel);
    CommandController.listOfImages.put(redImage, redImageModel);
    CommandController.listOfImages.put(greenImage, greenImageModel);
    CommandController.listOfImages.put(blueImage, blueImageModel);
  }
}
