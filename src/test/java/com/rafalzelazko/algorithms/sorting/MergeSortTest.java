package com.rafalzelazko.algorithms.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {
    @Test
    public void mergeSort__typicalCase() {
        int[] array = new int[] {6, 2, 2, -5, 13, -1, 7};
        int[] correct = new int[] {-5, -1, 2, 2, 6, 7, 13};
        MergeSort.mergeSort(array, 0, array.length - 1);
        assertArrayEquals(correct, array);
    }

    @Test
    public void mergeSort__singleItem() {
        int[] array = new int[] {1};
        MergeSort.mergeSort(array, 0, 0);
        assertArrayEquals(new int[] {1}, array);
    }

    @Test
    public void mergeSort__noItems() {
        int[] array = new int[] {};
        MergeSort.mergeSort(array, 0, 0);
        assertArrayEquals(new int[] {}, array);
    }
}
