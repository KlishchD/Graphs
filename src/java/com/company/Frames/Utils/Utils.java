package com.company.Frames.Utils;

import com.company.Frames.Listeners.TextFieldListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Utils {
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

    public static JTextField createInputTextField(DataCarrier<String> input) {
        JTextField textField = new JTextField();
        textField.getDocument().addDocumentListener(new TextFieldListener(input));
        return textField;
    }

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

}
