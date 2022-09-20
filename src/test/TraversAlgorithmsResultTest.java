import com.company.Graphs.Algorithms.TraversAlgorithmsResult;
import com.company.Graphs.Algorithms.TraversingAlgorithms.BFSTraversingAlgorithm;
import com.company.Graphs.Errors.EdgeAlreadyExistsException;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Errors.VertexAlreadyExistsException;
import com.company.Graphs.GraphInterface;
import com.company.Graphs.GridPoint;
import com.company.Graphs.GraphInterface.PointType;
import com.company.Graphs.Implementations.GridGraph;
import com.company.Graphs.Implementations.UnDirectedGraph;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TraversAlgorithmsResultTest {
    @Test
    public void runForGraphWithNoSelectedPoints_emptyResults() {
        Map<Integer, Integer> empty = new HashMap<>();
        GraphInterface<Integer, Integer> graph = new UnDirectedGraph<>();
        TraversAlgorithmsResult<Integer> result = new TraversAlgorithmsResult<>(empty, graph);
        assertTrue(result.getRestoredPaths().isEmpty());
        assertTrue(result.getVisited().isEmpty());
    }

    @Test
    public void runForGraphWithSelectedSource_mapForRestoration() throws VertexAlreadyExistsException, NoSuchVertexException, EdgeAlreadyExistsException {
        GraphInterface<GridPoint, Integer> graph = new GridGraph(10, 10);
        graph.updatePointType(new GridPoint(0, 0), PointType.SOURCE);
        Map<GridPoint, GridPoint> results = new BFSTraversingAlgorithm<GridPoint, Integer>().run(graph);
        TraversAlgorithmsResult<GridPoint> result = new TraversAlgorithmsResult<>(results, graph);
        assertTrue(result.getRestoredPaths().isEmpty());
        assertEquals(99, result.getVisited().size());
    }

    @Test
    public void runForGraphWithSelectedSourceAndBlocks_mapForRestoration() {
        GraphInterface<GridPoint, Integer> graph = new GridGraph(10, 10);
        graph.updatePointType(new GridPoint(0, 0), PointType.SOURCE);
        graph.updatePointType(new GridPoint(0, 1), PointType.BLOCKS);

        Map<GridPoint, GridPoint> results = new BFSTraversingAlgorithm<GridPoint, Integer>().run(graph);
        TraversAlgorithmsResult<GridPoint> result = new TraversAlgorithmsResult<>(results, graph);

        assertTrue(result.getRestoredPaths().isEmpty());
        assertEquals(98, result.getVisited().size());
        assertFalse(result.getVisited().contains(new GridPoint(0, 1)));
    }

    @Test
    public void runForGraphWithSelectedSourceAndBlocksAndFinish_mapForRestoration() {
        GraphInterface<GridPoint, Integer> graph = new GridGraph(10, 10);
        graph.updatePointType(new GridPoint(0, 0), PointType.SOURCE);
        graph.updatePointType(new GridPoint(1, 0), PointType.BLOCKS);
        graph.updatePointType(new GridPoint(0, 5), PointType.FINISH);

        Map<GridPoint, GridPoint> results = new BFSTraversingAlgorithm<GridPoint, Integer>().run(graph);
        TraversAlgorithmsResult<GridPoint> result = new TraversAlgorithmsResult<>(results, graph);

        List<Pair<GridPoint, GridPoint>> expectedRestoredPath = new ArrayList<>();
        for (int i = 4; i >= 0; --i) {
            expectedRestoredPath.add(new Pair<>(new GridPoint(0, i + 1), new GridPoint(0, i)));
        }

        for (int i = 0; i < 5; ++i) {
            assertEquals(expectedRestoredPath.get(i), result.getRestoredPaths().get(i));
        }
        assertEquals(97, result.getVisited().size());
        assertFalse(result.getVisited().contains(new GridPoint(1, 0)));
    }


}
