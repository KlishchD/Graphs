package com.company.Frames.GraphAgorithms.GridGrpahAlgorithms;

import com.company.Frames.AlgorithmSelectFrame;
import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.Listeners.SetGraphAlgorithmListener;
import com.company.Frames.MainFrame;
import com.company.Frames.RenderingFrame;
import com.company.Graphs.Algorithms.GraphAlgorithmInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.Frames.Utils.Utils.createButton;

/**
 * Window to select a graph algorithm
 */
public class GridGraphAlgorithmsSelectFrame extends AlgorithmSelectFrame {
    private static final GridGraphAlgorithmsSelectFrame instance = new GridGraphAlgorithmsSelectFrame();
    private final Dimension FRAME_SIZE = new Dimension(400, 200);
    private final String BACK_BUTTON_TEXT = "Back";
    private final List<JButton> algorithmSelectButton = new ArrayList<>();

    private GridGraphAlgorithmsSelectFrame() {
    }

    static public GridGraphAlgorithmsSelectFrame getInstance() {
        return instance;
    }

    @Override
    public <P, T, E> void registerAlgorithm(String name, GraphAlgorithmInterface<P, T, E> algorithm, RenderingFrame<P, T, E> frame) {
        JButton button = createButton(name, new FrameMoveActiveListener(this, frame), new SetGraphAlgorithmListener<>(algorithm, frame));
        algorithmSelectButton.add(button);
    }

    @Override
    public void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setSize(FRAME_SIZE);
        setVisible(false);
        addComponents();
    }

    private JButton createBackButton() {
        return createButton(BACK_BUTTON_TEXT, new FrameMoveActiveListener(this, MainFrame.getInstance()));
    }

    private void addComponents() {
        algorithmSelectButton.forEach(this::add);
        add(createBackButton());
    }
}
