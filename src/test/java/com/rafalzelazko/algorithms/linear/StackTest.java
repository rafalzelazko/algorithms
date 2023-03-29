package com.rafalzelazko.algorithms.linear;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.linear.Stack.Node;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    public void collectValues__typicalCase() {
        Node third = new Node(3, null);
        Node second = new Node(2, third);
        Node first = new Node(1, second);
        Stack stack = new Stack(first);
        assertEquals(List.of(1, 2, 3), stack.collectValues());
    }

    @Test
    public void collectValues__singleItem() {
        Node first = new Node(1, null);
        Stack stack = new Stack(first);
        assertEquals(List.of(1), stack.collectValues());
    }

    @Test
    public void collectValues__noItems() {
        Stack stack = new Stack();
        assertEquals(List.of(), stack.collectValues());
    }

    @Test
    public void constructor__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Stack stack = new Stack(array);
        assertEquals(List.of(1, 2, 3), stack.collectValues());
    }

    @Test
    public void constructor__singleItem() {
        int[] array = new int[] {1};
        Stack stack = new Stack(array);
        assertEquals(List.of(1), stack.collectValues());
    }

    @Test
    public void constructor__noItems() {
        int[] array = new int[] {};
        Stack stack = new Stack(array);
        assertEquals(List.of(), stack.collectValues());
    }

    @Test
    public void push__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Stack stack = new Stack(array);
        stack.push(0);
        assertEquals(List.of(0, 1, 2, 3), stack.collectValues());
    }

    @Test
    public void push__singleItem() {
        Node first = new Node(1, null);
        Stack stack = new Stack(first);
        stack.push(0);
        assertEquals(List.of(0, 1), stack.collectValues());
    }

    @Test
    public void push__noItems() {
        Stack stack = new Stack();
        stack.push(0);
        assertEquals(List.of(0), stack.collectValues());
    }

    @Test
    public void pop__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Stack stack = new Stack(array);
        int value = stack.pop();
        assertEquals(1, value);
        assertEquals(List.of(2, 3), stack.collectValues());
    }

    @Test
    public void pop__singleItem() {
        Node first = new Node(1, null);
        Stack stack = new Stack(first);
        int value = stack.pop();
        assertEquals(1, value);
        assertEquals(List.of(), stack.collectValues());
    }

    @Test
    public void pop__noItems() {
        Stack stack = new Stack();
        int value = stack.pop();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), stack.collectValues());
    }

    @Test
    public void peek__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Stack stack = new Stack(array);
        int value = stack.peek();
        assertEquals(1, value);
        assertEquals(List.of(1, 2, 3), stack.collectValues());
    }

    @Test
    public void peek__singleItem() {
        Node first = new Node(1, null);
        Stack stack = new Stack(first);
        int value = stack.peek();
        assertEquals(1, value);
        assertEquals(List.of(1), stack.collectValues());
    }

    @Test
    public void peek__noItems() {
        Stack stack = new Stack();
        int value = stack.peek();
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), stack.collectValues());
    }

    @Test
    public void isEmpty__typicalCase() {
        int[] array = new int[] {1, 2, 3};
        Stack stack = new Stack(array);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void isEmpty__singleItem() {
        Node first = new Node(1, null);
        Stack stack = new Stack(first);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void isEmpty__noItems() {
        Stack stack = new Stack();
        assertTrue(stack.isEmpty());
    }
}
