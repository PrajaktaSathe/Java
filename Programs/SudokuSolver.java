
/**
 * A class for solving Sudoku puzzles using a backtracking algorithm.
 */
public class SudokuSolver {
    /**
     * Solves the Sudoku puzzle using a recursive backtracking algorithm.
     *
     * @param board The Sudoku board to be solved.
     * @return true if the puzzle is solved successfully, false if no solution exists.
     */
    public static boolean solveSudoku(int[][] board) {
        int N = board.length;
        int[] emptyCell = findEmptyCell(board);

        if (emptyCell == null) {
            // No empty cells left, puzzle is solved
            return true;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        for (int num = 1; num <= N; num++) {
            if (isValidMove(board, row, col, num)) {
                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true; // If the puzzle can be solved from here
                }

                board[row][col] = 0; // If not, backtrack
            }
        }

        return false; // If no valid number can be placed, trigger backtracking
    }

    /**
     * Finds the next empty cell in the Sudoku board.
     *
     * @param board The Sudoku board to search for empty cells.
     * @return An array containing the row and column indices of the empty cell, or null if no empty cells are found.
     */
    public static int[] findEmptyCell(int[][] board) {
        int[] result = new int[2];
        int N = board.length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return null; // If no empty cell is found, the puzzle is solved
    }

    /**
     * Checks if a number can be placed in a given cell without violating Sudoku rules.
     *
     * @param board The Sudoku board.
     * @param row   The row index of the cell.
     * @param col   The column index of the cell.
     * @param num   The number to be placed.
     * @return true if the move is valid, false otherwise.
     */
    public static boolean isValidMove(int[][] board, int row, int col, int num) {
        int N = board.length;

        // Check if the number is already in the row or column
        for (int i = 0; i < N; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check if the number is already in the 3x3 subgrid
        int subgridSize = (int) Math.sqrt(N);
        int subgridStartRow = row - (row % subgridSize);
        int subgridStartCol = col - (col % subgridSize);

        for (int i = 0; i < subgridSize; i++) {
            for (int j = 0; j < subgridSize; j++) {
                if (board[subgridStartRow + i][subgridStartCol + j] == num) {
                    return false;
                }
            }
        }

        return true; // If no conflicts, the move is valid
    }

    /**
     * Prints the Sudoku board.
     *
     * @param board The Sudoku board to be printed.
     */
    public static void printBoard(int[][] board) {
        int N = board.length;
        for (int[] ints : board) {
            for (int j = 0; j < N; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Main method to test the SudokuSolver class.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)) {
            System.out.println("Sudoku Solution:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}

