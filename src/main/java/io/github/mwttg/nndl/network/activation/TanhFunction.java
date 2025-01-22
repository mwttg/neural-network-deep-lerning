package io.github.mwttg.nndl.network.activation;

public class TanhFunction implements ActivationFunction {
  @Override
  public double apply(double value) {
    // TODO ausklammern
    return (Math.exp(value) - Math.exp(-value)) / (Math.exp(value) + Math.exp(-value));
  }
}
