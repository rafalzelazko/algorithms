package com.rafalzelazko.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DistanceCollectorTest {
    @Test
    public void collectDistances__typicalCase() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 3),
            List.of(2, 3),
            List.of(),
            List.of(4),
            List.of(),
            List.of()
        );
        int[] distances = DistanceCollector.collectDistances(adjacency, 0);
        assertArrayEquals(new int[] {0, 1, 2, 1, 2, Integer.MAX_VALUE}, distances);
    }

    @Test
    public void collectDistances__singleItem() {
        List<List<Integer>> adjacency = List.of(List.of());
        int[] distances = DistanceCollector.collectDistances(adjacency, 0);
        assertArrayEquals(new int[] {0}, distances);
    }
}
