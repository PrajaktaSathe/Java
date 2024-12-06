// IDS algorithm in Java
package Programs;

import java.util.LinkedList;


class IDS {
    private final int v; // Total number of vertices
    private final LinkedList<Integer>[] adj; // Adjacency list
    private final boolean[] visited; // To keep track of visited nodes

    IDS(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        this.visited = new boolean[v];

        // Initialize adjacency list and visited array
        for (int i = 0; i < this.v; i++) {
            adj[i] = new LinkedList<>();
            this.visited[i] = false;
        }
    }

    // Add edge from u -> v
    public void addEdge(int u, int v) {
        this.adj[u].add(v);
    }

    // Helper function to perform Depth Limited Search (for IDS)
    private void DFS(int source, int depthLimit) {
        if (depthLimit == 0) {
            return; // Reached maximum depth, stop further exploration
        }

        // If the node is already visited, backtrack
        if (this.visited[source]) {
            return;
        }

        // Mark the node as visited
        System.out.print(source + " ");
        this.visited[source] = true;

        // Recur for all the neighbours with the reduced depth limit
        LinkedList<Integer> neighbours = this.adj[source];
        for (int next : neighbours) {
            DFS(next, depthLimit - 1);
        }
    }

    // Iterative Deepening Search (IDS) method
    public void ids(int source) {
        for (int depth = 0; depth < v; depth++) {
            System.out.println("\nDepth limit " + depth + ":");
            // Reset the visited array for each depth iteration
            for (int i = 0; i < v; i++) {
                visited[i] = false;
            }
            DFS(source, depth); // Perform DFS for the current depth limit
        }
    }
    public static void main(String[] args) {
        IDS g = new IDS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        // Start Iterative Deepening Search from node 2
        g.ids(2);
    }
}


