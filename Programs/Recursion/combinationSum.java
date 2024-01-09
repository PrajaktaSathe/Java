package Programs.Recursion;

import java.util.*;

public class combinationSum {

    public static void fun(int ind, int[] arr, int tar, List<List<Integer>> res, List<Integer> ds) {
        if(ind >= arr.length){
            if(tar == 0){
                res.add(new ArrayList(ds));
            }
            return;
        }
        // not pick
        fun(ind+1, arr, tar, res, ds);

        // pick

        if(arr[ind]<= tar){
            ds.add(arr[ind]);
            fun(ind, arr, tar-arr[ind], res, ds);
            ds.remove(ds.size()-1);
        }
        
    }
    public static void main(String[] args) {
        int[] arr = {2,3,6,7};
        int tar = 7;

        List<List<Integer>> res = new ArrayList<>();
        fun(0, arr, tar, res, new ArrayList<Integer>());
        System.out.println(res);
    }
}
