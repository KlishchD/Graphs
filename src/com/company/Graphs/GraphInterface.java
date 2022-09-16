package com.company.Graphs;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchEdgeException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;

import java.util.List;

/**
 * @param <T> Type of vertexId
 * @param <E> Type of values in vertex
 */
public interface GraphInterface<T, E> {
    /**
     * Adds an edge between two vertexes
     *
     * @param firstVertex  first vertex
     * @param secondVertex second vertex
     * @throws NoSuchVertexException      if firstVertex or secondVertex doesn't exist
     * @throws EdgeAlreadyExistsException if an edge between firstVertex and secondVertex exists
     */
    void addEdge(T firstVertex, T secondVertex) throws NoSuchVertexException, EdgeAlreadyExistsException;

    /**
     * Removes an edge between two vertexes
     *
     * @param firstVertex  first vertex
     * @param secondVertex second vertex
     * @throws NoSuchVertexException if firstVertex or secondVertex doesn't exist
     * @throws NoSuchEdgeException   if an edge between firstVertex and secondVertex doesn't exists
     */
    void removeEdge(T firstVertex, T secondVertex) throws NoSuchVertexException, NoSuchEdgeException;

    /**
     * Adds a new vertex with a specific id and value
     *
     * @param vertexId id of a new vertex
     * @param value    value of a new vertex
     * @throws VertexAlreadyExistsException if a vertex with a specified id already exists
     */
    void addVertex(T vertexId, E value) throws VertexAlreadyExistsException;

    /**
     * Removes a vertex with a specific id and value.
     * In addition, it deletes all edges to and from a vertex
     *
     * @param vertexId id of a vertex to delete
     * @throws NoSuchVertexException if a vertex with a specified id doesn't exist
     */
    void removeVertex(T vertexId) throws NoSuchVertexException;

    /**
     * @param vertexId id of a vertex
     * @return value of a vertex with a specified id
     * @throws NoSuchVertexException if a vertex with a specified id doesn't exist
     */
    E getVertexValue(T vertexId) throws NoSuchVertexException;

    /**
     * @return list of all ids of vertexes in a graph
     */
    List<T> getAllVertexesIds();

    /**
     * @param vertexId id of a vertex
     * @return list of vertexes connected by an edge with a specified vertex
     * @throws NoSuchVertexException if a specified vertex doesn't exist
     */
    List<T> getAllDirectlyConnectedVertexes(T vertexId) throws NoSuchVertexException;

    /**
     * Connects a specified vertex with all not yet connected vertexes
     *
     * @param vertexId id of a vertex
     * @throws NoSuchVertexException if a specified vertex doesn't exist
     */
    void connectVertexWithNotDirectlyConnectedVertexes(T vertexId) throws NoSuchVertexException, EdgeAlreadyExistsException;

    /**
     * Allows to run a specific algorithm on a grapht
     *
     * @param algorithm algorithm you want to run
     * @param <P>       type of result
     * @return returns result of an execution of the algorithm
     */
    <P> P runAlgorithm(GraphAlgorithmInterface<P, T, E> algorithm);

    /**
     * Checks if graph is connected
     *
     * @return true if graph is connected and false otherwise
     */
    boolean isGraphConnected();

    /**
     * Counts the shortest distance between two vertexes (considers each vertex of the same length)
     *
     * @param firstVertex  id of a first vertex
     * @param secondVertex id of a second vertex
     * @return if there is a path from firstVertex to secondVertex returns distance between them
     * otherwise 2147483647 (2^31 - 1)
     * @throws NoSuchVertexException
     */
    Integer calculateShortestDistanceBetweenVertexes(T firstVertex, T secondVertex) throws NoSuchVertexException;

    /**
     * @return number of vertexes in a graph
     */
    int getVertexNumber();

    /**
     * @return number of edges in a graph
     */
    int getEdgesNumber();
}
