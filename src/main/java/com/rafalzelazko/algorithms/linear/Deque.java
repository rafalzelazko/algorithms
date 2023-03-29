package com.rafalzelazko.algorithms.linear;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Deque {
    private Node left;
    private Node right;

    /**
     * Creates an instance of a double-ended queue according to the specified array
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the integer array
     */
    public Deque(int[] array) {
        if (array.length > 0)
            this.left = this.right = new Node(array[0], null, null);

        for (int index = 1; index < array.length; index++) {
            Node previous = this.right;
            this.right = new Node(array[index], previous, null);
            previous.next = this.right;
        }
    }

    /**
     * Collects values of all nodes in this queue starting from the left
     *
     * @return the collected values
     */
    public List<Integer> collectFromLeft() {
        List<Integer> collected = new ArrayList<>();
        Node current = this.left;
        while (current != null) {
            collected.add(current.value);
            current = current.next;
        }
        return collected;
    }

    /**
     * Collects values of all nodes in this queue starting from the right
     *
     * @return the collected values
     */
    public List<Integer> collectFromRight() {
        List<Integer> collected = new ArrayList<>();
        Node current = this.right;
        while (current != null) {
            collected.add(current.value);
            current = current.previous;
        }
        return collected;
    }

    /**
     * Creates a node with the specified value and attaches it to the left end of this queue
     *
     * @param value the value of a node to attach
     */
    public void attachLeft(int value) {
        if (this.left == null) {
            this.left = this.right = new Node(value, null, null);
        } else {
            Node created = new Node(value, null, this.left);
            this.left.previous = created;
            this.left = created;
        }
    }

    /**
     * Creates a node with the specified value and attaches it to the right end of this queue
     *
     * @param value the value of a node to attach
     */
    public void attachRight(int value) {
        if (this.right == null) {
            this.left = this.right = new Node(value, null, null);
        } else {
            Node created = new Node(value, this.right, null);
            this.right.next = created;
            this.right = created;
        }
    }

    /**
     * Detaches a node from the left end of this queue and returns its value
     *
     * @return the value of a node to detach, Integer.MIN_VALUE if this queue is empty
     */
    public int detachLeft() {
        if (this.left == null)
            return Integer.MIN_VALUE;

        Node selected = this.left;
        this.left = selected.next;
        selected.next = null;

        if (this.left != null)
            this.left.previous = null;
        else this.right = null;

        return selected.value;
    }

    /**
     * Detaches a node from the right end of this queue and returns its value
     *
     * @return the value of a node to detach, Integer.MIN_VALUE if this queue is empty
     */
    public int detachRight() {
        if (this.right == null)
            return Integer.MIN_VALUE;

        Node selected = this.right;
        this.right = selected.previous;
        selected.previous = null;

        if (this.right != null)
            this.right.next = null;
        else this.left = null;

        return selected.value;
    }

    /**
     * Returns the value of a node at the left end of this queue
     *
     * @return the value of a node at the left end, Integer.MIN_VALUE if this queue is empty
     */
    public int getLeft() {
        if (this.left == null)
            return Integer.MIN_VALUE;
        return this.left.value;
    }

    /**
     * Returns the value of a node at the right end of this queue
     *
     * @return the value of a node at the right end, Integer.MIN_VALUE if this queue is empty
     */
    public int getRight() {
        if (this.right == null)
            return Integer.MIN_VALUE;
        return this.right.value;
    }

    /**
     * Checks whether this queue is empty
     *
     * @return true if this queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.left == null;
    }

    @AllArgsConstructor
    public static class Node {
        private int value;
        private Node previous;
        @Setter
        private Node next;
    }
}
