package com.company.Frames.GraphAgorithms;

import com.company.Frames.Frame;
import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.Listeners.GraphGridSelectCellActiveListener;
import com.company.Frames.Listeners.ShowAnotherMenuActiveListener;
import com.company.Graphs.Algorithms.GridGraphAlgorithms.GridPoint;
import com.company.Graphs.GridAlgorithmManager;
import com.company.Graphs.GridAlgorithmResult;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static com.company.Frames.Utils.ButtonUtils.createButton;

/**
 * Window to render work of a graph algorithm and to manage it
 */
public class GridGraphAlgorithmFrame extends Frame {
    private static final GridGraphAlgorithmFrame instance = new GridGraphAlgorithmFrame();
    private static final Map<GridPoint.RelativePosition, String> arrowsForPath = new HashMap<>();

    static {
        arrowsForPath.put(GridPoint.RelativePosition.ON_LEFT, "→");
        arrowsForPath.put(GridPoint.RelativePosition.ON_RIGHT, "←");
        arrowsForPath.put(GridPoint.RelativePosition.ON_TOP, "↑");
        arrowsForPath.put(GridPoint.RelativePosition.ON_BOTTOM, "↓");
    }

    private final Color FREE_GRID_POINT_COLOR = Color.WHITE;
    private final Color VISITED_GRID_POINT = Color.ORANGE;
    private final Color GRID_POINT_PART_OF_RESTORED_PATH_COLOR = Color.CYAN;

    private final int GRID_BUTTON_BORDER_SIZE = 1;

    private final String RESIZE_BUTTON_TEXT = "resize";
    private final String FULL_CLEAR_BUTTON_TEXT = "full clear";
    private final String CLEAR_BUTTON_TEXT = "clear";
    private final String RUN_BUTTON_TEXT = "run";
    private final String BACK_BUTTON_TEXT = "back";
    private final String GRID_BUTTON_DEFAULT_TEXT = "";

    private final Rectangle GRID_SIZE = new Rectangle(5, 40, getWidth() - 10, getHeight() - 80);
    private final Rectangle CONTROLLER_SIZE = new Rectangle(0, 0, this.getWidth(), 35);
    private final Dimension FRAME_SIZE = new Dimension((int) (0.95 * 1600), (int) (0.95 * 900));

    private final int RESTORATION_PATH_ITERATIONS_DELAY = 30;
    private final int ALGORITHM_WORK_ITERATION_RENDER_DELAY = 10;

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
        button.setBackground(FREE_GRID_POINT_COLOR);
        Border border = BorderFactory.createMatteBorder(GRID_BUTTON_BORDER_SIZE, GRID_BUTTON_BORDER_SIZE, GRID_BUTTON_BORDER_SIZE, GRID_BUTTON_BORDER_SIZE, Color.GRAY);
        button.setBorder(border);
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
        field.setBounds(GRID_SIZE);
        field.setBorder(BorderFactory.createLineBorder(FREE_GRID_POINT_COLOR, 2));
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
        this.remove(this.getContentPane().findComponentAt(GRID_SIZE.x, GRID_SIZE.y));
        this.add(createField(rows, cols));
        this.validate();
    }

    private List<JButton> generateSelectTypeControllersButtons() {
        return Arrays.stream(GridPoint.GridPointType.values()).map(type -> createButton(type.getName(), e -> setSelectType(type))).collect(Collectors.toList());
    }

    private JButton createRizeGridButton() {
        return createButton(RESIZE_BUTTON_TEXT, new ShowAnotherMenuActiveListener(GridResizingFrame.getInstance()));
    }

    private JButton createFullClearGridButton() {
        return createButton(FULL_CLEAR_BUTTON_TEXT, e -> clearGrid());
    }

    private JButton createClearGridButton() {
        return createButton(CLEAR_BUTTON_TEXT, e -> resetFreeGridPointsVisualsExceptSelected());
    }

    private JButton createRunAlgorithmButton() {
        return createButton(RUN_BUTTON_TEXT, e -> runAlgorithm());
    }

    private JButton createBackButton() {
        return createButton(BACK_BUTTON_TEXT, e -> resetGrid(), new FrameMoveActiveListener(this, GraphAlgorithmsFrame.getInstance()));
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
        updateButton(buttons.get(point), VISITED_GRID_POINT, GRID_BUTTON_DEFAULT_TEXT);
        sleep(ALGORITHM_WORK_ITERATION_RENDER_DELAY);
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
        if (!algorithmManager.isPointSelected(to))
            updateButton(buttons.get(to), GRID_POINT_PART_OF_RESTORED_PATH_COLOR);
        if (from == null) return;
        GridPoint.RelativePosition position = to.getRelativePosition(from);
        String text = arrowsForPath.getOrDefault(position, GRID_BUTTON_DEFAULT_TEXT);
        updateButton(buttons.get(from), text);
        this.repaint();
        sleep(RESTORATION_PATH_ITERATIONS_DELAY);
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
        resetFreeGridPointsVisualsExceptSelected();
        GridAlgorithmResult result = algorithmManager.runAlgorithm();
        renderWorkOfAlgorithm(result.getVisited());
        renderRestorationOfPath(result.getRestoredPaths());
    }

    private void resetGrid() {
        for (JButton button : buttons.values()) {
            button.setBackground(FREE_GRID_POINT_COLOR);
            button.setText(GRID_BUTTON_DEFAULT_TEXT);
        }
    }

    private void resetFreeGridPointsVisualsExceptSelected() {
        for (Map.Entry<GridPoint, JButton> entry : buttons.entrySet()) {
            entry.getValue().setText(GRID_BUTTON_DEFAULT_TEXT);
            if (algorithmManager.isPointSelected(entry.getKey())) continue;
            entry.getValue().setBackground(FREE_GRID_POINT_COLOR);
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
        controllers.setBounds(CONTROLLER_SIZE);
        return controllers;
    }

    @Override
    public void setUp() {
        this.setSize(FRAME_SIZE);
        this.setLayout(null);
        this.add(createControllers());
        this.setResizable(false);
        this.setVisible(false);
        this.repaint();
    }
}
