package com.rafalzelazko.algorithms.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FloydWarshallTest {
    @Test
    public void floydWarshall__typicalCase() {
        int[][] costs = new int[][] {
            new int[] {0, 5, Integer.MAX_VALUE, 2, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 0, 4, Integer.MAX_VALUE, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        int[][] distances = new int[][] {
            new int[] {0, 3, 7, 2, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 0, 4, 7, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 4, 0, 3, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 1, 5, 0, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        int[][] previous = new int[][] {
            new int[] {0, 3, 1, 0, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 1, 1, 2, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 3, 2, 2, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 3, 1, 3, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 4}
        };
        FloydWarshall.Solution solution = FloydWarshall.floydWarshall(costs);
        assertNotNull(solution);
        assertArrayEquals(distances, solution.distances());
        assertArrayEquals(previous, solution.previous());
    }

    @Test
    public void floydWarshall__negativeEdge() {
        int[][] costs = new int[][] {
            new int[] {0, 1, 2},
            new int[] {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, -3, 0}
        };
        int[][] distances = new int[][] {
            new int[] {0, -1, 2},
            new int[] {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, -3, 0}
        };
        int[][] previous = new int[][] {
            new int[] {0, 2, 0},
            new int[] {Integer.MAX_VALUE, 1, Integer.MAX_VALUE},
            new int[] {Integer.MAX_VALUE, 2, 2}
        };
        FloydWarshall.Solution solution = FloydWarshall.floydWarshall(costs);
        assertNotNull(solution);
        assertArrayEquals(distances, solution.distances());
        assertArrayEquals(previous, solution.previous());
    }

    @Test
    public void floydWarshall__negativeCycle() {
        int[][] costs = new int[][] {
            new int[] {0, 1},
            new int[] {-2, 0}
        };
        FloydWarshall.Solution solution = FloydWarshall.floydWarshall(costs);
        assertNull(solution);
    }

    @Test
    public void floydWarshall__singleItem() {
        int[][] costs = new int[][] {new int[] {0}};
        FloydWarshall.Solution solution = FloydWarshall.floydWarshall(costs);
        assertNotNull(solution);
        assertArrayEquals(new int[][] {new int[] {0}}, solution.distances());
        assertArrayEquals(new int[][] {new int[] {0}}, solution.previous());
    }

    @Test
    public void floydWarshall__noItems() {
        int[][] costs = new int[][] {};
        FloydWarshall.Solution solution = FloydWarshall.floydWarshall(costs);
        assertNotNull(solution);
        assertArrayEquals(new int[][] {}, solution.distances());
        assertArrayEquals(new int[][] {}, solution.previous());
    }
}
