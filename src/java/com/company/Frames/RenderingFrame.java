package com.company.Frames;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.GraphInterface;

import java.awt.*;


/**
 * @param <P> return type of algorithm
 * @param <T> type of vertex ids in graph
 * @param <E> type of values in vertex and edge
 */
public abstract class RenderingFrame<P, T, E> extends Frame {
    protected final Color VISITED_POINT_COLOR = Color.ORANGE;
    protected final Color POINT_PART_OF_RESTORED_PATH_COLOR = Color.CYAN;
    protected GraphInterface<T, E> graph;
    protected GraphAlgorithmInterface<P, T, E> algorithm;

    public void setAlgorithm(GraphAlgorithmInterface<P, T, E> algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Runs specified algorithm and renders it
     */
    public abstract void runAlgorithm();

    @Override
    public abstract void setUp();

    public abstract void resetVisuals();

    public abstract void resetField();

    public abstract void resetVertexes();
}
