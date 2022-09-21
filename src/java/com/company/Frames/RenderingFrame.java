package com.company.Frames;

import com.company.Graphs.Algorithms.TraversingAlgorithms.GraphTraversingAlgorithm;
import com.company.Graphs.GraphInterface;

public class RenderingFrame<T, E> extends Frame {
    protected GraphInterface<T, E> graph;
    protected GraphTraversingAlgorithm<T, E> algorithm;

    public void setAlgorithm(GraphTraversingAlgorithm<T, E> algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void setUp() {

    }
}
