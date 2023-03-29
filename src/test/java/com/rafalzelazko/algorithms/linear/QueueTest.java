package com.rafalzelazko.algorithms.linear;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.linear.Queue.Node;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    @Test
    public void collectValues__typicalCase() {
        Node third = new Node(3, null);
        Node second = new Node(2, third);
        Node first = new Node(1, second);
        Queue queue = new Queue(first, third);
        assertEquals(List.of(1, 2, 3), queue.collectValues());
    }

    @Test
    public void collectValues__singleItem() {
        Node first = new Node(1, null);
        Queue queue = new Queue(first, first);
        assertEquals(List.of(1), queue.collectValues());
    }

    @Test
    public void collectValues__noItems() {
        Queue queue = new Queue();
        assertEquals(List.of(), queue.collectValues());
    }

    @Test
    public void constructor__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Queue queue = new Queue(array);
        assertEquals(List.of(1, 2, 3), queue.collectValues());
    }

    @Test
    public void constructor__singleItem() {
        int[] array = new int[] {1};
        Queue queue = new Queue(array);
        assertEquals(List.of(1), queue.collectValues());
    }

    @Test
    public void constructor__noItems() {
        int[] array = new int[] {};
        Queue queue = new Queue(array);
        assertEquals(List.of(), queue.collectValues());
    }

    @Test
    public void offer__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Queue queue = new Queue(array);
        queue.offer(4);
        assertEquals(List.of(1, 2, 3, 4), queue.collectValues());
    }

    @Test
    public void offer__singleItem() {
        Node first = new Node(1, null);
        Queue queue = new Queue(first, first);
        queue.offer(2);
        assertEquals(List.of(1, 2), queue.collectValues());
    }

    @Test
    public void offer__noItems() {
        Queue queue = new Queue();
        queue.offer(1);
        assertEquals(List.of(1), queue.collectValues());
    }

    @Test
    public void poll__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Queue queue = new Queue(array);
        int value = queue.poll();
        assertEquals(1, value);
        assertEquals(List.of(2, 3), queue.collectValues());
    }

    @Test
    public void poll__combined() {
        Node first = new Node(1, null);
        Queue queue = new Queue(first, first);
        int value = queue.poll();
        queue.offer(2);
        assertEquals(1, value);
        assertEquals(List.of(2), queue.collectValues());
    }

    @Test
    public void poll__singleItem() {
        Node first = new Node(1, null);
        Queue queue = new Queue(first, first);
        int value = queue.poll();
        assertEquals(1, value);
        assertEquals(List.of(), queue.collectValues());
    }

    @Test
    public void poll__noItems() {
        Queue queue = new Queue();
        int value = queue.poll();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), queue.collectValues());
    }

    @Test
    public void peek__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Queue queue = new Queue(array);
        int value = queue.peek();
        assertEquals(1, value);
        assertEquals(List.of(1, 2, 3), queue.collectValues());
    }

    @Test
    public void peek__singleItem() {
        Node first = new Node(1, null);
        Queue queue = new Queue(first, first);
        int value = queue.peek();
        assertEquals(1, value);
        assertEquals(List.of(1), queue.collectValues());
    }

    @Test
    public void peek__noItems() {
        Queue queue = new Queue();
        int value = queue.peek();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), queue.collectValues());
    }

    @Test
    public void isEmpty__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Queue queue = new Queue(array);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void isEmpty__singleItem() {
        Node first = new Node(1, null);
        Queue queue = new Queue(first, first);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void isEmpty__noItems() {
        Queue queue = new Queue();
        assertTrue(queue.isEmpty());
    }
}
