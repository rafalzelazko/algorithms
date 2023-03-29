package com.rafalzelazko.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {
    @Test
    public void binarySearch__typicalCase__present() {
        int[] array = new int[] {1, 2, 3, 5, 5, 6, 7, 7, 7, 9};
        int index = BinarySearch.binarySearch(array, 7);
        assertEquals(6, index);
    }

    @Test
    public void binarySearch__typicalCase__notPresent() {
        int[] array = new int[] {1, 2, 3, 5, 5, 6, 7, 7, 7, 9};
        int index = BinarySearch.binarySearch(array, 4);
        assertEquals(-1, index);
    }

    @Test
    public void binarySearch__singleItem__present() {
        int[] array = new int[] {1};
        int index = BinarySearch.binarySearch(array, 1);
        assertEquals(0, index);
    }

    @Test
    public void binarySearch__singleItem__notPresent() {
        int[] array = new int[] {1};
        int index = BinarySearch.binarySearch(array, 9);
        assertEquals(-1, index);
    }

    @Test
    public void binarySearch__noItems() {
        int[] array = new int[] {};
        int index = BinarySearch.binarySearch(array, 1);
        assertEquals(-1, index);
    }
}
