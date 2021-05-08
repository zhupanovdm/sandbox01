package org.zhupanovdm.graph;

import lombok.Getter;

public class WeightedRouteBuilder<N, W> {
    @Getter
    private final WeightedDirectedGraph<N, W> graph;
    private final N source;

    public WeightedRouteBuilder() {
        this(new WeightedDirectedGraph<>(), null);
    }

    public WeightedRouteBuilder(WeightedDirectedGraph<N, W> graph) {
        this(graph, null);
    }

    public WeightedRouteBuilder(WeightedDirectedGraph<N, W> graph, N source) {
        this.graph = graph;
        this.source = source;
    }

    public WeightedRouteBuilder<N, W> from(N node) {
        return new WeightedRouteBuilder<>(graph, node);
    }

    public WeightedRouteBuilder<N, W> through(N node, W weight) {
        graph.addEdge(source, node, weight);
        return from(node);
    }

    public WeightedRouteBuilder<N, W> to(N node, W weight) {
        graph.addEdge(source, node, weight);
        return this;
    }
}
