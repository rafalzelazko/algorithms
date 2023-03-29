package com.rafalzelazko.algorithms.sorting;

public class InsertionSort {
    /**
     * Sorts an integer array using insertion sort algorithm
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the integer array
     */
    public static void insertionSort(int[] array) {
        for (int count = 1; count < array.length; count++) {
            int considered = array[count];
            int index = count - 1;
            while (index >= 0 && array[index] > considered) {
                array[index + 1] = array[index];
                index--;
            }
            array[index + 1] = considered;
        }
    }
}
