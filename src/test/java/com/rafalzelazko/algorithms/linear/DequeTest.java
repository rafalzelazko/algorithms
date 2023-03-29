package com.rafalzelazko.algorithms.linear;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.linear.Deque.Node;
import static org.junit.jupiter.api.Assertions.*;

public class DequeTest {
    @Test
    public void collectFromLeft__typicalCase() {
        Node first = new Node(1, null, null);
        Node second = new Node(2, first, null);
        Node third = new Node(3, second, null);
        first.setNext(second);
        second.setNext(third);

        Deque deque = new Deque(first, third);
        assertEquals(List.of(1, 2, 3), deque.collectFromLeft());
    }

    @Test
    public void collectFromLeft__singleItem() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        assertEquals(List.of(1), deque.collectFromLeft());
    }

    @Test
    public void collectFromLeft__noItems() {
        Deque deque = new Deque();
        assertEquals(List.of(), deque.collectFromLeft());
    }

    @Test
    public void collectFromRight__typicalCase() {
        Node first = new Node(1, null, null);
        Node second = new Node(2, first, null);
        Node third = new Node(3, second, null);
        first.setNext(second);
        second.setNext(third);

        Deque deque = new Deque(first, third);
        assertEquals(List.of(3, 2, 1), deque.collectFromRight());
    }

    @Test
    public void collectFromRight__singleItem() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        assertEquals(List.of(1), deque.collectFromRight());
    }

    @Test
    public void collectFromRight__noItems() {
        Deque deque = new Deque();
        assertEquals(List.of(), deque.collectFromRight());
    }

    @Test
    public void constructor__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Deque deque = new Deque(array);
        assertEquals(List.of(1, 2, 3), deque.collectFromLeft());
        assertEquals(List.of(3, 2, 1), deque.collectFromRight());
    }

    @Test
    public void constructor__singleItem() {
        int[] array = new int[] {1};
        Deque deque = new Deque(array);
        assertEquals(List.of(1), deque.collectFromLeft());
        assertEquals(List.of(1), deque.collectFromRight());
    }

    @Test
    public void constructor__noItems() {
        int[] array = new int[] {};
        Deque deque = new Deque(array);
        assertEquals(List.of(), deque.collectFromLeft());
        assertEquals(List.of(), deque.collectFromRight());
    }

    @Test
    public void attachLeft__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Deque deque = new Deque(array);
        deque.attachLeft(0);
        assertEquals(List.of(0, 1, 2, 3), deque.collectFromLeft());
        assertEquals(List.of(3, 2, 1, 0), deque.collectFromRight());
    }

    @Test
    public void attachLeft__singleItem() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        deque.attachLeft(0);
        assertEquals(List.of(0, 1), deque.collectFromLeft());
        assertEquals(List.of(1, 0), deque.collectFromRight());
    }

    @Test
    public void attachLeft__noItems() {
        Deque deque = new Deque();
        deque.attachLeft(0);
        assertEquals(List.of(0), deque.collectFromLeft());
        assertEquals(List.of(0), deque.collectFromRight());
    }

    @Test
    public void attachRight__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Deque deque = new Deque(array);
        deque.attachRight(4);
        assertEquals(List.of(1, 2, 3, 4), deque.collectFromLeft());
        assertEquals(List.of(4, 3, 2, 1), deque.collectFromRight());
    }

    @Test
    public void attachRight__singleItem() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        deque.attachRight(4);
        assertEquals(List.of(1, 4), deque.collectFromLeft());
        assertEquals(List.of(4, 1), deque.collectFromRight());
    }

    @Test
    public void attachRight__noItems() {
        Deque deque = new Deque();
        deque.attachRight(4);
        assertEquals(List.of(4), deque.collectFromLeft());
        assertEquals(List.of(4), deque.collectFromRight());
    }

    @Test
    public void detachLeft__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Deque deque = new Deque(array);
        int value = deque.detachLeft();
        assertEquals(1, value);
        assertEquals(List.of(2, 3), deque.collectFromLeft());
        assertEquals(List.of(3, 2), deque.collectFromRight());
    }

    @Test
    public void detachLeft__combined() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        int value = deque.detachLeft();
        deque.attachRight(4);
        assertEquals(1, value);
        assertEquals(List.of(4), deque.collectFromLeft());
        assertEquals(List.of(4), deque.collectFromRight());
    }

    @Test
    public void detachLeft__singleItem() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        int value = deque.detachLeft();
        assertEquals(1, value);
        assertEquals(List.of(), deque.collectFromLeft());
        assertEquals(List.of(), deque.collectFromRight());
    }

    @Test
    public void detachLeft__noItems() {
        Deque deque = new Deque();
        int value = deque.detachLeft();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), deque.collectFromLeft());
        assertEquals(List.of(), deque.collectFromRight());
    }

    @Test
    public void detachRight__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Deque deque = new Deque(array);
        int value = deque.detachRight();
        assertEquals(3, value);
        assertEquals(List.of(1, 2), deque.collectFromLeft());
        assertEquals(List.of(2, 1), deque.collectFromRight());
    }

    @Test
    public void detachRight__combined() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        int value = deque.detachRight();
        deque.attachLeft(0);
        assertEquals(1, value);
        assertEquals(List.of(0), deque.collectFromLeft());
        assertEquals(List.of(0), deque.collectFromRight());
    }

    @Test
    public void detachRight__singleItem() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        int value = deque.detachRight();
        assertEquals(1, value);
        assertEquals(List.of(), deque.collectFromLeft());
        assertEquals(List.of(), deque.collectFromRight());
    }

    @Test
    public void detachRight__noItems() {
        Deque deque = new Deque();
        int value = deque.detachRight();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), deque.collectFromLeft());
        assertEquals(List.of(), deque.collectFromRight());
    }

    @Test
    public void getLeft__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Deque deque = new Deque(array);
        int value = deque.getLeft();
        assertEquals(1, value);
        assertEquals(List.of(1, 2, 3), deque.collectFromLeft());
        assertEquals(List.of(3, 2, 1), deque.collectFromRight());
    }

    @Test
    public void getLeft__singleItem() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        int value = deque.getLeft();
        assertEquals(1, value);
        assertEquals(List.of(1), deque.collectFromLeft());
        assertEquals(List.of(1), deque.collectFromRight());
    }

    @Test
    public void getLeft__noItems() {
        Deque deque = new Deque();
        int value = deque.getLeft();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), deque.collectFromLeft());
        assertEquals(List.of(), deque.collectFromRight());
    }

    @Test
    public void getRight__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Deque deque = new Deque(array);
        int value = deque.getRight();
        assertEquals(3, value);
        assertEquals(List.of(1, 2, 3), deque.collectFromLeft());
        assertEquals(List.of(3, 2, 1), deque.collectFromRight());
    }

    @Test
    public void getRight__singleItem() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        int value = deque.getRight();
        assertEquals(1, value);
        assertEquals(List.of(1), deque.collectFromLeft());
        assertEquals(List.of(1), deque.collectFromRight());
    }

    @Test
    public void getRight__noItems() {
        Deque deque = new Deque();
        int value = deque.getRight();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), deque.collectFromLeft());
        assertEquals(List.of(), deque.collectFromRight());
    }

    @Test
    public void isEmpty__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Deque deque = new Deque(array);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isEmpty__singleItem() {
        Node first = new Node(1, null, null);
        Deque deque = new Deque(first, first);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isEmpty__noItems() {
        Deque deque = new Deque();
        assertTrue(deque.isEmpty());
    }
}
