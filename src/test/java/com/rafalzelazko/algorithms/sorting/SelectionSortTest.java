package com.rafalzelazko.algorithms.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SelectionSortTest {
    @Test
    public void selectionSort__typicalCase() {
        int[] array = new int[] {6, 2, 2, -5, 13, -1, 7};
        int[] correct = new int[] {-5, -1, 2, 2, 6, 7, 13};
        SelectionSort.selectionSort(array);
        assertArrayEquals(correct, array);
    }

    @Test
    public void selectionSort__singleItem() {
        int[] array = new int[] {1};
        SelectionSort.selectionSort(array);
        assertArrayEquals(new int[] {1}, array);
    }

    @Test
    public void selectionSort__noItems() {
        int[] array = new int[] {};
        SelectionSort.selectionSort(array);
        assertArrayEquals(new int[] {}, array);
    }
}
