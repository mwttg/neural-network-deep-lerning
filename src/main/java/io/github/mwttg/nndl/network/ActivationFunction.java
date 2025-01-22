package io.github.mwttg.nndl.network;

public interface ActivationFunction {

    double apply(final double value);

    double applyDerivation(final double value);
}
