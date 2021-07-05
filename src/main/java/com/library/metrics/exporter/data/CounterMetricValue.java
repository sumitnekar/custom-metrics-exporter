package com.library.metrics.exporter.data;

public class CounterMetricValue extends MetricValue<Double> {

    public CounterMetricValue(Double value) {
        super(value);
    }

    @Override
    public void setValue(Double value) {
        super.setValue(value + 1);
    }
}

