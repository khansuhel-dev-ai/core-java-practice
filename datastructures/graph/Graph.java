package datastructures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * ============================================================================
 * Class: Graph
 * Package: datastructures.graph
 * Description: Generic Adjacency List Graph representation supporting:
 *   1. Directed & Undirected Edges
 *   2. Breadth-First Search (BFS) Traversal with Step-by-Step Queue Tracing
 *   3. Depth-First Search (DFS) Traversal (Recursive & Iterative with Stack)
 *   4. Path Finding & Connectivity Verification (hasPath)
 *   5. Cycle Detection in Undirected Graph
 *   6. Visual Adjacency List Display
 * ============================================================================
 */
public class Graph {
    private final Map<Integer, List<Integer>> adjList;
    private final boolean isDirected;

    public Graph(boolean isDirected) {
        this.adjList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);

        adjList.get(source).add(destination);
        if (!isDirected) {
            adjList.get(destination).add(source);
        }
    }

    /**
     * Breadth-First Search (BFS) using a Queue.
     * Explores neighbors level-by-level (ideal for shortest path in unweighted graphs).
     */
    public void bfs(int startVertex) {
        if (!adjList.containsKey(startVertex)) {
            System.out.println("  Vertex " + startVertex + " does not exist in the graph.");
            return;
        }

        System.out.println("\n  --- Breadth-First Search (BFS) starting at " + startVertex + " ---");
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.offer(startVertex);

        System.out.print("  BFS Order: [ ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjList.getOrDefault(current, List.of())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println("]");
    }

    /**
     * Depth-First Search (DFS) using Recursion.
     * Explores as deep as possible along each branch before backtracking.
     */
    public void dfs(int startVertex) {
        if (!adjList.containsKey(startVertex)) {
            System.out.println("  Vertex " + startVertex + " does not exist in the graph.");
            return;
        }

        System.out.println("\n  --- Depth-First Search (DFS) starting at " + startVertex + " ---");
        Set<Integer> visited = new HashSet<>();
        System.out.print("  DFS Order: [ ");
        dfsHelper(startVertex, visited);
        System.out.println("]");
    }

    private void dfsHelper(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (int neighbor : adjList.getOrDefault(current, List.of())) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    /**
     * Checks if a path exists between source and destination using BFS.
     */
    public boolean hasPath(int source, int destination) {
        if (!adjList.containsKey(source) || !adjList.containsKey(destination)) {
            return false;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(source);
        queue.offer(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == destination) {
                return true;
            }
            for (int neighbor : adjList.getOrDefault(current, List.of())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }

    /**
     * Detects cycles in an undirected graph using DFS.
     */
    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        for (int vertex : adjList.keySet()) {
            if (!visited.contains(vertex)) {
                if (hasCycleHelper(vertex, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleHelper(int current, Set<Integer> visited, int parent) {
        visited.add(current);
        for (int neighbor : adjList.getOrDefault(current, List.of())) {
            if (!visited.contains(neighbor)) {
                if (hasCycleHelper(neighbor, visited, current)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true; // Visited neighbor that is not the direct parent indicates a cycle!
            }
        }
        return false;
    }

    /**
     * Displays the complete Adjacency List.
     */
    public void display() {
        System.out.println("\n  Adjacency List (" + (isDirected ? "Directed" : "Undirected") + "):");
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            System.out.println("    Vertex [" + entry.getKey() + "] -> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("            GRAPH DATA STRUCTURE SUITE               ");
        System.out.println("=====================================================");

        // Building an undirected graph
        Graph graph = new Graph(false);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4); // Creates a cycle: 0 - 1 - 3 - 4 - 2 - 0
        graph.addEdge(4, 5);

        graph.display();

        graph.bfs(0);
        graph.dfs(0);

        System.out.println("\n--- Path Connectivity Queries ---");
        System.out.println("  Path exists between 0 and 5? " + graph.hasPath(0, 5));
        System.out.println("  Path exists between 3 and 9? " + graph.hasPath(3, 9));

        System.out.println("\n--- Cycle Detection ---");
        System.out.println("  Graph contains a cycle? " + graph.hasCycle());
    }
}

