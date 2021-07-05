package com.library.metrics.exporter.api;

import com.library.metrics.exporter.data.Label;
import com.library.metrics.exporter.data.Metric;
import com.library.metrics.exporter.data.MetricValue;
import com.library.metrics.exporter.exceptions.MetricsReporterException;

import java.util.List;

public interface MetricsReporter {

    public void register(Metric metric);

    public Metric update(String name, List<Label> labels, MetricValue<Double> value) throws MetricsReporterException;

    public void exportAll();


}
