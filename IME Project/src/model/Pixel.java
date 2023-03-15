package model;

/**
 * This class represents a pixel.
 */
public class Pixel {
  public int row;
  public int column;
  public Color colorComponent;

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
    this.row = row;
    this.column = column;
    this.colorComponent = new Color(redComponent, greenComponent, blueComponent);
  }

  /**
   * Returns the value component of Pixel.
   *
   * @return integer value of value-component.
   */
  public int getValueComponent() {
    return Math.max(this.colorComponent.redComponent, Math.max(this.colorComponent.greenComponent,
            this.colorComponent.blueComponent));
  }

  /**
   * Returns the Intensity component of Pixel.
   *
   * @return integer value of Intensity-component.
   */
  public int getIntensityComponent() {
    return (this.colorComponent.redComponent + this.colorComponent.greenComponent
            + this.colorComponent.blueComponent) / 3;
  }

  /**
   * Returns the Luma component of Pixel.
   *
   * @return integer value of Luma-component.
   */
  public int getLumaComponent() {
    return (int) Math.round((0.2126 * this.colorComponent.redComponent)
            + (0.7152 * this.colorComponent.greenComponent)
            + (0.0722 * this.colorComponent.blueComponent));
  }
}
