package com.rafalzelazko.algorithms.tree;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree {
    /**
     * Collects values of all nodes in a red-black tree in the inorder fashion
     * <ul>
     * <li> The node must not be null
     * </ul>
     *
     * @param node the root of the current tree
     * @return the collected values
     */
    public static List<Integer> collectValues(Node node) {
        if (node.value == Integer.MIN_VALUE) return new ArrayList<>();
        List<Integer> collected = collectValues(node.left);
        collected.add(node.value);
        collected.addAll(collectValues(node.right));
        return collected;
    }

    /**
     * Collects colours of all nodes in a red-black tree in the inorder fashion
     * <ul>
     * <li> The node must not be null
     * </ul>
     *
     * @param node the root of the current tree
     * @return the collected colours
     */
    public static List<Integer> collectColours(Node node) {
        if (node.value == Integer.MIN_VALUE) return new ArrayList<>();
        List<Integer> collected = collectColours(node.left);
        collected.add(node.colour);
        collected.addAll(collectColours(node.right));
        return collected;
    }

    /**
     * Collects parents of all nodes in a red-black tree in the inorder fashion
     * <ul>
     * <li> The node must not be null
     * </ul>
     *
     * @param node the root of the current tree
     * @return the collected values of the parents, -1 for each null
     */
    public static List<Integer> collectParents(Node node) {
        if (node.value == Integer.MIN_VALUE) return new ArrayList<>();
        List<Integer> collected = collectParents(node.left);
        if (node.parent != null) collected.add(node.parent.value);
        else collected.add(-1);
        collected.addAll(collectParents(node.right));
        return collected;
    }

    /**
     * Creates and builds a red-black tree from its array representation
     * <ul>
     * <li> The arrays must not be null
     * <li> The arrays must have the same length
     * <li> The arrays must match one another
     * <li> The index must be non-negative
     * </ul>
     *
     * @param array   the array representation of the tree
     * @param colours the colours of all nodes in the tree
     * @param index   the index of the current tree root
     * @return the root of the created tree
     */
    public static Node convertFromArray(int[] array, int[] colours, int index) {
        if (index >= array.length || array[index] == -1)
            return new Node(null);

        Node node = new Node(array[index], colours[index]);
        node.left = convertFromArray(array, colours, 2 * index + 1);
        node.right = convertFromArray(array, colours, 2 * index + 2);

        node.left.parent = node;
        node.right.parent = node;
        return node;
    }

    /**
     * Checks whether a red-black tree is coloured properly
     * <ul>
     * <li> The node must not be null
     * </ul>
     *
     * @param node the root of the current tree
     * @return true if the tree is coloured properly, false otherwise
     */
    public static boolean isColouredProperly(Node node) {
        if (node.colour != 0) return false;
        if (!areLeavesBlack(node)) return false;
        if (!areColoursLegal(node)) return false;
        if (!hasRedProperty(node)) return false;
        return computeBlackHeight(node) != Integer.MIN_VALUE;
    }

    private static boolean areLeavesBlack(Node node) {
        if (node.value == Integer.MIN_VALUE) return node.colour == 0;
        return areLeavesBlack(node.left) && areLeavesBlack(node.right);
    }

    private static boolean areColoursLegal(Node node) {
        if (node == null) return true;
        if (areColoursLegal(node.left) && areColoursLegal(node.right)) {
            return node.colour == 0 || node.colour == 1;
        }
        return false;
    }

    private static boolean hasRedProperty(Node node) {
        if (node.value == Integer.MIN_VALUE) return true;
        if (hasRedProperty(node.left) && hasRedProperty(node.right)) {
            return node.colour == 0 || (node.left.colour == 0 && node.right.colour == 0);
        }
        return false;
    }

    private static int computeBlackHeight(Node node) {
        if (node.value == Integer.MIN_VALUE) return 1;
        int left = computeBlackHeight(node.left);
        int right = computeBlackHeight(node.right);

        if (left == Integer.MIN_VALUE || right == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        if (left != right) return Integer.MIN_VALUE;
        return left + 1 - node.colour;
    }

    /**
     * Creates a node with the specified value and inserts it into a red-black tree
     * <ul>
     * <li> The node must not be null
     * </ul>
     *
     * @param node  the root of the current tree
     * @param value the value of a node to insert
     * @return the root of the modified tree
     */
    public static Node insert(Node node, int value) {
        Node current = node;
        Node parent = null;

        while (current.value != Integer.MIN_VALUE) {
            parent = current;
            if (value < current.value)
                current = current.left;
            else if (current.value < value)
                current = current.right;
            else return node;
        }

        if (parent == null)
            return new Node(value, 0);

        current = new Node(value, 1);
        current.setParent(parent);

        if (value < parent.value) parent.left = current;
        else parent.right = current;

        if (parent.parent == null)
            return node;

        return correctInsertion(node, current);
    }

    private static Node correctInsertion(Node node, Node current) {
        while (current.parent.colour == 1) {
            Node uncle = current.parent.parent.left;
            if (current.parent == uncle)
                uncle = current.parent.parent.right;

            if (uncle.colour == 1) {
                current.parent.parent.colour = 1;
                current.parent.colour = 0;
                uncle.colour = 0;
                current = current.parent.parent;
            } else {
                if (current.parent == current.parent.parent.right) {
                    if (current == current.parent.left) {
                        current = current.parent;
                        node = rotateRight(node, current);
                    }
                    current.parent.parent.colour = 1;
                    current.parent.colour = 0;
                    node = rotateLeft(node, current.parent.parent);
                } else {
                    if (current == current.parent.right) {
                        current = current.parent;
                        node = rotateLeft(node, current);
                    }
                    current.parent.parent.colour = 1;
                    current.parent.colour = 0;
                    node = rotateRight(node, current.parent.parent);
                }
            }
            if (current == node) {
                node.colour = 0;
                break;
            }
        }
        return node;
    }

    /**
     * Removes a node with the specified value from a red-black tree
     * <ul>
     * <li> The node must not be null
     * </ul>
     *
     * @param node  the root of the current tree
     * @param value the value of a node to remove
     * @return the root of the modified tree
     */
    public static Node remove(Node node, int value) {
        Node current = node;

        while (current.value != Integer.MIN_VALUE) {
            if (value < current.value)
                current = current.left;
            else if (current.value < value)
                current = current.right;
            else break;
        }

        if (current.value == Integer.MIN_VALUE)
            return node;

        int colour = current.colour;
        Node child;

        boolean hasLeftChild = current.left.value != Integer.MIN_VALUE;
        boolean hasRightChild = current.right.value != Integer.MIN_VALUE;

        if (hasLeftChild && hasRightChild) {
            Node successor = findSuccessor(current);
            child = successor.right;
            colour = successor.colour;
            successor.colour = current.colour;

            if (successor.parent != current) {
                node = substitute(node, successor, child);
                successor.right = current.right;
            }

            successor.right.parent = successor;
            node = substitute(node, current, successor);
            successor.left = current.left;
            successor.left.parent = successor;
        } else {
            if (hasLeftChild) child = current.left;
            else child = current.right;
            node = substitute(node, current, child);
        }

        if (colour == 1) return node;
        return correctRemoval(node, child);
    }

    private static Node correctRemoval(Node node, Node current) {
        while (current != node && current.colour == 0) {
            if (current == current.parent.left) {
                Node sibling = current.parent.right;

                if (sibling.colour == 1) {
                    sibling.colour = 0;
                    current.parent.colour = 1;
                    node = rotateLeft(node, current.parent);
                    sibling = current.parent.right;
                }

                if (sibling.left.colour == 1 || sibling.right.colour == 1) {
                    if (sibling.right.colour == 0) {
                        sibling.left.colour = 0;
                        sibling.colour = 1;
                        node = rotateRight(node, sibling);
                        sibling = current.parent.right;
                    }

                    sibling.colour = current.parent.colour;
                    current.parent.colour = 0;
                    sibling.right.colour = 0;
                    node = rotateLeft(node, current.parent);
                    current = node;
                } else {
                    sibling.colour = 1;
                    current = current.parent;
                }
            } else {
                Node sibling = current.parent.left;

                if (sibling.colour == 1) {
                    sibling.colour = 0;
                    current.parent.colour = 1;
                    node = rotateRight(node, current.parent);
                    sibling = current.parent.left;
                }

                if (sibling.left.colour == 1 || sibling.right.colour == 1) {
                    if (sibling.left.colour == 0) {
                        sibling.right.colour = 0;
                        sibling.colour = 1;
                        node = rotateLeft(node, sibling);
                        sibling = current.parent.left;
                    }

                    sibling.colour = current.parent.colour;
                    current.parent.colour = 0;
                    sibling.left.colour = 0;
                    node = rotateRight(node, current.parent);
                    current = node;
                } else {
                    sibling.colour = 1;
                    current = current.parent;
                }
            }
        }
        current.colour = 0;
        return node;
    }

    private static Node rotateLeft(Node node, Node first) {
        Node second = first.right;
        first.right = second.left;
        first.right.parent = first;
        node = substitute(node, first, second);
        second.left = first;
        first.parent = second;
        return node;
    }

    private static Node rotateRight(Node node, Node first) {
        Node second = first.left;
        first.left = second.right;
        first.left.parent = first;
        node = substitute(node, first, second);
        second.right = first;
        first.parent = second;
        return node;
    }

    private static Node substitute(Node node, Node first, Node second) {
        second.parent = first.parent;
        if (first.parent == null) {
            node = second;
        } else if (first.parent.left == first) {
            first.parent.left = second;
        } else {
            first.parent.right = second;
        }
        return node;
    }

    private static Node findSuccessor(Node node) {
        Node successor = node.right;
        while (successor.left.value != Integer.MIN_VALUE)
            successor = successor.left;
        return successor;
    }

    public static class Node {
        private final int value;
        private int colour;
        private Node left;
        private Node right;
        @Setter
        private Node parent;

        public Node(int value, int colour, Node left, Node right) {
            this.value = value;
            this.colour = colour;

            if (left != null) this.left = left;
            else this.left = new Node(this);

            if (right != null) this.right = right;
            else this.right = new Node(this);

            this.parent = null;
        }

        public Node(int value, int colour) {
            this.value = value;
            this.colour = colour;
            this.left = new Node(this);
            this.right = new Node(this);
            this.parent = null;
        }

        public Node(Node parent) {
            this.value = Integer.MIN_VALUE;
            this.colour = 0;
            this.left = null;
            this.right = null;
            this.parent = parent;
        }
    }
}
