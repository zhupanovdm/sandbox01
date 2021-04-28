package org.zhupanovdm.graph;

import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(of = "node")
public abstract class AbstractEdge<N, E extends AbstractEdge<N, E>> {
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
