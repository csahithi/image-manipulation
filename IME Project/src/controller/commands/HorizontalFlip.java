package controller.commands;

import controller.IController;
import model.ImageProcessingModel;

public class HorizontalFlip implements IController {
  ImageProcessingModel destImage;

  public HorizontalFlip(ImageProcessingModel destImage){
    this.destImage=destImage;
  }

  @Override
  public void go(ImageProcessingModel m) {
    m.horizontalFlip(destImage);
  }

}
