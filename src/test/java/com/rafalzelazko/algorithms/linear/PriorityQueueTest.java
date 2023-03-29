package com.rafalzelazko.algorithms.linear;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.linear.PriorityQueue.Node;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    @Test
    public void collectValues__typicalCase() {
        Node third = new Node(3, null);
        Node second = new Node(2, third);
        Node first = new Node(1, second);
        PriorityQueue priorityQueue = new PriorityQueue(first);
        assertEquals(List.of(1, 2, 3), priorityQueue.collectValues());
    }

    @Test
    public void collectValues__singleItem() {
        Node first = new Node(5, null);
        PriorityQueue priorityQueue = new PriorityQueue(first);
        assertEquals(List.of(5), priorityQueue.collectValues());
    }

    @Test
    public void collectValues__noItems() {
        PriorityQueue priorityQueue = new PriorityQueue();
        assertEquals(List.of(), priorityQueue.collectValues());
    }

    @Test
    public void constructor__typicalCase() {
        int[] array = new int[] {5, 2, 6, 2, 1, 3};
        PriorityQueue priorityQueue = new PriorityQueue(array);
        assertEquals(List.of(1, 2, 2, 3, 5, 6), priorityQueue.collectValues());
    }

    @Test
    public void constructor__singleItem() {
        int[] array = new int[] {5};
        PriorityQueue priorityQueue = new PriorityQueue(array);
        assertEquals(List.of(5), priorityQueue.collectValues());
    }

    @Test
    public void constructor__noItems() {
        int[] array = new int[] {};
        PriorityQueue priorityQueue = new PriorityQueue(array);
        assertEquals(List.of(), priorityQueue.collectValues());
    }

    @Test
    public void offer__typicalCase() {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        assertEquals(List.of(1, 2, 3), priorityQueue.collectValues());
    }

    @Test
    public void offer__singleItem() {
        Node first = new Node(5, null);
        PriorityQueue priorityQueue = new PriorityQueue(first);
        priorityQueue.offer(7);
        assertEquals(List.of(5, 7), priorityQueue.collectValues());
    }

    @Test
    public void offer__noItems() {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(7);
        assertEquals(List.of(7), priorityQueue.collectValues());
    }

    @Test
    public void poll__typicalCase() {
        int[] array = new int[] {5, 2, 6, 2, 1, 3};
        PriorityQueue priorityQueue = new PriorityQueue(array);
        int value = priorityQueue.poll();
        assertEquals(1, value);
        assertEquals(List.of(2, 2, 3, 5, 6), priorityQueue.collectValues());
    }

    @Test
    public void poll__singleItem() {
        Node first = new Node(5, null);
        PriorityQueue priorityQueue = new PriorityQueue(first);
        int value = priorityQueue.poll();
        assertEquals(5, value);
        assertEquals(List.of(), priorityQueue.collectValues());
    }

    @Test
    public void poll__noItems() {
        PriorityQueue priorityQueue = new PriorityQueue();
        int value = priorityQueue.poll();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), priorityQueue.collectValues());
    }

    @Test
    public void peek__typicalCase() {
        int[] array = new int[] {5, 2, 6, 2, 1, 3};
        PriorityQueue priorityQueue = new PriorityQueue(array);
        int value = priorityQueue.peek();
        assertEquals(1, value);
        assertEquals(List.of(1, 2, 2, 3, 5, 6), priorityQueue.collectValues());
    }

    @Test
    public void peek__singleItem() {
        Node first = new Node(5, null);
        PriorityQueue priorityQueue = new PriorityQueue(first);
        int value = priorityQueue.peek();
        assertEquals(5, value);
        assertEquals(List.of(5), priorityQueue.collectValues());
    }

    @Test
    public void peek__noItems() {
        PriorityQueue priorityQueue = new PriorityQueue();
        int value = priorityQueue.peek();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), priorityQueue.collectValues());
    }

    @Test
    public void isEmpty__typicalCase() {
        int[] array = new int[] {5, 2, 6, 2, 1, 3};
        PriorityQueue priorityQueue = new PriorityQueue(array);
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void isEmpty__singleItem() {
        Node first = new Node(5, null);
        PriorityQueue priorityQueue = new PriorityQueue(first);
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void isEmpty__noItems() {
        PriorityQueue priorityQueue = new PriorityQueue();
        assertTrue(priorityQueue.isEmpty());
    }
}
