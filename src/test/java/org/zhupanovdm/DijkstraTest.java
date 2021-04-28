package org.zhupanovdm;

import org.junit.jupiter.api.Test;
import org.zhupanovdm.graph.EvaluatedTransition;
import org.zhupanovdm.graph.Graph;
import org.zhupanovdm.graph.WeightedEdge;
import org.zhupanovdm.graph.WeightedRouteBuilder;

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
        List<EvaluatedTransition<String, Double>> result = dijkstra.calc("START", "END");
        for (EvaluatedTransition<String, Double> transition : result)
            System.out.println(transition);

        assertEquals(new EvaluatedTransition<>("END", "A", 6d), result.get(2));
    }

}