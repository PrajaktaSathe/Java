import java.util.*;

  public class fibo{

  public static void main(String[] args) {
      // write your code here

      int a = 0;
      int b=1;

      Scanner sc = new Scanner(System.in);

      int n= sc.nextInt();
      sc.close();

      for(int i=0; i<n; i++){

          System.out.print(a+" ");

          int c = a+b;

          a=b;
          b=c;
      }
      // fibbnacci using dynamic programming

      public static int fibdp(int n) { // bottom up approach
		int storage[]=new int[n+1];
		storage[0]=0;
		storage[1]=1;
		for(int i=2;i<=n;i++) {
			storage[i]= storage[i-1]+storage[i-2];
		}
		return storage[n];
	}

	public static void main(String[] args) {
       int n=44;
       System.out.println(fibM(n));
       System.out.println(fibdp(n));

       System.out.println(fib(n));


   }
  }
