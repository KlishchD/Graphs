package com.company.Frames.Utils;

/**
 * Class for data sharing
 * @param <T> type of date to shares
 */
public class DataCarrier<T> {
    private T value;
    public DataCarrier(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
