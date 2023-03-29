package com.rafalzelazko.algorithms.tree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.tree.AVLTree.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AVLTreeTest {
    @Test
    public void collectValues__typicalCase() {
        Node left = new Node(2, 1, new Node(1), new Node(3));
        Node right = new Node(6, 1, null, new Node(7));
        Node centre = new Node(4, 2, left, right);
        assertEquals(List.of(1, 2, 3, 4, 6, 7), collectValues(centre));
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
    public void collectHeights__typicalCase() {
        Node left = new Node(2, 1, new Node(1), new Node(3));
        Node right = new Node(6, 1, null, new Node(7));
        Node centre = new Node(4, 2, left, right);
        assertEquals(List.of(0, 1, 0, 2, 1, 0), collectHeights(centre));
    }

    @Test
    public void collectHeights__singleItem() {
        Node node = new Node(1);
        assertEquals(List.of(0), collectHeights(node));
    }

    @Test
    public void collectHeights__noItems() {
        assertEquals(List.of(), collectHeights(null));
    }

    @Test
    public void convertFromArray__typicalCase() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array, 0);
        assertEquals(List.of(1, 2, 3, 4, 6, 7), collectValues(centre));
        assertEquals(List.of(0, 1, 0, 2, 1, 0), collectHeights(centre));
    }

    @Test
    public void convertFromArray__singleItem() {
        int[] array = new int[] {1};
        Node node = convertFromArray(array, 0);
        assertEquals(List.of(1), collectValues(node));
        assertEquals(List.of(0), collectHeights(node));
    }

    @Test
    public void convertFromArray__noItems() {
        int[] array = new int[] {};
        Node node = convertFromArray(array, 0);
        assertEquals(List.of(), collectValues(node));
        assertEquals(List.of(), collectHeights(node));
    }

    @Test
    public void computeHeight__typicalCase() {
        int[] array = new int[] {3, 2, 4, 1, -1, -1, -1};
        Node centre = convertFromArray(array, 0);
        assertEquals(2, computeHeight(centre));
    }

    @Test
    public void computeHeight__imbalance() {
        int[] array = new int[] {4, 3, 5, 2, -1, -1, 6, 1, -1, -1, -1, -1, -1, -1, 7};
        Node centre = convertFromArray(array, 0);
        assertEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void computeHeight__singleItem() {
        Node node = new Node(1);
        assertEquals(0, computeHeight(node));
    }

    @Test
    public void computeHeight__noItems() {
        assertEquals(-1, computeHeight(null));
    }

    @Test
    public void attach__newValue() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array, 0);
        centre = attach(centre, 5);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void attach__existingValue() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5, 7};
        Node centre = convertFromArray(array, 0);
        centre = attach(centre, 5);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void attach__rightRotation() {
        int[] array = new int[] {5, 4, 6, 2, -1, -1, -1};
        Node centre = convertFromArray(array, 0);
        centre = attach(centre, 1);
        assertEquals(List.of(1, 2, 4, 5, 6), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void attach__leftRightRotation() {
        int[] array = new int[] {5, 4, 6, 2, -1, -1, -1};
        Node centre = convertFromArray(array, 0);
        centre = attach(centre, 3);
        assertEquals(List.of(2, 3, 4, 5, 6), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void attach__leftRotation() {
        int[] array = new int[] {2, 1, 3, -1, -1, -1, 5};
        Node centre = convertFromArray(array, 0);
        centre = attach(centre, 6);
        assertEquals(List.of(1, 2, 3, 5, 6), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void attach__rightLeftRotation() {
        int[] array = new int[] {2, 1, 3, -1, -1, -1, 5};
        Node centre = convertFromArray(array, 0);
        centre = attach(centre, 4);
        assertEquals(List.of(1, 2, 3, 4, 5), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void attach__singleItem() {
        Node node = new Node(1);
        node = attach(node, 2);
        assertEquals(List.of(1, 2), collectValues(node));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(node));
    }

    @Test
    public void attach__noItems() {
        Node node = attach(null, 1);
        assertEquals(List.of(1), collectValues(node));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(node));
    }

    @Test
    public void detach__immediateSuccessor() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array, 0);
        centre = detach(centre, 2);
        assertEquals(List.of(1, 3, 4, 6, 7), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void detach__distantSuccessor() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5, -1};
        Node centre = convertFromArray(array, 0);
        centre = detach(centre, 4);
        assertEquals(List.of(1, 2, 3, 5, 6), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void detach__singleChild() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array, 0);
        centre = detach(centre, 6);
        assertEquals(List.of(1, 2, 3, 4, 7), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void detach__noChildren() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array, 0);
        centre = detach(centre, 3);
        assertEquals(List.of(1, 2, 4, 6, 7), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void detach__missingValue() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array, 0);
        centre = detach(centre, 5);
        assertEquals(List.of(1, 2, 3, 4, 6, 7), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void detach__rightRotation() {
        int[] array = new int[] {4, 2, 5, 1, -1, -1, -1};
        Node centre = convertFromArray(array, 0);
        centre = detach(centre, 5);
        assertEquals(List.of(1, 2, 4), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void detach__leftRightRotation() {
        int[] array = new int[] {4, 2, 5, -1, 3, -1, -1};
        Node centre = convertFromArray(array, 0);
        centre = detach(centre, 5);
        assertEquals(List.of(2, 3, 4), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void detach__leftRotation() {
        int[] array = new int[] {2, 1, 4, -1, -1, -1, 5};
        Node centre = convertFromArray(array, 0);
        centre = detach(centre, 1);
        assertEquals(List.of(2, 4, 5), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void detach__rightLeftRotation() {
        int[] array = new int[] {2, 1, 4, -1, -1, 3, -1};
        Node centre = convertFromArray(array, 0);
        centre = detach(centre, 1);
        assertEquals(List.of(2, 3, 4), collectValues(centre));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(centre));
    }

    @Test
    public void detach__singleItem() {
        Node node = new Node(1);
        node = detach(node, 1);
        assertEquals(List.of(), collectValues(node));
        assertNotEquals(Integer.MIN_VALUE, computeHeight(node));
    }
}
