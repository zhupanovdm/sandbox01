package org.zhupanovdm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zhupanovdm.Graph.WeightedEdge;

import java.util.*;

public class Dijkstra<T> {
    private final Graph<T, WeightedEdge<T, Double>> graph;

    public Dijkstra(Graph<T, WeightedEdge<T, Double>> graph) {
        this.graph = graph;
    }

    public List<Transition<T>> calc(T src, T dest) {
        List<Transition<T>> result = new LinkedList<>();
        calc(src, dest, new HashMap<>(), new HashSet<>(), result);
        return result;
    }

    private void calc(T node, T destination, Map<T, Transition<T>> transitions, Set<T> discovered, List<Transition<T>> result) {
        double spent = 0;
        Transition<T> transition = transitions.get(node);
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
                Transition<T> next = transitions.computeIfAbsent(neighbour.node(), t -> {
                    discovered.add(t);
                    return new Transition<>(t, node, cost);
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
            Transition<T> next = transitions.get(current);
            double cost = next == null ? 0 : next.getCost();
            if (min >= cost) {
                shortest = current;
                min = cost;
            }
        }

        if (shortest != null)
            calc(shortest, destination, transitions, discovered, result);

    }

    @Data
    @EqualsAndHashCode
    public static class Transition<V> {
        private V destination;
        private V origin;
        private Double cost;

        public Transition(V destination, V origin, Double cost) {
            this.destination = destination;
            this.origin = origin;
            this.cost = cost;
        }

        @Override
        public String toString() {
            if (origin == null)
                return String.format("%s", destination);
            return String.format("%s >> %s: %.2f", origin, destination, cost);
        }
    }

}
