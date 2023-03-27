package model;

/**
 * This class represents RGB color factor for each pixel.
 */
public class Color extends java.awt.Color {
  public int redComponent;
  public int greenComponent;
  public int blueComponent;

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

  public Color(int rgbValue){
    super(rgbValue);
  }
}
