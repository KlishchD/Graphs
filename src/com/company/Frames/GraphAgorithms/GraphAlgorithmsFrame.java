package com.company.Frames.GraphAgorithms;

import com.company.Frames.Frame;
import com.company.Frames.Listeners.MenuMoveActiveListener;
import com.company.Frames.Listeners.SetGridGraphAlgorithm;
import com.company.Frames.MainFrame;
import com.company.Graphs.Algorithms.GridGraphAlgorithms.BFSWithRestorationMapGridGraphAlgorithm;
import com.company.Graphs.Algorithms.GridGraphAlgorithms.DFSWithRestorationMapGridGraphAlgorithm;

import javax.swing.*;

import static com.company.Frames.Utils.ButtonUtils.createButton;

public class GraphAlgorithmsFrame extends Frame {
    private static final GraphAlgorithmsFrame instance = new GraphAlgorithmsFrame();

    private GraphAlgorithmsFrame() {}

    static public GraphAlgorithmsFrame getInstance() {
        return instance;
    }

    private JButton createBFSButton() {
        return createButton("BFS",
                new MenuMoveActiveListener(this, GridGraphAlgorithmFrame.getInstance()),
                new SetGridGraphAlgorithm(new BFSWithRestorationMapGridGraphAlgorithm()));
    }

    private JButton createDFSButton() {
        return createButton("DFS",
                new MenuMoveActiveListener(this, GridGraphAlgorithmFrame.getInstance()),
                new SetGridGraphAlgorithm(new DFSWithRestorationMapGridGraphAlgorithm()));
    }

    private JButton createBackButton() {
        return createButton("back", new MenuMoveActiveListener(this, MainFrame.getInstance()));
    }

    private void addComponents() {
        add(createBFSButton());
        add(createDFSButton());
        add(createBackButton());
    }

    @Override
    public void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        setSize(100 * 4, 100 * 2);
        setVisible(false);
        addComponents();
    }
}