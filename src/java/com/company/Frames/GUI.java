package com.company.Frames;

import com.company.Frames.GraphAgorithms.GraphAlgorithmsFrame;
import com.company.Frames.GraphAgorithms.GridGraphAlgorithmFrame;
import com.company.Frames.GraphAgorithms.GridResizingFrame;

import java.util.Arrays;
import java.util.List;

public class GUI {
    public static void start() {
        List<Frame> frames = Arrays.asList(
                MainFrame.getInstance(),
                GraphAlgorithmsFrame.getInstance(),
                GridGraphAlgorithmFrame.getInstance(),
                GridGraphAlgorithmFrame.getInstance(),
                GridResizingFrame.getInstance()
        );
        frames.forEach(Frame::setUp);
    }
}
