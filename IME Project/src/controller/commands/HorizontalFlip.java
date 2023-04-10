package controller.commands;

import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;

/**
 * This command class flips image horizontally to create a new image,
 * referred to henceforth by the given destination name.
 */
public class HorizontalFlip implements ImageCommandController {
  String sourceImageName;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   */
  public HorizontalFlip(String sourceImageName, String destImageName) {
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    return List.of(new Image[]{m.horizontalFlip(sourceImageName, destImageName)});
  }
}
