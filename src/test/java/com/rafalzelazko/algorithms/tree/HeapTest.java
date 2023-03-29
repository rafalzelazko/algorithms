package com.rafalzelazko.algorithms.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeapTest {
    @Test
    public void build__typicalCase() {
        List<Integer> values = List.of(5, 1, 9, 2, 6, 2);
        List<Integer> list = new ArrayList<>(values);
        Heap.build(list);
        assertEquals(List.of(9, 6, 5, 2, 1, 2), list);
    }

    @Test
    public void build__singleItem() {
        List<Integer> values = List.of(5);
        List<Integer> list = new ArrayList<>(values);
        Heap.build(list);
        assertEquals(List.of(5), list);
    }

    @Test
    public void build__noItems() {
        List<Integer> list = new ArrayList<>();
        Heap.build(list);
        assertEquals(List.of(), list);
    }

    @Test
    public void insert__typicalCase() {
        List<Integer> values = List.of(9, 6, 5, 2, 1, 2);
        List<Integer> heap = new ArrayList<>(values);
        Heap.insert(heap, 7);
        assertEquals(List.of(9, 6, 7, 2, 1, 2, 5), heap);
    }

    @Test
    public void insert__singleItem() {
        List<Integer> values = List.of(5);
        List<Integer> heap = new ArrayList<>(values);
        Heap.insert(heap, 7);
        assertEquals(List.of(7, 5), heap);
    }

    @Test
    public void insert__noItems() {
        List<Integer> heap = new ArrayList<>();
        Heap.insert(heap, 7);
        assertEquals(List.of(7), heap);
    }

    @Test
    public void remove__typicalCase() {
        List<Integer> values = List.of(9, 6, 5, 2, 1, 2);
        List<Integer> heap = new ArrayList<>(values);
        Heap.remove(heap);
        assertEquals(List.of(6, 2, 5, 2, 1), heap);
    }

    @Test
    public void remove__singleItem() {
        List<Integer> values = List.of(5);
        List<Integer> heap = new ArrayList<>(values);
        Heap.remove(heap);
        assertEquals(List.of(), heap);
    }

    @Test
    public void remove__noItems() {
        List<Integer> heap = new ArrayList<>();
        Heap.remove(heap);
        assertEquals(List.of(), heap);
    }

    @Test
    public void sort__typicalCase() {
        List<Integer> values = List.of(5, 1, 9, 2, 6, 2);
        List<Integer> list = new ArrayList<>(values);
        Heap.sort(list);
        assertEquals(List.of(1, 2, 2, 5, 6, 9), list);
    }

    @Test
    public void sort__singleItem() {
        List<Integer> values = List.of(5);
        List<Integer> list = new ArrayList<>(values);
        Heap.sort(list);
        assertEquals(List.of(5), list);
    }

    @Test
    public void sort__noItems() {
        List<Integer> list = new ArrayList<>();
        Heap.sort(list);
        assertEquals(List.of(), list);
    }
}
