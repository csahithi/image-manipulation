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
      case "blur":
        resultImage = imageBlurring(sourceImage);
        break;
      case "sharpen":
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
    if (transformation.equals("sepia")) {
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
        int r = image.getPixels()[i][j].colorComponent.getRedComponent();
        int g = image.getPixels()[i][j].colorComponent.getGreenComponent();
        int b = image.getPixels()[i][j].colorComponent.getBlueComponent();
        listOfPixelsDestImage[i][j].colorComponent.setRedComponent(Math.max(0, Math.min(255,
                (int) ((0.393 * r) + (0.769 * g) + (0.189 * b)))));
        listOfPixelsDestImage[i][j].colorComponent.setGreenComponent(Math.max(0, Math.min(255,
                (int) ((0.349 * r) + (0.686 * g) + (0.168 * b)))));
        listOfPixelsDestImage[i][j].colorComponent.setBlueComponent(Math.max(0, Math.min(255,
                (int) ((0.272 * r) + (0.534 * g) + (0.131 * b)))));
        if (listOfPixelsDestImage[i][j].colorComponent.getRedComponent() > 255
                || listOfPixelsDestImage[i][j].colorComponent.getRedComponent() < 0) {
          System.out.println(listOfPixelsDestImage[i][j].colorComponent.getRedComponent());
        }
        if (listOfPixelsDestImage[i][j].colorComponent.getGreenComponent() > 255
                || listOfPixelsDestImage[i][j].colorComponent.getGreenComponent() < 0) {
          System.out.println(listOfPixelsDestImage[i][j].colorComponent.getGreenComponent());
        }
        if (listOfPixelsDestImage[i][j].colorComponent.getBlueComponent() > 255
                || listOfPixelsDestImage[i][j].colorComponent.getBlueComponent() < 0) {
          System.out.println(listOfPixelsDestImage[i][j].colorComponent.getBlueComponent());
        }

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
      }
    }
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        old_color = greyscaleImage.getPixels()[i][j].colorComponent.getRedComponent();
        new_color = (Math.abs(old_color - 255) < old_color) ? 255 : 0;
        error = old_color - new_color;
        listOfPixelsDestImage[i][j].colorComponent.setRedComponent(new_color);
        listOfPixelsDestImage[i][j].colorComponent.setGreenComponent(new_color);
        listOfPixelsDestImage[i][j].colorComponent.setBlueComponent(new_color);
        if (j + 1 < c) {
          listOfPixelsDestImage = addError(listOfPixelsDestImage, i, j + 1, error,
                  7.0 / 16.0);
        }
        if (j > 0 && i + 1 < r) {
          listOfPixelsDestImage = addError(listOfPixelsDestImage, i + 1, j - 1, error,
                  3.0 / 16.0);
        }
        if (i + 1 < r) {
          listOfPixelsDestImage = addError(listOfPixelsDestImage, i + 1, j, error,
                  5.0 / 16.0);
        }
        if (j + 1 < c && i + 1 < r) {
          listOfPixelsDestImage = addError(listOfPixelsDestImage, i + 1, j + 1, error,
                  1.0 / 16.0);
        }
      }
    }
    Image destImage = new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
    LIST_OF_IMAGES.put(destImageName, destImage);
    return destImage;

  }

  private Pixel[][] addError(Pixel[][] pixels, int r, int c, int error, double fraction) {
    pixels[r][c].colorComponent.setRedComponent(pixels[r][c].colorComponent.getRedComponent()+(int) fraction * error);
    pixels[r][c].colorComponent.setGreenComponent(pixels[r][c].colorComponent.getGreenComponent()+(int) fraction * error);
    pixels[r][c].colorComponent.setBlueComponent(pixels[r][c].colorComponent.getBlueComponent()+(int) fraction * error);
    return pixels;
  }

  private Pixel[][] createPaddedArray(Pixel[][] pixels, int r, int c, int padding) {
    Pixel[][] paddedArray = new Pixel[r + 2 * padding][c + 2 * padding];
    for (int i = 0; i < r + 2 * padding; i++) {
      for (int j = 0; j < c + 2 * padding; j++) {
        if (i >= padding && i < r + padding && j >= padding && j < c + padding) {
          paddedArray[i][j] = new Pixel(i, j,
                  pixels[i - padding][j - padding].colorComponent.getRedComponent(),
                  pixels[i - padding][j - padding].colorComponent.getGreenComponent(),
                  pixels[i - padding][j - padding].colorComponent.getBlueComponent());
        } else {
          paddedArray[i][j] = new Pixel(i, j, 0, 0, 0);
        }
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
      for (int j = 1; j < c + 1; j++) {
        rSum = (int) ((0.25) * paddedArray[i][j].colorComponent.getRedComponent());
        gSum = (int) ((0.25) * paddedArray[i][j].colorComponent.getGreenComponent());
        bSum = (int) ((0.25) * paddedArray[i][j].colorComponent.getBlueComponent());
        for (int x = i - 1; x < i + 2; x++) {
          for (int y = j - 1; y < j + 2; y++) {
            if ((x == i - 1 && (y == j - 1 || y == j + 1)) || (x == i + 1 && (y == j - 1 ||
                    y == j + 1))) {
              rSum += (0.0625) * (paddedArray[x][y].colorComponent.getRedComponent());
              gSum += (0.0625) * (paddedArray[x][y].colorComponent.getGreenComponent());
              bSum += (0.0625) * (paddedArray[x][y].colorComponent.getBlueComponent());
            } else if ((x == i && (y == j - 1 || y == j + 1)) || (y == j && (x == i - 1 ||
                    x == i + 1))) {
              rSum += (0.125) * (paddedArray[x][y].colorComponent.getRedComponent());
              gSum += (0.125) * (paddedArray[x][y].colorComponent.getGreenComponent());
              bSum += (0.125) * (paddedArray[x][y].colorComponent.getBlueComponent());
            }
          }
        }
        listOfPixelsDestImage[i - 1][j - 1] = new Pixel(i - 1, j - 1,
                Math.max(0, Math.min(rSum, 255)), Math.max(0, Math.min(gSum, 255)),
                Math.max(0, Math.min(bSum, 255)));
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
        rSum = paddedArray[i][j].colorComponent.getRedComponent();
        gSum = paddedArray[i][j].colorComponent.getGreenComponent();
        bSum = paddedArray[i][j].colorComponent.getBlueComponent();
        for (int x = i - 2; x < i + 3; x++) {
          for (int y = j - 2; y < j + 3; y++) {
            if (x == i - 2 || x == i + 2 || y == j - 2 || y == j + 2) {
              rSum += (-0.125) * (paddedArray[x][y].colorComponent.getRedComponent());
              gSum += (-0.125) * (paddedArray[x][y].colorComponent.getGreenComponent());
              bSum += (-0.125) * (paddedArray[x][y].colorComponent.getBlueComponent());
            } else if (x == i - 1 || x == i + 1 || y == j - 1 || y == j + 1) {
              rSum += (0.25) * (paddedArray[x][y].colorComponent.getRedComponent());
              gSum += (0.25) * (paddedArray[x][y].colorComponent.getGreenComponent());
              bSum += (0.25) * (paddedArray[x][y].colorComponent.getBlueComponent());
            }
          }
        }
        listOfPixelsDestImage[i - 2][j - 2] = new Pixel(i - 2, j - 2,
                Math.max(0, Math.min(rSum, 255)), Math.max(0, Math.min(gSum, 255)),
                Math.max(0, Math.min(bSum, 255)));
      }
    }
    return new Image(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }
}
