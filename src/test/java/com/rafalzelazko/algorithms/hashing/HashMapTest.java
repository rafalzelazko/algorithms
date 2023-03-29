package com.rafalzelazko.algorithms.hashing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.rafalzelazko.algorithms.hashing.HashMap.Node;
import static com.rafalzelazko.algorithms.hashing.HashMap.hashFunction;
import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {
    @Test
    public void hashFunction__typicalCase() {
        int hash = hashFunction("Stockholm");
        assertEquals(-1_611_642_150, hash);
    }

    @Test
    public void hashFunction__singleItem() {
        int hash = hashFunction("z");
        assertEquals(122, hash);
    }

    @Test
    public void hashFunction__noItems() {
        int hash = hashFunction("");
        assertEquals(0, hash);
    }

    @Test
    public void collectKeys__typicalCase() {
        Node[] content = new Node[] {
            new Node("Munich", 13, 206_701_560, null),
            new Node("Stockholm", 18, -1_611_642_150, null),
            new Node("Tokyo", 19, 11_749_350, null)
        };

        Node[] hashTable = new Node[13];
        System.arraycopy(content, 0, hashTable, 0, content.length);

        HashMap hashMap = new HashMap();
        hashMap.setHashTable(hashTable);
        hashMap.setOccupied(3);

        assertEquals(List.of("Munich", "Stockholm", "Tokyo"), hashMap.collectKeys());
    }

    @Test
    public void collectKeys__singleItem() {
        Node[] hashTable = new Node[13];
        hashTable[1] = new Node("Stockholm", 18, -1_611_642_150, null);

        HashMap hashMap = new HashMap();
        hashMap.setHashTable(hashTable);
        hashMap.setOccupied(1);

        assertEquals(List.of("Stockholm"), hashMap.collectKeys());
    }

    @Test
    public void collectKeys__noItems() {
        HashMap hashMap = new HashMap();
        assertEquals(List.of(), hashMap.collectKeys());
    }

    @Test
    public void collectValues__typicalCase() {
        Node[] content = new Node[] {
            new Node("Munich", 13, 206_701_560, null),
            new Node("Stockholm", 18, -1_611_642_150, null),
            new Node("Tokyo", 19, 11_749_350, null)
        };

        Node[] hashTable = new Node[13];
        System.arraycopy(content, 0, hashTable, 0, content.length);

        HashMap hashMap = new HashMap();
        hashMap.setHashTable(hashTable);
        hashMap.setOccupied(3);

        assertEquals(List.of(13, 18, 19), hashMap.collectValues());
    }

    @Test
    public void collectValues__singleItem() {
        Node[] hashTable = new Node[13];
        hashTable[1] = new Node("Stockholm", 18, -1_611_642_150, null);

        HashMap hashMap = new HashMap();
        hashMap.setHashTable(hashTable);
        hashMap.setOccupied(1);

        assertEquals(List.of(18), hashMap.collectValues());
    }

    @Test
    public void collectValues__noItems() {
        HashMap hashMap = new HashMap();
        assertEquals(List.of(), hashMap.collectValues());
    }

    @Test
    public void constructor__typicalCase() {
        String[] keys = new String[] {"Stockholm", "Munich", "Tokyo"};
        int[] values = new int[] {18, 13, 19};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        assertEquals(List.of("Munich", "Stockholm", "Tokyo"), hashMap.collectKeys());
        assertEquals(List.of(13, 18, 19), hashMap.collectValues());
    }

    @Test
    public void constructor__capacityChange() {
        String[] keys = new String[] {"a", "c", "e", "g", "i", "k", "m"};
        int[] values = new int[] {7, 9, 11, 13, 0, 2, 4};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        assertEquals(List.of("i", "k", "m", "a", "c", "e", "g"), hashMap.collectKeys());
        assertEquals(List.of(0, 2, 4, 7, 9, 11, 13), hashMap.collectValues());
    }

    @Test
    public void constructor__singleItem() {
        String[] keys = new String[] {"Stockholm"};
        int[] values = new int[] {18};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        assertEquals(List.of("Stockholm"), hashMap.collectKeys());
        assertEquals(List.of(18), hashMap.collectValues());
    }

    @Test
    public void constructor__noItems() {
        String[] keys = new String[] {};
        int[] values = new int[] {};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        assertEquals(List.of(), hashMap.collectKeys());
        assertEquals(List.of(), hashMap.collectValues());
    }

    @Test
    public void insert__newKey() {
        String[] keys = new String[] {"Stockholm", "Munich", "Tokyo"};
        int[] values = new int[] {18, 13, 19};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        hashMap.insert("Paris", 16);
        assertEquals(List.of("Munich", "Paris", "Stockholm", "Tokyo"), hashMap.collectKeys());
        assertEquals(List.of(13, 16, 18, 19), hashMap.collectValues());
    }

    @Test
    public void insert__existingKey() {
        String[] keys = new String[] {"Stockholm", "Munich", "Tokyo"};
        int[] values = new int[] {18, 13, 19};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        hashMap.insert("Stockholm", 26);
        assertEquals(List.of("Munich", "Stockholm", "Tokyo"), hashMap.collectKeys());
        assertEquals(List.of(13, 26, 19), hashMap.collectValues());
    }

    @Test
    public void insert__collision() {
        HashMap hashMap = new HashMap();
        hashMap.setHashFunction(String::length);
        hashMap.insert("Tokyo", 19);
        hashMap.insert("Paris", 16);
        assertEquals(List.of("Paris", "Tokyo"), hashMap.collectKeys());
        assertEquals(List.of(16, 19), hashMap.collectValues());
    }

    @Test
    public void insert__passingThreshold__true() {
        String[] keys = new String[] {"a", "c", "e", "g", "i", "k", "m", "o", "q", "s"};
        int[] values = new int[] {16, 18, 20, 22, 24, 26, 1, 3, 5, 7};

        HashMap hashMap = new HashMap();
        for (int index = 0; index < keys.length; index++)
            hashMap.insert(keys[index], values[index]);

        assertEquals(List.of("m", "o", "q", "s", "a", "c", "e", "g", "i", "k"), hashMap.collectKeys());
        assertEquals(List.of(1, 3, 5, 7, 16, 18, 20, 22, 24, 26), hashMap.collectValues());
    }

    @Test
    public void insert__passingThreshold__false() {
        String[] keys = new String[] {"a", "c", "e", "g", "i", "k", "m", "o", "q", "s"};
        int[] values = new int[] {16, 18, 20, 22, 24, 26, 1, 3, 5, 7};

        HashMap hashMap = new HashMap();
        hashMap.setHashFunction(String::length);

        for (int index = 0; index < keys.length; index++)
            hashMap.insert(keys[index], values[index]);

        assertEquals(List.of("s", "q", "o", "m", "k", "i", "g", "e", "c", "a"), hashMap.collectKeys());
        assertEquals(List.of(7, 5, 3, 1, 26, 24, 22, 20, 18, 16), hashMap.collectValues());
    }

    @Test
    public void insert__singleItem() {
        HashMap hashMap = new HashMap();
        hashMap.insert("Stockholm", 18);
        hashMap.insert("Munich", 13);
        assertEquals(List.of("Munich", "Stockholm"), hashMap.collectKeys());
        assertEquals(List.of(13, 18), hashMap.collectValues());
    }

    @Test
    public void insert__noItems() {
        HashMap hashMap = new HashMap();
        hashMap.insert("Stockholm", 18);
        assertEquals(List.of("Stockholm"), hashMap.collectKeys());
        assertEquals(List.of(18), hashMap.collectValues());
    }

    @Test
    public void remove__typicalCase() {
        String[] keys = new String[] {"Stockholm", "Munich", "Tokyo"};
        int[] values = new int[] {18, 13, 19};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        int value = hashMap.remove("Stockholm");
        assertEquals(18, value);
        assertEquals(List.of("Munich", "Tokyo"), hashMap.collectKeys());
        assertEquals(List.of(13, 19), hashMap.collectValues());
    }

    @Test
    public void remove__missingKey() {
        String[] keys = new String[] {"Stockholm", "Munich", "Tokyo"};
        int[] values = new int[] {18, 13, 19};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        int value = hashMap.remove("Paris");
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of("Munich", "Stockholm", "Tokyo"), hashMap.collectKeys());
        assertEquals(List.of(13, 18, 19), hashMap.collectValues());
    }

    @Test
    public void remove__collision() {
        String[] keys = new String[] {"Tokyo", "Paris"};
        int[] values = new int[] {19, 16};
        HashMap hashMap = new HashMap(keys, values, String::length);
        int value = hashMap.remove("Tokyo");
        assertEquals(19, value);
        assertEquals(List.of("Paris"), hashMap.collectKeys());
        assertEquals(List.of(16), hashMap.collectValues());
    }

    @Test
    public void remove__passingThreshold__true() {
        String[] keys = new String[] {"a", "c", "e", "g", "i", "k", "m", "o", "q", "s"};
        int[] values = new int[] {6, 8, 10, 12, 1, 3, 5, 7, 9, 11};

        HashMap hashMap = new HashMap();
        for (int index = 0; index < keys.length; index++)
            hashMap.insert(keys[index], values[index]);

        List.of("c", "g", "k", "o").forEach(hashMap::remove);
        assertEquals(List.of("i", "m", "a", "q", "e", "s"), hashMap.collectKeys());
        assertEquals(List.of(1, 5, 6, 9, 10, 11), hashMap.collectValues());
    }

    @Test
    public void remove__passingThreshold__false() {
        String[] keys = new String[] {"a", "c", "e", "g", "i", "k", "m", "o", "q", "s"};
        int[] values = new int[] {16, 18, 20, 22, 24, 26, 1, 3, 5, 7};

        HashMap hashMap = new HashMap();
        hashMap.setHashFunction(String::length);
        for (int index = 0; index < keys.length; index++)
            hashMap.insert(keys[index], values[index]);

        List.of("c", "g", "k", "o").forEach(hashMap::remove);
        assertEquals(List.of("s", "q", "m", "i", "e", "a"), hashMap.collectKeys());
        assertEquals(List.of(7, 5, 1, 24, 20, 16), hashMap.collectValues());
    }

    @Test
    public void remove__singleItem() {
        HashMap hashMap = new HashMap();
        hashMap.insert("Stockholm", 18);
        int value = hashMap.remove("Stockholm");
        assertEquals(18, value);
        assertEquals(List.of(), hashMap.collectKeys());
        assertEquals(List.of(), hashMap.collectValues());
    }

    @Test
    public void remove__noItems() {
        HashMap hashMap = new HashMap();
        int value = hashMap.remove("Stockholm");
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), hashMap.collectKeys());
        assertEquals(List.of(), hashMap.collectValues());
    }

    @Test
    public void find__typicalCase() {
        String[] keys = new String[] {"Stockholm", "Munich", "Tokyo"};
        int[] values = new int[] {18, 13, 19};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        int value = hashMap.find("Stockholm");
        assertEquals(18, value);
        assertEquals(List.of("Munich", "Stockholm", "Tokyo"), hashMap.collectKeys());
        assertEquals(List.of(13, 18, 19), hashMap.collectValues());
    }

    @Test
    public void find__missingKey() {
        String[] keys = new String[] {"Stockholm", "Munich", "Tokyo"};
        int[] values = new int[] {18, 13, 19};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        int value = hashMap.find("Paris");
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of("Munich", "Stockholm", "Tokyo"), hashMap.collectKeys());
        assertEquals(List.of(13, 18, 19), hashMap.collectValues());
    }

    @Test
    public void find__collision() {
        String[] keys = new String[] {"Tokyo", "Paris"};
        int[] values = new int[] {19, 16};
        HashMap hashMap = new HashMap(keys, values, String::length);
        int value = hashMap.find("Tokyo");
        assertEquals(19, value);
        assertEquals(List.of("Paris", "Tokyo"), hashMap.collectKeys());
        assertEquals(List.of(16, 19), hashMap.collectValues());
    }

    @Test
    public void find__singleItem() {
        HashMap hashMap = new HashMap();
        hashMap.insert("Stockholm", 18);
        int value = hashMap.find("Stockholm");
        assertEquals(18, value);
        assertEquals(List.of("Stockholm"), hashMap.collectKeys());
        assertEquals(List.of(18), hashMap.collectValues());
    }

    @Test
    public void find__noItems() {
        HashMap hashMap = new HashMap();
        int value = hashMap.find("Stockholm");
        assertEquals(Integer.MIN_VALUE, value);
        assertEquals(List.of(), hashMap.collectKeys());
        assertEquals(List.of(), hashMap.collectValues());
    }

    @Test
    public void isEmpty__typicalCase() {
        String[] keys = new String[] {"Stockholm", "Munich", "Tokyo"};
        int[] values = new int[] {18, 13, 19};
        HashMap hashMap = new HashMap(keys, values, HashMap::hashFunction);
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void isEmpty__singleItem() {
        HashMap hashMap = new HashMap();
        hashMap.insert("Stockholm", 18);
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void isEmpty__noItems() {
        HashMap hashMap = new HashMap();
        assertTrue(hashMap.isEmpty());
    }
}
