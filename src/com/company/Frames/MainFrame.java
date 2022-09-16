package com.company.Frames;


import com.company.Frames.DataStuctures.DataStructuresFrame;
import com.company.Frames.GraphAgorithms.GraphAlgorithmsFrame;
import com.company.Frames.Listeners.FrameMoveActiveListener;

import javax.swing.*;
import java.awt.*;

import static com.company.Frames.Utils.ButtonUtils.createButton;

/**
 * Main frame is a window which allows to select a type of algorithm to be watched or DataStructure, ...
 */
public class MainFrame extends Frame {
    private static final MainFrame instance = new MainFrame();

    private MainFrame() {
    }

    public static MainFrame getInstance() {
        return instance;
    }

    private JButton createGraphAlgorithmsButton() {
        return createButton("Graph Algorithms", new FrameMoveActiveListener(this, GraphAlgorithmsFrame.getInstance()));
    }

    private JButton createDataStructuresButton() {
        return createButton("Data Structures", new FrameMoveActiveListener(this, DataStructuresFrame.getInstance()));
    }

    private void addComponents() {
        add(createGraphAlgorithmsButton());
        //   add(createDataStructuresButton());
    }

    @Override
    public void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        addComponents();
        setSize(100 * 4, 50 * 4);
        setResizable(false);
        setVisible(true);
    }
}
