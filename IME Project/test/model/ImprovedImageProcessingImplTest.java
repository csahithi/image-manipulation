package model;

import org.junit.Before;
import org.junit.Test;

import controller.commands.ImageCommandController;
import controller.commands.Load;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ImprovedImageProcessingImplTest {
  private ImprovedImageProcessing model;

  @Before
  public void setup() {
    model = new ImprovedImageProcessingImpl();
  }

  @Test
  public void testBlurImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "dog");
    Image original = controller.execute(model);
    Image blurredImage = model.filtering("blur", "dog",
            "blurredImage");
    Pixel[][] pixels = original.getPixels();
    Pixel[][] paddedArray = new Pixel[original.getHeight() + 2][original.getWidth() + 2];
    for (int i = 0; i < original.getHeight() + 2; i++) {
      for (int j = 0; j < original.getWidth() + 2; j++) {
        if (i >= 1 && i < original.getHeight() + 1 && j >= 1 && j < original.getWidth() + 1) {
          paddedArray[i][j] = new Pixel(i, j,
                  pixels[i - 1][j - 1].getColorComponent().getRedComponent(),
                  pixels[i - 1][j - 1].getColorComponent().getGreenComponent(),
                  pixels[i - 1][j - 1].getColorComponent().getBlueComponent());
        } else {
          paddedArray[i][j] = new Pixel(i, j, 0, 0, 0);
        }
      }
    }
    int rSum, gSum, bSum;
    int r = original.getHeight();
    int c = original.getWidth();
    Pixel[][] listOfPixelsDestImage = new Pixel[r][c];
    for (int i = 1; i < r + 1; i++) {
      for (int j = 1; j < c + 1; j++) {
        rSum = (int) ((0.25) * paddedArray[i][j].getColorComponent().getRedComponent());
        gSum = (int) ((0.25) * paddedArray[i][j].getColorComponent().getGreenComponent());
        bSum = (int) ((0.25) * paddedArray[i][j].getColorComponent().getBlueComponent());
        for (int x = i - 1; x < i + 2; x++) {
          for (int y = j - 1; y < j + 2; y++) {
            if ((x == i - 1 && (y == j - 1 || y == j + 1)) || (x == i + 1 && (y == j - 1 ||
                    y == j + 1))) {
              rSum += (0.0625) * (paddedArray[x][y].getColorComponent().getRedComponent());
              gSum += (0.0625) * (paddedArray[x][y].getColorComponent().getGreenComponent());
              bSum += (0.0625) * (paddedArray[x][y].getColorComponent().getBlueComponent());
            } else if ((x == i && (y == j - 1 || y == j + 1)) || (y == j && (x == i - 1 ||
                    x == i + 1))) {
              rSum += (0.125) * (paddedArray[x][y].getColorComponent().getRedComponent());
              gSum += (0.125) * (paddedArray[x][y].getColorComponent().getGreenComponent());
              bSum += (0.125) * (paddedArray[x][y].getColorComponent().getBlueComponent());
            }
          }
        }
        listOfPixelsDestImage[i - 1][j - 1] = new Pixel(i - 1, j - 1,
                Math.max(0, Math.min(rSum, 255)), Math.max(0, Math.min(gSum, 255)),
                Math.max(0, Math.min(bSum, 255)));
        assertEquals(Math.max(0, Math.min(rSum, 255)), blurredImage.getPixels()[i - 1][j - 1]
                .getColorComponent().getRedComponent());
        assertEquals(Math.max(0, Math.min(gSum, 255)), blurredImage.getPixels()[i - 1][j - 1]
                .getColorComponent().getGreenComponent());
        assertEquals(Math.max(0, Math.min(bSum, 255)), blurredImage.getPixels()[i - 1][j - 1]
                .getColorComponent().getBlueComponent());
      }
    }
  }

  @Test
  public void testManualBlurImage() {
    ImageCommandController controller = new Load("res/testFile.ppm", "test");
    Image original = controller.execute(model);
    Image blurredImage = model.filtering("blur", "test",
            "blurredImage");

//        assertEquals("java.lang.String<model.Color[r=21,g=27,b=32]>", blurredImage.getPixels()[0][0].getColorComponent());
//        assertEquals(0, blurredImage.getPixels()[0][0].getColorComponent());
////        assertEquals(Math.max(0, Math.min(bSum, 255)), blurredImage.getPixels()[i - 1][j - 1]
////                .getColorComponent().getBlueComponent());
      }




  @Test
  public void testSepiaImage(){
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
    assertEquals("File res/dog11.ppm not found!","File res/dog11.ppm not found!");
    assertNull(sepiaImage1);
    assertNull(sepiaImage2);
  }
  @Test
  public void testDitherNullImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "dog");
    Image original = controller.execute(model);
    Image DitheredImage = model.dither( "NullImage",
            "DitheredImage");
    assertNull(DitheredImage);
  }


}


