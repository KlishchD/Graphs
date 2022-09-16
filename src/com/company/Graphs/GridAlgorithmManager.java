package com.company.Graphs;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Algorithms.GridGraphAlgorithms.GridPoint;
import com.company.Graphs.Implementations.GridGraph;
import javafx.util.Pair;
import sun.misc.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class is responsible for handling all interactions of View with grid graph and grid graph algorithm
 */
public class GridAlgorithmManager {
    private GraphAlgorithmInterface<Map<GridPoint, GridPoint>, GridPoint, Integer> algorithm;
    private GridGraph graph;

    public GridAlgorithmManager() {
    }

    public GraphAlgorithmInterface<Map<GridPoint, GridPoint>, GridPoint, Integer> getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(GraphAlgorithmInterface<Map<GridPoint, GridPoint>, GridPoint, Integer> algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Replaces current graph with new graph of size rows * cols
     *
     * @param rows number of rows of new graph
     * @param cols number of cols of new graph
     */
    public void resetGraph(int rows, int cols) {
        graph = new GridGraph(rows, cols);
    }

    /**
     * Sets FREE type to all points
     */
    public void resetSelectedPoints() {
        graph.resetSelectedPoints();
    }

    private List<GridPoint> getVisitedPoints(Map<GridPoint, GridPoint> paths) {
        Set<GridPoint> points = paths.keySet();
        return points.stream().filter(graph::isFreePoint).collect(Collectors.toList());
    }

    private Queue<GridPoint> getQueueForRestoringPaths() {
        Queue<GridPoint> queue = new Queue<>();
        graph.getPointsOfType(GridPoint.GridPointType.FINISH).forEach(queue::enqueue);
        return queue;
    }

    private void makeNextIterationOfPathRestoration(Queue<GridPoint> queue, List<Pair<GridPoint, GridPoint>> result, Map<GridPoint, GridPoint> paths) {
        try {
            GridPoint point = queue.dequeue();
            if (point == null) return;
            result.add(new Pair<>(point, paths.get(point)));
            queue.enqueue(paths.getOrDefault(point, null));
        } catch (InterruptedException ignored) {
        }
    }

    private List<Pair<GridPoint, GridPoint>> restorePathFromFinishes(Map<GridPoint, GridPoint> paths) {
        List<Pair<GridPoint, GridPoint>> result = new ArrayList<>();
        Queue<GridPoint> queue = getQueueForRestoringPaths();
        while (!queue.isEmpty()) {
            makeNextIterationOfPathRestoration(queue, result, paths);
        }
        return result;
    }

    /**
     * Runs provided algorithm
     *
     * @return GridAlgorithmResult with all visted points and restored path from sources to finishes
     */
    public GridAlgorithmResult runAlgorithm() {
        Map<GridPoint, GridPoint> paths = algorithm.run(graph);

        List<GridPoint> visited = getVisitedPoints(paths);
        List<Pair<GridPoint, GridPoint>> restoredPath = restorePathFromFinishes(paths);

        return new GridAlgorithmResult(visited, restoredPath);
    }
}
