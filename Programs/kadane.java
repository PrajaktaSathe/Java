
import java.io.*;
import java.util.*;
 
class Kadane
{
    public static void main (String[] args)
    {
        int [] a = {10,13,11,22,34,50,20,80};
        System.out.println("Sub array with maximum sum is " +maxSubArray(a));
                                       
    }
 
    static int maxSubArray(int a[])
    {
        int l = a.length;
        int max = Integer.MIN_VALUE, max_ending = 0;
        for (int i = 0; i < l; i++)
        {
            max_ending = max_ending + a[i];
            if (max < max_ending)
                max = max_ending;
            if (max_ending < 0)
                max_ending = 0;
        }
        return max;
    }
}