package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.Listeners.ShowAnotherMenuActiveListener;
import com.company.Frames.RenderingFrame;
import com.company.Graphs.Algorithms.TraversAlgorithmsResult;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchEdgeException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;
import com.company.Graphs.GraphInterface;
import com.company.Graphs.GraphInterface.PointType;
import com.company.Graphs.Implementations.UnDirectedGraph;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static com.company.Frames.Utils.Utils.createButton;

/**
 * Window to render work of a graph algorithm and to manage it
 */
public class ArbitraryGraphAlgorithmRenderFrame extends RenderingFrame<String, Integer> {
    private static final ArbitraryGraphAlgorithmRenderFrame instance = new ArbitraryGraphAlgorithmRenderFrame();

    private final Color FREE_GRID_POINT_COLOR = Color.WHITE;
    private final Color VISITED_GRID_POINT = Color.ORANGE;
    private final Color GRID_POINT_PART_OF_RESTORED_PATH_COLOR = Color.CYAN;

    private final int GRID_BUTTON_BORDER_SIZE = 1;

    private final String RESIZE_BUTTON_TEXT = "resize";
    private final String FULL_CLEAR_BUTTON_TEXT = "full clear";
    private final String CLEAR_BUTTON_TEXT = "clear";
    private final String RUN_BUTTON_TEXT = "run";
    private final String BACK_BUTTON_TEXT = "back";
    private final String GRID_BUTTON_DEFAULT_TEXT = "";

    private final Dimension FRAME_SIZE = new Dimension((int) (0.95 * 1600), (int) (0.95 * 900));
    private final Rectangle GRID_SIZE = new Rectangle(5, 40, FRAME_SIZE.width - 10, FRAME_SIZE.height - 80);
    private final Rectangle CONTROLLER_SIZE = new Rectangle(0, 0, FRAME_SIZE.width, 35);

    private final int RESTORATION_PATH_ITERATIONS_DELAY = 40;
    private final int ALGORITHM_WORK_ITERATION_RENDER_DELAY = 10;

    private final Map<String, Vertex> vertexes = new HashMap<>();
    private final Map<Pair<String, String>, Edge> edges = new HashMap<>();
    private GraphInterface<String, Integer> graph = new UnDirectedGraph<>();
    private final ListModel<String> listModel = new DefaultListModel<>();

    private PointType selectType = PointType.SOURCE;

    private ArbitraryGraphAlgorithmRenderFrame() {
    }

    public static ArbitraryGraphAlgorithmRenderFrame getInstance() {
        return instance;
    }

    private JButton createManageVertexButton() {
        return createButton("Manage vertexes", new ShowAnotherMenuActiveListener(ManageVertexArbitraryGraphRenderingFrame.getInstance()));
    }

    private JButton createManageEdgeButton() {
        return createButton("Manage edges", new ShowAnotherMenuActiveListener(MangeEdgeArbitraryGraphRenderingFrame.getInstance()));
    }

    public PointType getSelectType() {
        return selectType;
    }

    public void setSelectType(PointType selectType) {
        this.selectType = selectType;
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

    private void updatePointType(String point, PointType type) {
        vertexes.get(point).setColor(type.getColor());
        graph.updatePointType(point, type);
        repaint();
    }

    private List<JButton> generateSelectTypeControllersButtons() {
        return Arrays.stream(PointType.values()).map(type -> createButton(type.getName(), e -> setSelectType(type))).collect(Collectors.toList());
    }

    private JButton createFullClearGridButton() {
        return createButton(FULL_CLEAR_BUTTON_TEXT, e -> resetField());
    }

    private JButton createClearGridButton() {
        return createButton(CLEAR_BUTTON_TEXT, e -> resetVertexesVisualsExceptSelected());
    }

    private JButton createRunAlgorithmButton() {
        return createButton(RUN_BUTTON_TEXT, e -> runAlgorithm());
    }

    private JButton createBackButton() {
        return createButton(BACK_BUTTON_TEXT, e -> resetVertexes(), new FrameMoveActiveListener(this, ArbitraryGraphAlgorithmsSelectFrame.getInstance()));
    }

    private void renderNextAlgorithmStep(String point) {
        vertexes.get(point).setColor(VISITED_GRID_POINT);
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
        if (!graph.isPointSelected(to)) vertexes.get(to).setColor(GRID_POINT_PART_OF_RESTORED_PATH_COLOR);
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

    private void runAlgorithm() {
        resetVertexesVisualsExceptSelected();
        Map<String, String> results = algorithm.run(graph);
        TraversAlgorithmsResult<String> result = new TraversAlgorithmsResult<>(results, graph);
        renderWorkOfAlgorithm(result.getVisited());
        renderRestorationOfPath(result.getRestoredPaths());
    }

    private void resetVertexes() {
        resetVertexes(vertexes.keySet());
    }

    private void resetVertexesVisualsExceptSelected() {
        Set<String> nonSelectedVertexes = vertexes.keySet().stream().filter(x -> !graph.isPointSelected(x)).collect(Collectors.toSet());
        resetVertexes(nonSelectedVertexes);
    }

    private void resetVertexes(Set<String> vertexes) {
        for (String vertex : vertexes) {
            updatePointType(vertex, PointType.FREE);
        }
    }

    private void resetField() {
        graph = new UnDirectedGraph<>();

        vertexes.values().forEach(this::remove);
        vertexes.clear();

        edges.values().forEach(this::remove);
        edges.clear();

        repaint();
    }

    private JPanel createControllers() {
        JPanel controllers = new JPanel();
        generateSelectTypeControllersButtons().forEach(controllers::add);
        controllers.add(createFullClearGridButton());
        controllers.add(createClearGridButton());
        controllers.add(createRunAlgorithmButton());
        controllers.add(createBackButton());
        controllers.add(createManageVertexButton());
        controllers.add(createManageEdgeButton());
        controllers.setBounds(CONTROLLER_SIZE);
        return controllers;
    }

    @Override
    public void setUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_SIZE);
        setLayout(null);
        add(createControllers());
        add(createEdgesValuesList());
        setResizable(false);
        setVisible(false);
        repaint();
    }


    private JList<String> createEdgesValuesList() {
        JList<String> list = new JList<>(listModel);
        list.setSize(100, 1000);
        return list;
    }

    private void addEdgeValue(String from, String to, Integer value) {
        ((DefaultListModel<String>) listModel).addElement("(" + from + ", " + to + ") <->" + value);
    }

    private void removeEdgeValue(String from, String to, Integer value) {
        ((DefaultListModel<String>) listModel).removeElement("(" + from + ", " + to + ") <->" + value);
    }

    protected void addVertex(String name) {
        Vertex vertex = new Vertex(name);
        try {
            graph.addVertex(vertex.getName(), 0);
            vertexes.put(vertex.getName(), vertex);
            add(vertex);
        } catch (VertexAlreadyExistsException e) {
            e.printStackTrace();
        }
        repaint();
    }

    private void removeEdgesForVertex(String name) {
        Set<Pair<String, String>> edgesToRemove = edges.keySet().stream().filter(pair -> pair.getKey().equals(name) || pair.getValue().equals(name)).collect(Collectors.toSet());
        removeEdges(edgesToRemove);
    }

    private void removeEdges(Set<Pair<String, String>> edges) {
        edges.forEach(this::removeEdge);
    }

    protected void removeEdge(String firstVertex, String secondVertex) {
        removeEdge(new Pair<>(firstVertex, secondVertex));
    }

    protected void removeEdge(Pair<String, String> edge) {
        try {
            vertexes.get(edge.getKey()).removeFromEdge(edges.get(edge));
            vertexes.get(edge.getValue()).removeToEdge(edges.get(edge));
            graph.removeEdge(edge.getKey(), edge.getValue());
            remove(edges.get(edge));
            removeEdgeValue(edge.getKey(), edge.getValue(), edges.get(edge).getValue());
            edges.remove(edge);
        } catch (NoSuchVertexException | NoSuchEdgeException e) {
            e.printStackTrace();
        }
        repaint();
    }

    protected void removeVertex(String name) {
        try {
            removeEdgesForVertex(name);
            remove(vertexes.get(name));
            vertexes.remove(name);
            graph.removeVertex(name);
        } catch (NoSuchVertexException e) {
            e.printStackTrace();
        }
        repaint();
    }

    protected void addEdge(String from, String to, Integer value) {
        Edge edge = new Edge(vertexes.get(from), vertexes.get(to), value);
        try {
            graph.addEdge(from, to);
            edges.put(new Pair<>(from, to), edge);
            add(edge);
            addEdgeValue(from, to, value);
        } catch (NoSuchVertexException | EdgeAlreadyExistsException e) {
            e.printStackTrace();
        }
        repaint();
    }


}
