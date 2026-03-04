import java.util.Scanner;
public class SumOfDigit {
    public static void main(String [] args){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a number between 1 to 27 : ");
        int num = s.nextInt();
        for(int i=1;i<=999;i++){
            int r,sum=0;
            int temp = i;
            while(temp!=0){
                r=temp%10;
                sum=sum+r;
                temp=temp/10;
            } 
            if(sum==num){
                System.out.println(i);
            }
        }
    }
}
