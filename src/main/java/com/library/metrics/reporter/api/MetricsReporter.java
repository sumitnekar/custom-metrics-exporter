package com.library.metrics.reporter.api;

import com.library.metrics.reporter.data.Label;
import com.library.metrics.reporter.data.Metric;
import com.library.metrics.reporter.data.MetricValue;
import com.library.metrics.reporter.exceptions.MetricsReporterException;

import java.util.List;

public interface MetricsReporter {

    public Metric register(Metric metric);

    public Metric update(String name, List<Label> labels, MetricValue<Double> value) throws MetricsReporterException;

    public void exportAll();


}
