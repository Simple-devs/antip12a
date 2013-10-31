package org.carlgo11.anti.p12a.Metrics;

import org.carlgo11.anti.p12a.Metrics.Metrics;

public class SimplePlotter extends Metrics.Plotter {

    public SimplePlotter(final String name) {
        super(name);
    }

    @Override
    public int getValue() {
        return 1;
    }
}