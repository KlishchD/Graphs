package com.company.Frames;

import com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms.MangeEdgeArbitraryGraphRenderingFrame;
import com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms.ManageVertexArbitraryGraphRenderingFrame;
import com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms.ArbitraryGraphAlgorithmRenderFrame;
import com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms.ArbitraryGraphAlgorithmsSelectFrame;
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
                ArbitraryGraphAlgorithmRenderFrame.getInstance(),
                ArbitraryGraphAlgorithmsSelectFrame.getInstance(),
                ManageVertexArbitraryGraphRenderingFrame.getInstance(),
                MangeEdgeArbitraryGraphRenderingFrame.getInstance()
        );
        frames.forEach(Frame::setUp);
    }
}
