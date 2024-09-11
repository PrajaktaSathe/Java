package day1_recursion;

import java.util.Arrays;
import java.util.Collections;

public class Rec_Op {
	int bigNum(int n) {
		return bigNum(n, -9);
	}
	int bigNum(int n,int max) {
		if(n==0)
			return max;
		if(max<n%10)
			return bigNum(n/10,n%10);
		return bigNum(n/10, max);
	}
	int prodDigNum(int n) {
		if(n<10) {
			return n;
		}
		return n%10*prodDigNum(n/10);
	}
	
	int factNum(int n) {
		if(n==1||n==0)
			return 1;
		return n*factNum(n-1);
	}
	int smallNum(int n) {
		return smallNum(n, 9);
	}
	int smallNum(int n,int min) {
		if(n==0)
			return min;
		if(min>n%10)
			return smallNum(n/10,n%10);
		return smallNum(n/10, min);
	}
	boolean isPerfect(int n) {
		return isPerfect(n,0,1);
	}
	private boolean isPerfect(int n, int s, int d) {
		if(d>n/2)
			return s==n;
		return isPerfect(n,(n%d==0)?s+d:s, d+1);
	}
	int countFact(int n) {
		return countFact(n,1);
	}
	private int countFact(int n, int i) {
		if(i>n/2)
			return 0;
		if(n%i==0)
			return 1+countFact(n,i+1);
		return countFact(n, i+1);
	}
	boolean isPal(String n) {
		return isPal(n,0,n.length()-1);
	}
	private boolean isPal(String n, int f, int l) {
		if(f>=l)
			return true;
		if(n.charAt(f)!=n.charAt(l))
			return false;
		return isPal(n, f+1, l-1);
	}
	int lcm(int m,int n) {
		if(m>n)
			return lcm(n,m);
		return lcm(m,n,n);
	}
	private int lcm(int m, int n, int prod) {
		if(prod%m==0) {
			return prod;
		}
		return lcm(m,n,prod+n);
	}
	int fibNum(int n) {
		if(n==2||n==1)
			return n;
		return fibNum(n-1)+fibNum(n-2);
	}
	int binSearch(int[] ar,int n) {
		Arrays.sort(ar);
		return binSearch(ar,n,0,ar.length-1);
	}
	private int binSearch(int[] ar, int n, int i, int j) {
		if(i>j)
			return -1;
		int m=(i+j)/2;
		if(ar[m]>n)
			return binSearch(ar, n, i, m);
		else if(ar[m]<n)
			return binSearch(ar, n, m, j);
		return m;
	}
	
	//Program complexity is 2^n - 1 as for n disc we have to do 2^n - 1
	void tOHanoi(int n,char s,char t,char d) {
		if(n==1)
			System.out.println("disc"+n+"moves from"+s+" to "+d);
		tOHanoi(n-1, s, d, t);
		System.out.println("disc"+n+"moves from"+s+" to "+d);
		tOHanoi(n-1, t, s, d);
	}
	int sumEvenDig(int n) {
		if(n==0)
			return 0;
		int d=n%10;
		if(d%2==0)
			return d+ sumEvenDig(n/10);
		return sumEvenDig(n/10);
	}
	String decToBin(int d) {
		if(d==0||d==1)
			return d+"";
		return d%2+""+decToBin(d%2);
	}
	String decToOct(int d) {
		if(d<8)
			return d+"";
		return d%8+""+decToOct(d/8);
	}
}