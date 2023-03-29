package com.rafalzelazko.algorithms.mathematical;

public class GreatestCommonDivisor {
    /**
     * Computes the greatest common divisor of two integers
     *
     * @param first  the first integer
     * @param second the second integer
     * @return the greatest common divisor
     */
    public static int findSolution(int first, int second) {
        first = Math.abs(first);
        second = Math.abs(second);

        if (first < second)
            return findSolution(second, first);

        while (true) {
            if (second == 0) return first;
            int third = first % second;
            first = second;
            second = third;
        }
    }
}
