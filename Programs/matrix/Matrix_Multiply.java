// Program to multply two matrices in Java
package matrix;
import java.util.Scanner;
public class Matrix_Multiply {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
		System.out.println("Enter the row and column number for 1st matrix");
			int n=sc.nextInt();
			int m=sc.nextInt();
			// Input for the row and column number
			int a1[][] = new int[n][m];  
			// New 2D array for first matrix
			System.out.println("Enter "+(n*m)+ " numbers");
			// Taking input of the first matrix
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					a1[i][j] = sc.nextInt();
				}
			}
			int n1=sc.nextInt();
			int m1=sc.nextInt();
			// Input for the row and column number
			int a2[][] = new int[n1][m1];  
			// New 2D array for second matrix
			System.out.println("Enter "+(n1*m1)+ " numbers");
			// Taking input of the second matrix
			for (int i = 0; i < n1; i++) {
				for (int j = 0; j < m1; j++) {
					a2[i][j] = sc.nextInt();
				}
			}
			if(m!=n1)
			{
				System.out.println("Invalid coordinates of the matrix");
				return;
			}
			int multiply[][] = new int[n][m1];  
			// New 2D array for multiplication of the two matrices - 
			System.out.println("The multiplication of two matrices are - ");
			// Performing multiplication of two matrices - 
			for(int i=0;i<n;i++)
			{
			    for(int j=0;j<m1;j++)
			    {
			        multiply[i][j]=0;
			        for(int d=0;d<m;d++)
			        {
			            multiply[i][j]=multiply[i][j]+(a1[i][d]*a2[d][j]);
						// multiplication method
			        }
			    }
			}
			// Displaying multiplication of two matrices - 
			for(int i=0;i<n;i++)
			{
			    for(int j=0;j<m1;j++)
			    {
			        System.out.print(multiply[i][j]+" ");
			    }
			    System.out.println();
			}
		}
	}
}