package com.company.Graphs.Algorithms.GridGraphAlgorithms;

import com.company.Graphs.Algorithms.GridGraphAlgorithmInterface;
import com.company.Graphs.Errors.NoSuchVertexException;
import com.company.Graphs.Graph;

import java.util.*;


/**
 * Class for running DFS algorithm from source points to finish points omitting blocks points
 * Returns Map, where value represents point and key it parent
 */
public class DFSWithRestorationMapGridGraphAlgorithm implements GridGraphAlgorithmInterface<Map<GridPoint, GridPoint>, GridPoint, Integer> {
    private final Set<GridPoint> visited = new HashSet<>();
    private Set<GridPoint> starts;
    private Set<GridPoint> finishes;
    private Set<GridPoint> blocks;

    public DFSWithRestorationMapGridGraphAlgorithm() {
    }

    public DFSWithRestorationMapGridGraphAlgorithm(Set<GridPoint> starts, Set<GridPoint> finishes, Set<GridPoint> blocks) {
        this.starts = starts;
        this.finishes = finishes;
        this.blocks = blocks;
    }

    private void dfs(GridPoint point, Graph<GridPoint, Integer> graph, Map<GridPoint, GridPoint> result) throws NoSuchVertexException {
        if (visited.contains(point)) return;
        visited.add(point);
        List<GridPoint> neighbours = new ArrayList<>(graph.getAllDirectlyConnectedVertexes(point));
        Collections.shuffle(neighbours);
        for (GridPoint to : neighbours) {
            if (visited.contains(to) || blocks.contains(to)) continue;
            result.put(to, point);
            if (finishes.contains(to)) continue;
            dfs(to, graph, result);
        }
    }

    /**
     * Runs DFS algorithm
     * @param graph on which to run algorithm
     * @return map, where value represents point and key it parent
     */
    @Override
    public Map<GridPoint, GridPoint> run(Graph<GridPoint, Integer> graph) {
        Map<GridPoint, GridPoint> result = new LinkedHashMap<>();
        for (GridPoint start : starts) {
            try {
                dfs(start, graph, result);
            } catch (NoSuchVertexException e) {
                e.printStackTrace();
            }
        }

        visited.clear();
        return result;
    }

    @Override
    public Set<GridPoint> getStarts() {
        return starts;
    }

    @Override
    public void setStarts(Set<GridPoint> strats) {
        this.starts = strats;
    }

    @Override
    public Set<GridPoint> getFinishes() {
        return finishes;
    }

    @Override
    public void setFinishes(Set<GridPoint> finishes) {
        this.finishes = finishes;
    }

    @Override
    public Set<GridPoint> getBlocks() {
        return blocks;
    }

    @Override
    public void setBlocks(Set<GridPoint> blocks) {
        this.blocks = blocks;
    }


}
