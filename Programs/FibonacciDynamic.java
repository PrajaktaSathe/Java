import java.util.Arrays;
import java.util.Scanner;

public class fibo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("How many Fibonacci digits do you want?: ");
        int digits = sc.nextInt();

        // Normal Fibonacci
        int a = 0;
        int b = 1;
        int ab = a + b;

        System.out.print("0 ");
        System.out.print("1 ");
        for (int i = 0; i < digits; i++) {
            System.out.printf("%d ", ab);
            a = b;
            b = ab;
            ab = a + b;
        }

        // Dynamic Programming Fibonacci,
        System.out.println(Arrays.toString(FibonacciDynamic(digits)));

        sc.close();
    }

    public static int[] FibonacciDynamic(int size) { // bottom up approach
        int[] fibonacciDigits = new int[size + 1];
        fibonacciDigits[0] = 0;
        fibonacciDigits[1] = 1;
        for (int i = 2; i <= size; i++) {
            fibonacciDigits[i] = fibonacciDigits[i - 1] + fibonacciDigits[i - 2];
        }
        return fibonacciDigits;
    }
}
