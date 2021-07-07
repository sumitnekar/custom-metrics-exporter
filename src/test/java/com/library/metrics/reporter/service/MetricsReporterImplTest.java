package com.library.metrics.reporter.service;


import com.library.metrics.reporter.api.MetricsReporter;
import com.library.metrics.reporter.data.CounterMetricValue;
import com.library.metrics.reporter.data.Label;
import com.library.metrics.reporter.data.Metric;
import com.library.metrics.reporter.exceptions.MetricsReporterException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MetricsReporterImplTest {

    @Test
    public void testRegister() {
        MetricsReporter reporter = new MetricsReporterImpl("/metrics");
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("operation", "create"));
        labels.add(new Label("app", "userservice"));
        Metric testMetric = new Metric("operationFailureCounts", new CounterMetricValue(), labels);
        Metric newMetric = reporter.register(testMetric);
        assert (newMetric.getId() != null);
        assert (newMetric.getValue().getValue().equals(0D));
    }

    @Test
    public void testUpdate() throws MetricsReporterException {
        MetricsReporter reporter = new MetricsReporterImpl("/metrics");
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("operation", "create"));
        labels.add(new Label("app", "userservice"));
        Metric testMetric = new Metric("operationFailureCounts", new CounterMetricValue(), labels);
        Metric newMetric = reporter.register(testMetric);
        assert(newMetric.getValue().getValue().equals(0D));
        //update
        Metric updatedMetric1 = reporter.update("operationFailureCounts", labels, newMetric.getValue());
        assert (updatedMetric1.getValue().getValue().equals(1D));
        //one more update
        Metric updatedMetric2 = reporter.update("operationFailureCounts", labels, newMetric.getValue());
        assert (updatedMetric2.getValue().getValue().equals(2D));

    }

}