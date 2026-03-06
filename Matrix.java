import java.util.Scanner;
public class Matrix{
public static void main(String[] args){
Scanner s=new Scanner(System.in);
System.out.print("Enter the row size of the first matrix : ");
int r1=s.nextInt();
System.out.print("Enter the column size of the first matrix : ");
int c1=s.nextInt();
System.out.println("Enter the Numbers in the first matrix : ");
int a1[][]=new int[r1][c1];
for(int i=0;i<r1;i++){
for(int j=0;j<c1;j++){
a1[i][j]=s.nextInt();
}
}
System.out.print("Enter the row size of the second matrix : ");
int r2=s.nextInt();
System.out.print("Enter the column size of the second matrix : ");
int c2=s.nextInt();
System.out.println("Enter the Numbers in the second matrix : ");
int a2[][]=new int[r2][c2];
for(int i=0;i<r2;i++){
for(int j=0;j<c2;j++){
a2[i][j]=s.nextInt();
}
}
System.out.print("\nThe First matrix : ");
for(int i=0;i<r1;i++){
System.out.println();
for(int j=0;j<c1;j++){
System.out.print(a1[i][j]+"\t");
}
}
System.out.print("\nThe Second matrix : ");
for(int i=0;i<r2;i++){
System.out.println();
for(int j=0;j<c2;j++){
System.out.print(a2[i][j]+"\t");
}
}
System.out.print("\nEnter the Operation you want to perform (Addition = 1, Multiplication = 2) : ");
int c=s.nextInt();
switch(c){
case 1:
if(r1==r2&&c1==c2){
int a3[][]=new int[r1][c1];
System.out.print("\nThe Matrix : ");
for(int i=0;i<r1;i++){
System.out.println();
for(int j=0;j<c1;j++){
a3[i][j]=a1[i][j]+a2[i][j];
System.out.print(a3[i][j]+"\t");
}
}
}else{
System.out.println("Matrix addition not possible");
}
break;
case 2:
if(c1==r2){
int a3[][]=new int[r1][c2];
System.out.print("\nThe Matrix : ");
for(int i=0;i<r1;i++){
System.out.println();
for(int j=0;j<c2;j++){
a3[i][j]=0;
for(int k=0;k<c1;k++){
a3[i][j]+=a1[i][k]*a2[k][j];
}
System.out.print(a3[i][j]+"\t");
}
}
}else{
System.out.println("Matrix multiplication not possible");
}
break;
default:
System.out.println("Invalid Choice");
break;
}
}
}
