package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import com.company.Graphs.Algorithms.TraversAlgorithmsResult;
import com.company.Graphs.GraphInterface;
import com.company.Graphs.Implementations.UnDirectedGraph;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.Frames.Utils.Utils.createButton;

public class ArbitraryGraphTraversingAlgorithmRenderFrame extends ArbitraryGraphAlgorithmRenderFrame<Map<String, String>> {
    private static final ArbitraryGraphTraversingAlgorithmRenderFrame instance = new ArbitraryGraphTraversingAlgorithmRenderFrame();
    private final int RESTORATION_PATH_ITERATIONS_DELAY = 40;
    private final int ALGORITHM_WORK_ITERATION_RENDER_DELAY = 10;
    private final Dimension FRAME_SIZE = new Dimension((int) (0.95 * 1600), (int) (0.95 * 900));
    private final Rectangle CONTROLLER_SIZE = new Rectangle(0, 0, FRAME_SIZE.width, 35);
    private GraphInterface.PointType selectType = GraphInterface.PointType.SOURCE;

    public static ArbitraryGraphTraversingAlgorithmRenderFrame getInstance() {
        return instance;
    }

    @Override
    public void runAlgorithm() {
        resetVisuals();
        Map<String, String> results = algorithm.run(graph);
        TraversAlgorithmsResult<String> result = new TraversAlgorithmsResult<>(results, graph);
        renderWorkOfAlgorithm(result.getVisited());
        renderRestorationOfPath(result.getRestoredPaths());
    }

    /**
     * Sets type of point in a graph to a selected type by a user
     * Additionally, updates visual state of a point corresponding to a point type
     *
     * @param point point to be updated
     */
    public void updatePointType(String point) {
        updatePointType(point, selectType);
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

    @Override
    protected JPanel createController() {
        JPanel controller = super.createController();
        controller.setBounds(CONTROLLER_SIZE);
        generateSelectTypeControllersButtons().forEach(controller::add);
        return controller;
    }

    @Override
    public void resetVertexes() {
        resetVertexes(vertexes.keySet());
    }

    @Override
    public void resetField() {
        graph = new UnDirectedGraph<>();

        vertexes.values().forEach(this::remove);
        vertexes.clear();

        edges.values().forEach(this::remove);
        edges.clear();

        repaint();
    }

    @Override
    public void resetVisuals() {
        Set<String> nonSelectedVertexes = vertexes.keySet().stream().filter(x -> !graph.isPointSelected(x)).collect(Collectors.toSet());
        resetVertexes(nonSelectedVertexes);
    }

    private List<JButton> generateSelectTypeControllersButtons() {
        return Arrays.stream(GraphInterface.PointType.values()).map(type -> createButton(type.getName(), e -> setSelectType(type))).collect(Collectors.toList());
    }

    public GraphInterface.PointType getSelectType() {
        return selectType;
    }

    public void setSelectType(GraphInterface.PointType selectType) {
        this.selectType = selectType;
    }

    private void updatePointType(String point, GraphInterface.PointType type) {
        vertexes.get(point).setColor(type.getColor());
        graph.updatePointType(point, type);
        repaint();
    }

    private JList<String> createEdgesValuesList() {
        JList<String> list = new JList<>(listModel);
        list.setBounds(FRAME_SIZE.width - 100, 35, 100, 1000);
        return list;
    }

    private void renderNextAlgorithmStep(String point) {
        vertexes.get(point).setColor(VISITED_POINT_COLOR);
        repaint();
        sleep(ALGORITHM_WORK_ITERATION_RENDER_DELAY);
    }

    private void renderWorkOfAlgorithm(List<String> visited) {
        new Thread(() -> {
            synchronized (this) {
                visited.forEach(this::renderNextAlgorithmStep);
            }
        }).start();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void renderNextRestorationOfPathStep(String from, String to) {
        if (!graph.isPointSelected(to)) vertexes.get(to).setColor(POINT_PART_OF_RESTORED_PATH_COLOR);
        repaint();
        sleep(RESTORATION_PATH_ITERATIONS_DELAY);
    }

    private void renderRestorationOfPath(List<Pair<String, String>> path) {
        new Thread(() -> {
            synchronized (this) {
                for (Pair<String, String> points : path) {
                    renderNextRestorationOfPathStep(points.getValue(), points.getKey());
                }
            }
        }).start();
    }

    private void resetVertexes(Set<String> vertexes) {
        for (String vertex : vertexes) {
            updatePointType(vertex, GraphInterface.PointType.FREE);
        }
    }
}
