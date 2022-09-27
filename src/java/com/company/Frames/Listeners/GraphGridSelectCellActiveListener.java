package com.company.Frames.Listeners;

import com.company.Frames.GraphAgorithms.GridGrpahAlgorithms.GridGraphAlgorithmRenderingFrame;
import com.company.Graphs.GridPoint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener that in case of a click on a grid button triggers update of corresponding point in a graph
 */
public class GraphGridSelectCellActiveListener implements ActionListener {
    private final int row;
    private final int col;

    public GraphGridSelectCellActiveListener(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GridGraphAlgorithmRenderingFrame.getInstance().updatePointType(new GridPoint(row, col));
    }
}
