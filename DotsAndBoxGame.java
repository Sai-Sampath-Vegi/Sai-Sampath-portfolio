import java.awt.Frame;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.Font;
import java.awt.Button;

public class DotsAndBoxGame extends Frame implements MouseListener {

    Label gameTitle;
    Label scoresLabel;
    public static Label status;
    static Label scorePlayer1;
    static Label scorePlayer2;
    board gameBoard;
    Button playAgain, closeGame;
    static int player1Score, player2Score;
    public static int movesCounter;

    DotsAndBoxGame() {
        super("Dots Box game");
        gameTitle = new Label("DOTS BOX GAME");
        gameTitle.setFont(new Font("Arial", Font.BOLD, 50));
        gameTitle.setBounds(220, 95, 600, 100);
        add(gameTitle);
        playAgain = new Button("PLAY AGAIN");
        playAgain.addMouseListener(this);
        add(playAgain);
        playAgain.setBounds(760, 600, 200, 50);
        closeGame = new Button("X");
        closeGame.setFont(new Font("Arial", Font.BOLD, 30));
        closeGame.setBounds(900, 50, 50, 50);
        closeGame.addMouseListener(this);
        add(closeGame);
        scoresLabel = new Label("SCORES");
        player1Score = 0;
        player2Score = 0;
        movesCounter = 0;
        status = new Label();
        scorePlayer1 = new Label("PLAYER 1 : " + player1Score);
        scorePlayer2 = new Label("PLAYER 2 : " + player2Score);
        status.setBounds(775, 700, 200, 50);
        scoresLabel.setBounds(775, 400, 100, 50);
        scorePlayer1.setBounds(750, 450, 200, 50);
        scorePlayer2.setBounds(750, 500, 200, 50);
        Font font = new Font("Arial", Font.BOLD, 20);
        status.setFont(font);
        scoresLabel.setFont(font);
        scorePlayer1.setFont(font);
        scorePlayer2.setFont(font);
        add(status);
        add(scoresLabel);
        add(scorePlayer1);
        add(scorePlayer2);
        gameBoard = new board();
        add(gameBoard);
        setSize(1000, 1000);
        gameBoard.init();
        gameBoard.start();
        gameBoard.addMouseListener(this);
        setVisible(true);
        setLayout(null);
    }

    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == closeGame) {
            System.exit(0);
        } else if (me.getSource() == playAgain) {
            playGameAgain();
        } else if (me.getSource() == gameBoard) {
            int positionCurrentRow = me.getX();
            int positionCurrentColumn = me.getY();
            boolean isValidMove = false;

            outHorizontalLines: for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 10; j++) {
                    int coordinateX = 200 + i * 50;
                    int coordinateY = 210 + j * 50;
                    if (positionCurrentRow >= coordinateX && positionCurrentRow <= coordinateX + 10
                            && positionCurrentColumn >= coordinateY && positionCurrentColumn <= coordinateY + 40) {
                        if (gameBoard.arrHorizontalLines[i][j] == '\0') {
                            movesCounter++;
                            gameBoard.arrHorizontalLines[i][j] = (movesCounter % 2 == 0) ? 'Y' : 'G';
                            gameBoard.lastMoveX = i;
                            gameBoard.lastMoveY = j;
                            gameBoard.lastMoveColor = (movesCounter % 2 == 0) ? 'Y' : 'G';
                            isValidMove = true;
                            break outHorizontalLines;
                        }
                    }
                }
            }
            outVerticalLines: for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 11; j++) {
                    int coordinateX = 210 + i * 50;
                    int coordinateY = 200 + j * 50;
                    if (positionCurrentRow >= coordinateX && positionCurrentRow <= coordinateX + 40
                            && positionCurrentColumn >= coordinateY && positionCurrentColumn <= coordinateY + 10) {
                        if (gameBoard.arrVerticalLines[i][j] == '\0') {
                            movesCounter++;
                            gameBoard.arrVerticalLines[i][j] = (movesCounter % 2 == 0) ? 'Y' : 'G';
                            gameBoard.lastMoveX = i;
                            gameBoard.lastMoveY = j;
                            gameBoard.lastMoveColor = (movesCounter % 2 == 0) ? 'Y' : 'G';
                            isValidMove = true;
                            break outVerticalLines;
                        }
                    }
                }
            }
            if (isValidMove)
                gameBoard.repaint();
        }
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    void playGameAgain() {

        gameBoard.init();
        gameBoard.repaint();
        movesCounter = 0;
        player1Score = 0;
        player2Score = 0;
        scorePlayer1.setText("PLAYER 1 : " + player1Score);
        scorePlayer2.setText("PLAYER 2 : " + player2Score);
        DotsAndBoxGame.status.setText("");
    }

    public static void main(String args[]) {
        
        new DotsAndBoxGame();
    }

}

class board extends Applet {

    public char arrHorizontalLines[][] = new char[11][10], arrVerticalLines[][] = new char[10][11], lastMoveColor,
            completedColors[][] = new char[10][10];
    public boolean completed[][] = new boolean[10][10];
    public int lastMoveX, lastMoveY;

    public void init() {

        lastMoveColor = '\0';
        lastMoveX = -1;
        lastMoveY = -1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                arrHorizontalLines[i][j] = '\0';
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                arrVerticalLines[i][j] = '\0';
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                completed[i][j] = false;
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                completedColors[i][j] = '\0';
            }
        }
    }

    public void paint(Graphics g) {

        super.paint(g);
        int flag1 = 0, flag2 = 0;
        out1: for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                if (arrHorizontalLines[i][j] == '\0') {
                    flag1 = 1;
                    break out1;
                }
            }
        }
        out2: for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                if (arrVerticalLines[i][j] == '\0') {
                    flag2 = 1;
                    break out2;
                }
            }
        }
        if (flag1 == 0 && flag2 == 0) {
            if (DotsAndBoxGame.player1Score > DotsAndBoxGame.player2Score) {
                DotsAndBoxGame.status.setText("PLAYER 1 WON");
            } else if (DotsAndBoxGame.player1Score < DotsAndBoxGame.player2Score) {
                DotsAndBoxGame.status.setText("PLAYER 2 WON");
            } else if (DotsAndBoxGame.player1Score == DotsAndBoxGame.player2Score && DotsAndBoxGame.player1Score != 0) {
                DotsAndBoxGame.status.setText("MATCH IS TIE");
            }
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                g.setColor(Color.LIGHT_GRAY);
                if (arrHorizontalLines[i][j] == 'G')
                    g.setColor(Color.GREEN);
                else if (arrHorizontalLines[i][j] == 'Y')
                    g.setColor(Color.YELLOW);
                g.fillRect(200 + i * 50, 205 + j * 50, 10, 50);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                g.setColor(Color.LIGHT_GRAY);
                if (arrVerticalLines[i][j] == 'G') {
                    g.setColor(Color.GREEN);
                } else if (arrVerticalLines[i][j] == 'Y') {
                    g.setColor(Color.YELLOW);
                }
                g.fillRect(205 + i * 50, 200 + j * 50, 50, 10);
            }
        }
        fillArea(g);
        g.setColor(new Color(95, 95, 100));
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                g.fillOval(200 + i * 50, 200 + j * 50, 10, 10);
            }
        }
        g.drawRect(730, 375, 250, 200);
    }

    void fillArea(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                char top = arrHorizontalLines[i][j];
                char bottom = arrHorizontalLines[i + 1][j];
                char left = arrVerticalLines[i][j];
                char right = arrVerticalLines[i][j + 1];
                if (top != '\0' && bottom != '\0' && left != '\0' && right != '\0' && !completed[i][j]) {

                    char owner = lastMoveColor;
                    completed[i][j] = true;
                    completedColors[i][j] = owner;

                    if (owner == 'G') {
                        DotsAndBoxGame.player1Score++;
                        DotsAndBoxGame.scorePlayer1.setText("PLAYER 1 : " + DotsAndBoxGame.player1Score);
                    } else {
                        DotsAndBoxGame.player2Score++;
                        DotsAndBoxGame.scorePlayer2.setText("PLAYER 2 : " + DotsAndBoxGame.player2Score);
                    }
                }
                if (completedColors[i][j] == 'G') {
                    g.setColor(Color.GREEN);
                    g.fillRect(210 + i * 50, 210 + j * 50, 40, 40);
                } else if (completedColors[i][j] == 'Y') {
                    g.setColor(Color.YELLOW);
                    g.fillRect(210 + i * 50, 210 + j * 50, 40, 40);
                }
            }
        }
    }
}
