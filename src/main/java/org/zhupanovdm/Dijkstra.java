package org.zhupanovdm;

import org.zhupanovdm.graph.Graph;
import org.zhupanovdm.graph.WeightedEdge;

import java.util.*;

public class Dijkstra<T> {
    private final Graph<T, WeightedEdge<T, Double>> graph;

    public Dijkstra(Graph<T, WeightedEdge<T, Double>> graph) {
        this.graph = graph;
    }

    public Map<T, WeightedEdge<T, Double>> calc(T src) {
        Map<T, WeightedEdge<T, Double>> table = new HashMap<>();
        calc(src, table, new HashSet<>());
        return table;
    }

    private void calc(T node, Map<T, WeightedEdge<T, Double>> table, Set<T> discovered) {
        double spent = 0;
        WeightedEdge<T, Double> edge = table.get(node);
        if (edge != null) {
            spent = edge.getWeight();
        }

        discovered.remove(node);

        Set<WeightedEdge<T, Double>> edges = graph.edgesOf(node);
        for (WeightedEdge<T, Double> neighbour : edges) {
            double cost = spent + neighbour.getWeight();
            WeightedEdge<T, Double> next = table.computeIfAbsent(neighbour.destination(), t -> {
                discovered.add(t);
                return neighbour.clone().withWeight(cost);
            });

            if (next.compareTo(cost) > 0)
                next.withWeight(cost).setSource(node);
        }

        T shortest = null;
        double min = Double.MAX_VALUE;
        for (T current : discovered) {
            WeightedEdge<T, Double> next = table.get(current);
            double cost = next == null ? 0 : next.getWeight();
            if (min >= cost) {
                shortest = current;
                min = cost;
            }
        }

        if (shortest != null)
            calc(shortest, table, discovered);
    }

    public static <T> double print(Map<T, WeightedEdge<T, Double>> table, T to) {
        WeightedEdge<T, Double> edge = table.get(to);
        if (edge == null) {
            System.out.printf("%s%n", to);
            return 0;
        }

        double cost = print(table, edge.source());
        System.out.printf("%s: %f%n", to, edge.getWeight() - cost);
        return edge.getWeight();
    }

}
