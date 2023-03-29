package com.rafalzelazko.algorithms.mathematical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {
    @Test
    public void factorial__12() {
        int factorial = Factorial.factorial(12);
        assertEquals(479_001_600, factorial);
    }

    @Test
    public void factorial__1() {
        int factorial = Factorial.factorial(1);
        assertEquals(1, factorial);
    }

    @Test
    public void factorial__0() {
        int factorial = Factorial.factorial(0);
        assertEquals(1, factorial);
    }
}
