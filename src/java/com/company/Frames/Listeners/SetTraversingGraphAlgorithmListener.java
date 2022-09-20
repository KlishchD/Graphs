package com.company.Frames.Listeners;

import com.company.Frames.GraphAgorithms.GridGraphAlgorithmFrame;
import com.company.Graphs.Algorithms.TraversingAlgorithms.GraphTraversingAlgorithm;
import com.company.Graphs.GridPoint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener that in case of graph algorithm selection provides selected algorithm to an AlgorithManage
 */
public class SetTraversingGraphAlgorithmListener implements ActionListener {
    private final GraphTraversingAlgorithm<GridPoint, Integer> algorithm;

    public SetTraversingGraphAlgorithmListener(GraphTraversingAlgorithm<GridPoint, Integer> algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GridGraphAlgorithmFrame.getInstance().setAlgorithm(algorithm);
    }
}
