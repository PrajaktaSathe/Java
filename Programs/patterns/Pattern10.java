package pattern;

import java.util.*;

class Pattern10{
	


	static Scanner Sc=new Scanner(System.in);
	public static void main(String args[])
	{
		int n;
		n=Sc.nextInt();
		pattern10(n);
		
	}
	
//	A
//	BB
//	CCC
//	DDDD
//	EEEEE

	private static void pattern10(int n) {
		char ch='A';
		for(int i=0;i<n;i++){
			for(int j=0;j<=i;j++){
				System.out.print((char)(ch+i));
			}
			System.out.println();
		}
		
	}
	
}