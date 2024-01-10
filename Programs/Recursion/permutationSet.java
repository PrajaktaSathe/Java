import java.util.*;

public class permutationSet {

    public static void fun(int[] arr, boolean[] vis, List<Integer> ds, List<List<Integer>> res){
        if(ds.size() == arr.length){
            res.add(new ArrayList<>(ds));
            return;
        }

        for(int i=0;i<arr.length;i++){
            if(!vis[i]){
                vis[i]=true;
                ds.add(arr[i]);
                fun(arr, vis, ds, res);
                ds.remove(ds.size()-1);
                vis[i]=false;
            }
        }
    }
    public static void main(String[] args){
        int n = 3;
        int k = 3;  // return kth permutation of 1st 3 non negative numbers

        // contruct a array of size n such each element is i+1
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = i+1;
        }

        boolean[] vis = new boolean[n];
        List<List<Integer>> res = new ArrayList<>();

        fun(arr, vis, new ArrayList<>(), res);

        System.out.println(res.get(k-1));
    }
}

// This article is Contributed by Himanshu Jha.
// Connect with me on linkedin https://www.linkedin.com/in/himanshujhaa/