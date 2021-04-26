package org.zhupanovdm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zhupanovdm.WeightedGraph.EdgeVector;

import java.util.*;

public class Dijkstra<V> {
    private final WeightedGraph<V, Integer> data;

    public Dijkstra(WeightedGraph<V, Integer> data) {
        this.data = data;
    }

    public List<Transition<V>> calc(V src, V dest) {
        List<Transition<V>> result = new LinkedList<>();
        calc(src, dest, new HashMap<>(), new HashMap<>(), new HashSet<>(), result);
        return result;
    }

    private void calc(V vertex, V dest, Map<V, Integer> weights, Map<V, V> origins, Set<V> discovered, List<Transition<V>> result) {
        result.add(new Transition<>(origins.get(vertex), vertex, weights.get(vertex)));

        discovered.remove(vertex);
        if (vertex.equals(dest)) {
            discovered.clear();
        } else {
            Set<EdgeVector<V, Integer>> neighbours = data.neighbours(vertex);
            for (EdgeVector<V, Integer> neighbour : neighbours) {
                int cost = weights.getOrDefault(vertex, 0) + neighbour.getWeight();
                if (weights.containsKey(neighbour.getVertex())) {
                    if (weights.get(neighbour.getVertex()) > cost) {
                        weights.put(neighbour.getVertex(), cost);
                        origins.put(neighbour.getVertex(), vertex);
                    }
                } else {
                    weights.put(neighbour.getVertex(), cost);
                    discovered.add(neighbour.getVertex());
                    origins.put(neighbour.getVertex(), vertex);
                }
            }
        }

        V shortest = null;
        int min = Integer.MAX_VALUE;
        for (V node : discovered) {
            int w = weights.get(node);
            if (min >= w) {
                shortest = node;
                min = w;
            }
        }

        if (shortest != null)
            calc(shortest, dest, weights, origins, discovered, result);
    }

    @Data
    @EqualsAndHashCode
    public static class Transition<V> {
        private final V src;
        private final V dest;
        private final Integer cost;

        public Transition(V src, V dest, Integer cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public String toString() {
            if (src == null)
                return String.format("%s", dest);
            return String.format("%s->%s: %d", src, dest, cost);
        }
    }

}
