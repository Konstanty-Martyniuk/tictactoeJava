package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int moveNumber = 0;
        int[] coordinates = new int[2];
        char[][] field = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        printField(field);
        //field is done, ask user for input
        do {
            do {
                isValid = validateInput(coordinates, field, moveNumber);
            } while (!isValid);

        } while (!checkGameState(field, moveNumber));
    }

    static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.print("| " + field[i][j] + " ");
                } else if (j == 1) {
                    System.out.print(field[i][j] + " ");
                } else if (j == 2)
                    System.out.print(field[i][j] + " |");
            }
            System.out.println(" ");
        }
        System.out.println("---------");
    }

    static boolean checkGameState(char[][] field, int moveNumber) {
        if (isWinner(field)) {
            return true;
        } else if (moveNumber == 8) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    static boolean isWinner(char[][] field) {
        boolean result = false;
        int xWins = 0;
        int oWins = 0;

        for (int i = 0; i < 3; i++) {
            if (field[i][0] + field[i][1] + field[i][2] == 264 ||
                    field[0][i] + field[1][i] + field[2][i] == 264) {
                xWins++;
            } else if (field[i][0] + field[i][1] + field[i][2] == 237 ||
                    field[0][i] + field[1][i] + field[2][i] == 237) {
                oWins++;
            }
        }
        if (field[0][0] + field[1][1] + field[2][2] == 264 ||
                field[2][0] + field[1][1] + field[0][2] == 264) {
            xWins++;
        } else if (field[0][0] + field[1][1] + field[2][2] == 237 ||
                field[2][0] + field[1][1] + field[0][2] == 237) {
            oWins++;
        }

        if (xWins > 0) {
            System.out.println("X wins");
            result = true;
        } else if (oWins > 0) {
            System.out.println("O wins");
            result = true;
        }
        return result;
    }

    static boolean validateInput(int[] coordinates, char[][] field, int moveNumber) {
        System.out.print("Enter the coordinates: ");
        Scanner scanner = new Scanner(System.in);
        boolean result = false;
        String rawCoords = scanner.nextLine();
        if (!result) {
            try {
                coordinates = Arrays.stream(rawCoords.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                result = true;

            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }

        result = validateBounds(coordinates, result);

        result = validateCell(coordinates, result, moveNumber, field);

        return result;
    }

    private static
    boolean validateCell(int[] coordinates, boolean result, int moveNumber, char[][] field) {
        if (result) {
            if (field[coordinates[0] - 1][coordinates[1] - 1] != 'X' && field[coordinates[0] - 1][coordinates[1] - 1] != 'O') {
                field[coordinates[0] - 1][coordinates[1] - 1] = moveNumber % 2 == 0 ? 'X' : 'O';
                System.out.print(coordinates[0] + " " + coordinates[1] + "\n");
                moveNumber++;
                printField(field);
                result = true;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                result = false;
            }
        }
        return result;
    }

    private static
    boolean validateBounds(int[] coordinates, boolean result) {
        if (result) {
            if (coordinates[0] > 0 && coordinates[0] < 4 && coordinates[1] > 0 && coordinates[1] < 4) {
                result = true;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                result = false;
            }
        }
        return result;
    }
}