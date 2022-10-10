//Program to implement 0-1 Knapsack using DYNAMIC PROGRAMMING
import java.util.*;
public class KnapSack
{
    public int max(int a, int b)
    {
        return (a > b)?a:b;
    }

    public int knapSack(int W, int wt[], int val[], int n)
    {
        int i, w;
        int[][] K = new int[n+1][W+1];
        for(i=0; i<=n; i++)
        {
            for(w=0; w<=W; w++)
            {
                if(i == 0 || w == 0)
                {
                    K[i][w] = 0;
                }
                else if(wt[i-1] <= w)
                {
                    K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]], K[i-1][w]);
                }
                else
                {
                    K[i][w] = K[i-1][w];
                }
            }
        }
        return K[n][W];
    }

    public static void main(String[] args) 
    {
        int i, n, W;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of items : ");
        n = sc.nextInt();
        System.out.println("Enter the maximum weight/capacity : ");
        W = sc.nextInt();
        int wt[] = new int[n];
        int val[] = new int[n];
        System.out.println("Enter the weights of the items : ");
        for(i=0; i<n; i++)
        {
            wt[i] = sc.nextInt();
        }
        System.out.println("Enter their corresponding values : ");
        for(i=0; i<n; i++)
        {
            val[i] = sc.nextInt();
        }
        KnapSack ob = new KnapSack();
        int res = ob.knapSack(W, wt, val, n);
        System.out.println("Total profit : "+res);
    }
}
