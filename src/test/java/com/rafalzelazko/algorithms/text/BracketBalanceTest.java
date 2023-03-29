package com.rafalzelazko.algorithms.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BracketBalanceTest {
    @Test
    public void isBalanced__typicalCase__true() {
        String expression = "fn compute(arr: &[i32; 3]) -> i32 { arr[0] * (arr[1] + arr[2]) }";
        assertTrue(BracketBalance.isBalanced(expression));
    }

    @Test
    public void isBalanced__typicalCase__false() {
        String expression = "[{[}]]";
        assertFalse(BracketBalance.isBalanced(expression));
    }

    @Test
    public void isBalanced__singleItem__true() {
        String expression = "a";
        assertTrue(BracketBalance.isBalanced(expression));
    }

    @Test
    public void isBalanced__singleItem__false() {
        String expression = "{";
        assertFalse(BracketBalance.isBalanced(expression));
    }

    @Test
    public void isBalanced__noItems() {
        String expression = "";
        assertTrue(BracketBalance.isBalanced(expression));
    }
}
