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

    Label title;
    Label scoresLabel;
    public static Label status;
    static Label scorePlayer1;
    static Label scorePlayer2;
    board gameBoard;
    Button playAgain,closeGame;
    static int player1Score,player2Score;
    public static int moves;
    DotsAndBoxGame() {
        super("Dots Box game");
        title=new Label("DOTS BOX GAME");
        title.setFont(new Font("Arial",Font.BOLD,50));
        title.setBounds(220,95,600,100);
        add(title);
        playAgain=new Button("PLAY AGAIN");
        playAgain.addMouseListener(this);
        add(playAgain);
        playAgain.setBounds(760,600,200,50);
        closeGame=new Button("X");
        closeGame.setFont(new Font("Arial",Font.BOLD,30));
        closeGame.setBounds(900,50,50,50);
        closeGame.addMouseListener(this);
        add(closeGame);
        scoresLabel=new Label("SCORES");
        player1Score=0;
        player2Score=0;
        moves=0;
	status=new Label();
        scorePlayer1=new Label("PLAYER 1 : "+player1Score);
        scorePlayer2=new Label("PLAYER 2 : "+player2Score);
	status.setBounds(775,700,200,50);
        scoresLabel.setBounds(775,400,100,50);
        scorePlayer1.setBounds(750,450,200,50);
        scorePlayer2.setBounds(750,500,200,50);
	Font font=new Font("Arial",Font.BOLD,20);
	status.setFont(font);
        scoresLabel.setFont(font);
        scorePlayer1.setFont(font);
        scorePlayer2.setFont(font);
	add(status);
        add(scoresLabel);
        add(scorePlayer1);
        add(scorePlayer2);
        gameBoard=new board();
        add(gameBoard);
        setSize(1000,1000);
        gameBoard.init();
        gameBoard.start();
        gameBoard.addMouseListener(this);
        setVisible(true);
        setLayout(null);


    }
    public void mouseClicked(MouseEvent me) {
        if(me.getSource()==closeGame) {
            System.exit(0);
        }
        else if(me.getSource()==playAgain) {
            playGameAgain();
        }
        else if(me.getSource()==gameBoard) {
            int posX=me.getX();
            int posY=me.getY();
            boolean isValidMove=false;

outHor:
            for(int i=0; i<11; i++) {
                for(int j=0; j<10; j++) {
                    int x=200+i*50;
                    int y=210+j*50;
                    if(posX>=x&&posX<=x+10&&posY>=y&&posY<=y+40) {
                        if(gameBoard.horizontalLinesArr[i][j]=='\0') {
                            moves++;
                            gameBoard.horizontalLinesArr[i][j]=(moves%2==0)?'Y':'G';
                            gameBoard.lastMoveX=i;
                            gameBoard.lastMoveY=j;
                            gameBoard.lastMoveColor=(moves%2==0)?'Y':'G';
                            isValidMove=true;
                            break outHor;
                        }
                    }
                }
            }
outVer:
            for(int i=0; i<10; i++) {
                for(int j=0; j<11; j++) {
                    int x=210+i*50;
                    int y=200+j*50;
                    if(posX>=x&&posX<=x+40&&posY>=y&&posY<=y+10) {
                        if(gameBoard.verticalLinesArr[i][j]=='\0') {
                            moves++;
                            gameBoard.verticalLinesArr[i][j]=(moves%2==0)?'Y':'G';
                            gameBoard.lastMoveX=i;
                            gameBoard.lastMoveY=j;
                            gameBoard.lastMoveColor=(moves%2==0)?'Y':'G';
                            isValidMove=true;
                            break outVer;
                        }
                    }
                }
            }
            if(isValidMove)
                gameBoard.repaint();
        }
    }
    public void mouseReleased(MouseEvent me) {}
    public void mousePressed(MouseEvent me) {}
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}

    void playGameAgain() {

        gameBoard.init();
        gameBoard.repaint();
        moves=0;
        player1Score=0;
        player2Score=0;
        scorePlayer1.setText("PLAYER 1 : "+player1Score);
        scorePlayer2.setText("PLAYER 2 : "+player2Score);
	DotsAndBoxGame.status.setText("");

    }
    public static void main(String args[]) {

        new DotsAndBoxGame();
    }

}

class board extends Applet {

    public char horizontalLinesArr[][]=new char[11][10],verticalLinesArr[][]=new char[10][11],lastMoveColor,completedColors[][]=new char[10][10];
    public boolean completed[][]=new boolean[10][10];
    public int lastMoveX,lastMoveY;
    public void init() {
        lastMoveColor='\0';
        lastMoveX=-1;
        lastMoveY=-1;
        for(int i=0; i<11; i++) {
            for(int j=0; j<10; j++) {
                horizontalLinesArr[i][j]='\0';
            }
        }
        for(int i=0; i<10; i++) {
            for(int j=0; j<11; j++) {
                verticalLinesArr[i][j]='\0';
            }
        }
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                completed[i][j]=false;
            }
        }
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                completedColors[i][j]='\0';
            }
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
	int flag1=0,flag2=0;
	out1:for(int i=0; i<11; i++) {
                for(int j=0; j<10; j++) {
		    if(horizontalLinesArr[i][j]=='\0'){
			flag1=1;
			break out1;
		    }
		}
	      }
out2:for(int i=0; i<10; i++) {
            for(int j=0; j<11; j++) {
if(verticalLinesArr[i][j]=='\0'){
flag2=1;
break out2;
}
}
}
if(flag1==0&&flag2==0){
if(DotsAndBoxGame.player1Score>DotsAndBoxGame.player2Score){
DotsAndBoxGame.status.setText("PLAYER 1 WON");
}
else if(DotsAndBoxGame.player1Score<DotsAndBoxGame.player2Score){
DotsAndBoxGame.status.setText("PLAYER 2 WON");
}
else if(DotsAndBoxGame.player1Score==DotsAndBoxGame.player2Score&&DotsAndBoxGame.player1Score!=0){
DotsAndBoxGame.status.setText("MATCH IS TIE");
}
}
        for(int i=0; i<11; i++) {
            for(int j=0; j<10; j++) {
                g.setColor(Color.LIGHT_GRAY);
                if(horizontalLinesArr[i][j]=='G')
                    g.setColor(Color.GREEN);
                else if(horizontalLinesArr[i][j]=='Y')
                    g.setColor(Color.YELLOW);
                g.fillRect(200+i*50,205+j*50,10,50);
            }
        }
        for(int i=0; i<10; i++) {
            for(int j=0; j<11; j++) {
                g.setColor(Color.LIGHT_GRAY);
                if(verticalLinesArr[i][j]=='G') {
                    g.setColor(Color.GREEN);
                }
                else if(verticalLinesArr[i][j]=='Y') {
                    g.setColor(Color.YELLOW);
                }
                g.fillRect(205+i*50,200+j*50,50,10);
            }
        }
        fillArea(g,DotsAndBoxGame.moves);
        g.setColor(new Color(95,95,100));
        for(int i=0; i<11; i++) {
            for(int j=0; j<11; j++) {
                g.fillOval(200+i*50,200+j*50,10,10);
            }
        }
        g.drawRect(730,375,250,200);
    }
    void fillArea(Graphics g,int moves) {
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                char top=horizontalLinesArr[i][j];
                char bottom =horizontalLinesArr[i+1][j];
                char left=verticalLinesArr[i][j];
                char right=verticalLinesArr[i][j+1];
                if(top!='\0'&&bottom!='\0'&&left!='\0'&&right!='\0'&&!completed[i][j]) {

                    char owner =lastMoveColor;
                    completed[i][j] = true;
                    completedColors[i][j] = owner;

                    if(owner == 'G') {
                        DotsAndBoxGame.player1Score++;
                        DotsAndBoxGame.scorePlayer1.setText("PLAYER 1 : " + DotsAndBoxGame.player1Score);
                    } else {
                        DotsAndBoxGame.player2Score++;
                        DotsAndBoxGame.scorePlayer2.setText("PLAYER 2 : " + DotsAndBoxGame.player2Score);
                    }
                }
                if(completedColors[i][j] == 'G') {
                    g.setColor(Color.GREEN);
                    g.fillRect(210+i*50, 210+j*50, 40, 40);
                } else if(completedColors[i][j] == 'Y') {
                    g.setColor(Color.YELLOW);
                    g.fillRect(210+i*50, 210+j*50, 40, 40);
                }
            }
        }
    }
}
