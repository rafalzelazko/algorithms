package com.rafalzelazko.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.graph.Kruskal.Edge;
import static com.rafalzelazko.algorithms.graph.Kruskal.kruskal;
import static org.junit.jupiter.api.Assertions.*;

public class KruskalTest {
    @Test
    public void kruskal__typicalCase() {
        List<Edge> edges = List.of(
            new Edge(0, 0, 1, 1),
            new Edge(1, 0, 2, 5),
            new Edge(2, 0, 3, 6),
            new Edge(3, 0, 4, 7),
            new Edge(4, 1, 2, 2),
            new Edge(5, 2, 3, 3),
            new Edge(6, 3, 4, 4)
        );
        List<Edge> spanning = kruskal(5, edges);
        assertNotNull(spanning);
        List<Integer> identifiers = spanning.stream().map(Edge::getId).toList();
        assertEquals(List.of(0, 4, 5, 6), identifiers);
    }

    @Test
    public void kruskal__disconnectedGraph() {
        List<Edge> edges = List.of(
            new Edge(0, 1, 2, 1),
            new Edge(1, 2, 3, 2)
        );
        List<Edge> spanning = kruskal(4, edges);
        assertNull(spanning);
    }

    @Test
    public void kruskal__singleItem() {
        List<Edge> edges = List.of();
        List<Edge> spanning = kruskal(1, edges);
        assertNotNull(spanning);
        List<Integer> identifiers = spanning.stream().map(Edge::getId).toList();
        assertEquals(List.of(), identifiers);
    }

    @Test
    public void kruskal__noItems() {
        List<Edge> edges = List.of();
        List<Edge> spanning = kruskal(0, edges);
        assertNotNull(spanning);
        List<Integer> identifiers = spanning.stream().map(Edge::getId).toList();
        assertEquals(List.of(), identifiers);
    }
}
