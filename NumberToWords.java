import java.util.Scanner;

public class NumberToWords {

    static String[] ones = {
        "", "One", "Two", "Three", "Four", "Five",
        "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    static String[] tens = {
        "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number (0–999): ");
        int num = sc.nextInt();

        if (num == 0) {
            System.out.println("Zero");
        } 
        else if (num < 20) {
            System.out.println(ones[num]);
        } 
        else if (num < 100) {
            System.out.println(tens[num / 10] + " " + ones[num % 10]);
        } 
        else if (num < 1000) {
            System.out.println(
                ones[num / 100] + " Hundred " +
                tens[(num % 100) / 10] + " " +
                ones[num % 10]
            );
        } 
        else {
            System.out.println("Number out of range");
        }

        sc.close();
    }
}