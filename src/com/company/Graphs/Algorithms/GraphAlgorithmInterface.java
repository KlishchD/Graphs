package com.company.Graphs.Algorithms;

import com.company.Graphs.GraphInterface;

/**
 * @param <T> return type of result of an algorithm
 * @param <E> Type of vertexId
 * @param <V> Type of values in vertex
 */
public interface GraphAlgorithmInterface<T, E, V> {
    T run(GraphInterface<E, V> graph);
}
