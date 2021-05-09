package org.zhupanovdm.pathfind;

import org.zhupanovdm.graph.WeightedDirectedGraph;

import java.util.Map;

public class SalesmanEager<T> extends PathFinder.Int<T> {

    protected SalesmanEager(WeightedDirectedGraph<T, Integer> graph) {
        super(graph);
    }

    @Override
    public void doCalc(T node) {
        T prev = node;
        Integer cost = zero;
        costs.put(node, cost);

        while (true) {
            Integer min = infinity;
            T shortest = null;
            for (Map.Entry<T, Integer> edge: graph.edgesOf(prev).entrySet()) {
                if (edge.getValue().compareTo(min) < 0) {
                    min = edge.getValue();
                    shortest = edge.getKey();
                }
            }

            if (shortest == null)
                break;

            cost = add.apply(cost, min);

            costs.put(shortest, cost);
            origins.put(shortest, prev);

            prev = shortest;
        }

    }

}
