package com.rafalzelazko.algorithms.text;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt {
    /**
     * Collects all occurrences of the specified pattern in the specified text using KMP algorithm
     * <ul>
     * <li> The strings must not be null
     * </ul>
     * This algorithm creates a partial array that at each index i represents the length of the
     * longest proper prefix of the pattern ending at i
     *
     * @param pattern the pattern to search for
     * @param text    the text to search in
     * @return the collected occurrences
     */
    public static List<Integer> findSolutions(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();

        if (patternLength == 0)
            return new ArrayList<>();

        int[] partial = new int[patternLength];
        int prefixIndex = 0;
        int currentIndex = 1;

        while (currentIndex < patternLength) {
            if (pattern.charAt(prefixIndex) == pattern.charAt(currentIndex)) {
                partial[currentIndex] = prefixIndex + 1;
                prefixIndex++;
                currentIndex++;
            } else {
                if (prefixIndex == 0) currentIndex++;
                else prefixIndex = partial[prefixIndex - 1];
            }
        }

        List<Integer> collected = new ArrayList<>();
        int patternIndex = 0;
        int textIndex = 0;

        while (textIndex < textLength) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                if (patternIndex == patternLength - 1) {
                    collected.add(textIndex - patternIndex);
                    if (patternLength == 1 || textIndex == textLength - 1) textIndex++;
                    else patternIndex = partial[patternIndex - 1];
                } else {
                    patternIndex++;
                    textIndex++;
                }
            } else {
                if (patternIndex == 0) textIndex++;
                else patternIndex = partial[patternIndex - 1];
            }
        }

        return collected;
    }
}
