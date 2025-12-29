import java.util.Scanner;

class TicTacToe2Players {
	public static void main(String args[]) {
		TicTacToeGame game = new TicTacToeGame();
		game.play();
	}
}

class TicTacToeGame {
	char board[][] = new char[3][3], playerOneCharacter, playerTwoCharacter;
	Scanner scan = new Scanner(System.in);

	void printPositions() {
		System.out.print("...Position...\n");
		for (int counter = 1; counter < 10; counter++) {
			System.out.print(counter + " ");
			if (counter % 3 == 0)
				System.out.print("\n");
		}
	}

	void player(char playerCharacter) {
		int move, row = -1, column = -1;
		System.out.print("Enter position for player ");
		if (playerCharacter == playerOneCharacter)
			System.out.print(1 + "(" + playerCharacter + "):");
		else
			System.out.print(2 + "(" + playerCharacter + "):");
		move = scan.nextInt();
		if (move >= 1 && move <= 9) {
			switch (move) {
				case 1:
					row = column = 0;
					break;
				case 2:
					row = 0;
					column = 1;
					break;
				case 3:
					row = 0;
					column = 2;
					break;
				case 4:
					row = 1;
					column = 0;
					break;
				case 5:
					row = column = 1;
					break;
				case 6:
					row = 1;
					column = 2;
					break;
				case 7:
					row = 2;
					column = 0;
					break;
				case 8:
					row = 2;
					column = 1;
					break;
				case 9:
					row = column = 2;
					break;
			}
			if (board[row][column] == '\0')
				board[row][column] = playerCharacter;
		} else {
			System.out.print("Invalid position\n");
			player(playerCharacter);
		}
	}

	boolean rowCheck(char currentMove) {
		boolean isMatched;
		if ((board[0][0] == currentMove) && (board[0][1] == currentMove) && (board[0][2] == currentMove))
			isMatched = true;
		else if ((board[1][0] == currentMove) && (board[1][1] == currentMove) && (board[1][2] == currentMove))
			isMatched = true;
		else if ((board[2][0] == currentMove) && (board[2][1] == currentMove) && (board[2][2] == currentMove))
			isMatched = true;
		else
			isMatched = false;
		return isMatched;
	}

	boolean columnCheck(char currentMove) {
		boolean isMatched;
		if ((board[0][0] == currentMove) && (board[1][0] == currentMove) && (board[2][0] == currentMove))
			isMatched = true;
		else if ((board[0][1] == currentMove) && (board[1][1] == currentMove) && (board[2][1] == currentMove))
			isMatched = true;
		else if ((board[0][2] == currentMove) && (board[1][2] == currentMove) && (board[2][2] == currentMove))
			isMatched = true;
		else
			isMatched = false;
		return isMatched;
	}

	boolean diagnolCheck(char currentMove) {
		boolean isMatched;
		if ((board[0][0] == currentMove) && (board[1][1] == currentMove) && (board[2][2] == currentMove))
			isMatched = true;
		else if ((board[0][2] == currentMove) && (board[1][1] == currentMove) && (board[2][0] == currentMove))
			isMatched = true;
		else
			isMatched = false;
		return isMatched;
	}

	void display() {
		int rowCounter, columnCounter;
		for (rowCounter = 0; rowCounter < 3; rowCounter++) {
			for (columnCounter = 0; columnCounter < 3; columnCounter++) {
				if (board[rowCounter][columnCounter] == '\0')
					System.out.print("- ");
				else
					System.out.print(board[rowCounter][columnCounter] + " ");
			}
			System.out.print("\n");
		}
	}

	boolean check(char currentMove) {
		if ((rowCheck(currentMove)) || (columnCheck(currentMove)) || (diagnolCheck(currentMove)))
			return true;
		else
			return false;
	}

	void play() {
		char playerChoice;
		int rowCounter, columnCounter;
		boolean isPlayerWon = false;
		do {
			System.out.print("...TIC TAC TOE...\n");
			for (rowCounter = 0; rowCounter < 3; rowCounter++)
				for (columnCounter = 0; columnCounter < 3; columnCounter++)
					board[rowCounter][columnCounter] = '\0';
			System.out.print("...RULES...\n");
			System.out.print("1:The player cannot place their option in already existed positions\n");
			System.out.print("2:Each player gets their own option to play\n");
			System.out.print("3:Each player should enter the position of the next move between 1 and 9\n");
			System.out.print("4:The following are the positions\n");
			printPositions();
			enter: while (true) {
				System.out.print("Enter the option of player 1:");
				playerOneCharacter = scan.next().charAt(0);
				System.out.print("Enter the option of player 2:");
				playerTwoCharacter = scan.next().charAt(0);
				if (playerOneCharacter == playerTwoCharacter) {
					System.out.print("Both players choosed same options\n");
					continue enter;
				}
				break;
			}
			rowCounter = 0;
			end: while (rowCounter <= 4) {
				System.out.print("Player 1's turn\n");
				player(playerOneCharacter);
				display();
				isPlayerWon = check(playerOneCharacter);
				if (isPlayerWon) {
					System.out.print("...PLAYER 1 WON...\n");
					break end;
				} else if ((isPlayerWon == false) && (rowCounter == 4)) {
					System.out.print("...MATCH IS TIE...\n");
					break end;
				}
				if (rowCounter < 4) {
					System.out.print("Player 2's turn\n");
					player(playerTwoCharacter);
					display();
					isPlayerWon = check(playerTwoCharacter);
					if (isPlayerWon) {
						System.out.print("...PLAYER 2 WON...\n");
						break end;
					} else if ((isPlayerWon == false) && (rowCounter++ == 4)) {
						System.out.print("...MATCH IS TIE...\n");
						break end;
					}
				}
			}
			System.out.print("Do you want to play again press y for yes any key for no:");
			playerChoice = scan.next().charAt(0);
		} while (playerChoice == 'y' || playerChoice == 'Y');
		scan.close();
	}
}
