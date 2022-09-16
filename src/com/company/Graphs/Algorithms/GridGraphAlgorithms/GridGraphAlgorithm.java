package com.company.Graphs.Algorithms.GridGraphAlgorithms;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;

import java.util.Set;

public interface GridGraphAlgorithm<T, E, V> extends GraphAlgorithmInterface<T, E, V> {
    void setStarts(Set<GridPoint> strats);

    Set<GridPoint> getStarts();

    void setFinishes(Set<GridPoint> finishes);

    Set<GridPoint> getFinishes();

    void setBlocks(Set<GridPoint> blocks);

    Set<GridPoint> getBlocks();
}
