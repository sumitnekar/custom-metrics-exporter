package com.library.metrics.reporter.data;

public class CounterMetricValue extends MetricValue<Double> {

    public CounterMetricValue() {
        if (null == this.value) {
            this.value = 0D;
        }
    }

    public CounterMetricValue(Double value) {
        super(value);
    }

    @Override
    public void setValue(Double value) {
        super.setValue(value + 1);
    }
}

