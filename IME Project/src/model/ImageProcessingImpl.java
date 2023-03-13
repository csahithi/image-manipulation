package model;

import java.io.IOException;

public class ImageProcessingImpl implements ImageProcessingModel {
  private int width;
  private int height;
  private int maxValueOfColor;
  private Pixel[][] listOfPixels;

  public ImageProcessingImpl(int width, int height, int maxValueOfColor, Pixel[][] listOfPixels) {
    this.width = width;
    this.height = height;
    this.maxValueOfColor = maxValueOfColor;
    this.listOfPixels = listOfPixels;
  }

  @Override
  public ImageProcessingModel loadImage(String imagePath) {
    return ReadPPMUtil.readPPM(imagePath);
  }

  @Override
  public void saveImage(String imagePath) throws IOException {
    CreatePPMUtil.createPPM(imagePath, this);
  }

  @Override
  public void greyscaleRedComponent(ImageProcessingModel destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].colorComponent.redComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].colorComponent.redComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void greyscaleGreenComponent(ImageProcessingModel destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = listOfPixelsDestImage[i][j].colorComponent.greenComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].colorComponent.greenComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void greyscaleBlueComponent(ImageProcessingModel destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = listOfPixelsDestImage[i][j].colorComponent.blueComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].colorComponent.blueComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void greyscaleValueComponent(ImageProcessingModel destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = listOfPixelsDestImage[i][j].valueComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].valueComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].valueComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void greyscaleIntensityComponent(ImageProcessingModel destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = (int) listOfPixelsDestImage[i][j].intensityComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = (int) listOfPixelsDestImage[i][j].intensityComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = (int) listOfPixelsDestImage[i][j].intensityComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void greyscaleLumaComponent(ImageProcessingModel destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = (int) listOfPixelsDestImage[i][j].lumaComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = (int) listOfPixelsDestImage[i][j].lumaComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = (int) listOfPixelsDestImage[i][j].lumaComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void horizontalFlip(ImageProcessingModel destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    Pixel t;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width / 2; j++) {
        t = listOfPixelsDestImage[i][j];
        listOfPixelsDestImage[i][j] = listOfPixelsDestImage[i][this.width - j - 1];
        listOfPixelsDestImage[i][this.width - j - 1] = t;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void verticalFlip(ImageProcessingModel destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    Pixel t;
    for (int i = 0; i < this.height / 2; i++) {
      for (int j = 0; j < this.width; j++) {
        t = listOfPixelsDestImage[i][j];
        listOfPixelsDestImage[i][j] = listOfPixelsDestImage[this.height - i - 1][j];
        listOfPixelsDestImage[this.height - i - 1][j] = t;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void brighten(int increment, ImageProcessingModel destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (listOfPixelsDestImage[i][j].colorComponent.redComponent + increment > maxValueOfColor) {
          listOfPixelsDestImage[i][j].colorComponent.redComponent = maxValueOfColor;
        } else {
          listOfPixelsDestImage[i][j].colorComponent.redComponent += increment;
        }
        if (listOfPixelsDestImage[i][j].colorComponent.greenComponent + increment > maxValueOfColor) {
          listOfPixelsDestImage[i][j].colorComponent.greenComponent = maxValueOfColor;
        } else {
          listOfPixelsDestImage[i][j].colorComponent.greenComponent += increment;
        }
        if (listOfPixelsDestImage[i][j].colorComponent.blueComponent + increment > maxValueOfColor) {
          listOfPixelsDestImage[i][j].colorComponent.blueComponent = maxValueOfColor;
        } else {
          listOfPixelsDestImage[i][j].colorComponent.blueComponent += increment;
        }
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void rgbSplit(ImageProcessingModel redDestImage, ImageProcessingModel greenDestImage,
                       ImageProcessingModel blueDestImage) {
    Pixel[][] listOfPixelsRedDestImage = this.listOfPixels;
    Pixel[][] listOfPixelsGreenDestImage = this.listOfPixels;
    Pixel[][] listOfPixelsBlueDestImage = this.listOfPixels;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsRedDestImage[i][j].colorComponent.greenComponent =
                listOfPixelsRedDestImage[i][j].colorComponent.redComponent;
        listOfPixelsRedDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsRedDestImage[i][j].colorComponent.redComponent;
        listOfPixelsGreenDestImage[i][j].colorComponent.redComponent =
                listOfPixelsGreenDestImage[i][j].colorComponent.greenComponent;
        listOfPixelsGreenDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsGreenDestImage[i][j].colorComponent.greenComponent;
        listOfPixelsBlueDestImage[i][j].colorComponent.greenComponent =
                listOfPixelsBlueDestImage[i][j].colorComponent.blueComponent;
        listOfPixelsBlueDestImage[i][j].colorComponent.redComponent
                = listOfPixelsBlueDestImage[i][j].colorComponent.blueComponent;
      }
    }
    redDestImage.setPixels(listOfPixelsRedDestImage);
    greenDestImage.setPixels(listOfPixelsGreenDestImage);
    blueDestImage.setPixels(listOfPixelsBlueDestImage);
  }

  @Override
  public void rgbCombine(ImageProcessingModel destImage, ImageProcessingModel redImage,
                         ImageProcessingModel greenImage, ImageProcessingModel blueImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = redImage.getPixels()[i][j].colorComponent.redComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = greenImage.getPixels()[i][j].colorComponent.greenComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = blueImage.getPixels()[i][j].colorComponent.blueComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }


  @Override
  public void setPixels(Pixel[][] listOfPixels) {
    this.listOfPixels = listOfPixels;
  }

  @Override
  public Pixel[][] getPixels() {
    return this.listOfPixels;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getMaxValue() {
    return maxValueOfColor;
  }
}
