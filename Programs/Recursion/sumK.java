// find subsequences whose sum is k, there is no infinte supplies

//same as combination sum 1 but with some modification
import java.util.*;
public class sumK {

    // 1st method :-

    public static void fun(int ind, int[] arr, int s, int sum, List<Integer> ds){
        if(ind == arr.length){
            if(s == sum){
                System.out.println(ds);
            }
            return;
        }

        // not pick
        fun(ind+1, arr, s, sum, ds);

        // pick
        ds.add(arr[ind]);
        s+=arr[ind];
        fun(ind+1, arr, s, sum, ds);
        s-=arr[ind];
        ds.remove(ds.size()-1);
    }


    // 2nd method :-

    public static void f(int ind, int[] arr, int sum, List<Integer> ds){
        if(ind == arr.length){
            if(sum == 0){
                System.out.println(ds);
            }
            return;
        }

        // not pick
        f(ind+1, arr, sum, ds);

        // pick
        ds.add(arr[ind]);
        f(ind+1, arr, sum-arr[ind], ds);
        ds.remove(ds.size()-1);
    }

    public static void main(String[] args){
        int[] arr = {1,2,1};
        int sum = 2;

        int n = arr.length;

        System.out.println("1st Method ");
        fun(0, arr, 0, sum, new ArrayList<>());

        System.out.println("-------------------");

        System.out.println("2nd Method ");
        f(0, arr, sum, new ArrayList<>());
    }
}


// This article is Contributed by Himanshu Jha.
// Connect with me on linkedin https://www.linkedin.com/in/himanshujhaa/