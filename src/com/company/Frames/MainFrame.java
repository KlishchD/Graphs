package com.company.Frames;


import com.company.Frames.DataStuctures.DataStructuresFrame;
import com.company.Frames.GraphAgorithms.GraphAlgorithmsFrame;
import com.company.Frames.Listeners.MenuMoveActiveListener;

import javax.swing.*;
import java.awt.*;

import static com.company.Frames.Utils.ButtonUtils.createButton;

public class MainFrame extends Frame {
    private static final MainFrame instance = new MainFrame();

    private MainFrame() {
    }

    public static MainFrame getInstance() {
        return instance;
    }

    private JButton createGraphAlgorithmsButton() {
        return createButton("Graph Algorithms", new MenuMoveActiveListener(this, GraphAlgorithmsFrame.getInstance()));
    }

    private JButton createDataStructuresButton() {
        return createButton("Data Structures", new MenuMoveActiveListener(this, DataStructuresFrame.getInstance()));
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
