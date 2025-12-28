// Program to demonstrate the use of static member functions -
package staticMemberFunctions;

class StaticFunc {
    static int a = 5, b;

    static {
        System.out.println("Static block");
        b = a * 10;
    }

    static void method(int x) {
        System.out.println("x = " + x);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}

public class StaticMemberFunctions {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        staticMemberFunctions.StaticFunc.method(10);
    }
}
