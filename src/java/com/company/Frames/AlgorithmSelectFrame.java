package com.company.Frames;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;

public abstract class AlgorithmSelectFrame extends Frame {
    /**
     * Adds button with specified name to select provided algorithm and render it on specified frame
     * @param name text on a button
     * @param algorithm algorithm to render
     * @param frame frame to used for rendering
     * @param <P> return type of algorithm
     * @param <T> type of vertex ids in graph
     * @param <E> type of values in vertex and edge
     */
    public abstract <P, T, E> void registerAlgorithm(String name, GraphAlgorithmInterface<P, T, E> algorithm, RenderingFrame<P, T, E> frame);
}
