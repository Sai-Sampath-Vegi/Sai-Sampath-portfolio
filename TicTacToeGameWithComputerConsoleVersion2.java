import java.util.Scanner;

public class TicTacToeGameWithComputerConsoleVersion2 {
    public static void main(String[] args) {
        new TicTacToeGameVsComputer().playGame();
    }
}

class TicTacToeGameVsComputer {
    char board[] = new char[9], playerCharacter = 'X', computerCharacter = 'O';
    Scanner scan = new Scanner(System.in);

    void printpositions() {
        System.out.print("...Position...\n");
        for (int counter = 1; counter < 10; counter++) {
            System.out.print(" " + counter + " ");
            if (counter % 3 == 0) {
                System.out.print("\n");
            }
        }
    }

    int findBlockOrWinMove(int position1, int position2) {
        int winChances[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
                { 0, 4, 8 },
                { 2, 4, 6 } };

        for (int counter = 0; counter < 8; counter++) {
            if (((winChances[counter][0] == position1) && (winChances[counter][1] == position2)
                    && (board[winChances[counter][2]] == '\0'))
                    || ((winChances[counter][0] == position2) && (winChances[counter][1] == position1)
                            && (board[winChances[counter][2]] == '\0'))) {
                return winChances[counter][2];
            }

            if (((winChances[counter][1] == position1) && (winChances[counter][2] == position2)
                    && (board[winChances[counter][0]] == '\0'))
                    || ((winChances[counter][1] == position2) && (winChances[counter][2] == position1)
                            && (board[winChances[counter][0]] == '\0'))) {
                return winChances[counter][0];
            }

            if (((winChances[counter][0] == position1) && (winChances[counter][2] == position2)
                    && (board[winChances[counter][1]] == '\0'))
                    || ((winChances[counter][0] == position2) && (winChances[counter][2] == position1)
                            && (board[winChances[counter][1]] == '\0'))) {
                return winChances[counter][1];
            }
        }
        return -1;
    }

    void makeComputerMove() {
        int counter, playerMovesCounter = 0, computerMovesCounter = 0, computerMovesPositions[] = new int[4],
                playerMovesPositions[] = new int[5],
                computerMovePosition = -1,
                remainingPositionsCounter;

        for (counter = 0; counter < 9; counter++) {
            if (board[counter] == computerCharacter) {
                computerMovesPositions[computerMovesCounter++] = counter;
            } else if (board[counter] == playerCharacter) {
                playerMovesPositions[playerMovesCounter++] = counter;
            }
        }

        for (counter = 0; counter < computerMovesCounter; counter++) {
            for (remainingPositionsCounter = counter
                    + 1; remainingPositionsCounter < computerMovesCounter; remainingPositionsCounter++) {
                computerMovePosition = findBlockOrWinMove(computerMovesPositions[counter],
                        computerMovesPositions[remainingPositionsCounter]);
                if (computerMovePosition != -1) {
                    break;
                }
            }

            if (computerMovePosition != -1) {
                break;
            }
        }

        if (computerMovePosition == -1) {
            for (counter = 0; counter < playerMovesCounter; counter++) {
                for (remainingPositionsCounter = counter
                        + 1; remainingPositionsCounter < playerMovesCounter; remainingPositionsCounter++) {
                    computerMovePosition = findBlockOrWinMove(playerMovesPositions[counter],
                            playerMovesPositions[remainingPositionsCounter]);
                    if (computerMovePosition != -1) {
                        break;
                    }
                }

                if (computerMovePosition != -1) {
                    break;
                }
            }
        }

        if (computerMovePosition == -1) {
            int possibleBestMovesOrder[] = { 4, 0, 2, 6, 8, 1, 3, 5, 7 };
            for (counter = 0; counter < 9; counter++) {
                if (board[possibleBestMovesOrder[counter]] == '\0') {
                    computerMovePosition = possibleBestMovesOrder[counter];
                    break;
                }
            }
        }

        if (computerMovePosition != -1) {
            board[computerMovePosition] = computerCharacter;
        }
    }

    void getPlayerMove() {
        char playerMovePosition;

        System.out.print("Enter position for " + playerCharacter + ": ");
        playerMovePosition = scan.next().charAt(0);

        if (playerMovePosition >= '1' && playerMovePosition <= '9') {
            if (board[playerMovePosition - '1'] == '\0') {
                board[playerMovePosition - '1'] = playerCharacter;
            } else {
                System.out.print("Position occupied.\n");
                getPlayerMove();
            }
        } else {
            System.out.print("Invalid position.\n");
            getPlayerMove();
        }
    }

    boolean checkRows(char currentPlayerCharacter) {
        boolean isWinMatched;
        if ((board[0] == currentPlayerCharacter) && (board[1] == currentPlayerCharacter)
                && (board[2] == currentPlayerCharacter)) {
            isWinMatched = true;
        } else if ((board[3] == currentPlayerCharacter) && (board[4] == currentPlayerCharacter)
                && (board[5] == currentPlayerCharacter)) {
            isWinMatched = true;
        } else if ((board[6] == currentPlayerCharacter) && (board[7] == currentPlayerCharacter)
                && (board[8] == currentPlayerCharacter)) {
            isWinMatched = true;
        } else {
            isWinMatched = false;
        }

        return isWinMatched;
    }

    boolean checkColumns(char currentPlayerCharacter) {
        boolean isWinMatched;
        if ((board[0] == currentPlayerCharacter) && (board[3] == currentPlayerCharacter)
                && (board[6] == currentPlayerCharacter)) {
            isWinMatched = true;
        } else if ((board[1] == currentPlayerCharacter) && (board[4] == currentPlayerCharacter)
                && (board[7] == currentPlayerCharacter)) {
            isWinMatched = true;
        } else if ((board[2] == currentPlayerCharacter) && (board[5] == currentPlayerCharacter)
                && (board[8] == currentPlayerCharacter)) {
            isWinMatched = true;
        } else {
            isWinMatched = false;
        }

        return isWinMatched;
    }

    boolean checkDiagnol(char currentPlayerCharacter) {
        boolean isWinMatched;
        if ((board[0] == currentPlayerCharacter) && (board[4] == currentPlayerCharacter)
                && (board[8] == currentPlayerCharacter)) {
            isWinMatched = true;
        } else if ((board[2] == currentPlayerCharacter) && (board[4] == currentPlayerCharacter)
                && (board[6] == currentPlayerCharacter)) {
            isWinMatched = true;
        } else {
            isWinMatched = false;
        }

        return isWinMatched;
    }

    void displayBoard() {
        System.out.println();
        for (int counter = 0; counter < 9; counter++) {
            if (board[counter] == '\0') {
                System.out.print(" - ");
            } else {
                System.out.print(" " + board[counter] + " ");
            }
            if ((counter + 1) % 3 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    boolean checkWin(char currentPlayerCharacter) {
        boolean isWon;
        if ((checkRows(currentPlayerCharacter)) || (checkColumns(currentPlayerCharacter))
                || (checkDiagnol(currentPlayerCharacter))) {
            isWon = true;
        } else {
            isWon = false;
        }

        return isWon;
    }

    void playGame() {
        System.out.print("...TIC TAC TOE...\n");
        System.out.print("...RULES...\n");
        System.out.print("The player cannot place their move in already occupied positions.\n");
        System.out.print("Player should enter the position of their move between 1 and 9.\n");
        System.out.print("Player gets the character (X) and Computer gets the character (O)\n");

        char confirmation;

        int counter;
        do {
            for (counter = 0; counter < 9; counter++) {
                board[counter] = '\0';
            }

            counter = 0;
            endGame: while (counter <= 4) {
                printpositions();
                System.out.print("Your turn\n");
                getPlayerMove();

                boolean isPlayerWon = checkWin(playerCharacter);
                if (isPlayerWon == true) {
                    displayBoard();
                    System.out.print("...YOU WON...\n");
                    break endGame;
                } else if (isPlayerWon == false && counter == 4) {
                    displayBoard();
                    System.out.print("...MATCH IS TIE...\n");
                    break endGame;
                }

                if (counter < 4) {
                    makeComputerMove();
                    displayBoard();

                    boolean isComputerWon = checkWin(computerCharacter);
                    if (isComputerWon == true) {
                        displayBoard();
                        System.out.print("...COMPUTER WON TRY AGAIN...\n");
                        break endGame;
                    } else if (isComputerWon == false && counter++ == 4) {
                        displayBoard();
                        System.out.print("...MATCH IS TIE...\n");
                        break endGame;
                    }
                }
            }

            System.out.print("Do you want to play again press y for yes or any key for no: ");
            confirmation = scan.next().charAt(0);
        } while (confirmation == 'y' || confirmation == 'Y');

        scan.close();
    }
}
