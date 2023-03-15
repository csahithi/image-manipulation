package model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This class contains tests for ImageProcessing model class.
 */
public class ImageProcessingImplTest {
  private ImageProcessingModel model;

  @Before
  public void setup() {
    model = new ImageProcessingModelImpl();
  }

  @Test
  public void testLoadSamePPMFileInImagesCreateDifferentObjects() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image another = model.loadImage("res/dog.ppm", "another");
    assertNotEquals(original, another);
  }

  @Test
  public void testLoadImageFromExistingPPMFile() {
    Image original = model.loadImage("res/dog.ppm", "original");
    assertEquals(original.getWidth(), 200);
    assertEquals(original.getHeight(), 201);
    assertEquals(original.getMaxValueOfColor(), 255);
  }

  @Test
  public void testLoadImageFromNonExistingPPMFile() {
    Image original = model.loadImage("images/k.ppm", "original");
    assertNull(original);
  }

  @Test
  public void testLoadImageFromNonExistingImage() {
    Image original = model.loadImage("images/k.ppm", "original");
    assertNull(original);
  }

  @Test
  public void testSaveImage() throws IOException {
    Image original = model.loadImage("res/dog.ppm", "original");
    File f = new File("res/Original.ppm");
    assertFalse(f.exists());
    original = model.saveImage("res/Original.ppm", "original");
    assertTrue(f.exists());
    f.delete();
  }


  @Test
  public void testBrightenImage() {
    Image original = model.loadImage("res/dog.ppm", "original");
    int increment = 50;
    Image brightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertTrue(brightenedImage.getPixels()[i][j].colorComponent.redComponent
                - original.getPixels()[i][j].colorComponent.redComponent <= increment);
        assertTrue(brightenedImage.getPixels()[i][j].colorComponent.greenComponent
                - original.getPixels()[i][j].colorComponent.greenComponent <= increment);
        assertTrue(brightenedImage.getPixels()[i][j].colorComponent.blueComponent
                - original.getPixels()[i][j].colorComponent.blueComponent <= increment);
      }
    }
  }

  @Test
  public void testBrightenNullImage() {
    int increment = 50;
    Image brightenedImage = model.brighten(increment, null,
            "brightenedImage");
    assertNull(brightenedImage);
  }

  @Test
  public void testBrightenImageDoesNotExceedMaxValue() {
    Image original = model.loadImage("res/dog.ppm", "original");
    int increment = 50;
    Image brightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertTrue(brightenedImage.getPixels()[i][j].colorComponent.redComponent
                <= brightenedImage.getMaxValueOfColor());
        assertTrue(brightenedImage.getPixels()[i][j].colorComponent.greenComponent
                <= brightenedImage.getMaxValueOfColor());
        assertTrue(brightenedImage.getPixels()[i][j].colorComponent.blueComponent
                <= brightenedImage.getMaxValueOfColor());
      }
    }
  }

  @Test
  public void testBrightenImageIncrementMaxValue() {
    Image original = model.loadImage("res/dog.ppm", "original");
    int increment = 1000;
    Image maxBrightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(255,
                maxBrightenedImage.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(255,
                maxBrightenedImage.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(255,
                maxBrightenedImage.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testBrightenImageIncrementByMaxNegativeValue() {
    Image original = model.loadImage("res/dog.ppm", "original");
    int increment = -1000;
    Image brightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(0, brightenedImage.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(0, brightenedImage.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(0, brightenedImage.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testBrightenImageIncrementByNegativeValue() {
    Image original = model.loadImage("res/dog.ppm", "original");
    int increment = -25;
    Image brightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertTrue(Math.abs(brightenedImage.getPixels()[i][j].colorComponent.redComponent
                - original.getPixels()[i][j].colorComponent.redComponent) <= Math.abs(increment));
        assertTrue(Math.abs(brightenedImage.getPixels()[i][j].colorComponent.greenComponent
                - original.getPixels()[i][j].colorComponent.greenComponent) <= Math.abs(increment));
        assertTrue(Math.abs(brightenedImage.getPixels()[i][j].colorComponent.blueComponent
                - original.getPixels()[i][j].colorComponent.blueComponent) <= Math.abs(increment));
      }
    }
  }

  @Test
  public void testVerticalFlipNullImage() {
    Image verticalFlipImage = model.verticalFlip(null, "verticalFlipImage");
    assertNull(verticalFlipImage);
  }

  @Test
  public void testVerticalFlip() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image verticalFlipFirstTime = model.verticalFlip("original",
            "verticalFlipFirstTime");
    Image verticalFlipSecondTime = model.verticalFlip("verticalFlipFirstTime",
            "verticalFlipSecondTime");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(verticalFlipFirstTime.getPixels()[i][j].colorComponent.redComponent,
                original.getPixels()[original.getHeight() - i - 1][j].colorComponent.redComponent);
        assertEquals(verticalFlipFirstTime.getPixels()[i][j].colorComponent.greenComponent,
                original.getPixels()[original.getHeight() - i - 1][j]
                        .colorComponent.greenComponent);
        assertEquals(verticalFlipFirstTime.getPixels()[i][j].colorComponent.blueComponent,
                original.getPixels()[original.getHeight() - i - 1][j].colorComponent.blueComponent);
      }
    }
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(verticalFlipSecondTime.getPixels()[i][j].colorComponent.redComponent,
                original.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(verticalFlipSecondTime.getPixels()[i][j].colorComponent.greenComponent,
                original.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(verticalFlipSecondTime.getPixels()[i][j].colorComponent.blueComponent,
                original.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testHorizontalFlipNullImage() {
    Image horizontalFlipImage = model.horizontalFlip(null,
            "horizontalFlipImage");
    assertNull(horizontalFlipImage);
  }

  @Test
  public void testHorizontalFlip() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image horizontalFlipFirstTime = model.horizontalFlip("original",
            "horizontalFlipFirstTime");
    Image horizontalFlipSecondTime = model.horizontalFlip("horizontalFlipFirstTime",
            "horizontalFlipSecondTime");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(horizontalFlipFirstTime.getPixels()[i][j].colorComponent.redComponent,
                original.getPixels()[i][original.getWidth() - 1 - j].colorComponent.redComponent);
        assertEquals(horizontalFlipFirstTime.getPixels()[i][j].colorComponent.greenComponent,
                original.getPixels()[i][original.getWidth() - 1 - j].colorComponent.greenComponent);
        assertEquals(horizontalFlipFirstTime.getPixels()[i][j].colorComponent.blueComponent,
                original.getPixels()[i][original.getWidth() - 1 - j].colorComponent.blueComponent);
      }
    }
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(horizontalFlipSecondTime.getPixels()[i][j].colorComponent.redComponent,
                original.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(horizontalFlipSecondTime.getPixels()[i][j].colorComponent.greenComponent,
                original.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(horizontalFlipSecondTime.getPixels()[i][j].colorComponent.blueComponent,
                original.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnRedComponentNullImage() {
    Image greyscaleOnRedComponent = model.greyscale("red-component",
            null, "greyscaleOnRedComponent");
    assertNull(greyscaleOnRedComponent);
  }

  @Test
  public void testGreyScaleOnRedComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image greyscaleOnRedComponent = model.greyscale("red-component",
            "original", "greyscaleOnRedComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent,
                greyscaleOnRedComponent.getPixels()[i][j].colorComponent.blueComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent,
                greyscaleOnRedComponent.getPixels()[i][j].colorComponent.greenComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnGreenComponentNullImage() {
    Image greyscaleOnGreenComponent = model.greyscale("green-component",
            null, "greyscaleOnGreenComponent");
    assertNull(greyscaleOnGreenComponent);
  }

  @Test
  public void testGreyScaleOnGreenComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image greyscaleOnGreenComponent = model.greyscale("green-component",
            "original", "greyscaleOnGreenComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.greenComponent,
                greyscaleOnGreenComponent.getPixels()[i][j].colorComponent.blueComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.greenComponent,
                greyscaleOnGreenComponent.getPixels()[i][j].colorComponent.redComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnBlueComponentNullImage() {
    Image greyscaleOnBlueComponent = model.greyscale("blue-component",
            null, "greyscaleOnBlueComponent");
    assertNull(greyscaleOnBlueComponent);
  }

  @Test
  public void testGreyScaleOnBlueComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image greyscaleOnBlueComponent = model.greyscale("blue-component",
            "original", "greyscaleOnBlueComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.blueComponent,
                greyscaleOnBlueComponent.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.blueComponent,
                greyscaleOnBlueComponent.getPixels()[i][j].colorComponent.greenComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnLumaComponentOnNullImage() {
    Image greyscaleOnLumaComponent = model.greyscale("luma-component",
            null, "greyscaleOnLumaComponent");
    assertNull(greyscaleOnLumaComponent);
  }

  @Test
  public void testGreyScaleOnLumaComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image greyscaleOnLumaComponent = model.greyscale("luma-component",
            "original", "greyscaleOnLumaComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].getLumaComponent(),
                greyscaleOnLumaComponent.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].getLumaComponent(),
                greyscaleOnLumaComponent.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(original.getPixels()[i][j].getLumaComponent(),
                greyscaleOnLumaComponent.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnValueComponentOnNullImage() {
    Image greyscaleOnValueComponent = model.greyscale("value-component",
            null, "greyscaleOnValueComponent");
    assertNull(greyscaleOnValueComponent);
  }

  @Test
  public void testGreyScaleOnValueComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image greyscaleOnValueComponent = model.greyscale("value-component",
            "original", "greyscaleOnValueComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].getValueComponent(),
                greyscaleOnValueComponent.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].getValueComponent(),
                greyscaleOnValueComponent.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(original.getPixels()[i][j].getValueComponent(),
                greyscaleOnValueComponent.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnIntensityComponentOnNullImage() {
    Image greyscaleOnIntensityComponent = model.greyscale("intensity-component",
            null, "greyscaleOnIntensityComponent");
    assertNull(greyscaleOnIntensityComponent);
  }

  @Test
  public void testGreyScaleOnIntensityComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image greyscaleOnIntensityComponent = model.greyscale("intensity-component",
            "original", "greyscaleOnIntensityComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].getIntensityComponent(),
                greyscaleOnIntensityComponent.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].getIntensityComponent(),
                greyscaleOnIntensityComponent.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(original.getPixels()[i][j].getIntensityComponent(),
                greyscaleOnIntensityComponent.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testRGBSplit() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = model.rgbSplit("original", "redImage",
            "greenImage", "blueImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent,
                redImage.getPixels()[i][j].colorComponent.blueComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent,
                redImage.getPixels()[i][j].colorComponent.greenComponent);
      }
    }
  }

  @Test
  public void testRGBCombineOnNullRedImage() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = null;
    Image greenImage = model.greyscale("green-component",
            "original", "greenImage");
    Image blueImage = model.greyscale("blue-component",
            "original", "blueImage");
    Image resultImageRed = model.rgbCombine("resultImageRed", "redImage",
            "greenImage", "blueImage");
    assertNull(resultImageRed);
  }

  @Test
  public void testRGBCombineOnNullGreenImage() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = model.greyscale("red-component",
            "original", "redImage");
    Image greenImage = null;
    Image blueImage = model.greyscale("blue-component",
            "original", "blueImage");
    Image resultImageGreen = model.rgbCombine("resultImageGreen",
            "redImage", "greenImage", "blueImage");
    assertNull(resultImageGreen);
  }

  @Test
  public void testRGBCombineOnNullBlueImage() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = model.greyscale("red-component",
            "original", "redImage");
    Image greenImage = model.greyscale("green-component",
            "original", "greenImage");
    Image blueImage = null;
    Image resultImageBlue = model.rgbCombine("resultImageBlue",
            "redImage", "greenImage", "blueImage");
    assertEquals(resultImageBlue, null);
  }

  @Test
  public void testRGBCombine() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = model.greyscale("red-component",
            "original", "redImage");
    Image greenImage = model.greyscale("green-component",
            "original", "greenImage");
    Image blueImage = model.greyscale("blue-component",
            "original", "blueImage");
    Image rgbCombinedImage = model.rgbCombine("rgbCombinedImage",
            "redImage", "greenImage", "blueImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent,
                rgbCombinedImage.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.greenComponent,
                rgbCombinedImage.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.blueComponent,
                rgbCombinedImage.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }
}