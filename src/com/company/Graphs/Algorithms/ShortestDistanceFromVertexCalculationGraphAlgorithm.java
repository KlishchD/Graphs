package com.company.Graphs.Algorithms;

import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Graph;

import java.util.*;

public class ShortestDistanceFromVertexCalculationGraphAlgorithm<T, V> implements GraphAlgorithmInterface<Map<T, Integer>, T, V> {
    private final T startVertex;
    public ShortestDistanceFromVertexCalculationGraphAlgorithm(T vertexId) {
        startVertex = vertexId;
    }

    Map<T, Integer> calculateDistances(Graph<T, V> graph) throws NoSuchVertexException {
        Map<T, Integer> distances = new HashMap<>();
        for (T vertex: graph.getAllVertexesIds()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        distances.put(startVertex, 0);

        Queue<T> queue = new LinkedList<>();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for (T nextVertex: graph.getAllDirectlyConnectedVertexes(vertex)) {
                if (distances.get(vertex) + 1 >= distances.get(nextVertex)) continue;
                distances.put(nextVertex, distances.get(vertex) + 1);
                queue.add(nextVertex);
            }
        }
        return distances;
    }

    @Override
    public Map<T, Integer> run(Graph<T, V> graph) {
        try {
            return calculateDistances(graph);
        } catch (NoSuchVertexException ignored) {
            return null;
        }
    }
}
