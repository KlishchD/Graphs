package com.company.Frames;

import com.company.Graphs.Algorithms.TraversingAlgorithms.GraphTraversingAlgorithm;
import com.company.Graphs.GraphInterface;

import java.awt.*;

public abstract class RenderingFrame<T, E> extends Frame {
    protected final Color VISITED_POINT_COLOR = Color.ORANGE;
    protected final Color POINT_PART_OF_RESTORED_PATH_COLOR = Color.CYAN;
    protected GraphInterface<T, E> graph;
    protected GraphTraversingAlgorithm<T, E> algorithm;

    public void setAlgorithm(GraphTraversingAlgorithm<T, E> algorithm) {
        this.algorithm = algorithm;
    }

    protected abstract void runAlgorithm();

    @Override
    public void setUp() {

    }
}
