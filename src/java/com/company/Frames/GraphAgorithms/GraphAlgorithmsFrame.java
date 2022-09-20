package com.company.Frames.GraphAgorithms;

import com.company.Frames.Frame;
import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.Listeners.SetTraversingGraphAlgorithmListener;
import com.company.Frames.MainFrame;
import com.company.Graphs.Algorithms.TraversingAlgorithms.BFSTraversingAlgorithm;
import com.company.Graphs.Algorithms.TraversingAlgorithms.DFSTraversingAlgorithm;

import javax.swing.*;
import java.awt.*;

import static com.company.Frames.Utils.ButtonUtils.createButton;

/**
 * Window to select a graph algorithm
 */
public class GraphAlgorithmsFrame extends Frame {
    private static final GraphAlgorithmsFrame instance = new GraphAlgorithmsFrame();
    private final Dimension FRAME_SIZE = new Dimension(400, 200);
    private final String BFS_BUTTON_TEXT = "BFS";
    private final String DFS_BUTTON_TEXT = "DFS";
    private final String BACK_BUTTON_TEXT = "Back";

    private GraphAlgorithmsFrame() {
    }

    static public GraphAlgorithmsFrame getInstance() {
        return instance;
    }

    private JButton createBFSButton() {
        return createButton(BFS_BUTTON_TEXT,
                new FrameMoveActiveListener(this, GridGraphAlgorithmFrame.getInstance()),
                new SetTraversingGraphAlgorithmListener(new BFSTraversingAlgorithm()));
    }

    private JButton createDFSButton() {
        return createButton(DFS_BUTTON_TEXT,
                new FrameMoveActiveListener(this, GridGraphAlgorithmFrame.getInstance()),
                new SetTraversingGraphAlgorithmListener(new DFSTraversingAlgorithm()));
    }

    private JButton createBackButton() {
        return createButton(BACK_BUTTON_TEXT, new FrameMoveActiveListener(this, MainFrame.getInstance()));
    }

    private void addComponents() {
        add(createBFSButton());
        add(createDFSButton());
        add(createBackButton());
    }

    @Override
    public void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setSize(FRAME_SIZE);
        setVisible(false);
        addComponents();
    }
}
