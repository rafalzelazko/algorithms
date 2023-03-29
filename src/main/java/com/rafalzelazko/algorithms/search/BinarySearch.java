package com.rafalzelazko.algorithms.search;

public class BinarySearch {
    /**
     * Finds the specified integer in a sorted array using binary search
     * <ul>
     * <li> The array must not be null
     * <li> The array must be sorted in ascending order
     * </ul>
     *
     * @param array  the integer array
     * @param target the target
     * @return the index of the first occurrence of the target or -1 if not present
     */
    public static int binarySearch(int[] array, int target) {
        int start = 0;
        int finish = array.length - 1;

        while (start < finish) {
            int middle = (start + finish) / 2;

            if (array[middle] < target) {
                start = middle + 1;
            } else {
                finish = middle;
            }
        }

        if (array.length != 0 && array[start] == target)
            return start;

        return -1;
    }
}
