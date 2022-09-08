package com.company.Graphs.Algorithms;

import com.company.Graphs.Graph;

public interface GraphAlgorithmInterface<T, E, V> {
    T run(Graph<E, V> graph);
}
