package com.rafalzelazko.algorithms.tree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.tree.CompleteBinaryTree.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompleteBinaryTreeTest {
    @Test
    public void collectValues__typicalCase() {
        Node left = new Node(2, new Node(1), new Node(3));
        Node right = new Node(6, new Node(5), null);
        Node centre = new Node(4, left, right);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), collectValues(centre));
    }

    @Test
    public void collectValues__singleItem() {
        Node node = new Node(1);
        assertEquals(List.of(1), collectValues(node));
    }

    @Test
    public void collectValues__noItems() {
        assertEquals(List.of(), collectValues(null));
    }

    @Test
    public void convertFromArray__typicalCase() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5, -1};
        Node centre = convertFromArray(array, 0);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), collectValues(centre));
    }

    @Test
    public void convertFromArray__singleItem() {
        int[] array = new int[] {1};
        Node node = convertFromArray(array, 0);
        assertEquals(List.of(1), collectValues(node));
    }

    @Test
    public void convertFromArray__noItems() {
        int[] array = new int[] {};
        Node node = convertFromArray(array, 0);
        assertEquals(List.of(), collectValues(node));
    }

    @Test
    public void insert__typicalCase() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5};
        Node centre = convertFromArray(array, 0);
        centre = insert(centre, 7);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), collectValues(centre));
    }

    @Test
    public void insert__singleItem() {
        Node node = new Node(2);
        node = insert(node, 1);
        assertEquals(List.of(1, 2), collectValues(node));
    }

    @Test
    public void insert__noItems() {
        Node node = insert(null, 1);
        assertEquals(List.of(1), collectValues(node));
    }

    @Test
    public void remove__leftChild() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5, -1};
        Node centre = convertFromArray(array, 0);
        centre = remove(centre, 2);
        assertEquals(List.of(1, 5, 3, 4, 6), collectValues(centre));
    }

    @Test
    public void remove__rightChild() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5, 7};
        Node centre = convertFromArray(array, 0);
        centre = remove(centre, 2);
        assertEquals(List.of(1, 7, 3, 4, 5, 6), collectValues(centre));
    }

    @Test
    public void remove__lastItem() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5, -1};
        Node centre = convertFromArray(array, 0);
        centre = remove(centre, 5);
        assertEquals(List.of(1, 2, 3, 4, 6), collectValues(centre));
    }

    @Test
    public void remove__missingValue() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5, -1};
        Node centre = convertFromArray(array, 0);
        centre = remove(centre, 7);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), collectValues(centre));
    }

    @Test
    public void remove__singleItem() {
        Node node = new Node(1);
        node = remove(node, 1);
        assertEquals(List.of(), collectValues(node));
    }
}
