import com.company.Graphs.Implementations.AbstractGraph;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;
import com.company.Graphs.Implementations.UnDirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test abstract graph functionality using UnDirectedGraph
 */
public class AbstractGraphTest {

    @Test
    public void addVertex_addsVertexes() throws VertexAlreadyExistsException, NoSuchVertexException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        for (int i = 0; i < 100; ++i) {
            graph.addVertex(i, i);
        }
        boolean isCorrect = true;
        for (int i = 0; i < 100; ++i) {
            isCorrect = isCorrect && graph.containsVertex(i);
        }
        assertTrue(isCorrect);
    }

    @Test
    public void addVertexTwice_trowsException() throws VertexAlreadyExistsException, NoSuchVertexException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        Exception exception = assertThrows(VertexAlreadyExistsException.class, () -> {
            graph.addVertex(0, 0);
            graph.addVertex(0, 1);
        });
        assertEquals("Vertex 0 already exists", exception.getMessage());
    }

    @Test
    public void removeVertex_removesVertex() throws VertexAlreadyExistsException, NoSuchVertexException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        graph.addVertex(0, 0);
        graph.removeVertex(0);
        assertFalse(graph.containsVertex(0));
    }

    @Test
    public void removeVertexTwice_throwsException() throws VertexAlreadyExistsException, NoSuchVertexException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        graph.addVertex(0, 0);
        graph.removeVertex(0);
        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.removeVertex(0));
        assertEquals("There is no such vertex 0", exception.getMessage());

    }

    @Test
    public void removeNonExistingVertex_throwsException() {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.removeVertex(0));
        assertEquals("There is no such vertex 0", exception.getMessage());
    }

    @Test
    public void getVertexValue_returnsValue() throws NoSuchVertexException, VertexAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        for (int i = 0; i < 100; ++i) {
            graph.addVertex(i, i);
        }

        boolean isCorrect = true;
        for (int i = 0; i < 100; ++i) {
            isCorrect = isCorrect && (i == graph.getVertexValue(i));
        }
        assertTrue(isCorrect);
    }

    @Test
    public void getNonExistingVertexValue_throwsException() {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.getVertexValue(0));
        assertEquals("There is no such vertex 0", exception.getMessage());
    }

    @Test
    public void getAllVertexesIds_returnsAllVertexesIds() throws VertexAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        for (int i = 0; i < 100; ++i) {
            graph.addVertex(i, i);
        }
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            expected.add(i);
        }

        assertEquals(expected, graph.getAllVertexesIds());
    }

    @Test
    public void isGraphConnectedForConnectedGraph_returnsTrue() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 10; ++i) {
            graph.addEdge(i - 1, i);
        }
        assertTrue(graph.isGraphConnected());
    }

    @Test
    public void isGraphConnectedForNonConnectedGraph_returnsTrue() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 9; ++i) {
            graph.addEdge(i - 1, i);
        }
        assertFalse(graph.isGraphConnected());
    }

    @Test
    public void calculateShortestDistanceBetweenVertexesForConnectedVertexes_returnsDistances() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 9; ++i) {
            graph.addEdge(i - 1, i);
        }
        int result = graph.calculateShortestDistanceBetweenVertexes(0, 2);
        assertEquals(2, result);
    }

    @Test
    public void calculateShortestDistanceBetweenVertexesForNonConnectedVertexes_returnsDistances() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new UnDirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 9; ++i) {
            graph.addEdge(i - 1, i);
        }
        int result = graph.calculateShortestDistanceBetweenVertexes(0, 9);
        assertEquals(Integer.MAX_VALUE, result);
    }

}
