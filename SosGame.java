import java.awt.Frame;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.Dialog;
import java.awt.Color;

public class SosGame extends Frame implements MouseListener {

    gameBoard board;
    Button closeButton, playAgain, sButton, oButton;
    Label gameTitle, scoresTitle, scoreplayerMove1, scoreplayerMove2, move, status;
    int playerOneScore, playerTwoScore, playerMoveCounter;
    Dialog popupMenu;

    SosGame() {

        setLayout(null);
        playerOneScore = 0;
        playerTwoScore = 0;
        playerMoveCounter = 1;
        gameTitle = new Label("SOS GAME");
        gameTitle.setBounds(300, 50, 350, 50);
        add(gameTitle);
        status = new Label();
        status.setBounds(650, 675, 300, 40);
        add(status);
        gameTitle.setFont(new Font("Arial", Font.BOLD, 50));
        scoresTitle = new Label("SCORES");
        scoreplayerMove1 = new Label("PLAYER 1 SCORE : " + playerOneScore);
        scoreplayerMove2 = new Label("PLAYER 2 SCORE : " + playerTwoScore);
        move = new Label("PLAYER 1 MOVE");
        scoresTitle.setBounds(750, 200, 100, 50);
        scoreplayerMove1.setBounds(675, 250, 275, 50);
        scoreplayerMove2.setBounds(675, 300, 275, 50);
        move.setBounds(700, 500, 200, 100);
        Font font = new Font("Arial", Font.BOLD, 20);
        scoresTitle.setFont(font);
        scoreplayerMove1.setFont(font);
        scoreplayerMove2.setFont(font);
        status.setFont(font);
        move.setFont(font);
        add(scoresTitle);
        add(scoreplayerMove1);
        add(scoreplayerMove2);
        add(move);
        board = new gameBoard();
        board.setBounds(50, 100, 950, 700);
        board.init();
        board.start();
        board.addMouseListener(this);
        closeButton = new Button("CLOSE GAME");
        playAgain = new Button("PLAY AGAIN");
        closeButton.addMouseListener(this);
        playAgain.addMouseListener(this);
        closeButton.setFont(font);
        playAgain.setFont(font);
        playAgain.setBounds(700, 375, 200, 50);
        closeButton.setBounds(700, 440, 200, 50);
        add(playAgain);
        add(closeButton);
        add(board);
        setVisible(true);
        setSize(1200, 1200);
        popupMenu = new Dialog(this, "SELECT OPTION", true);
        popupMenu.setSize(100, 80);
        popupMenu.setLayout(null);
        sButton = new Button("S");
        oButton = new Button("O");
        sButton.setBounds(20, 40, 30, 30);
        oButton.setBounds(60, 40, 30, 30);
        sButton.setBackground(Color.WHITE);
        oButton.setBackground(Color.WHITE);
        popupMenu.add(sButton);
        popupMenu.add(oButton);
        Label label = new Label("CHOOSE S OR O");
        label.setBounds(0, 0, 100, 20);
        label.setForeground(Color.WHITE);
        popupMenu.add(label);
        sButton.addMouseListener(this);
        oButton.addMouseListener(this);
        popupMenu.setBackground(Color.GRAY);
        popupMenu.setVisible(false);
    }

    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == closeButton) {
            System.exit(0);
        } else if (me.getSource() == playAgain) {
            playAgain();
        } else if (me.getSource() == sButton) {
            board.board[board.cellPosX][board.cellPosY] = 'S';
            board.isChanged = false;
            board.repaint();
            popupMenu.setVisible(false);
            boolean isSecuredPoint = checkPoint(board.cellPosX, board.cellPosY);
            if (!isSecuredPoint) {
                playerMoveCounter++;
                move.setText("PLAYER " + String.valueOf((playerMoveCounter % 2 == 0) ? 2 : 1) + " MOVE");
            }
        } else if (me.getSource() == oButton) {
            board.board[board.cellPosX][board.cellPosY] = 'O';
            board.isChanged = false;
            board.repaint();
            popupMenu.setVisible(false);
            boolean isSecuredPoint = checkPoint(board.cellPosX, board.cellPosY);
            if (!isSecuredPoint) {
                playerMoveCounter++;
                move.setText("PLAYER " + String.valueOf((playerMoveCounter % 2 == 0) ? 2 : 1) + " MOVE");
            }
        } else {
            int posX = me.getX();
            int posY = me.getY();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (posX >= 50 + i * 50 && posX <= 50 + (i + 1) * 50 && posY >= 100 + j * 50
                            && posY <= 100 + (j + 1) * 50) {
                        board.cellPosX = i;
                        board.cellPosY = j;
                        board.isChanged = true;
                        if (board.board[board.cellPosX][board.cellPosY] == '\0') {
                            board.repaint();
                            popupMenu.setLocation(50 + i * 50, 100 + j * 50);
                            popupMenu.setVisible(true);
                        }
                    }
                }
            }
        }
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    void playAgain() {
        board.init();
        board.repaint();
        playerOneScore = 0;
        playerTwoScore = 0;
        playerMoveCounter = 1;
        move.setText("PLAYER 1 MOVE");
        scoreplayerMove1.setText("PLAYER 1 SCORE : " + playerOneScore);
        scoreplayerMove2.setText("PLAYER 2 SCORE : " + playerTwoScore);
        status.setText("");
    }

    boolean checkPoint(int row, int column) {
        boolean flag = false;
        if (board.board[row][column] == 'O') {
            if ((row == 0 && column == 0) || (row == 9 && column == 0) || (row == 0 && column == 9)
                    || (row == 9 && column == 9))
                return false;
            else {
                if (row == 0 || row == 9) {
                    if (column >= 1 && column <= 8) {
                        if (board.board[row][column - 1] == 'S' && board.board[row][column + 1] == 'S') {
                            if (playerMoveCounter % 2 == 1) {
                                board.completedPlayer1[playerOneScore][0] = row;
                                board.completedPlayer1[playerOneScore][1] = column - 1;
                                board.completedPlayer1[playerOneScore][2] = row;
                                board.completedPlayer1[playerOneScore][3] = column + 1;
                                playerOneScore++;
                            } else {
                                board.completedPlayer2[playerTwoScore][0] = row;
                                board.completedPlayer2[playerTwoScore][1] = column - 1;
                                board.completedPlayer2[playerTwoScore][2] = row;
                                board.completedPlayer2[playerTwoScore][3] = column + 1;
                                playerTwoScore++;
                            }
                            flag = true;
                        }
                    }
                } else if ((column == 0 || column == 9) && row >= 1 && row <= 8) {
                    if (board.board[row - 1][column] == 'S' && board.board[row + 1][column] == 'S') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 1;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row + 1;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 1;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row + 1;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                } else {
                    if (row >= 1 && row <= 8) {
                        if (board.board[row - 1][column] == 'S' && board.board[row + 1][column] == 'S') {
                            if (playerMoveCounter % 2 == 1) {
                                board.completedPlayer1[playerOneScore][0] = row - 1;
                                board.completedPlayer1[playerOneScore][1] = column;
                                board.completedPlayer1[playerOneScore][2] = row + 1;
                                board.completedPlayer1[playerOneScore][3] = column;
                                playerOneScore++;
                            } else {
                                board.completedPlayer2[playerTwoScore][0] = row - 1;
                                board.completedPlayer2[playerTwoScore][1] = column;
                                board.completedPlayer2[playerTwoScore][2] = row + 1;
                                board.completedPlayer2[playerTwoScore][3] = column;
                                playerTwoScore++;
                            }
                            flag = true;
                        }
                    }
                    if (row >= 1 && row <= 8 && column >= 1 && column <= 8) {
                        if (board.board[row - 1][column - 1] == 'S' && board.board[row + 1][column + 1] == 'S') {
                            if (playerMoveCounter % 2 == 1) {
                                board.completedPlayer1[playerOneScore][0] = row - 1;
                                board.completedPlayer1[playerOneScore][1] = column - 1;
                                board.completedPlayer1[playerOneScore][2] = row + 1;
                                board.completedPlayer1[playerOneScore][3] = column + 1;
                                playerOneScore++;
                            } else {
                                board.completedPlayer2[playerTwoScore][0] = row - 1;
                                board.completedPlayer2[playerTwoScore][1] = column - 1;
                                board.completedPlayer2[playerTwoScore][2] = row + 1;
                                board.completedPlayer2[playerTwoScore][3] = column + 1;
                                playerTwoScore++;
                            }
                            flag = true;
                        }
                        if (board.board[row - 1][column + 1] == 'S' && board.board[row + 1][column - 1] == 'S') {
                            if (playerMoveCounter % 2 == 1) {
                                board.completedPlayer1[playerOneScore][0] = row - 1;
                                board.completedPlayer1[playerOneScore][1] = column + 1;
                                board.completedPlayer1[playerOneScore][2] = row + 1;
                                board.completedPlayer1[playerOneScore][3] = column - 1;
                                playerOneScore++;
                            } else {
                                board.completedPlayer2[playerTwoScore][0] = row - 1;
                                board.completedPlayer2[playerTwoScore][1] = column + 1;
                                board.completedPlayer2[playerTwoScore][2] = row + 1;
                                board.completedPlayer2[playerTwoScore][3] = column - 1;
                                playerTwoScore++;
                            }
                            flag = true;
                        }
                        if (board.board[row][column - 1] == 'S' && board.board[row][column + 1] == 'S') {
                            if (playerMoveCounter % 2 == 1) {
                                board.completedPlayer1[playerOneScore][0] = row;
                                board.completedPlayer1[playerOneScore][1] = column - 1;
                                board.completedPlayer1[playerOneScore][2] = row;
                                board.completedPlayer1[playerOneScore][3] = column + 1;
                                playerOneScore++;
                            } else {
                                board.completedPlayer2[playerTwoScore][0] = row;
                                board.completedPlayer2[playerTwoScore][1] = column - 1;
                                board.completedPlayer2[playerTwoScore][2] = row;
                                board.completedPlayer2[playerTwoScore][3] = column + 1;
                                playerTwoScore++;
                            }
                            flag = true;
                        }
                    }
                }
            }
        } else {
            if (row == 0 && column == 0) {
                if (row <= 7) {
                    if (board.board[row + 2][column] == 'S' && board.board[row + 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column <= 7) {
                    if (board.board[row][column + 2] == 'S' && board.board[row][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column <= 7) {
                    if (board.board[row + 2][column + 2] == 'S' && board.board[row + 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
            } else if (row == 9 && column == 0) {
                if (row >= 2) {
                    if (board.board[row - 2][column] == 'S' && board.board[row - 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column <= 7) {
                    if (board.board[row][column + 2] == 'S' && board.board[row][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column <= 7) {
                    if (board.board[row + 2][column + 2] == 'S' && board.board[row + 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
            } else if (row == 0 && column == 9) {
                if (row <= 7) {
                    if (board.board[row + 2][column] == 'S' && board.board[row + 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column >= 2) {
                    if (board.board[row][column - 2] == 'S' && board.board[row][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column >= 2) {
                    if (board.board[row + 2][column - 2] == 'S' && board.board[row + 1][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
            } else if (row == 9 && column == 9) {
                if (row >= 2) {
                    if (board.board[row - 2][column] == 'S' && board.board[row - 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column >= 2) {
                    if (board.board[row][column - 2] == 'S' && board.board[row][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2 && column >= 2) {
                    if (board.board[row - 2][column - 2] == 'S' && board.board[row - 1][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
            } else if (row == 0) {
                if (row <= 7) {
                    if (board.board[row + 2][column] == 'S' && board.board[row + 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column <= 7) {
                    if (board.board[row][column + 2] == 'S' && board.board[row][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column >= 2) {
                    if (board.board[row][column - 2] == 'S' && board.board[row][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column <= 7) {
                    if (board.board[row + 2][column + 2] == 'S' && board.board[row + 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column >= 2) {
                    if (board.board[row + 2][column - 2] == 'S' && board.board[row + 1][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
            } else if (row == 9) {
                if (row >= 2) {
                    if (board.board[row - 2][column] == 'S' && board.board[row - 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column <= 7) {
                    if (board.board[row][column + 2] == 'S' && board.board[row][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column >= 2) {
                    if (board.board[row][column - 2] == 'S' && board.board[row][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2 && column <= 7) {
                    if (board.board[row - 2][column + 2] == 'S' && board.board[row - 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2 && column >= 2) {
                    if (board.board[row - 2][column - 2] == 'S' && board.board[row - 1][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
            } else if (column == 0) {
                if (row <= 7) {
                    if (board.board[row + 2][column] == 'S' && board.board[row + 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2) {
                    if (board.board[row - 2][column] == 'S' && board.board[row - 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column <= 7) {
                    if (board.board[row][column + 2] == 'S' && board.board[row][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column <= 7) {
                    if (board.board[row + 2][column + 2] == 'S' && board.board[row + 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2 && column <= 7) {
                    if (board.board[row - 2][column + 2] == 'S' && board.board[row - 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
            } else if (column == 9) {
                if (row <= 7) {
                    if (board.board[row + 2][column] == 'S' && board.board[row + 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2) {
                    if (board.board[row - 2][column] == 'S' && board.board[row - 1][column] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column >= 2) {
                    if (board.board[row][column - 2] == 'S' && board.board[row][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column >= 2) {
                    if (board.board[row + 2][column - 2] == 'S' && board.board[row + 1][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2 && column >= 2) {
                    if (board.board[row - 2][column - 2] == 'S' && board.board[row - 1][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
            } else {
                if (row >= 2 && row <= 7) {
                    if (row >= 2) {
                        if (board.board[row - 2][column] == 'S' && board.board[row - 1][column] == 'O') {
                            if (playerMoveCounter % 2 == 1) {
                                board.completedPlayer1[playerOneScore][0] = row - 2;
                                board.completedPlayer1[playerOneScore][1] = column;
                                board.completedPlayer1[playerOneScore][2] = row;
                                board.completedPlayer1[playerOneScore][3] = column;
                                playerOneScore++;
                            } else {
                                board.completedPlayer2[playerTwoScore][0] = row - 2;
                                board.completedPlayer2[playerTwoScore][1] = column;
                                board.completedPlayer2[playerTwoScore][2] = row;
                                board.completedPlayer2[playerTwoScore][3] = column;
                                playerTwoScore++;
                            }
                            flag = true;
                        }
                    }
                    if (row <= 7) {
                        if (board.board[row + 2][column] == 'S' && board.board[row + 1][column] == 'O') {
                            if (playerMoveCounter % 2 == 1) {
                                board.completedPlayer1[playerOneScore][0] = row + 2;
                                board.completedPlayer1[playerOneScore][1] = column;
                                board.completedPlayer1[playerOneScore][2] = row;
                                board.completedPlayer1[playerOneScore][3] = column;
                                playerOneScore++;
                            } else {
                                board.completedPlayer2[playerTwoScore][0] = row + 2;
                                board.completedPlayer2[playerTwoScore][1] = column;
                                board.completedPlayer2[playerTwoScore][2] = row;
                                board.completedPlayer2[playerTwoScore][3] = column;
                                playerTwoScore++;
                            }
                            flag = true;
                        }
                    }
                }
                if (column >= 2) {
                    if (board.board[row][column - 2] == 'S' && board.board[row][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (column <= 7) {
                    if (board.board[row][column + 2] == 'S' && board.board[row][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2 && column <= 7) {
                    if (board.board[row - 2][column + 2] == 'S' && board.board[row - 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column <= 7) {
                    if (board.board[row + 2][column + 2] == 'S' && board.board[row + 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2 && column >= 2) {
                    if (board.board[row - 2][column - 2] == 'S' && board.board[row - 1][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column >= 2) {
                    if (board.board[row + 2][column - 2] == 'S' && board.board[row + 1][column - 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column - 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column - 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row >= 2 && column <= 7) {
                    if (board.board[row - 2][column + 2] == 'S' && board.board[row - 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row - 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row - 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
                if (row <= 7 && column <= 7) {
                    if (board.board[row + 2][column + 2] == 'S' && board.board[row + 1][column + 1] == 'O') {
                        if (playerMoveCounter % 2 == 1) {
                            board.completedPlayer1[playerOneScore][0] = row + 2;
                            board.completedPlayer1[playerOneScore][1] = column + 2;
                            board.completedPlayer1[playerOneScore][2] = row;
                            board.completedPlayer1[playerOneScore][3] = column;
                            playerOneScore++;
                        } else {
                            board.completedPlayer2[playerTwoScore][0] = row + 2;
                            board.completedPlayer2[playerTwoScore][1] = column + 2;
                            board.completedPlayer2[playerTwoScore][2] = row;
                            board.completedPlayer2[playerTwoScore][3] = column;
                            playerTwoScore++;
                        }
                        flag = true;
                    }
                }
            }
        }
        if (flag == true) {
            scoreplayerMove1.setText("PLAYER 1 SCORE : " + playerOneScore);
            scoreplayerMove2.setText("PLAYER 2 SCORE : " + playerTwoScore);
            return true;
        }
        checkIfGameCompleted();
        return false;
    }

    void checkIfGameCompleted() {
        for (int rowCounter = 0; rowCounter < 10; rowCounter++) {
            for (int columnCounter = 0; columnCounter < 10; columnCounter++) {
                if (board.board[rowCounter][columnCounter] == '\0') {
                    return;
                }
            }
        }
        if (playerOneScore > playerTwoScore) {
            status.setText("PLAYER " + String.valueOf(1) + " WON");
        } else if (playerOneScore < playerTwoScore) {
            status.setText("PLAYER " + String.valueOf(2) + " WON");
        } else {
            status.setText("MATCH IS TIE");
        }
    }

    public static void main(String args[]) {
        new SosGame();
    }
}

class gameBoard extends Applet {
    public int completedPlayer1[][] = new int[150][4], completedPlayer2[][] = new int[150][4];
    public char board[][] = new char[10][10];
    public int cellPosX, cellPosY;
    boolean isChanged;

    public void init() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '\0';
            }
        }
        for (int i = 0; i < 150; i++) {
            completedPlayer1[i][0] = -1;
            completedPlayer2[i][0] = -1;
        }
    }

    public void paint(Graphics g) {
        for (int i = 0; i <= 10; i++) {
            g.drawLine(50 + i * 50, 100, 50 + i * 50, 600);
            g.drawLine(50, 100 + i * 50, 550, 100 + i * 50);
        }
        g.setFont(new Font("Arial", Font.BOLD, 30));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                String str = (board[i][j] == 'S' || board[i][j] == 'O') ? String.valueOf(board[i][j]) : "";
                if (str.equals("S")) {
                    g.drawString(str, 65 + i * 50, 140 + j * 50);
                } else if (str.equals("O")) {
                    g.drawString(str, 60 + i * 50, 140 + j * 50);
                }
            }
        }
        g.setColor(Color.GRAY);
        if (isChanged) {
            out: for (int i = 0; i < 10; i++) {
                if (cellPosX == i) {
                    for (int j = 0; j < 10; j++) {
                        if (cellPosY == j) {
                            g.fillRect(50 + i * 50, 100 + j * 50, 50, 50);
                            break out;
                        }
                    }
                }
                isChanged = false;
            }
        }
        g.setColor(Color.RED);
        out1: for (int i = 0; i < 150; i++) {
            if (completedPlayer1[i][0] != -1) {
                g.drawLine(75 + completedPlayer1[i][0] * 50, 125 + completedPlayer1[i][1] * 50,
                        75 + completedPlayer1[i][2] * 50, 125 + completedPlayer1[i][3] * 50);
            } else {
                break out1;
            }
        }
        g.setColor(Color.BLUE);
        out2: for (int i = 0; i < 150; i++) {
            if (completedPlayer2[i][0] != -1) {
                g.drawLine(75 + completedPlayer2[i][0] * 50, 125 + completedPlayer2[i][1] * 50,
                        75 + completedPlayer2[i][2] * 50, 125 + completedPlayer2[i][3] * 50);
            } else {
                break out2;
            }
        }
        g.setColor(Color.BLACK);
        g.drawRect(600, 70, 300, 440);
    }
}
