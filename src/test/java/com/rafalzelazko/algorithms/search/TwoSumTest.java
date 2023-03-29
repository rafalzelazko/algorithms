package com.rafalzelazko.algorithms.search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.search.TwoSum.Pair;
import static com.rafalzelazko.algorithms.search.TwoSum.collectPairs;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoSumTest {
    @Test
    public void collectPairs__typicalCase() {
        int[] array = new int[] {5, 6, 7, -2, 6, 14, 5};
        List<Pair> correct = List.of(new Pair(0, 2), new Pair(1, 4), new Pair(3, 5), new Pair(2, 6));
        List<Pair> collected = collectPairs(array, 12);
        assertEquals(correct, collected);
    }

    @Test
    public void collectPairs__singleItem() {
        int[] array = new int[] {1};
        List<Pair> collected = collectPairs(array, 1);
        assertEquals(List.of(), collected);
    }

    @Test
    public void collectPairs__noItems() {
        int[] array = new int[] {};
        List<Pair> collected = collectPairs(array, 1);
        assertEquals(List.of(), collected);
    }
}
