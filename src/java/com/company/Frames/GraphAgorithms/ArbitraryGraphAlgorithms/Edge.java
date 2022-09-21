package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import javax.swing.*;
import java.awt.*;

public class Edge extends JPanel {
    private final Vertex from;
    private final Vertex to;
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

    private Rectangle calculatePositionForLine() {
        int x1 = from.getLocationOnScreen().x + from.getWidth() / 2;
        int x2 = to.getLocationOnScreen().x + to.getWidth() / 2;
        int y1 = from.getLocationOnScreen().y;
        int y2 = to.getLocationOnScreen().y;


        double dx = x2 - x1;
        double dy = y2 - y1;

        double len = Math.sqrt(dx * dx + dy * dy);

        dx = dx / len;
        dy = dy / len;

        double r1 = 0.5 * from.getWidth() * from.getHeight() / Math.sqrt(from.getWidth() * from.getWidth() * dy * dy + from.getHeight() * from.getHeight() * dx * dx);
        double rx1 = x1 + r1 * dx;
        double ry1 = y1 + r1 * dy;

        double r2 = 0.5 * to.getWidth() * to.getHeight() / Math.sqrt(to.getWidth() * to.getWidth() * dy * dy + to.getHeight() * to.getHeight() * dx * dx);
        double rx2 = x2 - r2 * dx;
        double ry2 = y2 - r2 * dy;

        return new Rectangle((int) Math.min(rx1, rx2), (int) Math.min(ry1, ry2), (int) Math.abs(rx1 - rx2), Math.max(3, (int) Math.abs(ry1 - ry2)));
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
        graphics.setStroke(new BasicStroke(3));
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(Color.GREEN);
        graphics.drawLine(getLeftX(), getTopY(), getRightX(), getBottomY());
    }
}
