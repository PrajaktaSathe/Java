/* A Palindrome number is a number that remains unchanged when reversed.
EXAMPLE:
121 is a palindrome because its reverse ie '121' is same as the original number.
123 is NOT a palindrome because its reverse i.e. '321' is not same as the original number '123'.
*/

import java.util.*;

public class Palindrome {

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int copy=n;
        int r=0,d=0;
        while(n>0)
        {
            d=n%10;
            r=10*r+d;
            n=n/10;
        }
        if(r==copy)
        {
            System.out.println(copy+" is a palindrome number");
        }
        else{
            System.out.println(copy+" is not a palindrome number");
        }
    }
    
}
