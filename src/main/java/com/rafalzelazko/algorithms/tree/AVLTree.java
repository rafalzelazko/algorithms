package com.rafalzelazko.algorithms.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    /**
     * Collects values of all nodes in an AVL tree in the inorder fashion
     *
     * @param node the root of the current tree
     * @return the collected values
     */
    public static List<Integer> collectValues(Node node) {
        if (node == null) return new ArrayList<>();
        List<Integer> collected = collectValues(node.left);
        collected.add(node.value);
        collected.addAll(collectValues(node.right));
        return collected;
    }

    /**
     * Collects heights of all nodes in an AVL tree in the inorder fashion
     *
     * @param node the root of the current tree
     * @return the collected heights
     */
    public static List<Integer> collectHeights(Node node) {
        if (node == null) return new ArrayList<>();
        List<Integer> collected = collectHeights(node.left);
        collected.add(node.height);
        collected.addAll(collectHeights(node.right));
        return collected;
    }

    /**
     * Creates an AVL tree from its array representation
     * <ul>
     * <li> The array must not be null
     * <li> The index must be non-negative
     * </ul>
     *
     * @param array the array representation of the tree
     * @param index the index of the root
     * @return the root of the created tree
     */
    public static Node convertFromArray(int[] array, int index) {
        if (index >= array.length || array[index] == -1)
            return null;
        int height = fitHeight(index + 1, array.length + 1, 0);
        Node node = new Node(array[index], height);
        node.left = convertFromArray(array, 2 * index + 1);
        node.right = convertFromArray(array, 2 * index + 2);
        return node;
    }

    private static int fitHeight(int position, int power, int height) {
        if (power <= 1) return -1;
        if (position >= power / 2 && position < power) return height;
        return fitHeight(position, power / 2, height + 1);
    }

    /**
     * Computes the length of the longest path from the specified node to a leaf
     *
     * @param node the root of the current tree
     * @return the height of the node if the tree is balanced, Integer.MIN_VALUE otherwise
     */
    public static int computeHeight(Node node) {
        if (node == null) return -1;

        int left = computeHeight(node.left);
        int right = computeHeight(node.right);

        if (left == Integer.MIN_VALUE || right == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        if (Math.abs(left - right) > 1) return Integer.MIN_VALUE;
        return Math.max(left, right) + 1;
    }

    /**
     * Creates a node with the specified value and attaches it to an AVL tree
     *
     * @param node  the root of the current tree
     * @param value the value of a node to attach
     * @return the root of the modified tree
     */
    public static Node attach(Node node, int value) {
        if (node == null)
            return new Node(value);

        if (value < node.value)
            node.left = attach(node.left, value);
        else if (node.value < value)
            node.right = attach(node.right, value);

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        int balance = getBalance(node);

        if (balance > 1) {
            if (node.left.value < value)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1) {
            if (value < node.right.value)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Detaches a node with the specified value from an AVL tree
     *
     * @param node  the root of the current tree
     * @param value the value of a node to detach
     * @return the root of the modified tree
     */
    public static Node detach(Node node, int value) {
        if (node == null)
            return null;

        if (node.value == value) {
            if (node.left != null && node.right != null) {
                Node successor = findSuccessor(node);
                node.value = successor.value;
                node.right = detach(node.right, successor.value);
            } else {
                if (node.right == null) return node.left;
                return node.right;
            }
        } else if (value < node.value) {
            node.left = detach(node.left, value);
        } else {
            node.right = detach(node.right, value);
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private static int getHeight(Node node) {
        if (node == null) return -1;
        return node.height;
    }

    private static int getBalance(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private static Node rotateLeft(Node first) {
        Node second = first.right;
        first.right = second.left;
        second.left = first;
        first.height = Math.max(getHeight(first.left), getHeight(first.right)) + 1;
        second.height = Math.max(getHeight(second.left), getHeight(second.right)) + 1;
        return second;
    }

    private static Node rotateRight(Node first) {
        Node second = first.left;
        first.left = second.right;
        second.right = first;
        first.height = Math.max(getHeight(first.left), getHeight(first.right)) + 1;
        second.height = Math.max(getHeight(second.left), getHeight(second.right)) + 1;
        return second;
    }

    private static Node findSuccessor(Node node) {
        Node successor = node.right;
        while (successor.left != null)
            successor = successor.left;
        return successor;
    }

    @Getter
    @AllArgsConstructor
    public static class Node {
        private int value;
        private int height;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
            this.height = 0;
            this.left = null;
            this.right = null;
        }

        public Node(int value, int height) {
            this.value = value;
            this.height = height;
            this.left = null;
            this.right = null;
        }
    }
}
