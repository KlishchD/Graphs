package com.company.Graphs.Algorithms.ArbitraryGraphAlgoritm;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.GraphInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for checking graph's connectivity
 * @param <T> Type of vertexId
 * @param <E> Type of values in vertex
 */
public class ConnectionCheckGraphAlgorithm<T, E> implements GraphAlgorithmInterface<Boolean, T, E> {
    private void dfs(T currentVertex, Map<T, Boolean> visited, GraphInterface<T, E> graph) throws NoSuchVertexException {
        visited.put(currentVertex, true);
        for (T nextVertex: graph.getAllDirectlyConnectedVertexes(currentVertex)) {
            if (visited.getOrDefault(nextVertex, false)) continue;
            dfs(nextVertex, visited, graph);
        }
    }

    private Map<T, Boolean> runDFSForVertex(T vertexId, GraphInterface<T, E> graph) {
        Map<T, Boolean> visited = new HashMap<>();
        try {
            dfs(vertexId, visited, graph);
        } catch (NoSuchVertexException ignored) {}
        return visited;
    }

    private boolean areAllVertexesVisited(Map<T, Boolean> visited, List<T> vertexesIds) {
        for (T vertex: vertexesIds) {
            if (!visited.getOrDefault(vertex, false)) return false;
        }
        return true;
    }

    @Override
    public Boolean run(GraphInterface<T, E> graph) {
        if (graph.getVertexNumber() == 0) return true;
        List<T> vertexesIds = graph.getAllVertexesIds();
        Map<T, Boolean> visited = runDFSForVertex(vertexesIds.get(0), graph);
        return areAllVertexesVisited(visited, vertexesIds);
    }
}
