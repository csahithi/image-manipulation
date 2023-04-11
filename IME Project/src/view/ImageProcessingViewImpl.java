package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.Color;
import model.Image;
import model.Pixel;

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
  private JTextField input;
  private JLabel display;
  private JScrollPane imagePane;
  private JScrollPane histogramPane;
  private JPanel imagePanel;
  private JLabel imageLabel;
  private JPanel histogramPanel;
  private JLabel histogramLabel;
  private JPanel loadSaveButtonPanel;
  private List<String> imageParameters;
  private ImageIcon image;
  private JLabel inputDisplay;
  private JPanel inputDialogPanel;

  private JRadioButton redRadioButton;
  private JRadioButton greenRadioButton;
  private JRadioButton blueRadioButton;
  private JRadioButton lumaRadioButton;
  private JRadioButton intensityRadioButton;
  private JRadioButton valueRadioButton;


  public ImageProcessingViewImpl() {
    super();
    this.setTitle("Image Processing");
    this.setSize(1000, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());
//    turtlePanel = new TurtlePanel();
//    turtlePanel.setPreferredSize(new Dimension(500, 500));
//    this.add(turtlePanel, BorderLayout.CENTER);

    imageParameters = new ArrayList<>();

    loadSaveButtonPanel = new JPanel();
    loadSaveButtonPanel.setLayout(new FlowLayout());
    this.add(loadSaveButtonPanel, BorderLayout.NORTH);

    loadButton = new JButton("Load Image");
    loadButton.setActionCommand("load");
//    loadButton.addActionListener((ActionEvent e) -> {
//      String filePath = showLoadImageDialog();
//      List<String> params = new ArrayList<>();
//      params.add("load");
//      params.add(filePath);
//      params.add("image");
//      imageParameters = params;
//    });
    loadSaveButtonPanel.add(loadButton);

    saveButton = new JButton("Save Image");
    saveButton.setActionCommand("save");
    saveButton.addActionListener((ActionEvent e) -> {
      showSaveImageDialog();
    });
    loadSaveButtonPanel.add(saveButton);
//    saveButton.addActionListener((ActionEvent e) -> {
//      System.exit(0);
//    });
    //  buttonPanel.add(saveButton);

    imagePanel = new JPanel();
    imagePanel.setLayout(new FlowLayout());
    this.add(imagePanel, BorderLayout.CENTER);

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

    //input textfield
//    input = new JTextField(15);
//    buttonPanel.add(input);


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
    rgbSplitButton.setActionCommand("rgb-combine");
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
  }

  @Override
  public List<String> getParameters(String command) {
    switch (command) {
      case "load":
        String filePath = showLoadImageDialog();
        if (filePath != null) {
          return List.of(new String[]{"load", filePath, "image"});
        } else {
          return null;
        }
      case "save":
      case "brighten":
        inputDisplay.setText(JOptionPane.showInputDialog("Please enter your username"));
      case "horizontal-flip":
        return List.of(new String[]{"horizontal-flip", "image", "image"});
      case "vertical-flip":
        return List.of(new String[]{"vertical-flip", "image", "image"});
      case "greyscale":
        String value = showOptionsForGreyScale();
        return List.of(new String[]{"greyscale", value, "image", "image"});
      case "rgb-split":
        return List.of(new String[]{"rgb-split", "image", "image", "image", "image"});
      case "rgb-combine":
        return List.of(new String[]{"rgb-combine", "image", "image", "image", "image"});
      case "sepia":
        return List.of(new String[]{"sepia", "image", "image"});
      case "dither":
        return List.of(new String[]{"dither", "image", "image"});
      case "blur":
        return List.of(new String[]{"blur", "image", "image"});
      case "sharpen":
        return List.of(new String[]{"sharpen", "image", "image"});
    }
    return imageParameters;
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

  @Override
  public void displayCurrentImage(List<Image> m) {
    if (m.size() == 1) {
      BufferedImage sourceImage = getImageToDisplay(m.get(0));
      int[] redHistogram = new int[256];
      int[] greenHistogram = new int[256];
      int[] blueHistogram = new int[256];
      int[] intensityHistogram = new int[256];
      for (int y = 0; y < sourceImage.getHeight(); y++) {
        for (int x = 0; x < sourceImage.getWidth(); x++) {
          Color c = new Color(sourceImage.getRGB(x, y));
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
      image = new ImageIcon(sourceImage);
      imageLabel.setIcon(image);
      Dimension size = imageLabel.getPreferredSize();
      size.width = 450;
      size.height = 450;
      imageLabel.setPreferredSize(size);
      ImageIcon histogramIcon = new ImageIcon(chart.createBufferedImage(450, 450));
      histogramLabel.setIcon(histogramIcon);
      validate();
    } else {
      rgbSplitSaveDialog(m);
    }
  }

  @Override
  public void displayErrorDialog() {

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

  private void rgbSplitSaveDialog(List<Image> m) {
    JDialog rgbSplitSave = new JDialog();
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

  private void showSaveImageDialog() {
    JFileChooser fc = new JFileChooser(".");
    int returnVal = fc.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      //This is where a real application would save the file.
      System.out.println("Saving: " + file.getName() + ".");

    } else {
      System.out.println("Save command cancelled by user.");
    }
  }


}
