//program that demonstrate insertion sort algorithm
import java.util.Arrays;

public class Main {
      //Driver Function
    public static void main(String[] args) {
        int[] arr = {7, 9, 2, 4, 10};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
   //Insertion Sort Algorithm
    static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    swap(arr, j, j-1);
                } 
              else {
                    break;
                }
            }
        }
    }
  //Swapping Algorithm
  static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
