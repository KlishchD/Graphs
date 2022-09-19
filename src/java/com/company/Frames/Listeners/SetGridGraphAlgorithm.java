package com.company.Frames.Listeners;

import com.company.Frames.GraphAgorithms.GridGraphAlgorithmFrame;
import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Algorithms.GridGraphAlgorithms.GridPoint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Listener that in case of graph algorithm selection provides selected algorithm to an AlgorithManage
 */
public class SetGridGraphAlgorithm implements ActionListener {
    private GraphAlgorithmInterface<Map<GridPoint, GridPoint>, GridPoint, Integer> algorithm;

    public SetGridGraphAlgorithm(GraphAlgorithmInterface<Map<GridPoint, GridPoint>, GridPoint, Integer> algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GridGraphAlgorithmFrame.getInstance().getAlgorithmManager().setAlgorithm(algorithm);
    }
}
