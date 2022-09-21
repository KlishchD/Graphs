package com.company.Frames;


import com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms.ArbitraryGraphAlgorithmsSelectFrame;
import com.company.Frames.GraphAgorithms.GridGrpahAlgorithms.GridGraphAlgorithmsSelectFrame;
import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.Utils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Main frame is a window which allows to select a type of algorithm to be watched or DataStructure, ...
 */
public class MainFrame extends Frame {
    private static final MainFrame instance = new MainFrame();

    private final Dimension FRAME_SIZE = new Dimension(400, 200);
    private final String GRID_GRAPH_ALGORITHM_BUTTON_TEXT = "Grid Graph Algorithms";
    private final String ARBITRARY_GRAPH_ALGORITHM_BUTTON_TEXT = "Arbitrary Graph Algorithms";

    private MainFrame() {
    }

    public static MainFrame getInstance() {
        return instance;
    }

    private JButton createGridGraphAlgorithmsButton() {
        return Utils.createButton(GRID_GRAPH_ALGORITHM_BUTTON_TEXT, new FrameMoveActiveListener(this, GridGraphAlgorithmsSelectFrame.getInstance()));
    }

    private JButton createArbitraryGraphAlgorithmsButton() {
        return Utils.createButton(ARBITRARY_GRAPH_ALGORITHM_BUTTON_TEXT, new FrameMoveActiveListener(this, ArbitraryGraphAlgorithmsSelectFrame.getInstance()));
    }

    private void addComponents() {
        add(createGridGraphAlgorithmsButton());
        add(createArbitraryGraphAlgorithmsButton());
    }

    @Override
    public void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        addComponents();

        setSize(FRAME_SIZE);
        setResizable(false);
        setVisible(true);
    }
}
