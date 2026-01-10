import java.awt.Frame;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class TicTacToeGraphics extends Frame implements ActionListener {

	Button buttons[];
	Button playGameAgainButton, endGameButton;
	Applet lines;
	int moves;
	Label status;

	TicTacToeGraphics() {

		super("Play Tic Tac Toe");
		Label title = new Label("TIC TAC TOE");
		title.setFont(new Font("Arial", Font.BOLD, 25));
		title.setBounds(150, 70, 200, 50);
		add(title);

		status = new Label("PLAYER 1 MOVE");
		status.setBounds(150, 500, 400, 100);
		add(status);

		buttons = new Button[9];

		for (int buttonsCounter = 0; buttonsCounter < 9; buttonsCounter++) {
			buttons[buttonsCounter] = new Button();
			buttons[buttonsCounter].setFont(new Font("Arial", Font.BOLD, 30));
			int x = 100 + (buttonsCounter % 3) * 100;
			int y = 150 + (buttonsCounter / 3) * 100;
			buttons[buttonsCounter].setBounds(x, y, 90, 90);
			add(buttons[buttonsCounter]);
			buttons[buttonsCounter].addActionListener(this);
		}

		setLayout(null);
		setSize(500, 700);
		setResizable(false);
		setVisible(true);

		lines = new drawLines();
		add(lines);
		lines.setSize(500, 600);
		lines.start();

		playGameAgainButton = new Button("Play Again");
		playGameAgainButton.setBounds(150, 625, 75, 20);
		endGameButton = new Button("End Game");
		endGameButton.setBounds(225, 625, 75, 20);
		playGameAgainButton.addActionListener(this);
		endGameButton.addActionListener(this);
		add(playGameAgainButton);
		add(endGameButton);
		playGameAgainButton.setVisible(false);
		endGameButton.setVisible(false);

		moves = 0;
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == playGameAgainButton) {
			playAgain();
		} else if (ae.getSource() == endGameButton) {
			System.exit(0);
		} else {
			moves++;
			for (int buttonsCounter = 0; buttonsCounter < 9; buttonsCounter++) {
				if (ae.getSource() == buttons[buttonsCounter]) {
					buttons[buttonsCounter].setLabel((moves % 2 == 1) ? "X" : "O");
					buttons[buttonsCounter].setEnabled(false);
					if (checkWin())
						break;
					else {
						status.setText("PLAYER " + String.valueOf((moves % 2 == 0) ? '1' : '2') + " MOVE");
					}
				}
			}
		}
	}

	boolean checkWin() {
		boolean isCurrentPlayerWon = false;
		if (buttons[0].getLabel() == buttons[1].getLabel() && buttons[1].getLabel() == buttons[2].getLabel()
				&& (!(buttons[0].getLabel().equals("")))) {
			buttons[0].setBackground(Color.GREEN);
			buttons[1].setBackground(Color.GREEN);
			buttons[2].setBackground(Color.GREEN);
			showState(1);
			isCurrentPlayerWon = true;
		} else if (buttons[3].getLabel() == buttons[4].getLabel() && buttons[4].getLabel() == buttons[5].getLabel()
				&& (!(buttons[3].getLabel().equals("")))) {
			buttons[3].setBackground(Color.GREEN);
			buttons[4].setBackground(Color.GREEN);
			buttons[5].setBackground(Color.GREEN);
			showState(1);
			isCurrentPlayerWon = true;
		} else if (buttons[6].getLabel() == buttons[7].getLabel() && buttons[7].getLabel() == buttons[8].getLabel()
				&& (!(buttons[6].getLabel().equals("")))) {
			buttons[6].setBackground(Color.GREEN);
			buttons[7].setBackground(Color.GREEN);
			buttons[8].setBackground(Color.GREEN);
			showState(1);
			isCurrentPlayerWon = true;
		} else if (buttons[0].getLabel() == buttons[3].getLabel() && buttons[3].getLabel() == buttons[6].getLabel()
				&& (!(buttons[0].getLabel().equals("")))) {
			buttons[0].setBackground(Color.GREEN);
			buttons[3].setBackground(Color.GREEN);
			buttons[6].setBackground(Color.GREEN);
			showState(1);
			isCurrentPlayerWon = true;
		} else if (buttons[1].getLabel() == buttons[4].getLabel() && buttons[4].getLabel() == buttons[7].getLabel()
				&& (!(buttons[1].getLabel().equals("")))) {
			buttons[1].setBackground(Color.GREEN);
			buttons[4].setBackground(Color.GREEN);
			buttons[7].setBackground(Color.GREEN);
			showState(1);
			isCurrentPlayerWon = true;
		} else if (buttons[2].getLabel() == buttons[5].getLabel() && buttons[5].getLabel() == buttons[8].getLabel()
				&& (!(buttons[2].getLabel().equals("")))) {
			buttons[2].setBackground(Color.GREEN);
			buttons[5].setBackground(Color.GREEN);
			buttons[8].setBackground(Color.GREEN);
			showState(1);
			isCurrentPlayerWon = true;
		} else if (buttons[0].getLabel() == buttons[4].getLabel() && buttons[4].getLabel() == buttons[8].getLabel()
				&& (!(buttons[0].getLabel().equals("")))) {
			buttons[0].setBackground(Color.GREEN);
			buttons[4].setBackground(Color.GREEN);
			buttons[8].setBackground(Color.GREEN);
			showState(1);
			isCurrentPlayerWon = true;
		} else if (buttons[2].getLabel() == buttons[4].getLabel() && buttons[4].getLabel() == buttons[6].getLabel()
				&& (!(buttons[2].getLabel().equals("")))) {
			buttons[2].setBackground(Color.GREEN);
			buttons[4].setBackground(Color.GREEN);
			buttons[6].setBackground(Color.GREEN);
			showState(1);
			isCurrentPlayerWon = true;
		}

		if (moves == 9) {
			showState(0);
		}

		return isCurrentPlayerWon;
	}

	void showState(int state) {
		if (state == 1) {
			status.setText("Player " + ((moves % 2 == 1) ? "1" : "2") + " Won the Game");
		} else if (state == 0) {
			status.setText("The Match is Tie");
		}

		for (int buttonsCounter = 0; buttonsCounter < 9; buttonsCounter++) {
			buttons[buttonsCounter].setEnabled(false);
		}

		playGameAgainButton.setVisible(true);
		endGameButton.setVisible(true);
	}

	void playAgain() {
		for (int buttonsCounter = 0; buttonsCounter < 9; buttonsCounter++) {
			buttons[buttonsCounter].setLabel("");
			buttons[buttonsCounter].setEnabled(true);
			buttons[buttonsCounter].setBackground(Color.WHITE);
			playGameAgainButton.setVisible(false);
			endGameButton.setVisible(false);
			moves = 0;
			status.setText("PLAYER 1 MOVE");
		}
	}

	public static void main(String args[]) {
		new TicTacToeGraphics();
	}
}

class drawLines extends Applet {

	public void paint(Graphics g) {
		g.drawLine(195, 140, 195, 450);
		g.drawLine(295, 140, 295, 450);
		g.drawLine(90, 245, 400, 245);
		g.drawLine(90, 345, 400, 345);

		g.drawLine(90, 140, 90, 450);
		g.drawLine(400, 140, 400, 450);
		g.drawLine(90, 140, 400, 140);
		g.drawLine(90, 450, 400, 450);

	}
}
