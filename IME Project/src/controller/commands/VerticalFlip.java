package controller.commands;

import controller.IController;
import model.ImageProcessingModel;


public class VerticalFlip implements IController {
  ImageProcessingModel destImage;

  public VerticalFlip(ImageProcessingModel destImage){
    this.destImage=destImage;
  }

  @Override
  public void go(ImageProcessingModel m) {
    m.verticalFlip(destImage);
  }

}