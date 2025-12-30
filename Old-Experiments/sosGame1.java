
/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class sosGame
        extends Frame
        implements MouseListener {
    gameBoard board;
    Button closeButton;
    Button playAgain;
    Button sButton;
    Button oButton;
    Label gameTitle;
    Label scoresTitle;
    Label scoreplayerMove1;
    Label scoreplayerMove2;
    Label move;
    Label status;
    int player1Score;
    int player2Score;
    int playerMove;
    Dialog popupMenu;

    sosGame() {
        this.setLayout(null);
        this.player1Score = 0;
        this.player2Score = 0;
        this.playerMove = 1;
        this.gameTitle = new Label("SOS GAME");
        this.gameTitle.setBounds(300, 50, 350, 50);
        this.add(this.gameTitle);
        this.status = new Label();
        this.status.setBounds(650, 675, 300, 40);
        this.add(this.status);
        this.gameTitle.setFont(new Font("Arial", 1, 50));
        this.scoresTitle = new Label("SCORES");
        this.scoreplayerMove1 = new Label("PLAYER 1 SCORE : " + this.player1Score);
        this.scoreplayerMove2 = new Label("PLAYER 2 SCORE : " + this.player2Score);
        this.move = new Label("PLAYER 1 MOVE");
        this.scoresTitle.setBounds(750, 200, 100, 50);
        this.scoreplayerMove1.setBounds(675, 250, 275, 50);
        this.scoreplayerMove2.setBounds(675, 300, 275, 50);
        this.move.setBounds(700, 500, 200, 100);
        Font font = new Font("Arial", 1, 20);
        this.scoresTitle.setFont(font);
        this.scoreplayerMove1.setFont(font);
        this.scoreplayerMove2.setFont(font);
        this.status.setFont(font);
        this.move.setFont(font);
        this.add(this.scoresTitle);
        this.add(this.scoreplayerMove1);
        this.add(this.scoreplayerMove2);
        this.add(this.move);
        this.board = new gameBoard();
        this.board.setBounds(50, 100, 950, 700);
        this.board.init();
        this.board.start();
        this.board.addMouseListener(this);
        this.closeButton = new Button("CLOSE GAME");
        this.playAgain = new Button("PLAY AGAIN");
        this.closeButton.addMouseListener(this);
        this.playAgain.addMouseListener(this);
        this.closeButton.setFont(font);
        this.playAgain.setFont(font);
        this.playAgain.setBounds(700, 375, 200, 50);
        this.closeButton.setBounds(700, 440, 200, 50);
        this.add(this.playAgain);
        this.add(this.closeButton);
        this.add(this.board);
        this.setVisible(true);
        this.setSize(1200, 1200);
        this.popupMenu = new Dialog(this, "SELECT OPTION", true);
        this.popupMenu.setSize(100, 80);
        this.popupMenu.setLayout(null);
        this.sButton = new Button("S");
        this.oButton = new Button("O");
        this.sButton.setBounds(20, 40, 30, 30);
        this.oButton.setBounds(60, 40, 30, 30);
        this.sButton.setBackground(Color.WHITE);
        this.oButton.setBackground(Color.WHITE);
        this.popupMenu.add(this.sButton);
        this.popupMenu.add(this.oButton);
        Label label = new Label("CHOOSE S OR O");
        label.setBounds(0, 0, 100, 20);
        label.setForeground(Color.WHITE);
        this.popupMenu.add(label);
        this.sButton.addMouseListener(this);
        this.oButton.addMouseListener(this);
        this.popupMenu.setBackground(Color.GRAY);
        this.popupMenu.setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == this.closeButton) {
            System.exit(0);
        } else if (mouseEvent.getSource() == this.playAgain) {
            this.playAgain();
        } else if (mouseEvent.getSource() == this.sButton) {
            this.board.board[this.board.cellPosX][this.board.cellPosY] = 83;
            this.board.isChanged = false;
            this.board.repaint();
            this.popupMenu.setVisible(false);
            boolean bl = this.checkPoint(this.board.cellPosX, this.board.cellPosY);
            if (!bl) {
                ++this.playerMove;
                this.move.setText("PLAYER " + String.valueOf(this.playerMove % 2 == 0 ? 2 : 1) + " MOVE");
            }
        } else if (mouseEvent.getSource() == this.oButton) {
            this.board.board[this.board.cellPosX][this.board.cellPosY] = 79;
            this.board.isChanged = false;
            this.board.repaint();
            this.popupMenu.setVisible(false);
            boolean bl = this.checkPoint(this.board.cellPosX, this.board.cellPosY);
            if (!bl) {
                ++this.playerMove;
                this.move.setText("PLAYER " + String.valueOf(this.playerMove % 2 == 0 ? 2 : 1) + " MOVE");
            }
        } else {
            int n = mouseEvent.getX();
            int n2 = mouseEvent.getY();
            for (int i = 0; i < 10; ++i) {
                for (int j = 0; j < 10; ++j) {
                    if (n < 50 + i * 50 || n > 50 + (i + 1) * 50 || n2 < 100 + j * 50 || n2 > 100 + (j + 1) * 50)
                        continue;
                    this.board.cellPosX = i;
                    this.board.cellPosY = j;
                    this.board.isChanged = true;
                    if (this.board.board[this.board.cellPosX][this.board.cellPosY] != '\u0000')
                        continue;
                    this.board.repaint();
                    this.popupMenu.setLocation(50 + i * 50, 100 + j * 50);
                    this.popupMenu.setVisible(true);
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    void playAgain() {
        this.board.init();
        this.board.repaint();
        this.player1Score = 0;
        this.player2Score = 0;
        this.playerMove = 1;
        this.move.setText("PLAYER 1 MOVE");
        this.scoreplayerMove1.setText("PLAYER 1 SCORE : " + this.player1Score);
        this.scoreplayerMove2.setText("PLAYER 2 SCORE : " + this.player2Score);
        this.status.setText("");
    }

    boolean checkPoint(int n, int n2) {
        boolean bl = false;
        if (this.board.board[n][n2] == 'O') {
            if (n == 0 && n2 == 0 || n == 9 && n2 == 0 || n == 0 && n2 == 9 || n == 9 && n2 == 9) {
                return false;
            }
            if (n == 0 || n == 9) {
                if (n2 >= 1 && n2 <= 8 && this.board.board[n][n2 - 1] == 'S' && this.board.board[n][n2 + 1] == 'S') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.player1Score][0] = n;
                        this.board.completedPlayer1[this.player1Score][1] = n2 - 1;
                        this.board.completedPlayer1[this.player1Score][2] = n;
                        this.board.completedPlayer1[this.player1Score][3] = n2 + 1;
                        ++this.player1Score;
                    } else {
                        this.board.completedPlayer2[this.player2Score][0] = n;
                        this.board.completedPlayer2[this.player2Score][1] = n2 - 1;
                        this.board.completedPlayer2[this.player2Score][2] = n;
                        this.board.completedPlayer2[this.player2Score][3] = n2 + 1;
                        ++this.player2Score;
                    }
                    bl = true;
                }
            } else if ((n2 == 0 || n2 == 9) && n >= 1 && n <= 8) {
                if (this.board.board[n - 1][n2] == 'S' && this.board.board[n + 1][n2] == 'S') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.player1Score][0] = n - 1;
                        this.board.completedPlayer1[this.player1Score][1] = n2;
                        this.board.completedPlayer1[this.player1Score][2] = n + 1;
                        this.board.completedPlayer1[this.player1Score][3] = n2;
                        ++this.player1Score;
                    } else {
                        this.board.completedPlayer2[this.player2Score][0] = n - 1;
                        this.board.completedPlayer2[this.player2Score][1] = n2;
                        this.board.completedPlayer2[this.player2Score][2] = n + 1;
                        this.board.completedPlayer2[this.player2Score][3] = n2;
                        ++this.player2Score;
                    }
                    bl = true;
                }
            } else {
                if (n >= 1 && n <= 8 && this.board.board[n - 1][n2] == 'S' && this.board.board[n + 1][n2] == 'S') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.player1Score][0] = n - 1;
                        this.board.completedPlayer1[this.player1Score][1] = n2;
                        this.board.completedPlayer1[this.player1Score][2] = n + 1;
                        this.board.completedPlayer1[this.player1Score][3] = n2;
                        ++this.player1Score;
                    } else {
                        this.board.completedPlayer2[this.player2Score][0] = n - 1;
                        this.board.completedPlayer2[this.player2Score][1] = n2;
                        this.board.completedPlayer2[this.player2Score][2] = n + 1;
                        this.board.completedPlayer2[this.player2Score][3] = n2;
                        ++this.player2Score;
                    }
                    bl = true;
                }
                if (n >= 1 && n <= 8 && n2 >= 1 && n2 <= 8) {
                    if (this.board.board[n - 1][n2 - 1] == 'S' && this.board.board[n + 1][n2 + 1] == 'S') {
                        if (this.playerMove % 2 == 1) {
                            this.board.completedPlayer1[this.player1Score][0] = n - 1;
                            this.board.completedPlayer1[this.player1Score][1] = n2 - 1;
                            this.board.completedPlayer1[this.player1Score][2] = n + 1;
                            this.board.completedPlayer1[this.player1Score][3] = n2 + 1;
                            ++this.player1Score;
                        } else {
                            this.board.completedPlayer2[this.player2Score][0] = n - 1;
                            this.board.completedPlayer2[this.player2Score][1] = n2 - 1;
                            this.board.completedPlayer2[this.player2Score][2] = n + 1;
                            this.board.completedPlayer2[this.player2Score][3] = n2 + 1;
                            ++this.player2Score;
                        }
                        bl = true;
                    }
                    if (this.board.board[n - 1][n2 + 1] == 'S' && this.board.board[n + 1][n2 - 1] == 'S') {
                        if (this.playerMove % 2 == 1) {
                            this.board.completedPlayer1[this.player1Score][0] = n - 1;
                            this.board.completedPlayer1[this.player1Score][1] = n2 + 1;
                            this.board.completedPlayer1[this.player1Score][2] = n + 1;
                            this.board.completedPlayer1[this.player1Score][3] = n2 - 1;
                            ++this.player1Score;
                        } else {
                            this.board.completedPlayer2[this.player2Score][0] = n - 1;
                            this.board.completedPlayer2[this.player2Score][1] = n2 + 1;
                            this.board.completedPlayer2[this.player2Score][2] = n + 1;
                            this.board.completedPlayer2[this.player2Score][3] = n2 - 1;
                            ++this.player2Score;
                        }
                        bl = true;
                    }
                    if (this.board.board[n][n2 - 1] == 'S' && this.board.board[n][n2 + 1] == 'S') {
                        if (this.playerMove % 2 == 1) {
                            this.board.completedPlayer1[this.player1Score][0] = n;
                            this.board.completedPlayer1[this.player1Score][1] = n2 - 1;
                            this.board.completedPlayer1[this.player1Score][2] = n;
                            this.board.completedPlayer1[this.player1Score][3] = n2 + 1;
                            ++this.player1Score;
                        } else {
                            this.board.completedPlayer2[this.player2Score][0] = n;
                            this.board.completedPlayer2[this.player2Score][1] = n2 - 1;
                            this.board.completedPlayer2[this.player2Score][2] = n;
                            this.board.completedPlayer2[this.player2Score][3] = n2 + 1;
                            ++this.player2Score;
                        }
                        bl = true;
                    }
                }
            }
        } else if (n == 0 && n2 == 0) {
            if (n <= 7 && this.board.board[n + 2][n2] == 'S' && this.board.board[n + 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 <= 7 && this.board.board[n][n2 + 2] == 'S' && this.board.board[n][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 <= 7 && this.board.board[n + 2][n2 + 2] == 'S' && this.board.board[n + 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
        } else if (n == 9 && n2 == 0) {
            if (n >= 2 && this.board.board[n - 2][n2] == 'S' && this.board.board[n - 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 <= 7 && this.board.board[n][n2 + 2] == 'S' && this.board.board[n][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 <= 7 && this.board.board[n + 2][n2 + 2] == 'S' && this.board.board[n + 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
        } else if (n == 0 && n2 == 9) {
            if (n <= 7 && this.board.board[n + 2][n2] == 'S' && this.board.board[n + 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 >= 2 && this.board.board[n][n2 - 2] == 'S' && this.board.board[n][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 >= 2 && this.board.board[n + 2][n2 - 2] == 'S' && this.board.board[n + 1][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
        } else if (n == 9 && n2 == 9) {
            if (n >= 2 && this.board.board[n - 2][n2] == 'S' && this.board.board[n - 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 >= 2 && this.board.board[n][n2 - 2] == 'S' && this.board.board[n][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && n2 >= 2 && this.board.board[n - 2][n2 - 2] == 'S' && this.board.board[n - 1][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
        } else if (n == 0) {
            if (n <= 7 && this.board.board[n + 2][n2] == 'S' && this.board.board[n + 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 <= 7 && this.board.board[n][n2 + 2] == 'S' && this.board.board[n][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 >= 2 && this.board.board[n][n2 - 2] == 'S' && this.board.board[n][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 <= 7 && this.board.board[n + 2][n2 + 2] == 'S' && this.board.board[n + 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 >= 2 && this.board.board[n + 2][n2 - 2] == 'S' && this.board.board[n + 1][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
        } else if (n == 9) {
            if (n >= 2 && this.board.board[n - 2][n2] == 'S' && this.board.board[n - 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 <= 7 && this.board.board[n][n2 + 2] == 'S' && this.board.board[n][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 >= 2 && this.board.board[n][n2 - 2] == 'S' && this.board.board[n][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && n2 <= 7 && this.board.board[n - 2][n2 + 2] == 'S' && this.board.board[n - 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && n2 >= 2 && this.board.board[n - 2][n2 - 2] == 'S' && this.board.board[n - 1][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
        } else if (n2 == 0) {
            if (n <= 7 && this.board.board[n + 2][n2] == 'S' && this.board.board[n + 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && this.board.board[n - 2][n2] == 'S' && this.board.board[n - 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 <= 7 && this.board.board[n][n2 + 2] == 'S' && this.board.board[n][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 <= 7 && this.board.board[n + 2][n2 + 2] == 'S' && this.board.board[n + 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && n2 <= 7 && this.board.board[n - 2][n2 + 2] == 'S' && this.board.board[n - 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
        } else if (n2 == 9) {
            if (n <= 7 && this.board.board[n + 2][n2] == 'S' && this.board.board[n + 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && this.board.board[n - 2][n2] == 'S' && this.board.board[n - 1][n2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 >= 2 && this.board.board[n][n2 - 2] == 'S' && this.board.board[n][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 >= 2 && this.board.board[n + 2][n2 - 2] == 'S' && this.board.board[n + 1][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && n2 >= 2 && this.board.board[n - 2][n2 - 2] == 'S' && this.board.board[n - 1][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
        } else {
            if (n >= 2 && n <= 7) {
                if (n >= 2 && this.board.board[n - 2][n2] == 'S' && this.board.board[n - 1][n2] == 'O') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.player1Score][0] = n - 2;
                        this.board.completedPlayer1[this.player1Score][1] = n2;
                        this.board.completedPlayer1[this.player1Score][2] = n;
                        this.board.completedPlayer1[this.player1Score][3] = n2;
                        ++this.player1Score;
                    } else {
                        this.board.completedPlayer2[this.player2Score][0] = n - 2;
                        this.board.completedPlayer2[this.player2Score][1] = n2;
                        this.board.completedPlayer2[this.player2Score][2] = n;
                        this.board.completedPlayer2[this.player2Score][3] = n2;
                        ++this.player2Score;
                    }
                    bl = true;
                }
                if (n <= 7 && this.board.board[n + 2][n2] == 'S' && this.board.board[n + 1][n2] == 'O') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.player1Score][0] = n + 2;
                        this.board.completedPlayer1[this.player1Score][1] = n2;
                        this.board.completedPlayer1[this.player1Score][2] = n;
                        this.board.completedPlayer1[this.player1Score][3] = n2;
                        ++this.player1Score;
                    } else {
                        this.board.completedPlayer2[this.player2Score][0] = n + 2;
                        this.board.completedPlayer2[this.player2Score][1] = n2;
                        this.board.completedPlayer2[this.player2Score][2] = n;
                        this.board.completedPlayer2[this.player2Score][3] = n2;
                        ++this.player2Score;
                    }
                    bl = true;
                }
            }
            if (n2 >= 2 && this.board.board[n][n2 - 2] == 'S' && this.board.board[n][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n2 <= 7 && this.board.board[n][n2 + 2] == 'S' && this.board.board[n][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && n2 <= 7 && this.board.board[n - 2][n2 + 2] == 'S' && this.board.board[n - 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 <= 7 && this.board.board[n + 2][n2 + 2] == 'S' && this.board.board[n + 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && n2 >= 2 && this.board.board[n - 2][n2 - 2] == 'S' && this.board.board[n - 1][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 >= 2 && this.board.board[n + 2][n2 - 2] == 'S' && this.board.board[n + 1][n2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 - 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 - 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n >= 2 && n2 <= 7 && this.board.board[n - 2][n2 + 2] == 'S' && this.board.board[n - 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n - 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n - 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
            if (n <= 7 && n2 <= 7 && this.board.board[n + 2][n2 + 2] == 'S' && this.board.board[n + 1][n2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.player1Score][0] = n + 2;
                    this.board.completedPlayer1[this.player1Score][1] = n2 + 2;
                    this.board.completedPlayer1[this.player1Score][2] = n;
                    this.board.completedPlayer1[this.player1Score][3] = n2;
                    ++this.player1Score;
                } else {
                    this.board.completedPlayer2[this.player2Score][0] = n + 2;
                    this.board.completedPlayer2[this.player2Score][1] = n2 + 2;
                    this.board.completedPlayer2[this.player2Score][2] = n;
                    this.board.completedPlayer2[this.player2Score][3] = n2;
                    ++this.player2Score;
                }
                bl = true;
            }
        }
        if (bl) {
            this.scoreplayerMove1.setText("PLAYER 1 SCORE : " + this.player1Score);
            this.scoreplayerMove2.setText("PLAYER 2 SCORE : " + this.player2Score);
            return true;
        }
        this.checkIfGameCompleted();
        return false;
    }

    void checkIfGameCompleted() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (this.board.board[i][j] != '\u0000')
                    continue;
                return;
            }
        }
        if (this.player1Score > this.player2Score) {
            this.status.setText("PLAYER " + String.valueOf(1) + " WON");
        } else if (this.player1Score < this.player2Score) {
            this.status.setText("PLAYER " + String.valueOf(2) + " WON");
        } else {
            this.status.setText("MATCH IS TIE");
        }
    }

    public static void main(String[] stringArray) {
        new sosGame();
    }
}
