package com.rafalzelazko.algorithms.text;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnuthMorrisPrattTest {
    @Test
    public void findSolutions__typicalCase__differentMatches() {
        String pattern = "ababab";
        String text = "ababababab";
        List<Integer> collected = KnuthMorrisPratt.findSolutions(pattern, text);
        assertEquals(List.of(0, 2, 4), collected);
    }

    @Test
    public void findSolutions__typicalCase__singleMatch() {
        String pattern = "abcabe";
        String text = "abcabcabe";
        List<Integer> collected = KnuthMorrisPratt.findSolutions(pattern, text);
        assertEquals(List.of(3), collected);
    }

    @Test
    public void findSolutions__singleItem__pattern() {
        String pattern = "a";
        String text = "aaa";
        List<Integer> collected = KnuthMorrisPratt.findSolutions(pattern, text);
        assertEquals(List.of(0, 1, 2), collected);
    }

    @Test
    public void findSolutions__singleItem__text() {
        String pattern = "aaa";
        String text = "a";
        List<Integer> collected = KnuthMorrisPratt.findSolutions(pattern, text);
        assertEquals(List.of(), collected);
    }

    @Test
    public void findSolutions__singleItem__combined() {
        String pattern = "z";
        String text = "z";
        List<Integer> collected = KnuthMorrisPratt.findSolutions(pattern, text);
        assertEquals(List.of(0), collected);
    }

    @Test
    public void findSolutions__noItems__pattern() {
        String pattern = "";
        String text = "aaa";
        List<Integer> collected = KnuthMorrisPratt.findSolutions(pattern, text);
        assertEquals(List.of(), collected);
    }

    @Test
    public void findSolutions__noItems__text() {
        String pattern = "aaa";
        String text = "";
        List<Integer> collected = KnuthMorrisPratt.findSolutions(pattern, text);
        assertEquals(List.of(), collected);
    }

    @Test
    public void findSolutions__noItems__combined() {
        String pattern = "";
        String text = "";
        List<Integer> collected = KnuthMorrisPratt.findSolutions(pattern, text);
        assertEquals(List.of(), collected);
    }
}
