package com.rafalzelazko.algorithms.mathematical;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeCollectorTest {
    @Test
    public void collectPrimes__typicalCase() {
        List<Integer> correct = List.of(2, 3, 5, 7, 11, 13, 17);
        assertEquals(correct, PrimeCollector.collectPrimes(17));
    }

    @Test
    public void collectPrimes__singleItem() {
        List<Integer> correct = List.of(2);
        assertEquals(correct, PrimeCollector.collectPrimes(2));
    }

    @Test
    public void collectPrimes__noItems() {
        List<Integer> correct = List.of();
        assertEquals(correct, PrimeCollector.collectPrimes(1));
    }
}
