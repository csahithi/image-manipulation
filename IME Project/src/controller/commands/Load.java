package controller.commands;

import model.Image;
import model.ImageProcessingModel;

/**
 * This command class load an image from the specified path and refers it  in the program
 * by the given image name.
 */
public class Load implements ImageCommandController {
  String imagePath;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param imagePath     the string which contains source ppm image path.
   * @param destImageName the string which contains destination image name.
   */
  public Load(String imagePath, String destImageName) {
    this.imagePath = imagePath;
    this.destImageName = destImageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) {
    return m.loadImage(imagePath, destImageName);
  }
}
