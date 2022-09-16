package com.company;

import com.company.Graphs.Algorithms.ConnectionCheckGraphAlgorithm;
import com.company.Graphs.Algorithms.ShortestDistanceFromVertexCalculationGraphAlgorithm;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;
import com.company.Graphs.Graph;
import com.company.Graphs.Implementations.UnDirectedGraph;

import java.util.Map;

public class Main {

    public static void main(String[] args) throws NoSuchVertexException, EdgeAlreadyExistsException, VertexAlreadyExistsException {
        Graph<Integer, Integer> graph = new UnDirectedGraph<>();
        graph.addVertex(0, 0);
        graph.addVertex(1, 0);
        graph.addVertex(2, 0);
        graph.addVertex(3, 0);
        graph.addVertex(4, 0);
        graph.addVertex(5, 0);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);
        boolean graphConnected = graph.runAlgorithm(new ConnectionCheckGraphAlgorithm<>());
        Map<Integer, Integer> distances = graph.runAlgorithm(new ShortestDistanceFromVertexCalculationGraphAlgorithm<>(0));
        System.out.println(graphConnected);
        System.out.println(distances);
    }

}
