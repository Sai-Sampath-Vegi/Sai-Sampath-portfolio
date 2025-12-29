#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

void player(char);
bool check(char);
bool rowCheck(char);
bool columnCheck(char);
bool diagnolCheck(char);
void display();
void printpos();

char arrBoard[3][3], playerOneCharacter, playerTwoCharacter;

int main()
{
	char userChoice;
	do
	{
		printf("...TIC TAC TOE...\n");
		for (int rowCounter = 0; rowCounter < 3; rowCounter++)
			for (int columnCounter = 0; columnCounter < 3; columnCounter++)
				arrBoard[rowCounter][columnCounter] = '\0';
		printf("...RULES...\n");
		printf("1:The player cannot place their option in already existed positions\n");
		printf("2:Each player gets their own option to play\n");
		printf("3:Each player should enter the position of the next move between 1 and 9\n");
		printf("4:The following are the positions\n");
		printpos();
	enterUserChoice:
		printf("Enter the option of player 1:");
		scanf(" %c", &playerOneCharacter);
		printf("Enter the option of player 2:");
		scanf(" %c", &playerTwoCharacter);
		if (playerOneCharacter == playerTwoCharacter)
		{
			printf("Both players choosed same options\n");
			goto enterUserChoice;
		}
		int movesCounter = 0;
		while (movesCounter <= 4)
		{
			printf("Player 1's turn\n");
			player(playerOneCharacter);
			bool isPlayerWon = check(playerOneCharacter);
			if (isPlayerWon)
				goto playerOneWon;
			else if (isPlayerWon == 0 && movesCounter == 4)
				goto tie;
			if (movesCounter < 4)
			{
				printf("Player 2's turn\n");
				player(playerTwoCharacter);
				isPlayerWon = check(playerTwoCharacter);
				if (isPlayerWon)
					goto playerTwoWon;
				else if (isPlayerWon == 0 && movesCounter++ == 4)
					goto tie;
			}
		}
	playerOneWon:
		printf("...PLAYER 1 WON...\n");
		goto end;
	playerTwoWon:
		printf("...PLAYER 2 WON...\n");
		goto end;
	tie:
		printf("...MATCH IS TIE...\n");
	end:
		printf("Do you want to play again press y for yes any key for no:");
		scanf(" %c", &userChoice);
		system("cls");
	} while (userChoice == 'y' || userChoice == 'Y');
	return 0;
}

void printpos()
{
	printf("...Position...\n");
	for (int counter = 1; counter < 10; counter++)
	{
		printf("%d ", counter);
		if (counter % 3 == 0)
			printf("\n");
	}
}

void player(char playerCharacter)
{
	display();
	int userPosition, row, column;
	printf("Enter position for player %d (%c):", (playerCharacter == playerOneCharacter) ? 1 : 2, playerCharacter);
	scanf("%d", &userPosition);
	if (userPosition >= 1 && userPosition <= 9)
	{
		switch (userPosition)
		{
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
		if (arrBoard[row][column] == '\0')
			arrBoard[row][column] = playerCharacter;
		else
		{
			printf("Position already occupied.\nPlease enter it again\n");
			player(playerCharacter);
		}
	}
	else
	{
		printf("Invalid position\n");
		player(playerCharacter);
	}
}

bool rowCheck(char userCharacter)
{
	bool isMatched;
	if ((arrBoard[0][0] == userCharacter) && (arrBoard[0][1] == userCharacter) && (arrBoard[0][2] == userCharacter))
		isMatched = true;
	else if ((arrBoard[1][0] == userCharacter) && (arrBoard[1][1] == userCharacter) && (arrBoard[1][2] == userCharacter))
		isMatched = true;
	else if ((arrBoard[2][0] == userCharacter) && (arrBoard[2][1] == userCharacter) && (arrBoard[2][2] == userCharacter))
		isMatched = true;
	else
		isMatched = false;
	return isMatched;
}

bool columnCheck(char userCharacter)
{
	bool isMatched;
	if ((arrBoard[0][0] == userCharacter) && (arrBoard[1][0] == userCharacter) && (arrBoard[2][0] == userCharacter))
		isMatched = true;
	else if ((arrBoard[0][1] == userCharacter) && (arrBoard[1][1] == userCharacter) && (arrBoard[2][1] == userCharacter))
		isMatched = true;
	else if ((arrBoard[0][2] == userCharacter) && (arrBoard[1][2] == userCharacter) && (arrBoard[2][2] == userCharacter))
		isMatched = true;
	else
		isMatched = false;
	return isMatched;
}

bool diagnolCheck(char userCharacter)
{
	bool isMatched;
	if ((arrBoard[0][0] == userCharacter) && (arrBoard[1][1] == userCharacter) && (arrBoard[2][2] == userCharacter))
		isMatched = true;
	else if ((arrBoard[0][2] == userCharacter) && (arrBoard[1][1] == userCharacter) && (arrBoard[2][0] == userCharacter))
		isMatched = true;
	else
		isMatched = false;
	return isMatched;
}

bool check(char userCharacter)
{
	bool isMatched;
	if (rowCheck(userCharacter) || columnCheck(userCharacter) || diagnolCheck(userCharacter))
		isMatched = true;
	else
		isMatched = false;
	return isMatched;
}

void display()
{
	int counter, columnCounter;
	for (counter = 0; counter < 3; counter++)
	{
		for (columnCounter = 0; columnCounter < 3; columnCounter++)
		{
			if (arrBoard[counter][columnCounter] == '\0')
				printf("- ");
			else
				printf("%c ", arrBoard[counter][columnCounter]);
		}
		printf("\n");
	}
}
