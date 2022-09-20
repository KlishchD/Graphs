package com.company.Graphs.Implementations;

import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;
import com.company.Graphs.GridPoint;

public class GridGraph extends UnDirectedGraph<GridPoint, Integer> {
    public GridGraph() {
    }

    public GridGraph(int rows, int cols) {
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
}
