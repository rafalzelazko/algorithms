package com.rafalzelazko.algorithms.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManacherTest {
    @Test
    public void manacher__typicalCase__odd() {
        String string = "abacabaeabacae";
        String palindrome = Manacher.manacher(string);
        assertEquals("acabaeabaca", palindrome);
    }

    @Test
    public void manacher__typicalCase__even() {
        String string = "abaaba";
        String palindrome = Manacher.manacher(string);
        assertEquals("abaaba", palindrome);
    }

    @Test
    public void manacher__singleItem() {
        String string = "z";
        String palindrome = Manacher.manacher(string);
        assertEquals("z", palindrome);
    }

    @Test
    public void manacher__noItems() {
        String string = "";
        String palindrome = Manacher.manacher(string);
        assertEquals("", palindrome);
    }
}
