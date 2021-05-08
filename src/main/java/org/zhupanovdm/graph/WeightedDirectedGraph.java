package org.zhupanovdm.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WeightedDirectedGraph<N, W> {
    private final Map<N, Map<N, W>> data = new HashMap<>();

    public void addEdge(N src, N dest, W weight) {
        data.computeIfAbsent(src, node -> new HashMap<>()).put(dest, weight);
    }

    public Map<N, W> edgesOf(N node) {
        return Collections.unmodifiableMap(data.getOrDefault(node, Collections.emptyMap()));
    }

    public WeightedRouteBuilder<N, W> from(N src) {
        return new WeightedRouteBuilder<>(this, src);
    }

}
