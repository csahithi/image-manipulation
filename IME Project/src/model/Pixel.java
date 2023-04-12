package model;

/**
 * The Pixel class represents a single pixel in an image.
 * It stores the row and column number of the pixel, as well as the color component of the pixel.
 */
public class Pixel {
  private final Color colorComponent;

  /**
   * Constructor to initialize values of pixel properties/attributes.
   *
   * @param row            is the row number of pixel
   * @param column         is the column number of pixel
   * @param redComponent   is the red component value of colorComponent of pixel.
   * @param greenComponent is the green component value of colorComponent of pixel.
   * @param blueComponent  is the blue component value of colorComponent of pixel.
   */
  public Pixel(int row, int column, int redComponent, int greenComponent, int blueComponent) {
    int pixelRow = row;
    int pixelColumn = column;
    this.colorComponent = new Color(redComponent, greenComponent, blueComponent);
  }

  public Color getColorComponent() {
    return colorComponent;
  }

  /**
   * Returns the value component of Pixel.
   *
   * @return integer value of value-component.
   */
  public int getValueComponent() {
    return Math.max(this.colorComponent.getRedComponent(), Math.max(
            this.colorComponent.getGreenComponent(),
            this.colorComponent.getBlueComponent()));
  }

  /**
   * Returns the Intensity component of Pixel.
   *
   * @return integer value of Intensity-component.
   */
  public int getIntensityComponent() {
    return (this.colorComponent.getRedComponent() + this.colorComponent.getGreenComponent()
            + this.colorComponent.getBlueComponent()) / 3;
  }

  /**
   * Returns the Luma component of Pixel.
   *
   * @return integer value of Luma-component.
   */
  public int getLumaComponent() {
    return (int) Math.round((0.2126 * this.colorComponent.getRedComponent())
            + (0.7152 * this.colorComponent.getGreenComponent())
            + (0.0722 * this.colorComponent.getBlueComponent()));
  }
}
