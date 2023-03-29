package com.rafalzelazko.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MColouringTest {
    @Test
    public void checkSolution__typicalCase__correct() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 4),
            List.of(0, 2),
            List.of(1, 3),
            List.of(2, 4),
            List.of(3, 0),
            List.of()
        );
        int[] colours = new int[] {0, 1, 0, 1, 2, 0};
        assertTrue(MColouring.checkSolution(adjacency, colours, 3));
    }

    @Test
    public void checkSolution__typicalCase__incorrect() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 2),
            List.of(2, 0),
            List.of(0, 1)
        );
        int[] colours = new int[] {0, 1, 0};
        assertFalse(MColouring.checkSolution(adjacency, colours, 2));
    }

    @Test
    public void checkSolution__singleItem__correct() {
        List<List<Integer>> adjacency = List.of(List.of());
        int[] colours = new int[] {5};
        assertTrue(MColouring.checkSolution(adjacency, colours, 6));
    }

    @Test
    public void checkSolution__singleItem__incorrect() {
        List<List<Integer>> adjacency = List.of(List.of());
        int[] colours = new int[] {0};
        assertFalse(MColouring.checkSolution(adjacency, colours, 0));
    }

    @Test
    public void checkSolution__noItems() {
        List<List<Integer>> adjacency = List.of();
        int[] colours = new int[] {};
        assertTrue(MColouring.checkSolution(adjacency, colours, 0));
    }

    @Test
    public void mColouring__typicalCase__solutionPresent() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 4),
            List.of(0, 2),
            List.of(1, 3),
            List.of(2, 4),
            List.of(3, 0),
            List.of()
        );
        int[] colours = MColouring.mColouring(adjacency, 3);
        assertTrue(MColouring.checkSolution(adjacency, colours, 3));
    }

    @Test
    public void mColouring__typicalCase__noSolutions() {
        List<List<Integer>> adjacency = List.of(
            List.of(1, 2),
            List.of(2, 0),
            List.of(0, 1)
        );
        assertNull(MColouring.mColouring(adjacency, 2));
    }

    @Test
    public void mColouring__singleItem__solutionPresent() {
        List<List<Integer>> adjacency = List.of(List.of());
        int[] colours = MColouring.mColouring(adjacency, 1);
        assertTrue(MColouring.checkSolution(adjacency, colours, 1));
    }

    @Test
    public void mColouring__singleItem__noSolutions() {
        List<List<Integer>> adjacency = List.of(List.of());
        assertNull(MColouring.mColouring(adjacency, 0));
    }

    @Test
    public void mColouring__noItems() {
        List<List<Integer>> adjacency = List.of();
        int[] colours = MColouring.mColouring(adjacency, 0);
        assertTrue(MColouring.checkSolution(adjacency, colours, 0));
    }
}
