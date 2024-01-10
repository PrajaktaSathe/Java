package Recursion;


// Binary search using recursion
public class binarySearch {

    public static boolean search(int[] arr, int tar, int l, int r){
        if(l <= r)
        {
            int mid = (l+r)/2;

            if(arr[mid] == tar){
                return true;
            } else if(arr[mid] > tar){
                return search(arr, tar, l, mid - 1);
            } else {
                return search(arr, tar, mid + 1, r);
            }
        }
        return false;
    }

    public static void main(String[] args){
        int arr[] = {10, 25, 40, 55, 70, 95};
        int tar = 25;

        int l = 0;
        int r = arr.length-1;

        System.out.println(search(arr, tar, l, r));
    }
}


// This article is Contributed by Himanshu Jha.
// Connect with me on linkedin https://www.linkedin.com/in/himanshujhaa/