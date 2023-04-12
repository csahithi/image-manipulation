package view;

import java.io.PrintStream;

/**
 * The ImageProcessingTextViewImpl class is an implementation of the ImageProcessingTextView
 * interface.It is responsible for displaying the menu, error messages,
 * and executing command entered by the user on the command line.
 */
public class ImageProcessingTextViewImpl implements ImageProcessingTextView {

  private final PrintStream out;

  /**
   * Constructor for the ImageProcessingTextViewImpl class that takes a PrintStream object as a parameter.
   * The PrintStream object is used to write messages to the console.
   *
   * @param out the PrintStream object used to write messages to the console
   */
  public ImageProcessingTextViewImpl(PrintStream out) {
    this.out = out;
  }


  @Override
  public void displayMenu() {
    this.out.println("Please enter your choice: \n 1.Load Image \n 2.Save Image"
            + "\n 3.Brighten Image"
            + "\n 4.Horizontal-flip Image"
            + "\n 5.Vertical-flip Image"
            + "\n 6.RGB-Combine Image"
            + "\n 7.RGB Split"
            + "\n 8.Greyscale "
            + "\n 9.Blur Image"
            + "\n 10.Dither Image"
            + "\n 11.Sepia Image"
            + "\n 12 Sharpen Image"
            + "\n 0.Exit");
  }

  @Override
  public void displayExit() {
    this.out.println("Do you want to exit - [Y/N] : ");
  }

  @Override
  public void displayErrorWhileInvalidCommand(String command) {
    this.out.println("Invalid Command Entered" + " " + command);
  }

  @Override
  public void displayErrorWhileUnknownCommand(String command) {
    this.out.println("Unknown Command Entered" + " " + command);
  }

  @Override
  public void displayExecutingLine(String line) {
    this.out.println("Executing line: " + line);
  }

  @Override
  public void displayErrorWhileRunningScriptFile(String error, String command) {
    this.out.println("Error while running script file: " + error + " " + command);
  }

}
