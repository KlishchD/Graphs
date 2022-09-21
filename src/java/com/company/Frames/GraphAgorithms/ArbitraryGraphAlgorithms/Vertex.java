package com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Vertex extends JPanel {
    private final int width = 200;
    private final int height = 100;
    private final String name;
    private final List<Edge> fromEdges = new ArrayList<>();
    private final List<Edge> toEdges = new ArrayList<>();
    private volatile int xPrevious;
    private volatile int yPrevious;
    private volatile int xMy;
    private volatile int yMy;
    private Color color = Color.BLACK;


    public Vertex(String name) {
        this.name = name;
        setSize(width, height);
        setName(name);
        addMouseListener(new PressingListener());
        addMouseMotionListener(new DraggingListener());
    }

    public void addFromEdge(Edge edge) {
        fromEdges.add(edge);
    }

    public void removeFromEdge(Edge edge) {
        fromEdges.remove(edge);
    }

    public void addToEdge(Edge edge) {
        toEdges.add(edge);
    }

    public void removeToEdge(Edge edge) {
        toEdges.remove(edge);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setStroke(new BasicStroke(2));
        graphics.setColor(color);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawOval(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawString(name, width / 2 - 5 * name.length(), height / 2);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private class PressingListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            ArbitraryGraphTraversingAlgorithmRenderFrame.getInstance().updatePointType(name);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            xPrevious = e.getXOnScreen();
            yPrevious = e.getYOnScreen();
            xMy = getX();
            yMy = getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    private class DraggingListener implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            int deltaX = e.getXOnScreen() - xPrevious;
            int deltaY = e.getYOnScreen() - yPrevious;
            setLocation(xMy + deltaX, yMy + deltaY);
            fromEdges.forEach(Edge::moved);
            toEdges.forEach(Edge::moved);
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
