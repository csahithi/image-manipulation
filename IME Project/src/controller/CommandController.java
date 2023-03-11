package controller;

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
import controller.commands.RunScriptFile;
import controller.commands.SaveImage;
import controller.commands.VerticalFlip;
import model.ImageProcessingImpl;
import model.ImageProcessingModel;
import model.Pixel;

public class CommandController {
  public static Map<String, ImageProcessingModel> listOfImages = new HashMap<>();

  public static void main(String[] args) throws IOException {
    ImageProcessingModel m = null;
    String[] inputArray;
    IController cmd = null;
    Scanner scan = new Scanner(System.in);

//    Map<String, Function<Scanner, IController>> knownCommands = new HashMap<>();
//    knownCommands.put("load",s->new LoadImage();
//    knownCommands.put("brighten",s->new BrightenImage(s.nextInt(),m));
//    knownCommands.put("horizontal-flip",s->new HorizontalFlip(m));
//    knownCommands.put("vertical-flip", s->new VerticalFlip(m));


    while (scan.hasNext()) {
      String in = scan.nextLine();
      inputArray = in.split(" ");
      inputArray = Arrays.stream(inputArray).filter(Predicate.not(String::isEmpty))
              .toArray(String[]::new);
      if (inputArray.length == 2 && inputArray[0].equals("run")) {
        m = new ImageProcessingImpl(0, 0, 0, new Pixel[0][0]);
        cmd = new RunScriptFile(inputArray[1]);

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
//            case "run":
//              m = new ImageProcessingImpl(0, 0, 0, new Pixel[0][0]);
//              cmd=new RunScriptFile(inputArray[1]);

              default:
                System.out.println(String.format("Unknown command %s", in));
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
        cmd = null;
        m = null;
      } else {
        throw new UnsupportedOperationException();
      }
//      Function<Scanner, IController> cmd = knownCommands.getOrDefault(inputArray[0], null);
//      if (cmd == null) {
//        throw new UnsupportedOperationException();
//      } else {
//        //c = cmd.apply(scan);
//        c.go(m);
//      }
    }
  }
}