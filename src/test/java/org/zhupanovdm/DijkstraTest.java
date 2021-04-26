package org.zhupanovdm;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraTest {

    @Test
    public void testBasic() {
        WeightedGraph<String, Integer> graph = new WeightedGraph<String, Integer>()
                .edge("START", "A", 6)
                .edge("START", "B", 2)
                .edge("A", "END", 1)
                .edge("B", "A", 3)
                .edge("B", "END", 5);

        Dijkstra<String> dijkstra = new Dijkstra<>(graph);
        List<Dijkstra.Transition<String>> result = dijkstra.calc("START", "END");
        assertEquals(new Dijkstra.Transition<>("A", "END", 6), result.get(3));
    }

}