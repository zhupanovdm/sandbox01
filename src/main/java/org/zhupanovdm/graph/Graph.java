package org.zhupanovdm.graph;

import java.util.*;

public class Graph<N, E extends AbstractEdge<N, E>> {
    private final Map<N, Set<E>> data = new HashMap<>();

    public void add(N from, E edge) {
        Set<E> edges = data.computeIfAbsent(from, n -> new HashSet<>());
        edges.remove(edge);
        edges.add(edge);
    }

    public Set<E> edgesOf(N node) {
        return Collections.unmodifiableSet(data.getOrDefault(node, Collections.emptySet()));
    }

    public static class Basic<T> extends Graph<T, Edge<T>> {

        public RouteBuilder<T> from(T node) {
            return new RouteBuilder<>(this, node);
        }

    }

    public static class Weighted<T, W extends Comparable<W>> extends Graph<T, WeightedEdge<T, W>> {

        public WeightedRouteBuilder<T, W> from(T node) {
            return new WeightedRouteBuilder<>(this, node);
        }

    }

}
