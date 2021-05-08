package org.zhupanovdm.pathfind;

import org.zhupanovdm.graph.WeightedDirectedGraph;

import java.util.ArrayList;
import java.util.List;

public class BellmanFord<T> extends PathFinder.Int<T> {

    public BellmanFord(WeightedDirectedGraph<T, Integer> graph) {
        super(graph);
    }

    @Override
    public void doCalc(T from) {
        List<T[]> edges = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();
        graph.forEachEdge((edge, weight) -> {
            edges.add(edge);
            weights.add(weight);
            costs.putIfAbsent(edge[0], infinity);
            costs.putIfAbsent(edge[1], infinity);
        });

        costs.put(from, zero);

        int n = costs.keySet().size();
        boolean modified = true;
        for (int i = 1; modified; i++) {
            if (i == n)
                throw new IllegalStateException("Negative cycle detected");

            modified = false;
            for (int j = 0; j < edges.size(); j++) {
                T src = edges.get(j)[0];
                T dest = edges.get(j)[1];
                Integer weight = weights.get(j);
                Integer cost = costs.getOrDefault(src, infinity);
                if (cost < infinity) {
                    Integer current = costs.getOrDefault(dest, infinity);
                    if (current.compareTo(add.apply(cost, weight)) > 0) {
                        costs.put(dest, add.apply(cost, weight));
                        origins.put(dest, src);
                        modified = true;
                    }
                }
            }
        }
    }

}
