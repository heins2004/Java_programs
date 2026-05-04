public class SudokuSolver {

    static final int SIZE = 9;

    public static void main(String[] args) {

        int[][] board = {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        };

        if (solve(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    static boolean solve(int[][] board) {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == 0) {

                    for (int num = 1; num <= 9; num++) {

                        if (isSafe(board, row, col, num)) {

                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            board[row][col] = 0; // backtrack
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    static boolean isSafe(int[][] board, int row, int col, int num) {

        // row check
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) return false;
        }

        // column check
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) return false;
        }

        // 3x3 box check
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) return false;
            }
        }

        return true;
    }

    static void printBoard(int[][] board) {
        System.out.println("\nSolved Sudoku:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}