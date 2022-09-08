package com.company.Graphs.Algorithms;

import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionCheckGraphAlgorithm<E, V> implements GraphAlgorithmInterface<Boolean, E, V> {
    private void dfs(E currentVertex, Map<E, Boolean> visited, Graph<E, V> graph) throws NoSuchVertexException {
        visited.put(currentVertex, true);
        for (E nextVertex: graph.getAllDirectlyConnectedVertexes(currentVertex)) {
            if (visited.getOrDefault(nextVertex, false)) continue;
            dfs(nextVertex, visited, graph);
        }
    }

    @Override
    public Boolean run(Graph<E, V> graph) {
        List<E> vertexesIds = graph.getAllVertexesIds();
        if (vertexesIds.size() == 0) return true;

        Map<E, Boolean> visited = new HashMap<>();
        try {
            dfs(vertexesIds.get(0), visited, graph);
        } catch (NoSuchVertexException ignored) {}

        for (E vertex: vertexesIds) {
            if (!visited.getOrDefault(vertex, false)) return false;
        }

        return true;
    }
}
