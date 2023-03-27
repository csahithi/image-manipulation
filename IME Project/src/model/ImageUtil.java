package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ImageUtil {
  public static Image readImage(String filepath) {
    try {
      BufferedImage image = ImageIO.read(new File(filepath));
      Pixel[][] listOfPixels = new Pixel[image.getHeight()][image.getWidth()];
      Image finalImage = new Image(image.getWidth(), image.getHeight(), 255, listOfPixels);
      for (int y = 0; y < image.getHeight(); y++) {
        for (int x = 0; x < image.getWidth(); x++) {
          java.awt.Color rgb = new java.awt.Color(image.getRGB(x, y));
          try {
            listOfPixels[y][x] = new Pixel(y, x, rgb.getRed(), rgb.getGreen(), rgb.getBlue());
          } catch (NullPointerException e) {
            System.out.println("y" + y + "x" + x);
          }
        }
      }
      return finalImage;
    } catch (FileNotFoundException e) {
      System.out.println("File " + filepath + " not found!");
      return null;
    } catch (IOException e) {
      System.out.println("Unable to read file " + filepath);
      return null;
    }
  }

  public static void createImage(String imageExtension, String filepath, Image m) {
    try {
      BufferedImage image = new BufferedImage(m.getWidth(), m.getHeight(), BufferedImage.TYPE_INT_RGB);
      Pixel[][] listOfPixels = m.getPixels();
      for (int y = 0; y < image.getHeight(); y++) {
        for (int x = 0; x < image.getWidth(); x++) {
          java.awt.Color rgb = new Color(listOfPixels[y][x].colorComponent.redComponent,
                  listOfPixels[y][x].colorComponent.greenComponent,
                  listOfPixels[y][x].colorComponent.blueComponent);
          try {
            image.setRGB(x, y, rgb.getRGB());
          } catch (NullPointerException e) {
            System.out.println("y" + y + "x" + x);
          }
        }
      }
      ImageIO.write(image, imageExtension, new File(filepath));
    } catch (IOException e) {
      System.out.println("Unable to create file " + filepath);
    }
  }

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
      myWriter.write("P3\n"
              + "# Created by GIMP version 2.10.20 PNM plug-in\n");
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
