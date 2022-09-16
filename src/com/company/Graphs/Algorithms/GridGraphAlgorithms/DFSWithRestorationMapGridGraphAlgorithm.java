package com.company.Graphs.Algorithms.GridGraphAlgorithms;

import com.company.Graphs.Algorithms.GraphAlgorithmInterface;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.GraphInterface;
import com.company.Graphs.Implementations.GridGraph;

import java.util.*;


/**
 * Class for running DFS algorithm from source points to finish points omitting blocks points
 * Returns Map, where value represents point and key it parent
 */
public class DFSWithRestorationMapGridGraphAlgorithm implements GraphAlgorithmInterface<Map<GridPoint, GridPoint>, GridPoint, Integer> {
    private final Set<GridPoint> visited = new HashSet<>();

    public DFSWithRestorationMapGridGraphAlgorithm() {
    }

    private void dfs(GridPoint point, GridGraph graph, Map<GridPoint, GridPoint> result) throws NoSuchVertexException {
        if (visited.contains(point)) return;
        visited.add(point);
        List<GridPoint> neighbours = new ArrayList<>(graph.getAllDirectlyConnectedVertexes(point));
        Collections.shuffle(neighbours);
        for (GridPoint to : neighbours) {
            if (visited.contains(to)) continue;
            if (graph.getPointsOfType(GridPoint.GridPointType.BLOCKS).contains(to)) continue;
            result.put(to, point);
            if (graph.getPointsOfType(GridPoint.GridPointType.FINISH).contains(to)) continue;
            dfs(to, graph, result);
        }
    }

    /**
     * Runs DFS algorithm
     *
     * @param graph on which to run algorithm
     * @return map, where value represents point and key it parent
     */
    @Override
    public Map<GridPoint, GridPoint> run(GraphInterface<GridPoint, Integer> graph) {
        Map<GridPoint, GridPoint> result = new LinkedHashMap<>();
        for (GridPoint start : ((GridGraph) graph).getPointsOfType(GridPoint.GridPointType.SOURCE)) {
            try {
                dfs(start, (GridGraph) graph, result);
            } catch (NoSuchVertexException e) {
                e.printStackTrace();
            }
        }

        visited.clear();
        return result;
    }

}
