import java.util.Scanner;
class TicTacToe2Players{
public static void main(String args[]){
   TicTacToeGame game=new TicTacToeGame();
   game.play();
}}
class TicTacToeGame{
char arr[][]=new char[3][3],p1,p2;
Scanner scan=new Scanner(System.in);
void printpos(){
	System.out.print("...Position...\n");
	for(int i=1;i<10;i++){
		System.out.print(i+" ");
		if(i%3==0)
		System.out.print("\n");
	}
}
void player(char p)
{
	int pos,x=-1,y=-1;
	System.out.print("Enter position for player ");
if(p==p1)
System.out.print(1+"("+p+"):");
else
System.out.print(2+"("+p+"):");
	pos=scan.nextInt();
	if(pos>=1&&pos<=9){
		switch(pos){
			case 1:x=y=0;break;
			case 2:x=0;y=1;break;
			case 3:x=0;y=2;break;
			case 4:x=1;y=0;break;
			case 5:x=y=1;break;
			case 6:x=1;y=2;break;
			case 7:x=2;y=0;break;
			case 8:x=2;y=1;break;
			case 9:x=y=2;break;
		}
	if(arr[x][y]=='\0')
	  arr[x][y]=p;}
	else{
	 System.out.print("Invalid position\n");
	 player(p);
	 }
}
boolean rcheck(char p)
{
	if((arr[0][0]==p)&&(arr[0][1]==p)&&(arr[0][2]==p))
	   return true;
	else if((arr[1][0]==p)&&(arr[1][1]==p)&&(arr[1][2]==p))  
	   return true;
	else if((arr[2][0]==p)&&(arr[2][1]==p)&&(arr[2][2]==p)) 
		return true;
	else
		return false; 
}
boolean ccheck(char p)
{
	if((arr[0][0]==p)&&(arr[1][0]==p)&&(arr[2][0]==p))
	   return true;
	else if((arr[0][1]==p)&&(arr[1][1]==p)&&(arr[2][1]==p))
	   return true;
	else if((arr[0][2]==p)&&(arr[1][2]==p)&&(arr[2][2]==p))
		return true;
	else
		return false;
}
boolean dcheck(char p)
{
	if((arr[0][0]==p)&&(arr[1][1]==p)&&(arr[2][2]==p))
	   return true;
	else if((arr[0][2]==p)&&(arr[1][1]==p)&&(arr[2][0]==p))  
	   return true;
	else
		return false;
}
void display()
{	int i,j;
	for(i=0;i<3;i++)
	{
	 for(j=0;j<3;j++)
	 {if(arr[i][j]=='\0')
	 	System.out.print("- ");
	  else
	  System.out.print(arr[i][j]+" ");
	 }
	 System.out.print("\n");
	}
}
boolean check(char p)
{
	if((rcheck(p))||(ccheck(p))||(dcheck(p)))
		return true;
	else
		return false;
}
void play(){
char ch;
	int i,j;
    boolean ck=false;
	do{
	System.out.print("...TIC TAC TOE...\n");
	for(i=0;i<3;i++)
	 for(j=0;j<3;j++)
	  arr[i][j]='\0';
	System.out.print("...RULES...\n");
	System.out.print("1:The player cannot place their option in already existed positions\n");
	System.out.print("2:Each player gets their own option to play\n");
	System.out.print("3:Each player should enter the position of the next move between 1 and 9\n");
	System.out.print("4:The following are the positions\n");
	printpos();
	enter:
    while(true){
	System.out.print("Enter the option of player 1:");
	p1=scan.next().charAt(0);
	System.out.print("Enter the option of player 2:");
	p2=scan.next().charAt(0);
	if(p1==p2){
		System.out.print("Both players choosed same options\n");
		continue enter;
	}
    break;}
	i=0;
    end:
	while(i<=4){
	System.out.print("Player 1's turn\n");
	player(p1);
	display();
	ck=check(p1);
	if(ck){
    System.out.print("...PLAYER 1 WON...\n");
		break end;}
	else if((ck==false)&&(i==4)){
    System.out.print("...MATCH IS TIE...\n");
	break end;}
	if(i<4){
	System.out.print("Player 2's turn\n");
	player(p2);
	display();
	ck=check(p2);
	if(ck){
    System.out.print("...PLAYER 2 WON...\n");
    break end;}
	else if((ck==false)&&(i++==4)){
    System.out.print("...MATCH IS TIE...\n");
	break end;}
	}
	}
	System.out.print("Do you want to play again press y for yes any key for no:");
	ch=scan.next().charAt(0);
	}while(ch=='y'||ch=='Y');
    scan.close();
}
}