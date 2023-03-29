package com.rafalzelazko.algorithms.linear;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor
@AllArgsConstructor
public class LinkedList {
    private Node head;

    /**
     * Creates an instance of a linked list according to the specified array
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the integer array
     */
    public LinkedList(int[] array) {
        for (int index = array.length - 1; index >= 0; index--)
            this.head = new Node(array[index], this.head);
    }

    /**
     * Checks whether this list contains a cycle
     *
     * @return true if this list contains a cycle, false otherwise
     */
    public boolean containsCycle() {
        if (this.head == null) return false;
        Node faster = this.head.next;
        Node slower = this.head;

        while (faster != null && faster.next != null) {
            if (slower == faster) return true;
            faster = faster.next.next;
            slower = slower.next;
        }
        return false;
    }

    /**
     * Collects values of all nodes in this list
     * <ul>
     * <li> This list must not contain a cycle
     * </ul>
     *
     * @return the collected values
     */
    public List<Integer> collectValues() {
        List<Integer> collected = new ArrayList<>();
        Node current = this.head;
        while (current != null) {
            collected.add(current.value);
            current = current.next;
        }
        return collected;
    }

    /**
     * Creates a node with the specified value and inserts it at the beginning of this list
     *
     * @param value the value of a node to insert
     */
    public void insertFirst(int value) {
        this.head = new Node(value, this.head);
    }

    /**
     * Creates a node with the specified value and inserts it at the end of this list
     * <ul>
     * <li> This list must not contain a cycle
     * </ul>
     *
     * @param value the value of a node to insert
     */
    public void insertLast(int value) {
        if (this.head == null) {
            this.head = new Node(value, null);
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(value, null);
        }
    }

    /**
     * Removes a node from the beginning of this list and returns its value
     *
     * @return the value of a node to remove, Integer.MIN_VALUE if this list is empty
     */
    public int removeFirst() {
        if (this.head == null)
            return Integer.MIN_VALUE;

        Node first = this.head;
        this.head = first.next;
        first.next = null;

        return first.value;
    }

    /**
     * Removes a node from the end of this list and returns its value
     * <ul>
     * <li> This list must not contain a cycle
     * </ul>
     *
     * @return the value of a node to remove, Integer.MIN_VALUE if this list is empty
     */
    public int removeLast() {
        if (this.head == null)
            return Integer.MIN_VALUE;

        if (this.head.next == null) {
            int value = this.head.value;
            this.head = null;
            return value;
        }

        Node current = this.head;
        while (current.next.next != null)
            current = current.next;

        int value = current.next.value;
        current.next = null;
        return value;
    }

    /**
     * Checks whether this list is empty
     *
     * @return true if this list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Filters this list according to the specified condition
     * <ul>
     * <li> This list must not contain a cycle
     * <li> The condition must not be null
     * </ul>
     *
     * @param condition the condition to check each node against
     */
    public void filter(Function<Integer, Boolean> condition) {
        if (this.head == null) return;
        Node current = this.head;

        while (current.next != null) {
            if (!condition.apply(current.next.value)) {
                Node rejected = current.next;
                current.next = rejected.next;
                rejected.next = null;
            } else {
                current = current.next;
            }
        }

        if (!condition.apply(this.head.value)) {
            Node first = this.head;
            this.head = first.next;
            first.next = null;
        }
    }

    /**
     * Reverses this list
     * <ul>
     * <li> This list must not contain a cycle
     * </ul>
     */
    public void reverse() {
        Node current = this.head;
        this.head = null;

        while (current != null) {
            Node successor = current.next;
            current.next = this.head;
            this.head = current;
            current = successor;
        }
    }

    /**
     * Rotates this list by the specified distance
     * <ul>
     * <li> This list must not contain a cycle
     * </ul>
     *
     * @param distance the number of nodes to rotate this list by
     */
    public void rotate(int distance) {
        if (this.head == null) return;
        Node current = this.head;
        Node selected = null;

        while (current.next != null) {
            if (distance == 1) selected = current;
            current = current.next;
            distance--;
        }

        if (selected != null) {
            selected.next = null;
            current.next = this.head;
            this.head = current;
        }
    }

    /**
     * Sorts this list using merge sort algorithm
     * <ul>
     * <li> This list must not contain a cycle
     * </ul>
     */
    public void sort() {
        this.head = mergeSort(this.head);
    }

    private Node mergeSort(Node node) {
        int length = this.computeLength(node);
        if (length <= 1) return node;

        int middle = (length - 1) / 2;
        Node checkpoint = this.find(node, middle);
        Node successor = checkpoint.next;
        checkpoint.next = null;

        Node first = mergeSort(node);
        Node second = mergeSort(successor);
        return merge(first, second);
    }

    private Node merge(Node first, Node second) {
        Node special = new Node(1, null);
        Node current = special;

        while (first != null && second != null) {
            if (first.value < second.value) {
                current.next = first;
                first = first.next;
            } else {
                current.next = second;
                second = second.next;
            }
            current = current.next;
        }

        while (first != null) {
            current.next = first;
            first = first.next;
            current = current.next;
        }

        while (second != null) {
            current.next = second;
            second = second.next;
            current = current.next;
        }

        return special.next;
    }

    private int computeLength(Node current) {
        int length = 0;
        while (current != null) {
            current = current.next;
            length++;
        }
        return length;
    }

    private Node find(Node current, int distance) {
        while (current != null && distance > 0) {
            current = current.next;
            distance--;
        }
        return current;
    }

    @AllArgsConstructor
    public static class Node {
        private int value;

        @Setter
        private Node next;
    }
}
