package com.company.Graphs.Algorithms.GridGraphAlgorithms;

import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Graph;
import sun.misc.Queue;

import java.util.*;

public class BFSWithRestorationMapGridGraphAlgorithm implements GridGraphAlgorithm<Map<GridPoint, GridPoint>, GridPoint, Integer> {
    private final Queue<GridPoint> queue = new Queue<>();
    private final Set<GridPoint> visited = new HashSet<>();
    private Set<GridPoint> starts;
    private Set<GridPoint> finishes;
    private Set<GridPoint> blocks;

    public BFSWithRestorationMapGridGraphAlgorithm() {
    }

    public BFSWithRestorationMapGridGraphAlgorithm(Set<GridPoint> starts, Set<GridPoint> finishes, Set<GridPoint> blocks) {
        this.starts = starts;
        this.finishes = finishes;
        this.blocks = blocks;
    }

    private void setUpQueue() {
        for (GridPoint start : starts) {
            queue.enqueue(start);
        }
    }

    private void addNeighbours(Graph<GridPoint, Integer> graph, Map<GridPoint, GridPoint> result, GridPoint current) throws NoSuchVertexException {
        for (GridPoint neighbour : graph.getAllDirectlyConnectedVertexes(current)) {
            if (visited.contains(neighbour) || blocks.contains(neighbour) || starts.contains(neighbour) || result.containsKey(neighbour))
                continue;
            result.put(neighbour, current);
            if (finishes.contains(neighbour)) continue;
            queue.enqueue(neighbour);
        }
    }

    private void makeBFSNextIteration(Graph<GridPoint, Integer> graph, Map<GridPoint, GridPoint> result) {
        try {
            GridPoint current = queue.dequeue();
            if (visited.contains(current)) return;
            addNeighbours(graph, result, current);
            visited.add(current);
        } catch (InterruptedException | NoSuchVertexException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<GridPoint, GridPoint> run(Graph<GridPoint, Integer> graph) {
        setUpQueue();

        Map<GridPoint, GridPoint> result = new LinkedHashMap<>();
        while (!queue.isEmpty()) {
            makeBFSNextIteration(graph, result);
        }

        visited.clear();
        return result;
    }

    @Override
    public Set<GridPoint> getStarts() {
        return starts;
    }

    @Override
    public void setStarts(Set<GridPoint> strats) {
        this.starts = strats;
    }

    @Override
    public Set<GridPoint> getFinishes() {
        return finishes;
    }

    @Override
    public void setFinishes(Set<GridPoint> finishes) {
        this.finishes = finishes;
    }

    @Override
    public Set<GridPoint> getBlocks() {
        return blocks;
    }

    @Override
    public void setBlocks(Set<GridPoint> blocks) {
        this.blocks = blocks;
    }


}
