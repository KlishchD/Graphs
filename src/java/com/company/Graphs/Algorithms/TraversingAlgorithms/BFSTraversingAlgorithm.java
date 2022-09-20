package com.company.Graphs.Algorithms.TraversingAlgorithms;

import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.GraphInterface;
import com.company.Graphs.GraphInterface.PointType;
import sun.misc.Queue;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class for running BFS algorithm from source points to finish points omitting blocks points
 */
public class BFSTraversingAlgorithm<T, E> implements GraphTraversingAlgorithm<T, E> {
    private final Queue<T> queue = new Queue<>();
    private final Set<T> visited = new HashSet<>();

    public BFSTraversingAlgorithm() {
    }

    private void setUpQueue(GraphInterface<T, E> graph) {
        for (T start : graph.getPointsOfType(PointType.SOURCE)) {
            queue.enqueue(start);
        }
    }

    private void addNeighbours(GraphInterface<T, E> graph, Map<T, T> result, T current) throws NoSuchVertexException {
        for (T neighbour : graph.getAllDirectlyConnectedVertexes(current)) {
            if (visited.contains(neighbour) || result.containsKey(neighbour)) continue;
            if (graph.getPointsOfType(PointType.BLOCKS).contains(neighbour)) continue;
            if (graph.getPointsOfType(PointType.SOURCE).contains(neighbour)) continue;
            result.put(neighbour, current);
            if (graph.getPointsOfType(PointType.FINISH).contains(neighbour)) continue;
            queue.enqueue(neighbour);
        }
    }

    private void makeBFSNextIteration(GraphInterface<T, E> graph, Map<T, T> result) {
        try {
            T current = queue.dequeue();
            if (visited.contains(current)) return;
            addNeighbours(graph, result, current);
            visited.add(current);
        } catch (InterruptedException | NoSuchVertexException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs BFS algorithm
     *
     * @param graph on which to run algorithm
     * @return map, where value represents point and key it parent
     */
    @Override
    public Map<T, T> run(GraphInterface<T, E> graph) {
        setUpQueue(graph);
        Map<T, T> result = new LinkedHashMap<>();
        while (!queue.isEmpty()) {
            makeBFSNextIteration(graph, result);
        }
        visited.clear();
        return result;
    }

}
