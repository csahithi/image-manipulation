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
    ImageProcessingModel resultImage = null;
    switch (factor) {
      case "red-component":
        resultImage = m.greyscaleRedComponent();
        break;
      case "green-component":
        resultImage = m.greyscaleGreenComponent();
        break;
      case "blue-component":
        resultImage = m.greyscaleBlueComponent();
        break;
      case "value-component":
        resultImage = m.greyscaleValueComponent();
        break;
      case "intensity-component":
        resultImage = m.greyscaleIntensityComponent();
        break;
      case "luma-component":
        resultImage = m.greyscaleLumaComponent();
        break;
      default:
        System.out.println(String.format("Unknown GreyScale Component %s", factor));
        return;
    }
    CommandController.listOfImages.put(destImage, resultImage);
  }
}
