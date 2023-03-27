package model;

public interface ImprovedImageProcessing extends ImageProcessingModel {
  Image filtering(String operation, String sourceImageName, String destImageName);

  Image colorTransformation(String transformation, String sourceImageName, String destImageName);

  Image dither(String sourceImageName, String destImageName);
}
