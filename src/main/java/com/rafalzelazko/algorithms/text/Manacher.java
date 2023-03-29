package com.rafalzelazko.algorithms.text;

public class Manacher {
    /**
     * Finds the longest palindrome substring in the specified string using Manacher algorithm
     * <ul>
     * <li> The string must not be null
     * </ul>
     *
     * @param string the string to search in
     * @return the longest palindrome substring
     */
    public static String manacher(String string) {
        char[] chars = initializeCharacters(string);
        int[] palindromes = new int[chars.length];
        int centre = 0, right = 0, start = 0, longest = 0;

        for (int index = 1; index < chars.length - 1; index++) {
            if (index < right)
                palindromes[index] = Math.min(palindromes[2 * centre - index], right - index);

            while (chars[index - palindromes[index] - 1] == chars[index + palindromes[index] + 1])
                palindromes[index]++;

            if (index + palindromes[index] > right) {
                centre = index;
                right = index + palindromes[index];
            }

            if (palindromes[index] > longest) {
                start = (index - palindromes[index]) / 2;
                longest = palindromes[index];
            }
        }

        return string.substring(start, start + longest);
    }

    private static char[] initializeCharacters(String string) {
        int stringLength = string.length();
        int charsLength = 2 * stringLength + 3;
        char[] chars = new char[charsLength];

        chars[0] = '@';
        chars[charsLength - 2] = '#';
        chars[charsLength - 1] = '$';

        for (int index = 0; index < stringLength; index++) {
            chars[2 * index + 1] = '#';
            chars[2 * index + 2] = string.charAt(index);
        }

        return chars;
    }
}
