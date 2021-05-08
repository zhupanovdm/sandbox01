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

        int spent = costs.getOrDefault(node, zero);
        for (Map.Entry<T, Integer> edge : graph.edgesOf(node).entrySet()) {
            int newCost = add.apply(spent, edge.getValue());
            int cost = costs.computeIfAbsent(edge.getKey(), t -> {
                discovered.add(t);
                return infinity;
            });
            if (cost > newCost) {
                costs.put(edge.getKey(), newCost);
                origins.put(edge.getKey(), node);
            }
        }

        T shortest = null;
        int min = infinity;
        for (T current : discovered) {
            int cost = costs.getOrDefault(current, zero);
            if (min > cost) {
                shortest = current;
                min = cost;
            }
        }

        if (shortest != null)
            calc(shortest, discovered);
    }

}
