package com.library.metrics.exporter.service;

import com.library.metrics.exporter.api.MetricsReporter;
import com.library.metrics.exporter.data.Label;
import com.library.metrics.exporter.data.Metric;
import com.library.metrics.exporter.data.MetricValue;
import com.library.metrics.exporter.exceptions.MetricsReporterException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MetricsReporterImpl implements MetricsReporter {

    Map<String, Metric> metricStore = new HashMap<>();

    @Override
    public void register(Metric metric) {
        String key = getMetricStoreKey(metric.getName(), metric.getLabels());
        metricStore.put(key, metric);
    }

    @Override
    public Metric update(String name, List<Label> labels, MetricValue<Double> value) throws MetricsReporterException {
        String key = getMetricStoreKey(name, labels);
        Metric existingMetric = metricStore.get(key);
        if (key != null) {
            //update
            existingMetric.setValue(value);
            metricStore.put(key, existingMetric);
        } else {
            throw new MetricsReporterException("Metric with name:" + name + " Labels:" + labels + " not found");
        }
        return existingMetric;

    }

    @Override
    public void exportAll() {


    }


    //Key will be combination of name and hash of labels
    private String getMetricStoreKey(String name, List<Label> labels) {
        return name + "_" + Objects.hash(labels);
    }
}
