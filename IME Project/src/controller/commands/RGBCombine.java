package controller.commands;


import controller.IController;
import model.ImageProcessingModel;

public class RGBCombine implements IController {

  ImageProcessingModel destImage;
  ImageProcessingModel redImage;

  ImageProcessingModel greenImage;
  ImageProcessingModel blueImage;

  public RGBCombine(ImageProcessingModel destImage,ImageProcessingModel redImage,
                    ImageProcessingModel greenImage,ImageProcessingModel blueImage){
    this.blueImage=blueImage;
    this.destImage=destImage;
    this.greenImage=greenImage;
    this.redImage=redImage;
  }


  @Override
  public void go(ImageProcessingModel m) {
    m.rgbCombine(destImage,redImage,greenImage,blueImage);
  }
}
