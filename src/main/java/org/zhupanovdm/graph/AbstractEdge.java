package org.zhupanovdm.graph;

import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(of = "destination")
public abstract class AbstractEdge<N, E extends AbstractEdge<N, E>> {
    protected final Graph<N, E> graph;
    protected N source;
    protected final N destination;

    public AbstractEdge(Graph<N, E> graph, N destination) {
        this.graph = graph;
        this.destination = destination;
    }

    public Graph<N, E> graph() {
        return graph;
    }

    public void setSource(N source) {
        this.source = source;
    }

    public N source() {
        return source;
    }

    public N destination() {
        return destination;
    }

    public Set<E> neighbours() {
        return graph.edgesOf(destination);
    }
}
