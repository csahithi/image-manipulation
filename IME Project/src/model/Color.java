package model;

/**
 * This class represents RGB color factor for each pixel.
 */
public class Color extends java.awt.Color {
  private int redComponent;
  private int greenComponent;
  private int blueComponent;

  /**
   * Constructor to initialize values of components of color object.
   *
   * @param redComponent   is the red component value of color.
   * @param greenComponent is the green component value of color
   * @param blueComponent  is the blue component value of color
   */
  public Color(int redComponent, int greenComponent, int blueComponent) {
    super(redComponent, greenComponent, blueComponent);
    this.redComponent = redComponent;
    this.greenComponent = greenComponent;
    this.blueComponent = blueComponent;
  }

  public Color(int rgbValue) {
    super(rgbValue);
  }

  public int getRedComponent() {
    return redComponent;
  }

  public void setRedComponent(int redComponent) {
    this.redComponent = redComponent;
  }

  public int getGreenComponent() {
    return greenComponent;
  }

  public void setGreenComponent(int greenComponent) {
    this.greenComponent = greenComponent;
  }

  public int getBlueComponent() {
    return blueComponent;
  }

  public void setBlueComponent(int blueComponent) {
    this.blueComponent = blueComponent;
  }
}
