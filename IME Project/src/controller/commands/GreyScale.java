package controller.commands;

import controller.IController;
import model.ImageProcessingModel;

public class GreyScale implements IController {
  String factor;
  ImageProcessingModel destImage;
  public GreyScale(String factor,ImageProcessingModel destImage){
    this.factor=factor;
    this.destImage=destImage;

  }

  @Override
  public void go(ImageProcessingModel m) {
    switch (factor) {
      case "red-component":m.greyscaleRedComponent(destImage);
      case "green-component":m.greyscaleGreenComponent(destImage);
      case "blue-component":m.greyscaleBlueComponent(destImage);
      case "value-component":m.greyscaleValueComponent(destImage);
      case "intensity-component":m.greyscaleIntensityComponent(destImage);
      case "luma-component":m.greyscaleLumaComponent(destImage);
    }
  }
}
