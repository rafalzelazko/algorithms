package com.rafalzelazko.algorithms.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DistanceCollector {
    /**
     * Collects distances from the specified node to all nodes in a graph using BFS algorithm
     * <ul>
     * <li> The adjacency list must not be null
     * <li> The adjacency list must represent a graph without loops and parallel edges
     * <li> All adjacency list destinations must belong to the graph
     * <li> The start must belong to the graph
     * </ul>
     *
     * @param adjacency the adjacency list representing the graph
     * @param start     the node to collect the distances from
     * @return the collected distances
     */
    public static int[] collectDistances(List<List<Integer>> adjacency, int start) {
        int[] distances = new int[adjacency.size()];
        Queue<Integer> queue = new LinkedList<>();

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int source = queue.poll();
            adjacency.get(source).stream()
                .filter(destination -> distances[destination] == Integer.MAX_VALUE)
                .forEach(destination -> {
                    distances[destination] = distances[source] + 1;
                    queue.offer(destination);
                });
        }

        return distances;
    }
}
