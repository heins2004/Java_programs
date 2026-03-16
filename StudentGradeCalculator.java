import java.util.Scanner;
public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter marks for Subject 1: ");
        int m1 = sc.nextInt();
        System.out.print("Enter marks for Subject 2: ");
        int m2 = sc.nextInt();
        System.out.print("Enter marks for Subject 3: ");
        int m3 = sc.nextInt();
        System.out.print("Enter marks for Subject 4: ");
        int m4 = sc.nextInt();
        System.out.print("Enter marks for Subject 5: ");
        int m5 = sc.nextInt();
        int total = m1 + m2 + m3 + m4 + m5;
        double average = total / 5.0;
        System.out.println("Total Marks = " + total);
        System.out.println("Average = " + average);
        if (average >= 90) {
            System.out.println("Grade: A");
        } 
        else if (average >= 75) {
            System.out.println("Grade: B");
        } 
        else if (average >= 60) {
            System.out.println("Grade: C");
        } 
        else if (average >= 50) {
            System.out.println("Grade: D");
        } 
        else {
            System.out.println("Grade: F");
        }
        sc.close();
    }
}