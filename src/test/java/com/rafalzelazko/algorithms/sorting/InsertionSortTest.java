package com.rafalzelazko.algorithms.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InsertionSortTest {
    @Test
    public void insertionSort__typicalCase() {
        int[] array = new int[] {6, 2, 2, -5, 13, -1, 7};
        int[] correct = new int[] {-5, -1, 2, 2, 6, 7, 13};
        InsertionSort.insertionSort(array);
        assertArrayEquals(correct, array);
    }

    @Test
    public void insertionSort__singleItem() {
        int[] array = new int[] {1};
        InsertionSort.insertionSort(array);
        assertArrayEquals(new int[] {1}, array);
    }

    @Test
    public void insertionSort__noItems() {
        int[] array = new int[] {};
        InsertionSort.insertionSort(array);
        assertArrayEquals(new int[] {}, array);
    }
}
