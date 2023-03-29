package com.rafalzelazko.algorithms.tree;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CompleteBinaryTree {
    /**
     * Collects values of all nodes in a complete binary tree in the inorder fashion
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
     * Creates a complete binary tree from its array representation
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
        Node node = new Node(array[index]);
        node.left = convertFromArray(array, 2 * index + 1);
        node.right = convertFromArray(array, 2 * index + 2);
        return node;
    }

    /**
     * Creates a node with the specified value and inserts it into a complete binary tree
     *
     * @param node  the root of the current tree
     * @param value the value of a node to insert
     * @return the root of the modified tree
     */
    public static Node insert(Node node, int value) {
        if (node == null)
            return new Node(value);

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.left == null) {
                current.left = new Node(value);
                break;
            } else if (current.right == null) {
                current.right = new Node(value);
                break;
            } else {
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }

        return node;
    }

    /**
     * Removes a node with the specified value from a complete binary tree
     *
     * @param node  the root of the current tree
     * @param value the value of a node to remove
     * @return the root of the modified tree
     */
    public static Node remove(Node node, int value) {
        if (node == null)
            return null;

        if (node.left == null && node.right == null) {
            if (node.value == value)
                return null;
            return node;
        }

        Node current = null;
        Node target = null;
        Node parent = null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current.value == value)
                target = current;

            if (current.left != null) {
                parent = current;
                queue.offer(current.left);
            }
            if (current.right != null) {
                parent = current;
                queue.offer(current.right);
            }
        }

        if (target != null) {
            target.value = current.value;

            if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }

        return node;
    }

    @AllArgsConstructor
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
