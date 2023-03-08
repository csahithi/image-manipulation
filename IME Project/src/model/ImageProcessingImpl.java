package model;

public class ImageProcessingImpl implements ImageProcessingModel{
  private int width;
  private int height;
  private int maxValueOfColor;
  private Pixel[][] listOfPixels;

  public ImageProcessingImpl(int width, int height, int maxValueOfColor, Pixel[][] listOfPixels){
    this.width = width;
    this.height = height;
    this.maxValueOfColor = maxValueOfColor;
    this.listOfPixels = listOfPixels;
  }


  @Override
  public void loadImage(String imagePath, ImageProcessingImpl destImage) {

  }

  @Override
  public void saveImage(String imagePath) {

  }

  @Override
  public void greyscaleRedComponent(ImageProcessingImpl destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width; j++){
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].colorComponent.redComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].colorComponent.redComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void greyscaleGreenComponent(ImageProcessingImpl destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width; j++){
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = listOfPixelsDestImage[i][j].colorComponent.greenComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = listOfPixelsDestImage[i][j].colorComponent.greenComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void greyscaleBlueComponent(ImageProcessingImpl destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width; j++){
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = listOfPixelsDestImage[i][j].colorComponent.blueComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = listOfPixelsDestImage[i][j].colorComponent.blueComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void greyscaleValueComponent(ImageProcessingImpl destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width; j++){
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
  public void greyscaleIntensityComponent(ImageProcessingImpl destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width; j++){
//        listOfPixelsDestImage[i][j].colorComponent.redComponent
//                = listOfPixelsDestImage[i][j].intensityComponent;
//        listOfPixelsDestImage[i][j].colorComponent.greenComponent
//                = listOfPixelsDestImage[i][j].intensityComponent;
//        listOfPixelsDestImage[i][j].colorComponent.blueComponent
//                = listOfPixelsDestImage[i][j].intensityComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void greyscaleLumaComponent(ImageProcessingImpl destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width; j++){
//        listOfPixelsDestImage[i][j].colorComponent.redComponent
//                = listOfPixelsDestImage[i][j].lumaComponent;
//        listOfPixelsDestImage[i][j].colorComponent.greenComponent
//                = listOfPixelsDestImage[i][j].lumaComponent;
//        listOfPixelsDestImage[i][j].colorComponent.blueComponent
//                = listOfPixelsDestImage[i][j].lumaComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void horizontalFlip(ImageProcessingImpl destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    Pixel t;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width/2; j++){
        t = listOfPixelsDestImage[i][j];
        listOfPixelsDestImage[i][j] = listOfPixelsDestImage[i][this.width - j - 1];
        listOfPixelsDestImage[i][this.width - j - 1] = t;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void verticalFlip(ImageProcessingImpl destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    Pixel t;
    for(int i=0; i<this.width; i++){
      for(int j=0; j<this.height/2; j++){
        t = listOfPixelsDestImage[i][j];
        listOfPixelsDestImage[i][j] = listOfPixelsDestImage[i][this.height - j - 1];
        listOfPixelsDestImage[i][this.height - j - 1] = t;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void brighten(int increment, ImageProcessingImpl destImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width; j++){
        listOfPixelsDestImage[i][j].colorComponent.redComponent+=increment;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent+=increment;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent+=increment;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void rgbSplit(ImageProcessingImpl redDestImage, ImageProcessingImpl greenDestImage,
                       ImageProcessingImpl blueDestImage) {
    Pixel[][] listOfPixelsRedDestImage = this.listOfPixels;
    Pixel[][] listOfPixelsGreenDestImage = this.listOfPixels;
    Pixel[][] listOfPixelsBlueDestImage = this.listOfPixels;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width; j++){
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
  public void rgbCombine(ImageProcessingImpl destImage, ImageProcessingImpl redImage,
                         ImageProcessingImpl greenImage, ImageProcessingImpl blueImage) {
    Pixel[][] listOfPixelsDestImage = this.listOfPixels;
    for(int i=0; i<this.height; i++){
      for(int j=0; j<this.width; j++){
        listOfPixelsDestImage[i][j].colorComponent.redComponent
                = redImage.listOfPixels[i][j].colorComponent.redComponent;
        listOfPixelsDestImage[i][j].colorComponent.greenComponent
                = greenImage.listOfPixels[i][j].colorComponent.greenComponent;
        listOfPixelsDestImage[i][j].colorComponent.blueComponent
                = blueImage.listOfPixels[i][j].colorComponent.blueComponent;
      }
    }
    destImage.setPixels(listOfPixelsDestImage);
  }

  @Override
  public void setPixels(Pixel[][] listOfPixels){
    this.listOfPixels = listOfPixels;
  }

  @Override
  public Pixel[][] getPixels(){
    return this.listOfPixels;
  }
}
