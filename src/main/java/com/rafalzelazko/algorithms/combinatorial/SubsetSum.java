package com.rafalzelazko.algorithms.combinatorial;

import lombok.AllArgsConstructor;

import java.util.*;

public class SubsetSum {
    /**
     * Collects all such subsets of the specified array that their items
     * sum up to the specified target
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array  the integer array
     * @param target the target
     * @return the collected subsets
     */
    public static Set<List<Integer>> subsetSum(int[] array, int target) {
        int smallest = 0;
        int largest = 0;

        for (int current : array) {
            if (current < 0) smallest += current;
            if (current > 0) largest += current;
        }

        if (target < smallest || largest < target)
            return new HashSet<>();

        int columns = largest - smallest + 1;
        boolean[][] matching = new boolean[array.length + 1][columns];

        for (int index = 0; index <= array.length; index++)
            matching[index][-smallest] = true;

        for (int index = 1; index <= array.length; index++) {
            int value = array[index - 1];
            for (int column = 0; column < columns; column++) {
                matching[index][column] = matching[index - 1][column];
                if (column >= value && column - value < columns && !matching[index][column])
                    matching[index][column] = matching[index - 1][column - value];
            }
        }

        Set<List<Integer>> solutions = new HashSet<>();
        Stack<Square> stack = new Stack<>();

        List<Integer> selected = new ArrayList<>();
        int index = array.length;
        int column = target - smallest;

        if (matching[index][column])
            stack.push(new Square(selected, index, column));

        while (!stack.isEmpty()) {
            Square square = stack.pop();
            selected = square.selected;
            index = square.index;
            column = square.column;

            if (index == 0 && column == -smallest)
                solutions.add(selected);

            if (index == 0) continue;
            int value = array[index - 1];

            if (matching[index - 1][column])
                stack.push(new Square(selected, index - 1, column));

            if (column >= value && column - value < columns) {
                if (matching[index - 1][column - value]) {
                    selected = new ArrayList<>(selected);
                    selected.add(0, index - 1);
                    stack.push(new Square(selected, index - 1, column - value));
                }
            }
        }

        return solutions;
    }

    @AllArgsConstructor
    private static class Square {
        private List<Integer> selected;
        private int index;
        private int column;
    }
}
