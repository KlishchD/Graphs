package com.company.Frames.Listeners;

import com.company.Frames.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener that in case of event makes invisible specified frame
 */
public class HideMenuActiveListener implements ActionListener {
    private final Frame frame;

    public HideMenuActiveListener(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
    }
}
