import java.util.*;

public class Dijkstra{
    public static void main(String ...args){
        int v = 9;
        Graph g = new Graph(v);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);
        // g.printGraph();

        dijkstra(g,0);
    }

    private static void dijkstra(Graph g, int src){
        int V = g.v;
        int dist[] = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        boolean visited[] = new boolean[V];

        
        for(int i = 0 ; i < V; i ++){
            int u = minDist(dist,visited);
            visited[u] = true;

            List<AdjNode> list = g.adjList.get(u);
            for(AdjNode v  : list){
                if(!visited[v.dest] && dist[u] + v.cost < dist[v.dest]){
                    dist[v.dest] = dist[u] + v.cost;
                }
            }
        }

        System.out.println("Vertex  Distance from Source");
        for(int i = 0; i < dist.length; i++)
            System.out.println(i + "         " + dist[i]);
    }

    private static int minDist(int dist[], boolean[] visited){
        int min = Integer.MAX_VALUE, min_index = -1;

        for(int i = 0 ; i < dist.length; i++){
            if(!visited[i] && dist[i] <= min){
                min = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }
}


class Graph{
    int v;
    List<ArrayList<AdjNode>> adjList;
    Graph(int v){
        this.v = v;
        adjList = new ArrayList<>();
        for(int i = 0; i < this.v; i++){
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int cost){
        this.adjList.get(u).add(new AdjNode(u, v, cost));
        this.adjList.get(v).add(new AdjNode(v, u, cost));
    }
    public void printGraph(){
        for(int i = 0 ; i < this.v ; i++){
            System. out.print(i + " -> ");
            for(int j = 0 ; j < adjList.get(i).size(); j++){
                System.out.print(adjList.get(i).get(j).dest + " ");
            }
            System. out.println();
        }
    }
}

class AdjNode{
    int source, dest, cost;
    AdjNode(int source, int dest, int cost){
        this.source = source;
        this.dest = dest;
        this.cost = cost;
    }
}