package textgimp.model.macros.imagetransform;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import textgimp.model.Point;
import textgimp.model.betterimage.GenericImage;
import textgimp.model.betterimage.Image;
import textgimp.model.betterimage.Pixel;
import textgimp.model.macros.AbstractMacro;
import textgimp.model.macros.Macro;


/**
 * This class represents a Mosaic Flip macro.
 */
public class Mosaic extends AbstractMacro implements Macro {
  public Mosaic(int numSeeds) {
    this.numSeeds = numSeeds;
  }

  private final int numSeeds;

  @Override
  public Image apply(Image sourceImage) throws IllegalArgumentException {
    // validate the image
    this.validateImage(sourceImage);

    // read the image properties
    int maxValue = sourceImage.getMaxValue();
    int height = sourceImage.getHeight();
    int width = sourceImage.getWidth();
    Random rand = new Random();
    List<Point> seeds = new ArrayList<Point>();

    // Generate the seeds randomly
    for (int i = 0; i < numSeeds; i++) {
      int x = rand.nextInt(width);
      int y = rand.nextInt(height);
      Point seed = new Point(x, y);
      seeds.add(seed);
    }

    // Create the output image pixels array.
    Pixel[][] newPixels = new Pixel[height][width];

    // Replace each pixel with the color of its nearest seed
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        // Get the color of the current pixel
        Pixel pixel = sourceImage.getPixel(x, y);
        Point thisPoint = new Point(x, y);
        // Find the nearest seed to the current pixel
        double minDistance = Double.MAX_VALUE;
        Point nearestSeed = null;
        for (Point seed : seeds) {
          double distance = seed.distanceTo(thisPoint);
          if (distance < minDistance) {
            minDistance = distance;
            nearestSeed = seed;
          }
        }

        // Set the color of the current pixel to the color of the nearest seed
        newPixels[x][y] = sourceImage.getPixel(nearestSeed.getX(), nearestSeed.getY());
      }
    }

    // Save the output image
    return new GenericImage(newPixels, maxValue, sourceImage.getImageType());
  }

}