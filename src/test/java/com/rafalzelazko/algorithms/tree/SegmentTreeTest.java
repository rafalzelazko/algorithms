package com.rafalzelazko.algorithms.tree;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static com.rafalzelazko.algorithms.tree.SegmentTree.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SegmentTreeTest {
    @Test
    public void build__typicalCase() {
        int[] array = new int[] {5, 6, -1, -2, 3, 4};
        BiFunction<Integer, Integer, Integer> merge = Integer::sum;
        int[] tree = build(array, merge);
        int[] correct = new int[] {0, 15, 4, 11, -3, 7, 5, 6, -1, -2, 3, 4};
        assertArrayEquals(correct, tree);
    }

    @Test
    public void build__singleItem() {
        int[] array = new int[] {3};
        BiFunction<Integer, Integer, Integer> merge = Integer::sum;
        int[] tree = build(array, merge);
        assertArrayEquals(new int[] {0, 3}, tree);
    }

    @Test
    public void build__noItems() {
        int[] array = new int[] {};
        BiFunction<Integer, Integer, Integer> merge = Integer::sum;
        int[] tree = build(array, merge);
        assertArrayEquals(new int[] {}, tree);
    }

    @Test
    public void modify__typicalCase() {
        int[] tree = new int[] {0, 6, 4, 6, -1, 4, 5, 6, -1, -2, 3, 4};
        BiFunction<Integer, Integer, Integer> merge = Math::max;
        Input input = new Input(tree, merge);
        modify(input, 9, 7);
        int[] correct = new int[] {0, 7, 7, 6, 7, 4, 5, 6, -1, 7, 3, 4};
        assertArrayEquals(correct, input.getTree());
    }

    @Test
    public void modify__singleItem() {
        int[] tree = new int[] {0, 3};
        BiFunction<Integer, Integer, Integer> merge = Math::max;
        Input input = new Input(tree, merge);
        modify(input, 1, 2);
        assertArrayEquals(new int[] {0, 2}, input.getTree());
    }

    @Test
    public void query__typicalCase() {
        int[] tree = new int[] {0, 720, 24, 30, 2, 12, 5, 6, -1, -2, 3, 4};
        BiFunction<Integer, Integer, Integer> merge = (a, b) -> a * b;
        Input input = new Input(tree, merge);
        int result = query(input, 6, 10, 1);
        assertEquals(180, result);
    }

    @Test
    public void query__singleItem() {
        int[] tree = new int[] {0, 3};
        BiFunction<Integer, Integer, Integer> merge = (a, b) -> a * b;
        Input input = new Input(tree, merge);
        int result = query(input, 1, 1, 1);
        assertEquals(3, result);
    }

    @Test
    public void query__noItems() {
        int[] tree = new int[] {};
        BiFunction<Integer, Integer, Integer> merge = (a, b) -> a * b;
        Input input = new Input(tree, merge);
        int result = query(input, 1, 0, 1);
        assertEquals(1, result);
    }
}
