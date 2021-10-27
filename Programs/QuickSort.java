//Quick Sort
import java.util.*;
import java.lang.*;
import java.io.*;



public class QuickSort{
    public static void main(String args[]){
        int arr[]= {-1,-2,42,1,24,44,32,0,12,100};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    
    
    public static void quickSort(int arr[],int i, int j){
        int pivot = i;
        if(i<j){
            sort(arr,i,j,pivot);
            quickSort(arr,i,pivot);
            quickSort(arr,pivot+1,j);
        }
    }
    
    public static int[] sort(int arr[],int i,int j,int pivot){
        if(j<=pivot || i>=arr.length){
            return arr;
        }
        while(arr[i] < arr[pivot]){
            i++;
        }
        while(arr[j]> arr[pivot]){
            j--;
        }
        if(i<j){
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            return sort(arr,i,j,pivot);
        }
        else{
            int tmp = arr[pivot];
            arr[pivot] = arr[j];
            arr[j] = tmp;
            return arr;
        }
    }
}
