package Programs.patterns;

public class Pattern1 {
    public static void main(String[] args) {
        pattern1(4);
    }
//        *
//        * *
//        * * *
//        * * * *
    private static void pattern1(int n) {
        // for every row, run the col
        for (int row = 0; row <= n; row++) {
            for (int col = 0; col < row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}

