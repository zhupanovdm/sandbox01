package org.zhupanovdm;

import org.zhupanovdm.graph.WeightedTransition;
import org.zhupanovdm.graph.Graph;
import org.zhupanovdm.graph.WeightedEdge;

import java.util.*;

public class Dijkstra<T> {
    private final Graph<T, WeightedEdge<T, Double>> graph;

    public Dijkstra(Graph<T, WeightedEdge<T, Double>> graph) {
        this.graph = graph;
    }

    public List<WeightedTransition<T, Double>> calc(T src, T dest) {
        List<WeightedTransition<T, Double>> result = new LinkedList<>();
        calc(src, dest, new HashMap<>(), new HashSet<>(), result);
        return result;
    }

    private void calc(T node, T destination,
                      Map<T, WeightedTransition<T, Double>> transitions,
                      Set<T> discovered,
                      List<WeightedTransition<T, Double>> result) {

        double spent = 0;
        WeightedTransition<T, Double> transition = transitions.get(node);
        if (transition != null) {
            result.add(transition);
            spent = transition.getWeight();
        }

        discovered.remove(node);
        if (node.equals(destination)) {
            discovered.clear();

        } else {
            Set<WeightedEdge<T, Double>> edges = graph.edgesOf(node);
            for (WeightedEdge<T, Double> neighbour : edges) {
                double cost = spent + neighbour.getWeight();
                WeightedTransition<T, Double> next = transitions.computeIfAbsent(neighbour.node(), t -> {
                    discovered.add(t);
                    return new WeightedTransition<>(t, node, cost);
                });

                if (next.getWeight() > cost) {
                    next.setWeight(cost);
                    next.setOrigin(node);
                }
            }
        }

        T shortest = null;
        double min = Double.MAX_VALUE;
        for (T current : discovered) {
            WeightedTransition<T, Double> next = transitions.get(current);
            double cost = next == null ? 0 : next.getWeight();
            if (min >= cost) {
                shortest = current;
                min = cost;
            }
        }

        if (shortest != null)
            calc(shortest, destination, transitions, discovered, result);

    }

}
