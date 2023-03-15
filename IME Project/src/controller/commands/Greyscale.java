package controller.commands;

import model.Image;
import model.ImageProcessingModel;

public class Greyscale implements ImageCommandController {
  String component;
  String sourceImageName;
  String destImageName;

  public Greyscale(String component, String sourceImageName, String destImageName) {
    this.component = component;
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return m.greyscale(component, sourceImageName, destImageName);
  }
}
