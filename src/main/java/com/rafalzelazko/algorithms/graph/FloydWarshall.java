package com.rafalzelazko.algorithms.graph;

public class FloydWarshall {
    /**
     * Collects shortest paths between all pairs of nodes of a graph using Floyd-Warshall algorithm
     * <ul>
     * <li> The cost matrix must not be null
     * <li> The cost matrix diagonal must be filled with zeros
     * </ul>
     *
     * @param costs the cost matrix representing the graph
     * @return the collected shortest paths, null if the graph contains a negative cycle
     */
    public static Solution floydWarshall(int[][] costs) {
        int[][] distances = new int[costs.length][costs.length];
        int[][] previous = new int[costs.length][costs.length];

        for (int source = 0; source < costs.length; source++) {
            for (int destination = 0; destination < costs.length; destination++) {
                distances[source][destination] = costs[source][destination];
                previous[source][destination] = Integer.MAX_VALUE;

                if (costs[source][destination] != Integer.MAX_VALUE) {
                    previous[source][destination] = source;
                }
            }
        }

        for (int k = 0; k < costs.length; k++) {
            for (int i = 0; i < costs.length; i++) {
                for (int j = 0; j < costs.length; j++) {
                    if (distances[i][k] == Integer.MAX_VALUE) continue;
                    if (distances[k][j] == Integer.MAX_VALUE) continue;

                    if (distances[i][j] > distances[i][k] + distances[k][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        previous[i][j] = previous[k][j];
                    }
                }
            }
        }

        for (int index = 0; index < costs.length; index++)
            if (distances[index][index] < 0)
                return null;

        return new Solution(distances, previous);
    }

    public record Solution(int[][] distances, int[][] previous) {
    }
}
