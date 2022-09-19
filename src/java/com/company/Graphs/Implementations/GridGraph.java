package com.company.Graphs.Implementations;

import com.company.Graphs.Algorithms.GridGraphAlgorithms.GridPoint;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GridGraph extends UnDirectedGraph<GridPoint, Integer> {
    private final Map<GridPoint.GridPointType, Set<GridPoint>> points = new HashMap<>();
    private final Map<GridPoint, GridPoint.GridPointType> types = new HashMap<>();

    public GridGraph(int rows, int cols) {
        for (GridPoint.GridPointType type : GridPoint.GridPointType.values()) {
            points.put(type, new HashSet<>());
        }
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                addVertexAndEdges(row, col);
            }
        }
    }

    private void addVertexAndEdges(int row, int col) {
        try {
            addVertex(new GridPoint(row, col), 0);
            if (row > 0) {
                addEdge(new GridPoint(row, col), new GridPoint(row - 1, col));
            }
            if (col > 0) {
                addEdge(new GridPoint(row, col), new GridPoint(row, col - 1));
            }
        } catch (VertexAlreadyExistsException | EdgeAlreadyExistsException | NoSuchVertexException ignored) {
        }
    }

    /**
     * Adds point of a specified type to a graph
     *
     * @param point point to be added
     * @param type  type of point to be added
     */
    public void updatePointType(GridPoint point, GridPoint.GridPointType type) {
        if (types.containsKey(point) && types.get(point) != type) points.get(types.get(point)).remove(point);
        points.get(type).add(point);
        types.put(point, type);
    }

    /**
     * @param point - point to check
     * @return true if type of point is FREE, otherwise false
     */
    public boolean isFreePoint(GridPoint point) {
        return types.getOrDefault(point, GridPoint.GridPointType.FREE) == GridPoint.GridPointType.FREE;
    }

    public Set<GridPoint> getPointsOfType(GridPoint.GridPointType type) {
        return points.get(type);
    }

    /**
     * @param point point to check
     * @return true if point is of type is not FREE
     */
    public boolean isPointSelected(GridPoint point) {
        return points.get(GridPoint.GridPointType.SOURCE).contains(point) || points.get(GridPoint.GridPointType.BLOCKS).contains(point) || points.get(GridPoint.GridPointType.FINISH).contains(point);
    }

    /**
     * Sets FREE type to all points
     */
    public void resetSelectedPoints() {
        for (GridPoint.GridPointType type : GridPoint.GridPointType.values()) {
            points.get(type).clear();
        }
        types.clear();
    }

}
