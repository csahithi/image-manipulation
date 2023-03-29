package model;

import org.junit.Before;
import org.junit.Test;


public class ImprovedImageProcessingImplTest {
  private ImprovedImageProcessing model;

  @Before
  public void setup() {
    model = new ImprovedImageProcessingImpl();
  }

  @Test
  public void testBlurImage() {
    Image original = model.loadImage("res/dog.ppm", "dog");
    Image blurredImage = model.filtering("blur","dog",
            "blurredImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
      }
    }
  }

}

