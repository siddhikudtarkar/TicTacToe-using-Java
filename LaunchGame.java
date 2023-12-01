import java.util.Random;
import java.util.Scanner;

class TicTacToe {

    static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initBoard();
    }

    // Initialize the game board with empty spaces
    void initBoard() {
        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {

                board[i][j] = ' ';
            }
        }
    }

    // Display the current state of the game board
    static void dispBoard() {
        System.out.println("-------------");

        for (int i = 0; i < board.length; i++) {

            System.out.print("| ");

            for (int j = 0; j < board[i].length; j++) {

                System.out.print(board[i][j] + " | ");
            }
            System.out.println();

            System.out.println("-------------");
        }
    }

    // Place a player's mark on the board at the specified position
    static void placeMark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {

            board[row][col] = mark;
        } else {
            System.out.println("Invalid Position");
        }
    }

    // Check if any player has won in a column
    static boolean checkColWin() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    // Check if any player has won in a row
    static boolean checkRowWin() {
        for (int i = 0; i < 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    // Check if any player has won diagonally
    static boolean checkDiagWin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]
                ||
                board[0][2] != ' ' && board[0][2] == board[1][1] &&
                        board[1][1] == board[2][0]) {
            return true;
        } else {
            return false;
        }
    }

    // Check if the game is a draw
    static boolean checkDraw() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

// Abstract class representing a player
abstract class Player {
    String name;
    char mark;

    // Check if the move is valid within the bounds of the board and the cell is
    // empty
    boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }

    abstract void makeMove();
}

// Human player class
class HumanPlayer extends Player {

    HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makeMove() {
        int row;
        int col;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter the row and column");
            row = sc.nextInt();
            col = sc.nextInt();
        } while (!isValidMove(row, col));
        TicTacToe.placeMark(row, col, mark);

    }
}

// AI player class
class AIPlayer extends Player {

    AIPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    // Generate random row and column for AI move and make a move
    void makeMove() {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;

        do {
            Random r = new Random();
            System.out.println("Enter the row and column");
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (!isValidMove(row, col));
        TicTacToe.placeMark(row, col, mark);
    }
}

// Main class to launch the Tic Tac Toe game
public class LaunchGame {
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();

        HumanPlayer p1 = new HumanPlayer("Bob", 'X');
        AIPlayer p2 = new AIPlayer("AI", 'O');

        Player cp;
        cp = p1;

        while (true) {

            System.out.println(cp.name + " turn");
            cp.makeMove();

            TicTacToe.dispBoard();

            if (TicTacToe.checkColWin() || TicTacToe.checkRowWin() ||
                    TicTacToe.checkDiagWin()) {
                System.out.println(cp.name + " has won");
                break;
            } else if (TicTacToe.checkDraw()) {
                System.out.println("Game is a draw");
                break;
            } else {
                if (cp == p1) {
                    cp = p2;
                } else {
                    cp = p1;
                }
            }
        }

    }
}
