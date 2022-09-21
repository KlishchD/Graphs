package com.company.Graphs.Implementations;

import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchEdgeException;
import com.company.Graphs.Errors.NoSuchVertexException;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> Type of vertexId
 * @param <E> Type of values in vertex
 */
public class UnDirectedGraph<T, E> extends AbstractGraph<T, E> {
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
        connectionsMap.get(secondVertex).add(firstVertex);
        edgesValues.put(new Pair<>(firstVertex, secondVertex), value);
        edgesValues.put(new Pair<>(secondVertex, firstVertex), value);
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
        connectionsMap.get(secondVertex).remove(firstVertex);
        edgesValues.remove(new Pair<>(firstVertex, secondVertex));
        edgesValues.remove(new Pair<>(secondVertex, firstVertex));
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
     * Removes all vertexes that have specified vertex
     *
     * @param vertexId id of a vertex
     * @throws NoSuchVertexException if a specified vertex doesn't exist
     */
    public void removeAllEdgesForVertex(T vertexId) throws NoSuchVertexException {
        if (!connectionsMap.containsKey(vertexId))
            throw new NoSuchVertexException("There is no such vertex " + vertexId);
        List<T> vertexes = new ArrayList<>(connectionsMap.get(vertexId));
        for (T vertex : vertexes) {
            try {
                removeEdge(vertex, vertexId);
            } catch (NoSuchEdgeException ignore) {
            }
        }
    }


}
