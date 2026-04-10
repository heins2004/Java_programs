import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "@#$%&*";

        String allChars = upper + lower + numbers + symbols;

        System.out.print("Enter password length: ");
        int length = sc.nextInt();

        String password = "";

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(allChars.length());
            password += allChars.charAt(index);
        }

        System.out.println("Generated Password: " + password);

        sc.close();
    }
}