package org.zhupanovdm;

import org.zhupanovdm.graph.WeightedDirectedGraph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

public class BreadthFirstSearch<T> {
    private final WeightedDirectedGraph<T, Integer> graph;

    public BreadthFirstSearch(WeightedDirectedGraph<T, Integer> graph) {
        this.graph = graph;
    }

    public T find(T start, Predicate<T> predicate) {
        Queue<T> queue = new LinkedList<>();
        queue.add(start);

        Set<T> checked = new HashSet<>();

        T current;
        while ((current = queue.poll()) != null) {
            if (predicate.test(current))
                return current;

            checked.add(current);
            for (T node : graph.edgesOf(current).keySet()) {
                if (!checked.contains(node))
                    queue.add(node);
            }
        }

        return null;
    }

}
