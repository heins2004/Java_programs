import java.util.Scanner;

public class TicTacToe {

    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char player = 'X';
        boolean gameOver = false;

        while (!gameOver) {

            printBoard();

            System.out.println("Player " + player + " turn");
            System.out.print("Enter row (0-2): ");
            int row = sc.nextInt();
            System.out.print("Enter col (0-2): ");
            int col = sc.nextInt();

            if (board[row][col] == ' ') {
                board[row][col] = player;

                if (checkWin(player)) {
                    printBoard();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (isFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    player = (player == 'X') ? 'O' : 'X';
                }

            } else {
                System.out.println("Cell already taken! Try again.");
            }
        }

        sc.close();
    }

    static void printBoard() {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    static boolean checkWin(char p) {

        // rows & columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p)
                return true;

            if (board[0][i] == p && board[1][i] == p && board[2][i] == p)
                return true;
        }

        // diagonals
        if (board[0][0] == p && board[1][1] == p && board[2][2] == p)
            return true;

        if (board[0][2] == p && board[1][1] == p && board[2][0] == p)
            return true;

        return false;
    }

    static boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }
}