package org.zhupanovdm.graph;

import lombok.Getter;

public class RouteBuilder<N> {
    @Getter
    private final Graph.Basic<N> graph;
    private final N source;

    public RouteBuilder() {
        this(new Graph.Basic<>(), null);
    }

    public RouteBuilder(Graph.Basic<N> graph) {
        this(graph, null);
    }

    public RouteBuilder(Graph.Basic<N> graph, N source) {
        this.graph = graph;
        this.source = source;
    }

    public RouteBuilder<N> from(N node) {
        return new RouteBuilder<>(graph, node);
    }

    public RouteBuilder<N> through(N node) {
        Edge<N> edge = new Edge<>(graph, node);
        graph.add(source, edge);
        return from(node);
    }

    public RouteBuilder<N> to(N node) {
        graph.add(source, new Edge<>(graph, node));
        return this;
    }
}
