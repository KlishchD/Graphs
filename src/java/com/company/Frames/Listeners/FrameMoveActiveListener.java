package com.company.Frames.Listeners;

import com.company.Frames.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener that in case of an event makes 'from' Frame invisible and 'to' frame visible
 */
public class FrameMoveActiveListener implements ActionListener {
    private final Frame from;
    private final Frame to;

    public FrameMoveActiveListener(Frame from, Frame to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        to.setVisible(true);
        from.setVisible(false);
    }
}
