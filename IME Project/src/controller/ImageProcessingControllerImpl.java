package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

import controller.commands.BrightenImage;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageCommandController;
import controller.commands.LoadImage;
import controller.commands.RGBCombine;
import controller.commands.RGBSplit;
import controller.commands.SaveImage;
import controller.commands.VerticalFlip;
import model.Image;
import model.ImageProcessingModel;
import model.Pixel;

public class ImageProcessingControllerImpl implements ImageProcessingController {
  private ImageProcessingModel model;
  private InputStream in;
  private OutputStream out;

  public ImageProcessingControllerImpl(ImageProcessingModel model, InputStream in, OutputStream out) {
    this.model = model;
    this.in = in;
    this.out = out;
  }

  public void readCommands(String[] inputArray, String command) throws IOException {
    //Image m = null;
    ImageCommandController cmd = null;
    PrintStream outStream = new PrintStream(this.out);
    if (inputArray.length == 2 && inputArray[0].equals("run")) {
      try {
        BufferedReader reader = new BufferedReader(new FileReader(inputArray[1]));
        String line = reader.readLine();
        while (line != null) {
          System.out.println("Executing line: " + line);
          String[] runScriptInputArray = line.split(" ");
          runScriptInputArray = Arrays.stream(runScriptInputArray)
                  .filter(Predicate.not(String::isEmpty))
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
              //model.loadImage(inputArray[1], inputArray[2]);
              //m = new Image(0, 0, 0, new Pixel[0][0]);
              break;
            case "save":
              cmd = new SaveImage(inputArray[1], inputArray[2]);
              //model.saveImage(inputArray[1], inputArray[2]);
              //m = listOfImages.getOrDefault(inputArray[2], null);
              break;
            case "brighten":
              cmd = new BrightenImage(Integer.parseInt(inputArray[1]), inputArray[2], inputArray[3]);
              //model.brighten(Integer.parseInt(inputArray[1]), inputArray[2], inputArray[3]);
              //m = listOfImages.getOrDefault(inputArray[2], null);
              break;
            case "horizontal-flip":
              cmd = new HorizontalFlip(inputArray[1], inputArray[2]);
//              m = listOfImages.getOrDefault(inputArray[1], null);
              //model.horizontalFlip(inputArray[1], inputArray[2]);
              break;
            case "vertical-flip":
              cmd = new VerticalFlip(inputArray[1], inputArray[2]);
//              m = listOfImages.getOrDefault(inputArray[1], null);
              //model.verticalFlip(inputArray[1], inputArray[2]);
              break;
            case "greyscale":
              cmd = new Greyscale(inputArray[1], inputArray[2], inputArray[3]);
//              m = listOfImages.getOrDefault(inputArray[2], null);
             // model.greyscale(inputArray[1], inputArray[2], inputArray[3]);
              break;
            case "rgb-split":
              cmd = new RGBSplit(inputArray[1], inputArray[2], inputArray[3], inputArray[4]);
//              m = listOfImages.getOrDefault(inputArray[1], null);
            //  model.rgbSplit(inputArray[1], inputArray[2], inputArray[3], inputArray[4]);
              break;
            case "rgb-combine":
//              Image mRed = listOfImages.getOrDefault(inputArray[2], null);
//              Image mGreen = listOfImages.getOrDefault(inputArray[3], null);
//              Image mBlue = listOfImages.getOrDefault(inputArray[4], null);
              cmd = new RGBCombine(inputArray[1], inputArray[2], inputArray[3], inputArray[4]);
//              m = new Image(0, 0, 0, new Pixel[0][0]);
//              model.rgbSplit(inputArray[1], inputArray[2], inputArray[3], inputArray[4]);
              break;
            default:
              outStream.println(String.format("Unknown command %s", command));
              return;
          }
        } catch (NumberFormatException e) {
          outStream.println(String.format("Unknown command %s", command));
          return;
        }
      } else {
        outStream.println(String.format("Unknown command %s", command));
        return;
      }
    }
    if (cmd != null) {
      cmd.go(model);
    } else {
      System.out.println(String.format("Unknown command %s", command));
    }
  }

  @Override
  public void go() throws IOException {
    String[] inputArray;
    Scanner scan = new Scanner(this.in);
//    Map<String, Function<Scanner, ImageCommandController>> knownCommands = new HashMap<>();
//    knownCommands.put("load", s -> new LoadImage(s.next(), s.next()));
//    knownCommands.put("save", s -> new SaveImage(s.next(), s.next()));
//    knownCommands.put("brighten", s -> new BrightenImage(s.nextInt(), s.next(), s.next()));
//    knownCommands.put("horizontal-flip", s -> new HorizontalFlip(s.next(), s.next()));
//    knownCommands.put("vertical-flip", s -> new VerticalFlip(s.next(), s.next()));
//    knownCommands.put("greyscale", s -> new Greyscale(s.next(), s.next(), s.next()));
//    knownCommands.put("rgb-split", s -> new RGBSplit(s.next(), s.next(), s.next(), s.next()));
//    knownCommands.put("rgb-combine", s -> new RGBCombine(s.next(), s.next(), s.next(), s.next()));
//    while (scan.hasNext()) {
//      try {
//        ImageCommandController c;
//        String in = scan.next();
//        if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit"))
//          return;
//        if (in.equalsIgnoreCase("run")){
//         // BufferedReader reader = new BufferedReader(new FileReader(scan.next()));
//          try {
//            knownCommands.put("load", (BufferedReader reader) -> {
//              try {
//                return new LoadImage(reader.readLine());
//              } catch (IOException e) {
//                return null;
//              }
//            });
//            knownCommands.put("save", s -> {
//              try {
//                return new SaveImage(reader.readLine());
//              } catch (IOException e) {
//                return null;              }
//            });
//            knownCommands.put("brighten", s -> new BrightenImage(s.nextInt(), s.next(), s.next()));
//            knownCommands.put("horizontal-flip", s -> new HorizontalFlip(s.next(), s.next()));
//            knownCommands.put("vertical-flip", s -> new VerticalFlip(s.next(), s.next()));
//            knownCommands.put("greyscale", s -> new Greyscale(s.next(), s.next(), s.next()));
//            knownCommands.put("rgb-split", s -> new RGBSplit(s.next(), s.next(), s.next(), s.next()));
//            knownCommands.put("rgb-combine", s -> new RGBCombine(s.next(), s.next(), s.next(), s.next()));
//          }
//          catch (IOException e){
//
//          }
//          String line = reader.readLine();
//          while (line != null) {
//            System.out.println("Executing line: " + line);
//            String[] runScriptInputArray = line.split(" ");
//            runScriptInputArray = Arrays.stream(runScriptInputArray)
//                    .filter(Predicate.not(String::isEmpty))
//                    .toArray(String[]::new);
//            readCommands(runScriptInputArray, line);
//            line = reader.readLine();
//          }
//        reader.close();
//        }
//        Function<Scanner, ImageCommandController> cmd = knownCommands.getOrDefault(in, null);
//        if (cmd == null) {
//          //throw new IllegalArgumentException();
//          System.out.println(String.format("Invalid command"));
//        } else {
//          c = cmd.apply(scan);
//          if(c==null){
//            System.out.println(String.format("Invalid command"));
//          }
//          if(c.go(model)==null){
//            System.out.println(String.format("Invalid command"));
//          };
//        }
//      } catch (Exception e) {
//        System.out.println(String.format("Invalid command"));
//      }
      while (scan.hasNext()) {
        String input = scan.nextLine();
        inputArray = input.split(" ");
        inputArray = Arrays.stream(inputArray)
                .filter(Predicate.not(String::isEmpty))
                .toArray(String[]::new);
        if (inputArray.length > 0) {
          readCommands(inputArray, input);
      }
    }
  }
}