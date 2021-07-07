package com.library.metrics.reporter.service;

import com.library.metrics.reporter.api.MetricsReporter;
import com.library.metrics.reporter.data.CounterMetricValue;
import com.library.metrics.reporter.data.Label;
import com.library.metrics.reporter.data.Metric;
import com.library.metrics.reporter.data.MetricValue;
import com.library.metrics.reporter.exceptions.MetricsReporterException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MetricsReporterImpl implements MetricsReporter {

    Map<String, Metric> metricStore = new HashMap<>();
    PushGateway pushGateway;

    public MetricsReporterImpl(String url) {
        pushGateway = new PushGateway("/metrics");
    }

    @Override
    public Metric register(Metric metric) {
        String key = getMetricStoreKey(metric.getName(), metric.getLabels());
        metricStore.put(key, metric);
        return metric;
    }

    @Override
    public Metric update(String name, List<Label> labels, MetricValue<Double> value) throws MetricsReporterException {
        String key = getMetricStoreKey(name, labels);
        Metric existingMetric = metricStore.get(key);
        if (key != null) {

            if (CounterMetricValue.class.isAssignableFrom(existingMetric.getValue().getClass())) {
                //update
                Double existingValue = existingMetric.getValue().getValue();
                existingMetric.setValue(new CounterMetricValue(existingValue + 1));
            } else {
                //update
                existingMetric.setValue(value);
            }

            metricStore.put(key, existingMetric);
        } else {
            throw new MetricsReporterException("Metric with name:" + name + " Labels:" + labels + " not found");
        }
        return existingMetric;

    }

    @Override
    public void exportAll() {
        postPeriodically(2, TimeUnit.MINUTES);
    }


    //creates scheduled executor and starts writing metrics periodically
    private void postPeriodically(long delay, TimeUnit timeUnit) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        es.schedule(new Runnable() {
            @Override
            public void run() {
                pushGateway.pushAll(metricStore.values());
            }
        }, delay, timeUnit);
    }

    //Key will be combination of name and hash of labels
    private String getMetricStoreKey(String name, List<Label> labels) {
        return name + "_" + Objects.hash(labels);
    }


}
