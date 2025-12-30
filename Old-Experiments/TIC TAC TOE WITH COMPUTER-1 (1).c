#include<stdio.h>
#include<stdlib.h>
void player();
int check(char);
int rcheck(char);
int ccheck(char);
int dcheck(char);
void display();
void printpos();
void com();
int match(int,int);
char arr[9],p='X',comp='O';
main()
{ 
	char ch;
	int i,j,ck;
	printf("...TIC TAC TOE...\n");
	printf("...RULES...\n");
	printf("The player cannot place their option in already occupied positions\n");
	printf("Player should enter the position of his/her move between 1 and 9\n");
    printf("Player gets the option (X) and Computer gets the option (O)\n");
	printf("The following are the positions\n");
	printpos();
    do{
    for(i=0;i<9;i++)
	  arr[i]='\0';
	i=0;
	while(i<=4){
	printf("Your turn\n");
	player();
	ck=check(p);
	if(ck)
	goto w1;
	else if(ck==0&&i==4)
	goto tie;
	if(i<4){
	com();
    display();
	ck=check(comp);
	if(ck)
	goto w2;
	else if(ck==0&&i++==4)
	goto tie;
	}
	}
	w1: display();
        printf("...YOU WON...\n");
		goto end;
	w2: display();
        printf("...COMPUTER WON TRY AGAIN...\n");
		goto end;
	tie: display();
         printf("...MATCH IS TIE...\n");
	end:
	printf("Do you want to play again press y for yes any key for no:");
	scanf(" %c",&ch);
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
int match(int p1,int p2){
  int wcs[][3]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}},i,j,k;
  for(i=0;i<8;i++){
         if(((wcs[i][0]==p1)&&(wcs[i][1]==p2)&&(arr[wcs[i][2]]=='\0'))||((wcs[i][0]==p2)&&(wcs[i][1]==p1)&&(arr[wcs[i][2]]=='\0')))
           return wcs[i][2];
         if(((wcs[i][1]==p1)&&(wcs[i][2]==p2)&&(arr[wcs[i][0]]=='\0'))||((wcs[i][1]==p2)&&(wcs[i][2]==p1)&&(arr[wcs[i][0]]=='\0')))
           return wcs[i][0];
         if(((wcs[i][0]==p1)&&(wcs[i][2]==p2)&&(arr[wcs[i][1]]=='\0'))||((wcs[i][0]==p2)&&(wcs[i][2]==p1)&&(arr[wcs[i][1]]=='\0')))
           return wcs[i][1];  
  }
  return -1;
}
void com(){
 int i,j=0,k=0,carr[4],parr[5],pos=-1,f=0;
for(i=0;i<9;i++){
 if(arr[i]==comp)
   carr[k++]=i;
 else if(arr[i]==p)
   parr[j++]=i;
}
 if(k==2)
 pos=match(carr[0],carr[1]);
 else if(k==3){
 pos=match(carr[0],carr[1]);
 if(pos==-1){
 pos=match(carr[0],carr[2]);
 if(pos==-1)
 pos=match(carr[1],carr[2]);}}
 else if(k==4){
 pos=match(carr[0],carr[1]);
 if(pos==-1){
 pos=match(carr[0],carr[2]);
 if(pos==-1){
 pos=match(carr[0],carr[3]);
 if(pos==-1){
 pos=match(carr[1],carr[2]);
 if(pos==-1){
 pos=match(carr[1],carr[3]);
 if(pos==-1)
 pos=match(carr[2],carr[3]);}}}}}
   if(pos==-1){
   if(j==2)
   pos=match(parr[0],parr[1]);
   else if(j==3){
   pos=match(parr[0],parr[1]);
   if(pos==-1){
   pos=match(parr[0],parr[2]);
   if(pos==-1)
   pos=match(parr[1],parr[2]);}}
   else if(j==4){
   pos=match(parr[0],parr[1]);
   if(pos==-1){
   pos=match(parr[0],parr[2]);
   if(pos==-1){
   pos=match(parr[0],parr[3]);
   if(pos==-1){
   pos=match(parr[1],parr[2]);
   if(pos==-1){
   pos=match(parr[1],parr[3]);
   if(pos==-1)
   pos=match(parr[2],parr[3]);}}}}}}
   if(pos==-1){
      for(i=4;i<9;i+=2)
        if(arr[i]=='\0'){
        f=1;
          pos=i;
          break;}
      if(f==0)    
      for(i=2;i>=0;i-=2)
        if(arr[i]=='\0'){
        f=1;
         pos=i;
          break;}
      if(f==0)    
      for(i=4;i<9;i++)
        if(arr[i]=='\0'){
        f=1;
          pos=i;
          break;}
      if(f==0)    
      for(i=3;i>=0;i--)
        if(arr[i]=='\0'){
         pos=i;
          break;}    
   }
   arr[pos]=comp;}
void player()
{
	int pos;
	printf("Enter position for (%c):",p);
	scanf("%d",&pos);
	if(pos>=1&&pos<=9){
	if(arr[pos-1]=='\0')
	  arr[pos-1]=p;
      else{
	 printf("Position occupied\n");
	 player();
	 }}
	else{
	 printf("Invalid position\n");
	 player();
	 }
}
int rcheck(char p)
{
	if((arr[0]==p)&&(arr[1]==p)&&(arr[2]==p))
	   return 1;
	else if((arr[3]==p)&&(arr[4]==p)&&(arr[5]==p))  
	   return 1;
	else if((arr[6]==p)&&(arr[7]==p)&&(arr[8]==p)) 
		return 1;
	else
		return 0;
}
int ccheck(char p)
{
	if((arr[0]==p)&&(arr[3]==p)&&(arr[6]==p))
	   return 1;
	else if((arr[1]==p)&&(arr[4]==p)&&(arr[7]==p))
	   return 1;
	else if((arr[2]==p)&&(arr[5]==p)&&(arr[8]==p))
		return 1;
	else
		return 0;
}
int dcheck(char p)
{
	if((arr[0]==p)&&(arr[4]==p)&&(arr[8]==p))
	   return 1;
	else if((arr[2]==p)&&(arr[4]==p)&&(arr[6]==p))  
	   return 1;
	else
		return 0;
}
void display()
{	int i;
    printf("\n");
	for(i=1;i<10;i++){
	 if(arr[i-1]=='\0')
	 	printf(" - ");
	  else
	  printf(" %c ",arr[i-1]);
      if(i%3==0)
	 printf("\n");
	}
    printf("\n");
}
int check(char p)
{
	if(rcheck(p)||ccheck(p)||dcheck(p))
		return 1;
	else
		return 0;
}