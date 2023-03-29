package com.rafalzelazko.algorithms.tree;

import lombok.AllArgsConstructor;

import java.util.*;

public class BinarySearchTree {
    /**
     * Collects values of all nodes in a binary search tree in the preorder fashion
     *
     * @param node the root of the current tree
     * @return the collected values
     */
    public static List<Integer> presentPreorder(Node node) {
        List<Integer> collected = new ArrayList<>();
        if (node == null) return collected;
        collected.add(node.value);
        collected.addAll(presentPreorder(node.left));
        collected.addAll(presentPreorder(node.right));
        return collected;
    }

    /**
     * Collects values of all nodes in a binary search tree in the inorder fashion
     *
     * @param node the root of the current tree
     * @return the collected values
     */
    public static List<Integer> presentInorder(Node node) {
        List<Integer> collected = new ArrayList<>();
        if (node == null) return collected;
        collected.addAll(presentInorder(node.left));
        collected.add(node.value);
        collected.addAll(presentInorder(node.right));
        return collected;
    }

    /**
     * Collects values of all nodes in a binary search tree in the postorder fashion
     *
     * @param node the root of the current tree
     * @return the collected values
     */
    public static List<Integer> presentPostorder(Node node) {
        List<Integer> collected = new ArrayList<>();
        if (node == null) return collected;
        collected.addAll(presentPostorder(node.left));
        collected.addAll(presentPostorder(node.right));
        collected.add(node.value);
        return collected;
    }

    /**
     * Collects values of all nodes in a binary search tree in the level order fashion
     *
     * @param node the root of the current tree
     * @return the collected values
     */
    public static List<Integer> presentLevelOrder(Node node) {
        List<Integer> collected = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        if (node != null) queue.offer(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            collected.add(current.value);
            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }

        return collected;
    }

    /**
     * Collects values of all nodes in a binary search tree in the alternate order fashion
     *
     * @param node the root of the current tree
     * @return the collected values
     */
    public static List<Integer> presentAlternateOrder(Node node) {
        List<Integer> collected = new ArrayList<>();
        Deque<Node> deque = new LinkedList<>();
        if (node != null) deque.offerFirst(node);

        int currentLevelCount = 1;
        int nextLevelCount = 0;
        boolean fromLeft = true;
        Node current;

        while (!deque.isEmpty()) {
            if (fromLeft) {
                current = deque.pollFirst();

                if (current.left != null) {
                    deque.offerLast(current.left);
                    nextLevelCount++;
                }
                if (current.right != null) {
                    deque.offerLast(current.right);
                    nextLevelCount++;
                }
            } else {
                current = deque.pollLast();

                if (current.right != null) {
                    deque.offerFirst(current.right);
                    nextLevelCount++;
                }
                if (current.left != null) {
                    deque.offerFirst(current.left);
                    nextLevelCount++;
                }
            }

            collected.add(current.value);
            currentLevelCount--;

            if (currentLevelCount == 0) {
                currentLevelCount = nextLevelCount;
                nextLevelCount = 0;
                fromLeft = !fromLeft;
            }
        }

        return collected;
    }

    /**
     * Creates a binary search tree from its array representation
     * <ul>
     * <li> The array must not be null
     * </ul>
     *
     * @param array the array representation of the tree
     * @return the root of the created tree
     */
    public static Node convertFromArray(int[] array) {
        if (array.length == 0 || array[0] == -1)
            return null;

        Queue<LocatedNode> queue = new LinkedList<>();
        Node node = new Node(array[0]);
        queue.offer(new LocatedNode(node, 0));

        while (!queue.isEmpty()) {
            LocatedNode locatedNode = queue.poll();
            int left = 2 * locatedNode.location + 1;
            int right = 2 * locatedNode.location + 2;

            if (left < array.length && array[left] != -1) {
                locatedNode.node.left = new Node(array[left]);
                queue.offer(new LocatedNode(locatedNode.node.left, left));
            }

            if (right < array.length && array[right] != -1) {
                locatedNode.node.right = new Node(array[right]);
                queue.offer(new LocatedNode(locatedNode.node.right, right));
            }
        }

        return node;
    }

    /**
     * Creates a binary search tree from its preorder and inorder representations
     * <ul>
     * <li> The lists must not be null
     * <li> The lists must have the same length
     * <li> The lists must match one another
     * </ul>
     *
     * @param preorder the preorder representation of the tree
     * @param inorder  the inorder representation of the tree
     * @return the root of the created tree
     */
    public static Node convertFromPreorderAndInorder(
        List<Integer> preorder, List<Integer> inorder) {
        if (preorder.isEmpty())
            return null;

        Map<Integer, Integer> inorderIndices = createIndexMap(inorder);
        Stack<LimitedNode> stack = new Stack<>();

        int length = preorder.size();
        Node node = new Node(preorder.get(0));

        Node current = node;
        int start = 0, finish = length - 1;
        int inorderIndex = inorderIndices.get(current.value);

        for (int preorderIndex = 1; preorderIndex < length; preorderIndex++) {
            int childValue = preorder.get(preorderIndex);
            int childInorderIndex = inorderIndices.get(childValue);

            if (childInorderIndex >= start && childInorderIndex <= finish) {
                Node child = new Node(childValue);
                stack.push(new LimitedNode(current, start, finish));

                if (childInorderIndex <= inorderIndex - 1) {
                    current.left = child;
                    finish = inorderIndex - 1;
                } else {
                    current.right = child;
                    start = inorderIndex + 1;
                }

                current = child;
                inorderIndex = childInorderIndex;
            } else {
                LimitedNode limitedNode = stack.pop();
                current = limitedNode.node;
                start = limitedNode.start;
                finish = limitedNode.finish;
                inorderIndex = inorderIndices.get(current.value);
                preorderIndex--;
            }
        }

        return node;
    }

    /**
     * Creates a binary search tree from its postorder and inorder representations
     * <ul>
     * <li> The lists must not be null
     * <li> The lists must have the same length
     * <li> The lists must match one another
     * </ul>
     *
     * @param postorder the postorder representation of the tree
     * @param inorder   the inorder representation of the tree
     * @return the root of the created tree
     */
    public static Node convertFromPostorderAndInorder(
        List<Integer> postorder, List<Integer> inorder) {
        if (postorder.isEmpty())
            return null;

        Map<Integer, Integer> inorderIndices = createIndexMap(inorder);
        Stack<LimitedNode> stack = new Stack<>();

        int length = postorder.size();
        Node node = new Node(postorder.get(length - 1));

        Node current = node;
        int start = 0, finish = length - 1;
        int inorderIndex = inorderIndices.get(current.value);

        for (int postorderIndex = length - 2; postorderIndex >= 0; postorderIndex--) {
            int childValue = postorder.get(postorderIndex);
            int childInorderIndex = inorderIndices.get(childValue);

            if (childInorderIndex >= start && childInorderIndex <= finish) {
                Node child = new Node(childValue);
                stack.push(new LimitedNode(current, start, finish));

                if (childInorderIndex >= inorderIndex + 1) {
                    current.right = child;
                    start = inorderIndex + 1;
                } else {
                    current.left = child;
                    finish = inorderIndex - 1;
                }

                current = child;
                inorderIndex = childInorderIndex;
            } else {
                LimitedNode limitedNode = stack.pop();
                current = limitedNode.node;
                start = limitedNode.start;
                finish = limitedNode.finish;
                inorderIndex = inorderIndices.get(current.value);
                postorderIndex++;
            }
        }

        return node;
    }

    /**
     * Creates a binary search tree from its level order and inorder representations
     * <ul>
     * <li> The lists must not be null
     * <li> The lists must have the same length
     * <li> The lists must match one another
     * </ul>
     *
     * @param levelOrder the level order representation of the tree
     * @param inorder    the inorder representation of the tree
     * @return the root of the created tree
     */
    public static Node convertFromLevelOrderAndInorder(
        List<Integer> levelOrder, List<Integer> inorder) {
        if (levelOrder.isEmpty())
            return null;

        Map<Integer, Integer> inorderIndices = createIndexMap(inorder);
        Queue<LimitedNode> queue = new LinkedList<>();

        int length = levelOrder.size();
        int levelOrderIndex = 1;

        Node node = new Node(levelOrder.get(0));
        queue.offer(new LimitedNode(node, 0, length - 1));

        while (!queue.isEmpty()) {
            LimitedNode limitedNode = queue.poll();
            Node current = limitedNode.node;
            int start = limitedNode.start, finish = limitedNode.finish;
            int inorderIndex = inorderIndices.get(current.value);

            if (levelOrderIndex >= length)
                continue;

            int childValue = levelOrder.get(levelOrderIndex);
            int childInorderIndex = inorderIndices.get(childValue);

            if (childInorderIndex >= start && childInorderIndex <= inorderIndex - 1) {
                current.left = new Node(childValue);
                queue.offer(new LimitedNode(current.left, start, inorderIndex - 1));
                levelOrderIndex++;
            }

            if (levelOrderIndex >= length)
                continue;

            childValue = levelOrder.get(levelOrderIndex);
            childInorderIndex = inorderIndices.get(childValue);

            if (childInorderIndex >= inorderIndex + 1 && childInorderIndex <= finish) {
                current.right = new Node(childValue);
                queue.offer(new LimitedNode(current.right, inorderIndex + 1, finish));
                levelOrderIndex++;
            }
        }

        return node;
    }

    private static Map<Integer, Integer> createIndexMap(List<Integer> list) {
        Map<Integer, Integer> indices = new HashMap<>();
        for (int index = 0; index < list.size(); index++)
            indices.put(list.get(index), index);
        return indices;
    }

    /**
     * Creates a node with the specified value and inserts it into a binary search tree
     *
     * @param node  the root of the current tree
     * @param value the value of a node to insert
     * @return the root of the modified tree
     */
    public static Node insert(Node node, int value) {
        if (node == null)
            return new Node(value);

        if (value < node.value)
            node.left = insert(node.left, value);
        else if (node.value < value)
            node.right = insert(node.right, value);

        return node;
    }

    /**
     * Removes a node with the specified value from a binary search tree
     *
     * @param node  the root of the current tree
     * @param value the value of a node to remove
     * @return the root of the modified tree
     */
    public static Node remove(Node node, int value) {
        if (node == null)
            return null;

        if (node.value == value) {
            if (node.left != null && node.right != null) {
                Node successor = findSuccessor(node);
                node.value = successor.value;
                node.right = remove(node.right, successor.value);
            } else {
                if (node.right == null) return node.left;
                return node.right;
            }
        } else if (value < node.value) {
            node.left = remove(node.left, value);
        } else {
            node.right = remove(node.right, value);
        }

        return node;
    }

    private static Node findSuccessor(Node node) {
        Node successor = node.right;
        while (successor.left != null)
            successor = successor.left;
        return successor;
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

    @AllArgsConstructor
    private static class LocatedNode {
        private Node node;
        private int location;
    }

    @AllArgsConstructor
    private static class LimitedNode {
        private Node node;
        private int start;
        private int finish;
    }
}
