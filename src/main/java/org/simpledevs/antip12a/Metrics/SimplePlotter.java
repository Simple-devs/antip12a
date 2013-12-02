package org.simpledevs.antip12a.Metrics;

public class SimplePlotter extends Metrics.Plotter {

    public SimplePlotter(final String name) {
        super(name);
    }

    @Override
    public int getValue() {
        return 1;
    }
}