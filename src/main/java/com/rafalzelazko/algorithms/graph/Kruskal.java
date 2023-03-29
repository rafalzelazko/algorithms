package com.rafalzelazko.algorithms.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Kruskal {
    /**
     * Finds the minimum spanning tree of a graph using Kruskal algorithm
     * <ul>
     * <li> The number of nodes must be non-negative
     * <li> The edge list must not be null
     * <li> All edge endpoints must belong to the graph
     * </ul>
     *
     * @param nodes the number of nodes in the graph
     * @param edges the edge list of the graph
     * @return the minimum spanning tree of the graph, null if the graph is disconnected
     */
    public static List<Edge> kruskal(int nodes, List<Edge> edges) {
        List<Edge> spanning = new ArrayList<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(edges);

        int[] parents = new int[nodes];
        int[] ranks = new int[nodes];

        for (int index = 0; index < nodes; index++) {
            parents[index] = index;
            ranks[index] = 0;
        }

        while (spanning.size() < nodes - 1) {
            if (priorityQueue.isEmpty()) return null;
            Edge edge = priorityQueue.poll();

            int firstRoot = find(edge.first, parents);
            int secondRoot = find(edge.second, parents);

            if (firstRoot != secondRoot) {
                spanning.add(edge);
                union(firstRoot, secondRoot, parents, ranks);
            }
        }

        return spanning;
    }

    private static int find(int index, int[] parents) {
        if (parents[index] == index) return index;
        return find(parents[index], parents);
    }

    private static void union(int first, int second, int[] parents, int[] ranks) {
        if (ranks[first] < ranks[second]) {
            parents[first] = second;
        } else if (ranks[first] > ranks[second]) {
            parents[second] = first;
        } else {
            parents[second] = first;
            ranks[first]++;
        }
    }

    @AllArgsConstructor
    public static class Edge implements Comparable<Edge> {
        @Getter
        private int id;
        private int first;
        private int second;
        private int cost;

        public int compareTo(Edge that) {
            return this.cost - that.cost;
        }
    }
}
