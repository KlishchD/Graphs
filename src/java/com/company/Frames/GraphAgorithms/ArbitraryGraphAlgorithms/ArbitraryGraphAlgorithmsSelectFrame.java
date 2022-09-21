package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import com.company.Frames.Frame;
import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.Listeners.SetTraversingGraphAlgorithmListener;
import com.company.Frames.MainFrame;
import com.company.Frames.RenderingFrame;
import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Algorithms.TraversingAlgorithms.BFSTraversingAlgorithm;
import com.company.Graphs.Algorithms.TraversingAlgorithms.DFSTraversingAlgorithm;

import javax.swing.*;
import java.awt.*;

import static com.company.Frames.Utils.Utils.createButton;

/**
 * Window to select a graph algorithm
 */
public class ArbitraryGraphAlgorithmsSelectFrame extends Frame {
    private static final ArbitraryGraphAlgorithmsSelectFrame instance = new ArbitraryGraphAlgorithmsSelectFrame();
    private final Dimension FRAME_SIZE = new Dimension(400, 200);
    private final String BFS_BUTTON_TEXT = "BFS";
    private final String DFS_BUTTON_TEXT = "DFS";
    private final String BACK_BUTTON_TEXT = "Back";

    private ArbitraryGraphAlgorithmsSelectFrame() {
    }

    static public ArbitraryGraphAlgorithmsSelectFrame getInstance() {
        return instance;
    }

    private JButton createBFSButton() {
        return createButton(BFS_BUTTON_TEXT, new FrameMoveActiveListener(this, ArbitraryGraphTraversingAlgorithmRenderFrame.getInstance()),
                new SetTraversingGraphAlgorithmListener<>(new BFSTraversingAlgorithm<>(), ArbitraryGraphTraversingAlgorithmRenderFrame.getInstance()));
    }

    private JButton createDFSButton() {
        return createButton(DFS_BUTTON_TEXT, new FrameMoveActiveListener(this, ArbitraryGraphTraversingAlgorithmRenderFrame.getInstance()),
                new SetTraversingGraphAlgorithmListener<>(new DFSTraversingAlgorithm<>(), ArbitraryGraphTraversingAlgorithmRenderFrame.getInstance()));
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
