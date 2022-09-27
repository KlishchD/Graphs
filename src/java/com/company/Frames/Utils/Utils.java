package com.company.Frames.Utils;

import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.Listeners.TextFieldListener;
import com.company.Frames.RenderingFrame;
import com.company.Frames.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Utils {
    protected static final String FULL_CLEAR_BUTTON_TEXT = "full clear";
    protected static final String CLEAR_BUTTON_TEXT = "clear";
    protected static final String RUN_BUTTON_TEXT = "run";
    protected static final String BACK_BUTTON_TEXT = "back";

    /**
     * @param text            - text on a button
     * @param actionListeners listeners to be attached to a button
     * @return new button with specified text and listeners
     */
    public static JButton createButton(String text, ActionListener... actionListeners) {
        JButton button = new JButton(text);
        for (ActionListener actionListener : actionListeners) {
            button.addActionListener(actionListener);
        }
        return button;
    }

    /**
     * Creates text field and attaches DataCarrier class to which all changes in field will be written
     *
     * @param input DataCarrier object to attach
     * @return JTextField object with attached DataCarrier object
     */
    public static JTextField createInputTextField(DataCarrier<String> input) {
        JTextField textField = new JTextField();
        textField.getDocument().addDocumentListener(new TextFieldListener(input));
        return textField;
    }

    /**
     * Creates label for input text field and input text itself
     *
     * @param text  text on label
     * @param input DataCarrier object to attach to input text field
     * @return JPanel containing label and input field
     */
    public static JPanel createInputPanel(String text, DataCarrier<String> input) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(new JLabel(text));
        panel.add(createInputTextField(input));
        return panel;
    }

    public static void updateButton(JButton button, Color background, String text) {
        updateButton(button, background);
        updateButton(button, text);
    }

    public static void updateButton(JButton button, String text) {
        button.setText(text);
        button.repaint();
    }

    public static void updateButton(JButton button, Color background) {
        button.setBackground(background);
        button.repaint();
    }

    public static <P, T, E> JPanel creteBasicRenderingFrameController(RenderingFrame<P, T, E> frame, Frame previousFrame) {
        JPanel controllers = new JPanel();
        controllers.add(createFullClearGridButton(frame));
        controllers.add(createClearGridButton(frame));
        controllers.add(createRunAlgorithmButton(frame));
        controllers.add(createBackButton(frame, previousFrame));
        return controllers;
    }

    private static <P, T, E> JButton createRunAlgorithmButton(RenderingFrame<P, T, E> frame) {
        return createButton(RUN_BUTTON_TEXT, e -> frame.runAlgorithm());
    }

    private static <P, T, E> JButton createFullClearGridButton(RenderingFrame<P, T, E> frame) {
        return createButton(FULL_CLEAR_BUTTON_TEXT, e -> frame.resetField());
    }

    private static <P, T, E> JButton createClearGridButton(RenderingFrame<P, T, E> frame) {
        return createButton(CLEAR_BUTTON_TEXT, e -> frame.resetVisuals());
    }


    private static <P, T, E> JButton createBackButton(RenderingFrame<P, T, E> frame, Frame previousFrame) {
        return createButton(BACK_BUTTON_TEXT, e -> frame.resetVertexes(), new FrameMoveActiveListener(frame, previousFrame));
    }
}
