package com.rafalzelazko.algorithms.combinatorial;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

public class Hanoi {
    /**
     * Computes the list of actions to perfom to solve the specified Tower of Hanoi problem
     *
     * @param height      the number of discs
     * @param source      the integer label of the source stack
     * @param destination the integer label of the destination stack
     * @param helper      the integer label of the helper stack
     * @return the list of actions
     */
    public static List<Action> hanoi(int height, int source, int destination, int helper) {
        if (height == 0) return new ArrayList<>();
        List<Action> actions = hanoi(height - 1, source, helper, destination);
        actions.add(new Action(height, source, destination));
        actions.addAll(hanoi(height - 1, helper, destination, source));
        return actions;
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Action {
        private int diameter;
        private int source;
        private int destination;
    }
}
