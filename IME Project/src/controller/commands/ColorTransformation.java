package controller.commands;

import model.Image;
import model.ImprovedImageProcessing;

/**
 * This command class performs color transformations such as sepia on an image.
 */
public class ColorTransformation implements ImageCommandController {
  String operation;
  String sourceImageName;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param operation       the color transformation operation to be performed on the image.
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   */
  public ColorTransformation(String operation, String sourceImageName, String destImageName) {
    this.operation = operation;
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImprovedImageProcessing m) {
    return m.colorTransformation(operation, sourceImageName, destImageName);
  }

}
