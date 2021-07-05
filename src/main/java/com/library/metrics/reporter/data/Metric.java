package com.library.metrics.reporter.data;

import java.util.List;
import java.util.UUID;

public class Metric {
    String id;
    String name;
    MetricValue<Double> value;
    List<Label> labels;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetricValue<Double> getValue() {
        return value;
    }

    public void setValue(MetricValue<Double> value) {
        this.value = value;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }


    public Metric(String name, MetricValue<Double> value, List<Label> labels) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.value = value;
        this.labels = labels;
    }
}
