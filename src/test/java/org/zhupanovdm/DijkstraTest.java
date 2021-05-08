package org.zhupanovdm;

import org.junit.jupiter.api.Test;
import org.zhupanovdm.graph.WeightedDirectedGraph;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraTest {

    @Test
    public void testBasic() {
        WeightedDirectedGraph<String, Integer> graph = new WeightedDirectedGraph<String, Integer>()
                .from("START").through("B", 2).to("A", 3).to("END", 5)
                .from("START").through("A", 6).to("END", 1)
                .getGraph();

        PathFinder<String, Integer> dijkstra = new Dijkstra<>(graph).calcFrom("START").printTo("END");
        assertEquals(6, dijkstra.getCosts().get("END"));
    }

}