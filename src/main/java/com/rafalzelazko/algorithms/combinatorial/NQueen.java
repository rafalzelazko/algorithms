package com.rafalzelazko.algorithms.combinatorial;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NQueen {
    /**
     * Presents a solution to the specified N Queen problem on a chessboard
     * <ul>
     * <li> The solution must match the length of the chessboard
     * <li> The solution must contain exactly one queen in each row
     * <li> The length must be at least 1 and at most 12
     * </ul>
     *
     * @param solution the encoded configuration of queens on the chessboard
     * @param length   the length of the chessboard
     * @return the chessboard with marked positions of the queens
     */
    public static char[][] presentSolution(long solution, int length) {
        char[][] chessboard = new char[length][length];
        int[] queens = locateQueens(solution, length);

        for (int row = 0; row < length; row++) {
            Arrays.fill(chessboard[row], '.');
            chessboard[row][queens[row]] = 'Q';
        }

        return chessboard;
    }

    /**
     * Checks a solution to the specified N Queen problem
     * <ul>
     * <li> The solution must match the length of the chessboard
     * <li> The solution must contain exactly one queen in each row
     * <li> The length must be at least 1 and at most 12
     * </ul>
     *
     * @param solution the encoded configuration of queens on a chessboard
     * @param length   the length of the chessboard
     * @return true if the solution is correct, false otherwise
     */
    public static boolean checkSolution(long solution, int length) {
        boolean[] leftDiagonals = new boolean[2 * length - 1];
        boolean[] rightDiagonals = new boolean[2 * length - 1];
        boolean[] columns = new boolean[length];

        int[] queens = locateQueens(solution, length);

        for (int row = 0; row < length; row++) {
            int leftDiagonal = row - queens[row] + length - 1;
            int rightDiagonal = row + queens[row];

            if (leftDiagonals[leftDiagonal]) return false;
            if (rightDiagonals[rightDiagonal]) return false;
            if (columns[queens[row]]) return false;

            leftDiagonals[leftDiagonal] = true;
            rightDiagonals[rightDiagonal] = true;
            columns[queens[row]] = true;
        }

        return true;
    }

    private static int[] locateQueens(long solution, int length) {
        int[] queens = new int[length];

        for (int row = length - 1; row >= 0; row--) {
            queens[row] = (int) (solution % length);
            solution = solution / length;
        }

        return queens;
    }

    /**
     * Collects all such distributions of N queens on the chessboard
     * of length N that no two queens attack each other
     * <ul>
     * <li> The length must be at least 1 and at most 12
     * </ul>
     *
     * @param length the length of the chessboard
     * @return the collected distributions
     */
    public static Set<Long> nQueen(int length) {
        boolean[] leftDiagonals = new boolean[2 * length - 1];
        boolean[] rightDiagonals = new boolean[2 * length - 1];
        boolean[] columns = new boolean[length];

        int[] queens = new int[length];
        Set<Long> solutions = new HashSet<>();

        Input input = new Input(leftDiagonals, rightDiagonals, columns, queens, solutions);
        placeQueen(input, 0);

        return solutions;
    }

    private static void placeQueen(Input input, int row) {
        int length = input.queens.length;

        if (row == length) {
            long solution = createSolution(input.queens);
            input.solutions.add(solution);
            return;
        }

        for (int column = 0; column < length; column++) {
            int leftDiagonal = row - column + length - 1;
            int rightDiagonal = row + column;

            if (input.leftDiagonals[leftDiagonal]) continue;
            if (input.rightDiagonals[rightDiagonal]) continue;
            if (input.columns[column]) continue;

            input.leftDiagonals[leftDiagonal] = true;
            input.rightDiagonals[rightDiagonal] = true;
            input.columns[column] = true;

            input.queens[row] = column;
            placeQueen(input, row + 1);

            input.leftDiagonals[leftDiagonal] = false;
            input.rightDiagonals[rightDiagonal] = false;
            input.columns[column] = false;
        }
    }

    private static long createSolution(int[] queens) {
        int length = queens.length;
        long solution = 0L;

        for (int location : queens)
            solution = length * solution + location;

        return solution;
    }

    @AllArgsConstructor
    private static class Input {
        private boolean[] leftDiagonals;
        private boolean[] rightDiagonals;
        private boolean[] columns;
        private int[] queens;
        private Set<Long> solutions;
    }
}
