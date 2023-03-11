package model;

public class Pixel {
  public int row;
  public int column;
  public Color colorComponent;
  public int valueComponent;
  public double intensityComponent;
  public double lumaComponent;

  public Pixel(int row, int column, int redComponent, int greenComponent, int blueComponent) {
    this.row = row;
    this.column = column;
    this.colorComponent = new Color(redComponent, greenComponent, blueComponent);
    this.valueComponent = Math.max(redComponent, Math.max(greenComponent, blueComponent));
    this.intensityComponent = (redComponent + greenComponent + blueComponent) / 3;
    this.lumaComponent = (0.2126 * redComponent) + (0.7152 * greenComponent) + (0.0722 * blueComponent);
  }
}
