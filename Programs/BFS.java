// bfs - for unweighted connected graph

package project0;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Graph {
	private int v; // total number of vertices
	private LinkedList<Integer> adj[]; // adjacency list
	private boolean visited[];

	Graph(int v) {
		this.v = v;
		this.adj = new LinkedList[v];
		this.visited = new boolean[v];
		for (int i = 0; i < this.v; i++) {
			adj[i] = new LinkedList<Integer>();
		}

	}

	public void addEdge(int u, int v) { // edge from u -> v
		this.adj[u].add(v);
	}

	public void BFS(int source) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(source);
		visited[source] = true;
		
		while (!queue.isEmpty()) {
			
			int currNode = queue.poll();
			System.out.print(currNode + " ");
			
			for (int next : this.adj[currNode]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
	}

	public void BFS_end() {

	}

}

public class Main {
	public static void main(String args[]) {

		Graph g = new Graph(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		g.BFS(0);

	}

}
