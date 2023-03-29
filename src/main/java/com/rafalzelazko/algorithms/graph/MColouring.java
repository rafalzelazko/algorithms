package com.rafalzelazko.algorithms.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MColouring {
    /**
     * Checks a solution to the specified M Colouring problem
     * <ul>
     * <li> The adjacency list must not be null
     * <li> The adjacency list must represent a graph without loops and parallel edges
     * <li> The adjacency list must represent an undirected graph
     * <li> All adjacency list destinations must belong to the graph
     * <li> The colour array must not be null
     * <li> The colour array length must match the adjacency list size
     * <li> The colour limit must be non-negative
     * </ul>
     *
     * @param adjacency   the adjacency list representing the graph
     * @param colours     the colours of all nodes in the graph
     * @param colourLimit the number of colours available to use
     * @return true if the colouring is correct, false otherwise
     */
    public static boolean checkSolution(List<List<Integer>> adjacency, int[] colours, int colourLimit) {
        boolean[] inserted = new boolean[adjacency.size()];
        Queue<Integer> queue = new LinkedList<>();

        for (int index = 0; index < adjacency.size(); index++) {
            if (inserted[index]) continue;
            if (colours[index] >= colourLimit) return false;
            queue.offer(index);
            inserted[index] = true;

            while (!queue.isEmpty()) {
                int source = queue.poll();

                for (int destination : adjacency.get(source)) {
                    if (colours[source] == colours[destination]) return false;
                    if (colours[destination] >= colourLimit) return false;

                    if (!inserted[destination]) {
                        queue.offer(destination);
                        inserted[destination] = true;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Assigns M colours to all nodes of a graph such that no two adjacent
     * nodes have the same colour
     * <ul>
     * <li> The adjacency list must not be null
     * <li> The adjacency list must represent a graph without loops and parallel edges
     * <li> The adjacency list must represent an undirected graph
     * <li> All adjacency list destinations must belong to the graph
     * <li> The colour limit must be non-negative
     * </ul>
     *
     * @param adjacency   the adjacency list representing the graph
     * @param colourLimit the number of colours available to use
     * @return a colouring, null if there are no solutions
     */
    public static int[] mColouring(List<List<Integer>> adjacency, int colourLimit) {
        boolean[] inserted = new boolean[adjacency.size()];
        int[] colours = new int[adjacency.size()];
        Queue<Integer> queue = new LinkedList<>();
        int coloursInUse = 1;

        if (colourLimit == 0 && !adjacency.isEmpty())
            return null;

        for (int index = 0; index < adjacency.size(); index++) {
            if (inserted[index]) continue;
            queue.offer(index);
            inserted[index] = true;

            while (!queue.isEmpty()) {
                int source = queue.poll();

                for (int destination : adjacency.get(source)) {
                    if (colours[source] == colours[destination]) {
                        colours[destination]++;
                        coloursInUse = Math.max(coloursInUse, colours[destination] + 1);
                        if (coloursInUse > colourLimit) return null;
                    }

                    if (!inserted[destination]) {
                        queue.offer(destination);
                        inserted[destination] = true;
                    }
                }
            }
        }

        return colours;
    }
}
