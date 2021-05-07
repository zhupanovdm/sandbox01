package org.zhupanovdm;

import org.junit.jupiter.api.Test;
import org.zhupanovdm.graph.Graph;
import org.zhupanovdm.graph.WeightedEdge;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraTest {

    @Test
    public void testBasic() {
        Graph.Weighted<String, Double> graph = new Graph.Weighted<String, Double>()
                .from("START").through("B", 2d).to("A", 3d).to("END", 5d)
                .from("START").through("A", 6d).to("END", 1d)
                .getGraph();

        Dijkstra<String> dijkstra = new Dijkstra<>(graph);
        Map<String, WeightedEdge<String, Double>> result = dijkstra.calc("START");
        System.out.println(result);
        System.out.println("----------");
        Dijkstra.print(result, "END");


//        WeightedEdge<String, Double> end = new WeightedEdge<>(graph, "END", 6d);
//        end.setSource("A");

        //assertEquals(end, result.get(2));
    }

}