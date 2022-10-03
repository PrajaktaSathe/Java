package Programs.patterns;

public class Pattern8 {
    public static void main(String[] args) {
        pattern8(4);
    }

    //      1
    //    2 1 2
    //  3 2 1 2 3
    //4 3 2 1 2 3 4
    static void pattern8(int n) {
        for (int row = 1; row <= n; row++) {

            for (int space = 0; space < n-row; space++) {
                System.out.print("  ");
            }

            for (int col = row; col >= 1; col--) {
                System.out.print(col + " ");
            }
            for (int col = 2; col <= row; col++) {
                System.out.print(col + " ");
            }

            System.out.println();
        }
    }
}
