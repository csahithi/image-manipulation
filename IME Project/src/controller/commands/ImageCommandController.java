package controller.commands;

import java.io.IOException;

import model.Image;
import model.ImageProcessingModel;

/**
 *Command interface for the command classes.
 */
public interface ImageCommandController {
  Image execute(ImageProcessingModel m) throws IOException;
}
