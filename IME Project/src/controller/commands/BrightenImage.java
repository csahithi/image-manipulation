package controller.commands;

import controller.IController;
import model.ImageProcessingModel;

public class BrightenImage implements IController {
  int increment;
  ImageProcessingModel destImage;

  public BrightenImage(int increment,ImageProcessingModel destImage){
    this.destImage=destImage;
    this.increment=increment;
  }

  @Override
  public void go(ImageProcessingModel m) {
    m.brighten(increment,destImage);
  }


}
