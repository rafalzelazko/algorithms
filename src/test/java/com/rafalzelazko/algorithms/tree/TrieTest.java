package com.rafalzelazko.algorithms.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.rafalzelazko.algorithms.tree.Trie.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrieTest {
    @Test
    public void collectCharacters__typicalCase() {
        Node centre = new Node(false);
        Node c = new Node(false);
        Node ca = new Node(false);
        Node can = new Node(true);
        Node cat = new Node(true);
        Node i = new Node(false);
        Node it = new Node(true);

        centre.setChild(c, 2);
        c.setChild(ca, 0);
        ca.setChild(can, 13);
        ca.setChild(cat, 19);
        centre.setChild(i, 8);
        i.setChild(it, 19);

        List<Character> characters = List.of('c', 'a', 'n', 't', 'i', 't');
        assertEquals(characters, collectCharacters(centre));
    }

    @Test
    public void collectCharacters__singleItem() {
        Node node = new Node(false);
        Node a = new Node(true);
        node.setChild(a, 0);
        List<Character> characters = List.of('a');
        assertEquals(characters, collectCharacters(node));
    }

    @Test
    public void collectCharacters__noItems() {
        Node node = new Node(false);
        List<Character> characters = List.of();
        assertEquals(characters, collectCharacters(node));
    }

    @Test
    public void collectWords__typicalCase() {
        Node centre = new Node(false);
        Node c = new Node(false);
        Node ca = new Node(false);
        Node can = new Node(true);
        Node cat = new Node(true);
        Node i = new Node(false);
        Node it = new Node(true);

        centre.setChild(c, 2);
        c.setChild(ca, 0);
        ca.setChild(can, 13);
        ca.setChild(cat, 19);
        centre.setChild(i, 8);
        i.setChild(it, 19);

        List<String> words = List.of("can", "cat", "it");
        assertEquals(words, collectWords(centre, new ArrayList<>()));
    }

    @Test
    public void collectWords__singleItem() {
        Node node = new Node(false);
        Node a = new Node(true);
        node.setChild(a, 0);
        List<String> words = List.of("a");
        assertEquals(words, collectWords(node, new ArrayList<>()));
    }

    @Test
    public void collectWords__noItems() {
        Node node = new Node(false);
        List<String> words = List.of();
        assertEquals(words, collectWords(node, new ArrayList<>()));
    }

    @Test
    public void insert__newValue() {
        Node centre = new Node(false);
        insert(centre, "can");
        insert(centre, "cat");
        insert(centre, "it");

        List<Character> characters = List.of('c', 'a', 'n', 't', 'i', 't');
        List<String> words = List.of("can", "cat", "it");

        assertEquals(characters, collectCharacters(centre));
        assertEquals(words, collectWords(centre, new ArrayList<>()));
    }

    @Test
    public void insert__existingValue() {
        Node centre = new Node(false);
        insert(centre, "can");
        insert(centre, "cat");
        insert(centre, "it");
        insert(centre, "cat");

        List<Character> characters = List.of('c', 'a', 'n', 't', 'i', 't');
        List<String> words = List.of("can", "cat", "it");

        assertEquals(characters, collectCharacters(centre));
        assertEquals(words, collectWords(centre, new ArrayList<>()));
    }

    @Test
    public void insert__emptyString() {
        Node centre = new Node(false);
        insert(centre, "can");
        insert(centre, "cat");
        insert(centre, "it");
        insert(centre, "");

        List<Character> characters = List.of('c', 'a', 'n', 't', 'i', 't');
        List<String> words = List.of("", "can", "cat", "it");

        assertEquals(characters, collectCharacters(centre));
        assertEquals(words, collectWords(centre, new ArrayList<>()));
    }

    @Test
    public void insert__singleItem() {
        Node node = new Node(false);
        insert(node, "can");
        insert(node, "cat");

        List<Character> characters = List.of('c', 'a', 'n', 't');
        List<String> words = List.of("can", "cat");

        assertEquals(characters, collectCharacters(node));
        assertEquals(words, collectWords(node, new ArrayList<>()));
    }

    @Test
    public void insert__noItems() {
        Node node = new Node(false);
        insert(node, "can");

        List<Character> characters = List.of('c', 'a', 'n');
        List<String> words = List.of("can");

        assertEquals(characters, collectCharacters(node));
        assertEquals(words, collectWords(node, new ArrayList<>()));
    }

    @Test
    public void remove__sharingPrefix() {
        Node centre = new Node(false);
        insert(centre, "can");
        insert(centre, "cat");
        insert(centre, "it");
        remove(centre, "cat");

        List<Character> characters = List.of('c', 'a', 'n', 'i', 't');
        List<String> words = List.of("can", "it");

        assertEquals(characters, collectCharacters(centre));
        assertEquals(words, collectWords(centre, new ArrayList<>()));
    }

    @Test
    public void remove__missingValue() {
        Node centre = new Node(false);
        insert(centre, "can");
        insert(centre, "cat");
        insert(centre, "it");
        remove(centre, "car");

        List<Character> characters = List.of('c', 'a', 'n', 't', 'i', 't');
        List<String> words = List.of("can", "cat", "it");

        assertEquals(characters, collectCharacters(centre));
        assertEquals(words, collectWords(centre, new ArrayList<>()));
    }

    @Test
    public void remove__emptyString() {
        Node centre = new Node(false);
        insert(centre, "can");
        insert(centre, "cat");
        insert(centre, "it");
        insert(centre, "");
        remove(centre, "");

        List<Character> characters = List.of('c', 'a', 'n', 't', 'i', 't');
        List<String> words = List.of("can", "cat", "it");

        assertEquals(characters, collectCharacters(centre));
        assertEquals(words, collectWords(centre, new ArrayList<>()));
    }

    @Test
    public void remove__prefixOfAnother() {
        Node centre = new Node(false);
        insert(centre, "the");
        insert(centre, "these");
        insert(centre, "you");
        remove(centre, "the");

        List<Character> characters = List.of('t', 'h', 'e', 's', 'e', 'y', 'o', 'u');
        List<String> words = List.of("these", "you");

        assertEquals(characters, collectCharacters(centre));
        assertEquals(words, collectWords(centre, new ArrayList<>()));
    }

    @Test
    public void remove__havingAnotherAsPrefix() {
        Node centre = new Node(false);
        insert(centre, "the");
        insert(centre, "these");
        insert(centre, "you");
        remove(centre, "these");

        List<Character> characters = List.of('t', 'h', 'e', 'y', 'o', 'u');
        List<String> words = List.of("the", "you");

        assertEquals(characters, collectCharacters(centre));
        assertEquals(words, collectWords(centre, new ArrayList<>()));
    }

    @Test
    public void remove__singleItem() {
        Node node = new Node(false);
        insert(node, "can");
        remove(node, "can");

        List<Character> characters = List.of();
        List<String> words = List.of();

        assertEquals(characters, collectCharacters(node));
        assertEquals(words, collectWords(node, new ArrayList<>()));
    }

    @Test
    public void remove__noItems() {
        Node node = new Node(false);
        remove(node, "can");

        List<Character> characters = List.of();
        List<String> words = List.of();

        assertEquals(characters, collectCharacters(node));
        assertEquals(words, collectWords(node, new ArrayList<>()));
    }
}
