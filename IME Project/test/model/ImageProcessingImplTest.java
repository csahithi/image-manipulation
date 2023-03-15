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
    Image brightenedImage = model.brighten(increment, "original"
            , "brightenedImage");
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
    Image brightenedImage = model.brighten(increment, null
            , "brightenedImage");
    assertNull(brightenedImage);
  }

  @Test
  public void testBrightenImageDoesNotExceedMaxValue() {
    Image original = model.loadImage("res/dog.ppm", "original");
    int increment = 50;
    Image brightenedImage = model.brighten(increment, "original"
            , "brightenedImage");
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
    Image maxBrightenedImage = model.brighten(increment, "original"
            , "brightenedImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(255
                , maxBrightenedImage.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(255
                , maxBrightenedImage.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(255
                , maxBrightenedImage.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testBrightenImageIncrementByMaxNegativeValue() {
    Image original = model.loadImage("res/dog.ppm", "original");
    int increment = -1000;
    Image brightenedImage = model.brighten(increment, "original"
            , "brightenedImage");
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
    Image brightenedImage = model.brighten(increment, "original"
            , "brightenedImage");
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
    Image VerticalFlipImage = model.verticalFlip(null, "VerticalFlipImage");
    assertNull(VerticalFlipImage);
  }

  @Test
  public void testVerticalFlip() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image VerticalFlipFirstTime = model.verticalFlip("original"
            , "VerticalFlipFirstTime");
    Image VerticalFlipSecondTime = model.verticalFlip("VerticalFlipFirstTime"
            , "VerticalSecondTime");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(VerticalFlipFirstTime.getPixels()[i][j].colorComponent.redComponent
                , original.getPixels()[original.getHeight() - i - 1][j].colorComponent.redComponent);
        assertEquals(VerticalFlipFirstTime.getPixels()[i][j].colorComponent.greenComponent
                , original.getPixels()[original.getHeight() - i - 1][j].colorComponent.greenComponent);
        assertEquals(VerticalFlipFirstTime.getPixels()[i][j].colorComponent.blueComponent
                , original.getPixels()[original.getHeight() - i - 1][j].colorComponent.blueComponent);
      }
    }
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(VerticalFlipSecondTime.getPixels()[i][j].colorComponent.redComponent
                , original.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(VerticalFlipSecondTime.getPixels()[i][j].colorComponent.greenComponent
                , original.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(VerticalFlipSecondTime.getPixels()[i][j].colorComponent.blueComponent
                , original.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testHorizontalFlipNullImage() {
    Image HorizontalFlipImage = model.horizontalFlip(null, "HorizontalFlipImage");
    assertNull(HorizontalFlipImage);
  }

  @Test
  public void testHorizontalFlip() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image horizontalFlipFirstTime = model.horizontalFlip("original"
            , "horizontalFlipFirstTime");
    Image horizontalFlipSecondTime = model.horizontalFlip("horizontalFlipFirstTime"
            , "horizontalFlipSecondTime");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(horizontalFlipFirstTime.getPixels()[i][j].colorComponent.redComponent
                , original.getPixels()[i][original.getWidth() - 1 - j].colorComponent.redComponent);
        assertEquals(horizontalFlipFirstTime.getPixels()[i][j].colorComponent.greenComponent
                , original.getPixels()[i][original.getWidth() - 1 - j].colorComponent.greenComponent);
        assertEquals(horizontalFlipFirstTime.getPixels()[i][j].colorComponent.blueComponent
                , original.getPixels()[i][original.getWidth() - 1 - j].colorComponent.blueComponent);
      }
    }
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(horizontalFlipSecondTime.getPixels()[i][j].colorComponent.redComponent
                , original.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(horizontalFlipSecondTime.getPixels()[i][j].colorComponent.greenComponent
                , original.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(horizontalFlipSecondTime.getPixels()[i][j].colorComponent.blueComponent
                , original.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnRedComponentNullImage() {
    Image GreyScaleOnRedComponent = model.greyscale("red-component"
            , null, "GreyScaleOnRedComponent");
    assertNull(GreyScaleOnRedComponent);
  }

  @Test
  public void testGreyScaleOnRedComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image GreyScaleOnRedComponent = model.greyscale("red-component"
            , "original", "GreyScaleOnRedComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent
                , GreyScaleOnRedComponent.getPixels()[i][j].colorComponent.blueComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent
                , GreyScaleOnRedComponent.getPixels()[i][j].colorComponent.greenComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnGreenComponentNullImage() {
    Image GreyScaleOnGreenComponent = model.greyscale("green-component"
            , null, "GreyScaleOnGreenComponent");
    assertNull(GreyScaleOnGreenComponent);
  }

  @Test
  public void testGreyScaleOnGreenComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image GreyScaleOnGreenComponent = model.greyscale("green-component"
            , "original", "GreyScaleOnGreenComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.greenComponent
                , GreyScaleOnGreenComponent.getPixels()[i][j].colorComponent.blueComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.greenComponent
                , GreyScaleOnGreenComponent.getPixels()[i][j].colorComponent.redComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnBlueComponentNullImage() {
    Image GreyScaleOnBlueComponent = model.greyscale("blue-component"
            , null, "GreyScaleOnBlueComponent");
    assertNull(GreyScaleOnBlueComponent);
  }

  @Test
  public void testGreyScaleOnBlueComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image GreyScaleOnBlueComponent = model.greyscale("blue-component"
            , "original", "GreyScaleOnBlueComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.blueComponent
                , GreyScaleOnBlueComponent.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.blueComponent
                , GreyScaleOnBlueComponent.getPixels()[i][j].colorComponent.greenComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnLumaComponentOnNullImage() {
    Image GreyScaleOnLumaComponent = model.greyscale("luma-component"
            , null, "GreyScaleOnLumaComponent");
    assertNull(GreyScaleOnLumaComponent);
  }

  @Test
  public void testGreyScaleOnLumaComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image GreyScaleOnLumaComponent = model.greyscale("luma-component"
            , "original", "GreyScaleOnLumaComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].getLumaComponent()
                , GreyScaleOnLumaComponent.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].getLumaComponent()
                , GreyScaleOnLumaComponent.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(original.getPixels()[i][j].getLumaComponent()
                , GreyScaleOnLumaComponent.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnValueComponentOnNullImage() {
    Image GreyScaleOnValueComponent = model.greyscale("value-component"
            , null, "GreyScaleOnValueComponent");
    assertNull(GreyScaleOnValueComponent);
  }

  @Test
  public void testGreyScaleOnValueComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image GreyScaleOnValueComponent = model.greyscale("value-component"
            , "original", "GreyScaleOnValueComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].getValueComponent()
                , GreyScaleOnValueComponent.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].getValueComponent()
                , GreyScaleOnValueComponent.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(original.getPixels()[i][j].getValueComponent()
                , GreyScaleOnValueComponent.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testGreyScaleOnIntensityComponentOnNullImage() {
    Image GreyScaleOnIntensityComponent = model.greyscale("intensity-component"
            , null, "GreyScaleOnIntensityComponent");
    assertNull(GreyScaleOnIntensityComponent);
  }

  @Test
  public void testGreyScaleOnIntensityComponent() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image GreyScaleOnIntensityComponent = model.greyscale("intensity-component"
            , "original", "GreyScaleOnIntensityComponent");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].getIntensityComponent()
                , GreyScaleOnIntensityComponent.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].getIntensityComponent()
                , GreyScaleOnIntensityComponent.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(original.getPixels()[i][j].getIntensityComponent()
                , GreyScaleOnIntensityComponent.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }

  @Test
  public void testRGBSplit() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = model.rgbSplit("original", "redImage"
            , "greenImage", "blueImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent
                , redImage.getPixels()[i][j].colorComponent.blueComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent
                , redImage.getPixels()[i][j].colorComponent.greenComponent);
      }
    }
  }

  @Test
  public void testRGBCombineOnNullRedImage() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = null;
    Image greenImage = model.greyscale("green-component"
            , "original", "greenImage");
    Image blueImage = model.greyscale("blue-component"
            , "original", "blueImage");
    Image ResultImageRed = model.rgbCombine("ResultImageRed", "redImage"
            , "greenImage", "blueImage");
    assertNull(ResultImageRed);
  }

  @Test
  public void testRGBCombineOnNullGreenImage() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = model.greyscale("red-component"
            , "original", "redImage");
    Image greenImage = null;
    Image blueImage = model.greyscale("blue-component"
            , "original", "blueImage");
    Image ResultImageGreen = model.rgbCombine("ResultImageGreen", "redImage"
            , "greenImage", "blueImage");
    assertNull(ResultImageGreen);
  }

  @Test
  public void testRGBCombineOnNullBlueImage() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = model.greyscale("red-component"
            , "original", "redImage");
    Image greenImage = model.greyscale("green-component"
            , "original", "greenImage");
    Image blueImage = null;
    Image ResultImageBlue = model.rgbCombine("ResultImageBlue", "redImage"
            , "greenImage", "blueImage");
    assertEquals(ResultImageBlue, null);
  }

  @Test
  public void testRGBCombine() {
    Image original = model.loadImage("res/dog.ppm", "original");
    Image redImage = model.greyscale("red-component"
            , "original", "redImage");
    Image greenImage = model.greyscale("green-component"
            , "original", "greenImage");
    Image blueImage = model.greyscale("blue-component"
            , "original", "blueImage");
    Image RGBCombinedImage = model.rgbCombine("RGBCombinedImage", "redImage"
            , "greenImage", "blueImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        assertEquals(original.getPixels()[i][j].colorComponent.redComponent
                , RGBCombinedImage.getPixels()[i][j].colorComponent.redComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.greenComponent
                , RGBCombinedImage.getPixels()[i][j].colorComponent.greenComponent);
        assertEquals(original.getPixels()[i][j].colorComponent.blueComponent
                , RGBCombinedImage.getPixels()[i][j].colorComponent.blueComponent);
      }
    }
  }


}