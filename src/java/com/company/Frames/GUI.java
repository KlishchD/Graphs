package com.company.Frames;

import com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms.*;
import com.company.Frames.GraphAgorithms.GridGrpahAlgorithms.GridGraphAlgorithmsSelectFrame;
import com.company.Frames.GraphAgorithms.GridGrpahAlgorithms.GridGraphAlgorithmRenderingFrame;
import com.company.Frames.GraphAgorithms.GridGrpahAlgorithms.GridResizingFrame;

import java.util.Arrays;
import java.util.List;

public class GUI {
    public static void start() {
        List<Frame> frames = Arrays.asList(
                MainFrame.getInstance(),
                GridGraphAlgorithmsSelectFrame.getInstance(),
                GridGraphAlgorithmRenderingFrame.getInstance(),
                GridGraphAlgorithmRenderingFrame.getInstance(),
                GridResizingFrame.getInstance(),
                ArbitraryGraphTraversingAlgorithmRenderFrame.getInstance(),
                ArbitraryGraphAlgorithmsSelectFrame.getInstance(),
                ManageVertexArbitraryGraphRenderingFrame.getInstance(),
                MangeEdgeArbitraryGraphRenderingFrame.getInstance()
        );
        frames.forEach(Frame::setUp);
    }
}
