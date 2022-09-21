package com.company.Graphs.Algorithms.TraversingAlgorithms;

import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.GraphInterface;

import java.util.*;

public class DijkstraTraversingAlgorithm<T> implements GraphTraversingAlgorithm<T, Integer> {

    private PriorityQueue<Tuple> getPriorityQueue(GraphInterface<T, Integer> graph) {
        PriorityQueue<Tuple> order = new PriorityQueue<>();
        for (T point : graph.getPointsOfType(GraphInterface.PointType.SOURCE)) {
            order.add(new Tuple(0, null, point));
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

    private Map<T, T> dijkstra(GraphInterface<T, Integer> graph) throws NoSuchVertexException {
        PriorityQueue<Tuple> order = getPriorityQueue(graph);
        Map<T, Integer> distances = getDistances(graph);
        Set<T> visited = new LinkedHashSet<>();
        Map<T, T> result = new LinkedHashMap<>();
        while (!order.isEmpty()) {
            Tuple tuple = order.poll();
            if (visited.contains(tuple.getTo())) continue;
            T current = tuple.getTo();
            result.put(tuple.getTo(), tuple.getFrom());
            for (T to : graph.getAllDirectlyConnectedVertexes(current)) {
                if (distances.get(current) + graph.getEdgeValue(current, to) <= distances.get(to)) {
                    distances.put(to, distances.get(current) + graph.getEdgeValue(current, to));
                    order.add(new Tuple(distances.get(to), current, to));
                }
            }
            visited.add(current);
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

    private class Tuple implements Comparable<Tuple> {
        private final Integer value;
        private final T from;
        private final T to;

        public Tuple(Integer value, T from, T to) {
            this.value = value;
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Tuple o) {
            return Integer.compare(value, o.value);
        }

        public Integer getValue() {
            return value;
        }

        public T getFrom() {
            return from;
        }

        public T getTo() {
            return to;
        }
    }
}
