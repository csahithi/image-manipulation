package model;

import java.io.IOException;

public interface  ImageProcessingModel {

  ImageProcessingModel loadImage(String imagePath);
  void saveImage(String imagePath) throws IOException;
  void greyscaleRedComponent(ImageProcessingModel destImage);
  void greyscaleGreenComponent(ImageProcessingModel destImage);
  void greyscaleBlueComponent(ImageProcessingModel destImage);
  void greyscaleValueComponent(ImageProcessingModel destImage);
  void greyscaleIntensityComponent(ImageProcessingModel destImage);
  void greyscaleLumaComponent(ImageProcessingModel destImage);
  void horizontalFlip(ImageProcessingModel destImage);
  void verticalFlip(ImageProcessingModel destImage);
  void brighten(int increment, ImageProcessingModel destImage);
  void rgbSplit(ImageProcessingModel redDestImage, ImageProcessingModel greenDestImage,
                ImageProcessingModel blueDestImage);
  void rgbCombine(ImageProcessingModel destImage, ImageProcessingModel redImage,
                  ImageProcessingModel greenImage,ImageProcessingModel blueImage);
  void setPixels(Pixel[][] listOfPixels);


  Pixel[][] getPixels();

  int getWidth();
  int getHeight();
  int getMaxValue();
  void RunScriptFile(String filePath);
}
