package com.rafalzelazko.algorithms.search;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoSum {
    /**
     * Collects all pairs of integers in the specified array that add up to the specified target
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the integer array
     * @param sum   the target sum
     * @return the indices of the collected pairs
     */
    public static List<Pair> collectPairs(int[] array, int sum) {
        Map<Integer, List<Integer>> locations = new HashMap<>();
        List<Pair> collected = new ArrayList<>();

        for (int currentIndex = 0; currentIndex < array.length; currentIndex++) {
            final int index = currentIndex;

            if (locations.containsKey(sum - array[index]))
                locations.get(sum - array[index])
                    .forEach(location -> collected.add(new Pair(location, index)));

            if (!locations.containsKey(array[index]))
                locations.put(array[index], Stream.of(index).collect(Collectors.toList()));
            else
                locations.get(array[index]).add(index);
        }

        return collected;
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Pair {
        private int first;
        private int second;
    }
}
