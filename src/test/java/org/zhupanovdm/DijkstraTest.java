package org.zhupanovdm;

import org.junit.jupiter.api.Test;
import org.zhupanovdm.graph.WeightedTransition;
import org.zhupanovdm.graph.Graph;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraTest {

    @Test
    public void testBasic() {
        Graph.Weighted<String, Double> graph = new Graph.Weighted<String, Double>()
                .from("START").through("B", 2d).to("A", 3d).to("END", 5d)
                .from("START").through("A", 6d).to("END", 1d)
                .getGraph();

        Dijkstra<String> dijkstra = new Dijkstra<>(graph);
        List<WeightedTransition<String, Double>> result = dijkstra.calc("START", "END");
        for (WeightedTransition<String, Double> transition : result)
            System.out.println(transition);

        assertEquals(new WeightedTransition<>("END", "A", 6d), result.get(2));
    }

}