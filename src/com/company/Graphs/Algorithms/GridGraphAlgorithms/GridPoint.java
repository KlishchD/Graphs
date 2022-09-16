package com.company.Graphs.Algorithms.GridGraphAlgorithms;

import java.awt.*;
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
    public int compareTo(GridPoint o) {
        if (equals(o)) return 0;
        if (col == o.col) return row - o.row;
        if (row == o.row) return col - o.col;
        return row - o.row;
    }

    public RelativePosition getRelativePosition(GridPoint point) {
        if (col > point.col) return RelativePosition.ON_LEFT;
        if (col < point.col) return RelativePosition.ON_RIGHT;
        if (row < point.row) return RelativePosition.ON_TOP;
        if (row > point.row) return RelativePosition.ON_BOTTOM;
        return RelativePosition.ON_THE_SAME_PLACE;
    }

    public enum RelativePosition {
        ON_TOP,
        ON_BOTTOM,
        ON_LEFT,
        ON_RIGHT,
        ON_THE_SAME_PLACE
    }

    public enum GridPointType {
        SOURCE {
            @Override
            public Color getColor() {
                return Color.GREEN;
            }

            @Override
            public String getName() {
                return "Start";
            }

        }, FINISH {
            @Override
            public Color getColor() {
                return Color.BLUE;
            }

            @Override
            public String getName() {
                return "Finish";
            }

        }, BLOCKS {
            @Override
            public Color getColor() {
                return Color.BLACK;
            }

            @Override
            public String getName() {
                return "Block";
            }
        }, FREE {
            @Override
            public String getName() {
                return "Free";
            }

            @Override
            public Color getColor() {
                return Color.WHITE;
            }
        };

        public Color getColor() {
            return null;
        }

        public String getName() {
            return null;
        }
    }

}
