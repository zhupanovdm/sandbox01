package org.zhupanovdm;

import org.junit.jupiter.api.Test;
import org.zhupanovdm.graph.WeightedDirectedGraph;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadthFirstSearchTest {

    @Test
    public void testBasic() {
        WeightedDirectedGraph<String, Integer> graph = new WeightedDirectedGraph<String, Integer>()
                .from("me").through("Bob", 1).to("Anuj", 1)
                .from("me").through("Alice", 1).to("Peggy", 1)
                .from("Bob").to("Peggy", 1)
                .from("me").through("Claire", 1).to("Tom", 1)
                .from("Claire").to("John", 1)
                .getGraph();

        BreadthFirstSearch<String> bws = new BreadthFirstSearch<>(graph);
        assertEquals("Peggy", bws.find("me", "Peggy"::equals));
    }

}