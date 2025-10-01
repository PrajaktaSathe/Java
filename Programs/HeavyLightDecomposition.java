// HeavyLightDecomposition.java
//Heavy-Light Decomposition decomposes a tree into disjoint paths (chains) so that any path query between two nodes can be answered efficiently by traversing at most O(log n) chains.
import java.util.*;

public class HeavyLightDecomposition {
    private int n;
    private List<List<Integer>> adj;
    private int[] parent, depth, heavy, head, pos;
    private int currentPos;
    
    public HeavyLightDecomposition(int n) {
        this.n = n;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        Arrays.fill(heavy, -1);
        currentPos = 0;
    }
    
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    
    private int dfs(int v) {
        int size = 1;
        int maxChildSize = 0;
        
        for (int u : adj.get(v)) {
            if (u != parent[v]) {
                parent[u] = v;
                depth[u] = depth[v] + 1;
                int childSize = dfs(u);
                
                if (childSize > maxChildSize) {
                    maxChildSize = childSize;
                    heavy[v] = u;
                }
                size += childSize;
            }
        }
        return size;
    }
    
    private void decompose(int v, int h) {
        head[v] = h;
        pos[v] = currentPos++;
        
        if (heavy[v] != -1) {
            decompose(heavy[v], h);
        }
        
        for (int u : adj.get(v)) {
            if (u != parent[v] && u != heavy[v]) {
                decompose(u, u);
            }
        }
    }
    
    public void build(int root) {
        parent[root] = -1;
        depth[root] = 0;
        dfs(root);
        decompose(root, root);
    }
    
    // Query path from u to v
    public List<int[]> queryPath(int u, int v) {
        List<int[]> result = new ArrayList<>();
        
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                result.add(new int[]{pos[head[u]], pos[u]});
                u = parent[head[u]];
            } else {
                result.add(new int[]{pos[head[v]], pos[v]});
                v = parent[head[v]];
            }
        }
        
        result.add(new int[]{Math.min(pos[u], pos[v]), Math.max(pos[u], pos[v])});
        return result;
    }
    
    public static void main(String[] args) {
        HeavyLightDecomposition hld = new HeavyLightDecomposition(7);
        
        // Build a sample tree
        hld.addEdge(0, 1);
        hld.addEdge(0, 2);
        hld.addEdge(1, 3);
        hld.addEdge(1, 4);
        hld.addEdge(2, 5);
        hld.addEdge(2, 6);
        
        hld.build(0);
        
        System.out.println("Heavy-Light Decomposition completed!");
        System.out.println("Query path segments from node 3 to node 5:");
        List<int[]> path = hld.queryPath(3, 5);
        for (int[] segment : path) {
            System.out.println("Segment: [" + segment[0] + ", " + segment[1] + "]");
        }
    }
}
