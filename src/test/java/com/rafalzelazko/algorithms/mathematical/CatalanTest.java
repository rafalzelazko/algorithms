package com.rafalzelazko.algorithms.mathematical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalanTest {
    @Test
    public void catalan__16() {
        int catalan = Catalan.catalan(16);
        assertEquals(35_357_670, catalan);
    }

    @Test
    public void catalan__1() {
        int catalan = Catalan.catalan(1);
        assertEquals(1, catalan);
    }

    @Test
    public void catalan__0() {
        int catalan = Catalan.catalan(0);
        assertEquals(1, catalan);
    }
}
