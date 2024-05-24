import java.util.Scanner;

public class PalindromeUsingRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the input String :");
		String s=sc.nextLine();
		boolean res= isPalindrome(s,0,s.length()-1);
		if(res)
			System.out.println("given String is Palindrome");
		else
			System.out.println("given String is not a  Palindrome");

	}
	public static boolean isPalindrome(String s,int st,int lt)
	{
		if(s.charAt(st)!=s.charAt(lt))
			return false;
		if(st>=lt)
			return true;
		return isPalindrome(s,st+1,lt-1);
	}

}
