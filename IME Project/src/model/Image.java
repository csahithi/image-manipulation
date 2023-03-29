package model;

/**
 * This class represents Image.
 */
public class Image {
  private final int width;
  private final int height;
  private final int maxValueOfColor;
  private final Pixel[][] listOfPixels;

  /**
   * Constructor to initialize the attributes of Image class.
   *
   * @param width           is width of image.
   * @param height          is height of image.
   * @param maxValueOfColor max color value in the all the pixels of class.
   * @param listOfPixels    2-D array of pixels.
   */
  public Image(int width, int height, int maxValueOfColor, Pixel[][] listOfPixels) {
    this.width = width;
    this.height = height;
    this.maxValueOfColor = maxValueOfColor;
    this.listOfPixels = listOfPixels;
  }

  public Pixel[][] getPixels() {
    return this.listOfPixels;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getMaxValueOfColor() {
    return maxValueOfColor;
  }
}
