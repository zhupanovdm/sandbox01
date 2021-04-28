package org.zhupanovdm;

import org.zhupanovdm.Graph.WeightedEdge;

import java.util.*;

public class Dijkstra<T> {
    private final Graph<T, WeightedEdge<T, Double>> graph;

    public Dijkstra(Graph<T, WeightedEdge<T, Double>> graph) {
        this.graph = graph;
    }

    public List<Graph.EvaluatedTransition<T, Double>> calc(T src, T dest) {
        List<Graph.EvaluatedTransition<T, Double>> result = new LinkedList<>();
        calc(src, dest, new HashMap<>(), new HashSet<>(), result);
        return result;
    }

    private void calc(T node, T destination,
                      Map<T, Graph.EvaluatedTransition<T, Double>> transitions,
                      Set<T> discovered,
                      List<Graph.EvaluatedTransition<T, Double>> result) {

        double spent = 0;
        Graph.EvaluatedTransition<T, Double> transition = transitions.get(node);
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
                Graph.EvaluatedTransition<T, Double> next = transitions.computeIfAbsent(neighbour.node(), t -> {
                    discovered.add(t);
                    return new Graph.EvaluatedTransition<>(t, node, cost);
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
            Graph.EvaluatedTransition<T, Double> next = transitions.get(current);
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
