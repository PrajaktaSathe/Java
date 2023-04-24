package Programs.patterns;
//Program to generate a hollow diamond pattern
public class Hollow_diamond {
    public static void main(String[] args){
        pattern10(5);
//     *    
//    * *
//   *   *
//  *     *
// *       *
//  *     *
//   *   *
//    * *
//     *  
    
     }
    static void pattern10(int n)
    {
        //upper v pattern
		for(int i=1;i<=n;i++)
		{
			for(int j=n;j>i;j--)
				System.out.print(" ");
			System.out.print("*");
			for(int k=1;k<2*(i-1);k++)
				System.out.print(" ");
			if(i==1)
				System.out.print("");
			else
				System.out.print("*");
			
			System.out.println();
		}
        //lower v pattern
        for(int i=(n-1);i>=1;i--)
		{
			for(int j=n;j>i;j--)
				System.out.print(" ");
			System.out.print("*");
			for(int k=1;k<2*(i-1);k++)
				System.out.print(" ");
			if(i==1)
				System.out.print("");
			else
				System.out.print("*");
			
			System.out.println();
		}
    } 
    
}
