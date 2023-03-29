package com.rafalzelazko.algorithms.combinatorial;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnapsackTest {
    @Test
    public void knapsack__typicalCase() {
        int[] values = new int[] {3, 6, 2, 4, 5};
        int[] weights = new int[] {2, 5, 1, 3, 4};
        Set<List<Integer>> correct = Set.of(List.of(0, 2, 4));
        Set<List<Integer>> solutions = Knapsack.knapsack(7, values, weights);
        assertEquals(correct, solutions);
    }

    @Test
    public void knapsack__zeroCapacity() {
        int[] values = new int[] {3, 6, 2, 4, 5};
        int[] weights = new int[] {2, 5, 1, 3, 4};
        Set<List<Integer>> correct = Set.of(List.of());
        Set<List<Integer>> solutions = Knapsack.knapsack(0, values, weights);
        assertEquals(correct, solutions);
    }

    @Test
    public void knapsack__repeatedValue() {
        int[] values = new int[] {1, 2, 1};
        int[] weights = new int[] {1, 2, 1};
        Set<List<Integer>> correct = Set.of(List.of(0, 2), List.of(1));
        Set<List<Integer>> solutions = Knapsack.knapsack(2, values, weights);
        assertEquals(correct, solutions);
    }

    @Test
    public void knapsack__singleItem() {
        int[] values = new int[] {1};
        int[] weights = new int[] {1};
        Set<List<Integer>> correct = Set.of(List.of(0));
        Set<List<Integer>> solutions = Knapsack.knapsack(2, values, weights);
        assertEquals(correct, solutions);
    }

    @Test
    public void knapsack__noItems() {
        int[] values = new int[] {};
        int[] weights = new int[] {};
        Set<List<Integer>> correct = Set.of(List.of());
        Set<List<Integer>> solutions = Knapsack.knapsack(2, values, weights);
        assertEquals(correct, solutions);
    }
}
