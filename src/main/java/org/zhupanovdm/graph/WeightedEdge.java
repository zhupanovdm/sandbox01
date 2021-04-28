package org.zhupanovdm.graph;

import lombok.Getter;
import lombok.Setter;

public class WeightedEdge<N, W extends Comparable<W>>
        extends AbstractEdge<N, WeightedEdge<N, W>>
        implements Comparable<W>, Cloneable {

    @Getter
    @Setter
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
