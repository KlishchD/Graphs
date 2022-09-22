package com.company.Frames;

import com.company.Frames.GraphAgorithms.ArbitraryGraphAlgorithms.*;
import com.company.Frames.GraphAgorithms.GridGrpahAlgorithms.GridGraphAlgorithmsSelectFrame;
import com.company.Frames.GraphAgorithms.GridGrpahAlgorithms.GridGraphAlgorithmRenderingFrame;
import com.company.Frames.GraphAgorithms.GridGrpahAlgorithms.GridResizingFrame;
import com.company.Graphs.Algorithms.TraversingAlgorithms.BFSTraversingAlgorithm;
import com.company.Graphs.Algorithms.TraversingAlgorithms.DFSTraversingAlgorithm;
import com.company.Graphs.Algorithms.TraversingAlgorithms.DijkstraTraversingAlgorithm;

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

        ArbitraryGraphAlgorithmsSelectFrame.getInstance().registerAlgorithm("BFS", new BFSTraversingAlgorithm<>(), ArbitraryGraphTraversingAlgorithmRenderFrame.getInstance());
        ArbitraryGraphAlgorithmsSelectFrame.getInstance().registerAlgorithm("DFS", new DFSTraversingAlgorithm<>(), ArbitraryGraphTraversingAlgorithmRenderFrame.getInstance());
        ArbitraryGraphAlgorithmsSelectFrame.getInstance().registerAlgorithm("Dijkstra", new DijkstraTraversingAlgorithm<>(), ArbitraryGraphTraversingAlgorithmRenderFrame.getInstance());

        GridGraphAlgorithmsSelectFrame.getInstance().registerAlgorithm("BFS", new BFSTraversingAlgorithm<>(), GridGraphAlgorithmRenderingFrame.getInstance());
        GridGraphAlgorithmsSelectFrame.getInstance().registerAlgorithm("DFS", new DFSTraversingAlgorithm<>(), GridGraphAlgorithmRenderingFrame.getInstance());

        frames.forEach(Frame::setUp);
    }
}
