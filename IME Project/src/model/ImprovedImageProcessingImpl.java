package model;

public class ImprovedImageProcessingImpl extends ImageProcessingModelImpl
        implements ImprovedImageProcessing {

  @Override
  public Image filtering(String operation, String sourceImageName, String destImageName) {
    Image sourceImage = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    if (sourceImage == null) {
      return null;
    }
    Image resultImage;
    switch (operation) {
      case "blur-image":
        resultImage = imageBlurring(sourceImage);
        break;
      case "sharpen-image":
        resultImage = imageSharpening(sourceImage);
        break;
      default:
        return null;
    }
    LIST_OF_IMAGES.put(destImageName, resultImage);
    return resultImage;
  }

  @Override
  public Image colorTransformation(String transformation, String sourceImageName,
                                   String destImageName) {
    Image sourceImage = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    if (sourceImage == null) {
      return null;
    }
    Image resultImage = null;
    if (transformation.equals("sepia tone")) {
      resultImage = sepiaTone(sourceImage);
    }
    LIST_OF_IMAGES.put(destImageName, resultImage);
    return resultImage;
  }

  private Image sepiaTone(Image image) {
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        int r = image.getPixels()[i][j].colorComponent.redComponent;
        int g = image.getPixels()[i][j].colorComponent.greenComponent;
        int b = image.getPixels()[i][j].colorComponent.blueComponent;
        listOfPixelsDestImage[i][j].colorComponent.redComponent = (int) ((0.393 * r) + (0.769 * g)
                + (0.189 * b));
        listOfPixelsDestImage[i][j].colorComponent.greenComponent = (int) ((0.349 * r) + (0.686 * g)
                + (0.168 * b));
        listOfPixelsDestImage[i][j].colorComponent.blueComponent = (int) ((0.272 * r) + (0.534 * g)
                + (0.131 * b));
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  @Override
  public Image dither(String sourceImageName, String destImageName) {
    Image image = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    Image greyscaleImage = super.greyscale("luma-component", sourceImageName,
            "greyscaleImage");
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    int r = image.getHeight();
    int c = image.getWidth();
    int old_color;
    int new_color;
    int error;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0,
                0);
        old_color = greyscaleImage.getPixels()[i][j].colorComponent.redComponent;
        new_color = (Math.abs(old_color - 255) < old_color) ? 255 : 0;
        error = old_color - new_color;
        listOfPixelsDestImage[i][j].colorComponent.redComponent = new_color;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent = new_color;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent = new_color;
        listOfPixelsDestImage = addError(listOfPixelsDestImage, i, j + 1, error, 7 / 16);
        listOfPixelsDestImage = addError(listOfPixelsDestImage, i + 1, j - 1, error,
                3 / 16);
        listOfPixelsDestImage = addError(listOfPixelsDestImage, i + 1, j, error, 5 / 16);
        listOfPixelsDestImage = addError(listOfPixelsDestImage, i + 1, j + 1, error,
                1 / 16);
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  private Pixel[][] addError(Pixel[][] pixels, int r, int c, int error, int fraction) {
    pixels[r][c].colorComponent.redComponent += fraction * error;
    pixels[r][c].colorComponent.greenComponent += fraction * error;
    pixels[r][c].colorComponent.blueComponent += fraction * error;
    return pixels;
  }

  private Pixel[][] createPaddedArray(Pixel[][] pixels, int r, int c, int padding) {
    Pixel[][] paddedArray = new Pixel[r + 2 * padding][c + 2 * padding];
    for (int i = 0; i < r + 2 * padding; i++) {
      for (int j = 0; j < c + 2 * padding; j++) {
        if (i >= 0 && i < padding) {
          paddedArray[i][j] = new Pixel(i, j, 0, 0, 0);
        }
        if (i >= r + padding && i < r + 2 * padding) {
          paddedArray[i][j] = new Pixel(i, j, 0, 0, 0);
        }
        if (j >= 0 && j < padding) {
          paddedArray[i][j] = new Pixel(i, j, 0, 0, 0);
        }
        if (j >= c + padding && j < c + 2 * padding) {
          paddedArray[i][j] = new Pixel(i, j, 0, 0, 0);
        }
      }
    }
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        paddedArray[i + 1][j + 1] = new Pixel(i + 1, j + 1,
                pixels[i][j].colorComponent.redComponent,
                pixels[i][j].colorComponent.greenComponent,
                pixels[i][j].colorComponent.blueComponent);
      }
    }
    return paddedArray;
  }

  private Image imageBlurring(Image image) {
    Pixel[][] paddedArray = createPaddedArray(image.getPixels(), image.getHeight()
            , image.getWidth(), 1);
    int rSum, gSum, bSum;
    int r = image.getHeight();
    int c = image.getWidth();
    Pixel[][] listOfPixelsDestImage = new Pixel[r][c];
    for (int i = 1; i < r + 1; i++) {
      for (int j = 1; j <= c + 1; j++) {
        rSum = (1 / 4) * paddedArray[i][j].colorComponent.redComponent;
        gSum = (1 / 4) * paddedArray[i][j].colorComponent.greenComponent;
        bSum = (1 / 4) * paddedArray[i][j].colorComponent.blueComponent;
        for (int x = i - 1; x <= i + 1; x++) {
          for (int y = j - 1; y <= j + 1; y++) {
            if ((x == i - 1 && (y == j - 1 || y == j + 1)) || (x == i + 1 && (y == j - 1 ||
                    y == j + 1))) {
              rSum += (1 / 16) * (paddedArray[x][y].colorComponent.redComponent);
              gSum += (1 / 16) * (paddedArray[x][y].colorComponent.greenComponent);
              bSum += (1 / 16) * (paddedArray[x][y].colorComponent.blueComponent);
            } else if ((x == i && (y == j - 1 || y == j + 1)) || (y == j && (x == i - 1 ||
                    x == i + 1))) {
              rSum += (1 / 8) * (paddedArray[x][y].colorComponent.redComponent);
              gSum += (1 / 8) * (paddedArray[x][y].colorComponent.greenComponent);
              bSum += (1 / 8) * (paddedArray[x][y].colorComponent.blueComponent);
            }
          }
        }
        listOfPixelsDestImage[i - 1][j - 1] = new Pixel(i - 1, j - 1, rSum, bSum, gSum);
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  private Image imageSharpening(Image image) {
    Pixel[][] paddedArray = createPaddedArray(image.getPixels(), image.getHeight(),
            image.getWidth(), 2);
    int rSum, gSum, bSum;
    int r = image.getHeight();
    int c = image.getWidth();
    Pixel[][] listOfPixelsDestImage = new Pixel[r][c];
    for (int i = 2; i < r + 2; i++) {
      for (int j = 2; j < c + 2; j++) {
        rSum = paddedArray[i][j].colorComponent.redComponent;
        gSum = paddedArray[i][j].colorComponent.greenComponent;
        bSum = paddedArray[i][j].colorComponent.blueComponent;
        for (int x = i - 2; x < i + 3; x++) {
          for (int y = j - 2; y < j + 3; y++) {
            if (x == i - 2 || x == i + 2 || y == j - 2 || y == j + 2) {
              rSum += (-1 / 8) * (paddedArray[x][y].colorComponent.redComponent);
              gSum += (-1 / 8) * (paddedArray[x][y].colorComponent.greenComponent);
              bSum += (-1 / 8) * (paddedArray[x][y].colorComponent.blueComponent);
            } else if (x == i - 1 || x == i + 1 || y == j - 1 || y == j + 1) {
              rSum += (1 / 4) * (paddedArray[x][y].colorComponent.redComponent);
              gSum += (1 / 4) * (paddedArray[x][y].colorComponent.greenComponent);
              bSum += (1 / 4) * (paddedArray[x][y].colorComponent.blueComponent);
            }
          }
        }
        listOfPixelsDestImage[i - 2][j - 2] = new Pixel(i - 2, j - 2, rSum, bSum, gSum);
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }
}
