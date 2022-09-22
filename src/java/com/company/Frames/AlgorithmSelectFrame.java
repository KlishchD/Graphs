package com.company.Frames;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;

public abstract class AlgorithmSelectFrame extends Frame {
    public abstract <P, T, E> void registerAlgorithm(String name, GraphAlgorithmInterface<P, T, E> algorithm, RenderingFrame<P, T, E> frame);
}
