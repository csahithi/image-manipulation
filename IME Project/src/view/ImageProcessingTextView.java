package view;

/**
 * The ImageProcessingTextView interface defines the methods that a view for an image processing
 * application should implement in text-based view .
 * The view is responsible for displaying the menu, error messages, and executing command entered
 * by the user.
 */
public interface ImageProcessingTextView {
  /**
   * Displays menu containing all the operations supported by the application.
   */
  void displayMenu();

  /**
   * Displays a message asking the user if they want to exit the application.
   */
  void displayExit();

  /**
   * Displays an error message for an invalid command entered by the user.
   *
   * @param command the invalid command entered by the user
   */
  void displayErrorWhileInvalidCommand(String command);

  /**
   * Displays an error message for an unknown command entered by the user.
   *
   * @param command the unknown command entered by the user
   */
  void displayErrorWhileUnknownCommand(String command);

  /**
   * Displays a message indicating that a command entered by the user is being executed.
   *
   * @param command the command entered by the user
   */
  void displayExecutingLine(String command);

  /**
   * Displays an error message for an error that occurred while running a script file.
   *
   * @param error   the error message
   * @param command the command that caused the error
   */
  void displayErrorWhileRunningScriptFile(String error, String command);

}
