package com.company.Frames.GraphAgorithms.GridGrpahAlgorithms;

import com.company.Frames.Frame;
import com.company.Frames.Listeners.HideMenuActiveListener;
import com.company.Frames.Utils.DataCarrier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.Frames.Utils.Utils.createButton;
import static com.company.Frames.Utils.Utils.createInputPanel;

public class GridResizingFrame extends Frame {
    private final static GridResizingFrame instance = new GridResizingFrame();

    private final DataCarrier<String> rowsInput = new DataCarrier<>("");
    private final DataCarrier<String> colsInput = new DataCarrier<>("");

    private final String OK_BUTTON_TEXT = "OK";
    private final String CANCEL_BUTTON_TEXT = "Cancel";
    private final String COLS_NUMBER_LABEL_TEXT = "Cols:";
    private final String ROWS_NUMBER_LABEL_TEXT = "Rows:";

    private final Dimension FRAME_SIZE = new Dimension(400, 300);

    private GridResizingFrame() {
    }

    public static GridResizingFrame getInstance() {
        return instance;
    }

    public void setUp() {
        setLayout(new GridLayout(3, 1));

        add(createInputPanel(COLS_NUMBER_LABEL_TEXT, colsInput));
        add(createInputPanel(ROWS_NUMBER_LABEL_TEXT, rowsInput));
        add(createControlPanel());

        setSize(FRAME_SIZE);
        setResizable(false);
        repaint();
    }

    private JButton createOkButton() {
        return createButton(OK_BUTTON_TEXT, new OkButtonClickListener());
    }

    private JButton createCancelButton() {
        return createButton(CANCEL_BUTTON_TEXT, new HideMenuActiveListener(this));
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(createOkButton());
        panel.add(createCancelButton());
        return panel;
    }

    private class OkButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rows = Integer.parseInt(rowsInput.getValue());
            int cols = Integer.parseInt(colsInput.getValue());
            GridGraphAlgorithmRenderingFrame.getInstance().changeNumberOfCells(rows, cols);
        }
    }

}
