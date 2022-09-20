package com.company.Graphs.Algorithms;

import com.company.Graphs.GraphInterface;
import javafx.util.Pair;
import sun.misc.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TraversAlgorithmsResult<T> {
    private final List<T> visited;
    private final List<Pair<T, T>> restoredPaths;

    public <E> TraversAlgorithmsResult(Map<T, T> paths, GraphInterface<T, E> graph) {
        visited = extractVisitedPoints(paths, graph);
        restoredPaths = restorePathFromFinishes(paths, graph);
    }

    public TraversAlgorithmsResult(List<T> visited, List<Pair<T, T>> restoredPaths) {
        this.visited = visited;
        this.restoredPaths = restoredPaths;
    }

    public List<T> getVisited() {
        return visited;
    }

    public List<Pair<T, T>> getRestoredPaths() {
        return restoredPaths;
    }

    private <E> List<T> extractVisitedPoints(Map<T, T> paths, GraphInterface<T, E> graph) {
        Set<T> points = paths.keySet();
        return points.stream().filter(graph::isFreePoint).collect(Collectors.toList());
    }

    private <E> Queue<T> getQueueForRestoringPaths(GraphInterface<T, E> graph) {
        Queue<T> queue = new Queue<>();
        graph.getPointsOfType(GraphInterface.PointType.FINISH).forEach(queue::enqueue);
        return queue;
    }

    private void makeNextIterationOfPathRestoration(Queue<T> queue, List<Pair<T, T>> result, Map<T, T> paths) {
        try {
            T point = queue.dequeue();
            if (point == null) return;
            result.add(new Pair<>(point, paths.get(point)));
            queue.enqueue(paths.getOrDefault(point, null));
        } catch (InterruptedException ignored) {
        }
    }

    private <E> List<Pair<T, T>> restorePathFromFinishes(Map<T, T> paths, GraphInterface<T, E> graph) {
        List<Pair<T, T>> result = new ArrayList<>();
        Queue<T> queue = getQueueForRestoringPaths(graph);
        while (!queue.isEmpty()) {
            makeNextIterationOfPathRestoration(queue, result, paths);
        }
        return result;
    }

}
