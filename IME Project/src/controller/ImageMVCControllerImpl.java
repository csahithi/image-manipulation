package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.commands.Brighten;
import controller.commands.ColorTransformation;
import controller.commands.Dither;
import controller.commands.Filtering;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageCommandController;
import controller.commands.Load;
import controller.commands.RGBCombine;
import controller.commands.RGBSplit;
import controller.commands.Save;
import controller.commands.VerticalFlip;
import model.Image;
import model.ImprovedImageProcessing;
import view.ImageProcessingView;

public class ImageMVCControllerImpl implements ImageMVCController, ActionListener {
  private final ImprovedImageProcessing model;
  private final ImageProcessingView view;

  public ImageMVCControllerImpl(ImprovedImageProcessing model, ImageProcessingView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void execute() {
    this.view.setCommandButtonListener(this);
    this.view.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //String command = e.getActionCommand();
    List<String> commands = this.view.getParameters(e.getActionCommand());
    if (commands != null) {
      try {
        List<Image> m = readCommands(commands);
        if (m == null) {
          this.view.displayErrorDialog();
        } else {
          this.view.displayCurrentImage(m);
        }
      } catch (Exception ex) {
        this.view.displayErrorDialog();
      }
    }
  }

  private List<Image> readCommands(List<String> inputArray) {
    ImageCommandController cmd = null;
    try {
        switch (inputArray.get(0)) {
          case "load":
            cmd = new Load(inputArray.get(1), inputArray.get(2));
            break;
          case "save":
            cmd = new Save(inputArray.get(1), inputArray.get(2));
            break;
          case "horizontal-flip":
            cmd = new HorizontalFlip(inputArray.get(1), inputArray.get(2));
            break;
          case "vertical-flip":
            cmd = new VerticalFlip(inputArray.get(1), inputArray.get(2));
            break;
          case "greyscale":
            cmd = new Greyscale(inputArray.get(1), inputArray.get(2), inputArray.get(3));
            break;
          case "sepia":
            cmd = new ColorTransformation("sepia", inputArray.get(1), inputArray.get(2));
            break;
          case "blur":
            cmd = new Filtering("blur", inputArray.get(1), inputArray.get(2));
            break;
          case "sharpen":
            cmd = new Filtering("sharpen", inputArray.get(1), inputArray.get(2));
            break;
          case "dither":
            cmd = new Dither(inputArray.get(1), inputArray.get(2));
            break;
          case "brighten":
            cmd = new Brighten(Integer.parseInt(inputArray.get(1)), inputArray.get(2),
                    inputArray.get(3));
            break;
          case "rgb-split":
            cmd = new RGBSplit(inputArray.get(1), inputArray.get(2), inputArray.get(3),
                    inputArray.get(4));
            break;
          case "rgb-combine":
            cmd = new RGBCombine(inputArray.get(1), inputArray.get(2), inputArray.get(3),
                    inputArray.get(4));
            break;
          default:
            return null;
        }
    } catch (NumberFormatException e) {
      return null;
    }
    if (cmd != null) {
      return cmd.execute(model);
    } else {
      return null;
    }
  }
}
