package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

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
import static com.company.Frames.Utils.Utils.creteBasicRenderingFrameController;

/**
 * Window to render work of a graph algorithm and to manage it
 */
public abstract class ArbitraryGraphAlgorithmRenderFrame<P> extends RenderingFrame<P, String, Integer> {
    protected final Map<String, Vertex> vertexes = new HashMap<>();
    protected final Map<Pair<String, String>, Edge> edges = new HashMap<>();
    protected final ListModel<String> listModel = new DefaultListModel<>();
    protected final String MANAGE_VERTEX_BUTTON = "Manage vertexes";
    protected final String MANAGE_EDGE_BUTTON = "Manage edges";
    protected GraphInterface<String, Integer> graph = new UnDirectedGraph<>();

    protected ArbitraryGraphAlgorithmRenderFrame() {
    }

    protected void addEdge(String from, String to, Integer value) {
        try {
            addEdgeWithoutExceptionHandling(from, to, value);
        } catch (NoSuchVertexException | EdgeAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    protected void removeVertex(String name) {
        try {
            removeVertexWithoutExceptionHandling(name);
        } catch (NoSuchVertexException e) {
            e.printStackTrace();
        }
    }

    protected void removeEdge(String firstVertex, String secondVertex) {
        removeEdge(new Pair<>(firstVertex, secondVertex));
    }

    protected void removeEdge(Pair<String, String> edge) {
        try {
            removeEdgeWithoutExceptionHandling(edge);
        } catch (NoSuchVertexException | NoSuchEdgeException e) {
            e.printStackTrace();
        }
    }

    protected JPanel createController() {
        JPanel controllers = creteBasicRenderingFrameController(this, ArbitraryGraphAlgorithmsSelectFrame.getInstance());
        controllers.add(createManageVertexButton());
        controllers.add(createManageEdgeButton());
        return controllers;
    }

    protected void addVertex(String name) {
        try {
            addVertexWithoutExceptionHandling(name);
        } catch (VertexAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    private void removeEdgesForVertex(String name) {
        Set<Pair<String, String>> edgesToRemove = edges.keySet().stream().filter(pair -> pair.getKey().equals(name) || pair.getValue().equals(name)).collect(Collectors.toSet());
        removeEdges(edgesToRemove);
    }

    private void addEdgeValue(Edge edge) {
        ((DefaultListModel<String>) listModel).addElement(edge.toString());
    }

    private void removeEdgeValue(Edge edge) {
        ((DefaultListModel<String>) listModel).removeElement(edge.toString());
    }

    private void addVertexWithoutExceptionHandling(String name) throws VertexAlreadyExistsException {
        Vertex vertex = new Vertex(name);
        graph.addVertex(vertex.getName(), 0);
        vertexes.put(vertex.getName(), vertex);
        add(vertex);
        repaint();
    }

    private void removeVertexWithoutExceptionHandling(String name) throws NoSuchVertexException {
        removeEdgesForVertex(name);
        remove(vertexes.get(name));
        vertexes.remove(name);
        graph.removeVertex(name);
        repaint();
    }

    private void addEdgeWithoutExceptionHandling(String from, String to, Integer value) throws NoSuchVertexException, EdgeAlreadyExistsException {
        Edge edge = new Edge(vertexes.get(from), vertexes.get(to), value);
        graph.addEdge(from, to, value);
        edges.put(new Pair<>(from, to), edge);
        edges.put(new Pair<>(to, from), edge);
        add(edge);
        addEdgeValue(edge);
        repaint();
    }

    private void removeEdgeWithoutExceptionHandling(Pair<String, String> edge) throws NoSuchVertexException, NoSuchEdgeException {
        vertexes.get(edge.getKey()).removeFromEdge(edges.get(edge));
        vertexes.get(edge.getValue()).removeToEdge(edges.get(edge));
        graph.removeEdge(edge.getKey(), edge.getValue());
        graph.removeEdge(edge.getValue(), edge.getKey());
        remove(edges.get(edge));
        removeEdgeValue(edges.get(edge));
        edges.remove(edge);
        repaint();
    }

    private void removeEdges(Set<Pair<String, String>> edges) {
        edges.forEach(this::removeEdge);
    }

    private JButton createManageVertexButton() {
        return createButton(MANAGE_VERTEX_BUTTON, new ShowAnotherMenuActiveListener(ManageVertexArbitraryGraphRenderingFrame.getInstance()));
    }

    private JButton createManageEdgeButton() {
        return createButton(MANAGE_EDGE_BUTTON, new ShowAnotherMenuActiveListener(ManageEdgeArbitraryGraphRenderingFrame.getInstance()));
    }
}
