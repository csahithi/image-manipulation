package model;

public interface ImageProcessingModel {

  void loadImage(String imagePath, ImageProcessingImpl destImage);
  void saveImage(String imagePath);
  void greyscaleRedComponent(ImageProcessingImpl destImage);
  void greyscaleGreenComponent(ImageProcessingImpl destImage);
  void greyscaleBlueComponent(ImageProcessingImpl destImage);
  void greyscaleValueComponent(ImageProcessingImpl destImage);
  void greyscaleIntensityComponent(ImageProcessingImpl destImage);
  void greyscaleLumaComponent(ImageProcessingImpl destImage);
  void horizontalFlip(ImageProcessingImpl destImage);
  void verticalFlip(ImageProcessingImpl destImage);
  void brighten(int increment, ImageProcessingImpl destImage);
  void rgbSplit(ImageProcessingImpl redDestImage, ImageProcessingImpl greenDestImage,
                ImageProcessingImpl blueDestImage);
  void rgbCombine(ImageProcessingImpl destImage, ImageProcessingImpl redImage,
                  ImageProcessingImpl greenImage,ImageProcessingImpl blueImage);
  void setPixels(Pixel[][] listOfPixels);
  Pixel[][] getPixels();
}
