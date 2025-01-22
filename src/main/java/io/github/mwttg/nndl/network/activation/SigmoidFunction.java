package io.github.mwttg.nndl.network.activation;

public class SigmoidFunction implements ActivationFunction {
  @Override
  public double apply(final double value) {
    return 1 / (1 + Math.exp(-value));
  }
}
