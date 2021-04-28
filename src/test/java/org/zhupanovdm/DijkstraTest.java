package org.zhupanovdm;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraTest {

    @Test
    public void testBasic() {
        Graph<String, Graph.WeightedEdge<String, Double>> graph = new Graph.WeightedRouteBuilder<String, Double>()
                .from("START").through("B", 2d).to("A", 3d).to("END", 5d)
                .from("START").through("A", 6d).to("END", 1d)
                .getGraph();

        Dijkstra<String> dijkstra = new Dijkstra<>(graph);
        List<Dijkstra.Transition<String>> result = dijkstra.calc("START", "END");
        for (Dijkstra.Transition<String> transition : result)
            System.out.println(transition);

        assertEquals(new Dijkstra.Transition<>("END", "A", 6d), result.get(2));
    }

}