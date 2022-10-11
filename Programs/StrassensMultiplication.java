//Program to multiply two square matrices using Strassen's Multiplication
import java.util.*;
public class StrassensMultiplication
{
    public static void main(String[] args) 
    {
        int n,m;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows and columns of the first matrix : ");
        n = sc.nextInt();
        System.out.println("Enter the number of rows and columns of the second matrix : ");
        m = sc.nextInt();
        int[][] a = new int[n][n];
        int[][] b = new int[m][m];
        int[][] c = new int[2][2];
        int i, j, m1, m2, m3, m4, m5, m6, m7;

        //Taking input from the user
        System.out.println("Enter all the elemens of the first matrix : ");
        for(i=0; i<n; i++)
        {
            for(j=0; j<n; j++)
            {
                a[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter all the elemens of the second matrix : ");
        for(i=0; i<m; i++)
        {
            for(j=0; j<m; j++)
            {
                b[i][j] = sc.nextInt();
            }
        }

        //Strassen's multiplication
        m1= (a[0][0] + a[1][1]) * (b[0][0] + b[1][1]);
        m2= (a[1][0] + a[1][1]) * b[0][0];
        m3= a[0][0] * (b[0][1] - b[1][1]);
        m4= a[1][1] * (b[1][0] - b[0][0]);
        m5= (a[0][0] + a[0][1]) * b[1][1];
        m6= (a[1][0] - a[0][0]) * (b[0][0]+b[0][1]);
        m7= (a[0][1] - a[1][1]) * (b[1][0]+b[1][1]);

        c[0][0] = m1 + m4- m5 + m7;
        c[0][1] = m3 + m5;
        c[1][0] = m2 + m4;
        c[1][1] = m1 - m2 + m3 + m6;

        //Display the output matrix
        System.out.println("Ouput Matrix : ");
        for(i=0; i<n; i++)
        {
            for(j=0; j<m; j++)
            {
                System.out.print(c[i][j]+"\t");
            }
            System.out.println();
        }
    }
}