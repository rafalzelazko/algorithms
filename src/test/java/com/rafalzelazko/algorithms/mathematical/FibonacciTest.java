package com.rafalzelazko.algorithms.mathematical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciTest {
    @Test
    public void fibonacci__13() {
        int fibonacci = Fibonacci.fibonacci(13);
        assertEquals(233, fibonacci);
    }

    @Test
    public void fibonacci__46() {
        int fibonacci = Fibonacci.fibonacci(46);
        assertEquals(1_836_311_903, fibonacci);
    }

    @Test
    public void fibonacci__1() {
        int fibonacci = Fibonacci.fibonacci(1);
        assertEquals(1, fibonacci);
    }

    @Test
    public void fibonacci__0() {
        int fibonacci = Fibonacci.fibonacci(0);
        assertEquals(0, fibonacci);
    }
}
