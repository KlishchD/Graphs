import com.company.Graphs.Algorithms.GridGraphAlgorithms.BFSWithRestorationMapGridGraphAlgorithm;
import com.company.Graphs.Algorithms.GridGraphAlgorithms.GridPoint;
import com.company.Graphs.Algorithms.GridAlgorithmManager;
import com.company.Graphs.Algorithms.GridAlgorithmResult;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GridAlgorithmManagerTest {
    @Test
    public void runForGraphWithNoSelectedPoints_emptyResults() {
        GridAlgorithmManager manager = new GridAlgorithmManager(10, 10, new BFSWithRestorationMapGridGraphAlgorithm());
        GridAlgorithmResult result = manager.runAlgorithm();
        assertTrue(result.getRestoredPaths().isEmpty());
        assertTrue(result.getVisited().isEmpty());
    }

    @Test
    public void runForGraphWithSelectedSource_mapForRestoration() {
        GridAlgorithmManager manager = new GridAlgorithmManager(10, 10, new BFSWithRestorationMapGridGraphAlgorithm());
        manager.updatePointType(new GridPoint(0, 0), GridPoint.GridPointType.SOURCE);
        GridAlgorithmResult result = manager.runAlgorithm();
        assertTrue(result.getRestoredPaths().isEmpty());
        assertEquals(99, result.getVisited().size());
    }

    @Test
    public void runForGraphWithSelectedSourceAndBlocks_mapForRestoration() {
        GridAlgorithmManager manager = new GridAlgorithmManager(10, 10, new BFSWithRestorationMapGridGraphAlgorithm());
        manager.updatePointType(new GridPoint(0, 0), GridPoint.GridPointType.SOURCE);
        manager.updatePointType(new GridPoint(0, 1), GridPoint.GridPointType.BLOCKS);
        GridAlgorithmResult result = manager.runAlgorithm();

        assertTrue(result.getRestoredPaths().isEmpty());
        assertEquals(98, result.getVisited().size());
        assertFalse(result.getVisited().contains(new GridPoint(0, 1)));
    }

    @Test
    public void runForGraphWithSelectedSourceAndBlocksAndFinish_mapForRestoration() {
        GridAlgorithmManager manager = new GridAlgorithmManager(10, 10, new BFSWithRestorationMapGridGraphAlgorithm());
        manager.updatePointType(new GridPoint(0, 0), GridPoint.GridPointType.SOURCE);
        manager.updatePointType(new GridPoint(1, 0), GridPoint.GridPointType.BLOCKS);
        manager.updatePointType(new GridPoint(0, 5), GridPoint.GridPointType.FINISH);
        GridAlgorithmResult result = manager.runAlgorithm();

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
