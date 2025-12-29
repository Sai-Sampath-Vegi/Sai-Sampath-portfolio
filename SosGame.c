#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

int scoreOfPlayerOne = 0, scoreOfPlayerTwo = 0;
char board[16][16] = {
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}};

char positions[16][16][3] = {
    {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F"},
    {"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F"},
    {"20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F"},
    {"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F"},
    {"40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F"},
    {"50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F"},
    {"60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F"},
    {"70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F"},
    {"80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F"},
    {"90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F"},
    {"A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF"},
    {"B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF"},
    {"C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF"},
    {"D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF"},
    {"E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF"},
    {"F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF"}};

void printBoard();
void printPositions();
void playerMove(int);
void score(char[], int);
void play();
char toUpper(char);
char toLower(char);

int main()
{
    printf("\t\t\t\tRules\n");
    printf("1. Current player gets a point if he place his/her move to make a combination (SOS)\n");
    printf("2. No two players can enter their move in already occupied position\n\n");
    printf("\t\t\t\tNote\n");
    printf("1. You can enter the input in any case it will automatically convert the input to the necessary case\n");
    printf("2. The game prompts you to enter 'Y' to exit after every 15 points to any player to exit and any other character to continue playing\n\n\n");
    play();
    return 0;
}

void printPositions()
{
    int rowCounter, columnCounter;
    for (rowCounter = 0; rowCounter < 16; rowCounter++)
    {
        for (columnCounter = 0; columnCounter < 16; columnCounter++)
        {
            printf("%s", positions[rowCounter][columnCounter]);
            printf(" ");
        }
        printf("\n");
    }
}

void printBoard()
{
    int rowCounter, columnCounter;
    printf("Puzzle:\n");
    for (rowCounter = 0; rowCounter < 16; rowCounter++)
    {
        for (columnCounter = 0; columnCounter < 16; columnCounter++)
            printf("%c ", board[rowCounter][columnCounter]);
        if (rowCounter == 14)
            printf("\tPlayer1 score is %d", scoreOfPlayerOne);
        else if (rowCounter == 15)
            printf("\tPlayer2 score is %d", scoreOfPlayerTwo);
        printf("\n");
    }
}

void playerMove(int player)
{
    int rowCounter, columnCounter;
    bool isFilled = false;
    char character, *playerPosition = malloc(3);
    if (player == 1)
        printf("Enter player1 move\n");
    else
        printf("Enter player2 move\n");
readPosition:
    printf("Enter position:");
    scanf("%s", playerPosition);
    for (rowCounter = 0; rowCounter < 2; rowCounter++)
        playerPosition[rowCounter] = toUpper(playerPosition[rowCounter]);
    for (rowCounter = 0; rowCounter < 16; rowCounter++)
    {
        for (columnCounter = 0; columnCounter < 16; columnCounter++)
        {
            if ((strcmp(playerPosition, positions[rowCounter][columnCounter]) == 0))
            {
                if (board[rowCounter][columnCounter] == '-')
                {
                enterCharacter:
                    printf("Enter the character (S or O):");
                    scanf(" %c", &character);
                    if ((character != 's' && character != 'S') && (character != 'o' && character != 'O'))
                    {
                        printf("Enter the character again\n");
                        goto enterCharacter;
                    }
                    character = toUpper(character);
                    board[rowCounter][columnCounter] = character;
                    isFilled = true;
                    score(playerPosition, player);
                }
                else
                {
                    printf("Position already occupied\nPlease enter it again\n");
                    goto readPosition;
                }
            }
        }
    }
    if (!isFilled)
    {
        printf("You entered wrong position\nPlease enter it again\n");
        goto readPosition;
    }
}

void score(char lastPosition[], int player)
{
    int row, column, currentScoreOfPlayerOne = scoreOfPlayerOne, currentScoreOfPlayerTwo = scoreOfPlayerTwo;
    if (lastPosition[0] >= '0' && lastPosition[0] <= '9')
        row = lastPosition[0] - '0';
    if (lastPosition[0] >= 'A' && lastPosition[0] <= 'F')
    {
        if (lastPosition[0] == 'A')
            row = 10;
        else if (lastPosition[0] == 'B')
            row = 11;
        else if (lastPosition[0] == 'C')
            row = 12;
        else if (lastPosition[0] == 'D')
            row = 13;
        else if (lastPosition[0] == 'E')
            row = 14;
        else if (lastPosition[0] == 'F')
            row = 15;
    }
    if (lastPosition[1] >= '0' && lastPosition[1] <= '9')
        column = lastPosition[1] - '0';
    if (lastPosition[1] >= 'A' && lastPosition[1] <= 'F')
    {
        if (lastPosition[1] == 'A')
            column = 10;
        else if (lastPosition[1] == 'B')
            column = 11;
        else if (lastPosition[1] == 'C')
            column = 12;
        else if (lastPosition[1] == 'D')
            column = 13;
        else if (lastPosition[1] == 'E')
            column = 14;
        else if (lastPosition[1] == 'F')
            column = 15;
    }
    if (board[row][column] == 'O')
    {
        if ((row == 0 && column == 0) || (row == 15 && column == 0) || (row == 0 && column == 15) || (row == 15 && column == 15))
            return;
        else
        {
            if (row == 0 || row == 15)
            {
                if (board[row][column - 1] == 'S' && board[row][column + 1] == 'S')
                    (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            }
            else if (column == 0 || column == 15)
            {
                if (board[row - 1][column] == 'S' && board[row + 1][column] == 'S')
                    (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            }
            else
            {
                if (board[row - 1][column] == 'S' && board[row + 1][column] == 'S')
                    (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
                if (board[row - 1][column - 1] == 'S' && board[row + 1][column + 1] == 'S')
                    (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
                if (board[row - 1][column + 1] == 'S' && board[row + 1][column - 1] == 'S')
                    (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
                if (board[row][column - 1] == 'S' && board[row][column + 1] == 'S')
                    (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            }
        }
    }
    else
    {
        if (row == 0 && column == 0)
        {
            if (board[row + 2][column] == 'S' && board[row + 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column + 2] == 'S' && board[row][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column + 2] == 'S' && board[row + 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
        }
        else if (row == 15 && column == 0)
        {
            if (board[row - 2][column] == 'S' && board[row - 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column + 2] == 'S' && board[row][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column + 2] == 'S' && board[row + 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
        }
        else if (row == 0 && column == 15)
        {
            if (board[row + 2][column] == 'S' && board[row + 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column - 2] == 'S' && board[row][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column - 2] == 'S' && board[row + 1][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
        }
        else if (row == 15 && column == 15)
        {
            if (board[row - 2][column] == 'S' && board[row - 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column - 2] == 'S' && board[row][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column - 2] == 'S' && board[row - 1][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
        }
        else if (row == 0)
        {
            if (board[row + 2][column] == 'S' && board[row + 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column + 2] == 'S' && board[row][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column - 2] == 'S' && board[row][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column + 2] == 'S' && board[row + 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column - 2] == 'S' && board[row + 1][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
        }
        else if (row == 15)
        {
            if (board[row - 2][column] == 'S' && board[row - 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column + 2] == 'S' && board[row][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column - 2] == 'S' && board[row][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column + 2] == 'S' && board[row - 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column - 2] == 'S' && board[row - 1][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
        }
        else if (column == 0)
        {
            if (board[row + 2][column] == 'S' && board[row + 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column] == 'S' && board[row - 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column + 2] == 'S' && board[row][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column + 2] == 'S' && board[row + 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column + 2] == 'S' && board[row - 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
        }
        else if (column == 15)
        {
            if (board[row + 2][column] == 'S' && board[row + 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column] == 'S' && board[row - 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column - 2] == 'S' && board[row][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column - 2] == 'S' && board[row + 1][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column - 2] == 'S' && board[row - 1][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
        }
        else
        {
            if (board[row - 2][column] == 'S' && board[row - 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column] == 'S' && board[row + 1][column] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column - 2] == 'S' && board[row][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row][column + 2] == 'S' && board[row][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column + 2] == 'S' && board[row - 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column + 2] == 'S' && board[row + 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column - 2] == 'S' && board[row - 1][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column - 2] == 'S' && board[row + 1][column - 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row - 2][column + 2] == 'S' && board[row - 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
            if (board[row + 2][column + 2] == 'S' && board[row + 1][column + 1] == 'O')
                (player == 1) ? scoreOfPlayerOne++ : scoreOfPlayerTwo++;
        }
    }
    if (currentScoreOfPlayerOne != scoreOfPlayerOne)
    {
        printf("Player1 scored a point.\nEnter player1's next move\n");
        printPositions();
        printBoard();
    }
    else if (currentScoreOfPlayerTwo != scoreOfPlayerTwo)
    {
        printf("Player2 scored a point.\nEnter player2's next move\n");
        printPositions();
        printBoard();
    }
    if ((((scoreOfPlayerOne % 15) == 0) || ((scoreOfPlayerTwo % 15) == 0)) && (scoreOfPlayerOne != 0 && scoreOfPlayerTwo != 0))
    {
        printf("Do you want to continue playing or exit.\nPress 'Y' to exit any other to continue playing:");
        char ex;
        scanf(" %c", &ex);
        if (toLower(ex) == 'y')
            exit(0);
    }
    if (currentScoreOfPlayerOne != scoreOfPlayerOne)
        playerMove(1);
    else if (currentScoreOfPlayerTwo != scoreOfPlayerTwo)
        playerMove(2);
}

bool boardFilled()
{
    for (int rowCounter = 0; rowCounter < 16; rowCounter++)
        for (int columnCounter = 0; columnCounter < 16; columnCounter++)
            if (board[rowCounter][columnCounter] == '-')
                return false;
    return true;
}

void play()
{
    printPositions();
    while (!boardFilled())
    {
        playerMove(1);
        printPositions();
        printBoard();
        playerMove(2);
        printPositions();
        printBoard();
    }
}

char toUpper(char letter)
{
    return (letter >= 'a' && letter <= 'z') ? (letter - 32) : letter;
}

char toLower(char letter)
{
    return (letter >= 'A' && letter <= 'Z') ? (letter + 32) : letter;
}
