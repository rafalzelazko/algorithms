package com.rafalzelazko.algorithms.text;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

public class LongestCommonSubsequence {
    /**
     * Collects all longest common subsequences of two strings
     * <ul>
     * <li> The strings must not be null
     * </ul>
     *
     * @param first  the first string
     * @param second the second string
     * @return the collected subsequences
     */
    public static Set<String> findSolutions(String first, String second) {
        int firstLength = first.length();
        int secondLength = second.length();

        int[][] lengths = new int[firstLength + 1][secondLength + 1];

        for (int i = 1; i <= firstLength; i++) {
            for (int j = 1; j <= secondLength; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    lengths[i][j] = lengths[i - 1][j - 1] + 1;
                } else {
                    lengths[i][j] = Math.max(lengths[i - 1][j], lengths[i][j - 1]);
                }
            }
        }

        Set<String> solutions = new HashSet<>();
        Stack<Square> stack = new Stack<>();

        List<Character> selected = new ArrayList<>();
        int i = firstLength;
        int j = secondLength;

        if (lengths[i][j] > 0)
            stack.push(new Square(selected, i, j));

        while (!stack.isEmpty()) {
            Square square = stack.pop();
            selected = square.selected;
            i = square.i;
            j = square.j;

            if (lengths[i][j] == 0) {
                String solution = selected.stream()
                    .map(String::valueOf).collect(Collectors.joining());
                solutions.add(solution);
                continue;
            }

            if (lengths[i][j] == lengths[i - 1][j])
                stack.push(new Square(selected, i - 1, j));

            if (lengths[i][j] == lengths[i][j - 1])
                stack.push(new Square(selected, i, j - 1));

            if (first.charAt(i - 1) == second.charAt(j - 1)) {
                selected = new ArrayList<>(selected);
                selected.add(0, first.charAt(i - 1));
                stack.push(new Square(selected, i - 1, j - 1));
            }
        }

        return solutions;
    }

    @AllArgsConstructor
    private static class Square {
        private List<Character> selected;
        private int i;
        private int j;
    }
}
