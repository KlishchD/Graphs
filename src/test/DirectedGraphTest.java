import com.company.Graphs.Implementations.AbstractGraph;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchEdgeException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;
import com.company.Graphs.Implementations.DirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DirectedGraphTest {

    @Test
    public void addEdgeForNonExisingEdge_addsEdge() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(0, 0);
        graph.addVertex(1, 0);
        graph.addEdge(0, 1);
        assertTrue(graph.containsEdge(0, 1));
    }

    @Test
    public void addEdgeWithForExistingFirstVertex_throwsException() throws VertexAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(1, 0);
        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.addEdge(0, 1));
        assertEquals("There is no such vertex 0", exception.getMessage());
    }

    @Test
    public void addEdgeForNonExistingSecondVertex_throwsException() throws VertexAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(0, 0);
        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.addEdge(0, 1));
        assertEquals("There is no such vertex 1", exception.getMessage());
    }

    @Test
    public void addEdgeForAlreadyExistingEdge_throwsException() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(0, 0);
        graph.addVertex(1, 0);
        graph.addEdge(0, 1);
        Exception exception = assertThrows(EdgeAlreadyExistsException.class, () -> graph.addEdge(0, 1));
        assertEquals("Edge between 0 and 1 already exists", exception.getMessage());
    }

    @Test
    public void addEdgeForReversedEdgeOfExising_addsEdge() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(0, 0);
        graph.addVertex(1, 0);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        assertTrue(graph.containsEdge(0, 1) && graph.containsEdge(1, 0));
    }

    @Test
    public void removeEdge_removesEdge() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException, NoSuchEdgeException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(0, 0);
        graph.addVertex(1, 0);
        graph.addEdge(0, 1);
        graph.removeEdge(0, 1);
        assertTrue(!graph.containsEdge(0, 1) && !graph.containsEdge(1, 0));
    }

    @Test
    public void removeEdgeForNonExistingFirstVertex_throwsException() throws VertexAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(1, 0);
        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.removeEdge(0, 1));
        assertEquals("There is no such vertex 0", exception.getMessage());
    }

    @Test
    public void removeEdgeForNonExistingSecondVertex_throwsException() throws VertexAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(0, 0);
        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.removeEdge(0, 1));
        assertEquals("There is no such vertex 1", exception.getMessage());
    }

    @Test
    public void removeEdgeForNonExistingEdge_throwsException() throws VertexAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(0, 0);
        graph.addVertex(1, 0);
        Exception exception = assertThrows(NoSuchEdgeException.class, () -> graph.removeEdge(0, 1));
        assertEquals("There is no such edge between 0 and 1", exception.getMessage());
    }

    @Test
    public void removeEdgeForAlreadyRemovedEdge_throwsException() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException, NoSuchEdgeException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(0, 0);
        graph.addVertex(1, 0);
        graph.addEdge(0, 1);
        graph.removeEdge(0, 1);
        Exception exception = assertThrows(NoSuchEdgeException.class, () -> graph.removeEdge(0, 1));
        assertEquals("There is no such edge between 0 and 1", exception.getMessage());
    }

    @Test
    public void removeEdgeForNonExistingReversedEdge_throwsException() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException, NoSuchEdgeException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        graph.addVertex(0, 0);
        graph.addVertex(1, 0);
        graph.addEdge(0, 1);
        Exception exception = assertThrows(NoSuchEdgeException.class, () -> graph.removeEdge(1, 0));
        assertEquals("There is no such edge between 1 and 0", exception.getMessage());
    }

    @Test
    public void getAllDirectlyConnectedVertexesForConnectedVertex_returnsAllDirectlyConnectedVertexes() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 10; ++i) {
            graph.addEdge(0, i);
        }

        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            expected.add(i);
        }

        assertEquals(expected, graph.getAllDirectlyConnectedVertexes(0));
        assertTrue(graph.getAllDirectlyConnectedVertexes(1).isEmpty());

    }

    @Test
    public void getAllDirectlyConnectedVertexesForNonConnected_returnsAllDirectlyConnectedVertexes() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        AbstractGraph<Integer, Integer> graph = new DirectedGraph<>();
        for (int i = 0; i <= 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 10; ++i) {
            graph.addEdge(0, i);
        }

        assertTrue(graph.getAllDirectlyConnectedVertexes(10).isEmpty());
    }


    @Test
    public void removeAllEdgesPointedFromVertexForNonExistingVertex_removesEdges() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        DirectedGraph<Integer, Integer> graph = new DirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 10; ++i) {
            graph.addEdge(0, i);
        }

        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.removeAllEdgesPointedFromVertex(11));
        assertEquals("There is no such vertex 11", exception.getMessage());
    }

    @Test
    public void removeAllEdgesPointedFromVertexForRemovedVertex_removesEdges() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        DirectedGraph<Integer, Integer> graph = new DirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 10; ++i) {
            graph.addEdge(0, i);
        }
        graph.removeVertex(0);
        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.removeAllEdgesPointedFromVertex(0));
        assertEquals("There is no such vertex 0", exception.getMessage());
    }

    @Test
    public void removeAllEdgesPointedFromVertexForConnectedVertex_removesEdges() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        DirectedGraph<Integer, Integer> graph = new DirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 10; ++i) {
            graph.addEdge(0, i);
        }

        graph.removeAllEdgesPointedFromVertex(0);


        boolean isCorrect = true;
        for (int i = 0; i < 10; ++i) {
            isCorrect = isCorrect && graph.getAllDirectlyConnectedVertexes(0).isEmpty();
        }
        assertTrue(isCorrect);
    }


    @Test
    public void removeAllEdgesPointedToVertexForNonExistingVertex_removesEdges() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        DirectedGraph<Integer, Integer> graph = new DirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 10; ++i) {
            graph.addEdge(i, 0);
        }

        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.removeAllEdgesPointedToVertex(11));
        assertEquals("There is no such vertex 11", exception.getMessage());
    }

    @Test
    public void removeAllEdgesPointedToVertexForRemovedVertex_removesEdges() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        DirectedGraph<Integer, Integer> graph = new DirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 10; ++i) {
            graph.addEdge(i, 0);
        }
        graph.removeVertex(0);
        Exception exception = assertThrows(NoSuchVertexException.class, () -> graph.removeAllEdgesPointedToVertex(0));
        assertEquals("There is no such vertex 0", exception.getMessage());
    }

    @Test
    public void removeAllEdgesPointedToVertexForConnectedVertex_removesEdges() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        DirectedGraph<Integer, Integer> graph = new DirectedGraph<>();
        for (int i = 0; i < 10; ++i) {
            graph.addVertex(i, i);
        }
        for (int i = 1; i < 10; ++i) {
            graph.addEdge(i, 0);
        }

        graph.removeAllEdgesPointedToVertex(0);


        boolean isCorrect = true;
        for (int i = 0; i < 10; ++i) {
            isCorrect = isCorrect && graph.getAllDirectlyConnectedVertexes(0).isEmpty();
        }
        assertTrue(isCorrect);
    }

}
