package org.zhupanovdm.pathfind;

import org.junit.jupiter.api.Test;
import org.zhupanovdm.graph.WeightedDirectedGraph;

class SalesmanEagerTest {

    @Test
    public void testBasic() {
        // TODO: Test design

        WeightedDirectedGraph<String, Integer> graph = new WeightedDirectedGraph<String, Integer>()
                .from("START").through("B", 2).to("A", 3).to("END", 5)
                .from("START").through("A", 6).to("END", 1)
                .getGraph();

        PathFinder.Int<String> path = new SalesmanEager<>(graph);
        path.calcFrom("START").printTo("END");

    }

}