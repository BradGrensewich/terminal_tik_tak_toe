package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.println("Enter cells: ");
        Scanner scanner = new Scanner(System.in);
        char[] values = new char[9];


        for (int i = 0; i < 9; i++) {
            values[i] = ' ';
        }
        printBoard(values);
        char[][] board = getBoard(values);
        int count = 0;

        while (true) {
            System.out.println("Enter the coordinates: ");

            char player;
            if (count % 2 == 0) {
                 player = 'X';
            } else {
                 player = 'O';
            }

            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.next();
                scanner.next();
                continue;
            }
            int row = scanner.nextInt() - 1;

            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.next();
                continue;
            }
            int column = scanner.nextInt() - 1;

            if (row > 2 || row < 0 || column > 2 || column < 0) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (!(board[row][column] == ' ' || board[row][column] == '_')) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            if (row == 0) {
                values[column] = player;
            }
            if (row == 1) {
                values[column + 3] = player;
            }
            if (row == 2) {
                values[column + 6] = player;
            }
            board = getBoard(values);
            printBoard(values);
            checkState(board);
            if (notFinished(board)) {
                count++;
            } else {
                break;
            }

        }
    }

    public static void checkState(char[][] board) {
        if (impossible(board) || checkWin(board, 'X') && checkWin(board, 'O')) {
            System.out.println("Impossible");
        } else if (checkWin(board, 'X')) {
            System.out.println("X wins");
        } else if (checkWin(board, 'O')) {
            System.out.println("O wins");
        } else if (notFinished(board)) {
            return;
        } else {
            System.out.println("Draw");
        }
    }

    public static boolean notFinished(char[][] board) {
        for (char[] line : board) {
            for (char square : line) {
                if (square == '_' || square == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean impossible(char[][] board) {
        int xCount = 0;
        int oCount = 0;
        for (char[] line : board) {
            for (char square : line) {
                if (square == 'X') {
                    xCount++;
                }
                if (square == 'O') {
                    oCount++;
                }
            }
        }
        return xCount > oCount + 1 || oCount > xCount + 1;
    }

    public static boolean checkWin(char[][] board, char player) {
        int diagonalCount = 0;
        int reverseDiagonal = 0;

        for (int row = 0; row < 3; row++) {
            int horizontalCount = 0;
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == player) {
                    horizontalCount++;
                }
                if (horizontalCount == 3) {
                    return true;
                }
            }
        }
        for (int column = 0; column < 3; column++) {
            int verticalCount = 0;
            for (int row = 0; row < 3; row++) {
                if (board[row][column] == player) {
                    verticalCount++;
                }
                if (verticalCount == 3) {
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == player) {
                diagonalCount++;
            }
            if (board[i][2 - i] == player) {
                reverseDiagonal++;
            }
            if (diagonalCount == 3 || reverseDiagonal == 3) {
                return true;
            }
        }
        return false;
    }

    public static void printBoard(char[] values) {
        System.out.println("---------");

        for (int i = 0; i < 9; i = i + 3) {
            System.out.println("| " + values[i] + " " + values[i + 1] + " " + values[i + 2] + " |");
        }

        System.out.println("---------");
    }

    public static char[][] getBoard (char[] values) {
        int index = 0;
        char[][] board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                board[row][column] = values[index];
                index++;
            }
        }
        return board;
    }


}





