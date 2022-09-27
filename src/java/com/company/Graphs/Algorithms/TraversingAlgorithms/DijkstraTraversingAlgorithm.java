package com.company.Graphs.Algorithms.TraversingAlgorithms;

import com.company.Graphs.Algorithms.Tuple;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.GraphInterface;

import java.util.*;

public class DijkstraTraversingAlgorithm<T> implements GraphTraversingAlgorithm<T, Integer> {

    private PriorityQueue<Tuple<T, Integer>> getPriorityQueue(GraphInterface<T, Integer> graph) {
        PriorityQueue<Tuple<T, Integer>> order = new PriorityQueue<>();
        for (T point : graph.getPointsOfType(GraphInterface.PointType.SOURCE)) {
            order.add(new Tuple<>(0, null, point));
        }
        return order;
    }

    private Map<T, Integer> getDistances(GraphInterface<T, Integer> graph) {
        Map<T, Integer> distances = new HashMap<>();
        for (T point : graph.getAllVertexesIds()) {
            distances.put(point, Integer.MAX_VALUE);
        }
        for (T point : graph.getPointsOfType(GraphInterface.PointType.SOURCE)) {
            distances.put(point, 0);
        }
        return distances;
    }

    private void addVertexes(GraphInterface<T, Integer> graph, PriorityQueue<Tuple<T, Integer>> order, Map<T, Integer> distances, T point) throws NoSuchVertexException {
        for (T to : graph.getAllDirectlyConnectedVertexes(point)) {
            if (distances.get(point) + graph.getEdgeValue(point, to) <= distances.get(to)) {
                distances.put(to, distances.get(point) + graph.getEdgeValue(point, to));
                order.add(new Tuple<>(distances.get(to), point, to));
            }
        }
    }

    private void makeNextDijkstraIteration(GraphInterface<T, Integer> graph, PriorityQueue<Tuple<T, Integer>> order, Map<T, Integer> distances, Set<T> visited, Map<T, T> result) throws NoSuchVertexException {
        Tuple<T, Integer> tuple = order.poll();
        if (visited.contains(tuple.getTo())) return;
        result.put(tuple.getTo(), tuple.getFrom());
        addVertexes(graph, order, distances, tuple.getTo());
        visited.add(tuple.getTo());
    }

    private Map<T, T> dijkstra(GraphInterface<T, Integer> graph) throws NoSuchVertexException {
        PriorityQueue<Tuple<T, Integer>> order = getPriorityQueue(graph);
        Map<T, Integer> distances = getDistances(graph);
        Set<T> visited = new LinkedHashSet<>();
        Map<T, T> result = new LinkedHashMap<>();
        while (!order.isEmpty()) {
            makeNextDijkstraIteration(graph, order, distances, visited, result);
        }
        return result;
    }

    @Override
    public Map<T, T> run(GraphInterface<T, Integer> graph) {
        try {
            return dijkstra(graph);
        } catch (NoSuchVertexException e) {
            e.printStackTrace();
            return null;
        }
    }
}
