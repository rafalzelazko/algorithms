package com.rafalzelazko.algorithms.combinatorial;

import lombok.AllArgsConstructor;

import java.util.*;

public class Knapsack {
    /**
     * Collects all such subsets of the specified items that their total value is the largest
     * while their total weight does not exceed the specified capacity
     * <ul>
     * <li> The capacity must be non-negative
     * <li> The arrays must not be null
     * <li> All weights must be positive
     * <li> The arrays must have the same length
     * <li> The arrays must match one another
     * </ul>
     *
     * @param capacity the maximum total weight of selected items
     * @param values   the values of the items
     * @param weights  the weights of the items
     * @return the collected subsets
     */
    public static Set<List<Integer>> knapsack(int capacity, int[] values, int[] weights) {
        int[][] profit = new int[values.length + 1][capacity + 1];

        for (int index = 1; index <= values.length; index++) {
            int value = values[index - 1];
            int weight = weights[index - 1];

            for (int size = 1; size <= capacity; size++) {
                if (size < weight) {
                    profit[index][size] = profit[index - 1][size];
                } else {
                    profit[index][size] = Math.max(
                        profit[index - 1][size - weight] + value,
                        profit[index - 1][size]
                    );
                }
            }
        }

        Set<List<Integer>> solutions = new HashSet<>();
        Stack<Square> stack = new Stack<>();

        List<Integer> selected = new ArrayList<>();
        int score = 0;
        int index = values.length;
        int size = capacity;

        stack.push(new Square(selected, score, index, size));

        while (!stack.isEmpty()) {
            Square square = stack.pop();
            selected = square.selected;
            score = square.score;
            index = square.index;
            size = square.size;

            if (index == 0 && score == profit[values.length][capacity])
                solutions.add(selected);

            if (index == 0) continue;
            int value = values[index - 1];
            int weight = weights[index - 1];

            stack.push(new Square(selected, score, index - 1, size));

            if (size >= weight) {
                if (profit[index][size] == profit[index - 1][size - weight] + value) {
                    selected = new ArrayList<>(selected);
                    selected.add(0, index - 1);

                    score = score + value;
                    size = size - weight;

                    stack.push(new Square(selected, score, index - 1, size));
                }
            }
        }

        return solutions;
    }

    @AllArgsConstructor
    private static class Square {
        private List<Integer> selected;
        private int score;
        private int index;
        private int size;
    }
}
