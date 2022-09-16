package com.company.Graphs.Algorithms;

import com.company.Graphs.Algorithms.GridGraphAlgorithms.GridPoint;

import java.util.Set;

/**
 * @param <T> return type of result of an algorithm
 * @param <E> Type of vertexId
 * @param <V> Type of values in vertex
 */
public interface GridGraphAlgorithmInterface<T, E, V> extends GraphAlgorithmInterface<T, E, V> {
    void setStarts(Set<GridPoint> strats);

    Set<GridPoint> getStarts();

    void setFinishes(Set<GridPoint> finishes);

    Set<GridPoint> getFinishes();

    void setBlocks(Set<GridPoint> blocks);

    Set<GridPoint> getBlocks();
}
