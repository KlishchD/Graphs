package com.company.Frames.DataStuctures;

import com.company.Frames.Listeners.FrameMoveActiveListener;
import com.company.Frames.MainFrame;
import com.company.Frames.Frame;

import javax.swing.*;

import static com.company.Frames.Utils.ButtonUtils.createButton;

public class DataStructuresFrame extends Frame {
    private static final DataStructuresFrame instance = new DataStructuresFrame();

    private DataStructuresFrame() {
    }

    public static DataStructuresFrame getInstance() {
        return instance;
    }

    @Override
    public void setUp() {
        add(createButton("Back", new FrameMoveActiveListener(this, MainFrame.getInstance())));
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(100 * 4, 50 * 4);
        this.setResizable(false);
        this.setVisible(false);
    }
}
