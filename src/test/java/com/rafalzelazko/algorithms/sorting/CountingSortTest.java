package com.rafalzelazko.algorithms.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CountingSortTest {
    @Test
    public void countingSort__typicalCase() {
        int[] array = new int[] {6, 2, 2, -5, 13, -1, 7};
        int[] correct = new int[] {-5, -1, 2, 2, 6, 7, 13};
        CountingSort.countingSort(array);
        assertArrayEquals(correct, array);
    }

    @Test
    public void countingSort__singleItem() {
        int[] array = new int[] {1};
        CountingSort.countingSort(array);
        assertArrayEquals(new int[] {1}, array);
    }

    @Test
    public void countingSort__noItems() {
        int[] array = new int[] {};
        CountingSort.countingSort(array);
        assertArrayEquals(new int[] {}, array);
    }
}
