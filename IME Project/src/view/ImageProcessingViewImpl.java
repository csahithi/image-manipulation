package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.swing.*;

import model.Color;
import model.Image;
import model.Pixel;

/**
 * The ImageProcessingViewImpl class implements the ImageProcessingView interface and provides a
 * GUI for image processing.
 * The GUI includes buttons for loading, saving, flipping, and modifying the colors of images,
 * as well as panels for displaying images and histograms.
 * This class extends the JFrame class and uses various Swing components to create the GUI.
 */
public class ImageProcessingViewImpl extends JFrame implements ImageProcessingView {
  private JButton loadButton;
  private JButton saveButton;
  private JButton horizontalFlipButton;
  private JButton verticalFlipButton;
  private JButton rgbSplitButton;
  private JButton rgbCombineButton;
  private JButton sepiaButton;
  private JButton greyscaleButton;
  private JButton ditherButton;
  private JPanel radioPanel;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton brightenButton;
  private JPanel imageProcessingButtonPanel;
  private JScrollPane imagePane;
  private JScrollPane histogramPane;
  private JPanel imagePanel;
  private JLabel imageLabel;
  private JLabel histogramLabel;
  private JPanel loadSaveButtonPanel;
  private JRadioButton redRadioButton;
  private JRadioButton greenRadioButton;
  private JRadioButton blueRadioButton;
  private JRadioButton lumaRadioButton;
  private JRadioButton intensityRadioButton;
  private JRadioButton valueRadioButton;
  private JButton loadRedButton;
  private JButton loadGreenButton;
  private JButton loadBlueButton;
  private JButton saveRedButton;
  private JButton saveGreenButton;
  private JButton saveBlueButton;

  /**
   * Creates an instance of the ImageProcessingViewImpl class, which is a JFrame that displays the
   * graphical user interface for the Image Processing application.
   * Sets the title of the JFrame to "Image Processing" and the size to 1000 x 700 pixels.
   * Configures the default close operation to exit the application when the JFrame is closed.
   * Initializes and adds components to the JFrame, including buttons for loading and saving images,
   * a panel for displaying the loaded image and its histogram, and buttons for applying various
   * image processing operations.
   * Also initializes and adds components for selecting RGB color channels,
   * loading and saving split/composite images, and viewing text commands.
   */
  public ImageProcessingViewImpl() {
    super();
    this.setTitle("Image Processing");
    this.setSize(1000, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    loadSaveButtonPanel = new JPanel();
    loadSaveButtonPanel.setLayout(new FlowLayout());
    this.add(loadSaveButtonPanel, BorderLayout.NORTH);

    loadButton = new JButton("Load Image");
    loadButton.setActionCommand("load");
    loadSaveButtonPanel.add(loadButton);

    saveButton = new JButton("Save Image");
    saveButton.setActionCommand("save");
    loadSaveButtonPanel.add(saveButton);

    imagePanel = new JPanel();
    imagePanel.setLayout(new FlowLayout());
    this.add(imagePanel, BorderLayout.CENTER);

    //todo: scrolling
    //todo: rgbcombine flaw
    //todo: text commands view

    imageLabel = new JLabel(new ImageIcon());
    imagePane = new JScrollPane(imageLabel);
    imagePane.setSize(500, 500);
    imagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    imagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imagePanel.add(imagePane);

    histogramLabel = new JLabel(new ImageIcon());
    histogramPane = new JScrollPane(histogramLabel);
    histogramPane.setSize(500, 500);
    histogramPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    histogramPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imagePanel.add(histogramPane);

    imageProcessingButtonPanel = new JPanel();
    imageProcessingButtonPanel.setLayout(new FlowLayout());
    this.add(imageProcessingButtonPanel, BorderLayout.SOUTH);

    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("brighten");
    imageProcessingButtonPanel.add(brightenButton);

    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("greyscale");
    imageProcessingButtonPanel.add(greyscaleButton);

    rgbSplitButton = new JButton("RGBSplit");
    rgbSplitButton.setActionCommand("rgb-split");
    imageProcessingButtonPanel.add(rgbSplitButton);

    rgbCombineButton = new JButton("RGBCombine");
    rgbCombineButton.setActionCommand("rgb-combine");
    imageProcessingButtonPanel.add(rgbCombineButton);

    blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur");
    imageProcessingButtonPanel.add(blurButton);

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    imageProcessingButtonPanel.add(sharpenButton);

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("sepia");
    imageProcessingButtonPanel.add(sepiaButton);

    ditherButton = new JButton("Dither");
    ditherButton.setActionCommand("dither");
    imageProcessingButtonPanel.add(ditherButton);

    horizontalFlipButton = new JButton("Horizontal Flip");
    horizontalFlipButton.setActionCommand("horizontal-flip");
    imageProcessingButtonPanel.add(horizontalFlipButton);

    verticalFlipButton = new JButton("Vertical Flip");
    verticalFlipButton.setActionCommand("vertical-flip");
    imageProcessingButtonPanel.add(verticalFlipButton);

    redRadioButton = new JRadioButton("Red-component");
    redRadioButton.setActionCommand("red-component");
    greenRadioButton = new JRadioButton("Green-component");
    greenRadioButton.setActionCommand("green-component");
    blueRadioButton = new JRadioButton("Blue-component");
    blueRadioButton.setActionCommand("blue-component");
    lumaRadioButton = new JRadioButton("Luma-component");
    lumaRadioButton.setActionCommand("luma-component");
    intensityRadioButton = new JRadioButton("Intensity-component");
    intensityRadioButton.setActionCommand("intensity-component");
    valueRadioButton = new JRadioButton("Value-component");
    valueRadioButton.setActionCommand("value-component");

    loadRedButton = new JButton("Load Red Image");
    loadRedButton.setActionCommand("load redCombineImage");
    loadGreenButton = new JButton("Load Green Image");
    loadGreenButton.setActionCommand("load greenCombineImage");
    loadBlueButton = new JButton("Load Blue Image");
    loadBlueButton.setActionCommand("load blueCombineImage");

    saveRedButton = new JButton("Save Red Image");
    saveRedButton.setActionCommand("save redSplitImage");
    saveGreenButton = new JButton("Save Green Image");
    saveGreenButton.setActionCommand("save greenSplitImage");
    saveBlueButton = new JButton("Save Blue Image");
    saveBlueButton.setActionCommand("save blueSplitImage");
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandButtonListener(ActionListener actionEvent) {
    loadButton.addActionListener(actionEvent);
    saveButton.addActionListener(actionEvent);
    horizontalFlipButton.addActionListener(actionEvent);
    verticalFlipButton.addActionListener(actionEvent);
    brightenButton.addActionListener(actionEvent);
    greyscaleButton.addActionListener(actionEvent);
    rgbSplitButton.addActionListener(actionEvent);
    rgbCombineButton.addActionListener(actionEvent);
    sepiaButton.addActionListener(actionEvent);
    blurButton.addActionListener(actionEvent);
    sharpenButton.addActionListener(actionEvent);
    ditherButton.addActionListener(actionEvent);
    loadRedButton.addActionListener(actionEvent);
    loadGreenButton.addActionListener(actionEvent);
    loadBlueButton.addActionListener(actionEvent);
    saveRedButton.addActionListener(actionEvent);
    saveGreenButton.addActionListener(actionEvent);
    saveBlueButton.addActionListener(actionEvent);
  }

  @Override
  public List<String> getParameters(String command) {
    String[] commands = command.split(" ");
    String imageName = "image";
    if (commands.length > 1) {
      imageName = commands[1];
    }
    command = commands[0];
    switch (command) {
      case "load":
        String loadFilePath = showLoadImageDialog();
        if (loadFilePath != null) {
          return List.of(new String[]{"load", loadFilePath, imageName});
        } else {
          return null;
        }
      case "save":
        String saveFilePath = showSaveImageDialog();
        if (saveFilePath != null) {
          return List.of(new String[]{"save", saveFilePath, imageName});
        } else {
          return null;
        }
      case "brighten":
        String brightenValue = JOptionPane.showInputDialog("Please enter numeric value by which " +
                "the image should be brightened or darkened");
        if (brightenValue == null) {
          return null;
        }
        return List.of(new String[]{"brighten", brightenValue, "image", "image"});
      case "horizontal-flip":
        return List.of(new String[]{"horizontal-flip", "image", "image"});
      case "vertical-flip":
        return List.of(new String[]{"vertical-flip", "image", "image"});
      case "greyscale":
        String greyscaleComponent = showOptionsForGreyScale();
        return List.of(new String[]{"greyscale", greyscaleComponent, "image", "image"});
      case "sepia":
        return List.of(new String[]{"sepia", "image", "image"});
      case "dither":
        return List.of(new String[]{"dither", "image", "image"});
      case "blur":
        return List.of(new String[]{"blur", "image", "image"});
      case "sharpen":
        return List.of(new String[]{"sharpen", "image", "image"});
      case "rgb-split":
        return List.of(new String[]{
                "rgb-split", "image", "redSplitImage", "greenSplitImage", "blueSplitImage"});
      case "rgb-combine":
        showRGBCombineLoadDialog();
        return List.of(new String[]{"rgb-combine", "image", "redCombineImage", "greenCombineImage",
                "blueCombineImage"});
      default:
        return null;
    }
  }

  @Override
  public void displayCurrentImage(List<Image> m) {
    if (m.size() == 1) {
      BufferedImage image = getImageToDisplay(m.get(0));
      BufferedImage histogramImage = displayHistogram(image);
      ImageIcon imageIcon = new ImageIcon(image);
      JLabel imageLabel = new JLabel(imageIcon);
      JScrollPane scrollPane = new JScrollPane(imageLabel);
      scrollPane.setPreferredSize(new Dimension(450, 450));
      ImageIcon histogramIcon = new ImageIcon(histogramImage);
      JLabel histogramLabel = new JLabel(histogramIcon);
      histogramLabel.setPreferredSize(new Dimension(300, 450));
      JPanel panel = new JPanel(new GridLayout(1, 2));
      panel.add(scrollPane);
      panel.add(histogramLabel);
      imagePanel.removeAll();
      imagePanel.add(panel, BorderLayout.CENTER);
      validate();
    } else {
      showRGBSplitSaveDialog(m);
    }

  }

  @Override
  public void displayErrorDialog() {
    JOptionPane.showMessageDialog(this, "Operation failed", "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  private BufferedImage getImageToDisplay(Image m) {
    BufferedImage image = new BufferedImage(m.getWidth(), m.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    Pixel[][] listOfPixels = m.getPixels();
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        model.Color rgb = new Color(listOfPixels[y][x].getColorComponent().getRedComponent(),
                listOfPixels[y][x].getColorComponent().getGreenComponent(),
                listOfPixels[y][x].getColorComponent().getBlueComponent());
        try {
          image.setRGB(x, y, rgb.getRGB());
        } catch (NullPointerException e) {
          System.out.println("y" + y + "x" + x);
          return null;
        }
      }
    }
    return image;
  }

  private void showRGBSplitSaveDialog(List<Image> m) {
//    JButton saveRedButton = new JButton("Save Red Image");
//    saveRedButton.setActionCommand("save");
//    JButton saveRedButton = new JButton("Save Red Image");
//    JButton saveRedButton = new JButton("Save Red Image");
//
//    JOptionPane.showOptionDialog(this, "Please save RGB Split Images below",
//            "Save RGB Split Images", JOptionPane.YES_NO_CANCEL_OPTION, null, )
    JOptionPane optionPane = new JOptionPane();
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(saveRedButton);
    buttonPanel.add(saveGreenButton);
    buttonPanel.add(saveBlueButton);
//    JOptionPane.showOptionDialog(this, "Please load images to combine",
//            "Load RGB Combine Images", JOptionPane.DEFAULT_OPTION,
//            JOptionPane.PLAIN_MESSAGE, null,
//            new Object[]{loadRedButton, loadGreenButton, loadBlueButton}, null);
    JDialog dialog = null;
    optionPane.setMessage("Please save RGB Split images");
    optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
    optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
    optionPane.add(buttonPanel, 1);
    dialog = optionPane.createDialog(this, "Save RGB Split Images");
    dialog.setVisible(true);
  }

  private void showRGBCombineLoadDialog() {
    JOptionPane optionPane = new JOptionPane();
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(loadRedButton);
    buttonPanel.add(loadGreenButton);
    buttonPanel.add(loadBlueButton);
//    JOptionPane.showOptionDialog(this, "Please load images to combine",
//            "Load RGB Combine Images", JOptionPane.DEFAULT_OPTION,
//            JOptionPane.PLAIN_MESSAGE, null,
//            new Object[]{loadRedButton, loadGreenButton, loadBlueButton}, null);
    JDialog dialog = null;
    optionPane.setMessage("Please load images to combine");
    optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
    optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
    optionPane.add(buttonPanel, 1);
    dialog = optionPane.createDialog(this, "Load RGB Combine Images");
    dialog.setVisible(true);
  }

  private String showLoadImageDialog() {
    JFileChooser fc = new JFileChooser(".");
    int returnVal = fc.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      //This is where a real application would open the file.
      System.out.println("Opening: " + file.getName() + ".");
      return file.getPath();
    } else {
      System.out.println("Open command cancelled by user.");
      return null;
    }
  }

  private String showSaveImageDialog() {
    JFileChooser fc = new JFileChooser(".");
    int returnVal = fc.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      //This is where a real application would save the file.
      System.out.println("Saving: " + file.getName() + ".");
      return file.getPath();
    } else {
      System.out.println("Save command cancelled by user.");
      return null;
    }
  }

  private BufferedImage displayHistogram(BufferedImage image) {
    int[] redHistogram = new int[256];
    int[] greenHistogram = new int[256];
    int[] blueHistogram = new int[256];
    int[] intensityHistogram = new int[256];
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Color c = new Color(image.getRGB(x, y));
        redHistogram[c.getRed()]++;
        greenHistogram[c.getGreen()]++;
        blueHistogram[c.getBlue()]++;
        int intensity = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
        intensityHistogram[intensity]++;
      }
    }
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (int i = 0; i < 256; i++) {
      dataset.addValue(redHistogram[i], "Red", Integer.toString(i));
      dataset.addValue(greenHistogram[i], "Green", Integer.toString(i));
      dataset.addValue(blueHistogram[i], "Blue", Integer.toString(i));
      dataset.addValue(intensityHistogram[i], "Intensity", Integer.toString(i));
    }
    JFreeChart chart = ChartFactory.createLineChart("Histogram", "Value",
            "Frequency", dataset, PlotOrientation.VERTICAL, true,
            true, false);
    return chart.createBufferedImage(450, 450);
  }

  private String showOptionsForGreyScale() {
    radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    JLabel label = new JLabel("Select an option:");
    radioPanel.add(label);
    radioPanel.add(redRadioButton);
    radioPanel.add(greenRadioButton);
    radioPanel.add(blueRadioButton);
    radioPanel.add(lumaRadioButton);
    radioPanel.add(intensityRadioButton);
    radioPanel.add(valueRadioButton);
    ButtonGroup greyscaleOptions = new ButtonGroup();
    greyscaleOptions.add(redRadioButton);
    greyscaleOptions.add(greenRadioButton);
    greyscaleOptions.add(blueRadioButton);
    greyscaleOptions.add(lumaRadioButton);
    greyscaleOptions.add(intensityRadioButton);
    greyscaleOptions.add(valueRadioButton);
    JOptionPane optionPaneRB = new JOptionPane();
    JDialog dialog = null;
    optionPaneRB.setMessage("Please select an option");
    optionPaneRB.setMessageType(JOptionPane.PLAIN_MESSAGE);
    optionPaneRB.setOptionType(JOptionPane.DEFAULT_OPTION);
    optionPaneRB.add(radioPanel, 1);
    dialog = optionPaneRB.createDialog(this, "Greyscale on component");
    dialog.setVisible(true);
    if (optionPaneRB.getValue() == null) {
      return null;
    }
    return greyscaleOptions.getSelection().getActionCommand();
  }
}
