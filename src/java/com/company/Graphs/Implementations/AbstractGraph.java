package com.company.Graphs.Implementations;

import com.company.Graphs.Algorithms.ArbitraryGraphAlgoritm.ConnectionCheckGraphAlgorithm;
import com.company.Graphs.Algorithms.ArbitraryGraphAlgoritm.ShortestDistanceFromVertexCalculationGraphAlgorithm;
import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;
import com.company.Graphs.GraphInterface;
import javafx.util.Pair;

import java.util.*;


/**
 * @param <T> Type of vertexId
 * @param <E> Type of values in vertex
 */
public abstract class AbstractGraph<T, E> implements GraphInterface<T, E> {
    protected final Map<T, PointType> types = new HashMap<>();
    private final Map<PointType, Set<T>> points = new HashMap<>();
    protected Map<T, List<T>> connectionsMap = new HashMap<>();
    protected Map<T, E> vertexValuesMap = new HashMap<>();
    protected Map<Pair<T, T>, E> edgesValues = new HashMap();

    public AbstractGraph() {
        for (PointType type : PointType.values()) {
            points.put(type, new HashSet<>());
        }
    }

    /**
     * Adds a new vertex with a specific id and value
     *
     * @param vertexId id of a new vertex
     * @param value    value of a new vertex
     * @throws VertexAlreadyExistsException if a vertex with a specified id already exists
     */
    public void addVertex(T vertexId, E value) throws VertexAlreadyExistsException {
        if (vertexValuesMap.containsKey(vertexId))
            throw new VertexAlreadyExistsException("Vertex " + vertexId + " already exists");
        vertexValuesMap.put(vertexId, value);
        connectionsMap.put(vertexId, new ArrayList<>());
    }

    /**
     * Adds a new vertex with a specific id and null value
     *
     * @param vertexId id of a new vertex
     * @throws VertexAlreadyExistsException if a vertex with a specified id already exists
     */
    @Override
    public void addVertex(T vertexId) throws VertexAlreadyExistsException {
        addVertex(vertexId, null);
    }

    /**
     * Removes a vertex with a specific id and value.
     * In addition, it deletes all edges to and from a vertex
     *
     * @param vertexId id of a vertex to delete
     * @throws NoSuchVertexException if a vertex with a specified id doesn't exist
     */
    public void removeVertex(T vertexId) throws NoSuchVertexException {
        if (!connectionsMap.containsKey(vertexId))
            throw new NoSuchVertexException("There is no such vertex " + vertexId);
        vertexValuesMap.remove(vertexId);
        connectionsMap.remove(vertexId);
        for (List<T> list : connectionsMap.values()) {
            list.remove(vertexId);
        }
    }

    /**
     * @param vertexId id of a vertex
     * @return value of a vertex with a specified id
     * @throws NoSuchVertexException if a vertex with a specified id doesn't exist
     */
    public E getVertexValue(T vertexId) throws NoSuchVertexException {
        if (!connectionsMap.containsKey(vertexId))
            throw new NoSuchVertexException("There is no such vertex " + vertexId);
        return vertexValuesMap.get(vertexId);
    }

    /**
     * Allows to run a specific algorithm on a grapht
     *
     * @param algorithm algorithm you want to run
     * @param <P>       type of result
     * @return returns result of an execution of the algorithm
     */
    @Override
    public <P> P runAlgorithm(GraphAlgorithmInterface<P, T, E> algorithm) {
        return algorithm.run(this);
    }

    /**
     * @return list of all ids of vertexes in a graph
     */
    @Override
    public List<T> getAllVertexesIds() {
        return new ArrayList<>(vertexValuesMap.keySet());
    }

    /**
     * Connects a specified vertex with all not yet connected vertexes
     *
     * @param vertexId id of a vertex
     * @throws NoSuchVertexException if a specified vertex doesn't exist
     */
    @Override
    public void connectVertexWithNotDirectlyConnectedVertexes(T vertexId) throws NoSuchVertexException, EdgeAlreadyExistsException {
        if (!connectionsMap.containsKey(vertexId))
            throw new NoSuchVertexException("There is no such vertex " + vertexId);
        for (Map.Entry<T, List<T>> entry : connectionsMap.entrySet()) {
            if (entry.getKey().equals(vertexId)) continue;
            if (connectionsMap.get(vertexId).contains(entry.getKey())) continue;
            addEdge(vertexId, entry.getKey());
        }
    }

    /**
     * Checks if graph is connected
     *
     * @return true if graph is connected and false otherwise
     */
    @Override
    public boolean isGraphConnected() {
        return runAlgorithm(new ConnectionCheckGraphAlgorithm<>());
    }

    /**
     * Counts the shortest distance between two vertexes (considers each vertex of the same length)
     *
     * @param firstVertex  id of a first vertex
     * @param secondVertex id of a second vertex
     * @return if there is a path from firstVertex to secondVertex returns distance between them
     * otherwise 2147483647 (2^31 - 1)
     * @throws NoSuchVertexException
     */
    @Override
    public Integer calculateShortestDistanceBetweenVertexes(T firstVertex, T secondVertex) throws NoSuchVertexException {
        if (!connectionsMap.containsKey(firstVertex))
            throw new NoSuchVertexException("There is no such vertex " + firstVertex);
        if (!connectionsMap.containsKey(secondVertex))
            throw new NoSuchVertexException("There is no such vertex " + secondVertex);
        return runAlgorithm(new ShortestDistanceFromVertexCalculationGraphAlgorithm<>(firstVertex)).get(secondVertex);
    }

    /**
     * @return number of vertexes in a graph
     */
    @Override
    public int getVertexNumber() {
        return vertexValuesMap.size();
    }

    /**
     * @return number of edges in a graph
     */
    @Override
    public int getEdgesNumber() {
        return connectionsMap.values().stream().mapToInt(List::size).sum();
    }

    /**
     * @param vertexId id of an vertex to check
     * @return true if vertex present and false otherwise
     */
    @Override
    public boolean containsVertex(T vertexId) {
        return vertexValuesMap.containsKey(vertexId);
    }

    /**
     * @param firstVertex  id of vertex where edge starts
     * @param secondVertex id of vertex where edge ends
     * @return true if edge is present and false if edge is not present (additionally false if one of vertexes is not present)
     */
    @Override
    public boolean containsEdge(T firstVertex, T secondVertex) {
        return vertexValuesMap.containsKey(firstVertex) && vertexValuesMap.containsKey(secondVertex) && connectionsMap.get(firstVertex).contains(secondVertex);
    }


    /**
     * Sets specified type for specified point
     *
     * @param point point to be added
     * @param type  type of point to be added
     */
    @Override
    public void updatePointType(T point, PointType type) {
        if (types.containsKey(point) && types.get(point) != type) points.get(types.get(point)).remove(point);
        points.get(type).add(point);
        types.put(point, type);
    }

    /**
     * @param point - point to check
     * @return true if type of point is FREE, otherwise false
     */
    @Override
    public boolean isFreePoint(T point) {
        return types.getOrDefault(point, PointType.FREE) == PointType.FREE;
    }

    /**
     * @param type - type of points to retrieve
     * @return Set of points with specified type
     */
    @Override
    public Set<T> getPointsOfType(PointType type) {
        return points.get(type);
    }

    /**
     * @param point point to check
     * @return true if point is of type is not FREE
     */
    @Override
    public boolean isPointSelected(T point) {
        return points.get(PointType.SOURCE).contains(point) || points.get(PointType.BLOCKS).contains(point) || points.get(PointType.FINISH).contains(point);

    }

    /**
     * Sets FREE type to all points
     */
    public void resetSelectedPoints() {
        for (PointType type : PointType.values()) {
            points.get(type).clear();
        }
        types.clear();
    }

    /**
     * @param firstVertex  id of a first vertex
     * @param secondVertex id of a second vertex
     * @return value of an edge between a specified vertexes
     * @throws NoSuchVertexException if a vertex with a specified id doesn't exist
     */
    @Override
    public E getEdgeValue(T firstVertex, T secondVertex) throws NoSuchVertexException {
        return edgesValues.get(new Pair<>(firstVertex, secondVertex));
    }

}
