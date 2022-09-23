package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import com.company.Frames.Frame;
import com.company.Frames.Listeners.HideMenuActiveListener;
import com.company.Frames.Utils.DataCarrier;

import javax.swing.*;
import java.awt.*;

import static com.company.Frames.Utils.Utils.createButton;
import static com.company.Frames.Utils.Utils.createInputPanel;

public class ManageVertexArbitraryGraphRenderingFrame extends Frame {
    private static final ManageVertexArbitraryGraphRenderingFrame instance = new ManageVertexArbitraryGraphRenderingFrame();
    private final DataCarrier<String> data = new DataCarrier<>("");

    private ManageVertexArbitraryGraphRenderingFrame() {
    }

    public static ManageVertexArbitraryGraphRenderingFrame getInstance() {
        return instance;
    }

    @Override
    public void setUp() {
        setSize(300, 150);
        setResizable(false);
        setVisible(false);
        setLayout(new GridLayout(2, 1));
        add(createInputPanel("Vertex name: ", data));
        add(createControlPanel());
    }

    private JButton createAddVertexButton() {
        return createButton("Add", e -> {
            ArbitraryGraphAlgorithmsSelectFrame.getInstance().getRenderer().addVertex(data.getValue());
        });
    }

    private JButton createRemoveVertexButton() {
        return createButton("Remove", e -> {
            ArbitraryGraphAlgorithmsSelectFrame.getInstance().getRenderer().removeVertex(data.getValue());
        });
    }

    private JButton createCancelButton() {
        return createButton("Cancel", new HideMenuActiveListener(this));
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(createAddVertexButton());
        panel.add(createRemoveVertexButton());
        panel.add(createCancelButton());
        return panel;
    }
}
