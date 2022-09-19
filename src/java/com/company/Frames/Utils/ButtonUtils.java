package com.company.Frames.Utils;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonUtils {
    /**
     * @param text - text on a button
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
}
