package io.github.mwttg.nndl.network;

import io.github.mwttg.nndl.network.activation.ActivationFunction;
import java.util.Random;
import java.util.stream.DoubleStream;

public class Layer {

  private final int numNeuronsIn;
  private final int numNeuronsOut;
  private final double[] weights;
  private final double[] biases;
  private final ActivationFunction activationFunction;

  public Layer(
      final int numNeuronsIn,
      final int numNeuronsOut,
      final ActivationFunction activationFunction) {
    this.numNeuronsIn = numNeuronsIn;
    this.numNeuronsOut = numNeuronsOut;
    this.activationFunction = activationFunction;

    this.weights =
        DoubleStream.generate(() -> new Random().nextDouble()).limit(numNeuronsIn).toArray();
    this.biases =
        DoubleStream.generate(() -> new Random().nextDouble()).limit(numNeuronsOut).toArray();
  }

  public double[] calculateOutputOfEachNeuron(final double[] input) {
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
      value = activationFunction.apply(value);
      result[outIndex] = value;
    }

    return result;
  }

  public void updateBiases() {}

  public void updateWeights() {}
}
