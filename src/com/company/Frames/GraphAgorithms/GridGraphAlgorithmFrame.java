package com.company.Frames.GraphAgorithms;

import com.company.Frames.Frame;
import com.company.Frames.Listeners.GraphGridSelectCellActiveListener;
import com.company.Frames.Listeners.MenuMoveActiveListener;
import com.company.Frames.Listeners.ShowAnotherMenuActiveListener;
import com.company.Graphs.Algorithms.GridGraphAlgorithms.GridPoint;
import com.company.Graphs.GridAlgorithmManager;
import com.company.Graphs.GridAlgorithmResult;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static com.company.Frames.Utils.ButtonUtils.createButton;

public class GridGraphAlgorithmFrame extends Frame {
    private static final GridGraphAlgorithmFrame instance = new GridGraphAlgorithmFrame();
    private static final Map<GridPoint.RelativePosition, String> arrowsForPath = new HashMap<>();

    static {
        arrowsForPath.put(GridPoint.RelativePosition.ON_LEFT, "→");
        arrowsForPath.put(GridPoint.RelativePosition.ON_RIGHT, "←");
        arrowsForPath.put(GridPoint.RelativePosition.ON_TOP, "↑");
        arrowsForPath.put(GridPoint.RelativePosition.ON_BOTTOM, "↓");
    }

    private final Map<GridPoint, JButton> buttons = new HashMap<>();
    private final GridAlgorithmManager algorithmManager = new GridAlgorithmManager();
    private GridPoint.GridPointType selectType = GridPoint.GridPointType.SOURCE;

    private GridGraphAlgorithmFrame() {
    }

    public static GridGraphAlgorithmFrame getInstance() {
        return instance;
    }

    public GridAlgorithmManager getAlgorithmManager() {
        return algorithmManager;
    }

    public GridPoint.GridPointType getSelectType() {
        return selectType;
    }

    public void setSelectType(GridPoint.GridPointType selectType) {
        this.selectType = selectType;
    }

    /**
     * Sets type of point in a graph to a selected type by a user
     * Additionally, updates visual state of a point corresponding to a point type
     *
     * @param point point to be updated
     */
    public void updatePointType(GridPoint point) {
        buttons.get(point).setOpaque(true);
        buttons.get(point).setBackground(selectType.getColor());

        this.getContentPane().validate();

        algorithmManager.updatePointType(point, selectType);
    }

    private JButton createGridButton(int row, int col) {
        JButton button = new JButton(" ");
        button.setOpaque(true);
        button.addActionListener(new GraphGridSelectCellActiveListener(row, col));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        buttons.put(new GridPoint(row, col), button);
        return button;
    }

    private List<JButton> createGridButtons(int rows, int cols) {
        buttons.clear();
        List<JButton> result = new ArrayList<>();
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                result.add(createGridButton(row, col));
            }
        }
        return result;
    }

    private JPanel createField(int rows, int cols) {
        algorithmManager.resetGraph(rows, cols);
        JPanel field = new JPanel();
        field.setLayout(new GridLayout(rows, cols));
        createGridButtons(rows, cols).forEach(field::add);
        field.setBounds(5, 40, this.getWidth() - 10, this.getHeight() - 80);
        field.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return field;
    }

    /**
     * Creates a new grid with specified size.
     * Additionally, all points type will be reset.
     *
     * @param rows new number of rows in a grid
     * @param cols new number of cols in a grid
     */
    public void changeNumberOfCells(int rows, int cols) {
        this.remove(this.getContentPane().findComponentAt(5, 40));
        this.add(createField(rows, cols));
        this.validate();
    }

    private List<JButton> generateSelectTypeControllersButtons() {
        return Arrays.stream(GridPoint.GridPointType.values()).map(type -> createButton(type.getName(), e -> setSelectType(type))).collect(Collectors.toList());
    }

    private JButton createRizeGridButton() {
        return createButton("resize", new ShowAnotherMenuActiveListener(GridResizingFrame.getInstance()));
    }

    private JButton createFullClearGridButton() {
        return createButton("full clear", e -> clearGrid());
    }

    private JButton createClearGridButton() {
        return createButton("clear", e -> resetGridColorsExceptSelected());
    }


    private JButton createRunAlgorithmButton() {
        return createButton("run", e -> runAlgorithm());
    }

    private JButton createBackButton() {
        return createButton("back", e -> resetGrid(), new MenuMoveActiveListener(this, GraphAlgorithmsFrame.getInstance()));
    }

    private void updateButton(JButton button, Color background, String text) {
        updateButton(button, background);
        updateButton(button, text);
    }

    private void updateButton(JButton button, String text) {
        button.setText(text);
        this.repaint();
    }

    private void updateButton(JButton button, Color background) {
        button.setBackground(background);
        this.repaint();
    }

    private void renderNextAlgorithmStep(GridPoint point) {
        updateButton(buttons.get(point), Color.ORANGE, "");
        sleep(10);
    }

    private void renderWorkOfAlgorithm(List<GridPoint> visited) {
        new Thread(() -> {
            synchronized (this) {
                visited.forEach(this::renderNextAlgorithmStep);
            }
        }).start();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void renderNextRestorationOfPathStep(GridPoint from, GridPoint to) {
        if (!algorithmManager.isPointSelected(to)) updateButton(buttons.get(to), Color.CYAN);
        if (from == null) return;
        GridPoint.RelativePosition position = to.getRelativePosition(from);
        String text = arrowsForPath.getOrDefault(position, "");
        updateButton(buttons.get(from), text);
        this.repaint();
        sleep(30);
    }

    private void renderRestorationOfPath(List<Pair<GridPoint, GridPoint>> path) {
        new Thread(() -> {
            synchronized (this) {
                for (Pair<GridPoint, GridPoint> points : path) {
                    renderNextRestorationOfPathStep(points.getValue(), points.getKey());
                }
            }
        }).start();
    }

    private void runAlgorithm() {
        resetGridColorsExceptSelected();
        GridAlgorithmResult result = algorithmManager.runAlgorithm();
        renderWorkOfAlgorithm(result.getVisited());
        renderRestorationOfPath(result.getRestoredPaths());
    }

    private void resetGrid() {
        for (JButton button : buttons.values()) {
            button.setBackground(Color.WHITE);
            button.setText(" ");
        }
    }

    private void resetGridColorsExceptSelected() {
        for (Map.Entry<GridPoint, JButton> entry : buttons.entrySet()) {
            entry.getValue().setText(" ");
            if (algorithmManager.isPointSelected(entry.getKey())) continue;
            entry.getValue().setBackground(Color.WHITE);
        }
    }

    public void clearGrid() {
        algorithmManager.resetSelectedPoints();
        resetGrid();
    }

    private JPanel createControllers() {
        JPanel controllers = new JPanel();
        generateSelectTypeControllersButtons().forEach(controllers::add);
        controllers.add(createRizeGridButton());
        controllers.add(createFullClearGridButton());
        controllers.add(createClearGridButton());
        controllers.add(createRunAlgorithmButton());
        controllers.add(createBackButton());
        controllers.setBounds(0, 0, this.getWidth(), 35);
        return controllers;
    }

    @Override
    public void setUp() {
        this.setSize((int) (0.95 * 1600), (int) (0.95 * 900));
        this.setLayout(null);
        this.add(createControllers());
        this.setResizable(false);
        this.setVisible(false);
        this.repaint();
    }
}
