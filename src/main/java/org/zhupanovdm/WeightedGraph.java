package org.zhupanovdm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

public class WeightedGraph<V, W> {
    private final Map<V, Set<EdgeVector<V, W>>> data = new HashMap<>();

    public WeightedGraph<V, W> edge(V src, EdgeVector<V, W> edgeVector) {
        Set<EdgeVector<V, W>> edges = data.computeIfAbsent(src, v -> new HashSet<>());
        edges.remove(edgeVector);
        edges.add(edgeVector);
        return this;
    }

    public WeightedGraph<V, W> edge(V src, V dest, W weight) {
        return edge(src, new EdgeVector<>(dest, weight));
    }

    public Set<EdgeVector<V, W>> neighbours(V v) {
        return Collections.unmodifiableSet(data.getOrDefault(v, Collections.emptySet()));
    }

    @Data
    @EqualsAndHashCode(of = "vertex")
    public static class EdgeVector<V, W> implements Cloneable {
        private final V vertex;
        private W weight;

        public EdgeVector(V vertex, W weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public EdgeVector<V, W> clone() {
            try {
                //noinspection unchecked
                return (EdgeVector<V, W>) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Unable to clone " + this, e);
            }
        }
    }

}
