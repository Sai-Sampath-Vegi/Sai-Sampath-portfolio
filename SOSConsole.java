import java.util.Scanner;
import java.util.Locale;

public class SOSConsole {
    public static void main(String args[]) {
        new PlaySosGame().play();
    }
}

class PlaySosGame {
    int scoreOfPlayerOne = 0, scoreOfPlayerTwo = 0;
    char arrBoard[][] = new char[16][16];
    String arrPositions[][] = {
            { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F" },
            { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F" },
            { "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F" },
            { "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F" },
            { "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F" },
            { "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F" },
            { "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F" },
            { "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F" },
            { "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F" },
            { "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F" },
            { "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF" },
            { "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF" },
            { "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF" },
            { "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF" },
            { "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF" },
            { "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" } };
    Scanner scan = new Scanner(System.in);

    void init() {
        for (int rowCounter = 0; rowCounter < 16; rowCounter++) {
            for (int columnCounter = 0; columnCounter < 16; columnCounter++) {
                arrBoard[rowCounter][columnCounter] = '-';
            }
        }
    }

    void printPositions() {
        int rowCounter, columnCounter;
        for (rowCounter = 0; rowCounter < 16; rowCounter++) {
            for (columnCounter = 0; columnCounter < 16; columnCounter++) {
                System.out.print(arrPositions[rowCounter][columnCounter]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    void printBoard() {
        int rowCounter, columnCounter;

        System.out.print("...Board...\n");
        for (rowCounter = 0; rowCounter < 16; rowCounter++) {
            for (columnCounter = 0; columnCounter < 16; columnCounter++) {
                System.out.print(arrBoard[rowCounter][columnCounter] + " ");
            }

            if (rowCounter == 14) {
                System.out.print("\tPlayer1 score is " + scoreOfPlayerOne);
            } else if (rowCounter == 15) {
                System.out.print("\tPlayer2 score is " + scoreOfPlayerTwo);
            }
            System.out.println();
        }
    }

    void getPlayerMove(int player) {
        int rowCounter, columnCounter;
        boolean isFilled = false, isPositionOccupied = false;
        char userCharacter;
        String currentPlayerPosition;

        if (player == 1) {
            System.out.print("Enter player1 move\n");
        } else {
            System.out.print("Enter player2 move\n");
        }

        do {
            System.out.print("Enter position:");
            currentPlayerPosition = scan.nextLine();
            currentPlayerPosition = currentPlayerPosition.toUpperCase(Locale.ENGLISH);

            for (rowCounter = 0; rowCounter < 16; rowCounter++) {
                for (columnCounter = 0; columnCounter < 16; columnCounter++) {
                    if (currentPlayerPosition.equals(arrPositions[rowCounter][columnCounter])) {
                        if (arrBoard[rowCounter][columnCounter] == '-') {
                            do {
                                System.out.print("Enter the character (S or O):");
                                userCharacter = scan.nextLine().charAt(0);

                                if ((userCharacter != 's' && userCharacter != 'S')
                                        && (userCharacter != 'o' && userCharacter != 'O')) {
                                    System.out.print("Enter the character again.\n");
                                } else {
                                    break;
                                }
                            } while ((userCharacter != 's' || userCharacter != 'S')
                                    || (userCharacter != 'o' || userCharacter != 'O'));

                            userCharacter = Character.toUpperCase(userCharacter);
                            arrBoard[rowCounter][columnCounter] = userCharacter;
                            isFilled = true;
                            updateScore(currentPlayerPosition, player);
                        } else {
                            System.out.print("Position already occupied.\nPlease enter it again.\n");
                            isPositionOccupied = true;
                        }
                    }
                }
            }

            if ((!isFilled) && (isPositionOccupied != true)) {
                System.out.print("You entered wrong position.\nPlease enter it again.\n");
            }
        } while (!isFilled);
    }

    void addPoint(int player) {
        if (player == 1) {
            scoreOfPlayerOne++;
        } else {
            scoreOfPlayerTwo++;
        }
    }

    void updateScore(String lastPos, int player) {
        int row = -1, column = -1, currentScoreOfPlayerOne = scoreOfPlayerOne,
                currentScoreOfPlayerTwo = scoreOfPlayerTwo;

        if (lastPos.charAt(0) >= '0' && lastPos.charAt(0) <= '9') {
            row = lastPos.charAt(0) - '0';
        }

        if (lastPos.charAt(0) >= 'A' && lastPos.charAt(0) <= 'F') {
            if (lastPos.charAt(0) == 'A') {
                row = 10;
            } else if (lastPos.charAt(0) == 'B') {
                row = 11;
            } else if (lastPos.charAt(0) == 'C') {
                row = 12;
            } else if (lastPos.charAt(0) == 'D') {
                row = 13;
            } else if (lastPos.charAt(0) == 'E') {
                row = 14;
            } else if (lastPos.charAt(0) == 'F') {
                row = 15;
            }
        }

        if (lastPos.charAt(1) >= '0' && lastPos.charAt(1) <= '9') {
            column = lastPos.charAt(1) - '0';
        }

        if (lastPos.charAt(1) >= 'A' && lastPos.charAt(1) <= 'F') {
            if (lastPos.charAt(1) == 'A') {
                column = 10;
            } else if (lastPos.charAt(1) == 'B') {
                // column = 11;
            } else if (lastPos.charAt(1) == 'C') {
                column = 12;
            } else if (lastPos.charAt(1) == 'D') {
                column = 13;
            } else if (lastPos.charAt(1) == 'E') {
                column = 14;
            } else if (lastPos.charAt(1) == 'F') {
                column = 15;
            }
        }

        if (arrBoard[row][column] == 'O') {
            if ((row == 0 && column == 0) || (row == 15 && column == 0) || (row == 0 && column == 15)
                    || (row == 15 && column == 15)) {
                return;
            } else {
                if ((row == 0 || row == 15) && (arrBoard[row][column - 1] == 'S' && arrBoard[row][column + 1] == 'S')) {
                    addPoint(player);
                } else if ((column == 0 || column == 15)
                        && (arrBoard[row - 1][column] == 'S' && arrBoard[row + 1][column] == 'S')) {
                    addPoint(player);
                } else if ((arrBoard[row - 1][column] == 'S' && arrBoard[row + 1][column] == 'S')
                        || (arrBoard[row - 1][column - 1] == 'S' && arrBoard[row + 1][column + 1] == 'S')
                        || (arrBoard[row - 1][column + 1] == 'S' && arrBoard[row + 1][column - 1] == 'S')
                        || (arrBoard[row][column - 1] == 'S' && arrBoard[row][column + 1] == 'S')) {
                    addPoint(player);
                }
            }
        } else {
            if ((row == 0 && column == 0) &&
                    ((arrBoard[row + 2][column] == 'S' && arrBoard[row + 1][column] == 'O')
                            || (arrBoard[row][column + 2] == 'S' && arrBoard[row][column + 1] == 'O')
                            || (arrBoard[row + 2][column + 2] == 'S' && arrBoard[row + 1][column + 1] == 'O'))) {
                addPoint(player);
            } else if ((row == 15 && column == 0) &&
                    ((arrBoard[row - 2][column] == 'S' && arrBoard[row - 1][column] == 'O')
                            || (arrBoard[row][column + 2] == 'S' && arrBoard[row][column + 1] == 'O')
                            || (arrBoard[row + 2][column + 2] == 'S' && arrBoard[row + 1][column + 1] == 'O'))) {
                addPoint(player);
            } else if ((row == 0 && column == 15) &&
                    ((arrBoard[row + 2][column] == 'S' && arrBoard[row + 1][column] == 'O')
                            || (arrBoard[row][column - 2] == 'S' && arrBoard[row][column - 1] == 'O')
                            || (arrBoard[row + 2][column - 2] == 'S' && arrBoard[row + 1][column - 1] == 'O'))) {
                addPoint(player);
            } else if ((row == 15 && column == 15) &&
                    ((arrBoard[row - 2][column] == 'S' && arrBoard[row - 1][column] == 'O')
                            || (arrBoard[row][column - 2] == 'S' && arrBoard[row][column - 1] == 'O')
                            || (arrBoard[row - 2][column - 2] == 'S' && arrBoard[row - 1][column - 1] == 'O'))) {
                addPoint(player);
            } else if ((row == 0) &&
                    ((arrBoard[row + 2][column] == 'S' && arrBoard[row + 1][column] == 'O')
                            || (arrBoard[row][column + 2] == 'S' && arrBoard[row][column + 1] == 'O')
                            || (arrBoard[row][column - 2] == 'S' && arrBoard[row][column - 1] == 'O')
                            || (arrBoard[row + 2][column + 2] == 'S' && arrBoard[row + 1][column + 1] == 'O')
                            || (arrBoard[row + 2][column - 2] == 'S' && arrBoard[row + 1][column - 1] == 'O'))) {
                addPoint(player);
            } else if ((row == 15) &&
                    ((arrBoard[row - 2][column] == 'S' && arrBoard[row - 1][column] == 'O')
                            || (arrBoard[row][column + 2] == 'S' && arrBoard[row][column + 1] == 'O')
                            || (arrBoard[row][column - 2] == 'S' && arrBoard[row][column - 1] == 'O')
                            || (arrBoard[row - 2][column + 2] == 'S' && arrBoard[row - 1][column + 1] == 'O')
                            || (arrBoard[row - 2][column - 2] == 'S' && arrBoard[row - 1][column - 1] == 'O'))) {
                addPoint(player);
            } else if ((column == 0) &&
                    ((arrBoard[row + 2][column] == 'S' && arrBoard[row + 1][column] == 'O')
                            || (arrBoard[row - 2][column] == 'S' && arrBoard[row - 1][column] == 'O')
                            || (arrBoard[row][column + 2] == 'S' && arrBoard[row][column + 1] == 'O')
                            || (arrBoard[row + 2][column + 2] == 'S' && arrBoard[row + 1][column + 1] == 'O')
                            || (arrBoard[row - 2][column + 2] == 'S' && arrBoard[row - 1][column + 1] == 'O'))) {
                addPoint(player);
            } else if ((column == 15) &&
                    ((arrBoard[row + 2][column] == 'S' && arrBoard[row + 1][column] == 'O')
                            || (arrBoard[row - 2][column] == 'S' && arrBoard[row - 1][column] == 'O')
                            || (arrBoard[row][column - 2] == 'S' && arrBoard[row][column - 1] == 'O')
                            || (arrBoard[row + 2][column - 2] == 'S' && arrBoard[row + 1][column - 1] == 'O')
                            || (arrBoard[row - 2][column - 2] == 'S' && arrBoard[row - 1][column - 1] == 'O'))) {
                addPoint(player);
            } else if (((row >= 2) && (arrBoard[row - 2][column] == 'S' && arrBoard[row - 1][column] == 'O'))
                    || ((row <= 12) && (arrBoard[row + 2][column] == 'S' && arrBoard[row + 1][column] == 'O'))
                    || ((column >= 2) && (arrBoard[row][column - 2] == 'S' && arrBoard[row][column - 1] == 'O'))
                    || ((column <= 12) && (arrBoard[row][column + 2] == 'S' && arrBoard[row][column + 1] == 'O'))
                    || ((row >= 2 && column <= 12)
                            && (arrBoard[row - 2][column + 2] == 'S' && arrBoard[row - 1][column + 1] == 'O')
                            || ((row <= 12 && column <= 12)
                                    && (arrBoard[row + 2][column + 2] == 'S' && arrBoard[row + 1][column + 1] == 'O'))
                            || ((row >= 2 && column >= 2)
                                    && (arrBoard[row - 2][column - 2] == 'S' && arrBoard[row - 1][column - 1] == 'O'))
                            || ((row <= 12 && column >= 2)
                                    && (arrBoard[row + 2][column - 2] == 'S'
                                            && arrBoard[row + 1][column - 1] == 'O')))) {
                addPoint(player);
            }
        }

        if (currentScoreOfPlayerOne != scoreOfPlayerOne) {
            System.out.print("Player1 scored a point.\nEnter player1's next move\n");
            printPositions();
            printBoard();
        } else if (currentScoreOfPlayerTwo != scoreOfPlayerTwo) {
            System.out.print("Player2 scored a point.\nEnter player2's next move\n");
            printPositions();
            printBoard();
        }

        if ((((scoreOfPlayerOne % 15) == 0) || ((scoreOfPlayerTwo % 15) == 0))
                && (scoreOfPlayerOne != 0 && scoreOfPlayerTwo != 0)) {
            char ch;
            System.out.print(
                    "Do you want to continue playing or exit.\nPress 'Y' to exit any other to continue playing:");
            ch = scan.nextLine().charAt(0);

            if (Character.toLowerCase(ch) == 'y') {
                System.exit(0);
            }
        }

        if (currentScoreOfPlayerOne != scoreOfPlayerOne) {
            getPlayerMove(1);
        } else if (currentScoreOfPlayerTwo != scoreOfPlayerTwo) {
            getPlayerMove(2);
        }
    }

    boolean arrBoardFilled() {
        boolean isarrBoardFilled = true;

        for (int rowCounter = 0; rowCounter < 16; rowCounter++) {
            for (int columnCounter = 0; columnCounter < 16; columnCounter++) {
                if (arrBoard[rowCounter][columnCounter] == '-') {
                    isarrBoardFilled = false;
                }
            }
        }
        return isarrBoardFilled;
    }

    void play() {
        System.out.print("\t\t\t\tRules\n");
        System.out.print("1. Current player gets a point if he place his/her move to make a combination (SOS)\n");
        System.out.print("2. No two players can enter their move in already occupied position.\n\n");
        System.out.print("\t\t\t\tNote.\n");
        System.out.print(
                "1. You can enter the input in any case it will automatically convert the input to the necessary case.\n");
        System.out.print(
                "2. The game prompts you to enter 'Y' to exit after every 15 points to any player to exit and any other character to continue playing.\n\n\n");

        init();
        printPositions();

        while (!arrBoardFilled()) {
            getPlayerMove(1);
            printPositions();
            printBoard();

            getPlayerMove(2);
            printPositions();
            printBoard();
        }
    }
}
