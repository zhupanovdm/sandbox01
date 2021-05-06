package org.zhupanovdm;

import org.junit.jupiter.api.Test;
import org.zhupanovdm.graph.Graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadthFirstSearchTest {

    @Test
    public void testBasic() {
        Graph.Basic<String> graph = new Graph.Basic<String>()
                .from("me").through("Bob").to("Anuj")
                .from("me").through("Alice").to("Peggy")
                .from("Bob").to("Peggy")
                .from("me").through("Claire").to("Tom")
                .from("Claire").to("John")
                .getGraph();

        BreadthFirstSearch<String> bws = new BreadthFirstSearch<>(graph);
        assertEquals("Peggy", bws.find("me", "Peggy"::equals));
    }

}