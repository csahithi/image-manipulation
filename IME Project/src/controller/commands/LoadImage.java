package controller.commands;

import controller.IController;
import model.ImageProcessingModel;

public class LoadImage implements IController {

  String imagePath;
  public LoadImage(String imagePath){
    this.imagePath=imagePath;
  }

  @Override
  public void go(ImageProcessingModel m) {m.loadImage(imagePath);}
}
