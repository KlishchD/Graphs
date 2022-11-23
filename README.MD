This project consists of two 2 main parts:
1) Convenient interface to work with graphs and graph algorithms
2) GUI to illustrate work of graph algorithms

# Graphs

## GraphInterface

Interface that defines main features of any graph that this system supports.

Main methods:
1) addEdge, removeEdge - to manage edges
2) addVertex, removeVertex - to manage vertexes
3) runAlgorithm - to run algorithm on a graph
4) updatePointType - to set type of point.

## AbstractGraph
Abstract class that implements common method for all provided implementations.

## DirectedGraph

Class for representing directed graph.

Implements methods that were not implemented in AbstractGraph with directional graph specifications.


## UnDirectedGraph

Class for representing undirected graph.

Implements methods that were not implemented in AbstractGraph with unidirectional graph specifications.

## GridGraph

Class for representing grid NxM as a graph.

Adds methods for easy set up of grid graph.

## GraphAlgorithmInterface

Interface defines main features of any algorithm that this system supports.

The only method that this interface contains is run. It allows running algorithm any number of times on any type of supported graphs.

## ConnectionCheckGraphAlgorithm

Class implements connectivity check algorithm for graph.

## PrimGraphAlgorithm

Class implements Prim's algorithm to find minimum spanning tree.

## ShortestDistanceFromVertexCalculationGraphAlgorithm

Class implements BFS algorithm to find distance from vertex to any other vertex in a graph (considers all graphs unweighted)

## GraphTraversingAlgorithm

Interface defines main features of any traversing algorithm that this system supports.

## BFSTraversingAlgorithm

Class implements BFS algorithm to traverse all vertexes in a graph starting from source vertexes.

## BFSTraversingAlgorithm

Class implements DFS algorithm to traverse all vertexes in a graph starting from source vertexes.

## DijkstraTraversingAlgorithm

Class implements Dijkstra algorithm to traverse all vertexes in a graph starting from source vertexes.

# GUI

## Frame

Abstract class that enforces all required methods for windows in this application.

## MainFrame

Class for rendering window that allows selecting type graph for which algorithms will be rendered.

## AlgorithmSelectFrame

Abstract class defines methods required for classes that are used to render algorithm selection windows.
Allows adding select options during runtime and avoiding hardcoding all the option in each select frame.  

## RenderingFrame

Abstract class defines all the methods required for any class responsible for algorithms rendering windows.
Removes dependency on a specific graph algorithm.

## GridGraphAlgorithmsSelectFrame

Class for rendering window for selecting algorithm to be ran for Grid graph.

## GridGraphAlgorithmRenderingFrame

Class for rendering window for rendering all grid graph algorithms.

## GridResizingFrame

Class for rendering window for managing size of a grid.

## ArbitraryGraphAlgorithmsSelectFrame

Class for rendering window for selecting arbitrary graph algorithm to be rendered.

## ArbitraryGraphAlgorithmRenderFrame

Abstract class that implements all common arbitrary algorithm rendering methods.

## ArbitraryGraphEdgeSelectionAlgorithmRenderFrame

Class for rendering window for rendering arbitrary graph algorithm that return set of selected edges.

## ArbitraryGraphTraversingAlgorithmRenderFrame

Class for rendering window for rendering arbitrary graph traversing algorithms.

## Edge

Class for rendering edge of arbitrary graph.

## ManageEdgeArbitraryGraphRenderingFrame

Class for rendering window for managing edges on a graph.

## Vertex 

Class for rendering vertex of arbitrary graph.

## ManageVertexArbitraryGraphRenderingFrame

Class for rendering window for managing vertexes on a graph. 
