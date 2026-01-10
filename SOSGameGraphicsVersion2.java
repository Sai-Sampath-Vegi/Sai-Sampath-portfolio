import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SOSGameGraphicsVersion2 extends Frame implements MouseListener {
    GameBoard board;
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
    int playerOneScore;
    int playerTwoScore;
    int playerMove;
    Dialog popupMenu;

    SOSGameGraphicsVersion2() {
        this.setTitle("SOS Game");
        this.setLayout(null);
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
        this.playerMove = 1;

        this.gameTitle = new Label("SOS GAME");
        this.gameTitle.setBounds(300, 100, 350, 50);
        this.add(this.gameTitle);
        this.status = new Label();
        this.status.setBounds(650, 725, 300, 40);
        this.add(this.status);

        this.gameTitle.setFont(new Font("Arial", 1, 50));
        this.scoresTitle = new Label("SCORES");
        this.scoreplayerMove1 = new Label("PLAYER 1 SCORE : " + this.playerOneScore);
        this.scoreplayerMove2 = new Label("PLAYER 2 SCORE : " + this.playerTwoScore);
        this.move = new Label("PLAYER 1 MOVE");
        this.scoresTitle.setBounds(750, 250, 100, 50);
        this.scoreplayerMove1.setBounds(675, 300, 250, 50);
        this.scoreplayerMove2.setBounds(675, 350, 250, 50);
        this.move.setBounds(700, 550, 200, 100);
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

        this.board = new GameBoard();
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
        this.playAgain.setBounds(700, 425, 200, 50);
        this.closeButton.setBounds(700, 490, 200, 50);
        this.add(this.playAgain);
        this.add(this.closeButton);
        this.add(this.board);

        this.setVisible(true);
        this.setSize(1000, 800);
        this.setResizable(false);
        setLocation(0, 0);

        this.popupMenu = new Dialog(this, "CHOOSE S OR O", true);
        this.popupMenu.setSize(200, 80);
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
        label.setBounds(0, 0, 150, 20);
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

            boolean isCurrentPlayerScoredPoint = this.checkPoint(this.board.cellPosX, this.board.cellPosY);

            if (!isCurrentPlayerScoredPoint) {
                ++this.playerMove;
                this.move.setText("PLAYER " + String.valueOf(this.playerMove % 2 == 0 ? 2 : 1) + " MOVE");
            }
        } else if (mouseEvent.getSource() == this.oButton) {
            this.board.board[this.board.cellPosX][this.board.cellPosY] = 79;
            this.board.isChanged = false;
            this.board.repaint();
            this.popupMenu.setVisible(false);

            boolean isCurrentPlayerScoredPoint = this.checkPoint(this.board.cellPosX, this.board.cellPosY);

            if (!isCurrentPlayerScoredPoint) {
                ++this.playerMove;
                this.move.setText("PLAYER " + String.valueOf(this.playerMove % 2 == 0 ? 2 : 1) + " MOVE");
            }
        } else {
            int n = mouseEvent.getX();
            int n2 = mouseEvent.getY();

            for (int i = 0; i < 10; ++i) {
                for (int j = 0; j < 10; ++j) {
                    if (n < 50 + i * 50 || n > 50 + (i + 1) * 50 || n2 < 100 + j * 50 || n2 > 100 + (j + 1) * 50) {
                        continue;
                    }

                    this.board.cellPosX = i;
                    this.board.cellPosY = j;
                    this.board.isChanged = true;

                    if (this.board.board[this.board.cellPosX][this.board.cellPosY] != '\u0000') {
                        continue;
                    }

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
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
        this.playerMove = 1;
        this.move.setText("PLAYER 1 MOVE");
        this.scoreplayerMove1.setText("PLAYER 1 SCORE : " + this.playerOneScore);
        this.scoreplayerMove2.setText("PLAYER 2 SCORE : " + this.playerTwoScore);
        this.status.setText("");
    }

    boolean checkPoint(int position1, int position2) {
        boolean isCurrentPlayerScoredPoint = false;
        if (this.board.board[position1][position2] == 'O') {
            if (position1 == 0 && position2 == 0 || position1 == 9 && position2 == 0 || position1 == 0 && position2 == 9
                    || position1 == 9 && position2 == 9) {
                return false;
            }

            if (position1 == 0 || position1 == 9) {
                if (position2 >= 1 && position2 <= 8 && this.board.board[position1][position2 - 1] == 'S'
                        && this.board.board[position1][position2 + 1] == 'S') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.playerOneScore][0] = position1;
                        this.board.completedPlayer1[this.playerOneScore][1] = position2 - 1;
                        this.board.completedPlayer1[this.playerOneScore][2] = position1;
                        this.board.completedPlayer1[this.playerOneScore][3] = position2 + 1;
                        ++this.playerOneScore;
                    } else {
                        this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                        this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 1;
                        this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                        this.board.completedPlayer2[this.playerTwoScore][3] = position2 + 1;
                        ++this.playerTwoScore;
                    }
                    isCurrentPlayerScoredPoint = true;
                }
            } else if ((position2 == 0 || position2 == 9) && position1 >= 1 && position1 <= 8) {
                if (this.board.board[position1 - 1][position2] == 'S'
                        && this.board.board[position1 + 1][position2] == 'S') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.playerOneScore][0] = position1 - 1;
                        this.board.completedPlayer1[this.playerOneScore][1] = position2;
                        this.board.completedPlayer1[this.playerOneScore][2] = position1 + 1;
                        this.board.completedPlayer1[this.playerOneScore][3] = position2;
                        ++this.playerOneScore;
                    } else {
                        this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 1;
                        this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                        this.board.completedPlayer2[this.playerTwoScore][2] = position1 + 1;
                        this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                        ++this.playerTwoScore;
                    }
                    isCurrentPlayerScoredPoint = true;
                }
            } else {
                if (position1 >= 1 && position1 <= 8 && this.board.board[position1 - 1][position2] == 'S'
                        && this.board.board[position1 + 1][position2] == 'S') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.playerOneScore][0] = position1 - 1;
                        this.board.completedPlayer1[this.playerOneScore][1] = position2;
                        this.board.completedPlayer1[this.playerOneScore][2] = position1 + 1;
                        this.board.completedPlayer1[this.playerOneScore][3] = position2;
                        ++this.playerOneScore;
                    } else {
                        this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 1;
                        this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                        this.board.completedPlayer2[this.playerTwoScore][2] = position1 + 1;
                        this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                        ++this.playerTwoScore;
                    }
                    isCurrentPlayerScoredPoint = true;
                }

                if (position1 >= 1 && position1 <= 8 && position2 >= 1 && position2 <= 8) {
                    if (this.board.board[position1 - 1][position2 - 1] == 'S'
                            && this.board.board[position1 + 1][position2 + 1] == 'S') {
                        if (this.playerMove % 2 == 1) {
                            this.board.completedPlayer1[this.playerOneScore][0] = position1 - 1;
                            this.board.completedPlayer1[this.playerOneScore][1] = position2 - 1;
                            this.board.completedPlayer1[this.playerOneScore][2] = position1 + 1;
                            this.board.completedPlayer1[this.playerOneScore][3] = position2 + 1;
                            ++this.playerOneScore;
                        } else {
                            this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 1;
                            this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 1;
                            this.board.completedPlayer2[this.playerTwoScore][2] = position1 + 1;
                            this.board.completedPlayer2[this.playerTwoScore][3] = position2 + 1;
                            ++this.playerTwoScore;
                        }
                        isCurrentPlayerScoredPoint = true;
                    }

                    if (this.board.board[position1 - 1][position2 + 1] == 'S'
                            && this.board.board[position1 + 1][position2 - 1] == 'S') {
                        if (this.playerMove % 2 == 1) {
                            this.board.completedPlayer1[this.playerOneScore][0] = position1 - 1;
                            this.board.completedPlayer1[this.playerOneScore][1] = position2 + 1;
                            this.board.completedPlayer1[this.playerOneScore][2] = position1 + 1;
                            this.board.completedPlayer1[this.playerOneScore][3] = position2 - 1;
                            ++this.playerOneScore;
                        } else {
                            this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 1;
                            this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 1;
                            this.board.completedPlayer2[this.playerTwoScore][2] = position1 + 1;
                            this.board.completedPlayer2[this.playerTwoScore][3] = position2 - 1;
                            ++this.playerTwoScore;
                        }
                        isCurrentPlayerScoredPoint = true;
                    }

                    if (this.board.board[position1][position2 - 1] == 'S'
                            && this.board.board[position1][position2 + 1] == 'S') {
                        if (this.playerMove % 2 == 1) {
                            this.board.completedPlayer1[this.playerOneScore][0] = position1;
                            this.board.completedPlayer1[this.playerOneScore][1] = position2 - 1;
                            this.board.completedPlayer1[this.playerOneScore][2] = position1;
                            this.board.completedPlayer1[this.playerOneScore][3] = position2 + 1;
                            ++this.playerOneScore;
                        } else {
                            this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                            this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 1;
                            this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                            this.board.completedPlayer2[this.playerTwoScore][3] = position2 + 1;
                            ++this.playerTwoScore;
                        }
                        isCurrentPlayerScoredPoint = true;
                    }
                }
            }
        } else if (position1 == 0 && position2 == 0) {
            if (position1 <= 7 && this.board.board[position1 + 2][position2] == 'S'
                    && this.board.board[position1 + 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 <= 7 && this.board.board[position1][position2 + 2] == 'S'
                    && this.board.board[position1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 <= 7 && this.board.board[position1 + 2][position2 + 2] == 'S'
                    && this.board.board[position1 + 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }
        } else if (position1 == 9 && position2 == 0) {
            if (position1 >= 2 && this.board.board[position1 - 2][position2] == 'S'
                    && this.board.board[position1 - 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 <= 7 && this.board.board[position1][position2 + 2] == 'S'
                    && this.board.board[position1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 <= 7 && this.board.board[position1 + 2][position2 + 2] == 'S'
                    && this.board.board[position1 + 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }
        } else if (position1 == 0 && position2 == 9) {
            if (position1 <= 7 && this.board.board[position1 + 2][position2] == 'S'
                    && this.board.board[position1 + 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 >= 2 && this.board.board[position1][position2 - 2] == 'S'
                    && this.board.board[position1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 >= 2 && this.board.board[position1 + 2][position2 - 2] == 'S'
                    && this.board.board[position1 + 1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }
        } else if (position1 == 9 && position2 == 9) {
            if (position1 >= 2 && this.board.board[position1 - 2][position2] == 'S'
                    && this.board.board[position1 - 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 >= 2 && this.board.board[position1][position2 - 2] == 'S'
                    && this.board.board[position1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && position2 >= 2 && this.board.board[position1 - 2][position2 - 2] == 'S'
                    && this.board.board[position1 - 1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }
        } else if (position1 == 0) {
            if (position1 <= 7 && this.board.board[position1 + 2][position2] == 'S'
                    && this.board.board[position1 + 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 <= 7 && this.board.board[position1][position2 + 2] == 'S'
                    && this.board.board[position1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 >= 2 && this.board.board[position1][position2 - 2] == 'S'
                    && this.board.board[position1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 <= 7 && this.board.board[position1 + 2][position2 + 2] == 'S'
                    && this.board.board[position1 + 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 >= 2 && this.board.board[position1 + 2][position2 - 2] == 'S'
                    && this.board.board[position1 + 1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }
        } else if (position1 == 9) {
            if (position1 >= 2 && this.board.board[position1 - 2][position2] == 'S'
                    && this.board.board[position1 - 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 <= 7 && this.board.board[position1][position2 + 2] == 'S'
                    && this.board.board[position1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 >= 2 && this.board.board[position1][position2 - 2] == 'S'
                    && this.board.board[position1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && position2 <= 7 && this.board.board[position1 - 2][position2 + 2] == 'S'
                    && this.board.board[position1 - 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && position2 >= 2 && this.board.board[position1 - 2][position2 - 2] == 'S'
                    && this.board.board[position1 - 1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }
        } else if (position2 == 0) {
            if (position1 <= 7 && this.board.board[position1 + 2][position2] == 'S'
                    && this.board.board[position1 + 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && this.board.board[position1 - 2][position2] == 'S'
                    && this.board.board[position1 - 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 <= 7 && this.board.board[position1][position2 + 2] == 'S'
                    && this.board.board[position1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 <= 7 && this.board.board[position1 + 2][position2 + 2] == 'S'
                    && this.board.board[position1 + 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && position2 <= 7 && this.board.board[position1 - 2][position2 + 2] == 'S'
                    && this.board.board[position1 - 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }
        } else if (position2 == 9) {
            if (position1 <= 7 && this.board.board[position1 + 2][position2] == 'S'
                    && this.board.board[position1 + 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && this.board.board[position1 - 2][position2] == 'S'
                    && this.board.board[position1 - 1][position2] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 >= 2 && this.board.board[position1][position2 - 2] == 'S'
                    && this.board.board[position1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 >= 2 && this.board.board[position1 + 2][position2 - 2] == 'S'
                    && this.board.board[position1 + 1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && position2 >= 2 && this.board.board[position1 - 2][position2 - 2] == 'S'
                    && this.board.board[position1 - 1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }
        } else {
            if (position1 >= 2 && position1 <= 7) {
                if (position1 >= 2 && this.board.board[position1 - 2][position2] == 'S'
                        && this.board.board[position1 - 1][position2] == 'O') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                        this.board.completedPlayer1[this.playerOneScore][1] = position2;
                        this.board.completedPlayer1[this.playerOneScore][2] = position1;
                        this.board.completedPlayer1[this.playerOneScore][3] = position2;
                        ++this.playerOneScore;
                    } else {
                        this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                        this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                        this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                        this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                        ++this.playerTwoScore;
                    }
                    isCurrentPlayerScoredPoint = true;
                }

                if (position1 <= 7 && this.board.board[position1 + 2][position2] == 'S'
                        && this.board.board[position1 + 1][position2] == 'O') {
                    if (this.playerMove % 2 == 1) {
                        this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                        this.board.completedPlayer1[this.playerOneScore][1] = position2;
                        this.board.completedPlayer1[this.playerOneScore][2] = position1;
                        this.board.completedPlayer1[this.playerOneScore][3] = position2;
                        ++this.playerOneScore;
                    } else {
                        this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                        this.board.completedPlayer2[this.playerTwoScore][1] = position2;
                        this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                        this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                        ++this.playerTwoScore;
                    }
                    isCurrentPlayerScoredPoint = true;
                }
            }

            if (position2 >= 2 && this.board.board[position1][position2 - 2] == 'S'
                    && this.board.board[position1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position2 <= 7 && this.board.board[position1][position2 + 2] == 'S'
                    && this.board.board[position1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && position2 <= 7 && this.board.board[position1 - 2][position2 + 2] == 'S'
                    && this.board.board[position1 - 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 <= 7 && this.board.board[position1 + 2][position2 + 2] == 'S'
                    && this.board.board[position1 + 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && position2 >= 2 && this.board.board[position1 - 2][position2 - 2] == 'S'
                    && this.board.board[position1 - 1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 >= 2 && this.board.board[position1 + 2][position2 - 2] == 'S'
                    && this.board.board[position1 + 1][position2 - 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 - 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 >= 2 && position2 <= 7 && this.board.board[position1 - 2][position2 + 2] == 'S'
                    && this.board.board[position1 - 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 - 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 - 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }

            if (position1 <= 7 && position2 <= 7 && this.board.board[position1 + 2][position2 + 2] == 'S'
                    && this.board.board[position1 + 1][position2 + 1] == 'O') {
                if (this.playerMove % 2 == 1) {
                    this.board.completedPlayer1[this.playerOneScore][0] = position1 + 2;
                    this.board.completedPlayer1[this.playerOneScore][1] = position2 + 2;
                    this.board.completedPlayer1[this.playerOneScore][2] = position1;
                    this.board.completedPlayer1[this.playerOneScore][3] = position2;
                    ++this.playerOneScore;
                } else {
                    this.board.completedPlayer2[this.playerTwoScore][0] = position1 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][1] = position2 + 2;
                    this.board.completedPlayer2[this.playerTwoScore][2] = position1;
                    this.board.completedPlayer2[this.playerTwoScore][3] = position2;
                    ++this.playerTwoScore;
                }
                isCurrentPlayerScoredPoint = true;
            }
        }

        if (isCurrentPlayerScoredPoint) {
            this.scoreplayerMove1.setText("PLAYER 1 SCORE : " + this.playerOneScore);
            this.scoreplayerMove2.setText("PLAYER 2 SCORE : " + this.playerTwoScore);
            return true;
        }

        this.checkIfGameCompleted();

        return false;
    }

    void checkIfGameCompleted() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (this.board.board[i][j] != '\u0000') {
                    continue;
                }
                return;
            }
        }

        if (this.playerOneScore > this.playerTwoScore) {
            this.status.setText("PLAYER " + String.valueOf(1) + " WON");
        } else if (this.playerOneScore < this.playerTwoScore) {
            this.status.setText("PLAYER " + String.valueOf(2) + " WON");
        } else {
            this.status.setText("MATCH IS TIE");
        }
    }

    public static void main(String[] stringArray) {
        new SOSGameGraphicsVersion2();
    }
}

class GameBoard extends Applet {
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
        g.drawRect(600, 120, 300, 440);
    }
}
