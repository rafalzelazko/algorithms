package com.rafalzelazko.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DijkstraTest {
    @Test
    public void dijkstra__typicalCase() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 3),
            List.of(2),
            List.of(3),
            List.of(1),
            List.of()
        );
        int[][] costs = new int[][] {
            new int[] {0, 5, Integer.MAX_VALUE, 2, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 0, 4, Integer.MAX_VALUE, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        Dijkstra.Solution solution = Dijkstra.dijkstra(adjacency, costs, 0);
        assertNotNull(solution);
        assertArrayEquals(new int[] {0, 3, 7, 2, Integer.MAX_VALUE}, solution.distances());
        assertArrayEquals(new int[] {0, 3, 1, 0, Integer.MAX_VALUE}, solution.previous());
    }

    @Test
    public void dijkstra__singleItem() {
        List<List<Integer>> adjacency = List.of(List.of());
        int[][] costs = new int[][] {new int[] {0}};
        Dijkstra.Solution solution = Dijkstra.dijkstra(adjacency, costs, 0);
        assertNotNull(solution);
        assertArrayEquals(new int[] {0}, solution.distances());
        assertArrayEquals(new int[] {0}, solution.previous());
    }
}
