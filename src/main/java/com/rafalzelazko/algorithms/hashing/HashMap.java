package com.rafalzelazko.algorithms.hashing;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Setter
public class HashMap {
    private Node[] hashTable;
    private Function<String, Integer> hashFunction;
    private int capacity;
    private int occupied;

    public HashMap() {
        this.hashTable = new Node[13];
        this.hashFunction = HashMap::hashFunction;
        this.capacity = 13;
        this.occupied = 0;
    }

    /**
     * Creates an instance of and builds a hash map according to the specified
     * arrays and hash function
     * <ul>
     * <li> The arrays must be not null
     * <li> The arrays must have the same length
     * <li> The arrays must match one another
     * <li> The hash function must not be null
     * </ul>
     *
     * @param keys     the key array
     * @param values   the value array
     * @param function the hash function
     */
    public HashMap(String[] keys, int[] values, Function<String, Integer> function) {
        int capacity = Math.max(2 * keys.length + 1, 13);
        if (keys.length >= 1 << 30) capacity = keys.length;

        this.hashTable = new Node[capacity];
        this.hashFunction = function;
        this.capacity = capacity;
        this.occupied = 0;

        for (int index = 0; index < keys.length; index++)
            this.insert(keys[index], values[index]);
    }

    /**
     * Computes a hash code for the specified key
     * <ul>
     * <li> The string must not be null
     * </ul>
     *
     * @param key the key
     * @return the hash code
     */
    public static int hashFunction(String key) {
        int length = key.length();
        int hash = 0;

        for (int index = 0; index < length; index++)
            hash = 19 * hash + key.charAt(index);

        return hash;
    }

    /**
     * Collects keys of all entries in this map
     *
     * @return the collected keys
     */
    public List<String> collectKeys() {
        List<String> collected = new ArrayList<>();
        for (Node node : this.hashTable) {
            Node current = node;
            while (current != null) {
                collected.add(current.key);
                current = current.next;
            }
        }
        return collected;
    }

    /**
     * Collects values of all entries in this map
     *
     * @return the collected values
     */
    public List<Integer> collectValues() {
        List<Integer> collected = new ArrayList<>();
        for (Node node : this.hashTable) {
            Node current = node;
            while (current != null) {
                collected.add(current.value);
                current = current.next;
            }
        }
        return collected;
    }

    /**
     * Creates an entry with the specified key and value and inserts it into this map
     * <ul>
     * <li> The string must not be null
     * </ul>
     *
     * @param key   the key of an entry to insert
     * @param value the value of an entry to insert
     */
    public void insert(String key, int value) {
        int hashCode = this.hashFunction.apply(key);
        int index = Math.abs(hashCode) % this.capacity;
        Node current = this.hashTable[index];

        while (current != null) {
            if (current.hashCode == hashCode && current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        if (this.hashTable[index] == null)
            this.occupied++;

        this.hashTable[index] = new Node(key, value, hashCode, this.hashTable[index]);

        if (this.occupied > 0.75 * this.capacity)
            if (this.capacity < 1 << 30)
                this.changeCapacity(2 * this.capacity + 1);
    }

    /**
     * Removes an entry with the specified key from this map and returns its value
     * <ul>
     * <li> The string must not be null
     * </ul>
     *
     * @param key the key of an entry to remove
     * @return the value of an entry to remove, Integer.MIN_VALUE if not found
     */
    public int remove(String key) {
        int hashCode = this.hashFunction.apply(key);
        int index = Math.abs(hashCode) % this.capacity;
        Node current = this.hashTable[index];
        Node previous = null;

        while (current != null) {
            if (current.hashCode == hashCode && current.key.equals(key))
                break;
            previous = current;
            current = current.next;
        }

        if (current == null)
            return Integer.MIN_VALUE;

        if (previous == null) {
            this.hashTable[index] = null;
            this.occupied--;
        } else {
            previous.next = current.next;
        }

        if (this.occupied < 0.25 * this.capacity)
            if (this.capacity >= 27)
                this.changeCapacity((this.capacity - 1) / 2);

        return current.value;
    }

    private void changeCapacity(int capacity) {
        this.capacity = capacity;
        this.occupied = 0;

        Node[] input = this.hashTable.clone();
        this.hashTable = new Node[this.capacity];

        for (Node node : input)
            if (node != null)
                this.insert(node.key, node.value);
    }

    /**
     * Returns the value of an entry with the specified key in this map
     * <ul>
     * <li> The string must not be null
     * </ul>
     *
     * @param key the key of an entry to find
     * @return the value of an entry to find, Integer.MIN_VALUE if not found
     */
    public int find(String key) {
        int hashCode = this.hashFunction.apply(key);
        int index = Math.abs(hashCode) % this.capacity;
        Node current = this.hashTable[index];

        while (current != null) {
            if (current.hashCode == hashCode && current.key.equals(key))
                return current.value;
            current = current.next;
        }

        return Integer.MIN_VALUE;
    }

    /**
     * Checks whether this map is empty
     *
     * @return true if this map is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.occupied == 0;
    }

    @AllArgsConstructor
    public static class Node {
        private String key;
        private int value;
        private int hashCode;
        private Node next;
    }
}
