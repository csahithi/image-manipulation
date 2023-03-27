package controller.commands;

import model.Image;
import model.ImprovedImageProcessing;


/**
 * This command class saves the image with the given name to the specified path
 * which should include the name of the file.
 */
public class Save implements ImageCommandController {
  String imagePath;
  String imageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param imagePath the string which contains destination ppm image path.
   * @param imageName the string which contains source image name.
   */
  public Save(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public Image execute(ImprovedImageProcessing m) {
    return m.saveImage(imagePath, imageName);
  }
}
