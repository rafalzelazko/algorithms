package com.rafalzelazko.algorithms.tree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.tree.RedBlackTree.*;
import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeTest {
    @Test
    public void collectValues__typicalCase() {
        Node first = new Node(1, 1);
        Node third = new Node(3, 1);
        Node seventh = new Node(7, 1);

        Node second = new Node(2, 0, first, third);
        Node sixth = new Node(6, 0, null, seventh);
        Node fourth = new Node(4, 0, second, sixth);

        List.of(first, third).forEach(node -> node.setParent(second));
        List.of(second, sixth).forEach(node -> node.setParent(fourth));
        seventh.setParent(sixth);

        assertEquals(List.of(1, 2, 3, 4, 6, 7), collectValues(fourth));
    }

    @Test
    public void collectValues__singleItem() {
        Node node = new Node(1, 0);
        assertEquals(List.of(1), collectValues(node));
    }

    @Test
    public void collectValues__noItems() {
        Node node = new Node(null);
        assertEquals(List.of(), collectValues(node));
    }

    @Test
    public void collectColours__typicalCase() {
        Node first = new Node(1, 1);
        Node third = new Node(3, 1);
        Node seventh = new Node(7, 1);

        Node second = new Node(2, 0, first, third);
        Node sixth = new Node(6, 0, null, seventh);
        Node fourth = new Node(4, 0, second, sixth);

        List.of(first, third).forEach(node -> node.setParent(second));
        List.of(second, sixth).forEach(node -> node.setParent(fourth));
        seventh.setParent(sixth);

        assertEquals(List.of(1, 0, 1, 0, 0, 1), collectColours(fourth));
    }

    @Test
    public void collectColours__singleItem() {
        Node node = new Node(1, 0);
        assertEquals(List.of(0), collectColours(node));
    }

    @Test
    public void collectColours__noItems() {
        Node node = new Node(null);
        assertEquals(List.of(), collectColours(node));
    }

    @Test
    public void collectParents__typicalCase() {
        Node first = new Node(1, 1);
        Node third = new Node(3, 1);
        Node seventh = new Node(7, 1);

        Node second = new Node(2, 0, first, third);
        Node sixth = new Node(6, 0, null, seventh);
        Node fourth = new Node(4, 0, second, sixth);

        List.of(first, third).forEach(node -> node.setParent(second));
        List.of(second, sixth).forEach(node -> node.setParent(fourth));
        seventh.setParent(sixth);

        assertEquals(List.of(2, 4, 2, -1, 4, 6), collectParents(fourth));
    }

    @Test
    public void collectParents__singleItem() {
        Node node = new Node(1, 0);
        assertEquals(List.of(-1), collectParents(node));
    }

    @Test
    public void collectParents__noItems() {
        Node node = new Node(null);
        assertEquals(List.of(), collectParents(node));
    }

    @Test
    public void convertFromArray__typicalCase() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        int[] colours = new int[] {0, 0, 0, 1, 1, -1, 1};
        Node centre = convertFromArray(array, colours, 0);

        assertEquals(List.of(1, 2, 3, 4, 6, 7), collectValues(centre));
        assertEquals(List.of(1, 0, 1, 0, 0, 1), collectColours(centre));
        assertEquals(List.of(2, 4, 2, -1, 4, 6), collectParents(centre));
    }

    @Test
    public void convertFromArray__singleItem() {
        int[] array = new int[] {1};
        int[] colours = new int[] {0};
        Node node = convertFromArray(array, colours, 0);

        assertEquals(List.of(1), collectValues(node));
        assertEquals(List.of(0), collectColours(node));
        assertEquals(List.of(-1), collectParents(node));
    }

    @Test
    public void convertFromArray__noItems() {
        int[] array = new int[] {};
        int[] colours = new int[] {};
        Node node = convertFromArray(array, colours, 0);

        assertEquals(List.of(), collectValues(node));
        assertEquals(List.of(), collectColours(node));
        assertEquals(List.of(), collectParents(node));
    }

    @Test
    public void isColouredProperly__typicalCase() {
        int[] array = new int[] {5, 3, 6, 2, 4, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1};
        int[] colours = new int[] {0, 1, 0, 0, 0, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1};
        Node centre = convertFromArray(array, colours, 0);
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void isColouredProperly__illegalRootColour() {
        int[] array = new int[] {2, 1, 3};
        int[] colours = new int[] {1, 1, 1};
        Node centre = convertFromArray(array, colours, 0);
        assertFalse(isColouredProperly(centre));
    }

    @Test
    public void isColouredProperly__illegalColourValue() {
        int[] array = new int[] {2, 1, 3};
        int[] colours = new int[] {0, 1, 2};
        Node centre = convertFromArray(array, colours, 0);
        assertFalse(isColouredProperly(centre));
    }

    @Test
    public void isColouredProperly__missingRedProperty() {
        int[] array = new int[] {3, 2, -1, 1, -1, -1, -1};
        int[] colours = new int[] {0, 1, -1, 1, -1, -1, -1};
        Node centre = convertFromArray(array, colours, 0);
        assertFalse(isColouredProperly(centre));
    }

    @Test
    public void isColouredProperly__missingBlackProperty() {
        int[] array = new int[] {3, 2, -1, 1, -1, -1, -1};
        int[] colours = new int[] {0, 0, -1, 0, -1, -1, -1};
        Node centre = convertFromArray(array, colours, 0);
        assertFalse(isColouredProperly(centre));
    }

    @Test
    public void isColouredProperly__singleItem() {
        Node node = new Node(1, 0);
        assertTrue(isColouredProperly(node));
    }

    @Test
    public void isColouredProperly__noItems() {
        Node node = new Node(null);
        assertTrue(isColouredProperly(node));
    }

    @Test
    public void insert__newValue() {
        Node centre = new Node(null);
        List<Integer> values = List.of(2, 1, 3);
        for (int value : values) centre = insert(centre, value);
        assertEquals(List.of(1, 2, 3), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void insert__existingValue() {
        Node centre = new Node(null);
        List<Integer> values = List.of(2, 1, 3, 1);
        for (int value : values) centre = insert(centre, value);
        assertEquals(List.of(1, 2, 3), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void insert__recolouring() {
        Node centre = new Node(null);
        List<Integer> values = List.of(2, 1, 3, 4);
        for (int value : values) centre = insert(centre, value);
        assertEquals(List.of(1, 2, 3, 4), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void insert__rightRotation() {
        Node centre = new Node(null);
        List<Integer> values = List.of(3, 2, 1);
        for (int value : values) centre = insert(centre, value);
        assertEquals(List.of(1, 2, 3), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void insert__leftRightRotation() {
        Node centre = new Node(null);
        List<Integer> values = List.of(3, 1, 2);
        for (int value : values) centre = insert(centre, value);
        assertEquals(List.of(1, 2, 3), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void insert__leftRotation() {
        Node centre = new Node(null);
        List<Integer> values = List.of(1, 2, 3);
        for (int value : values) centre = insert(centre, value);
        assertEquals(List.of(1, 2, 3), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void insert__rightLeftRotation() {
        Node centre = new Node(null);
        List<Integer> values = List.of(1, 3, 2);
        for (int value : values) centre = insert(centre, value);
        assertEquals(List.of(1, 2, 3), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void insert__singleItem() {
        Node node = new Node(1, 0);
        node = insert(node, 2);
        assertEquals(List.of(1, 2), collectValues(node));
        assertTrue(isColouredProperly(node));
    }

    @Test
    public void insert__noItems() {
        Node node = new Node(null);
        node = insert(node, 1);
        assertEquals(List.of(1), collectValues(node));
        assertTrue(isColouredProperly(node));
    }

    @Test
    public void remove__immediateSuccessor() {
        Node centre = new Node(null);
        List<Integer> values = List.of(4, 2, 6, 1, 3, 7);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 2);
        assertEquals(List.of(1, 3, 4, 6, 7), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__distantSuccessor() {
        Node centre = new Node(null);
        List<Integer> values = List.of(4, 2, 6, 1, 3, 5);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 4);
        assertEquals(List.of(1, 2, 3, 5, 6), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__singleChild() {
        Node centre = new Node(null);
        List<Integer> values = List.of(4, 2, 6, 1, 3, 7);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 6);
        assertEquals(List.of(1, 2, 3, 4, 7), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__noChildren() {
        Node centre = new Node(null);
        List<Integer> values = List.of(4, 2, 6, 1, 3, 7);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 3);
        assertEquals(List.of(1, 2, 4, 6, 7), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__missingValue() {
        Node centre = new Node(null);
        List<Integer> values = List.of(4, 2, 6, 1, 3, 7);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 5);
        assertEquals(List.of(1, 2, 3, 4, 6, 7), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__recolouring() {
        Node centre = new Node(null);
        List<Integer> values = List.of(2, 1, 3, 4);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 4);
        centre = remove(centre, 1);
        assertEquals(List.of(2, 3), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__rightRotation() {
        Node centre = new Node(null);
        List<Integer> values = List.of(3, 2, 4, 1);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 4);
        assertEquals(List.of(1, 2, 3), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__leftRightRotation() {
        Node centre = new Node(null);
        List<Integer> values = List.of(3, 1, 4, 2);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 4);
        assertEquals(List.of(1, 2, 3), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__leftRotation() {
        Node centre = new Node(null);
        List<Integer> values = List.of(2, 1, 3, 4);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 1);
        assertEquals(List.of(2, 3, 4), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__rightLeftRotation() {
        Node centre = new Node(null);
        List<Integer> values = List.of(2, 1, 4, 3);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 1);
        assertEquals(List.of(2, 3, 4), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__redLeftSibling() {
        Node centre = new Node(null);
        List<Integer> values = List.of(5, 2, 6, 1, 3, 4);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 6);
        assertEquals(List.of(1, 2, 3, 4, 5), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__redRightSibling() {
        Node centre = new Node(null);
        List<Integer> values = List.of(2, 1, 5, 4, 6, 3);
        for (int value : values) centre = insert(centre, value);
        centre = remove(centre, 1);
        assertEquals(List.of(2, 3, 4, 5, 6), collectValues(centre));
        assertTrue(isColouredProperly(centre));
    }

    @Test
    public void remove__singleItem() {
        Node node = new Node(1, 0);
        node = remove(node, 1);
        assertEquals(List.of(), collectValues(node));
        assertTrue(isColouredProperly(node));
    }

    @Test
    public void remove__noItems() {
        Node node = new Node(null);
        node = remove(node, 1);
        assertEquals(List.of(), collectValues(node));
        assertTrue(isColouredProperly(node));
    }
}
