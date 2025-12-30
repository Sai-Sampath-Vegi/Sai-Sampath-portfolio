import java.awt.Frame;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class TicTacToe extends Frame implements ActionListener{

Button btns[];
Button playAgainBtn,endBtn;
Applet lines;
int moves;
Label status;
TicTacToe(){

//super("Play Tic Tac Toe");
Label title=new Label("TIC TAC TOE");
title.setFont(new Font("Arial",Font.BOLD,25));
title.setBounds(150,70,200,50);
add(title);
status=new Label("PLAYER 1 MOVE");
status.setBounds(150,500,400,100);
add(status);
btns = new Button[9];
for(int i=0;i<9;i++){
btns[i]=new Button();
btns[i].setFont(new Font("Arial",Font.BOLD,30));
int x = 100 + (i%3)*100;
int y=150+(i/3)*100;
btns[i].setBounds(x,y,90,90);
add(btns[i]);
btns[i].addActionListener(this);
}
setLayout(null);
setSize(500,700);
setVisible(true);
lines=new drawLines();
add(lines);
lines.setSize(500,600);
lines.start();
playAgainBtn=new Button("Play again");
playAgainBtn.setBounds(150,625,75,20);
endBtn=new Button("End game");
endBtn.setBounds(225,625,75,20);
playAgainBtn.addActionListener(this);
endBtn.addActionListener(this);
add(playAgainBtn);
add(endBtn);
playAgainBtn.setVisible(false);
endBtn.setVisible(false);
moves=0;
}

public void actionPerformed(ActionEvent ae){
if(ae.getSource()==playAgainBtn){
	playAgain();
}
else if(ae.getSource()==endBtn){
	System.exit(0);
}
else{
moves++;
	for(int i=0;i<9;i++){
	if(ae.getSource()==btns[i]){
		btns[i].setLabel((moves%2==1)?"X":"O");
		btns[i].setEnabled(false);
		if(checkWin())
		break;
		else{
		status.setText("PLAYER "+String.valueOf((moves%2==0)?'1':'2')+" MOVE");
		}
	}
	}
}
}

boolean checkWin(){
if(btns[0].getLabel()==btns[1].getLabel()&&btns[1].getLabel()==btns[2].getLabel()&&(!(btns[0].getLabel().equals("")))){
btns[0].setBackground(Color.GREEN);
btns[1].setBackground(Color.GREEN);
btns[2].setBackground(Color.GREEN);
showState(1);
return true;
}
else if(btns[3].getLabel()==btns[4].getLabel()&&btns[4].getLabel()==btns[5].getLabel()&&(!(btns[3].getLabel().equals("")))){
btns[3].setBackground(Color.GREEN);
btns[4].setBackground(Color.GREEN);
btns[5].setBackground(Color.GREEN);
showState(1);
return true;
}
else if(btns[6].getLabel()==btns[7].getLabel()&&btns[7].getLabel()==btns[8].getLabel()&&(!(btns[6].getLabel().equals("")))){
btns[6].setBackground(Color.GREEN);
btns[7].setBackground(Color.GREEN);
btns[8].setBackground(Color.GREEN);
showState(1);
return true;
}
else if(btns[0].getLabel()==btns[3].getLabel()&&btns[3].getLabel()==btns[6].getLabel()&&(!(btns[0].getLabel().equals("")))){
btns[0].setBackground(Color.GREEN);
btns[3].setBackground(Color.GREEN);
btns[6].setBackground(Color.GREEN);
showState(1);
return true;
}
else if(btns[1].getLabel()==btns[4].getLabel()&&btns[4].getLabel()==btns[7].getLabel()&&(!(btns[1].getLabel().equals("")))){
btns[1].setBackground(Color.GREEN);
btns[4].setBackground(Color.GREEN);
btns[7].setBackground(Color.GREEN);
showState(1);
return true;
}
else if(btns[2].getLabel()==btns[5].getLabel()&&btns[5].getLabel()==btns[8].getLabel()&&(!(btns[2].getLabel().equals("")))){
btns[2].setBackground(Color.GREEN);
btns[5].setBackground(Color.GREEN);
btns[8].setBackground(Color.GREEN);
showState(1);
return true;
}
else if(btns[0].getLabel()==btns[4].getLabel()&&btns[4].getLabel()==btns[8].getLabel()&&(!(btns[0].getLabel().equals("")))){
btns[0].setBackground(Color.GREEN);
btns[4].setBackground(Color.GREEN);
btns[8].setBackground(Color.GREEN);
showState(1);
return true;
}
else if(btns[2].getLabel()==btns[4].getLabel()&&btns[4].getLabel()==btns[6].getLabel()&&(!(btns[2].getLabel().equals("")))){
btns[2].setBackground(Color.GREEN);
btns[4].setBackground(Color.GREEN);
btns[6].setBackground(Color.GREEN);
showState(1);
return true;
}
if(moves==9){
showState(0);
}
return false;
}

void showState(int state){
if(state==1){
status.setText("Player "+((moves%2==1)?"1":"2") + " won the game");
}
else if(state==0){
status.setText("The match is tie");
}
for(int i=0;i<9;i++){
btns[i].setEnabled(false);
}
playAgainBtn.setVisible(true);
endBtn.setVisible(true);
}

void playAgain(){
for(int i=0;i<9;i++){
btns[i].setLabel("");
btns[i].setEnabled(true);
btns[i].setBackground(Color.WHITE);
playAgainBtn.setVisible(false);
endBtn.setVisible(false);
moves=0;
status.setText("PLAYER 1 MOVE");
}
}

public static void main(String args[]){
new TicTacToe();
}
}

class drawLines extends Applet{

public void paint(Graphics g){
g.drawLine(195,140,195,450);
g.drawLine(295,140,295,450);
g.drawLine(90,245,400,245);
g.drawLine(90,345,400,345);

g.drawLine(90,140,90,450);
g.drawLine(400,140,400,450);
g.drawLine(90,140,400,140);
g.drawLine(90,450,400,450);

}

}
