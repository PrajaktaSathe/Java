package Programs.patterns;

public class Pattern2 {
    public static void main(String[] args) {
        patter2(4);
    }
//        * * * *
//        * * * *
//        * * * *
//        * * * *
//        * * * *
    private static void patter2(int n) {
        // for every row, run the col
        for (int row = 0; row <= n; row++) {
            for (int col = 0; col < n; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
