package org.zhupanovdm;

import lombok.Getter;
import org.zhupanovdm.graph.WeightedDirectedGraph;

import java.util.*;
import java.util.function.BiFunction;

public class Dijkstra<T, W extends Comparable<W>> {
    private final WeightedDirectedGraph<T, W> graph;
    private final Map<T, W> costs = new HashMap<>();
    private final Map<T, T> origins = new HashMap<>();
    private final BiFunction<W, W, W> add;
    private final BiFunction<W, W, W> sub;
    private final W zero;
    private final W infinity;

    private Dijkstra(WeightedDirectedGraph<T, W> graph,
                    BiFunction<W, W, W> add,
                    BiFunction<W, W, W> sub,
                    W zero, W infinity) {
        this.graph = graph;
        this.add = add;
        this.sub = sub;
        this.zero = zero;
        this.infinity = infinity;
    }

    public Dijkstra<T, W> calcFrom(T src) {
        costs.clear();
        origins.clear();
        calc(src, new HashSet<>());
        return this;
    }

    public Dijkstra<T, W> printTo(T to) {
        print(to);
        return this;
    }

    private W print(T to) {
        T from = origins.get(to);
        if (from == null) {
            System.out.printf("%s%n", to);
            return zero;
        }

        W spent = print(from);
        W cost = costs.getOrDefault(to, zero);
        System.out.printf("%s: %s%n", to, sub.apply(cost, spent));
        return cost;
    }


    public Map<T, W> getCosts() {
        return Collections.unmodifiableMap(costs);
    }

    public Map<T, T> getOrigins() {
        return Collections.unmodifiableMap(origins);
    }

    private void calc(T node, Set<T> discovered) {
        discovered.remove(node);

        W spent = costs.getOrDefault(node, zero);
        for (Map.Entry<T, W> edge : graph.edgesOf(node).entrySet()) {
            W newCost = add.apply(spent, edge.getValue());
            W cost = costs.computeIfAbsent(edge.getKey(), t -> {
                discovered.add(t);
                return infinity;
            });

            if (cost.compareTo(newCost) > 0) {
                costs.put(edge.getKey(), newCost);
                origins.put(edge.getKey(), node);
            }
        }

        T shortest = null;
        W min = infinity;
        for (T current : discovered) {
            W cost = costs.getOrDefault(current, zero);
            if (min.compareTo(cost) > 0) {
                shortest = current;
                min = cost;
            }
        }

        if (shortest != null)
            calc(shortest, discovered);
    }

    public static <T> Dijkstra<T, Integer> forInt(WeightedDirectedGraph<T, Integer> graph) {
        return new Dijkstra<>(graph, Integer::sum, (l, r) -> l - r, 0, Integer.MAX_VALUE);
    }

}
