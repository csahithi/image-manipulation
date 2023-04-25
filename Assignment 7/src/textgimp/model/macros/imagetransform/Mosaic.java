package textgimp.model.macros.imagetransform;

import java.util.Random;

import textgimp.model.betterimage.GenericImage;
import textgimp.model.betterimage.Image;
import textgimp.model.betterimage.Pixel;
import textgimp.model.macros.AbstractMacro;
import textgimp.model.macros.Macro;
import textgimp.utility.ResultImpl;


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
    this.validateImage(sourceImage);
    if (numSeeds < 0) {
      throw new IllegalArgumentException();
    }
    Pixel[][] newPixels = new Pixel[sourceImage.getHeight()][sourceImage.getWidth()];
    int[][] seeds = getSeeds(sourceImage.getHeight(), sourceImage.getWidth(), numSeeds);
    for (int x = 0; x < sourceImage.getHeight(); x++) {
      for (int y = 0; y < sourceImage.getWidth(); y++) {
        double minDistance = Double.MAX_VALUE;
        int nearestSeedX = 0;
        int nearestSeedY = 0;
        for (int i = 0; i < numSeeds; i++) {
          double distance = getDistance(x, y, seeds[i][0], seeds[i][1]);
          if (distance < minDistance) {
            minDistance = distance;
            nearestSeedX = seeds[i][0];
            nearestSeedY = seeds[i][1];
          }
        }
        newPixels[x][y] = sourceImage.getPixel(nearestSeedX, nearestSeedY);
      }
    }
    return new GenericImage(newPixels, sourceImage.getMaxValue(), sourceImage.getImageType());
  }

  private double getDistance(int x1, int y1, int x2, int y2) {
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }

  private int[][] getSeeds(int height, int width, int numSeeds) {
    Random rand = new Random();
    int[][] seeds = new int[numSeeds][2];
    for (int i = 0; i < numSeeds; i++) {
      seeds[i][0] = rand.nextInt(height);
      seeds[i][1] = rand.nextInt(width);
    }
    return seeds;
  }
}