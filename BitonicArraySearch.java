//Program to search in a Bitonic Array
import java.util.Scanner;
public class BitonicArraySearch {
static int ascendingBS(int a[], int k, int range) {
	int l=0,h=range,m=0;
	while(l<=h) {
		m=(l+h)/2;
		if(a[m]==k) {
			return m;
		}else if(k<a[m]){
			h=m-1;
		}else {
			l=m+1;
		}
	}
	return -1;
}
static int descendingBS(int a[], int k, int range) {
	int l=range,h=a.length-1,m=0;
	while(l<=h) {
		m=(l+h)/2;
		if(a[m]==k) {
			return m;
		}else if(k<a[m]){
			l=m+1;
		}else {
			h=m-1;
		}
	}
	return -1;
}
static int bitonicpoint(int a[]) {
	int l=0,r=a.length-1,m=0;
	while(l<=r) {
		m=(l+r)/2;
		if(a[m]>a[m-1] && a[m]>a[m+1]) {
			return m;
		}else if(a[m]>a[m-1] && a[m]<a[m+1]){
			l=m;
		}else {
			r=m+1;
		}
	}
	return -1;
}
static int searchbitonic(int a[],int k,int bindex) {
	if(k==a[bindex]) {
		return bindex;
	}
	if(k>a[bindex]) {
		return -1;
	}
	int res1=ascendingBS(a, k, bindex-1);
	int res2=descendingBS(a, k, bindex+1);
	if (res1!=-1) {
		return res1;
	}
	else if(res2!=-1) {
		return res2;
	}
	return -1;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Number:");
		int k=sc.nextInt();
		int a[]= {1,3,6,7,9,12,8,5,2};
		int bindex=bitonicpoint(a);
		int b=searchbitonic(a, k, bindex);
		if (b == -1) {
			System.out.println("Element Not Found In Sequence");
		}
		else {
			System.out.println("Element Found at index: "+ b);
		}
	}
}