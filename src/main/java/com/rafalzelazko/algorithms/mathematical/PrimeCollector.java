package com.rafalzelazko.algorithms.mathematical;

import java.util.ArrayList;
import java.util.List;

public class PrimeCollector {
    /**
     * Collects all primes up to the specified integer
     * <ul>
     * <li> The integer must be positive
     * </ul>
     *
     * @param integer the integer
     * @return the collected primes
     */
    public static List<Integer> collectPrimes(int integer) {
        List<Integer> collected = new ArrayList<>();
        boolean[] composite = new boolean[integer + 1];

        for (int index = 2; index * index <= integer; index++)
            if (!composite[index])
                for (int multiple = index * index; multiple <= integer; multiple += index)
                    composite[multiple] = true;

        for (int index = 2; index <= integer; index++)
            if (!composite[index])
                collected.add(index);

        return collected;
    }
}
