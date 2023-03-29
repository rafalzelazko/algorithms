package com.rafalzelazko.algorithms.sorting;

public class CountingSort {
    /**
     * Sorts an integer array using counting sort algorithm
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the integer array
     */
    public static void countingSort(int[] array) {
        if (array.length == 0) return;

        int smallest = Integer.MAX_VALUE;
        int largest = Integer.MIN_VALUE;

        for (int current : array) {
            smallest = Math.min(current, smallest);
            largest = Math.max(current, largest);
        }

        int[] counting = new int[largest - smallest + 1];
        int[] input = array.clone();

        for (int current : array)
            counting[current - smallest]++;

        for (int index = 1; index < counting.length; index++)
            counting[index] += counting[index - 1];

        for (int current : input) {
            int destination = counting[current - smallest] - 1;
            array[destination] = current;
            counting[current - smallest]--;
        }
    }
}
