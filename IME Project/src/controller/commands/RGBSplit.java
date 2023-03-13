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
    ImageProcessingModel[] rgbSplitModels=m.rgbSplit();
    CommandController.listOfImages.put(redImage, rgbSplitModels[0]);
    CommandController.listOfImages.put(greenImage, rgbSplitModels[1]);
    CommandController.listOfImages.put(blueImage, rgbSplitModels[2]);
  }
}
