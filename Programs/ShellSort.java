// Program that implements Shell Sort (Invented by Donald Shell)
import java.util.Arrays;
 
public class ShellSortMain {
 public static void main(String[] args) {
    int[] array = { 22, 53, 33, 12, 75, 65, 887, 125, 37, 977 };
    System.out.println("Before Sorting : ");
    System.out.println(Arrays.toString(array));
    System.out.println("===================");
    System.out.println("After Sorting : ");
    array = shellsort(array);
    System.out.println(Arrays.toString(array));
 }
 
 private static int[] shellsort(int[] array) {
 
 // first part uses the Knuth's interval sequence
 int h = 1;
 while (h <= array.length / 3) {
    h = 3 * h + 1; 
 }
 
 while (h > 0) {
 
 for (int i = 0; i < array.length; i++) {
 
    int temp = array[i];
    int j;
 
 for (j = i; j > h - 1 && array[j - h] >= temp; j = j - h) {
    array[j] = array[j - h];
 }
    array[j] = temp;
 }
    h = (h - 1) / 3;
 }
    return array;
 }
 
}