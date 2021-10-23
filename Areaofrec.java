package practice.constructor.program;
import java.util.Scanner;
public class Areaofrec{
  int length,breadth;
   public Areaofrec(int l, int b)
  {
    length = l;
    breadth = b;
  }
  public int returnarea()
  {
    return length*breadth;
  }
}
class Ans
{
  public static void main(String args[] )
  {
    Scanner s = new Scanner(System.in);
    int l,b;
    System.out.println("Enter length:");
    l = s.nextInt();
    System.out.println("Enter breadth:");
    b = s.nextInt();
    Areaofrec a = new Areaofrec(l,b);
    System.out.println("Area of Rectangle: "+a.returnarea());
  }
}	