package sorting;
import java.util.*;

class SortWave {

    // A utility method to swap two numbers.
    void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // This function sorts arr[0..n-1] in wave form, i.e.,
    // arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4]....
    void sortInWave(int[] arr, int n) {
        // Traverse all even elements
        for (int i = 0; i < n; i += 2) {
            // If current even element is smaller
            // than previous
            if (i > 0 && arr[i - 1] > arr[i])
                swap(arr, i - 1, i);

            // If current even element is smaller
            // than next
            if (i < n - 1 && arr[i] < arr[i + 1])
                swap(arr, i, i + 1);
        }
    }
}
public class WiggleSorting {
    public static void main(String[] args) {
        // What is wiggle sort?
        // 1.To sort an array in waveform.
        // 2.The array’s elements shall be ordered such that,
        // arr[0]<arr[1]>arr[2]<arr[3] and so on in wiggle sort.(Even index have smaller value and odd
        // index having larger values)
        //Time complexity : average O(n) time .



        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements of required array :");
        int n= sc.nextInt();
        int[] arr= new int[n];
        System.out.println("Enter the array(enter only positive integer):");
        for(int i =0; i<n; i++){
            arr[i]=sc.nextInt();
        }
        SortWave ob = new SortWave();
        ob.sortInWave(arr, n);
        for (int i : arr) {
                System.out.print(i+" ");
            }
    }
    }

