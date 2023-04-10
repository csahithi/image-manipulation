package view;

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

public class ImageProcessingViewImpl extends JFrame implements ImageProcessingView{
  private JButton loadButton;
  private JButton saveButton;
  private JButton horizontalFlipButton;
  private JButton verticalFlipButton;
  private JButton rgbSplitButton;
  private JButton rgbCombineButton;
  private JButton sepiaButton;
  private JButton greyscaleButton;
  private JButton ditherButton;
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

    imagePane.setSize(200,200);
    imagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    imagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imagePanel.add(imagePane);

    histogramLabel = new JLabel(new ImageIcon());
    histogramPane = new JScrollPane(histogramLabel);
    histogramPane.setSize(200,200);
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

    //this.pack();
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
    switch(command){
      case "load":
        String filePath = showLoadImageDialog();
        if(filePath!=null) {
          return List.of(new String[]{"load", filePath, "image"});
        }
        else{
          return null;
        }
      case "save":
      case "brighten":
      case "greyscale":
        return List.of(new String[]{"greyscale", "luma-component", "image", "image"});
      case "rgb-split":
      case "rgb-combine":
    }
    return imageParameters;
  }

  @Override
  public void displayCurrentImage(List<Image> m) {
    if(m.size()==1){
      image = new ImageIcon(getImageToDisplay(m.get(0)));
      imageLabel.setIcon(image);
      validate();
      //imageLabel.revalidate();
    }
    else{
      rgbSplitSaveDialog(m);
    }
  }

  @Override
  public void displayErrorDialog() {

  }

  private BufferedImage getImageToDisplay(Image m){
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

  private void rgbSplitSaveDialog(List<Image> m){
    JDialog rgbSplitSave = new JDialog();
  }

  private String showLoadImageDialog(){
    JFileChooser fc = new JFileChooser(".");
    int returnVal = fc.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      //This is where a real application would open the file.
      System.out.println("Opening: " + file.getName() + "." );
      return file.getPath();
    } else {
      System.out.println("Open command cancelled by user.");
      return null;
    }
  }
  private void showSaveImageDialog(){
    JFileChooser fc = new JFileChooser(".");
    int returnVal = fc.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      //This is where a real application would save the file.
      System.out.println("Saving: " + file.getName() + "." );

    } else {
      System.out.println("Save command cancelled by user.");
    }
  }
}
