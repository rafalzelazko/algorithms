package com.rafalzelazko.algorithms.tree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.tree.BinarySearchTree.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTreeTest {
    @Test
    public void presentPreorder__typicalCase() {
        Node left = new Node(2, new Node(1), new Node(3));
        Node right = new Node(6, null, new Node(7));
        Node centre = new Node(4, left, right);
        assertEquals(List.of(4, 2, 1, 3, 6, 7), presentPreorder(centre));
    }

    @Test
    public void presentPreorder__singleItem() {
        Node node = new Node(1);
        assertEquals(List.of(1), presentPreorder(node));
    }

    @Test
    public void presentPreorder__noItems() {
        assertEquals(List.of(), presentPreorder(null));
    }

    @Test
    public void presentInorder__typicalCase() {
        Node left = new Node(2, new Node(1), new Node(3));
        Node right = new Node(6, null, new Node(7));
        Node centre = new Node(4, left, right);
        assertEquals(List.of(1, 2, 3, 4, 6, 7), presentInorder(centre));
    }

    @Test
    public void presentInorder__singleItem() {
        Node node = new Node(1);
        assertEquals(List.of(1), presentInorder(node));
    }

    @Test
    public void presentInorder__noItems() {
        assertEquals(List.of(), presentInorder(null));
    }

    @Test
    public void presentPostorder__typicalCase() {
        Node left = new Node(2, new Node(1), new Node(3));
        Node right = new Node(6, null, new Node(7));
        Node centre = new Node(4, left, right);
        assertEquals(List.of(1, 3, 2, 7, 6, 4), presentPostorder(centre));
    }

    @Test
    public void presentPostorder__singleItem() {
        Node node = new Node(1);
        assertEquals(List.of(1), presentPostorder(node));
    }

    @Test
    public void presentPostorder__noItems() {
        assertEquals(List.of(), presentPostorder(null));
    }

    @Test
    public void presentLevelOrder__typicalCase() {
        Node left = new Node(2, new Node(1), new Node(3));
        Node right = new Node(6, null, new Node(7));
        Node centre = new Node(4, left, right);
        assertEquals(List.of(4, 2, 6, 1, 3, 7), presentLevelOrder(centre));
    }

    @Test
    public void presentLevelOrder__singleItem() {
        Node node = new Node(1);
        assertEquals(List.of(1), presentLevelOrder(node));
    }

    @Test
    public void presentLevelOrder__noItems() {
        assertEquals(List.of(), presentLevelOrder(null));
    }

    @Test
    public void presentAlternateOrder__typicalCase() {
        Node left = new Node(2, new Node(1), new Node(3));
        Node right = new Node(6, null, new Node(7));
        Node centre = new Node(4, left, right);
        assertEquals(List.of(4, 6, 2, 1, 3, 7), presentAlternateOrder(centre));
    }

    @Test
    public void presentAlternateOrder__singleItem() {
        Node node = new Node(1);
        assertEquals(List.of(1), presentAlternateOrder(node));
    }

    @Test
    public void presentAlternateOrder__noItems() {
        assertEquals(List.of(), presentAlternateOrder(null));
    }

    @Test
    public void convertFromArray__typicalCase() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array);
        assertEquals(List.of(1, 2, 3, 4, 6, 7), presentInorder(centre));
    }

    @Test
    public void convertFromArray__singleItem() {
        int[] array = new int[] {1};
        Node node = convertFromArray(array);
        assertEquals(List.of(1), presentInorder(node));
    }

    @Test
    public void convertFromArray__noItems() {
        int[] array = new int[] {};
        Node node = convertFromArray(array);
        assertEquals(List.of(), presentInorder(node));
    }

    @Test
    public void convertFromPreorderAndInorder__typicalCase() {
        List<Integer> preorder = List.of(4, 2, 1, 3, 6, 7);
        List<Integer> inorder = List.of(1, 2, 3, 4, 6, 7);
        Node centre = convertFromPreorderAndInorder(preorder, inorder);
        assertEquals(preorder, presentPreorder(centre));
        assertEquals(inorder, presentInorder(centre));
    }

    @Test
    public void convertFromPreorderAndInorder__singleItem() {
        List<Integer> preorder = List.of(1);
        List<Integer> inorder = List.of(1);
        Node centre = convertFromPreorderAndInorder(preorder, inorder);
        assertEquals(preorder, presentPreorder(centre));
        assertEquals(inorder, presentInorder(centre));
    }

    @Test
    public void convertFromPreorderAndInorder__noItems() {
        List<Integer> preorder = List.of();
        List<Integer> inorder = List.of();
        Node centre = convertFromPreorderAndInorder(preorder, inorder);
        assertEquals(preorder, presentPreorder(centre));
        assertEquals(inorder, presentInorder(centre));
    }

    @Test
    public void convertFromPostorderAndInorder__typicalCase() {
        List<Integer> postorder = List.of(1, 3, 2, 7, 6, 4);
        List<Integer> inorder = List.of(1, 2, 3, 4, 6, 7);
        Node centre = convertFromPostorderAndInorder(postorder, inorder);
        assertEquals(postorder, presentPostorder(centre));
        assertEquals(inorder, presentInorder(centre));
    }

    @Test
    public void convertFromPostorderAndInorder__singleItem() {
        List<Integer> postorder = List.of(1);
        List<Integer> inorder = List.of(1);
        Node centre = convertFromPostorderAndInorder(postorder, inorder);
        assertEquals(postorder, presentPostorder(centre));
        assertEquals(inorder, presentInorder(centre));
    }

    @Test
    public void convertFromPostorderAndInorder__noItems() {
        List<Integer> postorder = List.of();
        List<Integer> inorder = List.of();
        Node centre = convertFromPostorderAndInorder(postorder, inorder);
        assertEquals(postorder, presentPostorder(centre));
        assertEquals(inorder, presentInorder(centre));
    }

    @Test
    public void convertFromLevelOrderAndInorder__typicalCase() {
        List<Integer> levelOrder = List.of(4, 2, 6, 1, 3, 7);
        List<Integer> inorder = List.of(1, 2, 3, 4, 6, 7);
        Node centre = convertFromLevelOrderAndInorder(levelOrder, inorder);
        assertEquals(levelOrder, presentLevelOrder(centre));
        assertEquals(inorder, presentInorder(centre));
    }

    @Test
    public void convertFromLevelOrderAndInorder__singleItem() {
        List<Integer> levelOrder = List.of(1);
        List<Integer> inorder = List.of(1);
        Node centre = convertFromLevelOrderAndInorder(levelOrder, inorder);
        assertEquals(levelOrder, presentLevelOrder(centre));
        assertEquals(inorder, presentInorder(centre));
    }

    @Test
    public void convertFromLevelOrderAndInorder__noItems() {
        List<Integer> levelOrder = List.of();
        List<Integer> inorder = List.of();
        Node centre = convertFromLevelOrderAndInorder(levelOrder, inorder);
        assertEquals(levelOrder, presentLevelOrder(centre));
        assertEquals(inorder, presentInorder(centre));
    }

    @Test
    public void insert__newValue() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array);
        centre = insert(centre, 5);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), presentInorder(centre));
    }

    @Test
    public void insert__existingValue() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5, 7};
        Node centre = convertFromArray(array);
        centre = insert(centre, 5);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), presentInorder(centre));
    }

    @Test
    public void insert__singleItem() {
        Node node = new Node(1);
        node = insert(node, 2);
        assertEquals(List.of(1, 2), presentInorder(node));
    }

    @Test
    public void insert__noItems() {
        Node node = insert(null, 1);
        assertEquals(List.of(1), presentInorder(node));
    }

    @Test
    public void remove__immediateSuccessor() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array);
        centre = remove(centre, 2);
        assertEquals(List.of(1, 3, 4, 6, 7), presentInorder(centre));
    }

    @Test
    public void remove__distantSuccessor() {
        int[] array = new int[] {4, 2, 6, 1, 3, 5, -1};
        Node centre = convertFromArray(array);
        centre = remove(centre, 4);
        assertEquals(List.of(1, 2, 3, 5, 6), presentInorder(centre));
    }

    @Test
    public void remove__singleChild() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array);
        centre = remove(centre, 6);
        assertEquals(List.of(1, 2, 3, 4, 7), presentInorder(centre));
    }

    @Test
    public void remove__noChildren() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array);
        centre = remove(centre, 3);
        assertEquals(List.of(1, 2, 4, 6, 7), presentInorder(centre));
    }

    @Test
    public void remove__missingValue() {
        int[] array = new int[] {4, 2, 6, 1, 3, -1, 7};
        Node centre = convertFromArray(array);
        centre = remove(centre, 5);
        assertEquals(List.of(1, 2, 3, 4, 6, 7), presentInorder(centre));
    }

    @Test
    public void remove__singleItem() {
        Node node = new Node(1);
        node = remove(node, 1);
        assertEquals(List.of(), presentInorder(node));
    }
}
