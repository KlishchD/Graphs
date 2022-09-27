package com.company.Graphs.Algorithms;

public class Tuple<T, E extends Comparable<E>> implements Comparable<Tuple<T, E>> {
    private final E value;
    private final T from;
    private final T to;

    public Tuple(E value, T from, T to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }

    @Override
    public int compareTo(Tuple<T, E> o) {
        return value.compareTo(o.value);
    }

    public E getValue() {
        return value;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }
}