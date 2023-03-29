package com.rafalzelazko.algorithms.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    /**
     * Collects shortest paths from the specified node to all nodes in a graph using Dijkstra algorithm
     * <ul>
     * <li> The adjacency list must not be null
     * <li> The adjacency list must represent a graph without loops and parallel edges
     * <li> All adjacency list destinations must belong to the graph
     * <li> The cost matrix must not be null
     * <li> The cost matrix diagonal must be filled with zeros
     * <li> The start must belong to the graph
     * <li> The adjacency list and the cost matrix must match one another
     * <li> All edges must have a non-negative cost
     * </ul>
     *
     * @param adjacency the adjacency list representing the graph
     * @param costs     the cost matrix representing the graph
     * @param start     the node to collect the distances from
     * @return the collected shortest paths
     */
    public static Solution dijkstra(List<List<Integer>> adjacency, int[][] costs, int start) {
        int[] distances = new int[adjacency.size()];
        int[] previous = new int[adjacency.size()];

        Comparator<Integer> comparator = Comparator.comparingInt((Integer integer) -> distances[integer]);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparator);

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, Integer.MAX_VALUE);
        distances[start] = 0;
        previous[start] = start;

        for (int index = 0; index < adjacency.size(); index++)
            priorityQueue.offer(index);

        while (!priorityQueue.isEmpty()) {
            int source = priorityQueue.poll();
            if (distances[source] == Integer.MAX_VALUE) continue;
            adjacency.get(source).stream()
                .filter(priorityQueue::contains)
                .forEach(destination -> {
                    if (distances[destination] > distances[source] + costs[source][destination]) {
                        distances[destination] = distances[source] + costs[source][destination];
                        previous[destination] = source;
                        priorityQueue.remove(destination);
                        priorityQueue.offer(destination);
                    }
                });
        }

        return new Solution(distances, previous);
    }

    public record Solution(int[] distances, int[] previous) {
    }
}
