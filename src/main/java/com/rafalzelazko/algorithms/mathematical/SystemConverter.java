package com.rafalzelazko.algorithms.mathematical;

import java.util.Stack;

public class SystemConverter {
    /**
     * Converts the specified integer between the specified bases
     * <ul>
     * <li> The character representation must not be null
     * <li> The bases must be at least 2 and at most 16
     * </ul>
     *
     * @param characters the character representation of the integer to convert
     * @param source     the source base
     * @param target     the target base
     * @return the character representation of the converted integer
     */
    public static char[] changeSystem(char[] characters, int source, int target) {
        int integer = convertToInt(characters, source);
        return convertFromInt(integer, target);
    }

    private static int convertToInt(char[] characters, int base) {
        int integer = 0;
        for (char character : characters)
            integer = base * integer + intValue(character);
        return integer;
    }

    private static char[] convertFromInt(int integer, int base) {
        Stack<Integer> stack = new Stack<>();

        while (integer > 0) {
            stack.push(integer % base);
            integer = integer / base;
        }

        if (stack.isEmpty()) return new char[] {'0'};
        char[] characters = new char[stack.size()];

        for (int index = 0; index < characters.length; index++)
            characters[index] = charValue(stack.pop());

        return characters;
    }

    private static int intValue(char character) {
        if (character >= '0' && character <= '9')
            return character - '0';
        if (character >= 'a' && character <= 'f')
            return character - 'a' + 10;
        return 0;
    }

    private static char charValue(int integer) {
        if (integer >= 0 && integer <= 9)
            return (char) (integer + '0');
        if (integer >= 10 && integer <= 15)
            return (char) (integer - 10 + 'a');
        return '0';
    }
}
