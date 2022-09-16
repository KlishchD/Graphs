package com.company.Frames.GraphAgorithms;

import com.company.Frames.Frame;
import com.company.Frames.Listeners.HideMenuActiveListener;
import com.company.Frames.Listeners.TextFieldListener;
import com.company.Frames.Utils.DataCarrier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.Frames.Utils.ButtonUtils.createButton;

public class GridResizingFrame extends Frame {
    private final static GridResizingFrame instance = new GridResizingFrame();
    private final DataCarrier<String> rowsInput = new DataCarrier<>("");
    private final DataCarrier<String> colsInput = new DataCarrier<>("");

    private GridResizingFrame() {

    }

    public static GridResizingFrame getInstance() {
        return instance;
    }

    private JTextField createInputTextField(DataCarrier<String> input) {
        JTextField textField = new JTextField();
        textField.getDocument().addDocumentListener(new TextFieldListener(input));
        return textField;
    }

    private JPanel createInputPanel(String text, DataCarrier<String> input) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(new JLabel(text));
        panel.add(createInputTextField(input));
        return panel;
    }

    private JButton createOkButton() {
        return createButton("Ok", new OkButtonClickListener());
    }

    private JButton createCancelButton() {
        return createButton("Cancel", new HideMenuActiveListener(this));
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(createOkButton());
        panel.add(createCancelButton());
        return panel;
    }

    public void setUp() {
        setLayout(new GridLayout(3, 1));

        add(createInputPanel("Cols:", colsInput));
        add(createInputPanel("Rows:", rowsInput));
        add(createControlPanel());

        setSize(400, 300);
        setResizable(false);
        repaint();
    }

    private class OkButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rows = Integer.parseInt(rowsInput.getValue());
            int cols = Integer.parseInt(colsInput.getValue());
            GridGraphAlgorithmFrame.getInstance().changeNumberOfCells(rows, cols);
        }
    }

}
