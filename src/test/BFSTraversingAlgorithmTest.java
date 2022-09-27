import com.company.Graphs.Algorithms.TraversingAlgorithms.BFSTraversingAlgorithm;
import com.company.Graphs.GridPoint;
import com.company.Graphs.GraphInterface.*;
import com.company.Graphs.Implementations.GridGraph;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class BFSTraversingAlgorithmTest {

    @Test
    public void runForGraphWithNoSelectedPoints_emptyResults() {
        GridGraph graph = new GridGraph(10, 10);
        Map<GridPoint, GridPoint> result = new BFSTraversingAlgorithm().run(graph);
        assertTrue(result.isEmpty());
    }

    @Test
    public void runForGraphWithSelectedSource_mapForRestoration() {
        GridGraph graph = new GridGraph(10, 10);
        graph.updatePointType(new GridPoint(0, 0), PointType.SOURCE);
        Map<GridPoint, GridPoint> result = new BFSTraversingAlgorithm().run(graph);
        assertEquals(99, result.size());
    }

    @Test
    public void runForGraphWithSelectedSourceAndBlocks_mapForRestoration() {
        GridGraph graph = new GridGraph(10, 10);
        graph.updatePointType(new GridPoint(0, 0), PointType.SOURCE);
        graph.updatePointType(new GridPoint(0, 1), PointType.BLOCKS);
        Map<GridPoint, GridPoint> result = new BFSTraversingAlgorithm().run(graph);
        assertEquals(98, result.size());
        assertFalse(result.containsKey(new GridPoint(0, 1)));
    }

}
