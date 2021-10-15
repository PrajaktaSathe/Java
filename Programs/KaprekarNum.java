//Java program to find the frequency of Kaprekar numbers within a defined range.
import java.util.*;
class KaprekarNum
{
    Scanner sc=new Scanner(System.in);
    int num;
    KaprekarNum()
    {
        num=0;   
    }
    void range()
    {
        System.out.println("Enter range");
        int r=sc.nextInt();
        int s=sc.nextInt();int c=0;
        for(int i=r;i<=s;i++)
        {
            if(i*i>9)
            {
                if(check(i)==1)
                {
                    System.out.print(i + "  ");c++;
                }
            }
            if(i==1||i==0)
            {
                c++;
                System.out.print(i + "  ");
            }
        }
        System.out.println("frequency="+ c);
    }
    int check(int n)
    {
        int sq=n*n;
        String str=Integer.toString(sq);
        int l=str.length(),c=0;
        int sum=Integer.valueOf(str.substring(0,l/2))+Integer.valueOf(str.substring(l/2));
        if(n==sum)
            return 1;
        else return 0;
    }
    public static void main()
    {
        KaprekarNum ob= new KaprekarNum();
        ob.range();
    }
}
/*Enter range
1
100
1  9  45  55  99  frequency=5
 * 
 */
