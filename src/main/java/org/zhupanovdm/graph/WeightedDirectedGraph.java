package org.zhupanovdm.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class WeightedDirectedGraph<N, W> {
    private final Map<N, Map<N, W>> data = new HashMap<>();

    public void addEdge(N src, N dest, W weight) {
        data.computeIfAbsent(src, node -> new HashMap<>()).put(dest, weight);
    }

    public Map<N, W> edgesOf(N node) {
        return Collections.unmodifiableMap(data.getOrDefault(node, Collections.emptyMap()));
    }

    public W getWeight(N src, N dest) {
        Map<N, W> edges = data.get(src);
        if (edges == null)
            return null;
        return edges.get(dest);
    }

    public WeightedRouteBuilder<N, W> from(N src) {
        return new WeightedRouteBuilder<>(this, src);
    }

    public void forEachEdge(BiConsumer<N[], W> action) {
        data.keySet().forEach(src ->
            data.get(src).forEach((dest, weight) -> {
                //noinspection unchecked
                N[] nodes = (N[]) new Object[2];
                nodes[0] = src;
                nodes[1] = dest;
                action.accept(nodes, weight);
            })
        );
    }

}
