import java.util.Scanner;
import java.util.Locale;

class sosPlay {
    public static void main(String args[]) {
        sosGame play = new sosGame();
        play.play();
    }
}

class sosGame {
    int scoreP1 = 0, scoreP2 = 0;
    char board[][] = new char[16][16];
    String positions[][] = {
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
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
                board[i][j] = '-';
    }

    void printPos() {
        int i, j;
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {
                System.out.print(positions[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    void printBoard() {
        int i, j;
        System.out.print("Puzzle:\n");
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++)
                System.out.print(board[i][j]);
            if (i == 14)
                System.out.print("\tPlayer1 score is " + scoreP1);
            else if (i == 15)
                System.out.print("\tPlayer2 score is " + scoreP2);
            System.out.print("\n");
        }
    }

    void input(int player) {
        int i, j;
        boolean filled = false, posocc = false;
        char ch;
        String pos;
        if (player == 1)
            System.out.print("Enter player1 move\n");
        else
            System.out.print("Enter player2 move\n");
        read: do {
            System.out.print("Enter position:");
            pos = scan.nextLine();
            pos = pos.toUpperCase(Locale.ENGLISH);
            for (i = 0; i < 16; i++) {
                for (j = 0; j < 16; j++) {
                    if (pos.equals(positions[i][j])) {
                        if (board[i][j] == '-') {
                            do {
                                System.out.print("Enter the character (S or O):");
                                ch = scan.nextLine().charAt(0);
                                if ((ch != 's' && ch != 'S') && (ch != 'o' && ch != 'O')) {
                                    System.out.print("Enter the character again\n");
                                } else
                                    break;
                            } while ((ch != 's' || ch != 'S') || (ch != 'o' || ch != 'O'));
                            ch = Character.toUpperCase(ch);
                            board[i][j] = ch;
                            filled = true;
                            score(pos, player);
                        } else {
                            System.out.print("Position already occupied\nPlease enter it again\n");
                            posocc = true;
                        }
                    }
                }
            }
            if ((!filled) && (posocc != true)) {
                System.out.print("You entered wrong position\nPlease enter it again\n");
            }
        } while (!filled);
    }

    void score(String lastPos, int player) {
        int row = -1, col = -1, currentScoreP1 = scoreP1, currentScoreP2 = scoreP2;
        if (lastPos.charAt(0) >= '0' && lastPos.charAt(0) <= '9')
            row = lastPos.charAt(0) - '0';
        if (lastPos.charAt(0) >= 'A' && lastPos.charAt(0) <= 'F') {
            if (lastPos.charAt(0) == 'A')
                row = 10;
            else if (lastPos.charAt(0) == 'B')
                row = 11;
            else if (lastPos.charAt(0) == 'C')
                row = 12;
            else if (lastPos.charAt(0) == 'D')
                row = 13;
            else if (lastPos.charAt(0) == 'E')
                row = 14;
            else if (lastPos.charAt(0) == 'F')
                row = 15;
        }
        if (lastPos.charAt(1) >= '0' && lastPos.charAt(1) <= '9')
            col = lastPos.charAt(1) - '0';
        if (lastPos.charAt(1) >= 'A' && lastPos.charAt(1) <= 'F') {
            if (lastPos.charAt(1) == 'A')
                col = 10;
            else if (lastPos.charAt(1) == 'B')
                col = 11;
            else if (lastPos.charAt(1) == 'C')
                col = 12;
            else if (lastPos.charAt(1) == 'D')
                col = 13;
            else if (lastPos.charAt(1) == 'E')
                col = 14;
            else if (lastPos.charAt(1) == 'F')
                col = 15;
        }
        if (board[row][col] == 'O') {
            if ((row == 0 && col == 0) || (row == 15 && col == 0) || (row == 0 && col == 15)
                    || (row == 15 && col == 15))
                return;
            else {
                if (row == 0 || row == 15) {
                    if (board[row][col - 1] == 'S' && board[row][col + 1] == 'S')
                        if (player == 1)
                            scoreP1++;
                        else
                            scoreP2++;
                } else if (col == 0 || col == 15) {
                    if (board[row - 1][col] == 'S' && board[row + 1][col] == 'S')
                        if (player == 1)
                            scoreP1++;
                        else
                            scoreP2++;
                } else {
                    if (board[row - 1][col] == 'S' && board[row + 1][col] == 'S')
                        if (player == 1)
                            scoreP1++;
                        else
                            scoreP2++;
                    if (board[row - 1][col - 1] == 'S' && board[row + 1][col + 1] == 'S')
                        if (player == 1)
                            scoreP1++;
                        else
                            scoreP2++;
                    if (board[row - 1][col + 1] == 'S' && board[row + 1][col - 1] == 'S')
                        if (player == 1)
                            scoreP1++;
                        else
                            scoreP2++;
                    if (board[row][col - 1] == 'S' && board[row][col + 1] == 'S')
                        if (player == 1)
                            scoreP1++;
                        else
                            scoreP2++;
                }
            }
        } else {
            if (row == 0 && col == 0) {
                if (board[row + 2][col] == 'S' && board[row + 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col + 2] == 'S' && board[row][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col + 2] == 'S' && board[row + 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
            } else if (row == 15 && col == 0) {
                if (board[row - 2][col] == 'S' && board[row - 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col + 2] == 'S' && board[row][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col + 2] == 'S' && board[row + 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
            } else if (row == 0 && col == 15) {
                if (board[row + 2][col] == 'S' && board[row + 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col - 2] == 'S' && board[row][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col - 2] == 'S' && board[row + 1][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
            } else if (row == 15 && col == 15) {
                if (board[row - 2][col] == 'S' && board[row - 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col - 2] == 'S' && board[row][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col - 2] == 'S' && board[row - 1][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
            } else if (row == 0) {
                if (board[row + 2][col] == 'S' && board[row + 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col + 2] == 'S' && board[row][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col - 2] == 'S' && board[row][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col + 2] == 'S' && board[row + 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col - 2] == 'S' && board[row + 1][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
            } else if (row == 15) {
                if (board[row - 2][col] == 'S' && board[row - 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col + 2] == 'S' && board[row][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col - 2] == 'S' && board[row][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col + 2] == 'S' && board[row - 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col - 2] == 'S' && board[row - 1][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
            } else if (col == 0) {
                if (board[row + 2][col] == 'S' && board[row + 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col] == 'S' && board[row - 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col + 2] == 'S' && board[row][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col + 2] == 'S' && board[row + 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col + 2] == 'S' && board[row - 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
            } else if (col == 15) {
                if (board[row + 2][col] == 'S' && board[row + 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col] == 'S' && board[row - 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col - 2] == 'S' && board[row][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col - 2] == 'S' && board[row + 1][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col - 2] == 'S' && board[row - 1][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
            } else {
                if (board[row - 2][col] == 'S' && board[row - 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col] == 'S' && board[row + 1][col] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col - 2] == 'S' && board[row][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row][col + 2] == 'S' && board[row][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col + 2] == 'S' && board[row - 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col + 2] == 'S' && board[row + 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col - 2] == 'S' && board[row - 1][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col - 2] == 'S' && board[row + 1][col - 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row - 2][col + 2] == 'S' && board[row - 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
                if (board[row + 2][col + 2] == 'S' && board[row + 1][col + 1] == 'O')
                    if (player == 1)
                        scoreP1++;
                    else
                        scoreP2++;
            }
        }
        if (currentScoreP1 != scoreP1) {
            System.out.print("Player1 scored a point.\nEnter player1's next move\n");
            printPos();
            printBoard();
        } else if (currentScoreP2 != scoreP2) {
            System.out.print("Player2 scored a point.\nEnter player2's next move\n");
            printPos();
            printBoard();
        }
        if ((((scoreP1 % 15) == 0) || ((scoreP2 % 15) == 0)) && (scoreP1 != 0 && scoreP2 != 0)) {
            System.out.print(
                    "Do you want to continue playing or exit.\nPress 'Y' to exit any other to continue playing:");
            char ch;
            ch = scan.nextLine().charAt(0);
            if (Character.toLowerCase(ch) == 'y')
                System.exit(0);
        }
        if (currentScoreP1 != scoreP1)
            input(1);
        else if (currentScoreP2 != scoreP2)
            input(2);
    }

    boolean boardFilled() {
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
                if (board[i][j] == '-')
                    return false;
        return true;
    }

    void play() {
        System.out.print("\t\t\t\tRules\n");
    System.out.print("1. Current player gets a point if he place his/her move to make a combination (SOS)\n");
    System.out.print("2. No two players can enter their move in already occupied position\n\n");
    System.out.print("\t\t\t\tNote\n");
    System.out.print("1. You can enter the input in any case it will automatically convert the input to the necessary case\n");
    System.out.print("2. The game prompts you to enter 'Y' to exit after every 15 points to any player to exit and any other character to continue playing\n\n\n");
        init();
        printPos();
        while (!boardFilled()) {
            input(1);
            printPos();
            printBoard();
            input(2);
            printPos();
            printBoard();
        }
    }
}