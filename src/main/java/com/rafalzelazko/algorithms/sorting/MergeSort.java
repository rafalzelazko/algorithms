package com.rafalzelazko.algorithms.sorting;

import java.util.Arrays;

public class MergeSort {
    /**
     * Sorts an integer array using merge sort algorithm
     * <ul>
     * <li> The array must not be null
     * <li> The indices must stay within the array bounds or be in non-ascending order
     * </ul>
     *
     * @param array  the integer array
     * @param start  the index of the first item to consider
     * @param finish the index of the last item to consider
     */
    public static void mergeSort(int[] array, int start, int finish) {
        if (start < finish) {
            int middle = (start + finish) / 2;
            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, finish);
            merge(array, start, middle, finish);
        }
    }

    private static void merge(int[] array, int start, int middle, int finish) {
        int[] first = Arrays.copyOfRange(array, start, middle + 1);
        int[] second = Arrays.copyOfRange(array, middle + 1, finish + 1);

        int firstIndex = 0;
        int secondIndex = 0;
        int arrayIndex = start;

        while (firstIndex < first.length && secondIndex < second.length) {
            if (first[firstIndex] <= second[secondIndex]) {
                array[arrayIndex] = first[firstIndex];
                firstIndex++;
            } else {
                array[arrayIndex] = second[secondIndex];
                secondIndex++;
            }
            arrayIndex++;
        }

        while (firstIndex < first.length) {
            array[arrayIndex] = first[firstIndex];
            firstIndex++;
            arrayIndex++;
        }

        while (secondIndex < second.length) {
            array[arrayIndex] = second[secondIndex];
            secondIndex++;
            arrayIndex++;
        }
    }
}
