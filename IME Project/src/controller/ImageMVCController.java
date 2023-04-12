package controller;

/**
 * Interface for the Image MVC Controller.It main controller for gui view.
 * It contains a single method "execute()" which is implemented by the concrete class
 * that controls the Image Processing functionality.
 */
public interface ImageMVCController {
  /**
   * This method is responsible for executing the actions and functionalities of the ImageMVCController.
   * It will be implemented by the concrete class that implements this interface and will contain the logic for processing
   * image data and updating the view accordingly.
   */
  void execute();
}
