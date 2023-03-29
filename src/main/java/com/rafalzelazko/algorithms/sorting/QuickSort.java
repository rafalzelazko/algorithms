package com.rafalzelazko.algorithms.sorting;

public class QuickSort {
    /**
     * Sorts an integer array using quick sort algorithm
     * <ul>
     * <li> The array must not be null
     * <li> The indices must stay within the array bounds or be in non-ascending order
     * </ul>
     *
     * @param array  the integer array
     * @param start  the index of the first item to consider
     * @param finish the index of the last item to consider
     */
    public static void quickSort(int[] array, int start, int finish) {
        if (start < finish) {
            int partitionIndex = partition(array, start, finish);
            quickSort(array, start, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, finish);
        }
    }

    private static int partition(int[] array, int start, int finish) {
        int partitionIndex = start;
        for (int current = start; current < finish; current++) {
            if (array[current] < array[finish]) {
                swap(array, partitionIndex, current);
                partitionIndex++;
            }
        }
        swap(array, partitionIndex, finish);
        return partitionIndex;
    }

    private static void swap(int[] array, int first, int second) {
        int carrier = array[first];
        array[first] = array[second];
        array[second] = carrier;
    }
}
