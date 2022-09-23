package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import com.company.Graphs.Implementations.UnDirectedGraph;
import javafx.util.Pair;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class ArbitraryGraphEdgeSelectionAlgorithmRenderFrame extends ArbitraryGraphAlgorithmRenderFrame<List<Pair<String, String>>> {
    private static final ArbitraryGraphEdgeSelectionAlgorithmRenderFrame instance = new ArbitraryGraphEdgeSelectionAlgorithmRenderFrame();
    private final Dimension FRAME_SIZE = new Dimension((int) (0.95 * 1600), (int) (0.95 * 900));
    private final Rectangle CONTROLLER_SIZE = new Rectangle(0, 0, FRAME_SIZE.width, 35);
    private final int ALGORITHM_RENDERING_DELAY = 30;

    public static ArbitraryGraphEdgeSelectionAlgorithmRenderFrame getInstance() {
        return instance;
    }

    @Override
    public void resetVisuals() {
        for (Edge edge: edges.values()) {
            edge.resetColor();
        }
        repaint();
    }

    @Override
    public void resetVertexes() {}

    @Override
    public void resetField() {
        graph = new UnDirectedGraph<>();

        vertexes.values().forEach(this::remove);
        vertexes.clear();

        edges.values().forEach(this::remove);
        edges.clear();

        repaint();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void renderSelectedEdges(List<Pair<String, String>> result) {
        new Thread(() -> {
            for (Pair<String, String> edge: result) {
                edges.get(edge).setColor(Color.GREEN);
                repaint();
                sleep(ALGORITHM_RENDERING_DELAY);
            }
        }).start();
    }

    @Override
    public void runAlgorithm() {
        List<Pair<String, String>> result = algorithm.run(graph);
        renderSelectedEdges(result);
    }


    @Override
    protected JPanel createController() {
        JPanel controller = super.createController();
        controller.setBounds(CONTROLLER_SIZE);
        return controller;
    }

    @Override
    public void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_SIZE);
        setLayout(null);
        add(createController());
        add(createEdgesValuesList());
        setResizable(false);
        setVisible(false);
        repaint();
    }

    private JList<String> createEdgesValuesList() {
        JList<String> list = new JList<>(listModel);
        list.setBounds(FRAME_SIZE.width - 100, 35, 100, 1000);
        return list;
    }
}
