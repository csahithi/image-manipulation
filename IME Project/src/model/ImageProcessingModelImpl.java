package model;

import java.util.HashMap;
import java.util.Map;

public class ImageProcessingModelImpl implements ImageProcessingModel {
  public static Map<String, Image> listOfImages = new HashMap<>();

  @Override
  public Image loadImage(String imagePath, String imageName) {
    Image image = ReadPPMUtil.readPPM(imagePath);
    if (image == null) {
      return null;
    }
    listOfImages.put(imageName, image);
    return image;
  }

  @Override
  public Image saveImage(String imagePath, String imageName) {
    Image image = listOfImages.getOrDefault(imageName, null);
    if (image == null) {
      return null;
    }
    CreatePPMUtil.createPPM(imagePath, image);
    return image;
  }

  @Override
  public Image greyscale(String component, String sourceImageName, String destImageName) {
    Image sourceImage = listOfImages.getOrDefault(sourceImageName, null);
    if (sourceImage == null) {
      return null;
    }
    Image resultImage = null;
    switch (component) {
      case "red-component":
        resultImage = greyscaleRedComponent(sourceImage);
        break;
      case "green-component":
        resultImage = greyscaleGreenComponent(sourceImage);
        break;
      case "blue-component":
        resultImage = greyscaleBlueComponent(sourceImage);
        break;
      case "value-component":
        resultImage = greyscaleValueComponent(sourceImage);
        break;
      case "intensity-component":
        resultImage = greyscaleIntensityComponent(sourceImage);
        break;
      case "luma-component":
        resultImage = greyscaleLumaComponent(sourceImage);
        break;
      default:
        return null;
    }
    listOfImages.put(destImageName, resultImage);
    return resultImage;
  }

  @Override
  public Image horizontalFlip(String sourceImageName, String destImageName) {
    Image image = listOfImages.getOrDefault(sourceImageName, null);
    if (image == null) {
      return null;
    }
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j <= image.getWidth() / 2; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j] = image.getPixels()[i][image.getWidth() - j - 1];
        listOfPixelsDestImage[i][image.getWidth() - j - 1] = image.getPixels()[i][j];
      }
    }
    Image destImage = new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
    listOfImages.put(destImageName, destImage);
    return destImage;
  }

  @Override
  public Image verticalFlip(String sourceImageName, String destImageName) {
    Image image = listOfImages.getOrDefault(sourceImageName, null);
    if (image == null) {
      return null;
    }
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i <= image.getHeight() / 2; i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j] = image.getPixels()[image.getHeight() - i - 1][j];
        listOfPixelsDestImage[image.getHeight() - i - 1][j] = image.getPixels()[i][j];
      }
    }
    Image destImage = new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
    listOfImages.put(destImageName, destImage);
    return destImage;
  }

  @Override
  public Image brighten(int increment, String sourceImageName, String destImageName) {
    Image image = listOfImages.getOrDefault(sourceImageName, null);
    if (image == null) {
      return null;
    }
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent = Math.max(0, (Math.min(image.getPixels()[i][j]
                .colorComponent.redComponent + increment, 255)));
        listOfPixelsDestImage[i][j].colorComponent.greenComponent = Math.max(0, (Math.min(image.getPixels()[i][j]
                .colorComponent.greenComponent + increment, 255)));
        listOfPixelsDestImage[i][j].colorComponent.blueComponent = Math.max(0, (Math.min(image.getPixels()[i][j]
                .colorComponent.blueComponent + increment, 255)));
      }
    }
    Image destImage = new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
    listOfImages.put(destImageName, destImage);
    return destImage;
  }

  @Override
  public Image rgbSplit(String sourceImageName, String redImageName, String greenImageName,
                        String blueImageName) {
    Image image = listOfImages.getOrDefault(sourceImageName, null);
    if (image == null) {
      return null;
    }
    Image redDestImage = greyscaleRedComponent(image);
    Image greenDestImage = greyscaleGreenComponent(image);
    Image blueDestImage = greyscaleBlueComponent(image);
    listOfImages.put(redImageName, redDestImage);
    listOfImages.put(greenImageName, greenDestImage);
    listOfImages.put(blueImageName, blueDestImage);
    return redDestImage;
  }

  @Override
  public Image rgbCombine(String destImageName, String redImageName, String greenImageName,
                          String blueImageName) {
    Image redImage = listOfImages.getOrDefault(redImageName, null);
    Image greenImage = listOfImages.getOrDefault(greenImageName, null);
    Image blueImage = listOfImages.getOrDefault(blueImageName, null);
    if (redImage == null || greenImage == null || blueImage == null) {
      return null;
    }
    Pixel[][] listOfPixelsDestImage = new Pixel[redImage.getHeight()][redImage.getWidth()];
    for (int i = 0; i < redImage.getHeight(); i++) {
      for (int j = 0; j < redImage.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = redImage.getPixels()[i][j].colorComponent.redComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = greenImage.getPixels()[i][j].colorComponent.greenComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = blueImage.getPixels()[i][j].colorComponent.blueComponent;
      }
    }
    Image destImage = new Image(redImage.getWidth(), redImage.getHeight(), redImage.getMaxValueOfColor(),
            listOfPixelsDestImage);
    listOfImages.put(destImageName, destImage);
    return destImage;
  }

  private Image greyscaleRedComponent(Image image) {
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = image.getPixels()[i][j].colorComponent.redComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = image.getPixels()[i][j].colorComponent.redComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = image.getPixels()[i][j].colorComponent.redComponent;
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  private Image greyscaleGreenComponent(Image image) {
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = image.getPixels()[i][j].colorComponent.greenComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = image.getPixels()[i][j].colorComponent.greenComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = image.getPixels()[i][j].colorComponent.greenComponent;
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  private Image greyscaleBlueComponent(Image image) {
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = image.getPixels()[i][j].colorComponent.blueComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = image.getPixels()[i][j].colorComponent.blueComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = image.getPixels()[i][j].colorComponent.blueComponent;
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  private Image greyscaleValueComponent(Image image) {
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j]
                = new Pixel(i, j, 0, 0, 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = image.getPixels()[i][j].getValueComponent();
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = image.getPixels()[i][j].getValueComponent();
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = image.getPixels()[i][j].getValueComponent();
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  private Image greyscaleIntensityComponent(Image image) {
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = image.getPixels()[i][j].getIntensityComponent();
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = image.getPixels()[i][j].getIntensityComponent();
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = image.getPixels()[i][j].getIntensityComponent();
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  private Image greyscaleLumaComponent(Image image) {
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = image.getPixels()[i][j].getLumaComponent();
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = image.getPixels()[i][j].getLumaComponent();
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = image.getPixels()[i][j].getLumaComponent();
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }
}
