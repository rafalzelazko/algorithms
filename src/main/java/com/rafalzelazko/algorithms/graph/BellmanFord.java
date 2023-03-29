package com.rafalzelazko.algorithms.graph;

import java.util.Arrays;
import java.util.List;

public class BellmanFord {
    /**
     * Collects shortest paths from the specified node to all nodes in a graph using Bellman-Ford algorithm
     * <ul>
     * <li> The adjacency list must not be null
     * <li> The adjacency list must represent a graph without loops and parallel edges
     * <li> All adjacency list destinations must belong to the graph
     * <li> The cost matrix must not be null
     * <li> The cost matrix diagonal must be filled with zeros
     * <li> The start must belong to the graph
     * <li> The adjacency list and the cost matrix must match one another
     * </ul>
     *
     * @param adjacency the adjacency list representing the graph
     * @param costs     the cost matrix representing the graph
     * @param start     the node to collect the distances from
     * @return the collected shortest paths, null if the graph contains a negative cycle
     */
    public static Solution bellmanFord(List<List<Integer>> adjacency, int[][] costs, int start) {
        int[] distances = new int[adjacency.size()];
        int[] previous = new int[adjacency.size()];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, Integer.MAX_VALUE);
        distances[start] = 0;
        previous[start] = start;

        for (int count = 0; count < adjacency.size() - 1; count++) {
            for (int source = 0; source < adjacency.size(); source++) {
                if (distances[source] == Integer.MAX_VALUE) continue;
                for (int destination : adjacency.get(source)) {
                    if (distances[destination] > distances[source] + costs[source][destination]) {
                        distances[destination] = distances[source] + costs[source][destination];
                        previous[destination] = source;
                    }
                }
            }
        }

        for (int source = 0; source < adjacency.size(); source++)
            for (int destination : adjacency.get(source))
                if (distances[destination] > distances[source] + costs[source][destination])
                    return null;

        return new Solution(distances, previous);
    }

    public record Solution(int[] distances, int[] previous) {
    }
}
