#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdbool.h>

void getPlayerMove();
bool checkWin(char);
bool rowCheck(char);
bool columnCheck(char);
bool diagnolCheck(char);
void displayBoard();
void printpositions();
void makeComputerMove();
int findWinningComputerMove(int, int);

char arrBoard[9], playerCharacter = 'X', computerCharacter = 'O';

int main()
{
	printf("...TIC TAC TOE...\n");
	printf("...RULES...\n");
	printf("The player cannot place their option in already occupied positions.\n");
	printf("Player should enter the position of his/her move between 1 and 9\n");
	printf("Player gets the option (X) and Computer gets the option (O)\n");
	printf("The following are the positions\n");
	printpositions();

	char ch;
	int counter, playerMovesCounter;

	bool isPlayerWon;
	do
	{
		for (counter = 0; counter < 9; counter++)
		{
			arrBoard[counter] = '\0';
		}
		counter = 0;
		while (counter <= 4)
		{
			printf("Your turn\n");
			getPlayerMove();

			bool isPlayerWon = checkWin(playerCharacter);
			if (isPlayerWon)
			{
				displayBoard();
				printf("...YOU WON...\n");
			}
			else if (isPlayerWon == 0 && counter == 4)
			{
				displayBoard();
				printf("...MATCH IS TIE...\n");
			}

			if (counter < 4)
			{
				makeComputerMove();
				displayBoard();

				bool isComputerWon = checkWin(computerCharacter);
				if (isComputerWon)
				{
					displayBoard();
					printf("...COMPUTER WON TRY AGAIN...\n");
				}
				else if (isComputerWon == 0 && counter++ == 4)
				{
					displayBoard();
					printf("...MATCH IS TIE...\n");
				}
			}
		}

		printf("Do you want to play again press y for yes any key for no: ");
		scanf(" %c", &ch);
	} while (ch == 'y' || ch == 'Y');
}

void printpositions()
{
	printf("...Position...\n");
	for (int counter = 1; counter < 10; counter++)
	{
		printf("%d ", counter);
		if (counter % 3 == 0)
		{
			printf("\n");
		}
	}
}

int findWinningComputerMove(int playerOneCharacter, int playerTwoCharacter)
{
	int winChances[][3] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}, counter;
	for (counter = 0; counter < 8; counter++)
	{
		if (((winChances[counter][0] == playerOneCharacter) && (winChances[counter][1] == playerTwoCharacter) && (arrBoard[winChances[counter][2]] == '\0')) || ((winChances[counter][0] == playerTwoCharacter) && (winChances[counter][1] == playerOneCharacter) && (arrBoard[winChances[counter][2]] == '\0')))
		{
			return winChances[counter][2];
		}

		if (((winChances[counter][1] == playerOneCharacter) && (winChances[counter][2] == playerTwoCharacter) && (arrBoard[winChances[counter][0]] == '\0')) || ((winChances[counter][1] == playerTwoCharacter) && (winChances[counter][2] == playerOneCharacter) && (arrBoard[winChances[counter][0]] == '\0')))
		{
			return winChances[counter][0];
		}

		if (((winChances[counter][0] == playerOneCharacter) && (winChances[counter][2] == playerTwoCharacter) && (arrBoard[winChances[counter][1]] == '\0')) || ((winChances[counter][0] == playerTwoCharacter) && (winChances[counter][2] == playerOneCharacter) && (arrBoard[winChances[counter][1]] == '\0')))
		{
			return winChances[counter][1];
		}
	}
	return -1;
}

void makeComputerMove()
{
	int counter, playerMovesCounter = 0, computerMovesCounter = 0, computerMoves[4], playerMoves[5], computerMove = -1;
	bool flag = false;
	for (counter = 0; counter < 9; counter++)
	{
		if (arrBoard[counter] == computerCharacter)
		{
			computerMoves[computerMovesCounter++] = counter;
		}
		else if (arrBoard[counter] == playerCharacter)
		{
			playerMoves[playerMovesCounter++] = counter;
		}
	}

	if (computerMovesCounter == 2)
	{
		computerMove = findWinningComputerMove(computerMoves[0], computerMoves[1]);
	}
	else if (computerMovesCounter == 3)
	{
		computerMove = findWinningComputerMove(computerMoves[0], computerMoves[1]);
		if (computerMove == -1)
		{
			computerMove = findWinningComputerMove(computerMoves[0], computerMoves[2]);
			if (computerMove == -1)
			{
				computerMove = findWinningComputerMove(computerMoves[1], computerMoves[2]);
			}
		}
	}
	else if (computerMovesCounter == 4)
	{
		computerMove = findWinningComputerMove(computerMoves[0], computerMoves[1]);
		if (computerMove == -1)
		{
			computerMove = findWinningComputerMove(computerMoves[0], computerMoves[2]);
			if (computerMove == -1)
			{
				computerMove = findWinningComputerMove(computerMoves[0], computerMoves[3]);
				if (computerMove == -1)
				{
					computerMove = findWinningComputerMove(computerMoves[1], computerMoves[2]);
					if (computerMove == -1)
					{
						computerMove = findWinningComputerMove(computerMoves[1], computerMoves[3]);
						if (computerMove == -1)
						{
							computerMove = findWinningComputerMove(computerMoves[2], computerMoves[3]);
						}
					}
				}
			}
		}
	}

	if (computerMove == -1)
	{
		if (playerMovesCounter == 2)
		{
			computerMove = findWinningComputerMove(playerMoves[0], playerMoves[1]);
		}
		else if (playerMovesCounter == 3)
		{
			computerMove = findWinningComputerMove(playerMoves[0], playerMoves[1]);
			if (computerMove == -1)
			{
				computerMove = findWinningComputerMove(playerMoves[0], playerMoves[2]);
				if (computerMove == -1)
				{
					computerMove = findWinningComputerMove(playerMoves[1], playerMoves[2]);
				}
			}
		}
		else if (playerMovesCounter == 4)
		{
			computerMove = findWinningComputerMove(playerMoves[0], playerMoves[1]);
			if (computerMove == -1)
			{
				computerMove = findWinningComputerMove(playerMoves[0], playerMoves[2]);
				if (computerMove == -1)
				{
					computerMove = findWinningComputerMove(playerMoves[0], playerMoves[3]);
					if (computerMove == -1)
					{
						computerMove = findWinningComputerMove(playerMoves[1], playerMoves[2]);
						if (computerMove == -1)
						{
							computerMove = findWinningComputerMove(playerMoves[1], playerMoves[3]);
							if (computerMove == -1)
							{
								computerMove = findWinningComputerMove(playerMoves[2], playerMoves[3]);
							}
						}
					}
				}
			}
		}
	}

	if (computerMove == -1)
	{
		for (counter = 4; counter < 9; counter += 2)
		{
			if (arrBoard[counter] == '\0')
			{
				flag = true;
				computerMove = counter;
				break;
			}
		}

		if (flag == false)
		{
			for (counter = 2; counter >= 0; counter -= 2)
			{
				if (arrBoard[counter] == '\0')
				{
					flag = true;
					computerMove = counter;
					break;
				}
			}
		}

		if (flag == false)
		{
			for (counter = 4; counter < 9; counter++)
			{
				if (arrBoard[counter] == '\0')
				{
					flag = true;
					computerMove = counter;
					break;
				}
			}
		}

		if (flag == false)
		{
			for (counter = 3; counter >= 0; counter--)
			{
				if (arrBoard[counter] == '\0')
				{
					computerMove = counter;
					break;
				}
			}
		}
	}

	arrBoard[computerMove] = computerCharacter;
}

void getPlayerMove()
{
	char position;
	printf("Enter position for (%c): ", playerCharacter);
	scanf(" %c", &position);

	if (position >= '1' && position <= '9')
	{
		if (arrBoard[position - '1'] == '\0')
		{
			arrBoard[position - '1'] = playerCharacter;
		}
		else
		{
			printf("Position occupied\n");
			getPlayerMove();
		}
	}
	else
	{
		printf("Invalid position\n");
		getPlayerMove();
	}
}

bool rowCheck(char currentPlayerCharacter)
{
	bool isCurrentPlayerWon = false;
	if (((arrBoard[0] == currentPlayerCharacter) && (arrBoard[1] == currentPlayerCharacter) && (arrBoard[2] == currentPlayerCharacter)) || ((arrBoard[3] == currentPlayerCharacter) && (arrBoard[4] == currentPlayerCharacter) && (arrBoard[5] == currentPlayerCharacter)) || ((arrBoard[6] == currentPlayerCharacter) && (arrBoard[7] == currentPlayerCharacter) && (arrBoard[8] == currentPlayerCharacter)))
	{
		isCurrentPlayerWon = true;
	}

	return isCurrentPlayerWon;
}

bool columnCheck(char currentPlayerCharacter)
{
	bool isCurrentPlayerWon = false;
	if (((arrBoard[0] == currentPlayerCharacter) && (arrBoard[3] == currentPlayerCharacter) && (arrBoard[6] == currentPlayerCharacter)) || ((arrBoard[1] == currentPlayerCharacter) && (arrBoard[4] == currentPlayerCharacter) && (arrBoard[7] == currentPlayerCharacter)) || ((arrBoard[2] == currentPlayerCharacter) && (arrBoard[5] == currentPlayerCharacter) && (arrBoard[8] == currentPlayerCharacter)))
	{
		isCurrentPlayerWon = true;
	}

	return isCurrentPlayerWon;
}

bool diagnolCheck(char currentPlayerCharacter)
{
	bool isCurrentPlayerWon = false;
	if (((arrBoard[0] == currentPlayerCharacter) && (arrBoard[4] == currentPlayerCharacter) && (arrBoard[8] == currentPlayerCharacter)) || ((arrBoard[2] == currentPlayerCharacter) && (arrBoard[4] == currentPlayerCharacter) && (arrBoard[6] == currentPlayerCharacter)))
	{
		isCurrentPlayerWon = true;
	}

	return isCurrentPlayerWon;
}

bool checkWin(char currentPlayerCharacter)
{
	bool isCurrentPlayerWon = false;
	if (rowCheck(currentPlayerCharacter) || columnCheck(currentPlayerCharacter) || diagnolCheck(currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}

	return isCurrentPlayerWon;
}

void displayBoard()
{
	int counter;
	printf("\n");
	for (counter = 0; counter < 9; counter++)
	{
		if (arrBoard[counter] == '\0')
		{
			printf(" - ");
		}
		else
		{
			printf(" %c ", arrBoard[counter]);
		}

		if ((counter + 1) % 3 == 0)
		{
			printf("\n");
		}
	}
	printf("\n");
}
