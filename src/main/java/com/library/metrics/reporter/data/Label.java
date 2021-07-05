package com.library.metrics.reporter.data;

import java.util.Objects;

public class Label {

    String key;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Label(String key, String value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return key.equals(label.key) && value.equals(label.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
