/*Hey there, I used modular approach to make this code more understandable and easier
 In this code, I have created three methods:
 1. Main method: It takes input from user and calls required methods in order to check wheteher the number is palindrome or not.thus,displaying correct output
 2. is palindrome method: Checks whether the value 'val' is palindrome or not stored in 'result' by calling Reverse method.
 3. Reverse method : Simply returns the reverse value 'rev' of entered number by mathematical approach.

Overall Time Complexity: O(log n)
Overall Space Complexity: O(1)

@author : https://github.com/Ananyaog
 */
import java.util.*;
class palindrome 
{
   
    public static void main(String args[]) // Space Complexity: O(1) & Time Complexity: O(log n)
    {
      Scanner sc=new Scanner(System.in);

      System.out.println("Enter the value");
        int val=sc.nextInt();

        sc.close();

        palindrome pd=new palindrome();
        boolean result=pd.ispalindrome(val);

        if(result==true)
        {
            System.out.print(val+" is a Palindrome number");
        }
        else
        {
            System.out.print(val+" is not a Palindrome number");
        }
    }

    public boolean ispalindrome(int num) // Space Complexity: O(1) & Time Complexity: O(log n)
    {
        if(Reverse(num)==num)
            return true;
        else
            return false;

    }

    public int Reverse(int num)  // Space Complexity: O(1) & Time Complexity: O(log n)
    {
        int reverse=0;
        int temp=num;
        while(temp!=0)
        {
        int mod=temp%10;
        reverse=reverse*10+mod;
        temp/=10;
        }
        return reverse;
    }
}


    
        
    
