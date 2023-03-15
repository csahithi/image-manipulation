package controller.commands;

import java.io.IOException;

import model.Image;
import model.ImageProcessingModel;

/**
 * This command class saves the image with the given name to the specified path which should include the
 * name of the file.
 */
public class SaveImage implements ImageCommandController {
  String imagePath;
  String imageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param imagePath the string which contains destination ppm image path.
   * @param imageName the string which contains source image name.
   */
  public SaveImage(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public Image execute(ImageProcessingModel m) throws IOException {
    return m.saveImage(imagePath, imageName);
  }
}
