//Program to find distinct elements
public class DistinctElements {
static int dupliremoveint(int a[]) {
	int rd=0;
	for(int i=1;i<a.length;i++) {
		if(a[rd]!=a[i]) {
			rd++;
			a[rd]=a[i];
		}
	}
	return rd+1;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
int a[]= {1,1,2,2,4,5,5,5,6,6,7};
for(int i=0;i<a.length;i++) {
	System.out.print(a[i]+" ");
}
System.out.println("\n Distinct Elements:");
int b=dupliremoveint(a);
for(int i=0;i<b;i++) {
	System.out.print(a[i]+" ");
}
	}

}
