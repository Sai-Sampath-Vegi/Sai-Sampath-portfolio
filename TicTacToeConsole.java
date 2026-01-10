import java.util.Scanner;

class TicTacToeConsole {
	public static void main(String args[]) {
		new TicTacToeGame().play();
	}
}

class TicTacToeGame {
	char board[][] = new char[3][3], playerOneCharacter, playerTwoCharacter;
	Scanner scan = new Scanner(System.in);

	void printPositions() {
		System.out.print("...Position...\n");
		for (int counter = 1; counter < 10; counter++) {
			System.out.print(counter + " ");
			if (counter % 3 == 0) {
				System.out.println();
			}
		}
	}

	void displayBoard() {
		int rowCounter, columnCounter;

		for (rowCounter = 0; rowCounter < 3; rowCounter++) {
			for (columnCounter = 0; columnCounter < 3; columnCounter++) {
				if (board[rowCounter][columnCounter] == '\0') {
					System.out.print("- ");
				} else {
					System.out.print(board[rowCounter][columnCounter] + " ");
				}
			}
			System.out.println();
		}
	}

	boolean rowCheck(char currentMove) {
		boolean isCurrentPlayerWon;
		if ((board[0][0] == currentMove) && (board[0][1] == currentMove) && (board[0][2] == currentMove)) {
			isCurrentPlayerWon = true;
		} else if ((board[1][0] == currentMove) && (board[1][1] == currentMove) && (board[1][2] == currentMove)) {
			isCurrentPlayerWon = true;
		} else if ((board[2][0] == currentMove) && (board[2][1] == currentMove) && (board[2][2] == currentMove)) {
			isCurrentPlayerWon = true;
		} else {
			isCurrentPlayerWon = false;
		}
		return isCurrentPlayerWon;
	}

	boolean columnCheck(char currentMove) {
		boolean isCurrentPlayerWon;
		if ((board[0][0] == currentMove) && (board[1][0] == currentMove) && (board[2][0] == currentMove)) {
			isCurrentPlayerWon = true;
		} else if ((board[0][1] == currentMove) && (board[1][1] == currentMove) && (board[2][1] == currentMove)) {
			isCurrentPlayerWon = true;
		} else if ((board[0][2] == currentMove) && (board[1][2] == currentMove) && (board[2][2] == currentMove)) {
			isCurrentPlayerWon = true;
		} else {
			isCurrentPlayerWon = false;
		}
		return isCurrentPlayerWon;
	}

	boolean diagnolCheck(char currentMove) {
		boolean isCurrentPlayerWon;
		if ((board[0][0] == currentMove) && (board[1][1] == currentMove) && (board[2][2] == currentMove)) {
			isCurrentPlayerWon = true;
		} else if ((board[0][2] == currentMove) && (board[1][1] == currentMove) && (board[2][0] == currentMove)) {
			isCurrentPlayerWon = true;
		} else {
			isCurrentPlayerWon = false;
		}
		return isCurrentPlayerWon;
	}

	boolean checkWin(char currentMove) {
		boolean isCurrentPlayerWon;
		if ((rowCheck(currentMove)) || (columnCheck(currentMove)) || (diagnolCheck(currentMove))) {
			isCurrentPlayerWon = true;
		} else {
			isCurrentPlayerWon = false;
		}
		return isCurrentPlayerWon;
	}

	void getPlayerMove(char playerCharacter) {
		int row = -1, column = -1;
		char move;
		System.out.print("Enter position for player " + ((playerCharacter == playerOneCharacter) ? 1
				: 2) + "(" + playerCharacter + "): ");
		move = scan.next().charAt(0);

		if (move >= '1' && move <= '9') {
			switch (move) {
				case '1':
					row = column = 0;
					break;
				case '2':
					row = 0;
					column = 1;
					break;
				case '3':
					row = 0;
					column = 2;
					break;
				case '4':
					row = 1;
					column = 0;
					break;
				case '5':
					row = column = 1;
					break;
				case '6':
					row = 1;
					column = 2;
					break;
				case '7':
					row = 2;
					column = 0;
					break;
				case '8':
					row = 2;
					column = 1;
					break;
				case '9':
					row = column = 2;
					break;
			}

			if (board[row][column] == '\0') {
				board[row][column] = playerCharacter;
			}
		} else {
			System.out.print("Invalid position\n");
			getPlayerMove(playerCharacter);
		}
	}

	void play() {
		char playerChoice;
		int rowCounter, columnCounter;

		boolean isPlayerWon = false;
		do {
			System.out.print("...TIC TAC TOE...\n");
			for (rowCounter = 0; rowCounter < 3; rowCounter++) {
				for (columnCounter = 0; columnCounter < 3; columnCounter++) {
					board[rowCounter][columnCounter] = '\0';
				}
			}

			System.out.print("...RULES...\n");
			System.out.print("1:The player cannot place their option in already existed positions.\n");
			System.out.print("2:Each player gets their own option to play.\n");
			System.out.print("3:Each player should enter the position of the next move between 1 and 9\n");
			System.out.print("4:The following are the positions.\n");
			printPositions();

			enterCharacterAgain: while (true) {
				System.out.print("Enter the option of player 1:");
				playerOneCharacter = scan.next().charAt(0);
				System.out.print("Enter the option of player 2:");
				playerTwoCharacter = scan.next().charAt(0);
				if (playerOneCharacter == playerTwoCharacter) {
					System.out.print("Both players choosed same options\n");
					continue enterCharacterAgain;
				}
				break;
			}

			rowCounter = 0;

			endGame: while (rowCounter <= 4) {
				printPositions();
				System.out.print("Player 1's turn\n");
				getPlayerMove(playerOneCharacter);
				isPlayerWon = checkWin(playerOneCharacter);

				if (isPlayerWon) {
					System.out.print("...PLAYER 1 WON...\n");
					break endGame;
				} else if ((isPlayerWon == false) && (rowCounter == 4)) {
					System.out.print("...MATCH IS TIE...\n");
					break endGame;
				}

				displayBoard();

				if (rowCounter < 4) {
					printPositions();
					System.out.print("Player 2's turn\n");
					getPlayerMove(playerTwoCharacter);
					isPlayerWon = checkWin(playerTwoCharacter);

					if (isPlayerWon) {
						System.out.print("...PLAYER 2 WON...\n");
						break endGame;
					} else if ((isPlayerWon == false) && (rowCounter++ == 4)) {
						System.out.print("...MATCH IS TIE...\n");
						break endGame;
					}
				}

				displayBoard();
			}

			System.out.print("Do you want to play again press y for yes any key for no:");
			playerChoice = scan.next().charAt(0);
		} while (playerChoice == 'y' || playerChoice == 'Y');

		scan.close();
	}
}
