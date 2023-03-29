package com.rafalzelazko.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BellmanFordTest {
    @Test
    public void bellmanFord__typicalCase() {
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
        BellmanFord.Solution solution = BellmanFord.bellmanFord(adjacency, costs, 0);
        assertNotNull(solution);
        assertArrayEquals(new int[] {0, 3, 7, 2, Integer.MAX_VALUE}, solution.distances());
        assertArrayEquals(new int[] {0, 3, 1, 0, Integer.MAX_VALUE}, solution.previous());
    }

    @Test
    public void bellmanFord__negativeEdge() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 2),
            List.of(),
            List.of(1)
        );
        int[][] costs = new int[][] {
            new int[] {0, 1, 2},
            new int[] {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, -3, 0}
        };
        BellmanFord.Solution solution = BellmanFord.bellmanFord(adjacency, costs, 0);
        assertNotNull(solution);
        assertArrayEquals(new int[] {0, -1, 2}, solution.distances());
        assertArrayEquals(new int[] {0, 2, 0}, solution.previous());
    }

    @Test
    public void bellmanFord__negativeCycle() {
        List<List<Integer>> adjacency = List.of(
            List.of(1),
            List.of(0)
        );
        int[][] costs = new int[][] {
            new int[] {0, 1},
            new int[] {-2, 0}
        };
        BellmanFord.Solution solution = BellmanFord.bellmanFord(adjacency, costs, 0);
        assertNull(solution);
    }

    @Test
    public void bellmanFord__singleItem() {
        List<List<Integer>> adjacency = List.of(List.of());
        int[][] costs = new int[][] {new int[] {0}};
        BellmanFord.Solution solution = BellmanFord.bellmanFord(adjacency, costs, 0);
        assertNotNull(solution);
        assertArrayEquals(new int[] {0}, solution.distances());
        assertArrayEquals(new int[] {0}, solution.previous());
    }
}
