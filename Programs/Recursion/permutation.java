import java.util.*;
public class permutation {

    public static void fun(List<List<Integer>> res, List<Integer> ds, boolean[] vis, int[] arr){
        if(ds.size() == arr.length){
            res.add(new ArrayList<>(ds));
            return;
        }

        for(int i=0;i<arr.length;i++){
            if(!vis[i]){
                vis[i] = true;
                ds.add(arr[i]);
                fun(res, ds, vis, arr);
                ds.remove(ds.size()-1);
                vis[i] = false;
            }
        }
    }

    public static void main(String[] args){
       int[] arr = {1,2,3};
        
        List<List<Integer>> res = new ArrayList<>();    

        boolean[] vis = new boolean[arr.length];

        fun(res, new ArrayList<>(), vis, arr);
        System.out.println(res);

    }
}


// This article is Contributed by Himanshu Jha.
// Connect with me on linkedin https://www.linkedin.com/in/himanshujhaa/