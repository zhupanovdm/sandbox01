package org.zhupanovdm;

import org.junit.jupiter.api.Test;
import org.zhupanovdm.graph.WeightedDirectedGraph;
import org.zhupanovdm.pathfind.BellmanFord;
import org.zhupanovdm.pathfind.PathFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BellmanFordTest {

    @Test
    public void testBasic() {
        WeightedDirectedGraph<String, Integer> graph = new WeightedDirectedGraph<String, Integer>()
                .from("START").through("B", 2).to("A", 3).to("END", 5)
                .from("START").through("A", 6).to("END", 1)
                .getGraph();

        PathFinder<String, Integer> bellmanFord = new BellmanFord<>(graph).calcFrom("START").printTo("END");
        assertEquals(6, bellmanFord.getCosts().get("END"));
    }

}