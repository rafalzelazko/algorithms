package com.rafalzelazko.algorithms.combinatorial;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubsetSumTest {
    @Test
    public void subsetSum__typicalCase() {
        int[] array = new int[] {5, -1, 2};
        Set<List<Integer>> correct = Set.of(List.of(0, 1));
        Set<List<Integer>> solutions = SubsetSum.subsetSum(array, 4);
        assertEquals(correct, solutions);
    }

    @Test
    public void subsetSum__negativeTarget() {
        int[] array = new int[] {5, -1, 2};
        Set<List<Integer>> correct = Set.of(List.of(1));
        Set<List<Integer>> solutions = SubsetSum.subsetSum(array, -1);
        assertEquals(correct, solutions);
    }

    @Test
    public void subsetSum__zeroTarget() {
        int[] array = new int[] {5, -1, 2};
        Set<List<Integer>> correct = Set.of(List.of());
        Set<List<Integer>> solutions = SubsetSum.subsetSum(array, 0);
        assertEquals(correct, solutions);
    }

    @Test
    public void subsetSum__noSolutions() {
        int[] array = new int[] {5, -1, 2};
        Set<List<Integer>> correct = Set.of();
        Set<List<Integer>> solutions = SubsetSum.subsetSum(array, 3);
        assertEquals(correct, solutions);
    }

    @Test
    public void subsetSum__repeatedValue() {
        int[] array = new int[] {1, 2, 1};
        Set<List<Integer>> correct = Set.of(List.of(0, 2), List.of(1));
        Set<List<Integer>> solutions = SubsetSum.subsetSum(array, 2);
        assertEquals(correct, solutions);
    }

    @Test
    public void subsetSum__zeroValue() {
        int[] array = new int[] {1, 2, 0};
        Set<List<Integer>> correct = Set.of(List.of(0), List.of(0, 2));
        Set<List<Integer>> solutions = SubsetSum.subsetSum(array, 1);
        assertEquals(correct, solutions);
    }

    @Test
    public void subsetSum__singleItem() {
        int[] array = new int[] {-3};
        Set<List<Integer>> correct = Set.of(List.of(0));
        Set<List<Integer>> solutions = SubsetSum.subsetSum(array, -3);
        assertEquals(correct, solutions);
    }

    @Test
    public void subsetSum__noItems() {
        int[] array = new int[] {};
        Set<List<Integer>> correct = Set.of(List.of());
        Set<List<Integer>> solutions = SubsetSum.subsetSum(array, 0);
        assertEquals(correct, solutions);
    }
}
