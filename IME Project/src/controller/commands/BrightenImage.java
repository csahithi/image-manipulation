package controller.commands;

import model.Image;
import model.ImageProcessingModel;

/**
 * This command class brightens the image by the given increment.
 */
public class BrightenImage implements ImageCommandController {
  int increment;
  String sourceImageName;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param increment       takes in integer value with which the image is brightened or darkened
   *                        based on its sign.
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   */
  public BrightenImage(int increment, String sourceImageName, String destImageName) {
    this.increment = increment;
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return m.brighten(increment, sourceImageName, destImageName);
  }
}
