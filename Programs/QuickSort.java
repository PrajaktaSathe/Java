// Program that implements the quick sort algorithm
// Best Case : O(n log n)
// Average Case : O(n log n)
// Worst Case : O(n^2)

import java.util.Arrays;
 
public class QuickSortMain {
 
    private static int array[];
 
    public static void sort(int[] arr) {
 
        if (arr == null || arr.length == 0) {
            return;
        }
        array = arr;
        quickSort(0, array.length-1);
    }
 
    private static void quickSort(int left, int right) {
 
        int i = left;
        int j = right;

        int pivot = array[left+(right-left)/2];
 
        while (i <= j) {
            while (array[i] < pivot) { i++; } while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            quickSort(left, j);
        if (i < right)
            quickSort(i, right);
    }
 
    private static void exchange(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
 
    public static void main(String a[]){
        int[] input = {33,21,45,64,55,34,11,8,3,5,1};
        System.out.println("Before Sorting : ");
        System.out.println(Arrays.toString(input));
        sort(input);
        System.out.println("==================");
        System.out.println("After Sorting : ");
        System.out.println(Arrays.toString(array));
 
    }
}