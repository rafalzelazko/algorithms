package com.rafalzelazko.algorithms.tree;

import java.util.List;

public class Heap {
    /**
     * Builds a heap according to the specified list
     * <ul>
     * <li> The list must not be null
     * <li> The list must be mutable
     * </ul>
     *
     * @param list the integer list
     */
    public static void build(List<Integer> list) {
        int length = list.size();
        for (int index = length / 2 - 1; index >= 0; index--)
            heapify(list, length, index);
    }

    /**
     * Inserts the specified value into the specified heap
     * <ul>
     * <li> The heap must not be null
     * <li> The heap must be mutable
     * </ul>
     *
     * @param heap  the heap
     * @param value the value to insert
     */
    public static void insert(List<Integer> heap, int value) {
        heap.add(value);
        heapifyAfterInsertion(heap, heap.size() - 1);
    }

    /**
     * Removes a maximum value from the specified heap
     * <ul>
     * <li> The heap must not be null
     * <li> The heap must be mutable
     * </ul>
     *
     * @param heap the heap
     */
    public static void remove(List<Integer> heap) {
        if (heap.isEmpty()) return;
        int length = heap.size();
        swap(heap, length - 1, 0);
        heap.remove(length - 1);
        heapify(heap, length - 1, 0);
    }

    /**
     * Sorts an integer list using heap sort algorithm
     * <ul>
     * <li> The list must not be null
     * <li> The list must be mutable
     * </ul>
     *
     * @param list the integer list
     */
    public static void sort(List<Integer> list) {
        int length = list.size();
        build(list);

        for (int index = length - 1; index >= 1; index--) {
            swap(list, index, 0);
            heapify(list, index, 0);
        }
    }

    private static void heapify(List<Integer> heap, int length, int index) {
        int selected = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < length && heap.get(selected) < heap.get(left))
            selected = left;

        if (right < length && heap.get(selected) < heap.get(right))
            selected = right;

        if (selected != index) {
            swap(heap, selected, index);
            heapify(heap, length, selected);
        }
    }

    private static void heapifyAfterInsertion(List<Integer> heap, int index) {
        int parent = (index - 1) / 2;
        if (parent >= 0 && heap.get(parent) < heap.get(index)) {
            swap(heap, parent, index);
            heapifyAfterInsertion(heap, parent);
        }
    }

    private static void swap(List<Integer> list, int first, int second) {
        int carrier = list.get(first);
        list.set(first, list.get(second));
        list.set(second, carrier);
    }
}
