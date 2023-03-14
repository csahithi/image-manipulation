package model;

import java.io.IOException;

public interface ImageProcessingModel {
  Image loadImage(String imagePath, String imageName);

  Image saveImage(String imagePath, String imageName) throws IOException;

  Image greyscale(String component, String sourceImageName, String destImageName);

//  Image greyscaleRedComponent(String sourceImageName, String destImageName);
//
//  Image greyscaleGreenComponent(String sourceImageName, String destImageName);
//
//  Image greyscaleBlueComponent(String sourceImageName, String destImageName);
//
//  Image greyscaleValueComponent(String sourceImageName, String destImageName);
//
//  Image greyscaleIntensityComponent(String sourceImageName, String destImageName);
//
//  Image greyscaleLumaComponent(String sourceImageName, String destImageName);

  Image horizontalFlip(String sourceImageName, String destImageName);

  Image verticalFlip(String sourceImageName, String destImageName);

  Image brighten(int increment, String sourceImageName, String destImageName);

  Image rgbSplit(String sourceImageName, String redImageName, String greenImageName, String blueImageName);

  Image rgbCombine(String destImageName, String redImageName, String greenImageName, String blueImageName);
}
