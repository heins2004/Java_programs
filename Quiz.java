import java.util.Scanner;

public class Quiz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = 0;
        char answer;

        System.out.println("1. Capital of Germany");
        System.out.println("A. Berlin");
        System.out.println("B. Paris");
        System.out.println("C. London");
        answer = sc.next().charAt(0);
        if (answer == 'A' || answer == 'a') {
            score++;
        }

        System.out.println("2. National Bird of India");
        System.out.println("A. Pigeon");
        System.out.println("B. Pecock");
        System.out.println("C. Eagle");
        answer = sc.next().charAt(0);
        if (answer == 'B' || answer == 'b') {
            score++;
        }

        System.out.println("3. Longest River in the World");
        System.out.println("A. Nile");
        System.out.println("B. Amazon");
        System.out.println("C. Indus");
        answer = sc.next().charAt(0);
        if (answer == 'A' || answer == 'a') {
            score++;
        }

        System.out.println("Your final score: " + score + "/3");

        sc.close();
    }
}