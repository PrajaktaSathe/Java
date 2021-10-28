/*
Make a Calculator.
Take 2 numbers(a&b)from the user and an operation as follows:

1:+(Addition)a+b.
2:-(Subtraction)a-b.
3:*(Multiplication)a*b.
4:/(Division)a/b.
5:%(Moduloorremainder)a%b.

Calculate the result according to the operation given and display it to the user.
*/

package chapter3sol;

import java.util.*;


public class s1 {
       public static void main(String[] args) { 
              Scanner sc = new Scanner(System.in);
              System.out.println("Enter First Number: ");
              double a = sc.nextDouble();

              System.out.println("Enter Second Number: ");
              double b = sc.nextDouble();
         
              System.out.print("choose one: \n 1. For Addition\n 2. For Substraction\n 3. For Multiplication\n 4. For Division\n 5. For Modulation \n : ");
              int n = sc.nextInt();
       
              switch (n) {
              case 1:  
                     System.out.println("Addition: "+(a+b));  
                     break;
              case 2: 
                     System.out.println("Substraction: "+(a-b)); 
                     break;
              case 3: 
                     System.out.println("Multiplication: "+(a*b)); 
                     break; 
              case 4: 
                     System.out.println("Division: "+(a/b));
                     break;       
              default:
                     System.out.println("Plz Choose Right Option");
                     break;
              }
       }
}

    
