Image Manipulation and Enhancement Project


Design of our project:-
--------------------------
We have designed our project using MVC pattern where-in the files are classified as follows:

Controller :
------------
We have 'ImageProcessingControllerImpl.class' which implements 'ImageProcessingController.class'
and takes user inputs and delegates the commands to the model to perform.
ImageProcessingController interface contains execute method, which starts the controller.

Model :
------------
We have 'ImageProcessingModelImpl.class' (it implements 'ImageProcessingModel') which takes input
data from controller and deals with the commands such as load/save/brighten/grey-scale/
horizontal-flip,vertical-flip,RGB-combine,RGB-split associated with images.


Driver Class:
------------

We have 'ImageManipulator.claas' is driver class.It is entry point of the program where we can
create Image Processing Model and run some operations on it.  It contains main method in it.


Command Design Pattern:
-------------------------

The command design pattern provides a unifying effect, making unconnected lines of code appear to be
 working towards the same goal. This improves cohesion since the controller is no longer performing
 one of ten separate tasks, but rather executing commands.

The command design pattern encourages delegation. We separated each command into its own class
rather than having them all in the controller. This allows us to accommodate new, more complex
instructions that may be implemented in the future without clogging the controller.

ImageCommandController Interface contains an execute method.All command classes implement this
interface and have their own implementation in the execute function.As a result, all command classes
are united.


Features the project supports:
---------------------------------------------------------------------------------------------------

1.Load image: Load an image from the specified path and refer it to henceforth in the program by
the given image name.

2.Save image: Save the image with the given name to the specified path which should include the
name of the file.

3.Greyscale image on Component: Create a greyscale image with a component of the image with
the given name, and refer to it henceforth in the program by the given destination name. Similar
commands for green, blue, value, luma, intensity components should be supported.Red, green, blue,
value, luma and intensity are the components that are supported.

4.Horizontal-flip image: Flip an image horizontally to create a new image,
referred to henceforth by the given destination name.

5.Vertical-flip image: Flip an image vertically to create a new image,
referred to henceforth by the given destination name.

6.Brighten image by increment value : brighten the image by the given increment to create a new
image, referred to henceforth by the given destination name. The increment may be positive
(brightening) or negative (darkening).

7.Rgb-split image: split the given image into three greyscale images containing its red, green and
blue components respectively.

8.Rgb-combine image: Combine the three greyscale images into a single image that gets its red,
green and blue components from the three images respectively.

9.Run script-file: Load and run the script commands in the specified file.u

Classes and Interfaces in this Project:
----------------------------------------

ImageManipulator: The entry point of the program where we can create Image Processing Model and run
some operations on it.
________________________________________

Controller-Related Classes and Interfaces:
___________________________________________

ImageProcessingController : This interface represents the main controller of the program.

ImageProcessingControllerImpl : This class implements ImageProcessingController class.The user
inputs/commands are processed at the controller level in this class.

ImageCommandController : Command interface for the command classes.

Command Classes:-

BrightenImage : This command class brightens the image by the given increment.

Greyscale : This command class creates a greyscale image with the component of the image with the
given name, and refer to it henceforth in the program by the given destination name.

HorizontalFlip : This command class flips image horizontally to create a new image, referred to
henceforth by the given destination name.

Load : This command class load an image from the specified path and refers it  in the program by
the given image name.

RGBCombine : This command class combines the three greyscale images into a single image that gets
its red, green and blue components from the three images respectively.

RGBSplit : This command class splits the given image into three greyscale images containing its red,
 green and blue components respectively.

Save : This command class saves the image with the given name to the specified path which should
include the name of the file.

VerticalFlip : This command class flips an image vertically to create a new image, referred to
henceforth by the given destination name.

Model-Related Classes and Interfaces:
______________________________________


Color : This class represents RGB color factor for each pixel.

CreatePPMUtil : This class contains utility methods to create a PPM image file from image object
and saves it in the specified file path .

Image : This class represents Image.

ImageProcessingModel : This interface specifies the operations on image.An image is characterized
by its width, height,its pixels representing its respective RGB-color.It can be asked to produce an
 image using on of the commands below.Another command can be used to save this image locally in ppm
 file format.

ImageProcessingModelImpl : This class implements all the operations/commands that can applied on
image.

Pixel : This class represents a pixel.

ReadPPMUtil : This class contains utility methods to read a PPM image from file and simply print
its contents.


Test-Related Classes and Interfaces:
______________________________________

CommandControllerTest : This class contains tests for ImageProcessing Controller class.

ImageProcessingImplTest : This class contains tests for ImageProcessing model class.

Citation for the source of our Image
--------------------------------------
We are the sole owners of the images provided in our project. We authorize the usage of the given
images in the project.


Instructions to run the script file:
--------------------------------------
Step 1: Run the ImageManipulator class
Step 2: Type 'run res/script.txt' in the console and press enter. Feel free to run any other script
file by mentioning the file path after run command.
Step 3: (Optional)Feel free to run any other commands by giving them as input in console.



