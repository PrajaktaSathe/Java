// Fin the two non-repeating elements in an array where elements occur two times.
package BitManipulation;
import java.util.*;
import java.lang.*;

public class TwoElementsOccurOnceWhileOthersOccurTwice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array:");
        int n = sc.nextInt();
        System.out.println("Enter the elements:");
        int[] a = new int[n];
        for(int i=0 ; i<n ; i++){
            a[i] = sc.nextInt();
        }

        int xor = 0;
        for(int i : a){
            xor = xor ^ i;  // finding the xor of all the elements of the array.
        }

        int setBit = xor & (-xor);  // finding the right most set bit of the xor variable.

        int x = 0;
        int y = 0;

        for(int i : a){
            if((setBit & i) == 0){  // checking right most set bits of elements of the array is set or not
                x = x ^ i;  // if not set then put in in x variable after doing XOR operation.
            }
            else{
                y = y ^ i;  // else put it in y variable after performing gXOR operation.
            }
        }

        System.out.println(x+" "+y);
    }
}
