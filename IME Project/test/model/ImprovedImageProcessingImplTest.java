package model;

import org.junit.Before;
import org.junit.Test;

import controller.commands.ImageCommandController;
import controller.commands.Load;


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
    Image blurredImage = model.filtering("blur","dog",
            "blurredImage");
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
      }
    }
  }

}

