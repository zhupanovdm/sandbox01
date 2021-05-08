package org.zhupanovdm.pathfind;

import org.zhupanovdm.graph.WeightedDirectedGraph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class PathFinder<T, W extends Comparable<W>> {
    protected final WeightedDirectedGraph<T, W> graph;
    protected final Map<T, W> costs = new HashMap<>();
    protected final Map<T, T> origins = new HashMap<>();
    protected final BiFunction<W, W, W> add;
    protected final BiFunction<W, W, W> sub;
    protected final W zero;
    protected final W infinity;

    public PathFinder(WeightedDirectedGraph<T, W> graph, BiFunction<W, W, W> add, BiFunction<W, W, W> sub, W zero, W infinity) {
        this.graph = graph;
        this.add = add;
        this.sub = sub;
        this.zero = zero;
        this.infinity = infinity;
    }

    public PathFinder<T, W> calcFrom(T src) {
        costs.clear();
        origins.clear();
        doCalc(src);
        return this;
    }

    public PathFinder<T, W> printTo(T to) {
        print(to);
        return this;
    }

    public Map<T, W> getCosts() {
        return Collections.unmodifiableMap(costs);
    }

    public Map<T, T> getOrigins() {
        return Collections.unmodifiableMap(origins);
    }

    public abstract void doCalc(T src);

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

    public static abstract class Int<T> extends PathFinder<T, Integer> {
        protected Int(WeightedDirectedGraph<T, Integer> graph) {
            super(graph, Integer::sum, (l, r) -> l - r, 0, Integer.MAX_VALUE);
        }
    }

}
