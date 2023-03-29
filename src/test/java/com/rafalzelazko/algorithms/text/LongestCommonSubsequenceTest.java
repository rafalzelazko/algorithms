package com.rafalzelazko.algorithms.text;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestCommonSubsequenceTest {
    @Test
    public void findSolutions__typicalCase() {
        String first = "!abcde";
        String second = "?baecd";
        Set<String> correct = Set.of("acd", "bcd");
        Set<String> solutions = LongestCommonSubsequence.findSolutions(first, second);
        assertEquals(correct, solutions);
    }

    @Test
    public void findSolutions__singleItem__first() {
        String first = "z";
        String second = "aza";
        Set<String> correct = Set.of("z");
        Set<String> solutions = LongestCommonSubsequence.findSolutions(first, second);
        assertEquals(correct, solutions);
    }

    @Test
    public void findSolutions__singleItem__second() {
        String first = "aza";
        String second = "z";
        Set<String> correct = Set.of("z");
        Set<String> solutions = LongestCommonSubsequence.findSolutions(first, second);
        assertEquals(correct, solutions);
    }

    @Test
    public void findSolutions__singleItem__combined() {
        String first = "z";
        String second = "z";
        Set<String> correct = Set.of("z");
        Set<String> solutions = LongestCommonSubsequence.findSolutions(first, second);
        assertEquals(correct, solutions);
    }

    @Test
    public void findSolutions__noItems__first() {
        String first = "";
        String second = "aza";
        Set<String> correct = Set.of();
        Set<String> solutions = LongestCommonSubsequence.findSolutions(first, second);
        assertEquals(correct, solutions);
    }

    @Test
    public void findSolutions__noItems__second() {
        String first = "aza";
        String second = "";
        Set<String> correct = Set.of();
        Set<String> solutions = LongestCommonSubsequence.findSolutions(first, second);
        assertEquals(correct, solutions);
    }

    @Test
    public void findSolutions__noItems__combined() {
        String first = "";
        String second = "";
        Set<String> correct = Set.of();
        Set<String> solutions = LongestCommonSubsequence.findSolutions(first, second);
        assertEquals(correct, solutions);
    }
}
