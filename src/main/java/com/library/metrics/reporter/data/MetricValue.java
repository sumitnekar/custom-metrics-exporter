package com.library.metrics.reporter.data;

public abstract class MetricValue<T> {

    T value;

    public MetricValue() {
    }

    public MetricValue(T value) {
        this.value = value;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }



}
