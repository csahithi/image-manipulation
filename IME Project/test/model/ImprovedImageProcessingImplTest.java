package model;

import org.junit.Before;
import org.junit.Test;

import controller.commands.ImageCommandController;
import controller.commands.Load;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This class contains tests for ImprovedImageProcessing model class.
 */
public class ImprovedImageProcessingImplTest {
  private ImprovedImageProcessing model;

  @Before
  public void setup() {
    model = new ImprovedImageProcessingImpl();
  }

  @Test
  public void testManualBlurImage() {
    ImageCommandController controller = new Load("res/testFile.ppm", "test");
    Image original = controller.execute(model);
    Image blurredImage = model.filtering("blur", "test",
            "blurredImage");
    assertEquals(21, blurredImage.getPixels()[0][0].getColorComponent().getRedComponent());
    assertEquals(32, blurredImage.getPixels()[0][1].getColorComponent().
            getGreenComponent());
    assertEquals(72, blurredImage.getPixels()[1][0].getColorComponent().getBlueComponent());
    assertEquals(66, blurredImage.getPixels()[1][1].getColorComponent().getRedComponent());
    assertEquals(72, blurredImage.getPixels()[2][0].getColorComponent().
            getGreenComponent());
    assertEquals(83, blurredImage.getPixels()[2][1].getColorComponent().getBlueComponent());
  }

  @Test
  public void testManualSharpenImage() {
    ImageCommandController controller = new Load("res/testFile.ppm", "test");
    Image original = controller.execute(model);
    Image sharpenImage = model.filtering("sharpen", "test",
            "sharpenImage");
    assertEquals(25, sharpenImage.getPixels()[0][0].getColorComponent().getRedComponent());
    assertEquals(62, sharpenImage.getPixels()[0][1].getColorComponent().
            getGreenComponent());
    assertEquals(224, sharpenImage.getPixels()[1][0].getColorComponent().
            getBlueComponent());
    assertEquals(201, sharpenImage.getPixels()[1][1].getColorComponent().getRedComponent());
    assertEquals(219, sharpenImage.getPixels()[2][0].getColorComponent().
            getGreenComponent());
    assertEquals(255, sharpenImage.getPixels()[2][1].getColorComponent().
            getBlueComponent());
  }

  @Test
  public void testManualSepiaImage() {
    ImageCommandController controller = new Load("res/testFile.ppm", "test");
    Image original = controller.execute(model);
    Image sepiaImage = model.colorTransformation("sepia", "test",
            "sepiaImage");
    assertEquals(24, sepiaImage.getPixels()[0][0].getColorComponent().getRedComponent());
    assertEquals(58, sepiaImage.getPixels()[0][1].getColorComponent().getGreenComponent());
    assertEquals(73, sepiaImage.getPixels()[1][0].getColorComponent().getBlueComponent());
    assertEquals(146, sepiaImage.getPixels()[1][1].getColorComponent().getRedComponent());
    assertEquals(166, sepiaImage.getPixels()[2][0].getColorComponent().getGreenComponent());
    assertEquals(157, sepiaImage.getPixels()[2][1].getColorComponent().getBlueComponent());
  }

  @Test
  public void testManualGreyscaleImage() {
    ImageCommandController controller = new Load("res/testFile.ppm", "test");
    Image original = controller.execute(model);
    Image greyscaleImage = model.colorTransformation("greyscale", "test",
            "greyscaleImage");
    assertEquals(18, greyscaleImage.getPixels()[0][0].getColorComponent().
            getRedComponent());
    assertEquals(48, greyscaleImage.getPixels()[0][1].getColorComponent().
            getGreenComponent());
    assertEquals(78, greyscaleImage.getPixels()[1][0].getColorComponent().
            getBlueComponent());
    assertEquals(108, greyscaleImage.getPixels()[1][1].getColorComponent().
            getRedComponent());
    assertEquals(138, greyscaleImage.getPixels()[2][0].getColorComponent().
            getGreenComponent());
    assertEquals(168, greyscaleImage.getPixels()[2][1].getColorComponent().
            getBlueComponent());
  }

  @Test
  public void testManualDitherImage() {
    ImageCommandController controller = new Load("res/testFile.ppm", "test");
    Image original = controller.execute(model);
    Image ditherImage = model.dither("test", "ditherImage");
    assertEquals(0, ditherImage.getPixels()[0][0].getColorComponent().getRedComponent());
    assertEquals(0, ditherImage.getPixels()[0][1].getColorComponent().getGreenComponent());
    assertEquals(0, ditherImage.getPixels()[1][0].getColorComponent().getBlueComponent());
    assertEquals(0, ditherImage.getPixels()[1][1].getColorComponent().getRedComponent());
    assertEquals(255, ditherImage.getPixels()[2][0].getColorComponent().
            getGreenComponent());
    assertEquals(255, ditherImage.getPixels()[2][1].getColorComponent().getBlueComponent());
  }

  @Test
  public void testSepiaImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "dog");
    Image original = controller.execute(model);
    Image sepiaImage = model.colorTransformation("sepia", "dog",
            "blurredImage");


  }

  @Test
  public void testBlurNullImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "dog");
    Image original = controller.execute(model);
    Image blurredImage = model.filtering("blur", "NullImage",
            "blurredImage");
    assertNull(blurredImage);
  }

  @Test
  public void testSharpenNullImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "dog");
    Image original = controller.execute(model);
    Image SharpenedImage = model.filtering("sharpen", "NullImage",
            "SharpenedImage");
    assertNull(SharpenedImage);
  }

  @Test
  public void testSepiaNullImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "dog");
    Image original = controller.execute(model);
    controller = new Load("res/dog11.ppm", "dog11");
    Image NullImage = controller.execute(model);
    Image sepiaImage1 = model.filtering("sepia", "NullImage",
            "sepiaImage");
    Image sepiaImage2 = model.filtering("sepia", "notExisting",
            "sepiaImage");
    assertEquals("File res/dog11.ppm not found!", "File res/dog11.ppm not found!");
    assertNull(sepiaImage1);
    assertNull(sepiaImage2);
  }

  @Test
  public void testGreyscaleNullImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "dog");
    Image original = controller.execute(model);
    controller = new Load("res/dog11.ppm", "dog11");
    Image NullImage = controller.execute(model);
    Image greyscaleImage1 = model.filtering("greyscale", "NullImage",
            "greyscaleImage1");
    Image greyscaleImage2 = model.filtering("sepia", "notExisting",
            "greyscaleImage2");
    assertEquals("File res/dog11.ppm not found!", "File res/dog11.ppm not found!");
    assertNull(greyscaleImage1);
    assertNull(greyscaleImage2);
  }

  @Test
  public void testDitherNullImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "dog");
    Image original = controller.execute(model);
    Image DitheredImage = model.dither("NullImage",
            "DitheredImage");
    assertNull(DitheredImage);
  }
}


