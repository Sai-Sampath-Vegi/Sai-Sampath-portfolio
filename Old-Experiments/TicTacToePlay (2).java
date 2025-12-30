import java.util.Scanner;

public class TicTacToePlay {
    public static void main(String[] args) {
        TicTacToeGameWithComputer play = new TicTacToeGameWithComputer();
        play.play();

    }
}

class TicTacToeGameWithComputer {
    char arr[] = new char[9], p = 'X', comp = 'O';
    Scanner scan = new Scanner(System.in);

    void printpos() {
        System.out.print("...Position...\n");
        for (int i = 1; i < 10; i++) {
            System.out.print(" " + i + " ");
            if (i % 3 == 0)
                System.out.print("\n");
        }
    }

    int match(int p1, int p2) {
        int wcs[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
                { 2, 4, 6 } }, i, j, k;
        for (i = 0; i < 8; i++) {
            if (((wcs[i][0] == p1) && (wcs[i][1] == p2) && (arr[wcs[i][2]] == '\0'))
                    || ((wcs[i][0] == p2) && (wcs[i][1] == p1) && (arr[wcs[i][2]] == '\0')))
                return wcs[i][2];
            if (((wcs[i][1] == p1) && (wcs[i][2] == p2) && (arr[wcs[i][0]] == '\0'))
                    || ((wcs[i][1] == p2) && (wcs[i][2] == p1) && (arr[wcs[i][0]] == '\0')))
                return wcs[i][0];
            if (((wcs[i][0] == p1) && (wcs[i][2] == p2) && (arr[wcs[i][1]] == '\0'))
                    || ((wcs[i][0] == p2) && (wcs[i][2] == p1) && (arr[wcs[i][1]] == '\0')))
                return wcs[i][1];
        }
        return -1;
    }

    void com() {
        int i, j = 0, k = 0, carr[] = new int[4], parr[] = new int[5], pos = -1, f = 0, r;
        for (i = 0; i < 9; i++) {
            if (arr[i] == comp)
                carr[k++] = i;
            else if (arr[i] == p)
                parr[j++] = i;
        }
        for (i = 0; i < k; i++) {
            for (r = i + 1; r < k; r++) {
                pos = match(carr[i], carr[r]);
                if (pos != -1)
                    break;
            }
            if (pos != -1)
                break;
        }
        if (pos == -1) {
            for (i = 0; i < j; i++) {
                for (r = i + 1; r < j; r++) {
                    pos = match(parr[i], parr[r]);
                    if (pos != -1)
                        break;
                }
                if (pos != -1)
                    break;
            }
        }
        if (pos == -1) {
            int prarr[] = { 4, 0, 2, 6, 8, 1, 3, 5, 7 };
            for (i = 0; i < 9; i++)
                if (arr[prarr[i]] == '\0') {
                    pos = prarr[i];
                    break;
                }
        }
        if (pos != -1)
            arr[pos] = comp;
    }

    void player() {
        int pos;

        System.out.print("Enter position for " + p + ":");
        pos = scan.nextInt();
        if (pos >= 1 && pos <= 9) {
            if (arr[pos - 1] == '\0')
                arr[pos - 1] = p;
            else {
                System.out.print("Position occupied\n");
                player();
            }
        } else {
            System.out.print("Invalid position\n");
            player();
        }

    }

    boolean rcheck(char p) {
        if ((arr[0] == p) && (arr[1] == p) && (arr[2] == p))
            return true;
        else if ((arr[3] == p) && (arr[4] == p) && (arr[5] == p))
            return true;
        else if ((arr[6] == p) && (arr[7] == p) && (arr[8] == p))
            return true;
        else
            return false;
    }

    boolean ccheck(char p) {
        if ((arr[0] == p) && (arr[3] == p) && (arr[6] == p))
            return true;
        else if ((arr[1] == p) && (arr[4] == p) && (arr[7] == p))
            return true;
        else if ((arr[2] == p) && (arr[5] == p) && (arr[8] == p))
            return true;
        else
            return false;
    }

    boolean dcheck(char p) {
        if ((arr[0] == p) && (arr[4] == p) && (arr[8] == p))
            return true;
        else if ((arr[2] == p) && (arr[4] == p) && (arr[6] == p))
            return true;
        else
            return false;
    }

    void display() {
        int i;
        System.out.print("\n");
        for (i = 1; i < 10; i++) {
            if (arr[i - 1] == '\0')
                System.out.print(" - ");
            else
                System.out.print(" " + arr[i - 1] + " ");
            if (i % 3 == 0)
                System.out.print("\n");
        }
        System.out.print("\n");
    }

    boolean check(char p) {
        if ((rcheck(p)) || (ccheck(p)) || (dcheck(p)))
            return true;
        else
            return false;
    }

    void play() {
        char ch;
        int i, j;
        boolean ck;
        System.out.print("...TIC TAC TOE...\n");
        System.out.print("...RULES...\n");
        System.out.print("The player cannot place their option in already occupied positions\n");
        System.out.print("Player should enter the position of his/her move between 1 and 9\n");
        System.out.print("Player gets the option (X) and Computer gets the option (O)\n");
        System.out.print("The following are the positions\n");
        printpos();
        do {
            for (i = 0; i < 9; i++)
                arr[i] = '\0';
            i = 0;
            end: while (i <= 4) {
                System.out.print("Your turn\n");
                player();
                ck = check(p);
                if (ck == true) {
                    display();
                    System.out.print("...YOU WON...\n");
                    break end;
                } else if (ck == false && i == 4) {
                    display();
                    System.out.print("...MATCH IS TIE...\n");
                    break end;
                }
                if (i < 4) {
                    com();
                    display();
                    ck = check(comp);
                    if (ck == true) {
                        display();
                        System.out.print("...COMPUTER WON TRY AGAIN...\n");
                        break end;
                    } else if (ck == false && i++ == 4) {
                        display();
                        System.out.print("...MATCH IS TIE...\n");
                        break end;
                    }
                }
            }
            System.out.print("Do you want to play again press y for yes any key for no:");
            ch = scan.next().charAt(0);
        } while (ch == 'y' || ch == 'Y');
        scan.close();
    }
}