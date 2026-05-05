import java.util.Scanner;

public class SudokuSolver {

    static final int SIZE = 9;
    static int solutionCount = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[][] board = new int[SIZE][SIZE];

        System.out.println("Enter Sudoku (use 0 for empty cells):");

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        if (!isValidBoard(board)) {
            System.out.println("Invalid Sudoku input!");
            return;
        }

        solve(board);

        if (solutionCount == 0) {
            System.out.println("No solution exists.");
        } else {
            System.out.println("Total solutions found: " + solutionCount);
        }

        sc.close();
    }

    static void solve(int[][] board) {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == 0) {

                    for (int num = 1; num <= 9; num++) {

                        if (isSafe(board, row, col, num)) {

                            board[row][col] = num;

                            solve(board);

                            board[row][col] = 0; // backtrack
                        }
                    }
                    return;
                }
            }
        }

        // Found a solution
        solutionCount++;
        System.out.println("\nSolution " + solutionCount + ":");
        printBoard(board);
    }

    static boolean isSafe(int[][] board, int row, int col, int num) {

        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean isValidBoard(int[][] board) {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                int num = board[row][col];

                if (num != 0) {
                    board[row][col] = 0;

                    if (!isSafe(board, row, col, num)) {
                        return false;
                    }

                    board[row][col] = num;
                }
            }
        }

        return true;
    }

    static void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
