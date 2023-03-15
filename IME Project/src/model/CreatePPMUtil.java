package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreatePPMUtil {
  public static void createPPM(String filepath, Image m) {
    try {
      File myObj = new File(filepath);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    try {
      FileWriter myWriter = new FileWriter(filepath, false);
      myWriter.write("P3\n" +
              "# Created by GIMP version 2.10.20 PNM plug-in\n");
      myWriter.write(m.getWidth() + " " + m.getHeight() + "\n" + m.getMaxValueOfColor() + "\n");
      Pixel[][] listOfPixels = m.getPixels();
      for (int i = 0; i < m.getHeight(); i++) {
        for (int j = 0; j < m.getWidth(); j++) {
          if (listOfPixels[i][j] == null || listOfPixels[i][j].colorComponent == null) {
            return;
          }
          myWriter.write(listOfPixels[i][j].colorComponent.redComponent + "\n"
                  + listOfPixels[i][j].colorComponent.greenComponent + "\n"
                  + listOfPixels[i][j].colorComponent.blueComponent + "\n");
        }
      }
      myWriter.close();
      System.out.println("Successfully wrote to the file." + filepath);
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}
