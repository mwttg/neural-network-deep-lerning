package io.github.mwttg.nndl.network;

public enum ActivationType implements ActivationFunction {
  SIGMOID {
    @Override
    public double apply(double value) {
      return 1 / (1 + Math.exp(-value));
    }

    @Override
    public double applyDerivation(double value) {
      return apply(value) * (1 - apply(value));
    }
  },

  RELU {
    @Override
    public double apply(double value) {
      return Math.max(0, value);
    }

    @Override
    public double applyDerivation(double value) {
      throw new UnsupportedOperationException("Not implemented yet");
    }
  },

  LEAKY_RELU {
    @Override
    public double apply(double value) {
      return Math.max((0.1 * value), value);
    }

    @Override
    public double applyDerivation(double value) {
      throw new UnsupportedOperationException("Not implemented yet");
    }
  },

  TANH {
    @Override
    public double apply(double value) {
      return (Math.exp(value) - Math.exp(-value)) / (Math.exp(value) + Math.exp(-value));
    }

    @Override
    public double applyDerivation(double value) {
      throw new UnsupportedOperationException("Not implemented yet");
    }
  }
}
