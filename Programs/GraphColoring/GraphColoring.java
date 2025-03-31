// Program to solve Graph Coloring using Backtracking in Java

import java.util.Arrays;

class GraphColoring {
    private int V; // Number of vertices
    private int[][] graph; // Adjacency matrix
    private int[] colors; // Stores colors assigned to vertices

    // Constructor
    public GraphColoring(int[][] graph, int V) {
        this.V = V;
        this.graph = graph;
        this.colors = new int[V];
    }

    // Utility function to check if color assignment is safe
    private boolean isSafe(int v, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && colors[i] == c) { // Adjacent vertex has the same color
                return false;
            }
        }
        return true;
    }

    // Recursive function to solve the graph coloring problem
    private boolean solveGraphColoring(int v, int m) {
        if (v == V) { // All vertices are colored
            return true;
        }

        // Try different colors for vertex v
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, c)) {
                colors[v] = c; // Assign color

                if (solveGraphColoring(v + 1, m)) { // Recur for next vertex
                    return true;
                }

                colors[v] = 0; // Backtrack
            }
        }
        return false; // No valid color assignment
    }

    // Function to start the graph coloring
    public void graphColoring(int m) {
        if (solveGraphColoring(0, m)) {
            System.out.println("Solution Exists: Color Assignment");
            System.out.println(Arrays.toString(colors));
        } else {
            System.out.println("No Solution Exists with " + m + " colors");
        }
    }

    // Main function
    public static void main(String[] args) {
        int[][] graph = {
                { 0, 1, 1, 1 },
                { 1, 0, 1, 0 },
                { 1, 1, 0, 1 },
                { 1, 0, 1, 0 }
        };

        int V = 4; // Number of vertices
        int m = 3; // Number of colors

        GraphColoring gc = new GraphColoring(graph, V);
        gc.graphColoring(m);
    }
}
