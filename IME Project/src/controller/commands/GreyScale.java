package controller.commands;

import controller.CommandController;
import controller.IController;
import model.ImageProcessingModel;

public class GreyScale implements IController {
  String factor;
  String destImage;

  public GreyScale(String factor, String destImage) {
    this.factor = factor;
    this.destImage = destImage;
  }

  @Override
  public void go(ImageProcessingModel m) {
    ImageProcessingModel destImageModel = m;
    switch (factor) {
      case "red-component":
        m.greyscaleRedComponent(destImageModel);
      case "green-component":
        m.greyscaleGreenComponent(destImageModel);
      case "blue-component":
        m.greyscaleBlueComponent(destImageModel);
      case "value-component":
        m.greyscaleValueComponent(destImageModel);
      case "intensity-component":
        m.greyscaleIntensityComponent(destImageModel);
      case "luma-component":
        m.greyscaleLumaComponent(destImageModel);
    }
    CommandController.listOfImages.put(destImage, destImageModel);
  }
}
