package controller;

import java.util.Scanner;



import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import controller.commands.BrightenImage;
import controller.commands.HorizontalFlip;
import controller.commands.LoadImage;
import controller.commands.VerticalFlip;
>>>>>>> Stashed changes
import model.ImageProcessingModel;



public class CommandController {
  public static void main(String[] args) {
    ImageProcessingModel m=null;
    String[] inputArray
    Scanner scan = new Scanner(System.in);


    Map<String, Function<Scanner, IController>> knownCommands = new HashMap<>();
    knownCommands.put("load",s->new LoadImage();
    knownCommands.put("brighten",s->new BrightenImage(s.nextInt(),m));
    knownCommands.put("horizontal-flip",s->new HorizontalFlip(m));
    knownCommands.put("vertical-flip", s->new VerticalFlip(m));

    while(scan.hasNext()) {
      IController c;
      String in = scan.next();
      inputArray = in.split(" ");
      Function<Scanner, IController> cmd = knownCommands.getOrDefault(inputArray[0], null);
      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        c = cmd.apply(scan);
        c.go(m);
      }
    }

  }
}