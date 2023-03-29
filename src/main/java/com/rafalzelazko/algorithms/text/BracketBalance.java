package com.rafalzelazko.algorithms.text;

import java.util.List;
import java.util.Stack;

public class BracketBalance {
    /**
     * Checks whether an expression is balanced in terms of brackets
     * <ul>
     * <li> The expression must not be null
     * </ul>
     *
     * @param expression the expression
     * @return true if the expression is balanced, false otherwise
     */
    public static boolean isBalanced(String expression) {
        List<Character> opening = List.of('(', '[', '{');
        List<Character> closing = List.of(')', ']', '}');

        Stack<Character> stack = new Stack<>();
        int length = expression.length();

        for (int index = 0; index < length; index++) {
            char character = expression.charAt(index);

            if (opening.contains(character)) {
                stack.push(character);
            } else if (closing.contains(character)) {
                if (stack.isEmpty())
                    return false;
                if (opening.indexOf(stack.peek()) != closing.indexOf(character))
                    return false;
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
