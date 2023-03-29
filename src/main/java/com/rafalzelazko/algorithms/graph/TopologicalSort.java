package com.rafalzelazko.algorithms.graph;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    /**
     * Checks whether a topological order matches the specified graph
     * <ul>
     * <li> The adjacency list must not be null
     * <li> The adjacency list must represent a graph without loops and parallel edges
     * <li> All adjacency list destinations must belong to the graph
     * <li> The adjacency list must represent a graph without a cycle
     * <li> The topological order must not be null
     * <li> The topological order length must match the adjacency list size
     * </ul>
     *
     * @param adjacency the adjacency list representing the graph
     * @param order     the topological order of the graph
     * @return true if the topological order is correct, false otherwise
     */
    public static boolean checkSolution(List<List<Integer>> adjacency, int[] order) {
        boolean[] finished = new boolean[adjacency.size()];

        for (int index = order.length - 1; index >= 0; index--) {
            for (int destination : adjacency.get(order[index]))
                if (!finished[destination])
                    return false;

            finished[order[index]] = true;
        }
        return true;
    }

    /**
     * Finds a topological order of a graph using DFS algorithm
     * <ul>
     * <li> The adjacency list must not be null
     * <li> The adjacency list must represent a graph without loops and parallel edges
     * <li> All adjacency list destinations must belong to the graph
     * </ul>
     *
     * @param adjacency the adjacency list representing the graph
     * @return a topological order, null if the graph contains a cycle
     */
    public static int[] topologicalSort(List<List<Integer>> adjacency) {
        int[] colours = new int[adjacency.size()];
        Stack<Integer> stack = new Stack<>();
        Input input = new Input(adjacency, colours, stack);

        for (int source = 0; source < adjacency.size(); source++) {
            if (colours[source] != 0) continue;
            boolean canContinue = collectValues(input, source);
            if (!canContinue) return null;
        }

        int[] order = new int[adjacency.size()];
        for (int index = 0; index < adjacency.size(); index++)
            order[index] = stack.pop();

        return order;
    }

    private static boolean collectValues(Input input, int source) {
        if (input.colours[source] == 1)
            return false;

        input.colours[source] = 1;

        for (int destination : input.adjacency.get(source))
            if (input.colours[destination] != 2)
                if (!collectValues(input, destination))
                    return false;

        input.colours[source] = 2;
        input.stack.push(source);
        return true;
    }

    @AllArgsConstructor
    private static class Input {
        private List<List<Integer>> adjacency;
        private int[] colours;
        private Stack<Integer> stack;
    }
}
