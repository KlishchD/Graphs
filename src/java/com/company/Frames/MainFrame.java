package com.company.Frames;


import com.company.Frames.Listeners.FrameMoveActiveListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.Frames.Utils.Utils.createButton;

/**
 * Main frame is a window which allows to select a type of algorithm to be watched or DataStructure, ...
 */
public class MainFrame extends Frame {
    private static final MainFrame instance = new MainFrame();
    private final Dimension FRAME_SIZE = new Dimension(400, 200);
    private final List<JButton> selectFrameButton = new ArrayList<>();

    private MainFrame() {
    }

    public static MainFrame getInstance() {
        return instance;
    }

    public void registerSelectFrame(String name, AlgorithmSelectFrame frame) {
        selectFrameButton.add(createButton(name, new FrameMoveActiveListener(this, frame)));
    }

    private void addComponents() {
        selectFrameButton.forEach(this::add);
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
