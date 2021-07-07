package com.library.metrics.reporter.service;

import com.library.metrics.reporter.data.Metric;

import java.util.Collection;

public class PushGateway {

    String url;

    public PushGateway(String url) {
        this.url = url;
    }

    public void push(Metric metric) {
        System.out.println("Write to /metrics endpoint");
        //Create http server
        //create /metrics endpoint
        // Write to output stream
    }


    public void pushAll(Collection<Metric> metrics) {
        System.out.println("Write to /metrics endpoint");
        //Create http server
        //create /metrics endpoint
        // Write to output stream
    }
}
