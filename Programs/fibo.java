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
          
          System.out.println(a);
          
          int c = a+b;
          
          a=b;
          b=c;
      }
      
      
   }
  }