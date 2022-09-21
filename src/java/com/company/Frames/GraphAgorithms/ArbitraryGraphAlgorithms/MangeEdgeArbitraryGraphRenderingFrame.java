package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import com.company.Frames.Frame;
import com.company.Frames.Listeners.HideMenuActiveListener;
import com.company.Frames.Utils.DataCarrier;

import javax.swing.*;
import java.awt.*;

import static com.company.Frames.Utils.Utils.createButton;
import static com.company.Frames.Utils.Utils.createInputPanel;

public class MangeEdgeArbitraryGraphRenderingFrame extends Frame {
    private static final MangeEdgeArbitraryGraphRenderingFrame instance = new MangeEdgeArbitraryGraphRenderingFrame();
    private final DataCarrier<String> firstVertex = new DataCarrier<>("");
    private final DataCarrier<String> secondVertex = new DataCarrier<>("");
    private final DataCarrier<String> value = new DataCarrier<>("");

    private MangeEdgeArbitraryGraphRenderingFrame() {
    }

    public static MangeEdgeArbitraryGraphRenderingFrame getInstance() {
        return instance;
    }

    private JButton createAddEdgeButton() {
        return createButton("Add", e -> {
            ArbitraryGraphAlgorithmRenderFrame.getInstance().addEdge(firstVertex.getValue(), secondVertex.getValue(), Integer.valueOf(value.getValue()));
        });
    }

    private JButton createRemoveEdgeButton() {
        return createButton("Remove", e -> {
            ArbitraryGraphAlgorithmRenderFrame.getInstance().removeEdge(firstVertex.getValue(), secondVertex.getValue());
        });
    }

    private JButton createCancelButton() {
        return createButton("Cancel", new HideMenuActiveListener(this));
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(createAddEdgeButton());
        panel.add(createRemoveEdgeButton());
        panel.add(createCancelButton());
        return panel;
    }

    @Override
    public void setUp() {
        setSize(300, 150);
        setResizable(false);
        setVisible(false);
        setLayout(new GridLayout(4, 1));
        add(createInputPanel("First vertex name: ", firstVertex));
        add(createInputPanel("Second vertex name: ", secondVertex));
        add(createInputPanel("Vertex value: ", value));
        add(createControlPanel());
    }
}
