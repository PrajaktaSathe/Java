//Program to check if the given number is a lucky number or not

import java.util.*;
public class LuckyNumber
{
    int c = 2;

    //Method to check if the number is a Lucky Number
    public boolean checkLucky(int n)
    {
        if(c > n)
            return true;
        if(n % c == 0)
            return false;
        
        //Position of the element
            n = n - (n/c);
        
        //Incrementing the counter variable
        c++;
        return checkLucky(n);
    }

    //Main method
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number to be checked : ");
        int a = sc.nextInt();
        LuckyNumber ob = new LuckyNumber();
        boolean res = ob.checkLucky(a);
        if(res == true)
        {
            System.out.println(a+" is a Lucky Number");
        }
        else
        {
            System.out.println(a+" is NOT a Lucky Number");
        }
    }
}