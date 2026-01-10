#include <stdio.h>
#include <stdlib.h>
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

	char confirmation;
	int counter, playerMovesCounter;

	do
	{
		for (counter = 0; counter < 9; counter++)
		{
			arrBoard[counter] = '\0';
		}

		counter = 0;
		while (counter <= 4)
		{
			printpositions();
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

		printf("Do you want to play again press y for yes any key for no:");
		scanf(" %c", &confirmation);
	} while (confirmation == 'y' || confirmation == 'Y');
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
	int counter, playerMovesCounter = 0, computerMovesCounter = 0, computerMoves[4], playerMoves[5], computerMove = -1, remainingPositionsCounter;

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

	for (counter = 0; counter < computerMovesCounter; counter++)
	{
		for (remainingPositionsCounter = counter + 1; remainingPositionsCounter < computerMovesCounter; remainingPositionsCounter++)
		{
			computerMove = findWinningComputerMove(computerMoves[counter], computerMoves[remainingPositionsCounter]);
			if (computerMove != -1)
			{
				break;
			}
		}

		if (computerMove != -1)
		{
			break;
		}
	}
	if (computerMove == -1)
	{
		for (counter = 0; counter < playerMovesCounter; counter++)
		{
			for (remainingPositionsCounter = counter + 1; remainingPositionsCounter < playerMovesCounter; remainingPositionsCounter++)
			{
				computerMove = findWinningComputerMove(playerMoves[counter], playerMoves[remainingPositionsCounter]);
				if (computerMove != -1)
				{
					break;
				}
			}

			if (computerMove != -1)
			{
				break;
			}
		}
	}

	if (computerMove == -1)
	{
		int possibleBestMovesOrder[] = {4, 0, 2, 6, 8, 1, 3, 5, 7};

		for (counter = 0; counter < 9; counter++)
		{
			if (arrBoard[possibleBestMovesOrder[counter]] == '\0')
			{
				computerMove = possibleBestMovesOrder[counter];
				break;
			}
		}
	}

	if (computerMove != -1)
	{
		arrBoard[computerMove] = computerCharacter;
	}
}

void getPlayerMove()
{
	char playerMove;
	printf("Enter position for (%c): ", playerCharacter);
	scanf(" %c", &playerMove);

	if (playerMove >= '1' && playerMove <= '9')
	{
		if (arrBoard[playerMove - '1'] == '\0')
		{
			arrBoard[playerMove - '1'] = playerCharacter;
		}
		else
		{
			printf("Position occupied.\n");
			getPlayerMove();
		}
	}
	else
	{
		printf("Invalid position.\n");
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
	printf("\n");
	for (int counter = 0; counter < 9; counter++)
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
