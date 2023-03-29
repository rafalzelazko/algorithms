package com.rafalzelazko.algorithms.linear;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Queue {
    private Node front;
    private Node rear;

    /**
     * Creates an instance of a queue according to the specified array
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the integer array
     */
    public Queue(int[] array) {
        if (array.length > 0)
            this.front = this.rear = new Node(array[array.length - 1], null);
        for (int index = array.length - 2; index >= 0; index--)
            this.front = new Node(array[index], this.front);
    }

    /**
     * Collects values of all nodes in this queue
     *
     * @return the collected values
     */
    public List<Integer> collectValues() {
        List<Integer> collected = new ArrayList<>();
        Node current = this.front;
        while (current != null) {
            collected.add(current.value);
            current = current.next;
        }
        return collected;
    }

    /**
     * Creates a node with the specified value and offers it to this queue
     *
     * @param value the value of a node to offer
     */
    public void offer(int value) {
        if (this.rear == null) {
            this.front = this.rear = new Node(value, null);
        } else {
            Node created = new Node(value, null);
            this.rear.next = created;
            this.rear = created;
        }
    }

    /**
     * Removes a node from this queue and returns its value
     *
     * @return the value of a node to remove, Integer.MIN_VALUE if this queue is empty
     */
    public int poll() {
        if (this.front == null)
            return Integer.MIN_VALUE;

        Node selected = this.front;
        this.front = selected.next;
        selected.next = null;

        if (this.front == null)
            this.rear = null;

        return selected.value;
    }

    /**
     * Returns the value of a node at the front of this queue
     *
     * @return the value of a node at the front, Integer.MIN_VALUE if this queue is empty
     */
    public int peek() {
        if (this.front == null)
            return Integer.MIN_VALUE;
        return this.front.value;
    }

    /**
     * Checks whether this queue is empty
     *
     * @return true if this queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.front == null;
    }

    @AllArgsConstructor
    public static class Node {
        private int value;
        private Node next;
    }
}
