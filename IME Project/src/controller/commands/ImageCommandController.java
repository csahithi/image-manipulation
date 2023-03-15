package controller.commands;

import java.io.IOException;

import model.Image;
import model.ImageProcessingModel;

public interface ImageCommandController {
  Image execute(ImageProcessingModel m) throws IOException;
}
