package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements all the operations/commands that can applied on image.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  protected final Map<String, Image> LIST_OF_IMAGES = new HashMap<>();

  @Override
  public Image loadImage(String imagePath, String imageName) {
    Image image = ReadPPMUtil.readPPM(imagePath);
    if (image == null) {
      return null;
    }
    LIST_OF_IMAGES.put(imageName, image);
    return image;
  }

  @Override
  public Image saveImage(String imagePath, String imageName) {
    Image image = LIST_OF_IMAGES.getOrDefault(imageName, null);
    if (image == null) {
      return null;
    }
    CreatePPMUtil.createPPM(imagePath, image);
    return image;
  }

  @Override
  public Image greyscale(String component, String sourceImageName, String destImageName) {
    Image sourceImage = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
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
    LIST_OF_IMAGES.put(destImageName, resultImage);
    return resultImage;
  }

  @Override
  public Image horizontalFlip(String sourceImageName, String destImageName) {
    Image image = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    if (image == null) {
      return null;
    }
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j <= image.getWidth() / 2; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        listOfPixelsDestImage[i][j] = image.getPixels()[i][image.getWidth() - j - 1];
        listOfPixelsDestImage[i][image.getWidth() - j - 1] = image.getPixels()[i][j];
      }
    }
    Image destImage = new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
    LIST_OF_IMAGES.put(destImageName, destImage);
    return destImage;
  }

  @Override
  public Image verticalFlip(String sourceImageName, String destImageName) {
    Image image = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    if (image == null) {
      return null;
    }
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i <= image.getHeight() / 2; i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        listOfPixelsDestImage[i][j] = image.getPixels()[image.getHeight() - i - 1][j];
        listOfPixelsDestImage[image.getHeight() - i - 1][j] = image.getPixels()[i][j];
      }
    }
    Image destImage = new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
    LIST_OF_IMAGES.put(destImageName, destImage);
    return destImage;
  }

  @Override
  public Image brighten(int increment, String sourceImageName, String destImageName) {
    Image image = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    if (image == null) {
      return null;
    }
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent = Math.max(0,
                (Math.min(image.getPixels()[i][j].colorComponent.redComponent + increment, 255)));
        listOfPixelsDestImage[i][j].colorComponent.greenComponent = Math.max(0,
                (Math.min(image.getPixels()[i][j].colorComponent.greenComponent + increment, 255)));
        listOfPixelsDestImage[i][j].colorComponent.blueComponent = Math.max(0,
                (Math.min(image.getPixels()[i][j].colorComponent.blueComponent + increment, 255)));
      }
    }
    Image destImage = new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
    LIST_OF_IMAGES.put(destImageName, destImage);
    return destImage;
  }

  @Override
  public Image rgbSplit(String sourceImageName, String redImageName, String greenImageName,
                        String blueImageName) {
    Image image = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    if (image == null) {
      return null;
    }
    Image redDestImage = greyscaleRedComponent(image);
    Image greenDestImage = greyscaleGreenComponent(image);
    Image blueDestImage = greyscaleBlueComponent(image);
    LIST_OF_IMAGES.put(redImageName, redDestImage);
    LIST_OF_IMAGES.put(greenImageName, greenDestImage);
    LIST_OF_IMAGES.put(blueImageName, blueDestImage);
    return redDestImage;
  }

  @Override
  public Image rgbCombine(String destImageName, String redImageName, String greenImageName,
                          String blueImageName) {
    Image redImage = LIST_OF_IMAGES.getOrDefault(redImageName, null);
    Image greenImage = LIST_OF_IMAGES.getOrDefault(greenImageName, null);
    Image blueImage = LIST_OF_IMAGES.getOrDefault(blueImageName, null);
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
    Image destImage = new Image(redImage.getWidth(), redImage.getHeight(),
            redImage.getMaxValueOfColor(), listOfPixelsDestImage);
    LIST_OF_IMAGES.put(destImageName, destImage);
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
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
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
