package io.github.mwttg.nndl.network;

import java.util.Random;
import java.util.stream.DoubleStream;

public class Layer {

  private final int numNeuronsIn;
  private final int numNeuronsOut;
  private final double[] weights;
  private final double[] biases;
  private final double[] costs;
  private final ActivationType activationType;

  public Layer(
      final int numNeuronsIn, final int numNeuronsOut, final ActivationType activationType) {
    this.numNeuronsIn = numNeuronsIn;
    this.numNeuronsOut = numNeuronsOut;
    this.activationType = activationType;

    this.weights =
        DoubleStream.generate(() -> new Random().nextDouble(-1.0, 1.0))
            .limit(numNeuronsIn)
            .toArray();
    this.biases =
        DoubleStream.generate(() -> new Random().nextDouble()).limit(numNeuronsOut).toArray();
    this.costs = new double[numNeuronsOut];
  }

  public double[] calculateOutput(final double[] input) {
    if (numNeuronsIn != input.length) {
      throw new RuntimeException(
          "The number ('%s') of input signals for this layer does NOT match the number ('%s') of Neurons inside this layer."
              .formatted(input.length, numNeuronsIn));
    }

    double[] result = new double[numNeuronsOut];
    for (int outIndex = 0; outIndex < numNeuronsOut; outIndex++) {

      double value = 0;
      for (int inIndex = 0; inIndex < numNeuronsIn; inIndex++) {
        value = value + input[inIndex] * weights[inIndex];
      }

      value = value + biases[outIndex];
      value = activationType.apply(value);
      result[outIndex] = value;
    }

    return result;
  }

  public double[] calculateCosts(final double[] expectedValues) {
    if (numNeuronsOut != expectedValues.length) {
      throw new RuntimeException(
          "The number ('%s') of expected values for this layer does NOT match the number ('%s') of the output of the Neurons of this layer"
              .formatted(numNeuronsOut, expectedValues.length));
    }

    return null;
  }

  public void updateBiases() {}

  public void updateWeights() {}
}
