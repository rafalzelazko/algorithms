package com.rafalzelazko.algorithms.mathematical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreatestCommonDivisorTest {
    @Test
    public void findSolution__commonFactors() {
        int solution = GreatestCommonDivisor.findSolution(18, 30);
        assertEquals(6, solution);
    }

    @Test
    public void findSolution__coprimeValues() {
        int solution = GreatestCommonDivisor.findSolution(55, 14);
        assertEquals(1, solution);
    }

    @Test
    public void findSolution__negativeValue() {
        int solution = GreatestCommonDivisor.findSolution(-9, 6);
        assertEquals(3, solution);
    }

    @Test
    public void findSolution__onePresent() {
        int solution = GreatestCommonDivisor.findSolution(1, 12);
        assertEquals(1, solution);
    }

    @Test
    public void findSolution__zeroPresent() {
        int solution = GreatestCommonDivisor.findSolution(0, 27);
        assertEquals(27, solution);
    }
}
