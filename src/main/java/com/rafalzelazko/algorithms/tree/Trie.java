package com.rafalzelazko.algorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trie {
    /**
     * Collects all characters in a trie in the preorder fashion
     *
     * @param node the root of the current tree
     * @return the collected characters
     */
    public static List<Character> collectCharacters(Node node) {
        List<Character> collected = new ArrayList<>();
        if (node == null) return collected;

        for (int location = 0; location < 26; location++) {
            if (node.children[location] != null) {
                collected.add((char) (location + 'a'));
                collected.addAll(collectCharacters(node.children[location]));
            }
        }
        return collected;
    }

    /**
     * Collects all words in a trie in alphabetical order
     * <ul>
     * <li> The node must not be null
     * <li> The list must not be null
     * <li> The list must be mutable
     * </ul>
     *
     * @param node   the root of the current tree
     * @param prefix the current prefix
     * @return the collected words
     */
    public static List<String> collectWords(Node node, List<Character> prefix) {
        List<String> collected = new ArrayList<>();

        if (node.lastCharacter) {
            String word = prefix.stream().map(String::valueOf).collect(Collectors.joining());
            collected.add(word);
        }

        for (int location = 0; location < 26; location++) {
            if (node.children[location] != null) {
                prefix.add((char) (location + 'a'));
                collected.addAll(collectWords(node.children[location], prefix));
                prefix.remove(prefix.size() - 1);
            }
        }
        return collected;
    }

    /**
     * Inserts the specified word into a trie
     * <ul>
     * <li> The node must not be null
     * <li> The string must not be null
     * </ul>
     *
     * @param node the root of the current tree
     * @param word the word to insert
     */
    public static void insert(Node node, String word) {
        int length = word.length();
        Node current = node;

        for (int index = 0; index < length; index++) {
            char character = word.charAt(index);
            int location = character - 'a';

            if (current.children[location] == null)
                current.children[location] = new Node(false);

            current = current.children[location];
        }
        current.lastCharacter = true;
    }

    /**
     * Removes the specified word from a trie
     * <ul>
     * <li> The node must not be null
     * <li> The string must not be null
     * </ul>
     *
     * @param node the root of the current tree
     * @param word the word to remove
     */
    public static void remove(Node node, String word) {
        int length = word.length();
        Node current = node;

        Node lastForkNode = null;
        int lastForkLocation = 0;

        for (int index = 0; index < length; index++) {
            char character = word.charAt(index);
            int location = character - 'a';

            if (current.children[location] == null)
                return;

            if (current.lastCharacter || hasChildNotAtLocation(current, location)) {
                lastForkNode = current;
                lastForkLocation = location;
            }

            current = current.children[location];
        }

        if (current.lastCharacter) {
            if (hasChildNotAtLocation(current, -1)) {
                current.lastCharacter = false;
            } else if (lastForkNode != null) {
                lastForkNode.children[lastForkLocation] = null;
            } else {
                node.children = new Node[26];
            }
        }
    }

    private static boolean hasChildNotAtLocation(Node node, int location) {
        for (int index = 0; index < 26; index++)
            if (node.children[index] != null && location != index)
                return true;
        return false;
    }

    public static class Node {
        private Node[] children;
        private boolean lastCharacter;

        public Node(boolean lastCharacter) {
            this.children = new Node[26];
            this.lastCharacter = lastCharacter;
        }

        public void setChild(Node child, int location) {
            this.children[location] = child;
        }
    }
}
