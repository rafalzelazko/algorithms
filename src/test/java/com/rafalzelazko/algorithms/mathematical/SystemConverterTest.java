package com.rafalzelazko.algorithms.mathematical;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SystemConverterTest {
    @Test
    public void changeSystem__typicalCase() {
        char[] characters = new char[] {'3', '3', '1'};
        char[] solution = SystemConverter.changeSystem(characters, 6, 12);
        assertArrayEquals(new char[] {'a', '7'}, solution);
    }

    @Test
    public void changeSystem__singleItem() {
        char[] characters = new char[] {'c'};
        char[] solution = SystemConverter.changeSystem(characters, 16, 2);
        assertArrayEquals(new char[] {'1', '1', '0', '0'}, solution);
    }

    @Test
    public void changeSystem__noItems() {
        char[] characters = new char[] {};
        char[] solution = SystemConverter.changeSystem(characters, 9, 11);
        assertArrayEquals(new char[] {'0'}, solution);
    }
}
