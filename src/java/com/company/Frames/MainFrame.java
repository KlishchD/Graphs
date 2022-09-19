package com.company.Frames;


import com.company.Frames.GraphAgorithms.GraphAlgorithmsFrame;
import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.Utils.ButtonUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Main frame is a window which allows to select a type of algorithm to be watched or DataStructure, ...
 */
public class MainFrame extends Frame {
    private static final MainFrame instance = new MainFrame();

    private final Dimension FRAME_SIZE = new Dimension(400, 200);
    private final String GRAPH_ALGORITHM_BUTTON_TEXT = "Graph Algorithms";

    private MainFrame() {
    }

    public static MainFrame getInstance() {
        return instance;
    }

    private JButton createGraphAlgorithmsButton() {
        return ButtonUtils.createButton(GRAPH_ALGORITHM_BUTTON_TEXT, new FrameMoveActiveListener(this, GraphAlgorithmsFrame.getInstance()));
    }

    private void addComponents() {
        add(createGraphAlgorithmsButton());
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
