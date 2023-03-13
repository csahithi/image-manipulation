package model;

public class Pixel {
  public int row;
  public int column;
  public Color colorComponent;


  public Pixel(int row, int column, int redComponent, int greenComponent, int blueComponent) {
    this.row = row;
    this.column = column;
    this.colorComponent = new Color(redComponent, greenComponent, blueComponent);
  }



  public int getValueComponent() {
    return Math.max(this.colorComponent.redComponent, Math.max(this.colorComponent.greenComponent, this.colorComponent.blueComponent));
  }

  public int getIntensityComponent() {
    return (this.colorComponent.redComponent + this.colorComponent.greenComponent + this.colorComponent.blueComponent) / 3;
  }

  public int getLumaComponent() {
    return (int) ((0.2126 * this.colorComponent.redComponent) + (0.7152 * this.colorComponent.greenComponent) + (0.0722 * this.colorComponent.blueComponent));
  }
}
