package org.zhupanovdm.graph;

public class Edge<N> extends AbstractEdge<N, Edge<N>> {
    public Edge(Graph<N, Edge<N>> graph, N neighbour) {
        super(graph, neighbour);
    }
}
