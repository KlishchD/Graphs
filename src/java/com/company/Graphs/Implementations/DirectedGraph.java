package com.company.Graphs.Implementations;

import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchEdgeException;
import com.company.Graphs.Errors.NoSuchVertexException;
import javafx.util.Pair;

import java.util.List;

/**
 * @param <T> Type of vertexId
 * @param <E> Type of values in vertex
 */
public class DirectedGraph<T, E> extends AbstractGraph<T, E> {
    /**
     * Adds an edge between two vertexes
     *
     * @param firstVertex  first vertex
     * @param secondVertex second vertex
     * @throws NoSuchVertexException      if firstVertex or secondVertex doesn't exist
     * @throws EdgeAlreadyExistsException if an edge between firstVertex and secondVertex exists
     */
    @Override
    public void addEdge(T firstVertex, T secondVertex) throws NoSuchVertexException, EdgeAlreadyExistsException {
        addEdge(firstVertex, secondVertex, null);
    }
    /**
     * Adds an edge between two vertexes with specified value
     *
     * @param firstVertex  first vertex
     * @param secondVertex second vertex
     * @param value        value of an edge
     * @throws NoSuchVertexException      if firstVertex or secondVertex doesn't exist
     * @throws EdgeAlreadyExistsException if an edge between firstVertex and secondVertex exists
     */
    @Override
    public void addEdge(T firstVertex, T secondVertex, E value) throws NoSuchVertexException, EdgeAlreadyExistsException {
        if (!connectionsMap.containsKey(firstVertex))
            throw new NoSuchVertexException("There is no such vertex " + firstVertex);
        if (!connectionsMap.containsKey(secondVertex))
            throw new NoSuchVertexException("There is no such vertex " + secondVertex);
        if (connectionsMap.get(firstVertex).contains(secondVertex))
            throw new EdgeAlreadyExistsException("Edge between " + firstVertex + " and " + secondVertex + " already exists");
        connectionsMap.get(firstVertex).add(secondVertex);
        edgesValues.put(new Pair<>(firstVertex, secondVertex), value);
    }

    /**
     * Removes an edge between two vertexes
     *
     * @param firstVertex  first vertex
     * @param secondVertex second vertex
     * @throws NoSuchVertexException if firstVertex or secondVertex doesn't exist
     * @throws NoSuchEdgeException   if an edge between firstVertex and secondVertex doesn't exists
     */
    @Override
    public void removeEdge(T firstVertex, T secondVertex) throws NoSuchVertexException, NoSuchEdgeException {
        if (!connectionsMap.containsKey(firstVertex))
            throw new NoSuchVertexException("There is no such vertex " + firstVertex);
        if (!connectionsMap.containsKey(secondVertex))
            throw new NoSuchVertexException("There is no such vertex " + secondVertex);
        if (!connectionsMap.get(firstVertex).contains(secondVertex))
            throw new NoSuchEdgeException("There is no such edge between " + firstVertex + " and " + secondVertex);
        connectionsMap.get(firstVertex).remove(secondVertex);
        edgesValues.remove(new Pair<>(firstVertex, secondVertex));
    }

    /**
     * @param vertexId id of a vertex
     * @return list of vertexes connected by an edge with a specified vertex
     * @throws NoSuchVertexException if a specified vertex doesn't exist
     */
    @Override
    public List<T> getAllDirectlyConnectedVertexes(T vertexId) throws NoSuchVertexException {
        if (!connectionsMap.containsKey(vertexId))
            throw new NoSuchVertexException("There is no such vertex " + vertexId);
        return connectionsMap.get(vertexId);
    }

    /**
     * Removes all vertexes pointed to a specified vertex
     *
     * @param vertexId id of a vertex
     * @throws NoSuchVertexException if specified vertex doesn't exist
     */
    public void removeAllEdgesPointedToVertex(T vertexId) throws NoSuchVertexException {
        if (!connectionsMap.containsKey(vertexId))
            throw new NoSuchVertexException("There is no such vertex " + vertexId);
        for (List<T> list : connectionsMap.values()) {
            list.remove(vertexId);
        }
    }

    /**
     * Removes all vertexes pointed from a specified vertex
     *
     * @param vertexId id of a vertex
     * @throws NoSuchVertexException if specified vertex doesn't exist
     */
    public void removeAllEdgesPointedFromVertex(T vertexId) throws NoSuchVertexException {
        if (!connectionsMap.containsKey(vertexId))
            throw new NoSuchVertexException("There is no such vertex " + vertexId);
        connectionsMap.get(vertexId).clear();
    }

}
