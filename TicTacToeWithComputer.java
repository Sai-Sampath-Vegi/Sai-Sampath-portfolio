import java.util.Scanner;

public class TicTacToeWithComputer {
	public static void main(String args[]) {
		TicTacToeGame game = new TicTacToeGame();
		game.play();
	}
}

class TicTacToeGame {
	char board[] = new char[9], playerCharacter = 'X', computerCharacter = 'O';
	Scanner scan = new Scanner(System.in);

	void printPositions() {
		System.out.print("...Position...\n");
		for (int i = 1; i < 10; i++) {
			System.out.print(i + " ");
			if (i % 3 == 0)
				System.out.print("\n");
		}
	}

	int match(int computerMove1, int computerMove2) {
		int winChances[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
				{ 0, 4, 8 }, { 2, 4, 6 } };
		int counter;
		for (counter = 0; counter < 8; counter++) {
			if (((winChances[counter][0] == computerMove1) && (winChances[counter][1] == computerMove2)
					&& (board[winChances[counter][2]] == '\0'))
					|| ((winChances[counter][0] == computerMove2) && (winChances[counter][1] == computerMove1)
							&& (board[winChances[counter][2]] == '\0')))
				return winChances[counter][2];
			if (((winChances[counter][1] == computerMove1) && (winChances[counter][2] == computerMove2)
					&& (board[winChances[counter][0]] == '\0'))
					|| ((winChances[counter][1] == computerMove2) && (winChances[counter][2] == computerMove1)
							&& (board[winChances[counter][0]] == '\0')))
				return winChances[counter][0];
			if (((winChances[counter][0] == computerMove1) && (winChances[counter][2] == computerMove2)
					&& (board[winChances[counter][1]] == '\0'))
					|| ((winChances[counter][0] == computerMove2) && (winChances[counter][2] == computerMove1)
							&& (board[winChances[counter][1]] == '\0')))
				return winChances[counter][1];
		}
		return -1;
	}

	void computerMove() {
		int counter, playerMovesCounter = 0, computerMovesCounter = 0, computerMoves[] = new int[4],
				playerMoves[] = new int[5], computerMove = -1, flag = 0;
		for (counter = 0; counter < 9; counter++) {
			if (board[counter] == computerCharacter)
				computerMoves[computerMovesCounter++] = counter;
			else if (board[counter] == playerCharacter)
				playerMoves[playerMovesCounter++] = counter;
		}
		if (computerMovesCounter == 2)
			computerMove = match(computerMoves[0], computerMoves[1]);
		else if (computerMovesCounter == 3) {
			computerMove = match(computerMoves[0], computerMoves[1]);
			if (computerMove == -1) {
				computerMove = match(computerMoves[0], computerMoves[2]);
				if (computerMove == -1)
					computerMove = match(computerMoves[1], computerMoves[2]);
			}
		} else if (computerMovesCounter == 4) {
			computerMove = match(computerMoves[0], computerMoves[1]);
			if (computerMove == -1) {
				computerMove = match(computerMoves[0], computerMoves[2]);
				if (computerMove == -1) {
					computerMove = match(computerMoves[0], computerMoves[3]);
					if (computerMove == -1) {
						computerMove = match(computerMoves[1], computerMoves[2]);
						if (computerMove == -1) {
							computerMove = match(computerMoves[1], computerMoves[3]);
							if (computerMove == -1)
								computerMove = match(computerMoves[2], computerMoves[3]);
						}
					}
				}
			}
		}
		if (computerMove == -1) {
			if (playerMovesCounter == 2)
				computerMove = match(playerMoves[0], playerMoves[1]);
			else if (playerMovesCounter == 3) {
				computerMove = match(playerMoves[0], playerMoves[1]);
				if (computerMove == -1) {
					computerMove = match(playerMoves[0], playerMoves[2]);
					if (computerMove == -1)
						computerMove = match(playerMoves[1], playerMoves[2]);
				}
			} else if (playerMovesCounter == 4) {
				computerMove = match(playerMoves[0], playerMoves[1]);
				if (computerMove == -1) {
					computerMove = match(playerMoves[0], playerMoves[2]);
					if (computerMove == -1) {
						computerMove = match(playerMoves[0], playerMoves[3]);
						if (computerMove == -1) {
							computerMove = match(playerMoves[1], playerMoves[2]);
							if (computerMove == -1) {
								computerMove = match(playerMoves[1], playerMoves[3]);
								if (computerMove == -1)
									computerMove = match(playerMoves[2], playerMoves[3]);
							}
						}
					}
				}
			}
		}
		if (computerMove == -1) {
			for (counter = 4; counter < 9; counter += 2)
				if (board[counter] == '\0') {
					flag = 1;
					computerMove = counter;
					break;
				}
			if (flag == 0)
				for (counter = 2; counter >= 0; counter -= 2)
					if (board[counter] == '\0') {
						flag = 1;
						computerMove = counter;
						break;
					}
			if (flag == 0)
				for (counter = 4; counter < 9; counter++)
					if (board[counter] == '\0') {
						flag = 1;
						computerMove = counter;
						break;
					}
			if (flag == 0)
				for (counter = 3; counter >= 0; counter--)
					if (board[counter] == '\0') {
						computerMove = counter;
						break;
					}
		}
		board[computerMove] = computerCharacter;
	}

	void player() {
		int playerMove;
		System.out.print("Enter position for " + playerCharacter + ": ");
		playerMove = scan.nextInt();
		if (playerMove >= 1 && playerMove <= 9) {
			if (board[playerMove - 1] == '\0')
				board[playerMove - 1] = playerCharacter;
			else {
				System.out.print("Position occupied\n");
				player();
			}
		} else {
			System.out.print("Invalid position\n");
			player();
		}
	}

	boolean rowCheck(char currentMove) {
		boolean isMatched;
		if ((board[0] == currentMove) && (board[1] == currentMove) && (board[2] == currentMove))
			isMatched = true;
		else if ((board[3] == currentMove) && (board[4] == currentMove) && (board[5] == currentMove))
			isMatched = true;
		else if ((board[6] == currentMove) && (board[7] == currentMove) && (board[8] == currentMove))
			isMatched = true;
		else
			isMatched = false;
		return isMatched;
	}

	boolean columnCheck(char currentMove) {
		boolean isMatched;
		if ((board[0] == currentMove) && (board[3] == currentMove) && (board[6] == currentMove))
			isMatched = true;
		else if ((board[1] == currentMove) && (board[4] == currentMove) && (board[7] == currentMove))
			isMatched = true;
		else if ((board[2] == currentMove) && (board[5] == currentMove) && (board[8] == currentMove))
			isMatched = true;
		else
			isMatched = false;
		return isMatched;
	}

	boolean diagnolCheck(char currentMove) {
		boolean isMatched;
		if ((board[0] == currentMove) && (board[4] == currentMove) && (board[8] == currentMove))
			isMatched = true;
		else if ((board[2] == currentMove) && (board[4] == currentMove) && (board[6] == currentMove))
			isMatched = true;
		else
			isMatched = false;
		return isMatched;
	}

	void display() {
		System.out.print("\n");
		for (int counter = 1; counter < 10; counter++) {
			if (board[counter - 1] == '\0')
				System.out.print(" - ");
			else
				System.out.print(" " + board[counter - 1] + " ");
			if (counter % 3 == 0)
				System.out.print("\n");
		}
		System.out.print("\n");
	}

	boolean check(char currentMove) {
		if ((rowCheck(currentMove)) || (columnCheck(currentMove)) || (diagnolCheck(currentMove)))
			return true;
		else
			return false;
	}

	void play() {
		char userChoice;
		int moveCounter;
		boolean isPlayerOneWon = false;
		System.out.print("...TIC TAC TOE...\n");
		System.out.print("...RULES...\n");
		System.out.print("The player cannot place their option in already occupied positions\n");
		System.out.print("Player should enter the position of his/her move between 1 and 9\n");
		System.out.print("Player gets the option (X) and Computer gets the option (O)\n");
		System.out.print("The following are the positions\n");
		printPositions();
		do {
			for (int counter = 0; counter < 9; counter++)
				board[counter] = '\0';
			moveCounter = 0;
			end: while (moveCounter <= 4) {
				System.out.print("Your turn\n");
				player();
				isPlayerOneWon = check(playerCharacter);
				if (isPlayerOneWon) {
					display();
					System.out.print("...YOU WON...\n");
					break end;
				} else if ((isPlayerOneWon == false) && (moveCounter == 4)) {
					display();
					System.out.print("...MATCH IS TIE...\n");
					break end;
				}
				if (moveCounter < 4) {
					computerMove();
					display();
					isPlayerOneWon = check(computerCharacter);
					if (isPlayerOneWon) {
						display();
						System.out.print("...COMPUTER WON TRY AGAIN...\n");
						break end;
					} else if ((isPlayerOneWon == false) && (moveCounter++ == 4)) {
						display();
						System.out.print("...MATCH IS TIE...\n");
						break end;
					}
				}
			}
			System.out.print("Do you want to play again press y for yes any key for no:");
			userChoice = scan.next().charAt(0);
		} while (userChoice == 'y' || userChoice == 'Y');
		scan.close();
	}
}
