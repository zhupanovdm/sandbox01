package org.zhupanovdm;

import org.zhupanovdm.graph.EvaluatedTransition;
import org.zhupanovdm.graph.Graph;
import org.zhupanovdm.graph.WeightedEdge;

import java.util.*;

public class Dijkstra<T> {
    private final Graph<T, WeightedEdge<T, Double>> graph;

    public Dijkstra(Graph<T, WeightedEdge<T, Double>> graph) {
        this.graph = graph;
    }

    public List<EvaluatedTransition<T, Double>> calc(T src, T dest) {
        List<EvaluatedTransition<T, Double>> result = new LinkedList<>();
        calc(src, dest, new HashMap<>(), new HashSet<>(), result);
        return result;
    }

    private void calc(T node, T destination,
                      Map<T, EvaluatedTransition<T, Double>> transitions,
                      Set<T> discovered,
                      List<EvaluatedTransition<T, Double>> result) {

        double spent = 0;
        EvaluatedTransition<T, Double> transition = transitions.get(node);
        if (transition != null) {
            result.add(transition);
            spent = transition.getCost();
        }

        discovered.remove(node);
        if (node.equals(destination)) {
            discovered.clear();

        } else {
            Set<WeightedEdge<T, Double>> edges = graph.edgesOf(node);
            for (WeightedEdge<T, Double> neighbour : edges) {
                double cost = spent + neighbour.getWeight();
                EvaluatedTransition<T, Double> next = transitions.computeIfAbsent(neighbour.node(), t -> {
                    discovered.add(t);
                    return new EvaluatedTransition<>(t, node, cost);
                });

                if (next.getCost() > cost) {
                    next.setCost(cost);
                    next.setOrigin(node);
                }
            }
        }

        T shortest = null;
        double min = Double.MAX_VALUE;
        for (T current : discovered) {
            EvaluatedTransition<T, Double> next = transitions.get(current);
            double cost = next == null ? 0 : next.getCost();
            if (min >= cost) {
                shortest = current;
                min = cost;
            }
        }

        if (shortest != null)
            calc(shortest, destination, transitions, discovered, result);

    }

}
