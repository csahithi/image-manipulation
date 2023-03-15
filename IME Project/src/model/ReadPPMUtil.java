package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 */
public class ReadPPMUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Image readPPM(String filename) {
    Scanner sc;
    Image m;
    Pixel[][] listOfPixels;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    listOfPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        listOfPixels[i][j] = new Pixel(j, i, sc.nextInt(), sc.nextInt(), sc.nextInt());
      }
    }
    m = new Image(width, height, maxValue, listOfPixels);
    return m;
  }
}

