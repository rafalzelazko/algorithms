package com.rafalzelazko.algorithms.sorting;

public class SelectionSort {
    /**
     * Sorts an integer array using selection sort algorithm
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the integer array
     */
    public static void selectionSort(int[] array) {
        for (int count = 0; count < array.length; count++) {
            int selected = count;
            for (int index = count + 1; index < array.length; index++) {
                if (array[index] < array[selected]) {
                    selected = index;
                }
            }
            int carrier = array[selected];
            array[selected] = array[count];
            array[count] = carrier;
        }
    }
}
