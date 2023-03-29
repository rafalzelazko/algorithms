package com.rafalzelazko.algorithms.linear;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Stack {
    private Node top;

    /**
     * Creates an instance of a stack according to the specified array
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the integer array
     */
    public Stack(int[] array) {
        for (int index = array.length - 1; index >= 0; index--)
            this.top = new Node(array[index], this.top);
    }

    /**
     * Collects values of all nodes in this stack
     *
     * @return the collected values
     */
    public List<Integer> collectValues() {
        List<Integer> collected = new ArrayList<>();
        Node current = this.top;
        while (current != null) {
            collected.add(current.value);
            current = current.next;
        }
        return collected;
    }

    /**
     * Creates a node with the specified value and pushes it onto this stack
     *
     * @param value the value of a node to push
     */
    public void push(int value) {
        this.top = new Node(value, this.top);
    }

    /**
     * Removes a node from this stack and returns its value
     *
     * @return the value of a node to remove, Integer.MIN_VALUE if this stack is empty
     */
    public int pop() {
        if (this.top == null)
            return Integer.MIN_VALUE;

        Node selected = this.top;
        this.top = selected.next;
        selected.next = null;

        return selected.value;
    }

    /**
     * Returns the value of a node at the top of this stack
     *
     * @return the value of a node at the top, Integer.MIN_VALUE if this stack is empty
     */
    public int peek() {
        if (this.top == null)
            return Integer.MIN_VALUE;
        return this.top.value;
    }

    /**
     * Checks whether this stack is empty
     *
     * @return true if this stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.top == null;
    }

    @AllArgsConstructor
    public static class Node {
        private int value;
        private Node next;
    }
}
