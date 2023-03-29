package com.rafalzelazko.algorithms.sorting;

public class CocktailSort {
    /**
     * Sorts an integer array using cocktail sort algorithm
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the integer array
     */
    public static void cocktailSort(int[] array) {
        int start = 0;
        int finish = array.length - 1;
        boolean changed = false;

        while (true) {
            for (int index = start; index < finish; index++) {
                if (array[index] > array[index + 1]) {
                    swap(array, index, index + 1);
                    changed = true;
                }
            }
            if (!changed) return;
            changed = false;
            finish--;

            for (int index = finish; index > start; index--) {
                if (array[index] < array[index - 1]) {
                    swap(array, index, index - 1);
                    changed = true;
                }
            }
            if (!changed) return;
            changed = false;
            start++;
        }
    }

    private static void swap(int[] array, int first, int second) {
        int carrier = array[first];
        array[first] = array[second];
        array[second] = carrier;
    }
}
