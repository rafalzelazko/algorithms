package com.rafalzelazko.algorithms.linear;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static com.rafalzelazko.algorithms.linear.LinkedList.Node;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    @Test
    public void containsCycle__typicalCase__true() {
        Node third = new Node(3, null);
        Node second = new Node(2, third);
        Node first = new Node(1, second);
        LinkedList linkedList = new LinkedList(first);
        third.setNext(second);
        assertTrue(linkedList.containsCycle());
    }

    @Test
    public void containsCycle__typicalCase__false() {
        Node third = new Node(3, null);
        Node second = new Node(2, third);
        Node first = new Node(1, second);
        LinkedList linkedList = new LinkedList(first);
        assertFalse(linkedList.containsCycle());
    }

    @Test
    public void containsCycle__singleItem__true() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        first.setNext(first);
        assertTrue(linkedList.containsCycle());
    }

    @Test
    public void containsCycle__singleItem__false() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        assertFalse(linkedList.containsCycle());
    }

    @Test
    public void containsCycle__noItems() {
        LinkedList linkedList = new LinkedList();
        assertFalse(linkedList.containsCycle());
    }

    @Test
    public void collectValues__typicalCase() {
        Node third = new Node(3, null);
        Node second = new Node(2, third);
        Node first = new Node(1, second);
        LinkedList linkedList = new LinkedList(first);
        assertEquals(List.of(1, 2, 3), linkedList.collectValues());
    }

    @Test
    public void collectValues__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        assertEquals(List.of(1), linkedList.collectValues());
    }

    @Test
    public void collectValues__noItems() {
        LinkedList linkedList = new LinkedList();
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void constructor__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        LinkedList linkedList = new LinkedList(array);
        assertEquals(List.of(1, 2, 3), linkedList.collectValues());
    }

    @Test
    public void constructor__singleItem() {
        int[] array = new int[] {1};
        LinkedList linkedList = new LinkedList(array);
        assertEquals(List.of(1), linkedList.collectValues());
    }

    @Test
    public void constructor__noItems() {
        int[] array = new int[] {};
        LinkedList linkedList = new LinkedList(array);
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void insertFirst__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        LinkedList linkedList = new LinkedList(array);
        linkedList.insertFirst(0);
        assertEquals(List.of(0, 1, 2, 3), linkedList.collectValues());
    }

    @Test
    public void insertFirst__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        linkedList.insertFirst(0);
        assertEquals(List.of(0, 1), linkedList.collectValues());
    }

    @Test
    public void insertFirst__noItems() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertFirst(0);
        assertEquals(List.of(0), linkedList.collectValues());
    }

    @Test
    public void insertLast__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        LinkedList linkedList = new LinkedList(array);
        linkedList.insertLast(4);
        assertEquals(List.of(1, 2, 3, 4), linkedList.collectValues());
    }

    @Test
    public void insertLast__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        linkedList.insertLast(2);
        assertEquals(List.of(1, 2), linkedList.collectValues());
    }

    @Test
    public void insertLast__noItems() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertLast(4);
        assertEquals(List.of(4), linkedList.collectValues());
    }

    @Test
    public void removeFirst__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        LinkedList linkedList = new LinkedList(array);
        int value = linkedList.removeFirst();
        assertEquals(1, value);
        assertEquals(List.of(2, 3), linkedList.collectValues());
    }

    @Test
    public void removeFirst__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        int value = linkedList.removeFirst();
        assertEquals(1, value);
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void removeFirst__noItems() {
        LinkedList linkedList = new LinkedList();
        int value = linkedList.removeFirst();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void removeLast__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        LinkedList linkedList = new LinkedList(array);
        int value = linkedList.removeLast();
        assertEquals(3, value);
        assertEquals(List.of(1, 2), linkedList.collectValues());
    }

    @Test
    public void removeLast__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        int value = linkedList.removeLast();
        assertEquals(1, value);
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void removeLast__noItems() {
        LinkedList linkedList = new LinkedList();
        int value = linkedList.removeLast();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void isEmpty__typicalCase() {
        Node third = new Node(3, null);
        Node second = new Node(2, third);
        Node first = new Node(1, second);
        LinkedList linkedList = new LinkedList(first);
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void isEmpty__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void isEmpty__noItems() {
        LinkedList linkedList = new LinkedList();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void filter__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        LinkedList linkedList = new LinkedList(array);
        Function<Integer, Boolean> condition = x -> x % 2 == 0;
        linkedList.filter(condition);
        assertEquals(List.of(2), linkedList.collectValues());
    }

    @Test
    public void filter__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        Function<Integer, Boolean> condition = x -> x % 2 == 0;
        linkedList.filter(condition);
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void filter__noItems() {
        LinkedList linkedList = new LinkedList();
        Function<Integer, Boolean> condition = x -> x % 2 == 0;
        linkedList.filter(condition);
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void reverse__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        LinkedList linkedList = new LinkedList(array);
        linkedList.reverse();
        assertEquals(List.of(3, 2, 1), linkedList.collectValues());
    }

    @Test
    public void reverse__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        linkedList.reverse();
        assertEquals(List.of(1), linkedList.collectValues());
    }

    @Test
    public void reverse__noItems() {
        LinkedList linkedList = new LinkedList();
        linkedList.reverse();
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void rotate__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        LinkedList linkedList = new LinkedList(array);
        linkedList.rotate(2);
        assertEquals(List.of(3, 1, 2), linkedList.collectValues());
    }

    @Test
    public void rotate__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        linkedList.rotate(1);
        assertEquals(List.of(1), linkedList.collectValues());
    }

    @Test
    public void rotate__noItems() {
        LinkedList linkedList = new LinkedList();
        linkedList.rotate(1);
        assertEquals(List.of(), linkedList.collectValues());
    }

    @Test
    public void sort__typicalCase() {
        int[] array = new int[] {1, 7, 2, 3, 6, 5, 7};
        LinkedList linkedList = new LinkedList(array);
        linkedList.sort();
        assertEquals(List.of(1, 2, 3, 5, 6, 7, 7), linkedList.collectValues());
    }

    @Test
    public void sort__singleItem() {
        Node first = new Node(1, null);
        LinkedList linkedList = new LinkedList(first);
        linkedList.sort();
        assertEquals(List.of(1), linkedList.collectValues());
    }

    @Test
    public void sort__noItems() {
        LinkedList linkedList = new LinkedList();
        linkedList.sort();
        assertEquals(List.of(), linkedList.collectValues());
    }
}
