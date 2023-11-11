//Note - for disconnected graph use the approach for  checking connected components.

package Programs;

import java.util.LinkedList;

class DFSGraph {
    private int v; //total number of vertices
    private LinkedList<Integer> adj[]; //adjacency list
    private boolean visited[]; //to keep track of the node which are visited.

    DFSGraph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        this.visited = new boolean[v];
        for (int i = 0; i < this.v; i++) {
            adj[i] = new LinkedList<Integer>();
            this.visited[i] = false;
        }

    }


    public void addEdge(int u, int v) { // edge from u -> v
        this.adj[u].add(v);
    }


    public void DFS(int source) {
        //if the node is already visited, then backtrack.
        if (this.visited[source] == true) {
            return;
        }
        //mark the source vertex as visited
        System.out.print(source + " ");
        this.visited[source] = true;

        //traverse through all the neighbours and backtrack if already visited.
        LinkedList<Integer> neighbours = this.adj[source];

        for (int next : neighbours) {
            DFS(next);
        }

    }

}

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from Main to DFSGraphExample
 * 2. Added package name
 * 3. Optimized imports
 */
public class DFSGraphExample {
    public static void main(String args[]) {

        DFSGraph g = new DFSGraph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.DFS(2);

    }

}
