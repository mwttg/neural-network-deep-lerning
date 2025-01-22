package io.github.mwttg.nndl.network.activation;

public class ReluFunction implements ActivationFunction {
  @Override
  public double apply(final double value) {
    return Math.max(0, value);
  }
}
