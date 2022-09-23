package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

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
public class ArbitraryGraphAlgorithmsSelectFrame extends AlgorithmSelectFrame {
    private static final ArbitraryGraphAlgorithmsSelectFrame instance = new ArbitraryGraphAlgorithmsSelectFrame();
    private final Dimension FRAME_SIZE = new Dimension(400, 200);
    private final String BACK_BUTTON_TEXT = "Back";
    private final List<JButton> algorithms = new ArrayList<>();
    private ArbitraryGraphAlgorithmRenderFrame<Object> renderer;

    private ArbitraryGraphAlgorithmsSelectFrame() {
    }

    static public ArbitraryGraphAlgorithmsSelectFrame getInstance() {
        return instance;
    }

    public ArbitraryGraphAlgorithmRenderFrame<Object> getRenderer() {
        return renderer;
    }

    @Override
    public <P, T, E> void registerAlgorithm(String name, GraphAlgorithmInterface<P, T, E> algorithm, RenderingFrame<P, T, E> frame) {
        JButton button = createButton(name, new FrameMoveActiveListener(this, frame), new SetGraphAlgorithmListener<>(algorithm, frame), e -> renderer = (ArbitraryGraphAlgorithmRenderFrame<Object>) frame);
        algorithms.add(button);
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
        algorithms.forEach(this::add);
        add(createBackButton());
    }
}
