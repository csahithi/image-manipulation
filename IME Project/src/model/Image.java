package model;

public class Image {
  private final int width;
  private final int height;
  private final int maxValueOfColor;
  private final Pixel[][] listOfPixels;

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
