import java.util.Scanner;
public class MinMax {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the array limit : ");
        int limit = s.nextInt();
        int a[]= new int[limit];
        System.out.println("Enter the elements : ");
        for (int i=0;i<limit;i++){
            a[i] = s.nextInt();
        }
        int min = a[0];
        int max = a[0];
        for (int i=0;i<limit;i++){
            if (a[i]<min){
                min = a[i];
            }
            if (a[i]>max){
                max = a[i];
            }
        }
        System.out.println("Minimum = "+min);
        System.out.println("Maximum = "+max);
    }
}
