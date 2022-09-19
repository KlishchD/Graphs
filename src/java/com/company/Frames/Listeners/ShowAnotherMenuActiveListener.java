package com.company.Frames.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener that in case of event makes visible specified Frame
 */
public class ShowAnotherMenuActiveListener implements ActionListener {
    private final JFrame frame;

    public ShowAnotherMenuActiveListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
    }
}
