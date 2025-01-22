package io.github.mwttg.nndl.network.activation;

public class LeakyReluFunction implements ActivationFunction {
  @Override
  public double apply(double value) {
    return Math.max((0.1 * value), value);
  }
}
