package Programs.Recursion;

public class reverseArray{

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void reverse(int[] arr, int l, int r){
        if(l>=r){
            return;
        }
        swap(arr, l, r);
        reverse(arr, l+1, r-1);
    }

    public static void print(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
    }
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int n = 10;

        int l = 0;
        int r = n-1;

        reverse(arr, l, r);

        print(arr);
    }

}

This article is Contributed by Himanshu Jha.
Connect with me on linkedin https://www.linkedin.com/in/himanshujhaa/
