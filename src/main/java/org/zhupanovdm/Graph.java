package org.zhupanovdm;

import lombok.*;

import java.util.*;

public class Graph<N, E extends Graph.AbstractEdge<N, E>> {
    private final Map<N, Set<E>> data = new HashMap<>();

    public E add(N from, E edge) {
        Set<E> edges = data.computeIfAbsent(from, n -> new HashSet<>());
        edges.remove(edge);
        edges.add(edge);
        return edge;
    }

    public Set<E> edgesOf(N node) {
        return Collections.unmodifiableSet(data.getOrDefault(node, Collections.emptySet()));
    }

    @EqualsAndHashCode(of = "node")
    public static abstract class AbstractEdge<N, E extends AbstractEdge<N, E>> {
        protected final Graph<N, E> graph;
        protected final N node;

        public AbstractEdge(Graph<N, E> graph, N node) {
            this.graph = graph;
            this.node = node;
        }

        public Graph<N, E> graph() {
            return graph;
        }

        public N node() {
            return node;
        }

        public Set<E> neighbours() {
            return graph.edgesOf(node);
        }
    }

    public static class Edge<N> extends AbstractEdge<N, Edge<N>> {
        public Edge(Graph<N, Edge<N>> graph, N neighbour) {
            super(graph, neighbour);
        }
    }

    public static class WeightedEdge<N, W extends Comparable<W>>
            extends AbstractEdge<N, WeightedEdge<N, W>>
            implements Comparable<W>, Cloneable {

        @Getter @Setter
        protected W weight;

        public WeightedEdge(Graph<N, WeightedEdge<N, W>> graph, N neighbour, W weight) {
            super(graph, neighbour);
            this.weight = weight;
        }

        @Override
        public WeightedEdge<N, W> clone() {
            try {
                //noinspection unchecked
                return (WeightedEdge<N, W>) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Unable to clone " + this, e);
            }
        }

        @Override
        public int compareTo(W o) {
            return weight.compareTo(o);
        }

    }

    public static class WeightedRouteBuilder<N, W extends Comparable<W>>  {
        @Getter
        private final Graph<N, WeightedEdge<N, W>> graph;
        private final N source;

        public WeightedRouteBuilder() {
            this(new Graph<>(), null);
        }

        public WeightedRouteBuilder(Graph<N, WeightedEdge<N, W>> graph) {
            this(graph, null);
        }

        public WeightedRouteBuilder(Graph<N, WeightedEdge<N, W>> graph, N source) {
            this.graph = graph;
            this.source = source;
        }

        public WeightedRouteBuilder<N, W> from(N node) {
            return new WeightedRouteBuilder<>(graph, node);
        }

        public WeightedRouteBuilder<N, W> through(N node, W weight) {
            WeightedEdge<N, W> edge = new WeightedEdge<>(graph, node, weight);
            graph.add(source, edge);
            return from(node);
        }

        public WeightedRouteBuilder<N, W> to(N node, W weight) {
            graph.add(source, new WeightedEdge<>(graph, node, weight));
            return this;
        }
    }

    public static class GraphBuilder<N>  {
        @Getter
        private final Graph<N, Edge<N>> graph;
        private final N source;

        public GraphBuilder() {
            this(new Graph<>(), null);
        }

        public GraphBuilder(Graph<N, Edge<N>> graph) {
            this(graph, null);
        }

        public GraphBuilder(Graph<N, Edge<N>> graph, N source) {
            this.graph = graph;
            this.source = source;
        }

        public GraphBuilder<N> from(N node) {
            return new GraphBuilder<>(graph, node);
        }

        public GraphBuilder<N> and(N node) {
            Edge<N> edge = new Edge<>(graph, node);
            graph.add(source, edge);
            return from(node);
        }

        public GraphBuilder<N> to(N node) {
            graph.add(source, new Edge<>(graph, node));
            return this;
        }
    }

    @Data
    @NoArgsConstructor
    public static class Transition<V> {
        protected V destination;
        protected V origin;

        public Transition(V destination, V origin) {
            this.destination = destination;
            this.origin = origin;
        }

        @Override
        public String toString() {
            if (origin == null)
                return String.format("%s", destination);
            return String.format("%s >> %s", origin, destination);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class EvaluatedTransition<V, W> extends Transition<V> {
        protected W cost;

        public EvaluatedTransition(V destination, V origin, W cost) {
            super(destination, origin);
            this.cost = cost;
        }

        @Override
        public String toString() {
            if (origin == null)
                return String.format("%s", destination);
            return String.format("%s >> %s: %s", origin, destination, cost);
        }
    }

}
