package io.github.mwttg.nndl.network.activation;

@FunctionalInterface
public interface ActivationFunction {

    double apply(final double value);
}
