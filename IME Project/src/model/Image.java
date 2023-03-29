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

  /**
   * Returns the pixel values of Image.
   *
   * @return Array of pixels of Image
   */
  public Pixel[][] getPixels() {
    return this.listOfPixels;
  }

  /**
   * Returns the width of Image.
   *
   * @return the integer value of width of Image
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of Image.
   *
   * @return the integer value of height of Image
   */
  public int getHeight() {
    return height;
  }

  /**
   * Returns the maximum value of color of Image.
   *
   * @return the integer value of maximum value of color of Image
   */
  public int getMaxValueOfColor() {
    return maxValueOfColor;
  }
}
