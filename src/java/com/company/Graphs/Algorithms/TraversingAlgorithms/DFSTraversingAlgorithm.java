package com.company.Graphs.Algorithms.TraversingAlgorithms;

import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.GraphInterface;
import com.company.Graphs.GraphInterface.*;

import java.util.*;


/**
 * Class for running DFS algorithm from source points to finish points omitting blocks points
 * Returns Map, where value represents point and key it parent
 */
public class DFSTraversingAlgorithm<T, E> implements GraphTraversingAlgorithm<T, E> {
    private final Set<T> visited = new HashSet<>();

    public DFSTraversingAlgorithm() {
    }

    private void dfs(T point, GraphInterface<T, E> graph, Map<T, T> result) throws NoSuchVertexException {
        if (visited.contains(point)) return;
        visited.add(point);
        List<T> neighbours = new ArrayList<>(graph.getAllDirectlyConnectedVertexes(point));
        Collections.shuffle(neighbours);
        for (T to : neighbours) {
            if (visited.contains(to)) continue;
            if (graph.getPointsOfType(PointType.BLOCKS).contains(to)) continue;
            result.put(to, point);
            if (graph.getPointsOfType(PointType.FINISH).contains(to)) continue;
            dfs(to, graph, result);
        }
    }

    /**
     * Runs DFS algorithm
     *
     * @param graph on which to run algorithm
     * @return map, where value represents point and key it parent
     */
    @Override
    public Map<T, T> run(GraphInterface<T, E> graph) {
        Map<T, T> result = new LinkedHashMap<>();
        for (T start : graph.getPointsOfType(PointType.SOURCE)) {
            try {
                dfs(start, graph, result);
            } catch (NoSuchVertexException e) {
                e.printStackTrace();
            }
        }

        visited.clear();
        return result;
    }

}
