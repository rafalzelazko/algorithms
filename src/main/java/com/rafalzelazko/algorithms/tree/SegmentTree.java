package com.rafalzelazko.algorithms.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiFunction;

public class SegmentTree {
    /**
     * Creates and builds a segment tree according to the specified array and merge function
     * <ul>
     * <li> The array must not be null
     * <li> The merge function must not be null
     * <li> The merge function must be associative
     * </ul>
     *
     * @param array the integer array
     * @param merge the merge function to apply to items of the tree
     * @return the created tree
     */
    public static int[] build(int[] array, BiFunction<Integer, Integer, Integer> merge) {
        int[] tree = new int[2 * array.length];
        System.arraycopy(array, 0, tree, array.length, array.length);

        for (int index = array.length - 1; index >= 1; index--)
            tree[index] = merge.apply(tree[2 * index], tree[2 * index + 1]);

        return tree;
    }

    /**
     * Modifies an item of and updates the specified segment tree
     * <ul>
     * <li> The input must not be null
     * <li> The tree must not be null
     * <li> The merge function must not be null
     * <li> The merge function must be associative
     * <li> The index must stay within the second half of the tree
     * </ul>
     *
     * @param input the segment tree
     * @param index the index of the item to modify
     * @param value the value to assign
     */
    public static void modify(Input input, int index, int value) {
        int[] tree = input.tree;
        tree[index] = value;

        while (index > 1) {
            index = index / 2;
            tree[index] = input.merge.apply(tree[2 * index], tree[2 * index + 1]);
        }
    }

    /**
     * Performs a query over an interval of the specified segment tree
     * <ul>
     * <li> The input must not be null
     * <li> The tree must not be null
     * <li> The merge function must not be null
     * <li> The merge function must be associative
     * <li> The indices must stay within the second half of the tree or be in descending order
     * </ul>
     *
     * @param input   the segment tree
     * @param start   the index of the first item to consider
     * @param finish  the index of the last item to consider
     * @param initial the initial value of the result
     * @return the result
     */
    public static int query(Input input, int start, int finish, int initial) {
        int result = initial;

        while (start <= finish) {
            if (start % 2 == 1) {
                result = input.merge.apply(result, input.tree[start]);
                start++;
            }
            if (finish % 2 == 0) {
                result = input.merge.apply(result, input.tree[finish]);
                finish--;
            }
            start = start / 2;
            finish = finish / 2;
        }

        return result;
    }

    @AllArgsConstructor
    public static class Input {
        @Getter
        private int[] tree;
        private BiFunction<Integer, Integer, Integer> merge;
    }
}
