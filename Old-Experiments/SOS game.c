#include<stdio.h>
#include<string.h>
#include<stdbool.h>
#include<stdlib.h>
int scoreP1=0,scoreP2=0;
char board[16][16]={
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};
char positions[16][16][3]={
{"00","01","02","03","04","05","06","07","08","09","0A","0B","0C","0D","0E","0F"},
{"10","11","12","13","14","15","16","17","18","19","1A","1B","1C","1D","1E","1F"},
{"20","21","22","23","24","25","26","27","28","29","2A","2B","2C","2D","2E","2F"},
{"30","31","32","33","34","35","36","37","38","39","3A","3B","3C","3D","3E","3F"},
{"40","41","42","43","44","45","46","47","48","49","4A","4B","4C","4D","4E","4F"},
{"50","51","52","53","54","55","56","57","58","59","5A","5B","5C","5D","5E","5F"},
{"60","61","62","63","64","65","66","67","68","69","6A","6B","6C","6D","6E","6F"},
{"70","71","72","73","74","75","76","77","78","79","7A","7B","7C","7D","7E","7F"},
{"80","81","82","83","84","85","86","87","88","89","8A","8B","8C","8D","8E","8F"},
{"90","91","92","93","94","95","96","97","98","99","9A","9B","9C","9D","9E","9F"},
{"A0","A1","A2","A3","A4","A5","A6","A7","A8","A9","AA","AB","AC","AD","AE","AF"},
{"B0","B1","B2","B3","B4","B5","B6","B7","B8","B9","BA","BB","BC","BD","BE","BF"},
{"C0","C1","C2","C3","C4","C5","C6","C7","C8","C9","CA","CB","CC","CD","CE","CF"},
{"D0","D1","D2","D3","D4","D5","D6","D7","D8","D9","DA","DB","DC","DD","DE","DF"},
{"E0","E1","E2","E3","E4","E5","E6","E7","E8","E9","EA","EB","EC","ED","EE","EF"},
{"F0","F1","F2","F3","F4","F5","F6","F7","F8","F9","FA","FB","FC","FD","FE","FF"}};
void print();
void printpos();
void input();
void score(char[],int);
void play();
int main()
{
    printf("\t\t\t\tRules\n");
    printf("1. Current player gets a point if he place his/her move to make a combination (SOS)\n");
    printf("2. No two players can enter their move in already occupied position\n\n");
    printf("\t\t\t\tNote\n");
    printf("1. You can enter the input in any case it will automatically convert the input to the necessary case\n");
    printf("2. The game prompts you to enter 'Y' to exit after every 15 points to any player to exit and any other character to continue playing\n\n\n");
    play();
    return 0;
}
void printpos(){
int i,j,k;
for(i=0;i<16;i++){
for(j=0;j<16;j++){
for(k=0;k<2;k++)
printf("%c",positions[i][j][k]);
printf(" ");}
printf("\n");}}
void print(){
int i,j;
printf("Puzzle:\n");
for(i=0;i<16;i++){
for(j=0;j<16;j++)
printf("%c ",board[i][j]);
if(i==14)
printf("\tPlayer1 score is %d",scoreP1);
else if(i==15)
printf("\tPlayer2 score is %d",scoreP2);
printf("\n");}}
void input(int player){
int i,j;
bool filled=false;
char ch,pos[3];
if(player==1)
printf("Enter player1 move\n");
else
printf("Enter player2 move\n");
read:
printf("Enter position:");
scanf("%s",pos);
for(i=0;i<2;i++)
pos[i]=toupper(pos[i]);
for(i=0;i<16;i++){
for(j=0;j<16;j++){
if((strcmp(pos,positions[i][j])==0)){
if(board[i][j]=='-'){
cha:
printf("Enter the character (S or O):");
scanf(" %c",&ch);
if((ch!='s'&&ch!='S')&&(ch!='o'&&ch!='O')){
printf("Enter the character again\n");
goto cha;}
ch=toupper(ch);
board[i][j]=ch;
filled=true;
score(pos,player);
}
else{
printf("Position already occupied\nPlease enter it again\n");
goto read;}}}}
if(!filled){
printf("You entered wrong position\nPlease enter it again\n");
goto read;}}
void score(char lastPos[],int player){
int row,col,currentScoreP1=scoreP1,currentScoreP2=scoreP2;
if(lastPos[0]>='0'&&lastPos[0]<='9')
row=lastPos[0]-'0';
if(lastPos[0]>='A'&&lastPos[0]<='F'){
if(lastPos[0]=='A')row=10;
else if(lastPos[0]=='B')row=11;
else if(lastPos[0]=='C')row=12;
else if(lastPos[0]=='D')row=13;
else if(lastPos[0]=='E')row=14;
else if(lastPos[0]=='F')row=15;}
if(lastPos[1]>='0'&&lastPos[1]<='9')
col=lastPos[1]-'0';
if(lastPos[1]>='A'&&lastPos[1]<='F'){
if(lastPos[1]=='A')col=10;
else if(lastPos[1]=='B')col=11;
else if(lastPos[1]=='C')col=12;
else if(lastPos[1]=='D')col=13;
else if(lastPos[1]=='E')col=14;
else if(lastPos[1]=='F')col=15;}
if(board[row][col]=='O'){
if((row==0&&col==0)||(row==15&&col==0)||(row==0&&col==15)||(row==15&&col==15))
return;
else{
if(row==0||row==15){
if(board[row][col-1]=='S'&&board[row][col+1]=='S')
(player==1)?scoreP1++:scoreP2++;}
else if(col==0||col==15){
if(board[row-1][col]=='S'&&board[row+1][col]=='S')
(player==1)?scoreP1++:scoreP2++;}
else {
if(board[row-1][col]=='S'&&board[row+1][col]=='S')
(player==1)?scoreP1++:scoreP2++;
if(board[row-1][col-1]=='S'&&board[row+1][col+1]=='S')
(player==1)?scoreP1++:scoreP2++;
if(board[row-1][col+1]=='S'&&board[row+1][col-1]=='S')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col-1]=='S'&&board[row][col+1]=='S')
(player==1)?scoreP1++:scoreP2++;}}}
else{
if(row==0&&col==0){
if(board[row+2][col]=='S'&&board[row+1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col+2]=='S'&&board[row][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col+2]=='S'&&board[row+1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;}
else if(row==15&&col==0){
if(board[row-2][col]=='S'&&board[row-1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col+2]=='S'&&board[row][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col+2]=='S'&&board[row+1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;}
else if(row==0&&col==15){
if(board[row+2][col]=='S'&&board[row+1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col-2]=='S'&&board[row][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col-2]=='S'&&board[row+1][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;}
else if(row==15&&col==15){
if(board[row-2][col]=='S'&&board[row-1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col-2]=='S'&&board[row][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col-2]=='S'&&board[row-1][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;}
else if(row==0){
if(board[row+2][col]=='S'&&board[row+1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col+2]=='S'&&board[row][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col-2]=='S'&&board[row][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col+2]=='S'&&board[row+1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col-2]=='S'&&board[row+1][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;}
else if(row==15){
if(board[row-2][col]=='S'&&board[row-1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col+2]=='S'&&board[row][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col-2]=='S'&&board[row][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col+2]=='S'&&board[row-1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col-2]=='S'&&board[row-1][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;}
else if(col==0){
if(board[row+2][col]=='S'&&board[row+1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col]=='S'&&board[row-1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col+2]=='S'&&board[row][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col+2]=='S'&&board[row+1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col+2]=='S'&&board[row-1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;}
else if(col==15){
if(board[row+2][col]=='S'&&board[row+1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col]=='S'&&board[row-1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col-2]=='S'&&board[row][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col-2]=='S'&&board[row+1][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col-2]=='S'&&board[row-1][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;}
else {
if(board[row-2][col]=='S'&&board[row-1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col]=='S'&&board[row+1][col]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col-2]=='S'&&board[row][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row][col+2]=='S'&&board[row][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col+2]=='S'&&board[row-1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col+2]=='S'&&board[row+1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col-2]=='S'&&board[row-1][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col-2]=='S'&&board[row+1][col-1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row-2][col+2]=='S'&&board[row-1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;
if(board[row+2][col+2]=='S'&&board[row+1][col+1]=='O')
(player==1)?scoreP1++:scoreP2++;}}
if(currentScoreP1!=scoreP1){
printf("Player1 scored a point.\nEnter player1's next move\n");
printpos();
print();}
else if(currentScoreP2!=scoreP2){
printf("Player2 scored a point.\nEnter player2's next move\n");
printpos();
print();}
if((((scoreP1%15)==0)||((scoreP2%15)==0))&&(scoreP1!=0&&scoreP2!=0)){
printf("Do you want to continue playing or exit.\nPress 'Y' to exit any other to continue playing:");
char ex;
scanf(" %c",&ex);
if(tolower(ex)=='y')
exit(0);}
if(currentScoreP1!=scoreP1)
input(1);
else if(currentScoreP2!=scoreP2)
input(2);}
bool boardFilled(){
for(int i=0;i<16;i++)
for(int j=0;j<16;j++)
if(board[i][j]=='-')
return false;
return true;}
void play(){
printpos();
while(!boardFilled()){
input(1);
printpos();
print();        
input(2);
printpos();
print();}}