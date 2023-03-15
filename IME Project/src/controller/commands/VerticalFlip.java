package controller.commands;

import model.Image;
import model.ImageProcessingModel;


/**
 * This command class flips an image vertically to create a new image,
 * referred to henceforth by the given destination name.
 */
public class VerticalFlip implements ImageCommandController {
  String sourceImageName;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   */
  public VerticalFlip(String sourceImageName, String destImageName) {
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return m.verticalFlip(sourceImageName, destImageName);
  }

}