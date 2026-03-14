#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void getPlayerMove(char);
bool checkMatch(char);
bool rowCheck(char);
bool columnCheck(char);
bool diagnolCheck(char);
void displayBoard();
void printPositions();

char arrBoard[3][3], playerOneCharacter, playerTwoCharacter;

int main()
{
	char CLEAR_COMMAND[6];

#if defined(_WIN32) || defined(_WIN64)
	strcpy(CLEAR_COMMAND, "cls");
#elif defined(__APPLE__) || defined(__linux__)
	strcpy(CLEAR_COMMAND, "clear");
#else
	strcpy(CLEAR_COMMAND, "");
#endif

	char userChoice;
	do
	{
		for (int rowsCounter = 0; rowsCounter < 3; rowsCounter++)
		{
			for (int columnsCounter = 0; columnsCounter < 3; columnsCounter++)
			{
				arrBoard[rowsCounter][columnsCounter] = '\0';
			}
		}

		system(CLEAR_COMMAND);

		printf("\n...TIC TAC TOE...\n");
		printf("\n...RULES...\n");
		printf("1:The player cannot place their option in already existed positions.\n");
		printf("2:Each player gets their own option to play.\n");
		printf("3:Each player should enter the position of the next move between 1 and 9\n");
		printf("4:The following are the positions\n");
		printPositions();

		bool isValidChoisesChosen;
		bool isPlayerChoicesChoosen = false;
		if (!isPlayerChoicesChoosen)
		{
			do
			{
				isValidChoisesChosen = false;
				printf("Enter the option of player 1:");
				scanf(" %c", &playerOneCharacter);
				printf("Enter the option of player 2:");
				scanf(" %c", &playerTwoCharacter);

				if (playerOneCharacter == playerTwoCharacter)
				{
					printf("Both players choosed same options\n");
				}
				else
				{
					isValidChoisesChosen = true;
				}
			} while (!isValidChoisesChosen);
			isPlayerChoicesChoosen = true;
		}

		int movesCounter = 0;
		while (movesCounter <= 4)
		{
			printf("\nPlayer 1's turn\n");
			getPlayerMove(playerOneCharacter);

			bool isCurrentPlayerWon = checkMatch(playerOneCharacter);
			if (isCurrentPlayerWon)
			{
				displayBoard();
				printf("\n...PLAYER 1 WON...\n\n");
				break;
			}
			else if (isCurrentPlayerWon == false && movesCounter == 4)
			{
				displayBoard();
				printf("\n...MATCH IS TIE...\n\n");
				break;
			}

			if (movesCounter < 4)
			{
				printf("\nPlayer 2's turn\n\n");
				getPlayerMove(playerTwoCharacter);
				isCurrentPlayerWon = checkMatch(playerTwoCharacter);

				if (isCurrentPlayerWon)
				{
					displayBoard();
					printf("\n...PLAYER 2 WON...\n\n");
					break;
				}
				else if (isCurrentPlayerWon == 0 && movesCounter++ == 4)
				{
					displayBoard();
					printf("\n...MATCH IS TIE...\n\n");
					break;
				}
			}
		}

		printf("\nDo you want to play again press y for Yes any key for No:");
		scanf(" %c", &userChoice);
	} while (userChoice == 'y' || userChoice == 'Y');
	return 0;
}

void printPositions()
{
	printf("\n...Position...\n");
	for (int counter = 1; counter < 10; counter++)
	{
		printf("%d ", counter);
		if (counter % 3 == 0)
		{
			printf("\n");
		}
	}
	printf("\n");
}

void displayBoard()
{
	int counter, columnsCounter;
	for (counter = 0; counter < 3; counter++)
	{
		for (columnsCounter = 0; columnsCounter < 3; columnsCounter++)
		{
			if (arrBoard[counter][columnsCounter] == '\0')
			{
				printf("- ");
			}
			else
			{
				printf("%c ", arrBoard[counter][columnsCounter]);
			}
		}
		printf("\n");
	}
	printf("\n");
}

void getPlayerMove(char playerCharacter)
{
	displayBoard();
	int row, column;
	char userPosition;
	printf("Enter position for player %d (%c): ", (playerCharacter == playerOneCharacter) ? 1 : 2, playerCharacter);
	scanf(" %c", &userPosition);

	if (userPosition >= '1' && userPosition <= '9')
	{
		switch (userPosition)
		{
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

		if (arrBoard[row][column] == '\0')
		{
			arrBoard[row][column] = playerCharacter;
		}
		else
		{
			printf("Position already occupied.\nPlease enter it again.\n");
			getPlayerMove(playerCharacter);
		}
	}
	else
	{
		printf("Invalid position.\n");
		getPlayerMove(playerCharacter);
	}
}

bool rowCheck(char currentPlayerCharacter)
{
	bool isCurrentPlayerWon;
	if ((arrBoard[0][0] == currentPlayerCharacter) && (arrBoard[0][1] == currentPlayerCharacter) && (arrBoard[0][2] == currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}
	else if ((arrBoard[1][0] == currentPlayerCharacter) && (arrBoard[1][1] == currentPlayerCharacter) && (arrBoard[1][2] == currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}
	else if ((arrBoard[2][0] == currentPlayerCharacter) && (arrBoard[2][1] == currentPlayerCharacter) && (arrBoard[2][2] == currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}
	else
	{
		isCurrentPlayerWon = false;
	}

	return isCurrentPlayerWon;
}

bool columnCheck(char currentPlayerCharacter)
{
	bool isCurrentPlayerWon;
	if ((arrBoard[0][0] == currentPlayerCharacter) && (arrBoard[1][0] == currentPlayerCharacter) && (arrBoard[2][0] == currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}
	else if ((arrBoard[0][1] == currentPlayerCharacter) && (arrBoard[1][1] == currentPlayerCharacter) && (arrBoard[2][1] == currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}
	else if ((arrBoard[0][2] == currentPlayerCharacter) && (arrBoard[1][2] == currentPlayerCharacter) && (arrBoard[2][2] == currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}
	else
	{
		isCurrentPlayerWon = false;
	}

	return isCurrentPlayerWon;
}

bool diagnolCheck(char currentPlayerCharacter)
{
	bool isCurrentPlayerWon;
	if ((arrBoard[0][0] == currentPlayerCharacter) && (arrBoard[1][1] == currentPlayerCharacter) && (arrBoard[2][2] == currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}
	else if ((arrBoard[0][2] == currentPlayerCharacter) && (arrBoard[1][1] == currentPlayerCharacter) && (arrBoard[2][0] == currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}
	else
	{
		isCurrentPlayerWon = false;
	}

	return isCurrentPlayerWon;
}

bool checkMatch(char currentPlayerCharacter)
{
	bool isCurrentPlayerWon;
	if (rowCheck(currentPlayerCharacter) || columnCheck(currentPlayerCharacter) || diagnolCheck(currentPlayerCharacter))
	{
		isCurrentPlayerWon = true;
	}
	else
	{
		isCurrentPlayerWon = false;
	}

	return isCurrentPlayerWon;
}
