package com.company.Graphs;

import com.company.Graphs.Algorithms.GridGraphAlgorithms.GridPoint;
import javafx.util.Pair;

import java.util.List;

public class GridAlgorithmResult {
    private final List<GridPoint> visited;
    private final List<Pair<GridPoint, GridPoint>> restoredPaths;

    public GridAlgorithmResult(List<GridPoint> visited, List<Pair<GridPoint, GridPoint>> restoredPaths) {
        this.visited = visited;
        this.restoredPaths = restoredPaths;
    }

    public List<GridPoint> getVisited() {
        return visited;
    }

    public List<Pair<GridPoint, GridPoint>> getRestoredPaths() {
        return restoredPaths;
    }
}
