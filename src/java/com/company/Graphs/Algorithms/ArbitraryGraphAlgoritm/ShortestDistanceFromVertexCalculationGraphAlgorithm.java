package com.company.Graphs.Algorithms.ArbitraryGraphAlgoritm;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.GraphInterface;

import java.util.*;

/**
 * Class for calculating shortest distance from point to all others
 * @param <T> Type of vertexId
 * @param <E> Type of values in vertex
 */
public class ShortestDistanceFromVertexCalculationGraphAlgorithm<T, E> implements GraphAlgorithmInterface<Map<T, Integer>, T, E> {
    private final T startVertex;
    public ShortestDistanceFromVertexCalculationGraphAlgorithm(T vertexId) {
        startVertex = vertexId;
    }

    private Map<T, Integer> initiateDistanceMap(GraphInterface<T, E> graph) {
        Map<T, Integer> distances = new HashMap<>();
        for (T vertex: graph.getAllVertexesIds()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);
        return distances;
    }

    private Map<T, Integer> calculateDistances(GraphInterface<T, E> graph) throws NoSuchVertexException {
        Map<T, Integer> distances = initiateDistanceMap(graph);
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
    public Map<T, Integer> run(GraphInterface<T, E> graph) {
        try {
            return calculateDistances(graph);
        } catch (NoSuchVertexException ignored) {
            return null;
        }
    }
}
