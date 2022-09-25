package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import javax.swing.*;
import java.awt.*;

public class Edge extends JPanel {
    private static final int STROKE_WIDTH = 2;
    private final Color DEFAULT_EDGE_COLOR = Color.BLACK;
    private final Vertex from;
    private final Vertex to;
    private Color color = DEFAULT_EDGE_COLOR;
    private int value;

    public Edge(Vertex from, Vertex to) {
        this(from, to, 0);
    }

    public Edge(Vertex from, Vertex to, int value) {
        this.from = from;
        this.from.addFromEdge(this);
        this.to = to;
        this.to.addToEdge(this);
        this.value = value;
        moved();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void resetColor() {
        this.color = DEFAULT_EDGE_COLOR;
    }

    private double calculateRadius(double dx, double dy, Vertex vertex) {
        double top = 0.5 * vertex.getWidth() * vertex.getHeight();
        double bottom = Math.sqrt(vertex.getWidth() * vertex.getWidth() * dy * dy + vertex.getHeight() * vertex.getHeight() * dx * dx);
        return top / bottom;
    }

    private Rectangle calculatePositionForLine() {
        double x1 = from.getX() + from.getWidth() / 2.0;
        double x2 = to.getX() + to.getWidth() / 2.0;
        double y1 = from.getY() + from.getHeight() / 2.0;
        double y2 = to.getY() + to.getHeight() / 2.0;


        double dx = x2 - x1;
        double dy = y2 - y1;

        double len = Math.sqrt(dx * dx + dy * dy);

        dx = dx / len;
        dy = dy / len;

        double r1 = calculateRadius(dx, dy, from);
        double rx1 = x1 + r1 * dx;
        double ry1 = y1 + r1 * dy;

        double r2 = calculateRadius(dx, dy, to);
        double rx2 = x2 - r2 * dx;
        double ry2 = y2 - r2 * dy;

        return new Rectangle((int) Math.min(rx1, rx2), (int) Math.min(ry1, ry2), (int) Math.abs(rx1 - rx2), Math.max(STROKE_WIDTH, (int) Math.abs(ry1 - ry2)));
    }

    public void moved() {
        setBounds(calculatePositionForLine());
        repaint();
    }

    private int getLeftX() {
        return from.getLocationOnScreen().x < to.getLocationOnScreen().x ? 0 : getWidth();
    }

    private int getRightX() {
        return from.getLocationOnScreen().x > to.getLocationOnScreen().x ? 0 : getWidth();
    }

    private int getTopY() {
        return from.getLocationOnScreen().y < to.getLocationOnScreen().y ? 0 : getHeight();
    }

    private int getBottomY() {
        return from.getLocationOnScreen().y > to.getLocationOnScreen().y ? 0 : getHeight();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setStroke(new BasicStroke(STROKE_WIDTH));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(color);
        graphics.drawLine(getLeftX(), getTopY(), getRightX(), getBottomY());
    }

    @Override
    public String toString() {
        return "(" + from + ", " + to + ") <-> " + value;
    }
}
