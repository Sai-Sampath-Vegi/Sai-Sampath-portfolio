#include<stdio.h>
#include<stdlib.h>
void player(char);
int check(char);
int rcheck(char);
int ccheck(char);
int dcheck(char);
void display();
void printpos();
char arr[3][3],p1,p2;
main()
{ 
	char ch;
	int i,j,ck;
	do{
	printf("...TIC TAC TOE...\n");
	for(i=0;i<3;i++)
	 for(j=0;j<3;j++)
	  arr[i][j]='\0';
	printf("...RULES...\n");
	printf("1:The player cannot place their option in already existed positions\n");
	printf("2:Each player gets their own option to play\n");
	printf("3:Each player should enter the position of the next move between 1 and 9\n");
	printf("4:The following are the positions\n");
	printpos();
	enter:
	printf("Enter the option of player 1:");
	scanf(" %c",&p1);
	printf("Enter the option of player 2:");
	scanf(" %c",&p2);
	if(p1==p2){
		printf("Both players choosed same options\n");
		goto enter;
	}
	i=0;
	while(i<=4){
	printf("Player 1's turn\n");
	player(p1);
	display();
	ck=check(p1);
	if(ck)
	goto w1;
	else if(ck==0&&i==4)
	goto tie;
	if(i<4){
	printf("Player 2's turn\n");
	player(p2);
	display();
	ck=check(p2);
	if(ck)
	goto w2;
	else if(ck==0&&i++==4)
	goto tie;
	}
	}
	w1:printf("...PLAYER 1 WON...\n");
		goto end;
	w2:printf("...PLAYER 2 WON...\n");
		goto end;
	tie:printf("...MATCH IS TIE...\n");
	end:
	printf("Do you want to play again press y for yes any key for no:");
	scanf(" %c",&ch);
	system("cls");
	}while(ch=='y'||ch=='Y');
}
void printpos(){
	printf("...Position...\n");
	for(int i=1;i<10;i++){
		printf("%d ",i);
		if(i%3==0)
		printf("\n");
	}
}
void player(char p)
{
	int pos,x,y;
	printf("Enter position for player %d (%c):",(p==p1)?1:2,p);
	scanf("%d",&pos);
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
	  arr[x][y]=p;
else{
	 System.out.print("Position already occupied.\nPlease enter it again\n");
	 player(p);
	 }
}
	else{
	 printf("Invalid position\n");
	 player(p);
	 }
}
int rcheck(char p)
{
	if((arr[0][0]==p)&&(arr[0][1]==p)&&(arr[0][2]==p))
	   return 1;
	else if((arr[1][0]==p)&&(arr[1][1]==p)&&(arr[1][2]==p))  
	   return 1;
	else if((arr[2][0]==p)&&(arr[2][1]==p)&&(arr[2][2]==p)) 
		return 1;
	else
		return 0;
}
int ccheck(char p)
{
	if((arr[0][0]==p)&&(arr[1][0]==p)&&(arr[2][0]==p))
	   return 1;
	else if((arr[0][1]==p)&&(arr[1][1]==p)&&(arr[2][1]==p))
	   return 1;
	else if((arr[0][2]==p)&&(arr[1][2]==p)&&(arr[2][2]==p))
		return 1;
	else
		return 0;
}
int dcheck(char p)
{
	if((arr[0][0]==p)&&(arr[1][1]==p)&&(arr[2][2]==p))
	   return 1;
	else if((arr[0][2]==p)&&(arr[1][1]==p)&&(arr[2][0]==p))  
	   return 1;
	else
		return 0;
}
void display()
{	int i,j;
	for(i=0;i<3;i++)
	{
	 for(j=0;j<3;j++)
	 {if(arr[i][j]=='\0')
	 	printf("- ");
	  else
	  printf("%c ",arr[i][j]);
	 }
	 printf("\n");
	}
}
int check(char p)
{
	if(rcheck(p)||ccheck(p)||dcheck(p))
		return 1;
	else
		return 0;
}