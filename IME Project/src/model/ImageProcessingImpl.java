package model;

public class ImageProcessingImpl implements ImageProcessingModel {
  private final int width;
  private final int height;
  private final int maxValueOfColor;
  private final Pixel[][] listOfPixels;

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
  public void saveImage(String imagePath) {
    CreatePPMUtil.createPPM(imagePath, this);
  }

  @Override
  public ImageProcessingModel greyscaleRedComponent() {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].colorComponent.redComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].colorComponent.redComponent;
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
  }

  @Override
  public ImageProcessingModel greyscaleGreenComponent() {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = listOfPixelsDestImage[i][j].colorComponent.greenComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].colorComponent.greenComponent;
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
  }

  @Override
  public ImageProcessingModel greyscaleBlueComponent() {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = listOfPixelsDestImage[i][j].colorComponent.blueComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].colorComponent.blueComponent;
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
  }

  @Override
  public ImageProcessingModel greyscaleValueComponent() {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = listOfPixelsDestImage[i][j].getValueComponent();
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].getValueComponent();
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].getValueComponent();
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
  }

  @Override
  public ImageProcessingModel greyscaleIntensityComponent() {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                =  listOfPixelsDestImage[i][j].getIntensityComponent();
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].getIntensityComponent();
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].getIntensityComponent();
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
  }

  @Override
  public ImageProcessingModel greyscaleLumaComponent() {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = listOfPixelsDestImage[i][j].getLumaComponent();
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].getLumaComponent();
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].getLumaComponent();
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
  }

  @Override
  public ImageProcessingModel horizontalFlip() {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    Pixel t;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width / 2; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        t = listOfPixelsDestImage[i][j];
        listOfPixelsDestImage[i][j] = listOfPixelsDestImage[i][this.width - j - 1];
        listOfPixelsDestImage[i][this.width - j - 1] = t;
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
  }

  @Override
  public ImageProcessingModel verticalFlip() {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    Pixel t;
    for (int i = 0; i < this.height / 2; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        t = listOfPixelsDestImage[i][j];
        listOfPixelsDestImage[i][j] = listOfPixelsDestImage[this.height - i - 1][j];
        listOfPixelsDestImage[this.height - i - 1][j] = t;
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
  }

  @Override
  public ImageProcessingModel brighten(int increment) {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent = Math.min(this.listOfPixels[i][j]
                .colorComponent.redComponent + increment, maxValueOfColor);
        listOfPixelsDestImage[i][j].colorComponent.greenComponent = Math.min(this.listOfPixels[i][j]
                .colorComponent.greenComponent + increment, maxValueOfColor);
        listOfPixelsDestImage[i][j].colorComponent.blueComponent = Math.min(this.listOfPixels[i][j]
                .colorComponent.blueComponent + increment, maxValueOfColor);
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
  }

  @Override
  public ImageProcessingModel[] rgbSplit() {
    Pixel[][] listOfPixelsRedDestImage = new Pixel[this.height][this.width];
    Pixel[][] listOfPixelsGreenDestImage = new Pixel[this.height][this.width];
    Pixel[][] listOfPixelsBlueDestImage = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsRedDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsGreenDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsBlueDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
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

    ImageProcessingModel RedDestImage = new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsRedDestImage);

    ImageProcessingModel GreenDestImage = new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsGreenDestImage);

    ImageProcessingModel BlueDestImage = new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsBlueDestImage);
    return new ImageProcessingModel[]{RedDestImage, GreenDestImage, BlueDestImage};
  }

  @Override
  public ImageProcessingModel rgbCombine(ImageProcessingModel redImage
          , ImageProcessingModel greenImage
          , ImageProcessingModel blueImage) {
    Pixel[][] listOfPixelsDestImage = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        listOfPixelsDestImage[i][j] = new Pixel(i, j, 0, 0
                , 0);
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = redImage.getPixels()[i][j].colorComponent.redComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = greenImage.getPixels()[i][j].colorComponent.greenComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = blueImage.getPixels()[i][j].colorComponent.blueComponent;
      }
    }
    return new ImageProcessingImpl(this.width, this.height,
            this.maxValueOfColor, listOfPixelsDestImage);
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
