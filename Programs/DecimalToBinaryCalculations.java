package Programs;
import java.util.Scanner;

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class name from BnaryCal to DecimalToBinaryCalculations
 * 2. Added package name
 * 3. Optimized imports
 */
public class DecimalToBinaryCalculations {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int number = scn.nextInt();

        calculateBinary(number, calculateLength(number));

    }

    static int calculateLength(int n){
        int count =0;
        while(n>0){
            n = n/2;
            count++;
        }
        return  count;
    }

    static void calculateBinary(int n,int positions){
//        int count = 0;
//        while(n>0){
//            n = n/2;
//            count ++;
//        }
        int length = positions;

        int[] Binary = new int[length];
        int i =0;
        while (n>0){
            Binary[i] = n%2;
            n = n/2;
            i++;

        }
        for(int j= Binary.length-1; j>=0; j--){
            System.out.print(Binary[j]);
        }
    }


}
