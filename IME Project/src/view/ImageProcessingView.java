package view;

import java.awt.event.ActionListener;
import java.util.List;

import model.Image;

public interface ImageProcessingView {
  void makeVisible();
  void setCommandButtonListener(ActionListener actionEvent);
  List<String> getParameters(String command);
  void displayCurrentImage(List<Image> m);
  void displayErrorDialog();
}
