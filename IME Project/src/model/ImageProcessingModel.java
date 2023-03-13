package model;

import java.io.IOException;

public interface ImageProcessingModel {
  ImageProcessingModel loadImage(String imagePath);

  void saveImage(String imagePath) throws IOException;

  ImageProcessingModel greyscaleRedComponent();

  ImageProcessingModel greyscaleGreenComponent();

  ImageProcessingModel greyscaleBlueComponent();

  ImageProcessingModel greyscaleValueComponent();

  ImageProcessingModel greyscaleIntensityComponent();

  ImageProcessingModel greyscaleLumaComponent();

  ImageProcessingModel horizontalFlip();

  ImageProcessingModel verticalFlip();

  ImageProcessingModel brighten(int increment);

  ImageProcessingModel[] rgbSplit();

  ImageProcessingModel rgbCombine(ImageProcessingModel redImage,
                                  ImageProcessingModel greenImage, ImageProcessingModel blueImage);

  Pixel[][] getPixels();

  int getWidth();

  int getHeight();

  int getMaxValue();
}
