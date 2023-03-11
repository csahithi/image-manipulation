package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method
 * as required.
 */
public class ReadPPMUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static ImageProcessingModel readPPM(String filename) {
    Scanner sc;
    ImageProcessingModel m;
    Pixel[][] listOfPixels;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }


    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
//    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
//    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
//    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    listOfPixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
//        load images/koala.ppm k
        listOfPixels[i][j]= new Pixel(j,i,sc.nextInt(),sc.nextInt(),sc.nextInt());
      }
    }
    m = new ImageProcessingImpl(width, height, maxValue, listOfPixels);
    return m;
  }


//  //demo main
//  public static void main(String[] args) {
//    String filename;
//
//    if (args.length > 0) {
//      filename = args[0];
//    } else {
//      filename = "koala.ppm";
//    }
//
//    ReadPPMUtil.readPPM(filename);
//  }
}

