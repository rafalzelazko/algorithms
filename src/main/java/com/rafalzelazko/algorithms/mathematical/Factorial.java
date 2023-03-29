package com.rafalzelazko.algorithms.mathematical;

public class Factorial {
    /**
     * Computes the factorial of the specified integer
     * <ul>
     * <li> The integer must be at least 0 and at most 12
     * </ul>
     *
     * @param integer the integer
     * @return the factorial
     */
    public static int factorial(int integer) {
        int factorial = 1;

        for (int index = 2; index <= integer; index++)
            factorial = factorial * index;

        return factorial;
    }
}
