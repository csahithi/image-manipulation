package controller.commands;

import controller.CommandController;
import controller.IController;
import model.ImageProcessingModel;

public class RunScriptFile implements IController {
  String filePath;



  public RunScriptFile(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void go(ImageProcessingModel m) {

  }
}
