import java.util.*;

public class Tail {

    public static void main (String[] args){

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter numbers to find GCD:");
       
        int n1 = scan.nextInt();
        int n2 = scan.nextInt();

        int ans = gcd(n1, n2);

        System.out.printf("G.C.D of %d and %d: %d.", n1, n2, ans);
    }
    public static int gcd(int n1, int n2)
    {
        if (n2 != 0)
            return gcd(n2, n1 % n2);
        else
            return n1;
    }
}
