package Programs.patterns;

public class Pattern7 {
    public static void main(String[] args) {
        pattern7(4);
    }
    //       1
    //    2 1 2
    //  3 2 1 2 3
    //4 3 2 1 2 3 4
    //  3 2 1 2 3
    //    2 1 2
    //      1
    static void pattern7(int n) {
        for (int row = 1; row <= 2 * n; row++) {

            int c = row > n ? 2 * n - row: row;

            for (int space = 0; space < n-c; space++) {
                System.out.print("  ");
            }

            for (int col = c; col >= 1; col--) {
                System.out.print(col + " ");
            }
            for (int col = 2; col <= c; col++) {
                System.out.print(col + " ");
            }

            System.out.println();
        }
    }
}
