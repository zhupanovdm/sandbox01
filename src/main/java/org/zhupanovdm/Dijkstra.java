package org.zhupanovdm;

import org.zhupanovdm.graph.WeightedDirectedGraph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra<T> extends PathFinder.Int<T> {

    public Dijkstra(WeightedDirectedGraph<T, Integer> graph) {
        super(graph);
    }

    @Override
    public void doCalc(T src) {
        calc(src, new HashSet<>());
    }

    private void calc(T node, Set<T> discovered) {
        discovered.remove(node);

        Integer spent = costs.getOrDefault(node, zero);
        for (Map.Entry<T, Integer> edge : graph.edgesOf(node).entrySet()) {
            Integer newCost = add.apply(spent, edge.getValue());
            Integer cost = costs.computeIfAbsent(edge.getKey(), t -> {
                discovered.add(t);
                return infinity;
            });
            if (cost.compareTo(newCost) > 0) {
                costs.put(edge.getKey(), newCost);
                origins.put(edge.getKey(), node);
            }
        }

        T shortest = null;
        Integer min = infinity;
        for (T current : discovered) {
            Integer cost = costs.getOrDefault(current, zero);
            if (min.compareTo(cost) > 0) {
                shortest = current;
                min = cost;
            }
        }

        if (shortest != null)
            calc(shortest, discovered);
    }

}
