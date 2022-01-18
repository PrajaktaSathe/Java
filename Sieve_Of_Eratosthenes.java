// Program to find all the primes upto N using Sieve Of Eratosthenes technique.
// A number is called prime if it has only two factors (1 and the number itself)
import java.util.*;
public class Sieve_Of_Eratosthenes
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Sieve_Of_Eratosthenes nm=new Sieve_Of_Eratosthenes();
        int n=sc.nextInt();
        // input taken upto which primes which will be displayed
        boolean k[]=nm.sieve_Of_Eratosthenes(n);
        // method calling where an integer value is sent and a boolean array is stored as a return value
        for(int i=0;i<=20000000;i++)
        {
            if(k[i])
            {
                System.out.print(i+" ");
            }
        }
        System.out.println();
    }
    boolean[] sieve_Of_Eratosthenes(int n)
    {
        boolean b[]=new boolean[n+1];
        // boolean array is created 
        Arrays.fill(b,true);
        b[0]=false;
        b[1]=false;
        // 0 & 1 are marked as false because they are neither prime nor composite
        for(int i=2;i<=Math.sqrt(n);i++)
        {
            // loop is started from 2
            if(b[i])
            {
                for(int j=i*i;j<=n;j+=i)
                {
                    // every multiple of the ith value is marked as false because they have a factor i
                    b[j]=false;
                }
            }
        }
        return b;
        // boolean array is returned
    }
}