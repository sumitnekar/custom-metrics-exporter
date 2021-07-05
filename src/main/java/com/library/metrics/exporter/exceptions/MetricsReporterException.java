package com.library.metrics.exporter.exceptions;

public class MetricsReporterException extends Exception {
    public MetricsReporterException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetricsReporterException(String message) {
        super(message);
    }
}
