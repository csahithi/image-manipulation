package controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

import controller.commands.BrightenImage;
import controller.commands.HorizontalFlip;
import controller.commands.LoadImage;
import model.ImageProcessingImpl;
import model.ImageProcessingModel;
import model.Pixel;

public class CommandController {
  public static Map<String, ImageProcessingModel> listOfImages = new HashMap<>();

  public static void main(String[] args) {
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
      String in = scan.next();
      inputArray = in.split(" ");
      inputArray = Arrays.stream(inputArray).filter(Predicate.not(String::isEmpty))
              .toArray(String[]::new);
      if (inputArray.length >= 3) {
        try {
          switch (in) {
            case "load":
              cmd = new LoadImage(inputArray[1], inputArray[2]);
              m = new ImageProcessingImpl(0, 0, 0, new Pixel[0][0]);
              break;
            case "brighten":
              cmd = new BrightenImage(Integer.parseInt(inputArray[1]), inputArray[3]);
              m = listOfImages.getOrDefault(inputArray[2], null);
              break;
            case "horizontal-flip":
              cmd = new HorizontalFlip(inputArray[2]);
              m = listOfImages.getOrDefault(inputArray[1], null);
              break;
            default:
              System.out.println(String.format("Unknown command %s", in));
              cmd = null;
              break;
          }
        } catch (NumberFormatException e) {
          throw new UnsupportedOperationException();
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