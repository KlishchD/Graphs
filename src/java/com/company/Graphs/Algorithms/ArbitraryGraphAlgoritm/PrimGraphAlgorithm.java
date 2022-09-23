package com.company.Graphs.Algorithms.ArbitraryGraphAlgoritm;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Algorithms.Tuple;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.GraphInterface;
import javafx.util.Pair;

import java.util.*;

public class PrimGraphAlgorithm<T, E extends Comparable<E>> implements GraphAlgorithmInterface<List<Pair<T, T>>, T, E> {
    private void addEdgesForPoint(GraphInterface<T, E> graph, T point, PriorityQueue<Tuple<T, E>> edges) {
        try {
            for (T to : graph.getAllDirectlyConnectedVertexes(point)) {
                edges.add(new Tuple<>(graph.getEdgeValue(point, to), point, to));
            }
        } catch (NoSuchVertexException e) {
            e.printStackTrace();
        }
    }

    private void addPoint(GraphInterface<T, E> graph, Set<T> selected, PriorityQueue<Tuple<T, E>> edges, T point) {
        selected.add(point);
        addEdgesForPoint(graph, point, edges);
    }

    @Override
    public List<Pair<T, T>> run(GraphInterface<T, E> graph) {
        PriorityQueue<Tuple<T, E>> edges = new PriorityQueue<>();
        Set<T> selected = new HashSet<>();
        addPoint(graph, selected, edges, graph.getAllVertexesIds().get(0));

        List<Pair<T, T>> result = new ArrayList<>();

        while (selected.size() != graph.getEdgesNumber() && !edges.isEmpty()) {
            Tuple<T, E> edge = edges.poll();
            if (selected.contains(edge.getTo())) continue;
            addPoint(graph, selected, edges, edge.getTo());
            result.add(new Pair<>(edge.getFrom(), edge.getTo()));
        }

        return result;
    }

}
