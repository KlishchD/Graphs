import com.company.Graphs.Algorithms.GridGraphAlgorithms.BFSWithRestorationMapGridGraphAlgorithm;
import com.company.Graphs.Algorithms.GridGraphAlgorithms.GridPoint;
import com.company.Graphs.Implementations.GridGraph;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class BFSWithRestorationMapGridGraphAlgorithmTest {

    @Test
    public void runForGraphWithNoSelectedPoints_emptyResults() {
        GridGraph graph = new GridGraph(10, 10);
        Map<GridPoint, GridPoint> result = new BFSWithRestorationMapGridGraphAlgorithm().run(graph);
        assertTrue(result.isEmpty());
    }

    @Test
    public void runForGraphWithSelectedSource_mapForRestoration() {
        GridGraph graph = new GridGraph(10, 10);
        graph.updatePointType(new GridPoint(0, 0), GridPoint.GridPointType.SOURCE);
        Map<GridPoint, GridPoint> result = new BFSWithRestorationMapGridGraphAlgorithm().run(graph);
        assertEquals(99, result.size());
    }

    @Test
    public void runForGraphWithSelectedSourceAndBlocks_mapForRestoration() {
        GridGraph graph = new GridGraph(10, 10);
        graph.updatePointType(new GridPoint(0, 0), GridPoint.GridPointType.SOURCE);
        graph.updatePointType(new GridPoint(0, 1), GridPoint.GridPointType.BLOCKS);
        Map<GridPoint, GridPoint> result = new BFSWithRestorationMapGridGraphAlgorithm().run(graph);
        assertEquals(98, result.size());
        assertFalse(result.containsKey(new GridPoint(0, 1)));
    }

}
