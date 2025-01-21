package io.github.mwttg.nndl.idx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdxFileReader {

  private static final Logger LOG = LoggerFactory.getLogger(IdxFileReader.class);

  public static IdxImageFile readImageFile(final String filename) {
    LOG.info("Reading idx image file '{}'", filename);
    try (final DataInputStream stream =
        new DataInputStream(new GZIPInputStream(new FileInputStream(filename)))) {

      final int magicNumber = stream.readInt();
      if (magicNumber != 2051) {
        throw new RuntimeException(
            "The file type is no IDX image file type. The magic number was '%s' instead of 2051"
                .formatted(magicNumber));
      }

      final int imageCount = stream.readInt();
      final int imageWidth = stream.readInt();
      final int imageHeight = stream.readInt();
      final int pixelCount = (imageWidth) * (imageHeight);

      List<int[]> images = new ArrayList<>();
      for (int index = 0; index < imageCount; index++) {
        final int[] imageData = new int[pixelCount];
        for (int dataIndex = 0; dataIndex < pixelCount; dataIndex++) {
          imageData[dataIndex] = stream.readUnsignedByte();
        }
        images.add(imageData);
      }
      return new IdxImageFile(imageCount, imageWidth, imageHeight, images);

    } catch (IOException e) {
      LOG.error("Error occurred during reading idx image file", e);
      throw new RuntimeException(e);
    }
  }

  public static IdxLabelFile readLabelFile(final String filename) {
    LOG.info("Reading idx label file '{}'", filename);
    try (final DataInputStream stream =
        new DataInputStream(new GZIPInputStream(new FileInputStream(filename)))) {

      final int magicNumber = stream.readInt();
      if (magicNumber != 2049) {
        throw new RuntimeException(
            "The file type is no IDX label file type. The magic number was '%s' instead of 2049"
                .formatted(magicNumber));
      }

      final int labelCount = stream.readInt();

      List<Integer> labels = new ArrayList<>();
      for (int index = 0; index < labelCount; index++) {
        final int label = stream.readUnsignedByte();
        labels.add(label);
      }
      return new IdxLabelFile(labelCount, labels);

    } catch (IOException e) {
      LOG.error("Error occurred during reading idx label file", e);
      throw new RuntimeException(e);
    }
  }
}
