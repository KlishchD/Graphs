package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.Listeners.ShowAnotherMenuActiveListener;
import com.company.Frames.RenderingFrame;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchEdgeException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;
import com.company.Graphs.GraphInterface;
import com.company.Graphs.Implementations.UnDirectedGraph;
import javafx.util.Pair;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.Frames.Utils.Utils.createButton;

/**
 * Window to render work of a graph algorithm and to manage it
 */
public abstract class ArbitraryGraphAlgorithmRenderFrame extends RenderingFrame<Map<String, String>, String, Integer> {
    protected final Map<String, Vertex> vertexes = new HashMap<>();
    protected final Map<Pair<String, String>, Edge> edges = new HashMap<>();
    protected final ListModel<String> listModel = new DefaultListModel<>();
    protected final String FULL_CLEAR_BUTTON_TEXT = "full clear";
    protected final String CLEAR_BUTTON_TEXT = "clear";
    protected final String RUN_BUTTON_TEXT = "run";
    protected final String BACK_BUTTON_TEXT = "back";
    protected final String MANAGE_VERTEX_BUTTON = "Manage vertexes";
    protected final String MANAGE_EDGE_BUTTON = "Manage edges";
    protected GraphInterface<String, Integer> graph = new UnDirectedGraph<>();

    protected ArbitraryGraphAlgorithmRenderFrame() {
    }

    private JButton createClearGridButton() {
        return createButton(CLEAR_BUTTON_TEXT, e -> resetVertexesVisuals());
    }

    protected abstract void resetVertexesVisuals();

    private JButton createBackButton() {
        return createButton(BACK_BUTTON_TEXT, e -> resetVertexes(), new FrameMoveActiveListener(this, ArbitraryGraphAlgorithmsSelectFrame.getInstance()));
    }

    protected abstract void resetVertexes();

    private JButton createManageVertexButton() {
        return createButton(MANAGE_VERTEX_BUTTON, new ShowAnotherMenuActiveListener(ManageVertexArbitraryGraphRenderingFrame.getInstance()));
    }

    private JButton createManageEdgeButton() {
        return createButton(MANAGE_EDGE_BUTTON, new ShowAnotherMenuActiveListener(MangeEdgeArbitraryGraphRenderingFrame.getInstance()));
    }

    private JButton createRunAlgorithmButton() {
        return createButton(RUN_BUTTON_TEXT, e -> runAlgorithm());
    }


    private JButton createFullClearGridButton() {
        return createButton(FULL_CLEAR_BUTTON_TEXT, e -> resetField());
    }

    protected abstract void resetField();

    protected JPanel createController() {
        JPanel controllers = new JPanel();
        controllers.add(createFullClearGridButton());
        controllers.add(createClearGridButton());
        controllers.add(createRunAlgorithmButton());
        controllers.add(createBackButton());
        controllers.add(createManageVertexButton());
        controllers.add(createManageEdgeButton());
        return controllers;
    }


    private void addEdgeValue(Edge edge) {
        ((DefaultListModel<String>) listModel).addElement(edge.toString());
    }

    private void removeEdgeValue(Edge edge) {
        ((DefaultListModel<String>) listModel).removeElement(edge.toString());
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
            removeEdgeValue(edges.get(edge));
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
            graph.addEdge(from, to, value);
            edges.put(new Pair<>(from, to), edge);
            add(edge);
            addEdgeValue(edge);
        } catch (NoSuchVertexException | EdgeAlreadyExistsException e) {
            e.printStackTrace();
        }
        repaint();
    }


}
