import java.awt.Frame;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.Font;
import java.awt.Button;

public class DotsAndBoxesGame extends Frame implements MouseListener {

    Label gameTitle;
    Label scoresLabel;
    public static Label status;
    static Label scorePlayer1;
    static Label scorePlayer2;
    Board gameBoard;
    Button playAgain, closeGame;
    static int playerOneScore, playerTwoScore;
    public static int movesCounter;

    DotsAndBoxesGame() {

        super("Dots and Boxes Game");
        gameTitle = new Label("DOTS AND BOXES GAME");
        gameTitle.setFont(new Font("Arial", Font.BOLD, 50));
        gameTitle.setBounds(220, 95, 600, 100);
        add(gameTitle);

        playAgain = new Button("PLAY AGAIN");
        playAgain.addMouseListener(this);
        add(playAgain);
        playAgain.setBounds(710, 600, 200, 50);

        closeGame = new Button("X");
        closeGame.setFont(new Font("Arial", Font.BOLD, 30));
        closeGame.setBounds(900, 50, 50, 50);
        closeGame.addMouseListener(this);
        add(closeGame);

        scoresLabel = new Label("SCORES");
        playerOneScore = 0;
        playerTwoScore = 0;
        movesCounter = 0;
        status = new Label();
        scorePlayer1 = new Label("PLAYER 1 : " + playerOneScore);
        scorePlayer2 = new Label("PLAYER 2 : " + playerTwoScore);
        status.setBounds(725, 700, 200, 50);
        scoresLabel.setBounds(725, 400, 100, 50);
        scorePlayer1.setBounds(700, 450, 200, 50);
        scorePlayer2.setBounds(700, 500, 200, 50);
        Font font = new Font("Arial", Font.BOLD, 20);
        status.setFont(font);
        scoresLabel.setFont(font);
        scorePlayer1.setFont(font);
        scorePlayer2.setFont(font);
        add(status);
        add(scoresLabel);
        add(scorePlayer1);
        add(scorePlayer2);

        gameBoard = new Board();
        add(gameBoard);

        setSize(1000, 1000);
        setResizable(false);
        setLocation(0, 0);

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
                    int coordinateX = 150 + i * 50;
                    int coordinateY = 210 + j * 50;
                    if (positionCurrentRow >= coordinateX && positionCurrentRow <= coordinateX + 10
                            && positionCurrentColumn >= coordinateY && positionCurrentColumn <= coordinateY + 40) {
                        if (gameBoard.arrHorizontalLines[i][j] == '\0') {
                            movesCounter++;
                            gameBoard.arrHorizontalLines[i][j] = (movesCounter % 2 == 0) ? 'Y' : 'G';
                            gameBoard.lastMoveColor = (movesCounter % 2 == 0) ? 'Y' : 'G';
                            isValidMove = true;
                            break outHorizontalLines;
                        }
                    }
                }
            }

            outVerticalLines: for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 11; j++) {
                    int coordinateX = 160 + i * 50;
                    int coordinateY = 200 + j * 50;
                    if (positionCurrentRow >= coordinateX && positionCurrentRow <= coordinateX + 40
                            && positionCurrentColumn >= coordinateY && positionCurrentColumn <= coordinateY + 10) {
                        if (gameBoard.arrVerticalLines[i][j] == '\0') {
                            movesCounter++;
                            gameBoard.arrVerticalLines[i][j] = (movesCounter % 2 == 0) ? 'Y' : 'G';
                            gameBoard.lastMoveColor = (movesCounter % 2 == 0) ? 'Y' : 'G';
                            isValidMove = true;
                            break outVerticalLines;
                        }
                    }
                }
            }

            if (isValidMove) {
                gameBoard.repaint();
            }
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
        playerOneScore = 0;
        playerTwoScore = 0;
        scorePlayer1.setText("PLAYER 1 : " + playerOneScore);
        scorePlayer2.setText("PLAYER 2 : " + playerTwoScore);
        DotsAndBoxesGame.status.setText("");
    }

    public static void main(String args[]) {

        new DotsAndBoxesGame();
    }
}

class Board extends Applet {

    public char arrHorizontalLines[][] = new char[11][10], arrVerticalLines[][] = new char[10][11], lastMoveColor,
            arrCompletedColors[][] = new char[10][10];
    public boolean isAlreadyFilled[][] = new boolean[10][10];

    public void init() {

        lastMoveColor = '\0';

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
                isAlreadyFilled[i][j] = false;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                arrCompletedColors[i][j] = '\0';
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
            if (DotsAndBoxesGame.playerOneScore > DotsAndBoxesGame.playerTwoScore) {
                DotsAndBoxesGame.status.setText("PLAYER 1 WON");
            } else if (DotsAndBoxesGame.playerOneScore < DotsAndBoxesGame.playerTwoScore) {
                DotsAndBoxesGame.status.setText("PLAYER 2 WON");
            } else if (DotsAndBoxesGame.playerOneScore == DotsAndBoxesGame.playerTwoScore
                    && DotsAndBoxesGame.playerOneScore != 0) {
                DotsAndBoxesGame.status.setText("MATCH IS TIE");
            }
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                g.setColor(Color.LIGHT_GRAY);
                if (arrHorizontalLines[i][j] == 'G')
                    g.setColor(Color.GREEN);
                else if (arrHorizontalLines[i][j] == 'Y')
                    g.setColor(Color.YELLOW);
                g.fillRect(150 + i * 50, 205 + j * 50, 10, 50);
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
                g.fillRect(155 + i * 50, 200 + j * 50, 50, 10);
            }
        }

        fillScoredAreas(g);
        drawDots(g);

        g.drawRect(680, 350, 250, 200);
    }

    void drawDots(Graphics g) {

        g.setColor(new Color(95, 95, 100));

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                g.fillOval(150 + i * 50, 200 + j * 50, 10, 10);
            }
        }
    }

    void fillScoredAreas(Graphics g) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                char top = arrHorizontalLines[i][j];
                char bottom = arrHorizontalLines[i + 1][j];
                char left = arrVerticalLines[i][j];
                char right = arrVerticalLines[i][j + 1];

                if (top != '\0' && bottom != '\0' && left != '\0' && right != '\0' && !isAlreadyFilled[i][j]) {

                    char currentColorOwner = lastMoveColor;
                    isAlreadyFilled[i][j] = true;
                    arrCompletedColors[i][j] = currentColorOwner;

                    if (currentColorOwner == 'G') {
                        DotsAndBoxesGame.playerOneScore++;
                        DotsAndBoxesGame.scorePlayer1.setText("PLAYER 1 : " + DotsAndBoxesGame.playerOneScore);
                    } else {
                        DotsAndBoxesGame.playerTwoScore++;
                        DotsAndBoxesGame.scorePlayer2.setText("PLAYER 2 : " + DotsAndBoxesGame.playerTwoScore);
                    }
                }

                if (arrCompletedColors[i][j] == 'G') {
                    g.setColor(Color.GREEN);
                    g.fillRect(160 + i * 50, 210 + j * 50, 40, 40);
                } else if (arrCompletedColors[i][j] == 'Y') {
                    g.setColor(Color.YELLOW);
                    g.fillRect(160 + i * 50, 210 + j * 50, 40, 40);
                }
            }
        }
    }
}
