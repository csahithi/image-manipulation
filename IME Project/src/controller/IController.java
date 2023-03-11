package controller;


import java.io.IOException;

import model.ImageProcessingModel;

public interface IController {
  void go(ImageProcessingModel m) throws IOException;


}
