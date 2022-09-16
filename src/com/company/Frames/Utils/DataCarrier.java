package com.company.Frames.Utils;

public class DataCarrier<T> {
    private T value;
    public DataCarrier(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value, int i) {
        this.value = value;
    }
}
