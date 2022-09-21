package com.company.Graphs;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchEdgeException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;

import java.awt.*;
import java.util.List;
import java.util.Set;

/**
 * @param <T> Type of vertexId
 * @param <E> Type of values in vertex
 */
public interface GraphInterface<T, E> {
    /**
     * Adds an edge between two vertexes with null value
     *
     * @param firstVertex  first vertex
     * @param secondVertex second vertex
     * @throws NoSuchVertexException      if firstVertex or secondVertex doesn't exist
     * @throws EdgeAlreadyExistsException if an edge between firstVertex and secondVertex exists
     */
    void addEdge(T firstVertex, T secondVertex) throws NoSuchVertexException, EdgeAlreadyExistsException;

    /**
     * Adds an edge between two vertexes with specified value
     *
     * @param firstVertex  first vertex
     * @param secondVertex second vertex
     * @param value        value of an edge
     * @throws NoSuchVertexException      if firstVertex or secondVertex doesn't exist
     * @throws EdgeAlreadyExistsException if an edge between firstVertex and secondVertex exists
     */
    void addEdge(T firstVertex, T secondVertex, E value) throws NoSuchVertexException, EdgeAlreadyExistsException;

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
     * Adds a new vertex with a specific id and null value
     *
     * @param vertexId id of a new vertex
     * @throws VertexAlreadyExistsException if a vertex with a specified id already exists
     */
    void addVertex(T vertexId) throws VertexAlreadyExistsException;

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
         * @param firstVertex  id of a first vertex
         * @param secondVertex id of a second vertex
         * @return value of an edge between a specified vertexes
         * @throws NoSuchVertexException if a vertex with a specified id doesn't exist
         */
        E getEdgeValue(T firstVertex, T secondVertex) throws NoSuchVertexException;

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

    /**
     * @param vertexId id of an vertex to check
     * @return true if vertex present and false otherwise
     */
    boolean containsVertex(T vertexId);

    /**
     * @param firstVertex  id of vertex where edge starts
     * @param secondVertex id of vertex where edge ends
     * @return true if edge is present and false if edge is not present (additionally false if one of vertexes is not present)
     */
    boolean containsEdge(T firstVertex, T secondVertex);

    /**
     * Sets specified type for specified point
     *
     * @param point point to be added
     * @param type  type of point to be added
     */
    void updatePointType(T point, PointType type);

    /**
     * @param point - point to check
     * @return true if type of point is FREE, otherwise false
     */
    boolean isFreePoint(T point);

    /**
     * @param type - type of points to retrieve
     * @return Set of points with specified type
     */
    Set<T> getPointsOfType(PointType type);

    /**
     * @param point point to check
     * @return true if point is of type is not FREE
     */
    boolean isPointSelected(T point);

    /**
     * Sets FREE type to all points
     */
    void resetSelectedPoints();

    enum PointType {
        SOURCE {
            @Override
            public Color getColor() {
                return Color.GREEN;
            }

            @Override
            public String getName() {
                return "Start";
            }

        }, FINISH {
            @Override
            public Color getColor() {
                return Color.BLUE;
            }

            @Override
            public String getName() {
                return "Finish";
            }

        }, BLOCKS {
            @Override
            public Color getColor() {
                return Color.BLACK;
            }

            @Override
            public String getName() {
                return "Block";
            }
        }, FREE {
            @Override
            public String getName() {
                return "Free";
            }

            @Override
            public Color getColor() {
                return Color.DARK_GRAY;
            }
        };

        public Color getColor() {
            return null;
        }

        public String getName() {
            return null;
        }
    }
}
