package com.rafalzelazko.algorithms.combinatorial;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class NQueenTest {
    @Test
    public void presentSolution__typicalCase() {
        char[][] chessboard = new char[][] {
            new char[] {'.', '.', 'Q', '.'},
            new char[] {'Q', '.', '.', '.'},
            new char[] {'.', '.', '.', 'Q'},
            new char[] {'.', 'Q', '.', '.'}
        };
        long solution = 141L;
        char[][] presentation = NQueen.presentSolution(solution, 4);
        assertArrayEquals(chessboard, presentation);
    }

    @Test
    public void presentSolution__singleItem() {
        char[][] chessboard = new char[][] {
            new char[] {'Q'}
        };
        long solution = 0L;
        char[][] presentation = NQueen.presentSolution(solution, 1);
        assertArrayEquals(chessboard, presentation);
    }

    @Test
    public void checkSolution__typicalCase__correct() {
        long solution = 141L;
        assertTrue(NQueen.checkSolution(solution, 4));
    }

    @Test
    public void checkSolution__typicalCase__incorrect() {
        long solution = 54L;
        assertFalse(NQueen.checkSolution(solution, 4));
    }

    @Test
    public void checkSolution__singleItem() {
        long solution = 0L;
        assertTrue(NQueen.checkSolution(solution, 1));
    }

    @Test
    public void nQueen__typicalCase() {
        Set<Long> solutions = NQueen.nQueen(12);
        assertEquals(14200, solutions.size());
        solutions.forEach(solution ->
            assertTrue(NQueen.checkSolution(solution, 12)));
    }

    @Test
    public void nQueen__singleItem() {
        Set<Long> solutions = NQueen.nQueen(1);
        assertEquals(1, solutions.size());
        solutions.forEach(solution ->
            assertTrue(NQueen.checkSolution(solution, 1)));
    }
}
