package controller;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import model.Image;
import model.ImageProcessingModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommandControllerTest {

  public class MockModel implements ImageProcessingModel {
    private StringBuilder sb;
    private Image image = new Image(0, 0, 0, new Pixel[0][0]);
    ;

    public MockModel(StringBuilder sb) {
      this.sb = sb;
    }

    @Override
    public Image loadImage(String imagePath, String imageName) {
      sb.append("Received inputs " + imagePath + " and " + imageName);
      return image;
    }

    @Override
    public Image saveImage(String imagePath, String imageName) throws IOException {
      sb.append("Received inputs " + imagePath + " and " + imageName);
      return image;
    }

    @Override
    public Image greyscale(String component, String sourceImageName, String destImageName) {
      sb.append("Received inputs " + component + ", " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public Image horizontalFlip(String sourceImageName, String destImageName) {
      sb.append("Received inputs " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public Image verticalFlip(String sourceImageName, String destImageName) {
      sb.append("Received inputs " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public Image brighten(int increment, String sourceImageName, String destImageName) {
      sb.append("Received inputs " + increment + ", " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public Image rgbSplit(String sourceImageName, String redImageName, String greenImageName, String blueImageName) {
      sb.append("Received inputs " + sourceImageName + ", " + redImageName + ", " + greenImageName + " and " + blueImageName);
      return image;
    }

    @Override
    public Image rgbCombine(String destImageName, String redImageName, String greenImageName, String blueImageName) {
      sb.append("Received inputs " + destImageName + ", " + redImageName + ", " + greenImageName + " and " + blueImageName);
      return image;
    }
  }

  @Test
  public void testLoadCommand() throws IOException {
    InputStream in = null;
    String a = "res/image.ppm";
    String b = "image";
    String input = "load " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mocklog.toString());
  }

  @Test
  public void testSaveCommand() throws IOException {
    InputStream in = null;
    String a = "res/image.ppm";
    String b = "image";
    String input = "save " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mocklog.toString());
  }

  @Test
  public void testHorizontalFlipCommand() throws IOException {
    InputStream in = null;
    String a = "image";
    String b = "image-horizontal";
    String input = "horizontal-flip " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mocklog.toString());
  }

  @Test
  public void testVerticalFlipCommand() throws IOException {
    InputStream in = null;
    String a = "image";
    String b = "image-vertical";
    String input = "vertical-flip " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mocklog.toString());
  }

  @Test
  public void testBrightenCommand() throws IOException {
    InputStream in = null;
    int a = 10;
    String b = "image";
    String c = "image-brighten";
    String input = "brighten " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mocklog.toString());
  }

  @Test
  public void testRGBSplitCommand() throws IOException {
    InputStream in = null;
    String a = "image";
    String b = "red-image";
    String c = "green-image";
    String d = "blue-image";
    String input = "rgb-split " + a + " " + b + " " + c + " " + d;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + ", " + c + " and " + d, mocklog.toString());
  }

  @Test
  public void testRGBCombineCommand() throws IOException {
    InputStream in = null;
    String a = "image";
    String b = "red-image";
    String c = "green-image";
    String d = "blue-image";
    String input = "rgb-combine " + a + " " + b + " " + c + " " + d;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + ", " + c + " and " + d, mocklog.toString());
  }

  @Test
  public void testRedGreyscaleCommand() throws IOException {
    InputStream in = null;
    String a = "red-component";
    String b = "image";
    String c = "red-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mocklog.toString());
  }

  @Test
  public void testGreenGreyscaleCommand() throws IOException {
    InputStream in = null;
    String a = "green-component";
    String b = "image";
    String c = "green-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mocklog.toString());
  }

  @Test
  public void testBlueGreyscaleCommand() throws IOException {
    InputStream in = null;
    String a = "blue-component";
    String b = "image";
    String c = "blue-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mocklog.toString());
  }

  @Test
  public void testValueGreyscaleCommand() throws IOException {
    InputStream in = null;
    String a = "value-component";
    String b = "image";
    String c = "value-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mocklog.toString());
  }

  @Test
  public void testIntensityGreyscaleCommand() throws IOException {
    InputStream in = null;
    String a = "intensity-component";
    String b = "image";
    String c = "intensity-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mocklog.toString());
  }

  @Test
  public void testLumaGreyscaleCommand() throws IOException {
    InputStream in = null;
    String a = "luma-component";
    String b = "image";
    String c = "luma-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mocklog.toString());
  }

  @Test
  public void testRunScriptCommand() throws IOException {
    InputStream in = null;
    String a = "test/controller/script.txt";
    String input = "run " + a;
    //in = new ByteArrayInputStream(input.getBytes());
    try{
      in = new FileInputStream("test/controller/script.txt");
    }
    catch(FileNotFoundException e){
      fail("Input file not present");
    }
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Received inputs res/koala.ppm and koala" +
            "Received inputs koala and koala-vertical" +
            "Received inputs res/koala-vertical.ppm and koala-vertical", mocklog.toString());
  }

  @Test
  public void testWrongCommandInput() throws IOException {
    InputStream in = null;
    String input = "dummy a b";
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("Unknown command " + input, out.toString().stripTrailing());
  }

  @Test
  public void testWrongGreyscaleComponentCommandInput() throws IOException {
    InputStream in = null;
    String input = "greyscale dummy-component a b";
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mocklog = new StringBuilder();
    ImageProcessingModel model = new MockModel(mocklog);
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, out);
    controller.execute();
    assertEquals("", out.toString().stripTrailing());
  }
}