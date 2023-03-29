package com.rafalzelazko.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {
    @Test
    public void checkSolution__typicalCase__correct() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 3),
            List.of(2, 3),
            List.of(),
            List.of(4),
            List.of(),
            List.of()
        );
        int[] order = new int[] {5, 0, 1, 3, 4, 2};
        assertTrue(TopologicalSort.checkSolution(adjacency, order));
    }

    @Test
    public void checkSolution__typicalCase__incorrect() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 3),
            List.of(2, 3),
            List.of(),
            List.of(4),
            List.of(),
            List.of()
        );
        int[] order = new int[] {5, 2, 0, 1, 3, 4};
        assertFalse(TopologicalSort.checkSolution(adjacency, order));
    }

    @Test
    public void checkSolution__singleItem() {
        List<List<Integer>> adjacency = List.of(List.of());
        int[] order = new int[] {0};
        assertTrue(TopologicalSort.checkSolution(adjacency, order));
    }

    @Test
    public void checkSolution__noItems() {
        List<List<Integer>> adjacency = List.of();
        int[] order = new int[] {};
        assertTrue(TopologicalSort.checkSolution(adjacency, order));
    }

    @Test
    public void topologicalSort__typicalCase() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 3),
            List.of(2, 3),
            List.of(),
            List.of(4),
            List.of(),
            List.of()
        );
        int[] order = TopologicalSort.topologicalSort(adjacency);
        assertNotNull(order);
        assertTrue(TopologicalSort.checkSolution(adjacency, order));
    }

    @Test
    public void topologicalSort__cycle() {
        List<List<Integer>> adjacency = List.of(
            List.of(1),
            List.of(2),
            List.of(0)
        );
        int[] order = TopologicalSort.topologicalSort(adjacency);
        assertNull(order);
    }

    @Test
    public void topologicalSort__singleItem() {
        List<List<Integer>> adjacency = List.of(List.of());
        int[] order = TopologicalSort.topologicalSort(adjacency);
        assertNotNull(order);
        assertTrue(TopologicalSort.checkSolution(adjacency, order));
    }

    @Test
    public void topologicalSort__noItems() {
        List<List<Integer>> adjacency = List.of();
        int[] order = TopologicalSort.topologicalSort(adjacency);
        assertNotNull(order);
        assertTrue(TopologicalSort.checkSolution(adjacency, order));
    }
}
