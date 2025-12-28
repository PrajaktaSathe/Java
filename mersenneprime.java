//To find mersenne's prime upto 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mersenneprime {

    public static boolean isPrime(long n) {
        boolean isPrime = true;

        for (int j = 2; j < n; j++) {
            if (n % j == 0 || n == 1 || n == 0) {
                isPrime = false;
                break;
            } else {
                isPrime = true;
            }

        }
        return isPrime;
    }

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Enter the range:");
        long p = scr.nextLong();
        List<Long> twoindexlist = new ArrayList<Long>();
        long two = 2;

        for (int k = 1; k < 100; k++) {
            two *= 2;
            twoindexlist.add(two);
            if (two >= p) {
                break;
            }
        }
        for (Long long1 : twoindexlist) {
            System.out.println(long1);
        }
      /* 
      for(long i=0; i<p;i++){
            if(isPrime(i)==true && twoindexlist.contains(i+1)==true){
                System.out.println(i);
            }
        }*/
    }
}
