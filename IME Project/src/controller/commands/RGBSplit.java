package controller.commands;


import controller.IController;
import model.ImageProcessingModel;

public class RGBSplit implements IController {

  ImageProcessingModel redImage;

  ImageProcessingModel greenImage;
  ImageProcessingModel blueImage;

  public RGBSplit(ImageProcessingModel redImage,ImageProcessingModel greenImage,
                  ImageProcessingModel blueImage){
    this.blueImage=blueImage;
    this.greenImage=greenImage;
    this.redImage=redImage;
  }


  @Override
  public void go(ImageProcessingModel m) {
    m.rgbSplit(redImage,greenImage,blueImage);
  }
}
