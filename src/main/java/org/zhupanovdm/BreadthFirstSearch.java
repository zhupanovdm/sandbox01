package org.zhupanovdm;

import org.zhupanovdm.graph.Edge;
import org.zhupanovdm.graph.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

public class BreadthFirstSearch<T> {
    private final Graph<T, Edge<T>> graph;

    public BreadthFirstSearch(Graph<T, Edge<T>> graph) {
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
            for (Edge<T> edge : graph.edgesOf(current)) {
                if (!checked.contains(edge.destination()))
                    queue.add(edge.destination());
            }
        }

        return null;
    }

}
