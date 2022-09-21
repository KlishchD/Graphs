package com.company.Frames.Listeners;

import com.company.Frames.RenderingFrame;
import com.company.Graphs.Algorithms.TraversingAlgorithms.GraphTraversingAlgorithm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener that in case of graph algorithm selection provides selected algorithm to an AlgorithManage
 */
public class SetTraversingGraphAlgorithmListener<T, E> implements ActionListener {
    private final GraphTraversingAlgorithm<T, E> algorithm;
    private final RenderingFrame<T, E> frame;

    public SetTraversingGraphAlgorithmListener(GraphTraversingAlgorithm<T, E> algorithm, RenderingFrame<T, E> frame) {
        this.algorithm = algorithm;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setAlgorithm(algorithm);
    }
}
