package com.company.Frames.Utils;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonUtils {
    public static JButton createButton(String name, ActionListener... actionListeners) {
        JButton button = new JButton(name);
        for (ActionListener actionListener : actionListeners) {
            button.addActionListener(actionListener);
        }
        return button;
    }
}
