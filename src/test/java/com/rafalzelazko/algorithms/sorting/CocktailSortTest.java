package com.rafalzelazko.algorithms.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CocktailSortTest {
    @Test
    public void cocktailSort__typicalCase() {
        int[] array = new int[] {6, 2, 2, -5, 13, -1, 7};
        int[] correct = new int[] {-5, -1, 2, 2, 6, 7, 13};
        CocktailSort.cocktailSort(array);
        assertArrayEquals(correct, array);
    }

    @Test
    public void cocktailSort__singleItem() {
        int[] array = new int[] {1};
        CocktailSort.cocktailSort(array);
        assertArrayEquals(new int[] {1}, array);
    }

    @Test
    public void cocktailSort__noItems() {
        int[] array = new int[] {};
        CocktailSort.cocktailSort(array);
        assertArrayEquals(new int[] {}, array);
    }
}
