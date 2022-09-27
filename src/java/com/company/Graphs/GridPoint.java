package com.company.Graphs;

import java.util.Objects;

public class GridPoint implements Comparable<GridPoint> {
    private final int row;
    private final int col;

    public GridPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridPoint gridPoint = (GridPoint) o;
        return row == gridPoint.row && col == gridPoint.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "GridPoint{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public int compareTo(GridPoint o) {
        if (equals(o)) return 0;
        if (col == o.col) return row - o.row;
        if (row == o.row) return col - o.col;
        return row - o.row;
    }

    public GridPointRelativePosition getRelativePosition(GridPoint point) {
        if (col > point.col) return GridPointRelativePosition.ON_LEFT;
        if (col < point.col) return GridPointRelativePosition.ON_RIGHT;
        if (row < point.row) return GridPointRelativePosition.ON_TOP;
        if (row > point.row) return GridPointRelativePosition.ON_BOTTOM;
        return GridPointRelativePosition.ON_THE_SAME_PLACE;
    }
    public enum GridPointRelativePosition {
        ON_TOP,
        ON_BOTTOM,
        ON_LEFT,
        ON_RIGHT,
        ON_THE_SAME_PLACE
    }
}
