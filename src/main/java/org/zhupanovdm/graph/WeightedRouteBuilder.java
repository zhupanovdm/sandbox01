package org.zhupanovdm.graph;

import lombok.Getter;

public class WeightedRouteBuilder<N, W extends Comparable<W>> {
    @Getter
    private final Graph.Weighted<N, W> graph;
    private final N source;

    public WeightedRouteBuilder() {
        this(new Graph.Weighted<>(), null);
    }

    public WeightedRouteBuilder(Graph.Weighted<N, W> graph) {
        this(graph, null);
    }

    public WeightedRouteBuilder(Graph.Weighted<N, W> graph, N source) {
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
