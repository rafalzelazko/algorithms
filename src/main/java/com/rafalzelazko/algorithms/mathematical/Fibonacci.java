package com.rafalzelazko.algorithms.mathematical;

import java.util.Stack;

public class Fibonacci {
    /**
     * Computes the Fibonacci number at the specified position in the sequence
     * <ul>
     * <li> The position must be at least 0 and at most 46
     * </ul>
     *
     * @param position the position
     * @return the Fibonacci number
     */
    public static int fibonacci(int position) {
        Stack<Boolean> stack = new Stack<>();
        int first = 0, second = 1;

        while (position > 0) {
            stack.push(position % 2 == 1);
            position = position / 2;
        }

        while (!stack.isEmpty()) {
            boolean shift = stack.pop();
            int even = first * (2 * second - first);
            int odd = second * second + first * first;

            if (shift) {
                first = odd;
                second = even + odd;
            } else {
                first = even;
                second = odd;
            }
        }

        return first;
    }
}
