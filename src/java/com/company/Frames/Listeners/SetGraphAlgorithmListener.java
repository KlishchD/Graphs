package com.company.Frames.Listeners;

import com.company.Frames.RenderingFrame;
import com.company.Graphs.Algorithms.GraphAlgorithmInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener that in case of graph algorithm selection provides selected algorithm to an AlgorithManage
 */
public class SetGraphAlgorithmListener<P, T, E> implements ActionListener {
    private final GraphAlgorithmInterface<P, T, E> algorithm;
    private final RenderingFrame<P, T, E> frame;

    public SetGraphAlgorithmListener(GraphAlgorithmInterface<P, T, E> algorithm, RenderingFrame<P, T, E> frame) {
        this.algorithm = algorithm;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setAlgorithm(algorithm);
    }
}
