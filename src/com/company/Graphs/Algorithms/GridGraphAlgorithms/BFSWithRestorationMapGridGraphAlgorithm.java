package com.company.Graphs.Algorithms.GridGraphAlgorithms;

import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.GraphInterface;
import com.company.Graphs.Implementations.GridGraph;
import sun.misc.Queue;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class for running BFS algorithm from source points to finish points omitting blocks points
 */
public class BFSWithRestorationMapGridGraphAlgorithm implements com.company.Graphs.Algorithms.GraphAlgorithmInterface<Map<GridPoint, GridPoint>, GridPoint, Integer> {
    private final Queue<GridPoint> queue = new Queue<>();
    private final Set<GridPoint> visited = new HashSet<>();

    public BFSWithRestorationMapGridGraphAlgorithm() {
    }

    private void setUpQueue(GridGraph graph) {
        for (GridPoint start : graph.getPointsOfType(GridPoint.GridPointType.SOURCE)) {
            queue.enqueue(start);
        }
    }

    private void addNeighbours(GridGraph graph, Map<GridPoint, GridPoint> result, GridPoint current) throws NoSuchVertexException {
        for (GridPoint neighbour : graph.getAllDirectlyConnectedVertexes(current)) {
            if (visited.contains(neighbour) || result.containsKey(neighbour)) continue;
            if (graph.getPointsOfType(GridPoint.GridPointType.BLOCKS).contains(neighbour)) continue;
            if (graph.getPointsOfType(GridPoint.GridPointType.SOURCE).contains(neighbour)) continue;
            result.put(neighbour, current);
            if (graph.getPointsOfType(GridPoint.GridPointType.FINISH).contains(neighbour)) continue;
            queue.enqueue(neighbour);
        }
    }

    private void makeBFSNextIteration(GridGraph graph, Map<GridPoint, GridPoint> result) {
        try {
            GridPoint current = queue.dequeue();
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
    public Map<GridPoint, GridPoint> run(GraphInterface<GridPoint, Integer> graph) {
        setUpQueue((GridGraph) graph);
        Map<GridPoint, GridPoint> result = new LinkedHashMap<>();
        while (!queue.isEmpty()) {
            makeBFSNextIteration((GridGraph) graph, result);
        }
        visited.clear();
        return result;
    }

}
