package io.github.mwttg.nndl;

import io.github.mwttg.nndl.idx.IdxFileReader;
import io.github.mwttg.nndl.idx.IdxImageFile;
import io.github.mwttg.nndl.idx.IdxLabelFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NeuralNetwork {

  private static final Logger LOG = LoggerFactory.getLogger(NeuralNetwork.class);

  public static void main(String[] args) throws IOException {
    final String t10kImages = "./dataset/original/t10k-images-idx3-ubyte.gz";
    final String t10kLabels = "./dataset/original/t10k-labels-idx1-ubyte.gz";
    final String trainImages = "./dataset/original/train-images-idx3-ubyte.gz";
    final String trainLabels = "./dataset/original/train-labels-idx1-ubyte.gz";

    final IdxImageFile idxImage = IdxFileReader.readImageFile(t10kImages);
    final IdxLabelFile idxLabel = IdxFileReader.readLabelFile(t10kLabels);

    final int index = 132;
    LOG.info("The shown number is '{}'", idxLabel.labels().get(index));
    showImage(idxImage.imageWidth(), idxImage.imageHeight(), idxImage.images().get(index));
  }

  // for debugging if IDX files are read correctly
  private static void showImage(final int width, final int height, final int[] pixels) throws IOException {
    final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    for(int y = 0; y < width; y++) {
      for (int x = 0; x < height; x++) {
        final int pixelIndex = y * height + x;
        final int pixelColor = pixels[pixelIndex];
        final Color color = new Color(pixelColor, pixelColor, pixelColor);
        image.setRGB(x, y, color.getRGB());
      }
    }

    // final File output = new File("output.png");
    // ImageIO.write(image, "png", output);

    SwingUtilities.invokeLater(() -> {
      final JFrame frame = new JFrame("Check MNIST dataset");
      frame.add(new JLabel(new ImageIcon(image)));

      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    });
  }
}
