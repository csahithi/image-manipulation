package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

import controller.commands.BrightenImage;
import controller.commands.GreyScale;
import controller.commands.HorizontalFlip;
import controller.commands.LoadImage;
import controller.commands.RGBCombine;
import controller.commands.RGBSplit;
import controller.commands.SaveImage;
import controller.commands.VerticalFlip;
import model.ImageProcessingImpl;
import model.ImageProcessingModel;
import model.Pixel;

public class CommandController {
  public static Map<String, ImageProcessingModel> listOfImages = new HashMap<>();

  public static void main(String[] args) throws IOException {
    String[] inputArray;
    Scanner scan = new Scanner(System.in);
    while (scan.hasNext()) {
      String in = scan.nextLine();
      inputArray = in.split(" ");
      inputArray = Arrays.stream(inputArray).filter(Predicate.not(String::isEmpty))
              .toArray(String[]::new);
      readCommands(inputArray, in);
    }
  }

  public static void readCommands(String[] inputArray, String command) throws IOException {
    ImageProcessingModel m = null;
    IController cmd = null;
    if (inputArray.length == 2 && inputArray[0].equals("run")) {
      try {
        BufferedReader reader = new BufferedReader(new FileReader(inputArray[1]));
        String line = reader.readLine();
        while (line != null) {
          System.out.println("Executing line: " + line);
          String[] runScriptInputArray = line.split(" ");
          runScriptInputArray = Arrays.stream(runScriptInputArray).filter(Predicate.not(String::isEmpty))
                  .toArray(String[]::new);
          readCommands(runScriptInputArray, line);
          line = reader.readLine();
        }
        reader.close();
        return;
      } catch (Exception e) {
        System.err.println("Error while running script file: " + e.getMessage());
      }
    } else {
      if (inputArray.length >= 3) {
        try {
          switch (inputArray[0]) {
            case "load":
              cmd = new LoadImage(inputArray[1], inputArray[2]);
              m = new ImageProcessingImpl(0, 0, 0, new Pixel[0][0]);
              break;
            case "save":
              cmd = new SaveImage(inputArray[1]);
              m = listOfImages.getOrDefault(inputArray[2], null);
              break;

            case "brighten":
              cmd = new BrightenImage(Integer.parseInt(inputArray[1]), inputArray[3]);
              m = listOfImages.getOrDefault(inputArray[2], null);
              break;
            case "horizontal-flip":
              cmd = new HorizontalFlip(inputArray[2]);
              m = listOfImages.getOrDefault(inputArray[1], null);
              break;
            case "vertical-flip":
              cmd = new VerticalFlip(inputArray[2]);
              m = listOfImages.getOrDefault(inputArray[1], null);
              break;
            case "greyscale":
              cmd = new GreyScale(inputArray[1], inputArray[3]);
              m = listOfImages.getOrDefault(inputArray[2], null);
              break;
            case "rgb-split":
              cmd = new RGBSplit(inputArray[2], inputArray[3], inputArray[4]);
              m = listOfImages.getOrDefault(inputArray[1], null);
              break;
            case "rgb-combine":
              ImageProcessingModel mRed = listOfImages.getOrDefault(inputArray[2], null);
              ImageProcessingModel mGreen = listOfImages.getOrDefault(inputArray[3], null);
              ImageProcessingModel mBlue = listOfImages.getOrDefault(inputArray[4], null);
              cmd = new RGBCombine(inputArray[1], mRed, mGreen, mBlue);
              m = new ImageProcessingImpl(0, 0, 0, new Pixel[0][0]);
              break;
            default:
              System.out.println(String.format("Unknown command %s", command));
              cmd = null;
              break;
          }
        } catch (NumberFormatException e) {
          throw new UnsupportedOperationException();
        }
      }
    }
    if (cmd != null && m != null) {
      cmd.go(m);
    } else {
      throw new UnsupportedOperationException();
    }
  }
}